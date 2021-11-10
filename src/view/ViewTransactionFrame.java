package view;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewTransactionFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jsp;
	
	public ViewTransactionFrame(List<Request> list) {
		super("Transaction History",true,true,true,true);
		//implement table model and table
		TransactionTableModel model = new TransactionTableModel(list);
		table = new JTable(model);
		table.removeColumn(table.getColumn("CusID"));
		table.removeColumn(table.getColumn("RequestID"));
		table.removeColumn(table.getColumn("RequestDate"));
		jsp = new JScrollPane(table);
		
		this.add(jsp);
		this.setSize(500,500);
		this.setVisible(true);
	}
}