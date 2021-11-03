package Hibernate;

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
 
	@Entity  
	@Table(name= "customer")   

	public class Customer implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//private static  Session SessionFactoryBuilder= null;
		@Id   
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(columnDefinition ="CusID")
		int CusID;  
		
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
			this.CusAccBal=0.0;
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
		public Customer selectCustomerByID(int CusID) throws Exception, SystemException
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			Transaction Trans = (Transaction) session.beginTransaction();
			Customer obj = (Customer) session.get(Customer.class, CusID);
			Trans.commit();
			session.close();
			return obj;
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Customer> selectAllCustomer() throws Exception, SystemException
		{
			List <Customer> CustomerList = null;
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			Transaction Trans = (Transaction) session.beginTransaction();
			CustomerList = session.createSQLQuery("SELECT * FROM customer").getResultList();
			Trans.commit();
			session.close();
			return CustomerList;
		}
		
		public void CreateCustomer (Customer Cus) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			Transaction Trans = null;
			
			try {
				Trans = (Transaction) session.beginTransaction();
				session.save(Cus);
				Trans.commit();
				JOptionPane.showMessageDialog(null, "Customer Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
			
			}catch(RuntimeException ex)
			{//can add logger in this
				JOptionPane.showMessageDialog(null,"Customer was not Added","",JOptionPane.ERROR_MESSAGE);
				Trans.rollback();
			}
			session.flush();
			session.close();
		}
		

		public void UpdateCustomer (Customer Cus) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			Transaction Trans = null;
			
			try {
				Trans = (Transaction) session.beginTransaction();
				Customer obj = (Customer) session.get(Customer.class, this.CusID);
				obj.setCusId(this.CusID);
				obj.setCusName(this.CusName);
				obj.setCusPassword(this.CusPassword);
				obj.setCusBal(this.CusAccBal);
				session.update(Cus);
				Trans.commit();
				JOptionPane.showMessageDialog(null, "Customer Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
			
			}catch(RuntimeException ex)
			{//can add logger in this
				JOptionPane.showMessageDialog(null,"Customer records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
				Trans.rollback();
			}
			session.flush();
			session.close();
		}
		
		public void DeleteCustomer(int CusID) throws Exception, SystemException
		{
			Session session = SessionFactoryBuilderCustomer.getSessionFactory().getCurrentSession();;
			Transaction Trans= null;
			try{
				Trans=(Transaction)session.beginTransaction();
				session.delete(selectCustomerByID(CusID));
				Trans.commit();
				JOptionPane.showMessageDialog(null, "Customer Records Deleted Successfully","", JOptionPane.INFORMATION_MESSAGE);
			}catch(RuntimeException ex)
			{
				JOptionPane.showMessageDialog(null,"Customer records was deleted Successfully","",JOptionPane.ERROR_MESSAGE);
				Trans.rollback();
			}
			session.flush();
			session.close();
			
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
