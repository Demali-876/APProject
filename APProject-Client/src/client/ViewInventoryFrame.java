package client;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import server.Equipment;

public class ViewInventoryFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jsp;
	
	public ViewInventoryFrame(List<Equipment> list) {
		super("Equipment Inventory",true,true,true,true);
		EquipmentTableModel model = new EquipmentTableModel(list);
		table = new JTable(model);
		jsp = new JScrollPane(table);
		
		this.add(jsp);
		this.setSize(500,500);
		this.setVisible(true);
	}
}
