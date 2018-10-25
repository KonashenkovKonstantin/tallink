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
	private List<EmployeeResponseBean> children =  null;
	
	public EmployeeResponseBean(EmployeeData employeeData) {
		this.employeeId = employeeData.getEmployeeId();
		this.managerId = employeeData.getManagerId();
		this.firstName = employeeData.getFirstName();
		this.secondName = employeeData.getSecondName();
		this.employeeLabel = getEmployerLabel();
		
	}
	
	public void addChildren(EmployeeResponseBean employeeResponseBean) {
		if (children == null) {
			children = new ArrayList<>();
		}
		children.add(employeeResponseBean);
	}
	
	public String getFirstName() {
		return firstName;
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



	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmployeeResponseBean> getChildren() {
		return children;
	}

	public String getEmployerLabel() {
		return this.firstName + " / " + this.secondName; 
	}
	
}
