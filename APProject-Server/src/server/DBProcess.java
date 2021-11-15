package server;
import java.io.Serializable;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class DBProcess implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int empId;
	private int cusId;
	private int equipId;
	private int requestId;
	
	
	private static final Logger logger = LogManager.getLogger(DBProcess.class);
	
	public DBProcess() 
	{
		empId = -1;
		cusId = -1;
		equipId = -1;
		requestId = -1;
	}
	
	public DBProcess(int staffId, int cusId, int equipId, int requestId) 
	{
		this.empId = staffId;
		this.cusId = cusId;
		this.equipId = equipId;
		this.requestId = requestId;
	}
	
	public void setCusId(int cusId) 
	{
		this.cusId = cusId;
	}
	
	public void setEquipId(int equipId) 
	{
		this.equipId = equipId;
	}
	
	public void setEmpId(int staffId) 
	{
		this.empId = staffId;
	}
	
	public int getCusId() 
	{
		return cusId;
	}
	
	public void setRequestId(int requestId) 
	{
		this.requestId = requestId;
	}
	
	public int getRequestId() 
	{
		return requestId;
	}
	
	
	public void create(Connection con) 
	{
		String sql = "insert into Process(empId, cusId, equipId , requestID) values( ? , ?, ? , ? ) ; ";
		
		try
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, empId);
			pStat.setInt(2, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(4, requestId);
			
			pStat.execute();
			
			logger.info("added a new record to the process table for customer " + cusId);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An sql execption occcured when creating a new record in the process table");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occcured when creating a new record in the process table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + " exception occured when creating a new record in the process table");
		}
		
	}
	
	//should only return one record 
	public DBProcess select(int cusId , int equipId, int staffId , int requestId,  Connection con) 
	{
		String sql = "select * from Process where cusId = ? and equipId = ? and staffId = ? and requestID = ? ;";
		DBProcess process = null ; 
		
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, staffId);
			pStat.setInt(4, requestId);
			ResultSet results = pStat.executeQuery();
			
			
			if(results.next()) 
			{
				process = new DBProcess();
				process.setCusId( results.getInt("cusId") ) ;
				process.setEquipId( results.getInt("equipId") ) ;
				process.setEmpId( results.getInt("empId") ) ;
				process.setRequestId(results.getInt("requestID"));
			}
			
			
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An sql exception occured when trying to select with conditions for cusId, equipId, staffId and requestId from the process table");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occured when trying to select with conditions for cusId, equipId, staffId and requestId from the process table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + " exception occured when trying to select with conditions for cusId, equipId, staffId and requestId from the process table");
		}
		
		return process;
		
	}
	
	public ArrayList<DBProcess> readAll(Connection con) 
	{
		String sql = "select * from Process;";
		ArrayList<DBProcess> processes = null;
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			ResultSet results = pStat.executeQuery();
			
			if(results.next()) 
			{
				processes = new ArrayList<DBProcess>();
			}
			
			while(results.next()) 
			{
				DBProcess process = new DBProcess() ; 
				
				process.setCusId( results.getInt("cusId") ) ;
				process.setEquipId( results.getInt("equipId") ) ;
				process.setEmpId( results.getInt("staffId") ) ;
				process.setRequestId( results.getInt("requestID") ) ;
				
				processes.add(process);
			}
			
			
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An sql exception occured when trying to select all records from the process table");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("A null pointer exception occured when trying to select all records from the process table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + " exception occured when trying to select all records from the process table");
		}
		
		return processes;
	}
	
	public void updateStaffId(int cusId , int equipId, int staffId, int requestId, int newStaffId , Connection con) 
	{
		String sql = "update Process set staffId = ? where cusId = ? and equipId = ? and staffId = ? and requestID = ?  ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, newStaffId );
			pStat.setInt(2, cusId );
			pStat.setInt(3, equipId );
			pStat.setInt(4, staffId );
			pStat.setInt(5, requestId);
			pStat.execute();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An sql exception occured when trying to update a staffId in the process table with conditions for cusId, equipId, staffId and requestId");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("A null pointer exception occured when trying to update a staffId in the process table with conditions for cusId, equipId, staffId and requestId");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + "exception occured when trying to update a staffId in the process table with conditions for cusId, equipId, staffId and requestId");
		}
	}
	
	
	
	
	public void delete(int cusId, int equipId, int staffId , int requestId, Connection con) 
	{
		String sql = "delete from Process where cusId = ? and equipId = ? and staffId = ? and requestID = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, staffId);
			pStat.setInt(4, requestId);
			pStat.execute();
			
			logger.info("deleted a record from the process table with customer " + cusId);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying to delete a record in the process table with conditions for cusId, equipId,  staffId and requestId");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occured when trying to delete a record in the process table with conditions for cusId, equipId, staffId and requestId");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + "exception occured when trying to delete a record in the process table with conditions for cusId, equipId, staffId and requestId");
		}
	}
	
	

}