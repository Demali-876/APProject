package view;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

public class ViewTransactionFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private String[] rowData;
	
	public ViewTransactionFrame() {
		super("Transaction History",true,true,true,true);
		//implement table model and table
		
		this.setSize(500,500);
		this.setVisible(true);
	}
}