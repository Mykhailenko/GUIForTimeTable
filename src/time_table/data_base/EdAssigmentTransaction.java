package time_table.data_base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import time_table.objects.CortEdAssign;
import time_table.objects.StoreEdAssign;

public class EdAssigmentTransaction {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public EdAssigmentTransaction() {
		conn = ConnectionSingleton.getInstance();
		try {
			st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getNameCathedras() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String s = "select distinct cathedra_name from cathedra;";
			rs = st.executeQuery(s);
			while (rs.next())
				result.add(rs.getString("cathedra_name"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public StoreEdAssign getEdAssignData(String cath) {
		StoreEdAssign assign = new StoreEdAssign();
		assign.setCath_name(cath);
		try {
			String s = "select specialty,semester, subject_name, teacher_name from educationalassignment, ";
			s += "lecture, specialty where ref_specialty=id_specialty and cathedra_name like '";
			s += cath + "' ";
			s += "and ref_educAssignment=id_educAssignment;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				CortEdAssign cortege = new CortEdAssign();
				cortege.setSpecialty(rs.getString("specialty"));
				cortege.setSemester(rs.getInt("semester"));
				cortege.setSubject_name(rs.getString("subject_name"));
				cortege.setTeacher_name(rs.getString("teacher_name"));
				assign.addCortEdAssign(cortege);
			}
			System.out.println(assign.getAssignment().size());
			ArrayList<CortEdAssign> list_lecture = assign.getAssignment();
			for (CortEdAssign cur : list_lecture) {
				if (cur.getType().compareTo(LECTURE) != 0)
					cur.setType(LECTURE);
			}

			s = "select specialty,semester, subject_name, teacher_name, computer_class, groups from educationalassignment, ";
			s += "practice, specialty where ref_specialty=id_specialty and cathedra_name like '";
			s += cath + "' ";
			s += "and  ref_educAssignment=id_educAssignment;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				CortEdAssign cortege = new CortEdAssign();
				cortege.setSpecialty(rs.getString("specialty"));
				cortege.setSemester(rs.getInt("semester"));
				cortege.setSubject_name(rs.getString("subject_name"));
				cortege.setGroup(rs.getString("groups"));
				cortege.setTeacher_name(rs.getString("teacher_name"));
				cortege.setRequest(rs.getInt("computer_class"));
				assign.addCortEdAssign(cortege);
			}
			System.out.println(assign.getAssignment().size());
			ArrayList<CortEdAssign> list_practice = assign.getAssignment();
			for (CortEdAssign cur : list_practice) {
				if (cur.getType().compareTo(PRACTICE) != 0)
					cur.setType(PRACTICE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return assign;
	}

	public boolean saveEdAssignData(StoreEdAssign assign, String cathedra) {
		try {
			for (CortEdAssign cur : assign.getAssignment()) {
				if (cur.getType().compareTo(PRACTICE) == 0) {
					int ref_spec = findIDSpecialty(cur.getSpecialty(),
							Integer.parseInt(cur.getSemester()));
					if (ref_spec == -1) {
						JOptionPane
								.showMessageDialog(
										null,
										"<html>Названия заданных специальностей не совпадают<p>"
												+ "с именами специальностей в учебных поручениях."
												+ "<p>Загрузка учебных поручений невозможна. Измените названия специальностей ('Установки -> Специальности').</html>");
						return false;
					}
					System.out.println(ref_spec);
					int id_ed = findIDEdAssignment(cur.getSubject_name(),
							ref_spec, cathedra);
					if (id_ed == -1) {
						id_ed = this.getIDEdAs();
						String s = "insert into educationalAssignment values(";
						s += id_ed + "," + ref_spec + ",'"
								+ this.makeWordForSQL(cur.getSubject_name())
								+ "','" + this.makeWordForSQL(cathedra) + "');";
						System.out.println(s);
						st.execute(s);
					}
					int id_prac = getIDPractice();
					String s_pr = "insert into practice values(";
					s_pr += id_prac + ", '"
							+ this.makeWordForSQL(cur.getTeacher_name()) + "',"
							+ cur.getRequest() + "," + id_ed + ",null,'"
							+ this.makeWordForSQL(cur.getGroup()) + "');";
					System.out.println(s_pr);
					st.execute(s_pr);
				} else {
					int ref_spec = findIDSpecialty(cur.getSpecialty(),
							Integer.parseInt(cur.getSemester()));
					if (ref_spec == -1) {
						JOptionPane
								.showMessageDialog(
										null,
										"<html>Названия заданных специальностей не совпадают<p>"
												+ "с именами специальностей в учебных поручениях."
												+ "<p>Загрузка учебных поручений невозможна. Измените названия специальностей ('Установки -> Специальности').</html>");
						return false;
					}
					System.out.println(ref_spec);
					int id_ed = findIDEdAssignment(cur.getSubject_name(),
							ref_spec, cathedra);
					if (id_ed == -1) {
						id_ed = this.getIDEdAs();
						String s = "insert into educationalAssignment values(";
						s += id_ed + "," + ref_spec + ",'"
								+ this.makeWordForSQL(cur.getSubject_name())
								+ "','" + this.makeWordForSQL(cathedra) + "');";
						System.out.println(s);
						st.execute(s);
					}
					int id_lect = getIDLecture();
					String s_lec = "insert into lecture values(";
					s_lec += id_lect + ", '"
							+ this.makeWordForSQL(cur.getTeacher_name()) + "',"
							+ id_ed + ");";
					System.out.println(s_lec);
					st.execute(s_lec);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private int findIDEdAssignment(String subject, int ref_spec, String cathedra) {
		int id_spec = -1;
		try {
			String s = "select id_educAssignment from educationalAssignment where subject_name like '";
			s += this.makeWordForSQL(subject) + "' and ref_specialty = "
					+ ref_spec + " and cathedra_name like '"
					+ this.makeWordForSQL(cathedra) + "';";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_spec = rs.getInt("id_educAssignment");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id_spec;
	}

	private int findIDSpecialty(String specialty, int semester) {
		int id_spec = -1;
		try {
			String s = "select id_specialty from specialty where specialty like '";
			s += this.makeWordForSQL(specialty) + "' and semester = "
					+ semester + ";";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_spec = rs.getInt("id_specialty");
			}
			return id_spec;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private int getIDPractice() {
		int id_pr = -1;
		try {
			String s = "SELECT max(id_practice) as AA FROM practice;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_pr = rs.getInt("AA");
			}
			id_pr++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id_pr;
	}

	private int getIDLecture() {
		int id_lt = -1;
		try {
			String s = "SELECT max(id_lecture) as AA FROM lecture;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_lt = rs.getInt("AA");
			}
			id_lt++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id_lt;
	}

	private int getIDEdAs() {
		int id_eda = -1;
		try {
			String s = "SELECT max(id_educAssignment) as AA FROM educationalassignment;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_eda = rs.getInt("AA");
			}
			id_eda++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id_eda;
	}

	private String makeWordForSQL(String word) {
		StringBuffer bur = new StringBuffer();
		int i;
		for (i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '\'') {
				bur.append(word.substring(0, i + 1));
				bur.append('\'');
				break;
			}
		}
		if (i != word.length()) {
			i++;
			bur.append(word.substring(i, word.length()));
			return bur.toString();
		}
		return word;
	}

	private ArrayList<Integer> getIDsEdAssCathedra(String cathedra) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			String s = "select id_educAssignment from educationalassignment where cathedra_name like '";
			s += cathedra;
			s += "';";
			rs = st.executeQuery(s);
			while (rs.next())
				ids.add(new Integer(rs.getInt("id_educAssignment")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ids;
	}

	private final String PRACTICE = "Практика";
	private final String LECTURE = "Лекція";

	public void removeEdAssignData(String cathedra) {
		String s = new String();
		try {
			// 1. find all ed_ass which has cathedra like cathedra;
			ArrayList<Integer> ids = getIDsEdAssCathedra(cathedra);
			// 2. remove all practices and lectures
			for (Integer cur_id : ids) {
				s = "delete from practice where ref_educAssignment = "+cur_id+";";
				st.execute(s);
				s = "delete from lecture where ref_educAssignment = "+cur_id+";";
				st.execute(s);
			}
			// 3. remove all ed_ass
			for (Integer cur_id : ids) {
				s = "delete from educationalassignment where id_educAssignment = "+cur_id+";";
				st.execute(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
