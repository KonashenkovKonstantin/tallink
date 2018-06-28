package com.tallink.employee;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class RunStandaloneApplication {
	
	public static final int LOCAL_SERVER_PORT = 8088;
	public static final String CONTEXT_PATH = "/";
	public static final String PATH_TO_WAR = "TallinkTask.war";
	
	public static void main(String args[]) throws Exception {
		
		
		Server server = new Server(LOCAL_SERVER_PORT);		
		WebAppContext webapp = new WebAppContext();				
		webapp.setContextPath(CONTEXT_PATH);
		webapp.setWar(PATH_TO_WAR);	
		server.setHandler(webapp);		
		server.start();		
		server.join();
	}
}
