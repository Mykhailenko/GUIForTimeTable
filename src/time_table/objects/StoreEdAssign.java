package time_table.objects;

import java.util.ArrayList;

public class StoreEdAssign {
	private ArrayList<CortEdAssign> assignment;
	private String cath_name;
	
	public StoreEdAssign(){
		assignment = new ArrayList<CortEdAssign>();
	}

	public String getCath_name() {
		return cath_name;
	}
	public void setCath_name(String cath_name) {
		this.cath_name = cath_name;
	}
	public ArrayList<CortEdAssign> getAssignment() {
		return assignment;
	}
	public void setAssignment(ArrayList<CortEdAssign> assignment) {
		this.assignment = assignment;
	}
	public int getSize(){
		return assignment.size();
	}
	public CortEdAssign getDataAtIndex(int i){
		return assignment.get(i);
	}
	public void addCortEdAssign(CortEdAssign as){
		assignment.add(as);
	}
}
