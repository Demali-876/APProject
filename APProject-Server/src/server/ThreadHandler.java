package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import server.Employee.SessionFactoryBuilderEmployee;
import server.Equipment.SessionFactoryBuilderEquipment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadHandler implements Runnable{
	public final static long serialVersionUID = 1L;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket conSocket;
	private Customer cus;
	
	private static final Logger logger = LogManager.getLogger(ThreadHandler.class);

	
	public ThreadHandler(Socket socket) {
		conSocket = socket;
	}
	
	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(conSocket.getOutputStream());
			ois = new ObjectInputStream(conSocket.getInputStream());
			String action = "";
			DBConnection DBcon = new DBConnection();
			Connection con = DBcon.getConnection();
			
			while(true) {
				Customer cus = new Customer();
				Employee emp = new Employee();
				Equipment equip = new Equipment();
				DBRequest request = new DBRequest();
				SessionFactoryBuilderEquipment equipSession = new SessionFactoryBuilderEquipment();
				SessionFactoryBuilderEmployee empSession = new SessionFactoryBuilderEmployee();
				action = (String)ois.readObject();
				
				if(action.equalsIgnoreCase("Exit")) {
					break;
				}else if(action.equalsIgnoreCase("Customer Login")) {
					int id = (int)ois.readObject();
					char[] password = (char[])ois.readObject();
					String pword = new String().valueOf(password);
					if(cus.selectCustomerByID(id).getCusPassword().equals(pword)) {
						oos.writeObject(true);
					}else {
						oos.writeObject(false);
					}
				}else if(action.equalsIgnoreCase("Employee Login")) {
					int id = (int)ois.readObject();
					char[] password = (char[])ois.readObject();
					String pword = new String().valueOf(password);
					emp = empSession.selectEmployeeByID(id);
					if(emp.getPassword().equals(pword)) {
						oos.writeObject(true);
					}else {
						oos.writeObject(false);
					}
				}else if(action.equalsIgnoreCase("Equipment List")) {
					oos.writeObject(equipSession.selectAllEquipment());
				}else if(action.equalsIgnoreCase("Confirm Rent")) {
					oos.writeObject(request.readAll(con).size());
					int cusId = (int)ois.readObject();
					oos.writeObject(request.selectByCusId(con, cusId).size());
					request = (DBRequest)ois.readObject();
					request.Create(con);
				}else if(action.equalsIgnoreCase("Request List")) {
					oos.writeObject(request.readAll(con));
				}else if(action.equalsIgnoreCase("Schedule")) {
					request = (DBRequest)ois.readObject();
					List<DBRequest> obj = new ArrayList<>();
					obj = request.selectByCusId(con, request.getCusId());
					
					if(obj.isEmpty()) {
						oos.writeObject(false);
						break;
					}
					
					for(int i = 0; i < obj.size(); i++) {
						if(obj.get(i).getRequestId() == request.getRequestId()) {
							if(obj.get(i).getEquipId() == request.getEquipId()) {
								equip = equipSession.selectEquipmentByID(request.getEquipId());
								System.out.print(equip.getEquipstatus());
							    if(equip.getEquipstatus().equalsIgnoreCase("Available")) {
							    	System.out.print(equip.getEquipstatus());
							    	request.updateByEmployee(request.getRequestStatus(),request.getCost(),
							    			request.getCusId(),request.getEquipId(),request.getRequestId(), 
							    			request.getReturnDate(), con);
							    	request.updateDateReserved(request.getDateReserved(),request.getCusId(),
							    			request.getEquipId(),request.getRequestId(), con);
							    	equip.setEquipstatus("Unavailable");
							    	new SessionFactoryBuilderEquipment().UpdateEquip(equip);
							    	oos.writeObject(true);
							    }else {
							    	oos.writeObject(false);
							    }
							}
						}
					}
				}
			}
			oos.close();
			ois.close();
		}catch(IOException e) {
			e.printStackTrace();
			logger.error( " An IO exception occured when receiving requests from the client ");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			logger.error( " A ClassNotFound occured when receiving requests from the client ");
		}catch(ClassCastException e) {
			e.printStackTrace();
			logger.error( " An ClassCast exception occured when receiving requests from the client ");
		}
	}
}
