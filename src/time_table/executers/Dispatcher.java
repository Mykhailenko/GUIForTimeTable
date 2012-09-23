package time_table.executers;

import java.io.File;
import java.util.ArrayList;

import time_table.data_base.CurriculumTransaction;
import time_table.gateways.ExcelGateway;
import time_table.models.StoreCurriculumModels;
import time_table.models.StoreEducAssignmentModels;
import time_table.objects.ComputerTimeTable;
import time_table.objects.GroupListInfo;

public class Dispatcher {
	private static CurriculumTransaction trans = new CurriculumTransaction();
	
	public static void loadCurriculum(File file, String specialty, StoreCurriculumModels m){
		ArrayList<ArrayList<Object>> res = ExcelGateway.firstLoadCurriculum(file, specialty);
		m.addModel(res, specialty);
	}
	public static boolean safetyDevice(){
		ArrayList<String> spec = trans.getNameSpecialties();
		if(spec.size()==0)
			return false;
		return true;
	}
	public static boolean safetyDeviceEducAssignment() {
		ArrayList<String> cathedras = trans.getCathedras();
		if(cathedras.size()!=0)
			return true;
		return false;
	}
	public static void deleteCurriculumData(String specialty, StoreCurriculumModels m){
		trans.deleteCurriculumData(specialty);
		m.removeModel(specialty);
	}
	public static void setGroupsInfo(GroupListInfo groupsData) {
		GroupsInfoLoader.setGroupsInfo(groupsData);
	}
	public static void removeGroupsInfo(GroupListInfo groupsData){
		GroupsInfoLoader.removeGroupsInfo(groupsData);
	}
	public static void loadEducAssignment(File file, String cathedra,
			StoreEducAssignmentModels m) {
		EducAssignLoader.loadEducAssignment(file, cathedra, m);		
	}
	public static void deleteAssignmentData(String cathedra,
			StoreEducAssignmentModels store) {
		EducAssignLoader.deleteAssignmentData(cathedra, store);
	}
	public static void loadComputerTimeTable(ComputerTimeTable tt){
		ComputerTTLoader.loadComputerTimeTable(tt);
	}
	public static void removeComputerTimeTableInfo(ComputerTimeTable compTTData) {
		ComputerTTLoader.removeComputerTimeTableInfo(compTTData);
	}
	
}
