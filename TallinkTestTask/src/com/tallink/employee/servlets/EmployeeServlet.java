package com.tallink.employee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tallink.employee.model.EmployeeException;
import com.tallink.employee.model.Errors;
import com.tallink.employee.model.beans.EmployeeRequestBean;
import com.tallink.employee.model.beans.EmployeeResponseBean;
import com.tallink.employee.model.beans.ErrorBean;
import com.tallink.employee.services.IEmployeeService;
import com.tallink.employee.services.ServiceLocator;
import com.tallink.employee.utils.ResponseUtil;
import com.tallink.employee.utils.ValidationUtil;
import com.tallink.employee.utils.parsers.RequestParser;
import com.tallink.employee.utils.responses.JSONResponse;


public class EmployeeServlet extends HttpServlet {
	
	private final static Gson gson = new Gson();
	private final static Logger logger = Logger.getLogger(EmployeeServlet.class);
       
  

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call service
		logger.debug("Started handling request 'getAllEmployee'" );
		IEmployeeService employeeService = ServiceLocator.getEmployeeService();
		try {
			EmployeeResponseBean employeeResponseBean = employeeService.getAllEmployees();
			JSONResponse successResponse = JSONResponse.getSuccessResponce(employeeResponseBean);
			ResponseUtil.makeSuccessJSONResponse(response, gson.toJson(successResponse));
			
			logger.debug("Finished 'getAllEmployee' request");
		} catch (EmployeeException ee) {
			logger.error("Error occured during 'getAllEmployee' request.", ee);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(ee.getErrors());
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		} catch (Exception e) {
			logger.error("Error occured during 'getAllEmployee' request.", e);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.SMTH_WENT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//parse
		EmployeeRequestBean employeeRequestBean = null;
		try {
			employeeRequestBean = RequestParser.parseEmployeeRequest(request);
		} catch(NumberFormatException nfe) {
			logger.error("Error occured during 'addEmployee' request.", nfe);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.DATA_FORMAT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		} catch (Exception e) {
			logger.error("Error occured during 'addEmployee' request.", e);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.SMTH_WENT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		}
		
		logger.debug("Started handling request 'addEmployee'. Employee: " + employeeRequestBean);
		
		//validate
		List<ErrorBean> errorList = ValidationUtil.validateAddEmployeeOperation(employeeRequestBean);
		if (!errorList.isEmpty()) {
			JSONResponse errorResponse = JSONResponse.getErrorResponce(errorList);
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			logger.debug("Data in request isn't valid: " + employeeRequestBean + ", errors: "+ errorList.toString());
			return;
		}
		
		//call service
		IEmployeeService employeeService = ServiceLocator.getEmployeeService();
		try {
			employeeService.addEmployee(employeeRequestBean);
			JSONResponse successResponse = JSONResponse.getSuccessResponce();
			ResponseUtil.makeSuccessJSONResponse(response, gson.toJson(successResponse));
			logger.debug("Finished handling request 'addEmployee'. Employee: " + employeeRequestBean);
		} catch (EmployeeException ee) {
			logger.error("Error occured during hangling request 'addEmployee'. Employee: " + employeeRequestBean, ee);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(ee.getErrors());
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		} catch (Exception e) {
			logger.error("Error occured during hangling request 'addEmployee'. Employee: " + employeeRequestBean, e);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.SMTH_WENT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		}
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//parse
		EmployeeRequestBean employeeRequestBean = null;
		try {
			employeeRequestBean = RequestParser.parseEmployeeRequest(request);
		} catch(NumberFormatException nfe) {
			logger.error("Error occured during 'deleteEmployee' request.", nfe);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.DATA_FORMAT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		} catch (Exception e) {
			logger.error("Error occured during 'deleteEmployee' request.", e);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.SMTH_WENT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		}
		
		logger.debug("Started handling request 'removeEmployee'. Employee: " + employeeRequestBean);
		
		//validate
		List<ErrorBean> errorList = ValidationUtil.validateDeleteEmployeeOperation(employeeRequestBean);
		if (!errorList.isEmpty()) {
			JSONResponse errorResponse = JSONResponse.getErrorResponce(errorList);
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			logger.debug("Data in request isn't valid: " + employeeRequestBean + ", errors: "+ errorList.toString());
			return;
		}
		
		//call service
		IEmployeeService employeeService = ServiceLocator.getEmployeeService();
		try {
			employeeService.deleteEmployee(employeeRequestBean);
			logger.debug("Finished handling request 'removeEmployee'. Employee: " + employeeRequestBean);
		} catch (EmployeeException ee) {
			logger.error("Error occured during hangling request 'removeEmployee'. Employee: " + employeeRequestBean, ee);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(ee.getErrors());
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		} catch (Exception e) {
			logger.error("Error occured during hangling request 'removeEmployee'. Employee: " + employeeRequestBean, e);
			JSONResponse errorResponse = JSONResponse.getErrorResponce(new ErrorBean(Errors.SMTH_WENT_WRONG));
			ResponseUtil.makeErrorJSONResponse(response, gson.toJson(errorResponse));
			return;
		}
	}

}
