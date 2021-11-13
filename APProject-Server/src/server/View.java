package server;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class View {
	private JFrame frame;
	private JLabel label;
	
	public View() {
		frame = new JFrame("Grizzly Server");
		label = new JLabel("Server Online");
				
		label.setBounds(75,75,150,30);
		
		frame.setSize(250,250);
		frame.add(label);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Server();
	}
}
