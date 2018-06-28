package com.tallink.employee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.tallink.test.EmployeeServletTest;

public class RunApiTestServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer writer = new PrintWriter(response.getOutputStream());
		
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(EmployeeServletTest.class);
		
		if (result.wasSuccessful()) {
			writer.append("Api tests are successful, number of run tests: " + result.getRunCount());
		} else {
			writer.append("Api tests failed, number of run tests: " + result.getRunCount() + ", failed tests: " +  result.getFailures());
		}
		writer.flush();
		 
	}
}
