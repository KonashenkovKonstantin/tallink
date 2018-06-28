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
	private DAOFactory daoFactory;
	
	//TODO:loggings
	
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
		
		return parseEmployees(employeeList);
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
	
	private EmployeeResponseBean parseEmployees(List<EmployeeData> employeeList) {
		EmployeeResponseBean result = null;
		
		Map<Long, EmployeeResponseBean> employeeMap = new HashMap<>();
		
		for (EmployeeData data : employeeList) {
			EmployeeResponseBean responseBean = new EmployeeResponseBean(data);
			if (employeeMap.isEmpty()) {
				employeeMap.put(data.getEmployeeId(), responseBean);
				result = responseBean;
				continue;
			} 
			
			if (employeeMap.containsKey(data.getManagerId())) {
				employeeMap.put(data.getEmployeeId(), responseBean);
				employeeMap.get(data.getManagerId()).addChildren(responseBean);
			}
			
		}
		return result;
	}
}

