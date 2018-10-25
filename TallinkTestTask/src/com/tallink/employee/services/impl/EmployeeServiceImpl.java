package com.tallink.employee.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tallink.employee.dao.IEmployeeDAO;
import com.tallink.employee.dao.base.DAOFactory;
import com.tallink.employee.dao.base.DerbyDAOFactory;
import com.tallink.employee.model.EmployeeException;
import com.tallink.employee.model.Errors;
import com.tallink.employee.model.beans.EmployeeRequestBean;
import com.tallink.employee.model.beans.EmployeeResponseBean;
import com.tallink.employee.model.beans.ErrorBean;
import com.tallink.employee.model.data.EmployeeData;
import com.tallink.employee.services.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {
	private final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
	private final DAOFactory daoFactory;

	private final static Object editDeleteLock = new Object();
	
	
	public EmployeeServiceImpl() throws Exception  {
		daoFactory = DerbyDAOFactory.getInstance();		
	}

	@Override
	public EmployeeResponseBean getAllEmployees() throws Exception {
		List<EmployeeData> employeeList = daoFactory.getEmployerDAO().getAllEmployees();
		
		for (EmployeeData data : employeeList) {
			System.out.println(data);
		}
		
		return makeEmployeeHierarchy(employeeList);
	}

	@Override
	public void addEmployee(EmployeeRequestBean employeeBean) throws Exception {
		EmployeeData employeeData = new EmployeeData(employeeBean);
		IEmployeeDAO employeeDAO = daoFactory.getEmployerDAO(); 
		
		synchronized (editDeleteLock) {
			EmployeeData managerData = employeeDAO.getEmployeeById(employeeData.getManagerId());
			if (managerData == null) {
				throw new EmployeeException(new ErrorBean(Errors.MANAGER_ID_NOT_EXIST));
			}
			//
			employeeData.setLevel(managerData.getLevel()+1);
			daoFactory.getEmployerDAO().addEmployee(employeeData);
		}
	}

	@Override
	public void deleteEmployee(EmployeeRequestBean employeeBean) throws Exception {
		EmployeeData employeeData = null;
		IEmployeeDAO employeeDAO = daoFactory.getEmployerDAO(); 
		
		synchronized (editDeleteLock) {
			//1. get full info about employee
			employeeData = employeeDAO.getEmployeeById(employeeBean.getEmployeeId());
			if (employeeData == null) {
				throw new EmployeeException(new ErrorBean(Errors.EMPLOYEE_ID_NOT_EXIST)); 
			} else if (employeeData.getManagerId() == 0) {
				throw new EmployeeException(new ErrorBean(Errors.CANT_DELETE_CEO));
			}
			
			
			//2. update all managers' ids
			employeeDAO.updateManagerId(employeeData.getEmployeeId(), employeeData.getManagerId());
			
			//3. delete current employee
			employeeDAO.deleteEmployee(employeeData);			
		}	
	}
	
	private EmployeeResponseBean makeEmployeeHierarchy(List<EmployeeData> employeeList) {
        if (employeeList == null || employeeList.isEmpty()) {
            return null;
        }

        // remember ? we sorted it by level, so the first is a manager
		Map<Long, EmployeeResponseBean> employeeHierarchyMap = new HashMap<>();
		EmployeeResponseBean manager = new EmployeeResponseBean(employeeList.get(0));
        employeeHierarchyMap.put(employeeList.get(0).getEmployeeId(), manager);

		// then go other levels and we just add children
		for (int i = 1; i < employeeList.size(); i++) {
            EmployeeData data = employeeList.get(i);
			EmployeeResponseBean responseBean = new EmployeeResponseBean(data);

            //we always have a managerId, because we sorted it by level
			employeeHierarchyMap.put(data.getEmployeeId(), responseBean);
			employeeHierarchyMap.get(data.getManagerId()).addChildren(responseBean);

		}
		return manager;
	}
}

