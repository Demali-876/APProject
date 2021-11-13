package client;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

public class TransactionDocumentFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea text;
	
	public TransactionDocumentFrame() {
		super("Transaction Document",true,true,true,true);
		
		text = new JTextArea();
		this.add(text);
		
		this.setSize(500,500);
		this.setVisible(true);
	}
}