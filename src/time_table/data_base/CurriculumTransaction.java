package time_table.data_base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import time_table.objects.Corteges;
import time_table.objects.Subject;

public class CurriculumTransaction {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public CurriculumTransaction() {
		conn = ConnectionSingleton.getInstance();
		try {
			st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getCathedras() {
		ArrayList<String> res = new ArrayList<String>();
		try {
			String s = "select distinct cathedra_name from cathedra";
			rs = st.executeQuery(s);
			while (rs.next())
				res.add(rs.getString("cathedra_name"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}
	public boolean insertCathedrasNames(Object[][] data) {
		try {
			String s = "delete from cathedra;";
			st.execute(s);
			int id = 1;
			for (int i = 0; i < data.length; i++) {
				s = "insert into cathedra values (";
				s += id;
				s += ", '";
				s += data[i][1];
				s += "');";
				System.out.println(s);
				st.execute(s);
				id++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<ArrayList<Object>> loadCurriculumData(List<Corteges> list,
			String specialty) {
		ArrayList<ArrayList<Object>> res = new ArrayList<ArrayList<Object>>();
		try {
			for (Corteges cort : list) {
				List<Subject> sub_list = cort.getSubjects();
				int id_spec = findSpecialty(cort.getSpecialty_name(),
						cort.getSemester());
				for (Subject sub : sub_list) {
					int id = getMaxCurriculumId();
					String s = "insert into curriculum values (";
					s += id;
					s += ",";
					s += id_spec;
					s += ", '";
					s += makeWordForSQL(sub.getName());
					s += "',";
					s += sub.getLect_hours();
					s += ",";
					if (sub.getPract_hours() != -10)
						s += sub.getPract_hours();
					else
						s += 0;
					s += ");";
					System.out.println(s);
					st.execute(s);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		res = this.getCurriculumData(specialty);
		return res;
	}

	private int getMaxCurriculumId() {
		int id_sub = -1;
		try {
			String s = "SELECT max(id_curriculum) as AA FROM curriculum;";
			System.out.println(s);
			id_sub = -1;
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_sub = rs.getInt("AA");
			}
			id_sub++;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return id_sub;
	}

	private int findSpecialty(String name, byte sem) {
		int res = -1;
		try {
			String s = "select id_specialty from specialty where specialty like '";
			s += name;
			s += "' and semester = ";
			s += sem;
			s += ";";
			rs = st.executeQuery(s);
			while (rs.next())
				res = rs.getInt("id_specialty");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return res;
	}

	// загрузка всех данных по одной специальности
	public ArrayList<ArrayList<Object>> getCurriculumData(String specialty_name) {
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		try {
			String s = "select subject_name, lect_hours, pract_hours, specialty, semester ";
			s += "from specialty, curriculum where ref_specialty = id_specialty and ";
			s += "specialty like '";
			s += specialty_name;
			s += "';";
			rs = st.executeQuery(s);
			while (rs.next()) {
				ArrayList<Object> cur = new ArrayList<Object>();
				cur.add(rs.getString("subject_name"));
				cur.add(rs.getInt("lect_hours"));
				cur.add(rs.getInt("pract_hours"));
				cur.add(rs.getString("specialty"));
				cur.add(rs.getInt("semester"));
				result.add(cur);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteCurriculumData(String spec_name) {
		try {
			ArrayList<Integer> id_specialtyName = this
					.getIdSpecialties(spec_name);
			for (Integer id : id_specialtyName) {
				String s = "delete from curriculum where ref_specialty = ";
				s += id;
				s += ";";
				st.execute(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertSpecialiesData(Object[][] data, int course) {
		try {
			String s = "delete from specialty;";
			st.execute(s);
			int id = 1;
			for (int i = 0; i < data.length; i++) {
				int semester = 1;
				for (int y = 0; y < course; y++) {
					s = "insert into specialty values (";
					s += id;
					s += ", '";
					s += data[i][1];
					s += "', ";
					s += semester;
					s += ");";
					System.out.println(s);
					st.execute(s);
					id++;
					s = "insert into specialty values (";
					s += id;
					s += ", '";
					s += data[i][1];
					s += "', ";
					s += semester + 1;
					s += ");";
					System.out.println(s);
					st.execute(s);
					id++;
					semester += 2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertRingsData(Object[][] data) {
		try {
			String s = "delete from rings;";
			st.execute(s);
			int id = 1;
			for (int i = 0; i < data.length; i++) {
				s = "insert into rings values (";
				s += id;
				s += ", " + data[i][0];
				s += ", '";
				s += data[i][1];
				s += "');";
				System.out.println(s);
				st.execute(s);
				id++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<String> getNameSpecialties() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String s = "select distinct specialty from specialty;";
			rs = st.executeQuery(s);
			while (rs.next())
				result.add(rs.getString("specialty"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<String> getRings() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String s = "select * from rings;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next())
				result.add(rs.getString("time"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<Integer> getCourses(String name) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			String s = "select distinct semester from specialty;";
			rs = st.executeQuery(s);
			while (rs.next())
				result.add(new Integer(rs.getString("semester")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	private ArrayList<Integer> getIdSpecialties(String spec_name) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			String s = "select id_specialty from specialty where specialty like '";
			s += spec_name;
			s += "';";
			rs = st.executeQuery(s);
			while (rs.next())
				result.add(rs.getInt("id_specialty"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
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
}
