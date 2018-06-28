package com.tallink.employee.services;

import org.apache.log4j.Logger;

import com.tallink.employee.services.impl.EmployeeServiceImpl;

public class ServiceLocator {
	private static final Logger logger  = Logger.getLogger(ServiceLocator.class);
	private static IEmployeeService employeeService;
	
	static {
		synchronized (ServiceLocator.class) {
			try {
				logger.info("Started service locator initialization");
				employeeService = new EmployeeServiceImpl();
				logger.info("Finished service locator initialization");
			} catch (Exception ee) {
				logger.error("Error occured during serviceLocator initialization", ee);
				throw new RuntimeException(ee);
			}
			
		}
	}

	public static IEmployeeService getEmployeeService() {
		return employeeService;
	}
	
}
