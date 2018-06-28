package com.tallink.employee.utils.parsers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.tallink.employee.model.beans.EmployeeRequestBean;

public class RequestParser {
	
	private static final String PARAMETER_EMPLOYEE_ID = "employeeId";
	private static final String PARAMETER_MANAGER_ID = "managerId"; 
	private static final String PARAMETER_FIRST_NAME = "firstName";
	private static final String PARAMETER_SECOND_NAME = "secondName";
	
	public static EmployeeRequestBean parseEmployeeRequest(HttpServletRequest request) {
		EmployeeRequestBean employeeBean = new EmployeeRequestBean();
		
		String employeeId = request.getParameter(PARAMETER_EMPLOYEE_ID);		
		if (StringUtils.isNotEmpty(employeeId)) {
			employeeBean.setEmployeeId(Long.parseLong(employeeId.trim()));
		}		

		String managerId = request.getParameter(PARAMETER_MANAGER_ID);		
		if (StringUtils.isNotEmpty(managerId)) {
			employeeBean.setManagerId(Long.parseLong(managerId.trim()));
		}		
		
		String firstName = request.getParameter(PARAMETER_FIRST_NAME);
		if (StringUtils.isNotBlank(firstName)) {
			employeeBean.setFirstName(firstName.trim());
		}
		
		String secondName = request.getParameter(PARAMETER_SECOND_NAME);
		if (StringUtils.isNotBlank(secondName)) {
			employeeBean.setSecondName(secondName.trim());
		}		
		return employeeBean;
	}
}
