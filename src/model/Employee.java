package model;

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

import controller.Driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@Entity  
@Table(name= "employee")   

public class Employee implements Serializable 
{
	
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
		this.EmpID = 0;
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
	
	public static class SessionFactoryBuilderEmployee{
		private static SessionFactory sessionFactory = null;
		public static SessionFactory getSessionFactory() {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
			}
			
			return sessionFactory;
		}
		
		public void closeSessionFactory() {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	 
		public Employee selectEmployeeByID(int EmpID) throws Exception, SystemException
		{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		Transaction Trans = (Transaction) session.beginTransaction();
		Employee obj = (Employee) session.get(Employee.class, EmpID);
		Trans.commit();
		session.close();
		return obj;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> selectAllCustomer() throws Exception, SystemException
	{
		List <Employee> EmployeeList = null;
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		Transaction Trans = (Transaction) session.beginTransaction();
		EmployeeList = session.createSQLQuery("SELECT * FROM employee").getResultList();
		Trans.commit();
		session.close();
		return EmployeeList;
	}
	
	public void InsertEmployee (Employee Emp) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			session.save(Emp);
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Employee Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Employee was not Added","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
	}
	
	public void UpdateEmployee (Employee Emp) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			Employee obj = (Employee) session.get(Employee.class, this.EmpID);
			obj.setId(this.EmpID);
			obj.setName(this.EmpName);
			obj.setPassword(this.EmpPassword);
			session.update(Emp);
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Employee Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Employee records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
	}
	
	public Boolean DeleteEmployee(int EmpID) throws Exception, SystemException
	{
		Session session = SessionFactoryBuilderEmployee.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			Employee obj = (Employee) session.get(Employee.class, this.EmpID);
			obj.setId(this.EmpID);
			obj.setName(this.EmpName);
			obj.setPassword(this.EmpPassword);
			session.delete(obj);
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Employee Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Employee records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
		return null;
	}

	
	
}

