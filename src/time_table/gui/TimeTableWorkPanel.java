package time_table.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import time_table.data_base.CurriculumTransaction;

public class TimeTableWorkPanel extends JPanel {
	public TimeTableWorkPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel left = new JPanel(new GridLayout(1, 1));
		left.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		left.setPreferredSize(new Dimension(400, 1500));
		left.setMaximumSize(left.getPreferredSize());
		JPanel left_inner = BoxLayoutUtils.createVerticalPanel();
		left_inner.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		// /
		JPanel settings_panel = BoxLayoutUtils.createVerticalPanel();
		settings_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		settings_panel.setPreferredSize(new Dimension(390, 100));
		settings_panel.setMaximumSize(settings_panel.getPreferredSize());
		settings_panel.setMinimumSize(settings_panel.getPreferredSize());
		JPanel settingsInner_panel = BoxLayoutUtils.createVerticalPanel();

		for (int i = 0; i < 5; i++) {
			combo_course.addItem(i + 1);
		}
		CurriculumTransaction t = new CurriculumTransaction();
		ArrayList<String> specialty_names = t.getNameSpecialties();
		for (String name : specialty_names)
			combo_specialty.addItem(name);

		JPanel label1 = new JPanel(new GridLayout(1, 1));
		label1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label1.setPreferredSize(new Dimension(150, 50));
		label1.setMaximumSize(label1.getPreferredSize());
		label1.setMinimumSize(label1.getMinimumSize());
		label1.add(course);
		JPanel label2 = new JPanel(new GridLayout(1, 1));
		label2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label2.setPreferredSize(new Dimension(150, 50));
		label2.setMaximumSize(label2.getPreferredSize());
		label2.setMinimumSize(label2.getMinimumSize());
		label2.add(specialty);

		JPanel combo1 = new JPanel(new GridLayout(1, 1));
		combo1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		combo1.setPreferredSize(new Dimension(300, 50));
		combo1.setMaximumSize(combo1.getPreferredSize());
		combo1.setMinimumSize(combo1.getMinimumSize());
		combo_course.setBackground(Color.white);
		combo1.add(combo_course);
		JPanel combo2 = new JPanel(new GridLayout(1, 1));
		combo2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		combo2.setPreferredSize(new Dimension(300, 50));
		combo2.setMaximumSize(combo2.getPreferredSize());
		combo2.setMinimumSize(combo2.getMinimumSize());
		combo_specialty.setBackground(Color.white);
		combo2.add(combo_specialty);

		JPanel first = BoxLayoutUtils.createHorizontalPanel();
		first.add(label1);
		first.add(combo1);
		JPanel second = BoxLayoutUtils.createHorizontalPanel();
		second.add(label2);
		second.add(combo2);

		settingsInner_panel.add(first);
		settingsInner_panel.add(second);
		settings_panel.add(settingsInner_panel);
		// /
		JPanel tabbed_panel_left = new JPanel(new GridLayout(1, 1));
		tabbed_panel_left
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		SubjectsTabe tab = new SubjectsTabe();
		JTabbedPane list = new JTabbedPane();
		list.add("Информатика 1", new JScrollPane(tab.getTable()));
		tabbed_panel_left.add(list);
		// /
		left_inner.add(settings_panel);
		left_inner.add(tabbed_panel_left);
		left.add(left_inner);

		JPanel tabbed_panel = new JPanel(new GridLayout(1, 1));
		tabbed_panel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		JPanel tabbedInner_panel = new JPanel(new GridLayout(1, 1));
		tabbedInner_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
				10));
		JTabbedPane tabbed_pane = new JTabbedPane();
		tabbed_pane.add("Информатика 1", new JScrollPane(this.createPanelForTabbed()));
		tabbedInner_panel.add(tabbed_pane);

		tabbed_panel.add(tabbedInner_panel);
		//
		add(left);
		add(tabbed_panel);
	}

	public JPanel createPanelForTabbed() {
		JPanel panel = BoxLayoutUtils.createHorizontalPanel();
		JPanel left = BoxLayoutUtils.createVerticalPanel();
		JPanel right = BoxLayoutUtils.createVerticalPanel();
		LessonTimeTable header_left = new LessonTimeTable(0,true);
		LessonTimeTable header_right = new LessonTimeTable(0,true);
		
		JScrollPane scroll1 = new JScrollPane();
		scroll1.add(header_left.getTable());
		//scroll1.setPreferredSize(new Dimension((int)header_left.getTable().getPreferredSize().getWidth(),30));
		//scroll1.setMaximumSize(scroll1.getPreferredSize());
		//scroll1.setMinimumSize(scroll1.getPreferredSize());
		
		JScrollPane scroll2 = new JScrollPane();
		scroll2.add(header_right.getTable());
		//scroll2.setPreferredSize(new Dimension((int)header_right.getTable().getPreferredSize().getWidth(),30));
		//scroll2.setMaximumSize(scroll2.getPreferredSize());
		//scroll2.setMinimumSize(scroll2.getPreferredSize());
		left.add(scroll1);
		right.add(scroll2);
		for (int day = 0; day < 5; day++) {
			JPanel day_left_panel = BoxLayoutUtils.createVerticalPanel();
			day_left_panel.setBorder(BorderFactory.createLoweredBevelBorder());
			JPanel day_right_panel = BoxLayoutUtils.createVerticalPanel();
			day_right_panel.setBorder(BorderFactory.createLoweredBevelBorder());
			for (int i = 0; i < 6; i++) {
				LessonTimeTable table = new LessonTimeTable(i + 1, false);
				day_left_panel.add(table.getTable());
				left.add(day_left_panel);
			}
			for (int i = 0; i < 6; i++) {
				LessonTimeTable table = new LessonTimeTable(i + 1, false);
				day_right_panel.add(table.getTable());
				right.add(day_right_panel);
			}
		}
		panel.add(left);
		panel.add(right);
		return panel;
	}

	private JLabel course = new JLabel("Курс: ");
	private JLabel specialty = new JLabel("Специальность: ");
	private JComboBox combo_course = new JComboBox();
	private JComboBox combo_specialty = new JComboBox();
}
