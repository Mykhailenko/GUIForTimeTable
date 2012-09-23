package time_table.executers;

import java.io.File;

import time_table.data_base.EdAssigmentTransaction;
import time_table.gateways.EducAssignExcelGateway;
import time_table.models.EducAssingmentModel;
import time_table.models.StoreEducAssignmentModels;
import time_table.objects.StoreEdAssign;

public class EducAssignLoader {
	private static EdAssigmentTransaction trans = new EdAssigmentTransaction();

	public static void loadEducAssignment(File file, String cathedra,
			StoreEducAssignmentModels m) {
		StoreEdAssign assign = EducAssignExcelGateway.firstLoadEducAssignment(file);
		if(trans.saveEdAssignData(assign,cathedra))
			m.addModel(new EducAssingmentModel(assign,cathedra), cathedra);
	}

	public static void deleteAssignmentData(String cathedra,
			StoreEducAssignmentModels store) {
		trans.removeEdAssignData(cathedra);
		store.removeModel(cathedra);
	}
}
