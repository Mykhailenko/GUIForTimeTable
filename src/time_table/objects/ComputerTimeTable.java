package time_table.objects;

import java.util.ArrayList;

public class ComputerTimeTable {
	private ArrayList<DayComputerClass> list;
	
	public ComputerTimeTable(){
		list = new ArrayList<DayComputerClass>();
	}

	public ArrayList<DayComputerClass> getList() {
		return list;
	}

	public void setList(ArrayList<DayComputerClass> list) {
		this.list = list;
	}
	
	public void addDayCCInfo(DayComputerClass day){
		list.add(day);
	}
	public void removeAllInfo(){
		list.removeAll(list);
	}
}
