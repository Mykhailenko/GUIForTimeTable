package time_table.gui;

import javax.swing.filechooser.FileFilter;

public class ExcelFileFilter extends FileFilter{
	
	public boolean accept(java.io.File file){
		if(file.isDirectory()) return true;
		return (file.getName().endsWith("xls"));
	}
	public String getDescription(){
		return "����� Excel (*.xls)";
	}
}

