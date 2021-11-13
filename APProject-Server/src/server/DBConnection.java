package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection 
{
    private static Connection con = null;
	
    private static final Logger logger = LogManager.getLogger(DBConnection.class);
	
	public Connection getConnection() 
	{
		if(con == null) 
		{
			String url =  "jdbc:mysql://localhost:3306/grizzlydb";
			
			try 
			{
				con = DriverManager.getConnection(url, "root", "");
			} 
			
			catch (SQLException e) 
			{
				e.printStackTrace();
				logger.error("An sql exception occured when trying to get a connection to the database");
			}
			catch(NullPointerException e) 
			{
				e.printStackTrace();
				logger.error("An null pointer exception occured when trying to get a connection to the database");
			}
			
			catch(Exception e) 
			{
				e.printStackTrace();
				logger.error(e.getClass().getName()  +  " exception occured when trying to get a connection to the database");
			}
		}
		
		return con;
		
	}

}
