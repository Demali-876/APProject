package controller;
import model.*;
import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Driver 
{
	
	private static final Logger logger = LogManager.getLogger(Driver.class);
	

	public static void main(String[] args) 
	{
		logger.info("Test Info Message");
		logger.debug("Test debug Message");
		logger.error("Test error Message");
		logger.trace("Test trace Message");
		logger.fatal("Test Fatal Message");
		logger.warn("Test Warning Message");

	}

}
