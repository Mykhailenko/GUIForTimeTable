package time_table.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import time_table.data_base.GroupsTransaction;
import time_table.models.GroupsModel;
import time_table.objects.GroupListInfo;


public class GroupListPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private GroupsModel groupsModel;
	private GroupsTransaction trans = new GroupsTransaction();
	
	
	public GroupListPanel(){
		GroupListInfo groupInfo = trans.getGroupsData();
		if(groupInfo.getList().size()!=0){
			groupsModel = new GroupsModel(groupInfo);
		}else 
			groupsModel = new GroupsModel();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel text_panel_first = new JPanel(new GridLayout(1,1));
		text_panel_first.setPreferredSize(new Dimension(200,474));
		text_panel_first.setMaximumSize(text_panel_first.getPreferredSize());
		//text_panel_first.setMinimumSize(text_panel_first.getPreferredSize());
		text_panel_first.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JPanel text_label = BoxLayoutUtils.createHorizontalPanel();
		text_label.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel text1_label = BoxLayoutUtils.createHorizontalPanel();
		text1_label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel label = new JLabel("<html>Определите для каждой специальности аббревиатуру и число групп.<p><p>" +
				"Данная информация будет использована при составлении расписания.<p><p>" +
				"Введенные данные вступают в силу только после схранения.</html>");
		text1_label.setBackground(Color.white);
		text1_label.add(label);
		text1_label.setPreferredSize(new Dimension(500, 225));
		text1_label.setMaximumSize(text1_label.getPreferredSize());
		text_label.setBackground(Color.white);
		text_label.add(text1_label);
		text_panel_first.add(text_label);
		
		JTable table = new JTable(groupsModel);
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
		JComboBox<String> specialty_variants = new JComboBox<String>();
		specialty_variants.addItem("");
		GroupsTransaction trans = new GroupsTransaction();
		ArrayList<String> values_specialty = trans.getSpecialies();
		if(values_specialty!=null){
			for(String str: values_specialty)
				specialty_variants.addItem(str);
		}
		JComboBox<String> counrse_variants = new JComboBox<String>();
		counrse_variants.addItem("");
		int counrse = trans.getCourse();
		for(int k=0; k<counrse; k++)
			counrse_variants.addItem(Integer.toString(k+1));
		TableColumn specialty_column = column_model.getColumn(GroupsModel.SPECIALTY);
		TableColumn sem_column = column_model.getColumn(GroupsModel.SEMESTER);	
		specialty_column.setCellEditor(new DefaultCellEditor(specialty_variants));
		sem_column.setCellEditor(new DefaultCellEditor(counrse_variants));

		JPanel table_panel_first = BoxLayoutUtils.createHorizontalPanel();
		table_panel_first.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		table_panel_first.add(new JScrollPane(table));
		
		add(text_panel_first);
		add(table_panel_first);
	}
	public GroupsModel getTableGroupsModel(){
		return groupsModel;
	}
}

