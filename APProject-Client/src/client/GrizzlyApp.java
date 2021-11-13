package client;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
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
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import server.Equipment;
import server.DBRequest;
import server.Customer;
import server.Employee;

public class GrizzlyApp{
	private JFrame frame;
	private JButton button;
	private JLabel userIdLabel;
	private JLabel passwordLabel;
	private JTextField userIdTF;
	private JPasswordField password;
	private JPanel userIdPanel;
	private JPanel passwordPanel;
	private JPanel loginTypePanel;
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
	private JRadioButton cusButton;
	private JRadioButton empButton;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket conSocket;
	private int id;
	
	public GrizzlyApp() {
		try {
			conSocket = new Socket("127.0.0.1",8000);
			oos = new ObjectOutputStream(conSocket.getOutputStream());
			ois = new ObjectInputStream(conSocket.getInputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
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
		cusButton = new JRadioButton("Customer");
		empButton = new JRadioButton("Employee");
		userIdTF = new JTextField(20);
		password = new JPasswordField(20);
		userIdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		userIdPanel.setSize(new Dimension(450,30));
		passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		passwordPanel.setSize(new Dimension(450,30));
		loginTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		loginTypePanel.setSize(new Dimension(450,30));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setSize(new Dimension(450,30));
		ButtonGroup bg = new ButtonGroup();
		cusButton.setSelected(true);//preselects a login type
		bg.add(cusButton);
		bg.add(empButton);
	}
	
	public void addLoginPanel() {//add the components into their respective panels
		userIdPanel.add(userIdLabel);
		userIdPanel.add(userIdTF);
		
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		
		loginTypePanel.add(cusButton);
		loginTypePanel.add(empButton);
		
		buttonPanel.add(button);
	}
	
	public void configFrame() {//add components to the frame to finish setting up frame
		frame.add(userIdPanel);
		frame.add(passwordPanel);
		frame.add(loginTypePanel);
		frame.add(buttonPanel);
		frame.setSize(350,150);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					oos.writeObject("Exit");
				}catch(IOException ex) {
					ex.printStackTrace();
				}finally {
					closeConnection();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void registerLoginButtonListener() {//setup what the login button does
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cusButton.isSelected()) {
					try {
						if(password.getText().isBlank()) {
							JOptionPane.showMessageDialog(frame,"Enter a Password","Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					    oos.writeObject("Customer Login");
					    try {
					    	oos.writeObject(Integer.parseInt(userIdTF.getText()));
					    }catch(NumberFormatException exc) {
					    	JOptionPane.showMessageDialog(frame,"ID must contain only numbers",
					    			"Error",JOptionPane.ERROR_MESSAGE);
					    	return;
					    }
					    oos.writeObject(password.getPassword());
					    if((boolean)ois.readObject() == true) {
					    	id = Integer.parseInt(userIdTF.getText());
					    	JOptionPane.showMessageDialog(null, "Login Succesful");
							initializeDashboardComponents();
							registerDashboardListeners();
							initializeCustomerComponents();
							registerCustomerListeners();
							addToMenuBar();
							DashboardFrame();
					    }else {
					    	JOptionPane.showMessageDialog(frame,"Incorrect UserID or Password",
					    			"Login Failed",JOptionPane.INFORMATION_MESSAGE);
					    	return;
					    }
					}catch(IOException ex) {
						ex.printStackTrace();
					}catch(ClassNotFoundException ex) {
						ex.printStackTrace();
					}catch(ClassCastException ex) {
						ex.printStackTrace();
					}
				}else if(empButton.isSelected()) {
					try {
					    oos.writeObject("Employee Login");
					    try {
					    	oos.writeObject(Integer.parseInt(userIdTF.getText()));
					    }catch(NumberFormatException exc) {
					    	JOptionPane.showMessageDialog(frame,"ID must contain only numbers",
					    			"Error",JOptionPane.ERROR_MESSAGE);
					    	return;
					    }
					    oos.writeObject(password.getPassword());
					    if((boolean)ois.readObject() == true) {
					    	id = Integer.parseInt(userIdTF.getText());
					    	JOptionPane.showMessageDialog(null, "Login Succesful");
							initializeDashboardComponents();
							registerDashboardListeners();
							initializeEmployeeComponents();
							registerEmployeeListeners();
							addToMenuBar();
							DashboardFrame();
					    }else {
					    	JOptionPane.showMessageDialog(frame,"Incorrect UserID or Password",
					    			"Login Failed",JOptionPane.INFORMATION_MESSAGE);
					    	return;
					    }
					}catch(IOException ex) {
						ex.printStackTrace();
					}catch(ClassNotFoundException ex) {
						ex.printStackTrace();
					}catch(ClassCastException ex) {
						ex.printStackTrace();
					}
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
		rentEquipment.addActionListener(new ActionListener() {//loads rental frame for customer
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				    oos.writeObject("Equipment List");
				    desktop.add(new RentEquipmentFrame((List<Equipment>)ois.readObject(),oos,ois,id));
				}catch(IOException ex) {
					ex.printStackTrace();
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(ClassCastException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		viewTransaction.addActionListener(new ActionListener() {//load transaction list for customer
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					oos.writeObject("Request List");
				    desktop.add(new ViewTransactionFrame((List<DBRequest>)ois.readObject()));
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
	
	private void registerEmployeeListeners() {
		//creates the internal frames for the selected menu items for the employee dashboard
		viewRentRequest.addActionListener(new ActionListener() {//loads rent request frame for employee
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					oos.writeObject("Request List");
				    desktop.add(new ViewRentRequestFrame((List<DBRequest>)ois.readObject()));
				}catch(IOException ex) {
					ex.printStackTrace();
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(ClassCastException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		viewInventory.addActionListener(new ActionListener() {//loads equipment inventory frame for employee
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					oos.writeObject("Equipment List");
				    desktop.add(new ViewInventoryFrame((List<Equipment>)ois.readObject()));
				}catch(IOException ex) {
					ex.printStackTrace();
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(ClassCastException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		scheduleEquipment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new ScheduleEquipmentFrame(oos,ois,id));
			}
		});
		
		transDoc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new TransactionDocumentFrame());
			}
		});
	}
	
	private void closeConnection() {
		try {
			oos.close();
			ois.close();
			conSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}