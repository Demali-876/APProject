package view;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTextField;
public class RentEquipmentFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private String[] rowData;
	
	public RentEquipmentFrame() {
		super("Equipment Rental",true,true,true,true);
		//implement table model and table
		this.setSize(500,500);
		this.setVisible(true);
	}
}