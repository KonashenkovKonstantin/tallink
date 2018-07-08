package com.tallink.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class EmployeeServletTest {
	
	private static final String URL_GET_ALL_EMPLOYEE = "http://localhost:8088/employees";
	private static final String URL_ADD_EMPLOYEE = "http://localhost:8088/employees";
	private static final String URL_DELETE_EMPLOYEE = "http://localhost:8088/employees";

	
	@Test
	public void getAllEmployeePositiveTest() throws IOException {
		String testUrl = URL_GET_ALL_EMPLOYEE;
		assertEquals(200, TestUtils.makeGetRequest(testUrl, null).getResponseCode());		
	}
	
	@Test
	public void addEmployeePositiveTest() throws IOException {
		Map<String, String> parameters = new HashMap<String, String>(3);
		parameters.put("managerId", "1");
		parameters.put("firstName", "TestFirstName");
		parameters.put("secondName", "TestSecondName");		
		assertEquals(200, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
	}

	@Test
	public void addEmployeeNegativeTest() throws IOException {		
		//1. manager id is empty
		Map<String, String> parameters = new HashMap<String, String>(3);
		parameters.put("managerId", "");
		parameters.put("firstName", "TestFirstName");
		parameters.put("secondName", "TestSecondName");
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());

			
		//2. manager id is negative 
		parameters.put("managerId", "-1");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
	

		//3. manager id is not a number 
		parameters.put("managerId", "eq");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());		
		
		//4. manager id is not a integer
		parameters.put("managerId", "20.6");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
			
		//5. manager id is too small
		parameters.put("managerId", "-922337203685477580700");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
			
		//6. manager id is too big
		parameters.put("managerId", "922337203685477580700");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());	
			
		//set back correct managerId		
		parameters.put("managerId", "1");
			
		//7. empty firstName			
		parameters.put("firstName", "");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());		
			
		//8. to short firstName		
		parameters.put("firstName", "e");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
			
		//9. too long firstName		
		parameters.put("firstName", "rewrrrrrrrrrrrrrrrrrrrrrrreweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
			
		//10. not only letters in firstName
		parameters.put("firstName", "4234rwerwe");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());		
			
		//set correct firstName
		parameters.put("firstName", "TestFirstName");
			
		//11. empty secondName
		parameters.put("secondName", "");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());		
		
		//12. to short secondName
		parameters.put("secondName", "r");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
			
		//13. to long secondName
		parameters.put("secondName", "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeeeeeefffffffffffffffff");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());
		
		//14. not only letters in secondName
		parameters.put("secondName", "qeq332");		
		assertEquals(500, TestUtils.makePostRequest(URL_ADD_EMPLOYEE, parameters).getResponseCode());		
	}

	@Test
	public void deleteEmployeePositiveTest() throws IOException {
		Map<String, String> parameters = new HashMap<String, String>(1);
		parameters.put("employeeId", "3");		
		assertEquals(200, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());		
	}
	
	@Test
	public void deleteEmployeeNegativeTest() throws IOException {
		//1. employee id is empty
		Map<String, String> parameters = new HashMap<String, String>(1);
		parameters.put("employeeId", "");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());		
			
		//2. employee id is negative
		parameters.put("employeeId", "-1");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());
		
		//3. employee id is not a number
		parameters.put("employeeId", "rwerw");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());
		
		//4. employee id is not a integer 
		parameters.put("employeeId", "20.6");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());		
					
		//5. employee id is too small
		parameters.put("employeeId", "-922337203685477580700");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());		
			
		//6. employee id is too big
		parameters.put("employeeId", "922337203685477580700");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());		
		
		//7. delete ceo 
		parameters.put("employeeId", "1");				
		assertEquals(500, TestUtils.makeDeleteRequest(URL_DELETE_EMPLOYEE, parameters).getResponseCode());
	}
	
	
}
