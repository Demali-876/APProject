package client;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import server.Equipment;

public class EquipmentTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] {"EquipID","EquipName","EquipStatus",
			"EquipCategory"};
	private Class<?>[] columnClass = new Class[] {Integer.class,String.class,String.class,
			String.class};
	private List<Equipment> list;
	
	public EquipmentTableModel(List<Equipment> list) {
		this.list = list;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0: return list.get(rowIndex).getEquipID();
		case 1: return list.get(rowIndex).getEquipName();
		case 2: return list.get(rowIndex).getEquipstatus();
		case 3: return list.get(rowIndex).getEquipCat();
		default: return null;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
	
	@Override
	public void fireTableCellUpdated(int row, int column) {
		super.fireTableCellUpdated(row, column);
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Class<?> getColumnClass(int col){
		return columnClass[col];
	}

}