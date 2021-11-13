package client;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;

import server.Equipment;
import server.DBRequest;

public class RentEquipmentFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jsp;
	private JButton button;
	private JLabel label;
	private JPanel panel;
	private JSpinner date;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private int cusId;
	
	public RentEquipmentFrame(List<Equipment> list,ObjectOutputStream oos,ObjectInputStream ois,
			int id) {
		super("Equipment Rental",true,true,true,true);
		
		this.oos = oos;
		this.ois = ois;
		cusId = id;
		
		for(int j = 0; j < list.size(); j++) {//removes unavailable equipment
			if(list.get(j).getEquipstatus().equalsIgnoreCase("Unavailable")){
			    list.remove(j);
			    j--;
			}
		}
		
		this.setLayout(new BorderLayout());
		EquipmentTableModel model = new EquipmentTableModel(list);
		table = new JTable(model);
		table.removeColumn(table.getColumn("EquipStatus"));
		table.setToolTipText("Click on the equipment you would like to rent, then click on the"
				+ "\"Rent Equipment\" button");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp = new JScrollPane(table);
		panel = new JPanel();
		label = new JLabel("Date: ");
		
		date = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
		date.setEditor(new DateEditor(date,"MMM dd, yyyy"));
		
		button = new JButton("Rent Equipment");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0, j = 0;
				DBRequest request = new DBRequest();
				try {
					oos.writeObject("Confirm Rent");
					i = (int)ois.readObject(); //get number of requests
					oos.writeObject(cusId);
					j = (int)ois.readObject(); //get number of transactions customer made
					
					request.setRequestId(i+1);
					request.setEquipId((int) table.getValueAt(table.getSelectedRow(), 0));
					request.setRequestStatus("Pending"); 
					request.setDateRequested(String.valueOf(date.getValue()));
					request.setCusId(cusId);
					request.setTransactionId(j+1);
					oos.writeObject(request);
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(ClassCastException ex) {
					ex.printStackTrace();
				}catch(IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(button,"Please select an item","Error",JOptionPane.ERROR_MESSAGE);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		panel.add(label);
		panel.add(date);
		panel.add(button);
		this.add(jsp,BorderLayout.CENTER);
		this.add(panel,BorderLayout.PAGE_END);
		this.setSize(500,500);
		this.setVisible(true);
	}
}