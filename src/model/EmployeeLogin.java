package Hibernate;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



@Entity
@Table(name="employee_login")
public class EmployeeLogin implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(EmployeeLogin.class.getName()); 
	
	private static void setupLogger() {
        LogManager.getLogManager().reset();
        
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("Log.log", true);
            logger.addHandler(fh);
        } catch (java.io.IOException e) {            
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
         
    }
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="emp_password")
	private String employeePassword;
	
	public EmployeeLogin() {
		this.id = 0;
		this.employeeId = 0;
		this.employeePassword = "";
	}
	
	public EmployeeLogin(int id, int employeeId, String employeePassword) {
		this.id = id;
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
	}
	
	public EmployeeLogin(EmployeeLogin obj) {
		this.id = obj.id;
		this.employeeId = obj.employeeId;
		this.employeePassword = obj.employeePassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nemployeeId: " + employeeId + "\nemployeePassword: " + employeePassword + "\n";
	}
	
	public static class SessionFactoryBuilderEmployeeLogin {
		private static SessionFactory sessionFactory = null;

		
		public static SessionFactory getSessionFactory() {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(EmployeeLogin.class).buildSessionFactory();
			}
			
			return sessionFactory;
		}
		
		public static void closeSessionFactory() {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		
	}
	public void create() {
		setupLogger();
		Session session = SessionFactoryBuilderEmployeeLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("employyee credentials created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<EmployeeLogin> readAll(){
		ArrayList<EmployeeLogin> employeeLoginList = new ArrayList<>();
		Session session = SessionFactoryBuilderEmployeeLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		employeeLoginList = (ArrayList<EmployeeLogin>) session.createQuery("FROM EmployeeLogin").getResultList();
		transaction.commit();
		session.close();
		return employeeLoginList;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderEmployeeLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		EmployeeLogin obj = (EmployeeLogin) session.get(EmployeeLogin.class, this.id);
		obj.setEmployeeId(this.employeeId);
		obj.setEmployeePassword(this.employeePassword);
		session.update(obj);
		transaction.commit();
		session.close();
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderEmployeeLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		EmployeeLogin obj = (EmployeeLogin) session.get(EmployeeLogin.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
	
}
