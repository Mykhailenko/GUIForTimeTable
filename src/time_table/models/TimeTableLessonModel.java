package time_table.models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TimeTableLessonModel extends AbstractTableModel {
	private static final long serialVersionUID = -2858833941307648938L;

	public TimeTableLessonModel(int number, boolean isEmptyHeader) {
		super();
		if (!isEmptyHeader) {
			data = new Object[row_count][column_count];
			data[0][0] = number;
			data[1][0] = "";
			visible_columns = new Vector<Integer>();
			for (int i = 0; i < columnNames.length; i++)
				visible_columns.add(new Integer(i));
			for (int i = 1; i < columnNames.length; i++) {
				data[0][i] = "";
				data[1][i] = "";
			}
		} else {
			data = new Object[0][column_count];
			visible_columns = new Vector<Integer>();
			for (int i = 0; i < columnNames.length; i++)
				visible_columns.add(new Integer(i));
		}
		visible_rows = new Vector<Integer>();
		visible_rows.add(0);
	}

	public int getColumnCount() { // получаем кол-во ВИДИМЫХ столбцов
		return visible_columns.size();
	}

	public String getColumnName(int col) { // получаем заголовок
		// для этого с помощь вектора видимых столбцов получаем номер столбца,
		// к-й надо отобразить
		int true_column = ((Integer) visible_columns.get(col)).intValue();
		return columnNames[true_column];
	}

	public Object getValueAt(int row, int col) {
		// аналогично getColumnName
		int true_column = ((Integer) visible_columns.get(col)).intValue();
		int true_row = ((Integer) visible_rows.get(row)).intValue();
		return data[true_row][true_column];
	}

	@SuppressWarnings("unchecked")
	public Class<Object> getColumnClass(int c) {
		return (Class<Object>) data[0][c].getClass();
	}

	@Override
	public int getRowCount() {
		return visible_rows.size();
	}

	public void setValueAt(Object obj, int r, int c) {
		data[r][c] = obj;
	}

	public boolean isCellEditable(int r, int c) {
		return c == LOWER_WEEK || c == UPPER_WEEK || c == SUBGROUPS
				|| c == AUDIT1 || c == AUDIT2 || c == LESSON1 || c == LESSON2;
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

	public void turnOffColumn(int col) {
		Integer intCol = new Integer(col);
		int i = visible_columns.indexOf(intCol);
		if (i != -1) {
			visible_columns.remove(i);
		}
		;
		fireTableStructureChanged();
	}

	public void resetColumns(int i) {
		visible_columns.add(new Integer(i));
		fireTableStructureChanged();
	}
	public void turnOffRow(int col) {
		Integer intCol = new Integer(col);
		int i = visible_rows.indexOf(intCol);
		if (i != -1) {
			visible_rows.remove(i);
		}
		;
		fireTableStructureChanged();
	}

	public void resetRow(int i) {
		visible_rows.add(new Integer(i));
		fireTableStructureChanged();
	}

	private Object[][] data;
	private String[] columnNames = { "", "Н.Н", "В.Н.", "Подгруппы", "Аудит.",
			"Занятие", "Аудит.", "Занятие" };
	private Vector<Integer> visible_columns;
	private Vector<Integer> visible_rows;
	private int row_count = 2;
	private int column_count = 8;

	public static final int UPPER_WEEK = 1;
	public static final int LOWER_WEEK = 2;
	public static final int SUBGROUPS = 3;
	public static final int AUDIT1 = 4;
	public static final int LESSON1 = 5;
	public static final int AUDIT2 = 6;
	public static final int LESSON2 = 7;

}
