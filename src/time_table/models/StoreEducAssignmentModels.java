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

import time_table.data_base.EdAssigmentTransaction;
import time_table.gui.CurriculumHeaderRenderer;
import time_table.objects.StoreEdAssign;

public class StoreEducAssignmentModels {

	private CopyOnWriteArrayList<EducAssingmentModel> models;

	public StoreEducAssignmentModels(JTabbedPane tabbed) {
		models = new CopyOnWriteArrayList<EducAssingmentModel>();
		this.tabbed = tabbed;
	}

	public StoreEducAssignmentModels() {
		models = new CopyOnWriteArrayList<EducAssingmentModel>();
	}

	public JTabbedPane getTabbed() {
		return tabbed;
	}

	public void setTabbed(JTabbedPane tabbed) {
		this.tabbed = tabbed;
	}

	public void initData() {
		EdAssigmentTransaction trans = new EdAssigmentTransaction();
		ArrayList<String> list = trans.getNameCathedras();
		for (String cath : list) {
			StoreEdAssign model = trans.getEdAssignData(cath);
			if (model != null) {
				if (model.getSize() != 0) {
					EducAssingmentModel m = new EducAssingmentModel(model, cath);
					models.add(m);
					addTabbedPane(m);
				}
			}
		}
	}

	public ArrayList<String> getListCathedras() {
		ArrayList<String> result = new ArrayList<String>();
		for (EducAssingmentModel model : models)
			result.add(model.getCathedra_name());
		return result;
	}

	public void addModel(EducAssingmentModel model, String cath_name) {
		if (!isModelExist(cath_name)) {
			models.add(model);
			addTabbedPane(model);
		}
	}

	public void removeModel(String cath_name) {
		int i = 0;
		for (EducAssingmentModel mod : models) {
			if (mod.getCathedra_name().compareTo(cath_name) == 0)
				models.remove(i);
			i++;
		}
		int tab_count = this.tabbed.getTabCount();
		System.out.println(tab_count);
		for (int j = 0; j < tab_count; j++) {
			System.out.println(tabbed.getTitleAt(j));
			if (tabbed.getTitleAt(j).compareTo(cath_name) == 0) {
				tabbed.remove(j);
				break;
			}
		}
	}

	public int getModelsCount() {
		return models.size();
	}

	private boolean isModelExist(String cath_name) {
		for (EducAssingmentModel m : models)
			if (m.getCathedra_name().compareTo(cath_name) == 0)
				return true;
		return false;
	}

	private void addTabbedPane(EducAssingmentModel m) {
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
				column.setCellRenderer(table.getTableHeader()
						.getDefaultRenderer());
				break;
			}
			j++;
		}
		this.tabbed.addTab(m.getCathedra_name(), new JScrollPane(table));
	}

	private JTabbedPane tabbed;
}
