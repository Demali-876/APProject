package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;


import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class DBHibernate {
	
	
		
public static void main(String[] args) { // use to test connectivity   
	
		        
	    StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
	    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
	  
	   StandardServiceRegistry factory = ( (StandardServiceRegistryBuilder) ((Session) meta.getSessionFactoryBuilder()).getSessionFactory()).build();  
	    Session session = ((SessionFactory) factory).openSession();  
	    Transaction trans = session.beginTransaction();   
	      // use to test connectivity      
	    Customer e1=new Customer();    
	    e1.setCusId(2343);    
	    e1.setCusName("joel powell");    
	    e1.setCusPassword("hyf65");    
	    e1.setCusBal(5652.8); 
	    session.save(e1);  
	    trans.commit();  
	    System.out.println("successfully saved");    
	    factory.close();  
	    session.close();    
	        
	}   

}

