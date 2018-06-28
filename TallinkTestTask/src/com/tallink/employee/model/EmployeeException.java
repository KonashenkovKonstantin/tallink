package com.tallink.employee.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tallink.employee.model.beans.ErrorBean;


public class EmployeeException extends Exception {
	private List<ErrorBean> errors = new ArrayList<>();
	
	public EmployeeException(ErrorBean errorBean) {
		super();
		this.errors  = Arrays.asList(errorBean);
	}
	
	public EmployeeException(List<ErrorBean> errors) {
		super();
		this.errors = errors;
	}

	public List<ErrorBean> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorBean> errors) {
		this.errors = errors;
	}
}
