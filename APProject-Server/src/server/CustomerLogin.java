package server;
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
@Table(name="customer_login")
public class CustomerLogin implements Serializable {

	/**
	 * 
	 */
	
	private final static Logger logger = Logger.getLogger(CustomerLogin.class.getName()); 
	
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
	//private static final Session SessionFactoryBuilderCustomerLogin = null;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="cus_password")
	private String customerPassword;
	
	public CustomerLogin() {
		this.id = 0;
		this.customerId = 0;
		this.customerPassword = "";
	}

	public CustomerLogin(int id, int customerId, String customerPassword) {
		this.id = id;
		this.customerId = customerId;
		this.customerPassword = customerPassword;
	}
	
	public CustomerLogin(CustomerLogin obj) {
		this.id = obj.id;
		this.customerId = obj.customerId;
		this.customerPassword = obj.customerPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncustomerId: " + customerId + "\ncustomerPassword: " + customerPassword + "\n";
	}
	
	public static class SessionFactoryBuilderCustomerLogin {
		private static SessionFactory sessionFactory = null;

		
		public static SessionFactory getSessionFactory() {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CustomerLogin.class).buildSessionFactory();
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
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		logger.info("customer credentials created");
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CustomerLogin> readAll() {
		ArrayList<CustomerLogin> customerLoginList = new ArrayList<>();
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		customerLoginList = (ArrayList<CustomerLogin>) session.createQuery("FROM CustomerLogin").getResultList();
		transaction.commit();
		session.close();
		return customerLoginList;
	}
	
	public void update() {
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CustomerLogin obj = (CustomerLogin) session.get(CustomerLogin.class, this.id);
		obj.setCustomerId(this.customerId);
		obj.setCustomerPassword(this.customerPassword);
		session.update(obj);
		transaction.commit();
		session.close();
	}
	
	public void delete() {
		Session session = SessionFactoryBuilderCustomerLogin.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CustomerLogin obj = (CustomerLogin) session.get(CustomerLogin.class, this.id);
		session.delete(obj);
		transaction.commit();
		session.close();
	}
}