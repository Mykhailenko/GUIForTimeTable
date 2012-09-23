package time_table.objects;

public class CortEdAssign {
	private String subject_name;
	private String specialty;
	private String semester;
	private String type;
	private String teacher_name;
	private String group;
	private String request;
	
	public CortEdAssign(String subject_name, String specialty,String semester, String type,
			String teacher_name, String group, String request){		
	}
	public CortEdAssign(){
		subject_name = new String("");
		specialty  = new String("");
		semester  = new String("");
		type = new String("");
		teacher_name = new String("");
		group = new String("");
		request = new String("");
	}	
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = new Integer(semester).toString();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getRequest() {
		if(request.compareTo(NO_REQUEST_CC)==0)
			return 0;
		return 1;
	}
	public void setRequest(int request) {
		if(request==0)
			this.request = NO_REQUEST_CC;
		else
			this.request = YES_REQUEST_CC;
	}
	public String printData(){
		return this.subject_name+" "+this.specialty+" "+this.semester+" "+this.type+" "+this.teacher_name+" "+this.group+" "+this.request;
	}
	private final String YES_REQUEST_CC = "YES";
	private final String NO_REQUEST_CC = "NO";
}
