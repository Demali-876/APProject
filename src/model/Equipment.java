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
@Table(name= "equipment")  

public class Equipment implements Serializable {

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
		
		private Serializable EquipID;
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
	public Equipment selectEquipmentByID(int EquipID) throws Exception, SystemException
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		Transaction Trans = (Transaction) session.beginTransaction();
		Equipment obj = (Equipment) session.get(Equipment.class, EquipID);
		Trans.commit();
		session.close();
		return obj;
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Equipment> selectAllEquipment() throws Exception, SystemException
	{
		List <Equipment> EquipmentList = null;
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		Transaction Trans = (Transaction) session.beginTransaction();
		EquipmentList = session.createSQLQuery("SELECT * FROM equipment").getResultList();
		Trans.commit();
		session.close();
		return EquipmentList;
	}
	
	public void InsertEquipment (Equipment EQ) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			session.save(EQ);
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Equipment Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Equipment was not Added","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
	}
	
	public void UpdateEquip (Equipment EQ) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			Equipment obj = (Equipment) session.get(Equipment.class, this.EquipID);
			obj.setEquipID((int) this.EquipID);
			obj.setEquipName(this.EquipName);
			obj.setEquipstatus(this.EquipStatus);// Rented, Available or damage 
			obj.setEquipCat(this.EquipCategory);
			session.update(EQ);
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Equipment Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Equipment records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
	}
	
	public void DeleteEquipment(int EquipID) throws Exception, SystemException
	{
		Session session = SessionFactoryBuilderEquipment.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			
			session.delete(selectEquipmentByID(EquipID));
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Equipment Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Equipment records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
	}
	
	
	
	
	
	// code below is from sir document, dont know if it works 
	
	
	// test codes below
	
	/*public Boolean TestDeleteEquipment(int EquipID) throws Exception, SystemException
	{
		Session session = getSession();
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			
			session.delete(selectEquipmentByID(EquipID));
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Equipment Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Equipment records was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
		return null;
	}

	private Session getSession() {
		// TODO Auto-generated method stub
		SessionFactory factory = getSessionFactory();
		if(factory!=null)
		{
			return factory.openSession();
		}else {
		return null;
		}
	}

	private static final Configuration config = new Configuration();
	private static SessionFactory sessionFactory = null;
	private SessionFactory getSessionFactory1() throws HibernateException {
		// TODO Auto-generated method stub
		if(sessionFactory1==null)
		{
			config.configure("hibernate.cfg.xml");
			sessionFactory1=config.buildSessionFactory();
		}
		return sessionFactory1;
	}*/
}
}