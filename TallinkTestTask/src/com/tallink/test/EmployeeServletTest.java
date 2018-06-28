package com.tallink.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class EmployeeServletTest {
	
	private static final String URL_GET_ALL_EMPLOYEE = "http://localhost:8088/employees";
	private static final String URL_ADD_EMPLOYEE = "http://localhost:8088/employees?managerId=%s&firstName=%s&secondName=%s";
	private static final String URL_DELETE_EMPLOYEE = "http://localhost:8088/employees?employeeId=%s";
	

	@Test
	public void getAllEmployeePositiveTest() {

		try {
			String testUrl = URL_GET_ALL_EMPLOYEE;			
			URL serveltUrl = new URL(testUrl);
			HttpURLConnection con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("GET");			
			int responseCode = con.getResponseCode();			
			assertEquals(200, responseCode);
		} catch(Exception ee) {						
			fail();
		}
	}
	
	@Test
	public void addEmployeePositiveTest() {

		try {
			String managerId = "3";
			String firstName = "TestFirstName";
			String secondName = "TestSecondName";			
			
			String testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			URL serveltUrl = new URL(testUrl);
			HttpURLConnection con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);

			
			int responseCode = con.getResponseCode();			
			assertEquals(200, responseCode);
		} catch(Exception ee) {						
			fail();
		}
	}

	@Test
	public void addEmployeeNegativeTest() {

		try {
			
			//1. manager id is empty
			String managerId = "";
			String firstName = "TestFirstName";
			String secondName = "TestSecondName";
			String testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			URL serveltUrl = new URL(testUrl);
			HttpURLConnection con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			int responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//2. manager id is negative 
			managerId = "-1";			
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);

			//3. manager id is not a number 
			managerId = "eq";			
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);			
			
			//4. manager id is not a integer 
			managerId = "20.6";			
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			//5. manager id is too small 
			managerId = "-922337203685477580700";			
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//6. manager id is too big 
			managerId = "922337203685477580700";			
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//set back correct managerId
			managerId = "3";
			
			//7. empty firstName			
			firstName = "";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//8. to short firstName
			firstName = "e";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//9. too long firstName
			firstName = "rewrrrrrrrrrrrrrrrrrrrrrrreweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			
			//10. not only letters in firstName
			firstName = "4234rwerwe";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//set correct firstName
			firstName = "TestFirstName";
			
			//11. empty secondName			
			secondName = "";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//12. to short secondName
			secondName = "r";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//13. to long secondName
			secondName = "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeeeeeefffffffffffffffff";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//14. not only letters in secondName
			secondName = "qeq332";
			testUrl = String.format(URL_ADD_EMPLOYEE, managerId, firstName, secondName);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("POST");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
		} catch(Exception ee) {						
			fail();
		}
	}

	@Test
	public void deleteEmployeePositiveTest() {

		try {
			String employeeId = "3";
					
			
			String testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			URL serveltUrl = new URL(testUrl);
			HttpURLConnection con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("GET");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			int responseCode = con.getResponseCode();			
			assertEquals(200, responseCode);
		} catch(Exception ee) {						
			fail();
		}
	}
	
	@Test
	public void deleteEmployeeNegativeTest() {

		try {
			
			//1. employee id is empty
			String employeeId = "";			
			String testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			URL serveltUrl = new URL(testUrl);
			HttpURLConnection con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			int responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//2. employee id is negative 
			employeeId = "-1";			
			testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);

			//3. employee id is not a number 
			employeeId = "rwerw";			
			testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);			
			
			//4. employee id is not a integer 
			employeeId = "20.6";			
			testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			//5. employee id is too small 
			employeeId = "-922337203685477580700";			
			testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//6. employee id is too big 
			employeeId = "922337203685477580700";			
			testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
			
			//7. delete ceo 
			employeeId = "1";			
			testUrl = String.format(URL_DELETE_EMPLOYEE, employeeId);			
			serveltUrl = new URL(testUrl);
			con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod("DELETE");	
			con.setDoInput(true);
			con.setDoOutput(true);
			
			responseCode = con.getResponseCode();			
			assertEquals(500, responseCode);
		} catch(Exception ee) {						
			fail();
		}
	}
}
