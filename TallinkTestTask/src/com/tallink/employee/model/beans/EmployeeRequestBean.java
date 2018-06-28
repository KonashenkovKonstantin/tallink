package com.tallink.employee.model.beans;

public class EmployeeRequestBean {
	
	private long employeeId;
	private String firstName;
	private String secondName;
	private long managerId;
		
	
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
	@Override
	public String toString() {
		return "EmployeeBean [employeeId=" + employeeId + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", managerId=" + managerId + "]";
	}

}
