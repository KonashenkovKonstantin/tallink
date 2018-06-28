package com.tallink.employee.utils.responses;

import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.tallink.employee.model.beans.EmployeeResponseBean;
import com.tallink.employee.model.beans.ErrorBean;

public class JSONResponse {	
	@SerializedName("meta")
	private Meta meta = new Meta();	
	
	@SerializedName("data")
	private EmployeeResponseBean responseBean;
	
	class Meta {		
		@SerializedName("isSuccessOperation")
		public boolean isSuccessOperation;
		
		@SerializedName("errors")
		public List<ErrorBean> errors;
		
	}
	
	public static JSONResponse getSuccessResponce() {
		JSONResponse resp = new JSONResponse();
		resp.meta.isSuccessOperation = true;
		resp.responseBean = null;
		return resp;
	}
	
	public static JSONResponse getSuccessResponce(EmployeeResponseBean responseBean) {
		JSONResponse resp = new JSONResponse();
		resp.meta.isSuccessOperation = true;
		resp.responseBean = responseBean;
		return resp;
	}
	
	public static JSONResponse getErrorResponce(ErrorBean errorBean) {
		return getErrorResponce(Arrays.asList(errorBean));
	}
	
	public static JSONResponse getErrorResponce(List<ErrorBean> errorsList) {
		JSONResponse resp = new JSONResponse();
		resp.meta.isSuccessOperation = false;
		resp.meta.errors = errorsList;
		return resp;
	}
}
