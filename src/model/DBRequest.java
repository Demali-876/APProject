package model;

import java.io.Serializable;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBRequest implements Serializable
{
	private int requestId;
	private int transactionId;
	private String dateRequested;
	private String dateReserved;
	private String requestStatus;
	private float cost;
	
	private int cusId;
	private int equipId;
	
	private static final Logger logger = LogManager.getLogger(DBRequest.class);
	
	public DBRequest() 
	{
		requestId = 0;
		transactionId = 0;
		dateRequested = "";
		dateReserved = "";
		requestStatus = "";
		cost = 0f;
		cusId = 0;
		equipId = 0;
	}
	
	public DBRequest(int requestId, int transactionId, String dateRequested, String dateReserved, String requestStatus, float cost, int cusId, int equipId) 
	{
		this.requestId = requestId;
		this.transactionId = transactionId;
		this.dateRequested = dateRequested;
		this.dateReserved = dateReserved;
		this.requestStatus = requestStatus;
		this.cost = cost;
		this.cusId = cusId;
		this.equipId = equipId;
	}
	
	public void setRequestId(int requestId) 
	{
		this.requestId = requestId;
	}
	
	public void setTransactionId(int transactionId) 
	{
		this.transactionId = transactionId;
	}
	
	public void setDateRequested(String dateRequested) 
	{
		this.dateRequested = dateRequested;
	}
	
	public void setDateReserved(String dateReserved) 
	{
		this.dateReserved = dateReserved;
	}
	
	public void setRequestStatus(String requestStatus) 
	{
		this.requestStatus = requestStatus;
	}
	
	public void setCost(float cost) 
	{
		this.cost = cost;
	}
	
	public void setCusId(int cusId) 
	{
		this.cusId = cusId;
	}
	
	public void setEquipId(int equipId) 
	{
		this.transactionId = transactionId;
	}
	
	
	public int getRequestId() 
	{
		return requestId;
	}
	
	public int getTransactionId() 
	{
		return transactionId;
	}
	
	public int getCusId() 
	{
		return cusId;
	}
	
	public int getEquipId() 
	{
		return equipId;
	}
	
	public float getCost() 
	{
		return cost;
	}
	
	public String getDateRequested() 
	{
		return dateRequested;
	}
	
	public String getDateReserved() 
	{
		return dateReserved;
	}
	
	public String getRequestStatus() 
	{
		return requestStatus;
	}
	
	public void Create(Connection con) 
	{
		String sql = "insert into Request(requestId, transactionId, dateRequested, dateReserved, requestStatus, cost, cusId, equipId) values( ? , ? , ?, ?, ?, ?, ? , ?  ) ; ";
		
		try
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, requestId);
			pStat.setInt(2, transactionId);
			pStat.setString(3, dateRequested);
			pStat.setString(4, dateReserved);
			pStat.setString(5, requestStatus);
			pStat.setFloat(6, cost);
			pStat.setInt(7, cusId);
			pStat.setInt(8, equipId);
			pStat.execute();
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.fatal("An sql execption occcured when creating a new record in the request table");
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
	
	
	public void select(int cusId , int equipId, int requestId ,  Connection con) 
	{
		String sql = "select * from Request where cusId = ? and equipId = ? and requestId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, requestId);
			ResultSet results = pStat.executeQuery();
			String output = "";
			while(results.next()) 
			{
				output += "\n Customer Id: " + results.getString("cusId") ;
				output += "\n Equipment Id: " + results.getString("equipId");
				output += "\n Request Id: " + results.getString("requestId");
				output += "\n Transaction Id: " + results.getString("transactionId");
				output += "\n Date Requested (yyyy/mm/dd)"+ ": " + results.getString("dateRequested");
				output += "\n Date Reserved (yyyy/mm/dd)"+ ": " + results.getString("dateReserved");
				output += "\n Request Status" + ": " + results.getString("requestStatus");
				output += "\n Cost "+ ": " + results.getString("cost");
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
		String sql = "select * from Request ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			ResultSet results = pStat.executeQuery();
			String output = "";
			while(results.next()) 
			{
				output += "\n Customer Id: " + results.getString("cusId") ;
				output += "\n Equipment Id: " + results.getString("equipId");
				output += "\n Request Id: " + results.getString("requestId");
				output += "\n Transaction Id: " + results.getString("transactionId");
				output += "\n Date Requested (yyyy/mm/dd)"+ ": " + results.getString("dateRequested");
				output += "\n Date Reserved (yyyy/mm/dd)"+ ": " + results.getString("dateReserved");
				output += "\n Request Status" + ": " + results.getString("requestStatus");
				output += "\n Cost "+ ": " + results.getString("cost");
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
	
	
	
	// for some of these where we try to identify a record i'm using multiple where because its possible to have the same customer two times for example due to it being a many to many relationship table 
	
	public void updateRequestStatus(String requestStatus , int cusId , int equipId, int requestId ,  Connection con) 
	{
		String sql = "update Request set requestStatus = ? where cusId = ? and equipId = ? and requestId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setString(1, requestStatus);
			pStat.setInt(2, cusId);
			pStat.setInt(3, equipId);
			pStat.setInt(4, requestId );
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
	
	
	public void delete(int cusId, int equipId, int requestId, Connection con) 
	{
		String sql = "delete from Request where cusId = ? and equipId = ? and requestId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, requestId);
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
