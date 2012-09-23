package time_table.gui;

import javax.swing.table.AbstractTableModel;

public class SubejctsTableModel  extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	public SubejctsTableModel() {
		super();
		data = new Object[row_count][column_count];
		for (int i = 0; i < row_count; i++) {
			data[i][0] = i + 1;
		}
		data[0][1]="<html>История Украины<p>Романов П.О.<p>лекция МФ-11, МФ-12</html>";
		data[0][2]=1;
		data[0][3]="Л";
		data[0][4]="";
		data[1][1]="<html>История Украины<p>Зорин П.Т.<p>практика МФ-11</html>";
		data[1][2]=0.5;
		data[1][3]="П";
		data[1][4]="";
		data[2][1]="<html>История Украины<p>Зубков М.М. <p>практика МФ-12</html>";
		data[2][2]=0.5;
		data[2][3]="П";
		data[2][4]="";
		data[3][1]="<html>Алгебра и геометрия<p>Куринной Г.Ч.<p>лекция МФ-11, МФ-12</html>";
		data[3][2]=1;
		data[3][3]="Л";
		data[3][4]="";
		data[4][1]="<html>Алгебра и геометрия<p>Власенко Д.В.<p>практика МФ-11</html>";
		data[4][2]=1;
		data[4][3]="П";
		data[4][4]="";
		data[5][1]="<html>Алгебра и геометрия<p>Власенко Д.В.<p>практика МФ-12</html>";
		data[5][2]=1;
		data[5][3]="П";
		data[5][4]="";
		data[6][1]="<html>Программирование<p>Зарецкая И.Т. <p>лекция МФ-11, МФ-12</html>";
		data[6][2]=1;
		data[6][3]="Л";
		data[6][4]="";
		data[7][1]="<html>Программирование<p>Зарецкая И.Т.<p>практика МФ-11</html>";
		data[7][2]=2;
		data[7][3]="П";
		data[7][4]="заявка";
		data[8][1]="<html>Программирование<p>Белова Л.П.<p>практика МФ-12</html>";
		data[8][2]=2;
		data[8][3]="П";
		data[8][4]="заявка";
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
		return c == RINGS;
	}

	public Object[][] getData() {
		int i=0;
		while(data[i][1].toString().compareTo("")!=0)
			i++;
		Object [][]result = new Object[i][2];
		for(int j=0;j<i;j++)
			result[j][1]=data[j][1];
		return result;
	}

	public void printData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++)
				System.out.print(data[i][j] + "    ");
			System.out.println();
		}
	}

	private Object[][] data;
	private String[] columnNames = { "", "Занятие","Число пар","Тип","Комп.кл."};
	private int row_count = 9;
	private int column_count = 5;

	public static final int RINGS = 1;
}