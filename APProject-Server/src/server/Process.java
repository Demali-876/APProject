package server;
import java.io.Serializable;
import java.sql.SQLException;
/*import java.util.logging.LogManager;
import java.util.logging.Logger;*/
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

import server.Request.SessionFactoryBuilderRequest;



@Entity  
@Table(name= "process")  

public class Process implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int equipId;
	
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition ="EmpID")
	private int EmpId;
	
	@Column(columnDefinition ="CusID")
	private int CusId;
	
	@Column(columnDefinition ="EquipID")
	private int EquipID;
		
	public Process() 
	{
		EmpId = 0;
		CusId = 0;
		EquipID = 0;
	}
	
	public Process(Process Pre) 
	{
		this.EmpId = Pre.EmpId;
		this.CusId = Pre.CusId;
		this.EquipID = Pre.EquipID;
	}
	
	public void setCusId(int cusId) 
	{
		this.CusId = cusId;
	}
	
	public void setEquipId(int equipId) 
	{
		this.EquipID = equipId;
	}
	
	public void setEmpID(int empId) 
	{
		this.EmpId = empId;
	}
	
	public int getCusId() 
	{
		return CusId;
	}
	
	public int getEmpID() 
	{
		return EmpId;
	}
	public int getEquipId() 
	{
		return equipId;
	}
	
	
	@Override
	public String toString() {
		return "EmpID: "+ EmpId+"/nCusID: "+ CusId + "/EquipID: "+ equipId +"\n";
	}
	
	public static class SessionFactoryBuilderProcess{ // open database connection 
		private static SessionFactory sessionFactory = null;
		public static SessionFactory getSessionFactory() {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Process.class).buildSessionFactory();
			}
			
			return sessionFactory;
		}

		private int equipId;
		
		public void closeSessionFactory() {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		
	public void CreateProcess (Process Pre) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, SQLException
	{
		Session session = SessionFactoryBuilderProcess.getSessionFactory().getCurrentSession();;
		Transaction Trans = null;
		
		try {
			Trans = (Transaction) session.beginTransaction();
			
			session.save(Pre);
			Trans.commit();
			JOptionPane.showMessageDialog(null, "Process Added Successfully", "", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(RuntimeException ex)
		{//can add logger in this
			JOptionPane.showMessageDialog(null,"Process was not Added","",JOptionPane.ERROR_MESSAGE);
			Trans.rollback();
		}
		session.flush();
		session.close();
	}
	

	// select a Process with condition
	
	public Process selectProcessByID(int EmpID, int CusID, int equipId) throws Exception, SystemException
	{
		Session session = SessionFactoryBuilderRequest.getSessionFactory().getCurrentSession();;
		Transaction Trans = (Transaction) session.beginTransaction();
		Process obj = (Process) session.get(Process.class, equipId);
		Trans.commit();
		session.close();
		return obj;
					
	}
	
	
	// return all Process
	
			@SuppressWarnings("unchecked")
			public List<Process> selectAllProcess() throws Exception, SystemException
			{
				List <Process> ProcessList = null;
				Session session = SessionFactoryBuilderProcess.getSessionFactory().getCurrentSession();;
				Transaction Trans = (Transaction) session.beginTransaction();
				ProcessList = session.createSQLQuery("SELECT * FROM process").getResultList();
				Trans.commit();
				session.close();
				return ProcessList;
			}
	
			// update Process 
			
			public void UpdateProcess (Process Pre) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
			{
				Session session = SessionFactoryBuilderProcess.getSessionFactory().getCurrentSession();;
				Transaction Trans = null;
				
				try {
					Trans = (Transaction) session.beginTransaction();
					Process obj = (Process) session.get(Process.class, Pre);
						
					obj.setEquipId(this.equipId);
										
					session.update(Pre);
					Trans.commit();
					JOptionPane.showMessageDialog(null, "Process Records Updated Successfully","", JOptionPane.INFORMATION_MESSAGE);
				
				}catch(RuntimeException ex)
				{//can add logger in this
					JOptionPane.showMessageDialog(null,"Processrecords was not updated Successfully","",JOptionPane.ERROR_MESSAGE);
					Trans.rollback();
				}catch(Exception e) 
				{
					e.printStackTrace();
				}
				session.flush();
				session.close();
			}
			
	
			public void DeleteProcess(int EmpId,int CusID, int equipId) throws Exception, SystemException
			{
				Session session = SessionFactoryBuilderProcess.getSessionFactory().getCurrentSession();;
				Transaction Trans = null;
				
				try {
					Trans = (Transaction) session.beginTransaction();
					
					session.delete(selectProcessByID(EmpId, CusID, equipId));
					Trans.commit();
					JOptionPane.showMessageDialog(null, "Process Records Deleted Successfully","", JOptionPane.INFORMATION_MESSAGE);
				
				}catch(RuntimeException ex)
				{//can add logger in this
					JOptionPane.showMessageDialog(null,"Process records was not Deleted Successfully","",JOptionPane.ERROR_MESSAGE);
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