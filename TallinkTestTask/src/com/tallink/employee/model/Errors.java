package com.tallink.employee.model;

public enum Errors {
	
	SMTH_WENT_WRONG(5000, "Something went wrong"),
	DATA_FORMAT_WRONG(5001, "Wrong data format"),
	CANT_DELETE_CEO(5002, "You can't delete CEO"),
	
	MANAGER_ID_EMPTY(6100, "Manager id is empty"),
	MANAGER_ID_INVALID(6101, "Manager id isn't valid"),
	MANAGER_ID_NEGATIVE(6102, "Manager id can't be negative"),
	MANAGER_ID_NOT_EXIST(6103, "Manager id doesn't exist"),
	
	FIRST_NAME_EMPTY(6200, "First name is empty"),
	FIRST_NAME_INVALID(6201, "First name isn't valid"),	
	
	SECOND_NAME_EMPTY(6300, "Second name is empty"),
	SECOND_NAME_INVALID(6301, "Second name isn't valid"),
	
	EMPLOYEE_ID_EMPTY(6100, "Employee id is empty"),
	EMPLOYEE_ID_INVALID(6101, "Employee id isn't valid"),
	EMPLOYEE_ID_NEGATIVE(6102, "Employee id can't be negative"),
	EMPLOYEE_ID_NOT_EXIST(6103, "Employee id doesn't exist");
	
	
	private int errorCode;
	private String errorDescription;
	
	Errors(int errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
}
