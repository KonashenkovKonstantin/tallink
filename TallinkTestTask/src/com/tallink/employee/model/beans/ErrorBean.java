package com.tallink.employee.model.beans;

import com.google.gson.annotations.SerializedName;
import com.tallink.employee.model.Errors;

public class ErrorBean {
	
	@SerializedName("errorCode")
	private int errorCode;
	@SerializedName("errorDescription")
	private String errorDescription;
	
		
	public ErrorBean(Errors error) {
		this.errorCode = error.getErrorCode();
		this.errorDescription = error.getErrorDescription();
	}


	@Override
	public String toString() {
		return "ErrorBean [errorCode=" + errorCode + ", errorDescription=" + errorDescription + "]";
	}

}
