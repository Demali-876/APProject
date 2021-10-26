package view;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrizzlyApp {
	private JFrame frame;
	private JButton button;
	private JLabel userIdLabel;
	private JLabel passwordLabel;
	private JTextField userIdTF;
	private JPasswordField password;
	private JPanel userIdPanel;
	private JPanel passwordPanel;
	private JPanel buttonPanel;
	
	public GrizzlyApp() {
		initializeLoginComponents();
		registerLoginButtonListener();
		addLoginPanel();
		configFrame();
	}
	
	public void initializeLoginComponents() {//initializing the attributes that will be use for login screen
		frame = new JFrame("Grizzly App");
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
	
	public void configFrame() {//add components to the frame finish setting up frame
		frame.add(userIdPanel);
		frame.add(passwordPanel);
		frame.add(buttonPanel);
		frame.setSize(350,150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void registerLoginButtonListener() {//setup what the login button does
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//implement verification check and database connectivity later
				JOptionPane.showMessageDialog(null, "Login Succesful");
			}
		});
	}
}

