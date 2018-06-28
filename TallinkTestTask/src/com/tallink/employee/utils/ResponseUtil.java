package com.tallink.employee.utils;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharEncoding;

public class ResponseUtil {

	public static void makeSuccessJSONResponse(HttpServletResponse httpResponse, String jsonResponse) 
			throws ServletException, IOException {
		httpResponse.setStatus(HttpServletResponse.SC_OK);
		makeJSONResponse(httpResponse, jsonResponse);
	}
	
	public static void makeErrorJSONResponse(HttpServletResponse httpResponse, String jsonResponse) 
			throws ServletException, IOException {
		httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		makeJSONResponse(httpResponse, jsonResponse);
	}

	private static void makeJSONResponse(HttpServletResponse httpResponse, String jsonResponse)
			throws ServletException, IOException {
		httpResponse.setContentType("application/json");		
		httpResponse.setCharacterEncoding(CharEncoding.UTF_8);
		httpResponse.getOutputStream().write(jsonResponse.getBytes(CharEncoding.UTF_8));
		
		httpResponse.setDateHeader("Date", new Date().getTime());
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.addHeader("Cache-Control", "no-cache, no-store");
		httpResponse.addHeader("Pragma", "no-cache");
	}

}
