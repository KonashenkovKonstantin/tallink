package com.tallink.test;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class TestUtils {
	enum HttpMethod {
		GET, POST, PUT, DELETE, HEAD;
	}
	
	public static HttpURLConnection makeGetRequest(String requestUrl, Map<String, String> parameters) {
		return makeRequest(requestUrl, parameters, HttpMethod.GET);
	}
	
	public static HttpURLConnection makePostRequest(String requestUrl, Map<String, String> parameters) {
		return makeRequest(requestUrl, parameters, HttpMethod.POST);
	}
	
	public static HttpURLConnection makeDeleteRequest(String requestUrl, Map<String, String> parameters) {
		return makeRequest(requestUrl, parameters, HttpMethod.DELETE);
	}
	
	public static HttpURLConnection makeRequest(String requestUrl, Map<String, String> parameterMap, HttpMethod httpMethod) {
		String parameters = prepareParameters(parameterMap);
		boolean parametersInBody = (httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PUT); 
		
		if (!parametersInBody) {
			requestUrl = requestUrl + "?" + parameters;
		}
		
		try {
			String testUrl = requestUrl;			
			URL serveltUrl = new URL(testUrl);
			HttpURLConnection con =  (HttpURLConnection) serveltUrl.openConnection();
			con.setRequestMethod(httpMethod.name());
			con.setUseCaches(false);
			
			if (parametersInBody) {
				con.setDoInput(true);
				con.setDoOutput(true);
				try( DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
					wr.write(parameters.getBytes());
				} catch(Exception ee) {
					return null;
				}
			}
			
			return con;
		} catch(Exception ee) {	
			ee.printStackTrace();
			return null;
		}
		
	}
	
	
	
	public static String prepareParameters(Map<String, String> parameterMap) {
		if (parameterMap == null || parameterMap.isEmpty()) {
			return "";
		}
		
		StringBuilder parameters = new StringBuilder();
		for (String parameterName : parameterMap.keySet()) {
			if (parameters.length() != 0) {
				parameters.append("&");
			}
			parameters.append(parameterName).append("=").append(parameterMap.get(parameterName));
		}
		return parameters.toString();		
	}	
}
