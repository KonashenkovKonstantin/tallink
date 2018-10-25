package com.tallink.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.tallink.employee.model.beans.ErrorBean;


public class EmployeeException extends Exception {
	private final List<ErrorBean> errors = new ArrayList<>();
	
	public EmployeeException(ErrorBean errorBean) {
		super();
		errors.add(errorBean);
	}

	public List<ErrorBean> getErrors() {
		return errors;
	}

}
