package com.tallink.employee.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.tallink.employee.model.Errors;
import com.tallink.employee.model.beans.EmployeeRequestBean;
import com.tallink.employee.model.beans.ErrorBean;

public class ValidationUtil {
	
	private static final String REGEX_FIRST_NAME = "^[a-zA-z]{3,30}$";
	private static final String REGEX_SECOND_NAME = "^[a-zA-z]{3,30}$";
	
	private static final Pattern PATTERN_FIRST_NAME = Pattern.compile(REGEX_FIRST_NAME);
	private static final Pattern PATTERN_SECOND_NAME = Pattern.compile(REGEX_SECOND_NAME);
	
	public static List<ErrorBean> validateAddEmployeeOperation(EmployeeRequestBean employeeBean) {
		List<ErrorBean> list = new ArrayList<>();

		//manager id
		if (employeeBean.getManagerId() == 0) {
			list.add(new ErrorBean(Errors.MANAGER_ID_EMPTY));
		} else if (employeeBean.getManagerId() < 0) {
			list.add(new ErrorBean(Errors.MANAGER_ID_NEGATIVE));
		}
		
		//first name
		if (StringUtils.isEmpty(employeeBean.getFirstName())) {
			list.add(new ErrorBean(Errors.FIRST_NAME_EMPTY));
		} else if (!isValidFirstName(employeeBean.getFirstName())) {
			list.add(new ErrorBean(Errors.FIRST_NAME_INVALID));
		}
		
		//second name
		if (StringUtils.isEmpty(employeeBean.getSecondName())) { 
			list.add(new ErrorBean(Errors.SECOND_NAME_EMPTY));
		} else if (!isValidSecondName(employeeBean.getSecondName())) { 
			list.add(new ErrorBean(Errors.SECOND_NAME_INVALID));			
		}
		
		return list;
	}
	
	public static List<ErrorBean> validateDeleteEmployeeOperation(EmployeeRequestBean employeeBean) {
		List<ErrorBean> list = new ArrayList<>();
		
		if (employeeBean.getEmployeeId() == 0) {
			list.add(new ErrorBean(Errors.EMPLOYEE_ID_EMPTY));
		} else if (employeeBean.getEmployeeId() < 0) {
			list.add(new ErrorBean(Errors.EMPLOYEE_ID_NEGATIVE));
		}
		
		return list;
	}
	
	private static boolean isValidFirstName(String firstName) {
		return PATTERN_FIRST_NAME.matcher(firstName).matches();
	}
	
	private static boolean isValidSecondName(String secondName) {
		return PATTERN_SECOND_NAME.matcher(secondName).matches();
	}

}
