package time_table.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import time_table.data_base.CurriculumTransaction;
import time_table.gui.CurriculumHeaderRenderer;

public class StoreCurriculumModels {
	private CopyOnWriteArrayList<CurriculumModel> models;
	
	public StoreCurriculumModels(JTabbedPane tabbed){
		models = new CopyOnWriteArrayList<CurriculumModel>();
		this.tabbed = tabbed;
	}
	public StoreCurriculumModels(){
		models = new CopyOnWriteArrayList<CurriculumModel>();
	}
	public JTabbedPane getTabbed() {
		return tabbed;
	}
	public void setTabbed(JTabbedPane tabbed) {
		this.tabbed = tabbed;
	}
	public void initData(){
		CurriculumTransaction trans = new CurriculumTransaction();
		ArrayList<String> list = trans.getNameSpecialties();
		for(String spec : list){
			ArrayList<ArrayList<Object>> data = trans.getCurriculumData(spec);
			if(data.size()!=0){
				CurriculumModel m = new CurriculumModel(this.transformation(data), spec);
				models.add(m);
				addTabbedPane(m);
			}
		}
	}
	
	public ArrayList<String> getListSpecialies(){
		ArrayList<String> result = new ArrayList<String>();
		for(CurriculumModel model: models)
			result.add(model.getSpecialty_name());
		return result;
	}

	public void addModel(ArrayList<ArrayList<Object>> list, String specialty_name){
		Object [][] data = transformation(list);
		System.out.println(specialty_name);
		if(isModelExist(specialty_name)){
			for(CurriculumModel cur_mod : models)
				if(cur_mod.getSpecialty_name().compareTo(specialty_name)==0){
					cur_mod.removeData();
					cur_mod.setData(this.transformation(list));
				}
		}else{
			CurriculumModel m = new CurriculumModel(data, specialty_name); 
			models.add(m);
			addTabbedPane(m);
		}
	}
	public void removeModel(String specialty){
		int i =0;
		for(CurriculumModel mod :models){
			if(mod.getSpecialty_name().compareTo(specialty)==0)
				models.remove(i);
			i++;
		}
		int tab_count = this.tabbed.getTabCount();
		System.out.println(tab_count);
		for(int j =0; j< tab_count;j++){
			System.out.println(tabbed.getTitleAt(j));
			if(tabbed.getTitleAt(j).compareTo(specialty)==0){
				tabbed.remove(j);
				break;
			}
		}
	}
	public int getModelsCount(){
		return models.size();
	}
	private boolean isModelExist(String specialty_name){
		for(CurriculumModel m: models)
			if(m.getSpecialty_name().compareTo(specialty_name)==0)
				return true;
		return false;
	}
	private Object[][] transformation(ArrayList<ArrayList<Object>> res){
		int row_count = res.size();
		int column_count = res.get(0).size();
		Object [][] trans_data = new Object[row_count][column_count];
		for(int i = 0; i<row_count; i++){
			for(int j = 0; j<column_count; j++)
				trans_data[i][j] = res.get(i).get(j);
		}
		return trans_data;
	}
	private void addTabbedPane(CurriculumModel m){
		JTable table = new JTable(m);
		table.setRowHeight(25);
		TableColumnModel column_model = table.getColumnModel();
		Enumeration<TableColumn> e = column_model.getColumns();
		int j = 0;
		while (e.hasMoreElements()) {
			TableColumn column = (TableColumn) e.nextElement();
			JTableHeader header_model = table.getTableHeader();
			header_model.setReorderingAllowed(true);
			header_model.setResizingAllowed(true);
			header_model.setDefaultRenderer(new CurriculumHeaderRenderer());
			switch (j) {
			case 0:
				column.setPreferredWidth(50);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				column.setCellRenderer(table.getTableHeader().getDefaultRenderer());
				break;
			}
			j++;
		}
		this.tabbed.addTab(m.getSpecialty_name(), new JScrollPane(table));
	}
	
	private JTabbedPane tabbed;
}
