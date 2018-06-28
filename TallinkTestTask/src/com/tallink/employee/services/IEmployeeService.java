package com.tallink.employee.services;

import com.tallink.employee.model.beans.EmployeeRequestBean;
import com.tallink.employee.model.beans.EmployeeResponseBean;

public interface IEmployeeService {
	
	EmployeeResponseBean getAllEmployees() throws Exception;
	
	void addEmployee(EmployeeRequestBean employeeBean) throws Exception;
	
	void deleteEmployee(EmployeeRequestBean employeeBean) throws Exception;

}
