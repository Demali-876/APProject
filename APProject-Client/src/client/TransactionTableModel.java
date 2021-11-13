package client;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import server.DBRequest;

public class TransactionTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] {"TransactionID","EquipID","CusID","RequestID",
			"ReserveDate","RequestDate","ReturnDate", "RequestStatus", "Cost"};
	private Class<?>[] columnClass = new Class[] {Integer.class,Integer.class,Integer.class,
			Integer.class,String.class,String.class,String.class,String.class,Float.class};
	private List<DBRequest> list;

	public TransactionTableModel(List<DBRequest> list) {
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
		return 9;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0: return list.get(rowIndex).getTransactionId();
		case 1: return list.get(rowIndex).getEquipId();
		case 2: return list.get(rowIndex).getCusId();
		case 3: return list.get(rowIndex).getRequestId();
		case 4: return list.get(rowIndex).getDateRequested();
		case 5: return list.get(rowIndex).getDateReserved();
		case 6: return list.get(rowIndex).getReturnDate();
		case 7: return list.get(rowIndex).getRequestStatus();
		case 8: return list.get(rowIndex).getCost();
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
