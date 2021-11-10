package view;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class RentEquipmentFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jsp;
	private JButton button;
	private JLabel label;
	private JTextField text;
	private JPanel panel;
	
	public RentEquipmentFrame(List<Equipment> list) {
		super("Equipment Rental",true,true,true,true);
		this.setLayout(new BorderLayout());
		EquipmentTableModel model = new EquipmentTableModel(list);
		table = new JTable(model);
		table.removeColumn(table.getColumn("EquipStatus"));
		table.setToolTipText("Click on the equipment you would like to rent, then click on the"
				+ "\"Rent Equipment\" button");
		jsp = new JScrollPane(table);
		panel = new JPanel();
		label = new JLabel("Date: ");
		text = new JTextField(15);
		text.setToolTipText("Enter the date you wish to reserve the selected equipment for."
				+ "Enter in DD/MM/YYYY format");
		
		button = new JButton("Rent Equipment");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.getValueAt(table.getSelectedRow(), 0);
					table.getValueAt(table.getSelectedRow(), 1);
					table.getValueAt(table.getSelectedRow(), 2);
					table.getValueAt(table.getSelectedRow(), 3);
				}catch(IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(button,"Please select an item","Error",JOptionPane.ERROR_MESSAGE);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		panel.add(label);
		panel.add(text);
		panel.add(button);
		this.add(jsp,BorderLayout.CENTER);
		this.add(panel,BorderLayout.PAGE_END);
		this.setSize(500,500);
		this.setVisible(true);
	}
}