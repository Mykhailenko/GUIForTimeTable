package time_table.models;

import javax.swing.table.AbstractTableModel;

import time_table.objects.StoreEdAssign;

public class EducAssingmentModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public EducAssingmentModel(StoreEdAssign edassig, String cath_name) {
		row_count = edassig.getSize();
		data = new Object[row_count][column_count];
		for (int i = 0; i < row_count; i++) {
			data[i][0] = i + 1;
			data[i][1] = edassig.getDataAtIndex(i).getSubject_name();
			data[i][2] = edassig.getDataAtIndex(i).getSpecialty();
			data[i][3] = edassig.getDataAtIndex(i).getSemester();
			data[i][4] = edassig.getDataAtIndex(i).getType();
			data[i][5] = edassig.getDataAtIndex(i).getTeacher_name();
			data[i][6] = edassig.getDataAtIndex(i).getGroup(); 
			data[i][7] = edassig.getDataAtIndex(i).getRequest();
		}
		cathedra_name = cath_name;
	}

	public void removeData() {
		data = null;
	}

	public void setData(StoreEdAssign edassig) {
		row_count = edassig.getSize();
		data = new Object[row_count][column_count];
		for (int i = 0; i < row_count; i++) {
			data[i][0] = i + 1;
			data[i][1] = edassig.getDataAtIndex(i).getSubject_name();
			data[i][2] = edassig.getDataAtIndex(i).getSpecialty();
			data[i][3] = edassig.getDataAtIndex(i).getSemester();
			data[i][4] = edassig.getDataAtIndex(i).getType();
			data[i][5] = edassig.getDataAtIndex(i).getTeacher_name();
			data[i][6] = edassig.getDataAtIndex(i).getGroup(); 
			data[i][7] = edassig.getDataAtIndex(i).getRequest();
		}
	}

	public String getColumnName(int c) {
		return columnNames[c];
	}

	@SuppressWarnings("unchecked")
	public Class<Object> getColumnClass(int c) {
		return (Class<Object>) data[0][c].getClass();
	}

	@Override
	public int getColumnCount() {
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int r, int c) {
		return data[r][c];
	}

	public void setValueAt(Object obj, int r, int c) {
		data[r][c] = obj;
	}

	public boolean isCellEditable(int r, int c) {
		return false;
	}

	public Object[][] getData() {
		return data;
	}

	public void printData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++)
				System.out.print(data[i][j] + "    ");
			System.out.println();
		}
	}

	private Object[][] data;
	private String[] columnNames = { "", "Название дисциплины",
			"Специальность", "Семестр", "Тип", "Преподаватель","Группа","Заявна на комп.класс"};
	private int row_count;
	private int column_count = 8;
	private String cathedra_name;

	public String getCathedra_name() {
		return cathedra_name;
	}

	public void setCathedra_name(String cathedra_name) {
		this.cathedra_name = cathedra_name;
	}
}
