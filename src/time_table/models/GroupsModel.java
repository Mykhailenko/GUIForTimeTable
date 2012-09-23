package time_table.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import time_table.objects.Group;
import time_table.objects.GroupListInfo;

public class GroupsModel  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
		
	public GroupsModel(){
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
	public GroupsModel(GroupListInfo list){
		data = new Object[row_count][column_count];
		int i=0;
		ArrayList<Group> groups_data = list.getList();
		for(Group cur: groups_data){
			data[i][0]=(String)new Integer(i+1).toString();
			data[i][1]=(String)cur.getSpecialty();
			data[i][2]=(String)new Integer(cur.getCourse()).toString();
			data[i][3]=(String)cur.getAbbr();
			data[i][4]=(String)new Integer(cur.getCount()).toString();
			data[i][5]="";
			i++;
		}
		for(i =list.getSize() ; i<row_count; i++){
			data[i][0]=new Integer(i+1).toString();
			data[i][1]="";
			data[i][2]="";
			data[i][3]="";
			data[i][4]="";
			data[i][5]="";
		}
	}
	
	public GroupListInfo getGroupstData(){
		GroupListInfo result = new GroupListInfo();
		for(int i = 0; i<row_count; i++){
			if(data[i][1].toString().compareTo("")!=0){
				Group g = new Group();
				g.setAbbr(data[i][3].toString());
				g.setSpecialty(data[i][1].toString());
				g.setCourse(Integer.parseInt(data[i][2].toString()));
				g.setCount(Integer.parseInt(data[i][4].toString()));
				result.addGroup(g);
			}
		}
		return result;
	}
	
	public String makeGroupName(String abbr, int course, int i) {
		return abbr+"\\"+new Integer(course).toString()+new Integer(i+1).toString();
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
		return c == SPECIALTY || c == SEMESTER|| c == AB|| c == NUM;
	}
	public void removeData(){
		for(int i=0; i<row_count; i++)
			for(int j = 1; j<column_count; j++)
				data[i][j]="";
	}
	
	private Object[][] data;
	private int row_count=40;
	private int column_count = 6;
	private String[] columnNames = {"", "Специальность", "Курс",
			 "Аббревиатура", "Число групп", "" };

	public static final int SPECIALTY = 1;
	public static final int SEMESTER = 2;
	public static final int AB = 3;
	public static final int NUM = 4;
}



