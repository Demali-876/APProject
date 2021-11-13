package server;
import java.io.Serializable;
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

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
	@Entity  
	@Table(name= "customer")   

	public class Customer implements Serializable{
		
		private static final Logger logger = LogManager.getLogger(Customer.class);
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//private static  Session SessionFactoryBuilder= null;
		@Id   
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(columnDefinition ="CusID")
		private int CusID;  
		
		@Column(columnDefinition ="CusName")
		private String CusName;
		
		@Column(columnDefinition ="CusPassword")
		private String CusPassword;
		
		@Column(columnDefinition ="CusAccBal")
		private double CusAccBal;
		
		
		public Customer ()//default constructor 
		{
			this.CusID =0 ;
			this.CusName="";
			this.CusPassword="";
			this.CusAccBal=0f;
		}
		
		public Customer (Customer Cus)
		{
			this.CusID= Cus.CusID;
			this.CusName= Cus.CusName;
			this.CusPassword= Cus.CusPassword;
			this.CusAccBal= Cus.CusAccBal;
			
		}
		    
		public int getCusId() {    
		    return CusID;    
		}    
		public void setCusId(int Cusid) {    
		    this.CusID = Cusid;    
		}    
		public String getCusName() {    
		    return CusName;    
		}    
		public void setCusName(String CusName) {    
		    this.CusName = CusName;    
		}    
		public String getCusPassword() {    
		    return CusPassword;    
		}    
		public void setCusPassword(String CusPassword) {    
		    this.CusPassword = CusPassword;    
		}
		public double getCusBal() {    
		    return CusAccBal;    
		}    
		public void setCusBal(double CusAccBal) {    
		    this.CusAccBal = CusAccBal;    
		}
		
		
		
		public String toString() {
			return "CusID: " + CusID + "\nCusNanme: " + CusName+ "\nCusPassword: " + CusPassword+ "\nCusAccBal: "
					+ CusAccBal+ "\n";
		}
		 
		 // for server 
		public Customer selectCustomerByID(int CusID)
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			Customer obj = new Customer();
			
			try 
			{
			Trans = session.beginTransaction();
			obj = (Customer) session.get(Customer.class, CusID);
			Trans.commit();
			}
			catch(RuntimeException e )
			{
				logger.error(  e.getClass().getName() +  " exception occured when trying to select a customer by id ");
				e.printStackTrace();
			}
			catch(Exception e) 
			{
				logger.error(  e.getClass().getName() +  " exception occured when trying to select a customer by id ");
				e.printStackTrace();
			}
			
			finally 
			{
				session.close();
			}
			
			return obj;
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Customer> selectAllCustomer()
		{
			List <Customer> CustomerList = null;
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			try 
			{
				Trans = session.beginTransaction();
				CustomerList = session.createSQLQuery("SELECT * FROM customer").getResultList();
				Trans.commit();
			}
			catch(RuntimeException e )
			{
				e.printStackTrace();
				logger.error(  e.getClass().getName() +  " exception occured when trying to select all customers  ");
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				logger.error(  e.getClass().getName() +  " exception occured when trying to select all customers");
			}
			finally 
			{
				
				session.close();
			}
			
			return CustomerList;
		}
		
		public void CreateCustomer (Customer Cus)
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			
			try 
			{
				Trans = session.beginTransaction();
				session.save(Cus);
				Trans.commit();
				logger.info("A new record was added in the customer table id: " + Cus.CusID );
			
			}
			catch(RuntimeException ex)
			{
				JOptionPane.showMessageDialog(null,"Customer was not Added","",JOptionPane.ERROR_MESSAGE);
				
				try 
				{
					Trans.rollback();
				} 
				catch(Exception e ) 
				{
					e.printStackTrace();
					logger.error(  e.getClass().getName() +  " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() + " exception when creating a new record in the customer table " );
				}
				
				ex.printStackTrace();
				logger.error( ex.getClass().getName() +  " exception occured when trying to create a new record in the customer table ");
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				logger.error(  e.getClass().getName() +  " exception occured when trying to create a new record in the customer table ");
			}
			finally 
			{
				session.flush();
				session.close();
			}
			
		}
		

		public void UpdateCustomer (Customer Cus)
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			
			try 
			{
				Trans = session.beginTransaction();
				Customer obj = (Customer) session.get(Customer.class, this.CusID);
				obj.setCusId(this.CusID);
				obj.setCusName(this.CusName);
				obj.setCusPassword(this.CusPassword);
				obj.setCusBal(this.CusAccBal);
				session.update(Cus);
				Trans.commit();
			
			}
			catch(RuntimeException ex)
			{
				ex.printStackTrace();
				try 
				{
					Trans.rollback();
				} 
				catch(Exception e ) 
				{
					e.printStackTrace();
					logger.error(  e.getClass().getName() +  " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() + " exception when updating a record in the customer table  " );
				}
				
				logger.error(  ex.getClass().getName() +  " exception occured when trying to update a record in the customer table  " );
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error(  e.getClass().getName() +  " exception occured when trying to update a record in the customer table  " );
				
			}
			
			finally 
			{
				session.flush();
				session.close();
			}
			
		}
		
		public void DeleteCustomer(int CusID)
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans= null;
			try
			{
				Trans = session.beginTransaction();
				session.delete(selectCustomerByID(CusID));
				Trans.commit();
				
				logger.info("deleted a record from the customer table with customer " + CusID );
			}
			catch(RuntimeException ex)
			{
				try 
				{
					Trans.rollback();
				} 
				catch(Exception e) 
				{
					e.printStackTrace();
					logger.error( e.getClass().getName() + " exception occured after when trying to rollback the transaction after " +   ex.getClass().getName() +  " exception occured when trying to delete a record in the customer table  " );
				}
				
				logger.error(  ex.getClass().getName() +  " exception occured when trying to delete a record in the customer table  " );
			}
			catch(Exception e) 
			{
				logger.error(  e.getClass().getName() +  " exception occured when trying to delete a record in the customer table  " );
			}
			finally 
			{
				session.flush();
				session.close();
			}
			
		}
		
		
			public static class SessionFactoryBuilderCustomer {
			private static SessionFactory sessionFactory = null;
			public static SessionFactory getSessionFactory() {
				
				if (sessionFactory == null) {
					sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();
				}
				
				return sessionFactory;
			}
			
			public void closeSessionFactory() {
				if (sessionFactory != null) {
					sessionFactory.close();
				}
			}
	}
}