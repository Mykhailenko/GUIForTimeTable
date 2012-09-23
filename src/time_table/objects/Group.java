package time_table.objects;

public class Group {
	private String specialty;
	private int course;
	private String abbr;
	private int count;
	
	public int getCourse() {
		return course;
	}
	public void setCourse(int semester) {
		this.course = semester;
	}
	public Group(String specialty, String abbr, int count){
		this.specialty = specialty;
		this.abbr = abbr;
		this.count = count;
	}
	public Group(){
		this.specialty = new String("");
		this.abbr = new String ("");
		this.count = 0;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String printData(){
		return specialty+" "+course+" "+abbr+" "+count;
	}
}