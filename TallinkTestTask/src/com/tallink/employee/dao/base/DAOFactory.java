package com.tallink.employee.dao.base;

import java.sql.Connection;
import java.sql.SQLException;

import com.tallink.employee.dao.IEmployeeDAO;

public interface DAOFactory {
	
	IEmployeeDAO getEmployerDAO();
	
	Connection getConnection() throws SQLException;
	

}
