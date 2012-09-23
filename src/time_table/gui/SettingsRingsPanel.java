package time_table.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import time_table.data_base.CurriculumTransaction;

public class SettingsRingsPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private SettingsRingsTable table;
	private JCheckBox sixDays;

	public SettingsRingsPanel() {
		super("Определение звонков...");
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/звонки.png").getImage());
		setIconImages(icons);
		DEFAULT_WIDTH = 400;
		DEFAULT_HEIGHT = 387;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setResizable(false);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) dim_screen.getWidth() / 2 - 200,
				(int) dim_screen.getHeight() / 2 - 195);

		JPanel content = BoxLayoutUtils.createVerticalPanel();
		JPanel first_content = BoxLayoutUtils.createVerticalPanel();
		first_content.setBorder(BorderFactory.createEtchedBorder());

		JPanel rings = BoxLayoutUtils.createHorizontalPanel();
		rings.setPreferredSize(new Dimension(400, 271));
		rings.setMaximumSize(rings.getPreferredSize());

		JPanel text_panel_first = BoxLayoutUtils.createHorizontalPanel();
		text_panel_first.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JPanel text_label = BoxLayoutUtils.createHorizontalPanel();
		text_label.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel text1_label = BoxLayoutUtils.createHorizontalPanel();
		text1_label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel label = new JLabel(
				"<html>Определите время начала и конца каждого занятия.<p><p>"
						+ "Эти данные будут использоваться при печати расписания.<p><p>"
						+ "Формат ввода произвольный.</html>");
		text1_label.setBackground(Color.white);
		text1_label.add(label);
		text1_label.setPreferredSize(new Dimension(500, 225));
		text1_label.setMaximumSize(text1_label.getPreferredSize());
		text_label.add(text1_label);
		text_panel_first.add(text_label);

		JPanel table_panel_first = BoxLayoutUtils.createHorizontalPanel();
		table_panel_first
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		table = new SettingsRingsTable("Звонки", 150);
		table_panel_first.add(new JScrollPane(table.getTable()));

		rings.add(text_panel_first);
		rings.add(table_panel_first);

		JPanel panel_checks = BoxLayoutUtils.createHorizontalPanel();
		panel_checks.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		// panel_checks.setAlignmentX(panel_checks.LEFT_ALIGNMENT);
		sixDays = new JCheckBox("Задать шесть рабочих дней");
		sixDays.setPreferredSize(new Dimension(500, 40));
		sixDays.setMaximumSize(sixDays.getPreferredSize());
		sixDays.setMinimumSize(sixDays.getPreferredSize());
		panel_checks.add(sixDays);

		JPanel combo_checks = BoxLayoutUtils.createHorizontalPanel();
		combo_checks.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		JLabel semester_label = new JLabel(" Cеместер: ");
		JRadioButton summer_semester = new JRadioButton("летний");
		summer_semester.setActionCommand("summer_semester");
		JRadioButton winter_semester = new JRadioButton("зимний");
		winter_semester.setActionCommand("winter_semester");
		winter_semester.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(summer_semester);
		group.add(winter_semester);
		semester_label.setPreferredSize(new Dimension(160, 20));
		semester_label.setMinimumSize(semester_label.getPreferredSize());
		semester_label.setMaximumSize(semester_label.getPreferredSize());
		winter_semester.setPreferredSize(new Dimension(110, 20));
		winter_semester.setMinimumSize(winter_semester.getPreferredSize());
		winter_semester.setMaximumSize(winter_semester.getPreferredSize());
		summer_semester.setPreferredSize(new Dimension(110, 20));
		summer_semester.setMinimumSize(summer_semester.getPreferredSize());
		summer_semester.setMaximumSize(summer_semester.getPreferredSize());
		combo_checks.add(semester_label);
		combo_checks.add(winter_semester);
		combo_checks.add(summer_semester);

		JPanel button_panel = BoxLayoutUtils.createHorizontalPanel();
		button_panel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 7));
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Отмена");
		ok.setPreferredSize(new Dimension(100, 27));
		ok.setMaximumSize(ok.getPreferredSize());
		ok.setMinimumSize(ok.getPreferredSize());
		cancel.setPreferredSize(new Dimension(100, 27));
		cancel.setMaximumSize(cancel.getPreferredSize());
		cancel.setMinimumSize(cancel.getPreferredSize());
		button_panel.add(Box.createGlue());
		button_panel.add(ok);
		button_panel.add(Box.createHorizontalStrut(5));
		button_panel.add(cancel);

		ok.addActionListener(new CloseActionListener(ok, cancel, this));
		cancel.addActionListener(new CloseActionListener(ok, cancel, this));

		first_content.add(rings);
		first_content.add(panel_checks);
		first_content.add(combo_checks);
		content.add(first_content);
		content.add(button_panel);
		setContentPane(content);
		setVisible(true);
	}

	private class CloseActionListener implements ActionListener {
		private JFrame frame;
		private JButton ok;

		public CloseActionListener(JButton ok, JButton cancel, JFrame f) {
			frame = f;
			this.ok = ok;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ok) {
				CurriculumTransaction t = new CurriculumTransaction();
				t.insertRingsData(table.getModel().getData());
				frame.dispose();
			} else
				frame.dispose();
		}
	}

	private int DEFAULT_WIDTH;
	private int DEFAULT_HEIGHT;
}
