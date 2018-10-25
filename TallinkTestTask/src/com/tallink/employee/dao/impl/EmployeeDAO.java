package com.tallink.employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tallink.employee.dao.IEmployeeDAO;
import com.tallink.employee.dao.base.DerbyDAOFactory;
import com.tallink.employee.model.data.EmployeeData;

public class EmployeeDAO implements IEmployeeDAO {
	
	private static final String SQL_SELECT_ALL_EMPLOYEES = "Select * from Employee order by level";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID = "Select * from Employee where employeeId = ?";

    private static final String SQL_INSERT_NEW_EMPLOYEE = "Insert into Employee (managerId, level, firstName, secondName) values (?, ?, ?, ?)";

    private static final String SQL_UPDATE_MANAGER_IDS = "Update Employee set managerId = ? where managerId = ?";

    private static final String SQL_DELETE_EMPLOYEE = "Delete from Employee where employeeId = ?";
	

	@Override
	public List<EmployeeData> getAllEmployees() throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;
		ResultSet rs = null; 
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_SELECT_ALL_EMPLOYEES);			
			prep.executeQuery();			
			
			rs = prep.getResultSet();
			List<EmployeeData> result = new ArrayList<>();
			while(rs.next()) {
				EmployeeData employeeData = new EmployeeData();
				employeeData.setEmployeeId(rs.getLong("employeeId"));
				employeeData.setManagerId(rs.getLong("managerId"));
				employeeData.setLevel(rs.getInt("level"));
				employeeData.setFirstName(rs.getString("firstName"));
				employeeData.setSecondName(rs.getString("secondName"));
				result.add(employeeData);
			}
			return result;
			
		} catch (SQLException se) {
			throw se;
		} finally {
			if (rs != null) {rs.close();}
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}			
		}		
	}

	@Override
	public EmployeeData getEmployeeById(long employeeId) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;
		ResultSet rs = null; 
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
			prep.setLong(1, employeeId);
			prep.executeQuery();			
			
			rs = prep.getResultSet();
			EmployeeData employeeData = new EmployeeData();
			while(rs.next()) {
				employeeData.setEmployeeId(rs.getLong("employeeId"));
				employeeData.setManagerId(rs.getLong("managerId"));
				employeeData.setLevel(rs.getInt("level"));
				employeeData.setFirstName(rs.getString("firstName"));
				employeeData.setSecondName(rs.getString("secondName"));
			}
			return employeeData;
			
		} catch (SQLException se) {
			throw se;
		} finally {
			if (rs != null) {rs.close();}
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}			
		}		
	}

	@Override
	public void addEmployee(EmployeeData employeeData) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;		
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_INSERT_NEW_EMPLOYEE);
			prep.setLong(1, employeeData.getManagerId());
			prep.setInt(2, employeeData.getLevel());
			prep.setString(3, employeeData.getFirstName());
			prep.setString(4, employeeData.getSecondName());
			prep.executeUpdate();
			
		} catch (SQLException se) {
			throw se;
		} finally {			
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}			
		}		
	
				
		
	}

	@Override
	public void deleteEmployee(EmployeeData employeeData) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;		
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_DELETE_EMPLOYEE);
			prep.setLong(1, employeeData.getEmployeeId());			
			prep.executeUpdate();
			
		} catch (SQLException se) {
			throw se;
		} finally {			
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}			
		}		
		
	}

	@Override
	public void updateManagerId(long oldManagerId, long newManagerId) throws SQLException {
		Connection con = null;
		PreparedStatement prep = null;		
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_UPDATE_MANAGER_IDS);
			prep.setLong(1, newManagerId);			
			prep.setLong(2, oldManagerId);
			prep.executeUpdate();
			
		} catch (SQLException se) {
			throw se;
		} finally {			
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}			
		}	
		
	}
	
	private Connection getConnection() throws SQLException {
		return DerbyDAOFactory.getInstance().getConnection();
	}
}
