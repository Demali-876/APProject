package model;

import java.io.Serializable;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBProcess implements Serializable
{
	private int staffId;
	private int cusId;
	private int equipId;
	
	
	private static final Logger logger = LogManager.getLogger(DBProcess.class);
	
	public DBProcess() 
	{
		staffId = 0;
		cusId = 0;
		equipId = 0;
	}
	
	public DBProcess(int staffId, int cusId, int equipId) 
	{
		this.staffId = staffId;
		this.cusId = cusId;
		this.equipId = equipId;
	}
	
	public void setCusId(int cusId) 
	{
		this.cusId = cusId;
	}
	
	public void setEquipId(int equipId) 
	{
		this.equipId = equipId;
	}
	
	public void setStaffId(int staffId) 
	{
		this.staffId = staffId;
	}
	
	public int getCusId() 
	{
		return cusId;
	}
	
	public void create(Connection con) 
	{
		String sql = "insert into Process(staffId, cusId, equipId) values( ? , ?, ? ) ; ";
		
		try
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, staffId);
			pStat.setInt(2, cusId);
			pStat.setInt(2, equipId);
			
			pStat.execute();
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.fatal("An sql execption occcured when creating a new record in the process table");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	// can be modified to get a string out of this 
	public void select(int cusId , int equipId, int staffId ,  Connection con) 
	{
		String sql = "select * from Process where cusId = ? and equipId = ? and staffId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, staffId);
			ResultSet results = pStat.executeQuery();
			String output = "";
			while(results.next()) 
			{
				output += "\n Customer Id: " + results.getString("cusId") ;
				output += "\n Equipment Id: " + results.getString("equipId");
				output += "\n Staff Id: " + results.getString("staffId");
			}
			
			System.out.println(output);
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void readAll(Connection con) 
	{
		String sql = "select * from Process;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			ResultSet results = pStat.executeQuery();
			String output = "";
			while(results.next()) 
			{
				output += "\n Customer Id: " + results.getString("cusId") ;
				output += "\n Equipment Id: " + results.getString("equipId");
				output += "\n Staff Id: " + results.getString("staffId");
			}
			
			System.out.println(output);
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateStaffId(int cusId , int equipId, int staffId, int newStaffId , Connection con) 
	{
		String sql = "update Process set staffId = ? where cusId = ? and equipId = ? and staffId = ?  ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, newStaffId );
			pStat.setInt(2, cusId );
			pStat.setInt(3, equipId );
			pStat.setInt(4, staffId );
			pStat.execute();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public void delete(int cusId, int equipId, int staffId , Connection con) 
	{
		String sql = "delete from Process where cusId = ? and equipId = ? and staffId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, staffId);
			pStat.execute();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	

}
