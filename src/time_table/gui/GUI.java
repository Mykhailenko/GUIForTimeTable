package time_table.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import time_table.executers.Dispatcher;
import time_table.models.StoreCurriculumModels;
import time_table.models.StoreEducAssignmentModels;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private StoreCurriculumModels m;
	private StoreEducAssignmentModels m_educ;

	public GUI(){
		super("����������");
		m = new StoreCurriculumModels(new JTabbedPane());
		m_educ = new StoreEducAssignmentModels(new JTabbedPane());
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/����.png").getImage());
		setIconImages(icons);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		DEFAULT_WIDTH = (int)dim_screen.getWidth();
		DEFAULT_HEIGHT = (int)dim_screen.getHeight();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT-50);
		String s = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file_menu = new JMenu("����");
		JMenu settings_menu = new JMenu("���������");
		menuBar.add(file_menu);		
		menuBar.add(settings_menu);
		JMenuItem load_tt = new JMenuItem("��������� ����������", new ImageIcon("images/�������� ����.png")); 
		file_menu.add(load_tt);
		load_tt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dispatcher.safetyDevice() == true)
					new CurriculumLoaderPanel(m);
				else
					JOptionPane
							.showMessageDialog(null,
									"���������� ��������� ����������. (���� '���������')");
			}
		});
		load_tt.setPreferredSize(new Dimension(250,30));
		load_tt.setMinimumSize(load_tt.getPreferredSize());
		load_tt.setMaximumSize(load_tt.getPreferredSize());
		//load_tt.addActionListener();
		JMenuItem save_tt = new JMenuItem("��������� ����������",new ImageIcon("images/�����.png")); 
		file_menu.add(save_tt);
		save_tt.setPreferredSize(new Dimension(250,30));
		save_tt.setMinimumSize(load_tt.getPreferredSize());
		save_tt.setMaximumSize(load_tt.getPreferredSize());
		//save_tt.addActionListener();
		JMenuItem delete_tt = new JMenuItem("������� ����������",new ImageIcon("images/�������� ����.png")); 
		file_menu.add(delete_tt);
		delete_tt.setPreferredSize(new Dimension(250,30));
		delete_tt.setMinimumSize(load_tt.getPreferredSize());
		delete_tt.setMaximumSize(load_tt.getPreferredSize());
		//delete_tt.addActionListener();
		file_menu.addSeparator();
		JMenuItem compl_tt = new JMenuItem("�������� ������� ����������",new ImageIcon("images/�������.png")); 
		file_menu.add(compl_tt);
		compl_tt.setPreferredSize(new Dimension(250,30));
		compl_tt.setMinimumSize(load_tt.getPreferredSize());
		compl_tt.setMaximumSize(load_tt.getPreferredSize());
		//compl_tt.addActionListener();
		file_menu.addSeparator();
		JMenuItem print_version = new JMenuItem("�������� ������",new ImageIcon("images/������.png")); 
		file_menu.add(print_version);
		print_version.setPreferredSize(new Dimension(250,30));
		print_version.setMinimumSize(load_tt.getPreferredSize());
		print_version.setMaximumSize(load_tt.getPreferredSize());
		//print_version.addActionListener();
		file_menu.addSeparator();
		JMenuItem exit = new JMenuItem("�����",new ImageIcon("images/�����.png")); 
		file_menu.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		exit.setPreferredSize(new Dimension(250,30));
		exit.setMinimumSize(load_tt.getPreferredSize());
		exit.setMaximumSize(load_tt.getPreferredSize());
		load_tt.setPreferredSize(new Dimension(250,30));
		JMenuItem rings = new JMenuItem("����������� �������...",new ImageIcon("images/������ ����.png")); 
		settings_menu.add(rings);
		rings.setPreferredSize(new Dimension(250,30));
		rings.setMinimumSize(load_tt.getPreferredSize());
		rings.setMaximumSize(load_tt.getPreferredSize());
		rings.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SettingsRingsPanel();
			}
		});
		JMenuItem specialty = new JMenuItem("����������� ��������������...",new ImageIcon("images/������������� ����.png")); 
		settings_menu.add(specialty);
		specialty.setPreferredSize(new Dimension(250,30));
		specialty.setMinimumSize(load_tt.getPreferredSize());
		specialty.setMaximumSize(load_tt.getPreferredSize());
		specialty.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(m.getModelsCount()==0)
					new SettingsSpecialtyPanel();
				else
					JOptionPane.showMessageDialog(null, "��� ���� ����� ������ ��������� � ������������ ����������"
											+ " �������������� ������� ��� ����������� ������� �����.");
			}
		});
		JMenuItem cathedra = new JMenuItem("����������� ������ ������...",
				new ImageIcon("images/������� ����.png"));
		settings_menu.add(cathedra);
		cathedra.setPreferredSize(new Dimension(250, 30));
		cathedra.setMinimumSize(cathedra.getPreferredSize());
		cathedra.setMaximumSize(cathedra.getPreferredSize());
		cathedra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m_educ.getModelsCount()==0)
					new SettingsCathedraPanel();
				else
					JOptionPane.showMessageDialog(null, "��� ���� ����� ������ ��������� � ������������ ����������"
											+ " �������������� ������� ��� ����������� ������� ���������.");
			}
		});
		// insert panels... 
		tabbed = new JTabbedPane();
		tabbed.add("������� �����", new CurriculumWorkPanel(m));
		tabbed.add("������� ���������", new EducAssigmentWorkPanel(m_educ));
		tabbed.add("������", new GroupWorkPanel());
		tabbed.add("���������� ������������ �������", new ComputerTTWorkPanel());
		tabbed.add("����������", new TimeTableWorkPanel());
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(1,1));
		main.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		main.add(tabbed);
		
		this.getContentPane().add(main);
		setVisible(true);
	}
	private int DEFAULT_WIDTH;
	private int DEFAULT_HEIGHT;
	private JTabbedPane tabbed;
	
	public static void main(String[] args){
		new GUI();
	}
}
