package com.tallink.employee.dao;

import java.sql.SQLException;
import java.util.List;

import com.tallink.employee.model.data.EmployeeData;

public interface IEmployeeDAO {
	
	List<EmployeeData> getAllEmployees() throws SQLException;
	
	EmployeeData getEmployeeById(long employeeId) throws SQLException;
	
	void addEmployee(EmployeeData employeeData) throws SQLException;
	
	void deleteEmployee(EmployeeData employeeData) throws SQLException;
	
	void updateManagerId(long oldManagerId, long newManagerId) throws SQLException;
	
}
