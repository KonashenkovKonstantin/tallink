package com.tallink.employee.model.beans;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.tallink.employee.model.data.EmployeeData;

public class EmployeeResponseBean {
	@SerializedName("employeeId")
	private long employeeId;
	@SerializedName("text")
	private String employeeLabel;
		
	private long managerId;
	private String firstName;	
	private String secondName;
	
	@SerializedName("nodes")
	private List<EmployeeResponseBean> childrens =  null;
	
	public EmployeeResponseBean(EmployeeData employeeData) {
		this.employeeId = employeeData.getEmployeeId();
		this.managerId = employeeData.getManagerId();
		this.firstName = employeeData.getFirstName();
		this.secondName = employeeData.getSecondName();
		this.employeeLabel = getEmployerLabel();
		
	}
	
	public void addChildren(EmployeeResponseBean employeeResponseBean) {
		if (childrens == null) {
			childrens = new ArrayList<>(); 
		}
		childrens.add(employeeResponseBean);
	}
	
	
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmployeeResponseBean> getChildrens() {
		return childrens;
	}

	public String getEmployerLabel() {
		return this.firstName + " / " + this.secondName; 
	}
	
}
