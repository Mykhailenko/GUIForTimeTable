package time_table.objects;

import java.util.ArrayList;

public class GroupListInfo {
	private ArrayList<Group> list;
	
	public GroupListInfo(){
		list =  new ArrayList<Group>();
	}

	public ArrayList<Group> getList() {
		return list;
	}
	public void setList(ArrayList<Group> list) {
		this.list = list;
	}
	public void addGroup(Group g){
		list.add(g);
	}
	public int getSize(){
		return list.size();
	}
	public void printData(){
		for(Group g: list)
			System.out.println(g.printData());
	}
	public void removeAll(){
		list.removeAll(list);
	}
}
