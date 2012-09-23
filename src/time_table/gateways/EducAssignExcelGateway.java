package time_table.gateways;

import java.io.File;

import time_table.objects.CortEdAssign;
import time_table.objects.StoreEdAssign;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class EducAssignExcelGateway {
	public static StoreEdAssign firstLoadEducAssignment(File file){
		StoreEdAssign assig = new StoreEdAssign();
		File inputWorkbook = file;
		Workbook w;
		try{
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			Cell cell = sheet.getCell(ID, BOTTOM);
			while(isDigit(cell.getContents())){
				cell = sheet.getCell(ID, ++BOTTOM);
			}
			for(int i=TOP; i< BOTTOM; i++){
				CortEdAssign cur = new CortEdAssign();
				cur.setSubject_name(sheet.getCell(NAME_SUBJECT,i).getContents().toString());
				cur.setSpecialty(sheet.getCell(SPECIALTY,i).getContents().toString());
				cur.setSemester(Integer.parseInt(sheet.getCell(SEMESTER, i).getContents().toString()));
				cur.setType(sheet.getCell(TYPE,i).getContents().toString());
				cur.setTeacher_name(sheet.getCell(TEACHER, i).getContents().toString());
				cur.setGroup(sheet.getCell(GROUP,i).getContents().toString());
				String request = sheet.getCell(REQUEST,i).getContents().toString();
				if(request.compareTo("")!=0)
					cur.setRequest(1);
				else
					cur.setRequest(0);
				assig.addCortEdAssign(cur);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		BOTTOM =3;
		return assig;
	}
	public static boolean isDigit(String str){
		if(str.charAt(0)=='1' || str.charAt(0)=='2' ||
				str.charAt(0)=='3' || str.charAt(0)=='4' ||
				str.charAt(0)=='5' || str.charAt(0)=='6' ||
				str.charAt(0)=='7' || str.charAt(0)=='8' ||
				str.charAt(0)=='9')
			return true;
		return false;
	}
	private static int TOP=3;
	private static int ID=1;
	private static int NAME_SUBJECT = 2;
	private static int SPECIALTY = 3;
	private static int SEMESTER = 4;
	private static int TYPE = 5;
	private static int TEACHER = 6;
	private static int GROUP = 7;
	private static int REQUEST = 8;
	private static int BOTTOM=3;
}
