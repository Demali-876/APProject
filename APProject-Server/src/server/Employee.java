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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@Entity  
@Table(name= "employee")   

public class Employee implements Serializable {
	
	private static final Logger logger = LogManager.getLogger(Employee.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition ="EmpID")
	private int EmpID;   
	
	@Column(columnDefinition ="EmpName")
	private String EmpName; 
	
	@Column(columnDefinition ="EmpPassword")
	private String EmpPassword;
	
		
	public Employee ()//default constructor 
	{
		this.EmpID = 00000;
		this.EmpName="";
		this.EmpPassword="";
	}
	
	public Employee (Employee Emp)
	{
		this.EmpID= Emp.EmpID;
		this.EmpName= Emp.EmpName;
		this.EmpPassword= Emp.EmpPassword;
				
	}
		     
	public int getId() {    
	    return EmpID;  
	    
	}    
	public void setId(int id) {    
	    this.EmpID = id;    
	}    
	public String getName() {    
	    return EmpName;    
	}    
	public void setName(String Name) {    
	    this.EmpName = Name;    
	}    
	public String getPassword() {    
	    return EmpPassword;    
	}    
	public void setPassword(String Password) {    
	    this.EmpPassword = Password;    
	}
	
	
	@Override
	public String toString() {
		return "EmpID: " + EmpID + "\nEmpName: " + EmpName+ "\nEmpPassword: " + EmpPassword+ "\n";
	}
	 
	
	
	// for server 
	
	public static class SessionFactoryBuilderEmployee{
		private String EmpName = null;
		private static SessionFactory sessionFactory = null;
		public static SessionFactory getSessionFactory() {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
			}
			
			return sessionFactory;
		}

		private int EmpID;
		private String EmpPassword;
		
		public void closeSessionFactory() {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	
	 
		public Employee selectEmployeeByID(int EmpID)
		{
			Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
			org.hibernate.Transaction Trans = null;
			Employee obj = null;
		
			try 
			{
				Trans = session.beginTransaction();
				obj = (Employee) session.get(Employee.class, EmpID);
				Trans.commit();
			}
			catch(RuntimeException e) 
			{
				e.printStackTrace();
				logger.error(  e.getClass().getName() +  " exception occured when trying to select an employee by id in the employee table");
			
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				logger.error(  e.getClass().getName() +  " exception occured when trying to select an employee by id in the employee table");
			
			}
			finally 
			{
				session.close();
			
			}
			
			
			return obj;
		}
	
	
	@SuppressWarnings("unchecked")
	public List<Employee> selectAllCustomer() throws Exception, SystemException
	{
		List <Employee> EmployeeList = null;
		org.hibernate.Transaction Trans = null ;
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		
		try 
		{
			Trans = session.beginTransaction();
			EmployeeList = session.createSQLQuery("SELECT * FROM employee").getResultList();
			Trans.commit();
		}
		catch(RuntimeException e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to select all employees in the employee table");
		
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to select all employees in the employee table");
			
		}
		finally 
		{
			session.close();
		
		}
		
		return EmployeeList;
	}
	
	public void CreateEmployee (Employee Emp)
	{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		
		try 
		{
			Trans = session.beginTransaction();
			session.save(Emp);
			Trans.commit();
			logger.info("A new record was added in the employee table id: " + Emp.EmpID );
		
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
				logger.error( e.getClass().getName() + " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() +  " exception occured when trying to add a record to the employee table");
			}
			
			
			logger.error( ex.getClass().getName() +  " exception occured when trying to add a record to the employee table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to add a record to the employee table");
		}
		finally 
		{
			session.flush();
			session.close();
		}
		
	}
	
	public void UpdateEmployee (Employee Emp)
	{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		
		try 
		{
			Trans =  session.beginTransaction();
			Employee obj = (Employee) session.get(Employee.class, this.EmpID);
			obj.setId(this.EmpID);
			obj.setName(this.EmpName);
			obj.setPassword(this.EmpPassword);
			session.update(Emp);
			Trans.commit();
		
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
				logger.error( e.getClass().getName() + " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() +  " exception occured when trying to update a record in the employee table");
			}
			
			
			logger.error(  ex.getClass().getName() +  " exception occured when trying to update a record in the employee table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to update a record in the employee table");
		}
		finally 
		{
			session.flush();
			session.close();
		}
		
	}
	
	public Boolean DeleteEmployee(int EmpID)
	{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		
		try 
		{
			Trans = session.beginTransaction();
			Employee obj = (Employee) session.get(Employee.class, this.EmpID);
			obj.setId(this.EmpID);
			obj.setName(this.EmpName);
			obj.setPassword(this.EmpPassword);
			session.delete(obj);
			Trans.commit();
			
			logger.info("deleted a record from the employee table with employee " + EmpID);
		
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
				logger.error( e.getClass().getName() + " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() +  " exception occured when trying to delete a record in the employee table");
			}
			
			
			logger.error(  ex.getClass().getName() +  " exception occured when trying to delete a record in the employee table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to delete a record in the employee table");
		}
		finally 
		{
			session.flush();
			session.close();
		}
		return null;
	}

	}
}