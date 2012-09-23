package time_table.gui;

import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SubjectsTabe {
	private SubejctsTableModel model;
	private JTable table;
	
	public SubjectsTabe(){
		model = new SubejctsTableModel();		
		table = new JTable(model);
		table.setRowHeight(50);
		TableColumnModel column_model = table.getColumnModel();
		Enumeration<TableColumn> e = column_model.getColumns();
		int j = 0;
		while (e.hasMoreElements()) {
			TableColumn column = (TableColumn) e.nextElement();
			JTableHeader header_model = table.getTableHeader();
			header_model.setReorderingAllowed(false);
			header_model.setResizingAllowed(false);
			header_model.setDefaultRenderer(new CurriculumHeaderRenderer());
			switch (j) {
			case 0:
				column.setPreferredWidth(20);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				column.setCellRenderer(table.getTableHeader().getDefaultRenderer());
				break;
			case 1:
				column.setPreferredWidth(180);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				break;
			case 2:
				column.setPreferredWidth(50);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				break;
			case 3:
				column.setPreferredWidth(50);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				break;
			case 4:
				column.setPreferredWidth(60);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				break;
			}
			j++;
		}
			
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public void setModel(SubejctsTableModel model) {
		this.model = model;
	}
	public SubejctsTableModel getModel(){
		return model;
	}
}
