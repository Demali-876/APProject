package server;
import java.io.Serializable;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class DBRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int requestId;
	private int transactionId;
	private String dateRequested;
	private String dateReserved;
	private String requestStatus;
	private String ReturnDate;
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
		ReturnDate = "";
		cost = 0f;
		cusId = -1;
		equipId = -1;
	}
	
	public DBRequest(int requestId, int transactionId, String dateRequested, String dateReserved,String ReturnDate, String requestStatus, float cost, int cusId, int equipId) 
	{
		this.requestId = requestId;
		this.transactionId = transactionId;
		this.dateRequested = dateRequested;
		this.dateReserved = dateReserved;
		this.ReturnDate = ReturnDate;
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
	
	public void setReturnDate(String ReturnDate) 
	{
		this.ReturnDate = ReturnDate;
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
		this.equipId = equipId;
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
	
	public String getReturnDate() 
	{
		return ReturnDate;
	}
	
	public String getRequestStatus() 
	{
		return requestStatus;
	}
	
	public void Create(Connection con) 
	{
		String sql = "insert into Request(requestId, transactionId, RequestDate, ReserveDate, RequestStatus, RequestCost, cusId, equipId, ReturnDate) values( ? , ? , ?, ?, ?, ?, ? , ? , ?) ; ";
		
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
			pStat.setString(9, ReturnDate);
			pStat.execute();
			
			logger.info("added a new record to the request table for customer " + cusId );
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when creating a new record in the request table");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("A null pointer exception occured when creating a new record in the request table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(e.getClass().getName() + "exception occured when creating a new record in the request table");
		}
		
		
	}
	
	//This should only return one request
	public DBRequest select(int cusId , int equipId, int requestId ,  Connection con) 
	{
		String sql = "select * from Request where cusId = ? and equipId = ? and requestId = ? ;";
		DBRequest request = null;
		
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			pStat.setInt(2, equipId);
			pStat.setInt(3, requestId);
			ResultSet results = pStat.executeQuery();
			
			if(results.next()) 
			{
				request = new DBRequest();
				request.setCusId( results.getInt("cusId") ) ;
				request.setEquipId( results.getInt("equipId") ) ;
				request.setRequestId( results.getInt("requestId") ) ;
				request.setTransactionId( results.getInt("transactionId") ) ;
				request.setDateRequested( results.getString("RequestDate") ) ;
				request.setDateReserved( results.getString("ReserveDate") ) ;
				request.setRequestStatus( results.getString("requestStatus") ) ;
				request.setCost( results.getFloat("RequestCost") ) ;
				request.setReturnDate(results.getString("ReturnDate"));
			}
			
			
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying to select a record with conditions of cusId, equipId and requestId in the request table");

		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("A null pointer exception occured when trying to select a record with conditions of cusId, equipId and requestId in the request table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + " exception occured when trying to select a record with conditions of cusId, equipId and requestId in the request table");
		}
		
		return request;
	}
	
	public ArrayList<DBRequest> readAll(Connection con) 
	{
		String sql = "select * from Request ;";
		ArrayList<DBRequest> requests  = new ArrayList<>();
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) 
			{
				
				DBRequest request = new DBRequest();
				
				request.setCusId( results.getInt("cusId") ) ;
				request.setEquipId( results.getInt("equipId") ) ;
				request.setRequestId( results.getInt("requestId") ) ;
				request.setTransactionId( results.getInt("transactionId") ) ;
				request.setDateRequested( results.getString("RequestDate") ) ;
				request.setDateReserved( results.getString("ReserveDate") ) ;
				request.setRequestStatus( results.getString("requestStatus") ) ;
				request.setCost( results.getFloat("RequestCost") ) ;
				request.setReturnDate(results.getString("ReturnDate"));
				
				requests.add(request);
				
			}
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying to select all records from the request table");

		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occured when trying to select all records from the request table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(e.getClass().getName() + " exception occured when trying to select all records from the request table");
		}
		
		return requests;
		
	}
	
	public ArrayList<DBRequest> selectByCusId(Connection con , int cusId) 
	{
		String sql = "select * from Request where cusId = ? ;";
		ArrayList<DBRequest> requests  = new ArrayList<>();
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setInt(1, cusId);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) 
			{
				
				DBRequest request = new DBRequest();
				
				request.setCusId( results.getInt("cusId") ) ;
				request.setEquipId( results.getInt("equipId") ) ;
				request.setRequestId( results.getInt("requestId") ) ;
				request.setTransactionId( results.getInt("transactionId") ) ;
				request.setDateRequested( results.getString("RequestDate") ) ;
				request.setDateReserved( results.getString("ReserveDate") ) ;
				request.setRequestStatus( results.getString("requestStatus") ) ;
				request.setCost( results.getFloat("RequestCost") ) ;
				request.setReturnDate(results.getString("ReturnDate"));
				
				requests.add(request);
				
			}
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying to select records with the same cusId from the request table");

		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occured when trying to select records with the same cusId from the request table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(e.getClass().getName() + " exception occured when trying to select records with the same cusId from the request table");
		}
		
		return requests;
		
	}
	
	
	
	// for some of these where we try to identify a record i'm using multiple where because its possible to have the same customer two times for example due to it being a many to many relationship table 
	
	//for when employee gets to this enquiry
	public void updateByEmployee(String requestStatus , float cost, int cusId , int equipId, int requestId ,String ReturnDate,  Connection con) 
	{
		String sql = "update Request set requestStatus = ? , RequestCost = ?, ReturnDate = ? where cusId = ? and equipId = ? and requestId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setString(1, requestStatus);
			pStat.setFloat(2, cost);
			pStat.setString(3, ReturnDate);
			pStat.setInt(4, cusId);
			pStat.setInt(5, equipId);
			pStat.setInt(6, requestId );
			pStat.execute();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying to update records from the request table with conditions for cusId , equipId and requestId");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occured when trying to update records from the request table with conditions for cusId , equipId and requestId");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() +  " exception occured when trying to update records from the request table with conditions for cusId , equipId and requestId");
		}
	}
	
	// in case date reserved needs to be changed
	public void updateDateReserved(String dateReserved , int cusId , int equipId, int requestId ,  Connection con) 
	{
		String sql = "update Request set ReserveDate = ? where cusId = ? and equipId = ? and requestId = ? ;";
		try 
		{
			PreparedStatement pStat = con.prepareStatement(sql);
			pStat.setString(1, dateReserved);
			pStat.setInt(2, cusId);
			pStat.setInt(3, equipId);
			pStat.setInt(4, requestId );
			pStat.execute();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying to update dateReserved from the request table with conditions for cusId , equipId and requestId");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("An null pointer exception occured when trying to update dateReserved from the request table with conditions for cusId , equipId and requestId");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() +  " exception occured when trying to update dateReserved from the request table with conditions for cusId , equipId and requestId");
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
			
			logger.info("deleted a record from the request table with customer " + cusId);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			logger.error("An SQL exception occured when trying delete a record from the request table with conditions for cusId , equipId and requestId");
		}
		catch(NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("A null pointer exception occured when trying delete a record from the request table with conditions for cusId , equipId and requestId");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error( e.getClass().getName() + "exception occured when trying delete a record from the request table with conditions for cusId , equipId and requestId");
		}
	}
}