package time_table.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import time_table.executers.Dispatcher;

public class ComputerTTWorkPanel  extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private ComputerTTListPanel computerListPanel;
	
	public  ComputerTTWorkPanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel button_panel1 = new JPanel(new GridLayout(1,1));
		button_panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		button_panel1.setPreferredSize(new Dimension(1400, 100));
		button_panel1.setMaximumSize(button_panel1.getPreferredSize());
		JPanel button_panel2 = new JPanel(new GridLayout(1,1));
		button_panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JPanel button_panel = BoxLayoutUtils.createHorizontalPanel();
		button_panel.setBorder(BorderFactory.createLineBorder(Color.white));
		save = new JButton("<html>C��������<p>����������<p>��</html>", new ImageIcon("images/���� ������������.png"));
		delete = new JButton("<html>��������<p>�����<p>����������</html>", new ImageIcon("images/�������� ����������.png"));
		save.setMargin(new Insets(5,5,5,5));
		save.setVerticalAlignment(SwingConstants.TOP);
		save.setVerticalTextPosition(SwingConstants.BOTTOM);
		save.setIconTextGap(10);
		save.setBorderPainted(false);
		save.setPreferredSize(new Dimension(150,100));
		save.setMinimumSize(save.getPreferredSize());
		save.setMaximumSize(save.getPreferredSize());
		delete.setMargin(new Insets(15,15,15,15));
		delete.setMargin(new Insets(5,5,5,5));
		delete.setVerticalAlignment(SwingConstants.TOP);
		delete.setVerticalTextPosition(SwingConstants.BOTTOM);
		delete.setIconTextGap(10);
		delete.setBorderPainted(false);
		delete.setPreferredSize(new Dimension(150,100));
		delete.setMinimumSize(delete.getPreferredSize());
		delete.setMaximumSize(delete.getPreferredSize());
		button_panel.add(save);
		button_panel.add(delete);
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
		button_panel.add(new JLabel(""));
	
		button_panel2.add(button_panel);
		button_panel1.add(button_panel2);
		
		tabbed = new JTabbedPane();
		computerListPanel = new ComputerTTListPanel();
		tabbed.add("���������� ������������ �������", computerListPanel);
		
		JPanel tabbed_panel = new JPanel(new GridLayout(1,1));
		tabbed_panel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		tabbed_panel.add(tabbed);
		
		save.addActionListener(this);
		delete.addActionListener(this);
		
		add(button_panel1);
		add(tabbed_panel);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==save){
			Dispatcher.loadComputerTimeTable(computerListPanel.getModel().getCompTTData());
		}else{
			Dispatcher.removeComputerTimeTableInfo(computerListPanel.getModel().getCompTTData());
			computerListPanel.getModel().removeData();
			computerListPanel.repaint();
		}
	}
	
	private JButton save;
	private JButton delete;
	private JTabbedPane tabbed;
}
