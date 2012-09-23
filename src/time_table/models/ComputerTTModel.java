package time_table.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import time_table.objects.ComputerTimeTable;
import time_table.objects.DayComputerClass;

public class ComputerTTModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
		
	public ComputerTTModel(){
		data = new Object[row_count][column_count];
		for(int i =0 ; i<row_count; i++){
			data[i][0]=new Integer(i+1).toString();
			data[i][1]="";
			data[i][2]="";
			data[i][3]="";
			data[i][4]="";
			data[i][5]="";
		}
	}
	public ComputerTTModel(ComputerTimeTable comp_tt){
		ArrayList<DayComputerClass> list = comp_tt.getList();
		data = new Object[row_count][column_count];
		for(int i =0 ; i<row_count; i++){
			data[i][0]=new Integer(i+1).toString();
			data[i][1]="";
			data[i][2]="";
			data[i][3]="";
			data[i][4]="";
			data[i][5]="";
		}
		for(DayComputerClass cur: list){
			data[cur.getNumberLess()-1][cur.getDayNumber()-1]=cur.getAuditory();
		}
	}
	
	public ComputerTimeTable getCompTTData(){
		ComputerTimeTable result = new ComputerTimeTable();
		for(int i = 0; i<row_count; i++){
			for(int j = 1; j<column_count; j++){
				if(data[i][j].toString().compareTo("")!=0){
					DayComputerClass day = new DayComputerClass();
					day.setAuditory(data[i][j].toString());
					day.setDayNumber(j+1);
					day.setNumberLess(i+1);
					result.addDayCCInfo(day);
				}
			}
		}
		return result;
	}
	
	public String getColumnName(int c) {
		return columnNames[c];
	}

	@SuppressWarnings("unchecked")
	public Class<Object> getColumnClass(int c) {
		return (Class<Object>)data[0][c].getClass();
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

	
	public Object[][] getData(){
		return data;
	}	
	public void printData(){
		for(int i =0 ; i<data.length; i++){
			for(int j=0; j<data[0].length; j++)
				System.out.print(data[i][j]+"    ");
			System.out.println();
		}
	}
	public boolean isCellEditable(int r, int c) {
		return c == P || c == V|| c == S|| c == CH|| c == PT;
	}
	public void removeData(){
		for(int i=0; i<row_count; i++)
			for(int j = 1; j<column_count; j++)
				data[i][j]="";
	}
	
	private Object[][] data;
	private int row_count=6;
	private int column_count = 6;
	private String[] columnNames = {"", "ом", "бр",
			 "яп", "вр", "ор" };
		
	public static final int P = 1;
	public static final int V = 2;
	public static final int S = 3;
	public static final int CH = 4;
	public static final int PT = 5;
}


