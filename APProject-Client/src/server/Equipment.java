package server;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
@Table(name= "equipment")  

public class Equipment implements Serializable {
	
	private static final Logger logger = LogManager.getLogger(Equipment.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition ="EquipID")
	 int EquipID;
	
	@Column(columnDefinition ="EquipName")
	private String EquipName; 
	
	@Column(columnDefinition ="EquipStatus")
	private String EquipStatus;
	
	@Column(columnDefinition ="EquipCategory")
	private String EquipCategory;
	
	
	public Equipment ()//default constructor 
	{
		this.EquipID = 0;
		this.EquipName="";
		this.EquipStatus="";
		this.EquipCategory="";
	}
	
	public Equipment (Equipment EQ)
	{
		this.EquipID= EQ.EquipID;
		this.EquipName= EQ.EquipName;
		this.EquipStatus= EQ.EquipStatus;
		this.EquipCategory= EQ.EquipCategory;
		
	}
	
	    
	public int getEquipID() {    
	    return EquipID;    
	}    
	public void setEquipID(int Equipid) {    
	    this.EquipID = Equipid;    
	}    
	public String getEquipName() {    
	    return EquipName;    
	}    
	public void setEquipName(String EquipName) {    
	    this.EquipName = EquipName;    
	}    
	public String getEquipstatus() {    
	    return EquipStatus;    
	}    
	public void setEquipstatus(String EquipStatus) {    
	    this.EquipStatus = EquipStatus;    
	}
	public String getEquipCat() {    
	    return EquipCategory;    
	}    
	public void setEquipCat(String EquipCategory) {    
	    this.EquipCategory= EquipCategory;    
	}
	
	@Override
	public String toString() {
		return "EquipID: " + EquipID + "\nEquipNanme: " + EquipName+ "\nEquipStatus: " + EquipStatus+ "\nEquipCategory: "
				+ EquipCategory+ "\n";
	}
	
	
	public static class SessionFactoryBuilderEquipment{
		
		private int EquipID;
		private String EquipName;
		private String EquipStatus;
		private String EquipCategory;
		private static SessionFactory sessionFactory = null;
		public static SessionFactory getSessionFactory() {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Equipment.class).buildSessionFactory();
			}
			
			return sessionFactory;
		}

				
		public void closeSessionFactory() {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	
	 
	 // server side 
	public Equipment selectEquipmentByID(int EquipID)
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		Equipment obj = null ;
		
		try 
		{
			Trans = session.beginTransaction();
			obj = (Equipment) session.get(Equipment.class, EquipID);
			Trans.commit();
		}
		catch(RuntimeException e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to select  equipment by id in the equipment table");
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to select equipment by id in the equipment table");
		
		}
		
		finally 
		{
			session.close();
		}
		
		
		
		return obj;
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Equipment> selectAllEquipment()
	{
		List<Equipment> EquipmentList = new ArrayList<>();
		List<Object> List = new ArrayList<>();
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		
		try 
		{
			org.hibernate.Transaction Trans = session.beginTransaction();
			List = session.createSQLQuery("SELECT * FROM equipment").getResultList();
			Trans.commit();
		}
		catch(RuntimeException e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to select all equipment from the equipment table");
		
		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to select all equipment from the equipment table");
		
		}
		finally 
		{
			session.close();
		}
		//Convert list from Object to Equipment
		Iterator i = List.iterator();
		while(i.hasNext()) {
			Equipment e = new Equipment();
			Object[] obj = (Object[]) i.next();
			e.setEquipID(Integer.parseInt(String.valueOf(obj[0])));
			e.setEquipName(String.valueOf(obj [1]));
			e.setEquipstatus(String.valueOf(obj [2]));
			e.setEquipCat(String.valueOf(obj [3]));
			EquipmentList.add(e);
		}
		return EquipmentList;
	}
	
	public void InsertEquipment (Equipment EQ)
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		
		try 
		{
			Trans = session.beginTransaction();
			session.save(EQ);
			Trans.commit();
			logger.info("A new record was added in the equipment table id: " + EQ.EquipID );
		
		}
		catch(RuntimeException ex)
		{
			JOptionPane.showMessageDialog(null,"Equipment was not Added","",JOptionPane.ERROR_MESSAGE);
			try 
			{
				Trans.rollback();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				logger.error( e.getClass().getName() + " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() +  " exception occured when trying to add a record to the equipment table");
			}
			
			
			logger.error(  ex.getClass().getName() +  " exception occured when trying to add a record to the equipment table");

		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to add a record to the equipment table");
		
		}
		finally 
		{
			session.flush();
			session.close();
		}
	}
	
	public void UpdateEquip (Equipment EQ)
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		
		try 
		{
			Trans = session.beginTransaction();
			Equipment obj = (Equipment) session.get(Equipment.class, this.EquipID);
			session.update(EQ);
			Trans.commit();
		
		}
		catch(RuntimeException ex)
		{
			JOptionPane.showMessageDialog(null,"Equipment records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
			try 
			{
				Trans.rollback();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
				logger.error( e.getClass().getName() + " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() +  " exception occured when trying to update a record in the equipment table");
			}
			
			
			logger.error(  ex.getClass().getName() +  " exception occured when trying to update a record in the employee table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to update a record in the equipment table");
		}
		finally 
		{
			session.close();
		}
		
	}
	
	public void DeleteEquipment(int EquipID)
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		org.hibernate.Transaction Trans = null;
		
		try 
		{
			Trans = session.beginTransaction();
			
			session.delete(selectEquipmentByID(EquipID));
			Trans.commit();
			
			logger.info("deleted a record from the equipment table with equipment " + EquipID);
		
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
				logger.error( e.getClass().getName() + " exception occured when trying to rollback the transaction after a " +  ex.getClass().getName() +  " exception occured when trying to delete a record in the equipment table");
			}
			
			
			logger.error(  ex.getClass().getName() +  " exception occured when trying to delete a record in the equipment table");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			logger.error(  e.getClass().getName() +  " exception occured when trying to delete a record in the equipment table");
		}
		finally 
		{
			session.flush();
			session.close();
		}
		
	}
	}
}