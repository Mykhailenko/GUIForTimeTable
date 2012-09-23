package time_table.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import time_table.data_base.ComputerTTTracsaction;
import time_table.models.ComputerTTModel;
import time_table.objects.ComputerTimeTable;

public class ComputerTTListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ComputerTTModel comp_model;
	private ComputerTTTracsaction trans = new ComputerTTTracsaction();

	public ComputerTTListPanel() {
		ComputerTimeTable comp_ttInfo = trans.getComputerTimeTable();
		if(comp_ttInfo.getList().size()!=0){
			comp_model = new ComputerTTModel(comp_ttInfo);
		}else 
			comp_model = new ComputerTTModel();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel text_panel_first = new JPanel(new GridLayout(1, 1));
		text_panel_first.setPreferredSize(new Dimension(200, 474));
		text_panel_first.setMaximumSize(text_panel_first.getPreferredSize());
		// text_panel_first.setMinimumSize(text_panel_first.getPreferredSize());
		text_panel_first.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel text_label = BoxLayoutUtils.createHorizontalPanel();
		text_label.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel text1_label = BoxLayoutUtils.createHorizontalPanel();
		text1_label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel label = new JLabel(
				"<html>Для определения расписания компьютерных классов введите номер аудитории в соответствующей ячейке.<p><p>"
						+ "Данная информация будет использована при составлении расписания.<p><p>"
						+ "Введенные данные вступают в силу только после схранения.</html>");
		text1_label.setBackground(Color.white);
		text1_label.add(label);
		text1_label.setPreferredSize(new Dimension(500, 225));
		text1_label.setMaximumSize(text1_label.getPreferredSize());
		text_label.setBackground(Color.white);
		text_label.add(text1_label);
		text_panel_first.add(text_label);

		JTable table = new JTable(comp_model);
		table.setFont(new Font("Serif", Font.BOLD, 20));
		table.setRowHeight(50);
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
				column.setCellRenderer(table.getTableHeader()
						.getDefaultRenderer());
				break;
			}
			j++;
		}

		JPanel table_panel_first = BoxLayoutUtils.createHorizontalPanel();
		table_panel_first
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		table_panel_first.add(new JScrollPane(table));

		add(text_panel_first);
		add(table_panel_first);
	}
	
	public ComputerTTModel getModel(){
		return comp_model;
	}
}
