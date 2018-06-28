package com.tallink.employee.model.data;

import com.tallink.employee.model.beans.EmployeeRequestBean;

public class EmployeeData {
	
	private int level;
	private long employeeId;
	private String firstName;
	private String secondName;
	private long managerId;
	
	public EmployeeData() {
		
	}
	
	public EmployeeData(EmployeeRequestBean employeeBean) {
		this.employeeId = employeeBean.getEmployeeId();
		this.firstName = employeeBean.getFirstName();
		this.secondName= employeeBean.getSecondName();
		this.managerId = employeeBean.getManagerId();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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

	@Override
	public String toString() {
		return "EmployeeData [level=" + level + ", employeeId=" + employeeId + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", managerId=" + managerId + "]";
	}
	
	

}
