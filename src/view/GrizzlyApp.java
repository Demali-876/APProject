package view;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GrizzlyApp{
	private JFrame frame;
	private JButton button;
	private JLabel userIdLabel;
	private JLabel passwordLabel;
	private JTextField userIdTF;
	private JPasswordField password;
	private JPanel userIdPanel;
	private JPanel passwordPanel;
	private JPanel buttonPanel;
	private JDesktopPane desktop;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenu edit;
	private JMenuItem rentEquipment;
	private JMenuItem viewTransaction;
	private JMenuItem viewRentRequest;
	private JMenuItem viewInventory;
	private JMenuItem scheduleEquipment;
	private JMenuItem transDoc;
	private JMenuItem save;
	
	public GrizzlyApp() {
		initializeLoginComponents();
		registerLoginButtonListener();
		addLoginPanel();
		configFrame();
	}
	
	public void initializeLoginComponents() {//initializing the attributes that will be use for login screen
		frame = new JFrame("Grizzly App");
		frame.setTitle("Grizzly App");
		frame.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		button = new JButton("Login");
		userIdLabel = new JLabel("User ID: ");
		passwordLabel = new JLabel("Password: ");
		userIdTF = new JTextField(20);
		password = new JPasswordField(20);
		userIdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		userIdPanel.setSize(new Dimension(450,30));
		passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		passwordPanel.setSize(new Dimension(450,30));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setSize(new Dimension(450,30));
	}
	
	public void addLoginPanel() {//add the components into their respective panels
		userIdPanel.add(userIdLabel);
		userIdPanel.add(userIdTF);
		
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		
		buttonPanel.add(button);
	}
	
	public void configFrame() {//add components to the frame to finish setting up frame
		frame.add(userIdPanel);
		frame.add(passwordPanel);
		frame.add(buttonPanel);
		frame.setSize(350,150);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void registerLoginButtonListener() {//setup what the login button does
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//implement verification check and database connectivity later
				JOptionPane.showMessageDialog(null, "Login Succesful");
				initializeDashboardComponents();
				registerDashboardListeners();
				if(userIdTF.getText().contains("CUS")) {
					initializeCustomerComponents();
					registerCustomerListeners();
					addToMenuBar();
					DashboardFrame();
				}else if(userIdTF.getText().contains("EMP")) {
					initializeEmployeeComponents();
					registerEmployeeListeners();
					addToMenuBar();
					DashboardFrame();
				}
			}
		});
	}
	
	private void DashboardFrame() {
		//this method changes the frame from the login screen to the dashboard screen
		frame.getContentPane().removeAll();
		frame.repaint();
		//Clears out all the components previously added to frame
		frame.setLayout(new BorderLayout());//sets frame's layout manager back to default
		frame.setTitle("Grizzly App - Dashboard");
		frame.add(desktop);
		frame.setJMenuBar(menuBar);
		frame.setSize(1260,720);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeDashboardComponents() {
		//this method sets up the universal dashboard components
		desktop = new JDesktopPane();
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		save = new JMenuItem("Save");
		save.setToolTipText("Saves the current document");
		save.setMnemonic(KeyEvent.VK_S);
		KeyStroke keyStrokeSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		save.setAccelerator(keyStrokeSave);
		
		edit.add(save);
	}
	
	private void initializeCustomerComponents() {
		//this method sets up the customer dashboard specific components
		rentEquipment = new JMenuItem("Equipment Rental");
		rentEquipment.setToolTipText("Select a piece of equipment to rent");
		rentEquipment.setMnemonic(KeyEvent.VK_R);
		KeyStroke keyStrokeRent = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
		rentEquipment.setAccelerator(keyStrokeRent);
		
		viewTransaction = new JMenuItem("View Transactions");
		viewTransaction.setToolTipText("View past Transactions");
		viewTransaction.setMnemonic(KeyEvent.VK_T);
		KeyStroke keyStrokeTrans = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
		viewTransaction.setAccelerator(keyStrokeTrans);
		
		file.add(rentEquipment);
		file.add(viewTransaction);
	}
	
	private void initializeEmployeeComponents() {
		//this method sets up the employee dashboard specific components
		viewRentRequest = new JMenuItem("View Rental Requests");
		viewRentRequest.setToolTipText("View the list of pending rental requests");
		viewRentRequest.setMnemonic(KeyEvent.VK_R);
		KeyStroke keyStrokeRequest = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
		viewRentRequest.setAccelerator(keyStrokeRequest);
		
		viewInventory = new JMenuItem("View Equipment Inventory");
		viewInventory.setToolTipText("View the list of company equipment");
		viewInventory.setMnemonic(KeyEvent.VK_I);
		viewInventory.setDisplayedMnemonicIndex(15);
		KeyStroke keyStrokeInventory = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
		viewInventory.setAccelerator(keyStrokeInventory);
		
		scheduleEquipment = new JMenuItem("Schedule Equipment");
		scheduleEquipment.setToolTipText("Schedule a piece of equipment for an upcoming event.");
		scheduleEquipment.setMnemonic(KeyEvent.VK_E);
		scheduleEquipment.setDisplayedMnemonicIndex(9);
		KeyStroke keyStrokeSchedule = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
		scheduleEquipment.setAccelerator(keyStrokeSchedule);
		
		transDoc = new JMenuItem("Generate Transaction Documents");
		transDoc.setToolTipText("Create Quotations, Invoices and Receipts for bookings and rentals");
		transDoc.setMnemonic(KeyEvent.VK_D);
		KeyStroke keyStrokeTransDoc = KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK);
		transDoc.setAccelerator(keyStrokeTransDoc);
		
		file.add(viewRentRequest);
		file.add(viewInventory);
		file.add(scheduleEquipment);
		file.add(transDoc);
	}
	
	private void addToMenuBar() {//adds the file and edit menus to the menubar 
		menuBar.add(file);
		menuBar.add(edit);
	}
	
	private void registerDashboardListeners() {
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//implement what happens when save is used
				JOptionPane.showMessageDialog(frame, "Saving","Saved",JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private void registerCustomerListeners() {
		//creates the internal frames for the selected menu items for the customer dashboard
		rentEquipment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new RentEquipmentFrame());
			}
		});
		
		viewTransaction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ViewTransactionFrame());
			}
		});
	}
	
	private void registerEmployeeListeners() {
		//creates the internal frames for the selected menu items for the employee dashboard
		viewRentRequest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ViewRentRequestFrame());
			}
		});
		
		viewInventory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ViewInventoryFrame());
			}
		});
		
		scheduleEquipment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ScheduleEquipmentFrame());
			}
		});
		
		transDoc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new TransactionDocumentFrame());
			}
		});
	}
}