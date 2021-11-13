package server;

import java.io.Serializable;

import java.sql.Date;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



@Entity  
@Table(name= "request")   

public class Request implements Serializable {
	
	
		@SuppressWarnings("unused")
		private static final Date date = null;
	private static final long serialVersionUID = 1L;
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition ="RequestID")
	private int requestId;
	
	@Column(columnDefinition ="TransactionID")
	private int transactionId;
	
	@Column(columnDefinition ="RequestDate")
	private String dateRequested;
	
	@Column(columnDefinition ="ReserveDate")
	private String dateReserved;
	
	@Column(columnDefinition ="ReturnDate")
	private String ReturnDate;
	
	@Column(columnDefinition ="RequestStatus")
	private String requestStatus;
	
	@Column(columnDefinition ="RequestCost")
	private float cost;
	
	@Column(columnDefinition ="CusID")
	private int cusId;
	
	@Column(columnDefinition ="EquipID")
	private int equipId;
	public Serializable Res;
		
		//private static final Logger logger = LogManager.resetLogger(Request.class);
		
		public Request() 
		{
			requestId = 0;
			transactionId = 0;
			dateRequested = "";
			dateReserved = "";
			ReturnDate = "";
			requestStatus = "";
			cost = 0f;
			cusId = 0;
			equipId = 0;
		}
		
		public Request(Request Res) 
		{
			this.requestId = Res.requestId;
			this.transactionId = Res.transactionId;
			this.dateRequested = Res.dateRequested;
			this.dateReserved = Res.dateReserved;
			this.ReturnDate= Res.ReturnDate ;
			this.requestStatus = Res.requestStatus;
			this.cost = Res.cost;
			this.cusId = Res.cusId;
			this.equipId = Res.equipId;
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
		
		public void setDateReturn(String ReturnDate) 
		{
			this.ReturnDate = ReturnDate;
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
		
		public String getDateReturn() 
		{
			return ReturnDate;
		}
		
		public String getRequestStatus() 
		{
			return requestStatus;
		}
			 
		
		@Override
		public String toString() {
			return "RequestID: "+ requestId+"/nTransactionID: "+ transactionId + "/CusID: "+ cusId + "\nEquipID: " + equipId + 
					"\nrequestCost: " + cost + "\nRequestStatus: " + requestStatus+ "\nReturnDate"+ReturnDate +"\nRequestDate: "
					+ dateRequested+ "\nReserveDate"+dateReserved+"\n";
		}
		
		public static class SessionFactoryBuilderRequest { // open database connection 
			private static SessionFactory sessionFactory = null;
			public static SessionFactory getSessionFactory() {
				
				if (sessionFactory == null) {
					sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Request.class).buildSessionFactory();
				}
				
				return sessionFactory;
			}


			private String requestStatus;
			
			public void closeSessionFactory() {
				if (sessionFactory != null) {
					sessionFactory.close();
				}
			}
		
		// create 
		public void CreateRequest (Request Res) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, SQLException
		{
			Session session = SessionFactoryBuilderRequest.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			
			try {
				Trans = session.beginTransaction();
				
				session.save(Res);
				Trans.commit();
				JOptionPane.showMessageDialog(null, "Request Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
			
			}catch(RuntimeException ex)
			{//can add logger in this
				JOptionPane.showMessageDialog(null,"Request was not Added","",JOptionPane.ERROR_MESSAGE);
				Trans.rollback();
			}
			session.flush();
			session.close();
		}
		
		
		
		
	// select a request with condition
		
		public Request selectRequestByID(int requestID, int CusID, int EquipId) throws Exception, SystemException
		{
			Session session = SessionFactoryBuilderRequest.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = session.beginTransaction();
			Request obj = (Request) session.get(Request.class, requestID);
			Trans.commit();
			session.close();
			return obj;
						
		}
		
		
		// return all request 
		
		@SuppressWarnings("unchecked")
		public List<Request> selectAllRequest() throws Exception, SystemException
		{
			List <Request> RequestList = null;
			Session session = SessionFactoryBuilderRequest.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = session.beginTransaction();
			RequestList = session.createSQLQuery("SELECT * FROM request").getResultList();
			Trans.commit();
			session.close();
			return RequestList;
		}
		
		
		// update request status 
		
		public void UpdateRequestStatus (Request Res) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
		{
			Session session = SessionFactoryBuilderRequest.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			
			try {
				Trans = session.beginTransaction();
				Request obj = (Request) session.get(Request.class, Res);
					
				obj.setRequestStatus(this.requestStatus);// Rented, Available or damage 
				
				session.update(Res);
				Trans.commit();
				JOptionPane.showMessageDialog(null, "Request Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
			
			}catch(RuntimeException ex)
			{//can add logger in this
				JOptionPane.showMessageDialog(null,"Request records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
				Trans.rollback();
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			session.flush();
			session.close();
		}
		
		
		
		// delete request 
		
		
		public void DeleteRequest(int requestID,int CusID, int EquipId) throws Exception, SystemException
		{
			Session session = SessionFactoryBuilderRequest.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			
			try {
				Trans = session.beginTransaction();
				
				session.delete(selectRequestByID(requestID, CusID, EquipId));
				Trans.commit();
				JOptionPane.showMessageDialog(null, "Request Records delete Successfully","", JOptionPane.INFORMATION_MESSAGE);
			
			}catch(RuntimeException ex)
			{//can add logger in this
				JOptionPane.showMessageDialog(null,"Request records was not deleted Successfully","",JOptionPane.ERROR_MESSAGE);
				Trans.rollback();
			}catch(SQLException e) 
			{
				e.printStackTrace();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			session.flush();
			session.close();
		}
		
	}
}