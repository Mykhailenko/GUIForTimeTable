package time_table.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import time_table.data_base.CurriculumTransaction;
import time_table.executers.Dispatcher;
import time_table.models.StoreEducAssignmentModels;

public class EducAssignmentLoaderPanel   extends JFrame {
	private static final long serialVersionUID = 1L;
	private StoreEducAssignmentModels m;
		
	public EducAssignmentLoaderPanel(StoreEducAssignmentModels m){
		super("Загрузка учебных поручений");
		this.m = m;
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/загрузка меню.png").getImage());
		setIconImages(icons);
		DEFAULT_WIDTH = 700;
		DEFAULT_HEIGHT = 500;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		this.setResizable(false);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) dim_screen.getWidth()/2-DEFAULT_WIDTH/2, (int) dim_screen.getHeight()/2-DEFAULT_HEIGHT/2);
		JPanel content = BoxLayoutUtils.createVerticalPanel();
		
		JPanel combo_out = new JPanel(new GridLayout(1,1));
		combo_out.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JPanel combo = new JPanel(new GridLayout(1,1));
		combo.setBorder(BorderFactory.createLoweredBevelBorder());
		JPanel combo_inner = BoxLayoutUtils.createHorizontalPanel();
		combo_inner.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		//
		JLabel lable = new JLabel("Кафедра:                ");
		JComboBox<String> combo_cathedra = new JComboBox<String>();
		combo_cathedra.setBackground(Color.white);
		CurriculumTransaction tr = new CurriculumTransaction();
		ArrayList<String> spec = tr.getCathedras();
		for(String s: spec)
			combo_cathedra.addItem(s);
		combo_inner.add(lable);
		combo_inner.add(combo_cathedra);
		combo.add(combo_inner);
		combo_out.add(combo);
		
		JPanel file = new JPanel(new GridLayout(1,1));
		file.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JFileChooser file_chooser = new JFileChooser();
		file_chooser.setDialogTitle("Выберите учебный план");
		file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file_chooser.setFileFilter(new ExcelFileFilter());
		file.add(file_chooser);
		//		
		JPanel first_part = BoxLayoutUtils.createVerticalPanel();
		first_part.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel button_panel = BoxLayoutUtils.createHorizontalPanel();
		button_panel.setBorder(BorderFactory.createEmptyBorder(15,5,5,7));
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Отмена");
		ok.setPreferredSize(new Dimension(100,27));
		ok.setMaximumSize(ok.getPreferredSize());
		ok.setMinimumSize(ok.getPreferredSize());
		cancel.setPreferredSize(new Dimension(100,27));
		cancel.setMaximumSize(cancel.getPreferredSize());
		cancel.setMinimumSize(cancel.getPreferredSize());
		button_panel.add(Box.createGlue());
		button_panel.add(ok);
		button_panel.add(Box.createHorizontalStrut(5));
		button_panel.add(cancel);
		
		first_part.add(combo_out);
		first_part.add(file);
		content.add(first_part);
		content.add(button_panel);
		cancel.addActionListener(new ButtonsActionListener(this, ok, cancel, file_chooser,
				combo_cathedra));
		ok.addActionListener(new ButtonsActionListener(this, ok, cancel, file_chooser,combo_cathedra));
		setContentPane(content);
		setVisible(true);
	}
	private class ButtonsActionListener  implements ActionListener{
		private JFrame frame;
		private JButton ok;
		private JButton cancel;
		private JFileChooser file_chooser;
		private JComboBox<String> combo_cathedra;
		public ButtonsActionListener(JFrame f, JButton ok , JButton cancel, 
				JFileChooser file_chooser, JComboBox<String> combo_cathedra){
			frame = f;
			this.ok = ok;
			this.cancel = cancel;
			this.file_chooser = file_chooser;
			this.combo_cathedra = combo_cathedra;
		}
		public void actionPerformed(ActionEvent e){
			String cathedra = combo_cathedra.getSelectedItem().toString();
			if(e.getSource()==cancel){
				frame.dispose();
			}
			if(e.getSource()==ok){
				java.io.File file = file_chooser.getSelectedFile();
				if(file.getName().endsWith("xls")){
					Dispatcher.loadEducAssignment(file, cathedra, m);
				}
				frame.dispose();
			}
		}
	}
	private int DEFAULT_WIDTH;
	private int DEFAULT_HEIGHT;
}
