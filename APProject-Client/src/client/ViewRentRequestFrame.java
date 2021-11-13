package client;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import server.DBRequest;

public class ViewRentRequestFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jsp;
	
	public ViewRentRequestFrame(List<DBRequest> list) {
		super("Rental Requests",true,true,true,true);
		TransactionTableModel model = new TransactionTableModel(list);
		table = new JTable(model);
		jsp = new JScrollPane(table);
		
		this.add(jsp);
		this.setSize(500,500);
		this.setVisible(true);
	}
}
