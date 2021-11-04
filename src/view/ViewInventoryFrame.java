package view;
import javax.swing.JInternalFrame;

public class ViewInventoryFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private String[] rowData;
	
	public ViewInventoryFrame() {
		super("Equipment Inventory",true,true,true,true);
		//implement table model and table
		this.setSize(500,500);
		this.setVisible(true);
	}
}
