package main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import server.Customer;
import server.View;

public class Driver {
	
	private static final Logger logger = LogManager.getLogger(Driver.class);
	

	public static void main(String[] args) 
	{   
		
		
		logger.error("check");
		// TODO Auto-generated method stub
		new View();
	}

}
