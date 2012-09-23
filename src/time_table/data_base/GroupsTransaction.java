package time_table.data_base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import time_table.objects.Group;
import time_table.objects.GroupListInfo;

public class GroupsTransaction {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public GroupsTransaction() {
		conn = ConnectionSingleton.getInstance();
		try {
			st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getSpecialies(){
		ArrayList<String> result = new ArrayList<String>();
		try{
			String s = "select distinct specialty from specialty;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while(rs.next())
				result.add(rs.getString("specialty"));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}
	public int getCourse(){
		int result = 0;
		try{
			String s ="select max(semester) as AA FROM specialty;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				result = rs.getInt("AA");
			}
			result = result/2;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean deleteFromGroup(){
		try{
			String sql = "delete from groupone;";
			st.execute(sql);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertGroupsInfoData(GroupListInfo list){
		ArrayList<Group> groups = list.getList();
		try{
			deleteFromGroup();
			int id = 1;
			for(Group cur: groups){
				int id_spec = getIdSpecialty(cur.getSpecialty(), cur.getCourse());
				int count = cur.getCount();
				for(int i = 0; i<count; i++){
					String group_name = makeGroupName(cur.getAbbr(), cur.getCourse(), i);
					String s = "insert into groupone values (" +
							id+","+id_spec+",'"+group_name+"');";
					
					System.out.println(s);
					st.execute(s);
					id++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private int getIdSpecialty(String spec, int semester){
		int id = -1;
		try{
			String s = "select id_specialty from specialty where specialty like '";
			s += spec+"' and semester = "+semester+";";
			System.out.println(s);
			rs = st.executeQuery(s);
			while(rs.next())
				id = rs.getInt("id_specialty");
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	private String makeGroupName(String abbr, int course, int i) {
		return abbr+"***"+new Integer(course).toString()+new Integer(i+1).toString();
	}
	private ArrayList<Integer> getGroupsId(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		try{
			String s ="select distinct ref_specialty from groupone;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while(rs.next())
				result.add(new Integer(rs.getInt("ref_specialty")));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}
	private void getSpecialtyInfoByID(Group g, int id){
		try{
			String specialty = new String();
			int semester=-10;
			String s ="select specialty, semester from specialty where id_specialty = ";
			s += id +";";
			System.out.println(s);
			rs = st.executeQuery(s);
			while(rs.next()){
				specialty = rs.getString("specialty");
				semester = rs.getInt("semester");
			}
			g.setSpecialty(specialty);
			g.setCourse(semester);
			System.out.println(specialty +" "+semester);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private String getAbbriv(String str){
		for(int i=0; i<str.length(); i++)
			if(str.charAt(i)=='*'){
				System.out.println("Аббривиатура"+str.substring(0,i));
				return str.substring(0,i);
			}
		return null;
	}
	private int getNumber(String str){
		for(int i=0; i<str.length(); i++)
			if(str.charAt(i)=='*'){
				System.out.println("ЧИсло"+str.substring(i+4, str.length()));
				return Integer.parseInt(str.substring(i+4, str.length()));
			}
		return -10;
	}

	public GroupListInfo getGroupsData() {
		GroupListInfo groupInfo = new GroupListInfo();
		ArrayList<Integer> ref_spec = getGroupsId();
		try{
			for(Integer integ: ref_spec){
				ArrayList<String> groups_name = new ArrayList<String>();
				String s = "select name_group from groupone where ref_specialty = ";
				s += integ+";";
				System.out.println(s);
				rs = st.executeQuery(s);
				while(rs.next())
					groups_name.add(rs.getString("name_group"));
				Group g = new Group();
				getSpecialtyInfoByID(g, integ.intValue());
				g.setAbbr(getAbbriv(groups_name.get(0)));
				int max = getNumber(groups_name.get(0));
				for(String cur:groups_name)
					if(max<getNumber(cur))
						max = getNumber(cur);
				g.setCount(max);
				groupInfo.addGroup(g);
			}
			groupInfo.printData();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return groupInfo;
	}
}
