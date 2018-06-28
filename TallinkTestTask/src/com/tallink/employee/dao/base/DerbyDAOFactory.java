package com.tallink.employee.dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.tallink.employee.dao.IEmployeeDAO;
import com.tallink.employee.dao.impl.EmployeeDAO;

public class DerbyDAOFactory implements DAOFactory {
	private final static Logger logger = Logger.getLogger(DerbyDAOFactory.class);
	
	private static final String DB_URL = "jdbc:derby:memory:revolut;create=true";
	private static final IEmployeeDAO employeeDAO = new EmployeeDAO();
	
	private static DerbyDAOFactory instance = null;
	
	public static DerbyDAOFactory getInstance() {
		if (instance == null) {
			synchronized (DerbyDAOFactory.class) {
				if (instance == null) {
					try {
						logger.debug("Started db initialization");
						instance = new DerbyDAOFactory();
						logger.debug("Finished db initialization");
					} catch(Exception ee) {
						logger.error("Somethig went wrong during db initialization", ee);
					}
				}				
			}
		}
		return instance;
	}
	
	private DerbyDAOFactory() throws Exception {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			intiDB();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}			
	}
	
	private void intiDB() throws Exception {
		
		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			//1. create tables
			stmt.executeUpdate("CREATE TABLE Employee ("
					+ "employeeId BIGINT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "managerId BIGINT, "
					+ "level INT, "
					+ "firstName VARCHAR(30), "
					+ "secondName VARCHAR(30))");
			
			//2. add foreign key
			stmt.executeUpdate("ALTER TABLE Employee "
					+ "ADD CONSTRAINT employee_id_fk FOREIGN KEY (managerId) " 
					+ "REFERENCES Employee (employeeId)");
			
			//3. fill accounts
			//1-level
			stmt.executeUpdate("insert into Employee (level, firstName, secondName) values (1, 'bossFirstName', 'bossLastName')");
			
			//2-level
			stmt.executeUpdate("insert into Employee (managerId, level, firstName, secondName) values (1, 2, 'AaaName', 'AaaSecondName')");
			stmt.executeUpdate("insert into Employee (managerId, level, firstName, secondName) values (1, 2, 'BbbName', 'BbbSecondName')");
			
			//3-level
			stmt.executeUpdate("insert into Employee (managerId, level, firstName, secondName) values (2, 3, 'CccName', 'CccSecondName')");
			stmt.executeUpdate("insert into Employee (managerId, level, firstName, secondName) values (3, 3, 'DddName', 'DddSecondName')");
			
			//4-level
			stmt.executeUpdate("insert into Employee (managerId, level, firstName, secondName) values (4, 4, 'EeeName', 'EeeSecondName')");
			stmt.executeUpdate("insert into Employee (managerId, level, firstName, secondName) values (5, 4, 'FffName', 'FffSecondName')");
			
			
			
			
		    logger.info("Finished init Derby db");    
		} catch(Exception ee) {			
			logger.error("Something went wrong during db init", ee);
			throw ee;
		}
	}
	
	@Override
	public IEmployeeDAO getEmployerDAO() {
		return employeeDAO;
	}	
	
	
	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}

	
}
