package client;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import server.DBRequest;

import javax.swing.JSpinner.DateEditor;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ScheduleEquipmentFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTextField requestIdText;
	private JTextField equipIdText;
	private JTextField costText;
	private JTextField cusIdText;
	private JTextField requestStatusText;
	private JLabel requestIdLabel;
	private JLabel equipIdLabel;
	private JLabel dateReturnLabel;
	private JLabel dateReservedLabel;
	private JLabel costLabel;
	private JLabel cusIdLabel;
	private JLabel requestStatusLabel;
	private JSpinner dateReturn;
	private JSpinner dateReserved;
	private JButton button;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private int empId;
	
	public ScheduleEquipmentFrame(ObjectOutputStream oos,ObjectInputStream ois,int id) {
		super("Equipment Scheduler",true,true,true,true);
		
		this.oos = oos;
		this.ois = ois;
		empId = id;
		
		initializeComponents();
		registerListener();
		addToFrame();
		this.setLayout(null);
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	private void initializeComponents() {
		requestIdText = new JTextField(20);
		requestIdText.setBounds(200,10,200,30);
		equipIdText = new JTextField(20);
		equipIdText.setBounds(200,50,200,30);
		costText = new JTextField(20);
		costText.setBounds(200,170,200,30);
		cusIdText = new JTextField(20);
		cusIdText.setBounds(200,210,200,30);
		requestStatusText = new JTextField(20);
		requestStatusText.setBounds(200,250,200,30);
		
		requestIdLabel = new JLabel("Request Id");
		requestIdLabel.setBounds(10,10,100,30);
		equipIdLabel = new JLabel("Equipment Id");
		equipIdLabel.setBounds(10,50,100,30);
		dateReturnLabel = new JLabel("Return Date");
		dateReturnLabel.setBounds(10,90,100,30);
		dateReservedLabel = new JLabel("Date Reserved");
		dateReservedLabel.setBounds(10,130,100,30);
		costLabel = new JLabel("Cost $");
		costLabel.setBounds(10,170,100,30);
		cusIdLabel = new JLabel("Customer Id");
		cusIdLabel.setBounds(10,210,100,30);
		requestStatusLabel = new JLabel("Request Status");
		requestStatusLabel.setBounds(10,250,100,30);
		
		dateReturn = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
		dateReturn.setEditor(new DateEditor(dateReturn,"MMM dd, yyyy"));
		dateReturn.setBounds(200,90,200,30);
		
		dateReserved = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
		dateReserved.setEditor(new DateEditor(dateReserved,"MMM dd, yyyy"));
		dateReserved.setBounds(200,130,200,30);
		
		button = new JButton("Submit");
		button.setBounds(200,350,100,50);
	}
	
	private void addToFrame() {
		this.add(requestIdLabel);
		this.add(requestIdText);
		this.add(equipIdLabel);
		this.add(equipIdText);
		this.add(dateReturnLabel);
		this.add(dateReturn);
		this.add(dateReservedLabel);
		this.add(dateReserved);
		this.add(costLabel);
		this.add(costText);
		this.add(cusIdLabel);
		this.add(cusIdText);
		this.add(requestStatusLabel);
		this.add(requestStatusText);
		this.add(button);
	}
	
	private void registerListener() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBRequest request = new DBRequest();
				try {
					if(requestIdText.getText().isBlank() || equipIdText.getText().isBlank()
							|| costText.getText().isBlank() || cusIdText.getText().isBlank()) {
						JOptionPane.showMessageDialog(getParent(),"No field should be left empty",
								"Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
					    request.setRequestId(Integer.parseInt(requestIdText.getText()));
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(getParent(),"Request Id must contain only"
								+ "numbers","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
					    request.setCusId(Integer.parseInt(cusIdText.getText()));
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(getParent(),"Customer Id must contain only"
								+ "numbers","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						request.setCost(Float.parseFloat(costText.getText()));
					}catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(getParent(),"Equipment Id must contain only"
								+ "numbers","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						request.setEquipId(Integer.parseInt(equipIdText.getText()));
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(getParent(),"Enter a valid cost","Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					request.setReturnDate(String.valueOf(dateReturn.getValue()));
					request.setDateReserved(String.valueOf(dateReserved.getValue()));
					request.setRequestStatus(requestStatusText.getText());
					oos.writeObject("Schedule");
					oos.writeObject(request);
					if((boolean)ois.readObject() == true) {
						JOptionPane.showMessageDialog(getParent(),"Equipment Scheduled",
								"Successful Schedule",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(getParent(),"Equipment Schedule Failed",
								"Failed Schedule",JOptionPane.ERROR_MESSAGE);
					}
				}catch(IOException ex) {
					ex.printStackTrace();
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(ClassCastException ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}