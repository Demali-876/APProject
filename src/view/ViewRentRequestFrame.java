package view;
import javax.swing.JInternalFrame;

public class ViewRentRequestFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private String[] rowData;
	
	public ViewRentRequestFrame() {
		super("Rental Requests",true,true,true,true);
		//implement table model and table
		this.setSize(500,500);
		this.setVisible(true);
	}
}
