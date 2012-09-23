package time_table.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import time_table.models.TimeTableLessonModel;

public class LessonTimeTable implements ActionListener{
	private JTable table;
	private TimeTableLessonModel model;
	private JCheckBox check1;
	private JCheckBox check2;
	
	public LessonTimeTable(int number, boolean isEmptyHeader){
		model = new TimeTableLessonModel(number, isEmptyHeader);
		table = new JTable(model);
		//model.turnOffColumn(1);
		table.setRowHeight(50);
		model.turnOffColumn(6);
		model.turnOffColumn(7);
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
				column.setPreferredWidth(20);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				check1 = new JCheckBox();
				column.setCellEditor(new DefaultCellEditor(check1));
				check1.addActionListener(this);
				break;
			case 2:
				column.setPreferredWidth(20);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				check2 = new JCheckBox();
				column.setCellEditor(new DefaultCellEditor(check2));
				check2.addActionListener(this);
				break;
			case 3:
				column.setPreferredWidth(40);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				JComboBox<String> subgroup = new JComboBox<String>();
				for(int i =1; i< 3; i++)
					subgroup.addItem(new Integer(i).toString());
				subgroup.setSelectedIndex(0);
				column.setCellEditor(new DefaultCellEditor(subgroup));
				break;
			case 4:
				column.setPreferredWidth(40);
				column.setMinWidth(column.getPreferredWidth());
				column.setMaxWidth(column.getPreferredWidth());
				break;
			case 5:
				column.setPreferredWidth(350);
				column.setMaxWidth(column.getPreferredWidth());
				JComboBox<String> combo1 = new JComboBox<String>();
				ArrayList<String> subjects1 = this.makeValues();
				for(String cur: subjects1)
					combo1.addItem(cur);
				column.setCellEditor(new DefaultCellEditor(combo1));
				break;
			case 6:
				column.setPreferredWidth(40);
				column.setMinWidth(column.getPreferredWidth());
				column.setMaxWidth(column.getPreferredWidth());
				break;
			case 7:
				column.setPreferredWidth(350);
				column.setMaxWidth(column.getPreferredWidth());
				JComboBox<String> combo2 = new JComboBox<String>();
				ArrayList<String> subjects2 = this.makeValues();
				for(String cur: subjects2)
					combo2.addItem(cur);
				column.setCellEditor(new DefaultCellEditor(combo2));
				break;
			}
			j++;
		}			
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==check1){
			if(check1.isSelected()){
				model.resetColumns(6);
				model.resetColumns(7);
			}
			
		}
	}
	public void turnOffColumn(int col) {
		model.turnOffColumn(col);
	}

	public void resetColumns(int i) {
		model.resetColumns(i);
	}
	public void turnOffRow(int row) {
		model.turnOffRow(row);
	}

	public void resetRow(int i) {
		model.resetRow(i);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public TimeTableLessonModel getModel() {
		return model;
	}

	public void setModel(TimeTableLessonModel model) {
		this.model = model;
	}
	//it will be deleted...
	public ArrayList<String> makeValues(){
		ArrayList<String> result = new ArrayList<String>();
		String s = "<html><p><p></html>";
		result.add(s);
		 s = "<html>История Украины<p>Романов П.О.<p>лекция МФ-11, МФ-12</html>";
		 result.add(s);
		 s = "<html>История Украины<p>Зорин П.Т.<p>практика МФ-11</html>";
		 result.add(s);
		 s = "<html>История Украины<p>Зубков М.М. <p>практика МФ-12</html>";
		 result.add(s);
		 s = "<html>Алгебра и геометрия<p>Куринной Г.Ч.<p>лекция МФ-11, МФ-12</html>";
		 result.add(s);
		 s = "<html>Алгебра и геометрия<p>Власенко Д.В.<p>практика МФ-11</html>";
		 result.add(s);
		 s = "<html>Алгебра и геометрия<p>Власенко Д.В.<p>практика МФ-12</html>";
		 result.add(s);
		 s = "<html>Программирование<p>Зарецкая И.Т. <p>лекция МФ-11, МФ-12</html>";
		 result.add(s);
		 s = "<html>Программирование<p>Зарецкая И.Т.<p>практика МФ-11</html>";
		 result.add(s);
		 s = "<html>Программирование<p>Белова Л.П.<p>практика МФ-12</html>";
		 result.add(s);
		return result;
	}
}
