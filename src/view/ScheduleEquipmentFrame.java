package view;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScheduleEquipmentFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTextField requestIdText;
	private JTextField transactionIdText;
	private JTextField dateRequestedText;
	private JTextField dateReservedText;
	private JTextField costText;
	private JTextField cusIdText;
	private JTextField empIdText;
	private JLabel requestIdLabel;
	private JLabel transactionIdLabel;
	private JLabel dateRequestedLabel;
	private JLabel dateReservedLabel;
	private JLabel costLabel;
	private JLabel cusIdLabel;
	private JLabel empIdLabel;
	private JButton button;
	
	public ScheduleEquipmentFrame() {
		super("Equipment Scheduler",true,true,true,true);
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
		transactionIdText = new JTextField(20);
		transactionIdText.setBounds(200,50,200,30);
		dateRequestedText = new JTextField(20);
		dateRequestedText.setBounds(200,90,200,30);
		dateReservedText = new JTextField(20);
		dateReservedText.setBounds(200,130,200,30);
		costText = new JTextField(20);
		costText.setBounds(200,170,200,30);
		cusIdText = new JTextField(20);
		cusIdText.setBounds(200,210,200,30);
		empIdText = new JTextField(20);
		empIdText.setBounds(200,250,200,30);
		
		requestIdLabel = new JLabel("Request Id");
		requestIdLabel.setBounds(10,10,100,30);
		transactionIdLabel = new JLabel("Transaction Id");
		transactionIdLabel.setBounds(10,50,100,30);
		dateRequestedLabel = new JLabel("Date Requested");
		dateRequestedLabel.setBounds(10,90,100,30);
		dateReservedLabel = new JLabel("Date Reserved");
		dateReservedLabel.setBounds(10,130,100,30);
		costLabel = new JLabel("Cost $");
		costLabel.setBounds(10,170,100,30);
		cusIdLabel = new JLabel("Customer Id");
		cusIdLabel.setBounds(10,210,100,30);
		empIdLabel = new JLabel("Employee Id");
		empIdLabel.setBounds(10,250,100,30);
		
		button = new JButton("Submit");
		button.setBounds(200,350,100,50);
	}
	
	private void addToFrame() {
		this.add(requestIdLabel);
		this.add(requestIdText);
		this.add(transactionIdLabel);
		this.add(transactionIdText);
		this.add(dateRequestedLabel);
		this.add(dateRequestedText);
		this.add(dateReservedLabel);
		this.add(dateReservedText);
		this.add(costLabel);
		this.add(costText);
		this.add(cusIdLabel);
		this.add(cusIdText);
		this.add(empIdLabel);
		this.add(empIdText);
		this.add(button);
	}
	
	private void registerListener() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//implement client function
			}
		});
	}

}
