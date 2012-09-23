package time_table.data_base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import time_table.objects.ComputerTimeTable;
import time_table.objects.DayComputerClass;

public class ComputerTTTracsaction {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public ComputerTTTracsaction() {
		conn = ConnectionSingleton.getInstance();
		try {
			st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ComputerTimeTable getComputerTimeTable() {
		ComputerTimeTable result = new ComputerTimeTable();
		try {
			String s = "select * from computertimetable";
			rs = st.executeQuery(s);
			while (rs.next()) {
				DayComputerClass day = new DayComputerClass();
				day.setAuditory(rs.getString("auditory"));
				day.setDayNumber(rs.getInt("day_number"));
				day.setNumberLess(rs.getInt("lesson_number"));
				result.addDayCCInfo(day);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public void insertComputerTimeTableInfo(ComputerTimeTable tt) {
		ArrayList<DayComputerClass> list = tt.getList();
		try {
			String s = "delete from computertimeTable;";
			st.execute(s);
			for (DayComputerClass cur : list) {
				int cur_id = getComputerTTID();
				s = "insert into computerTimeTable values("+cur_id+",'";
				s += cur.getDayName()+"', "+cur.getNumberLess()+", '"+cur.getAuditory();
				s += "', "+cur.getDayNumber()+");";
				System.out.println(s);
				st.execute(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getComputerTTID() {
		int id = -1;
		try {
			String s = "select max(id_computerTT) as AA FROM computerTimeTable;";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next()) {
				id = rs.getInt("AA");
			}
			id++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public void deleteFromComputerTimeTable() {
		try{
			String s = "delete from computertimeTable;";
			System.out.println(s);
			st.execute(s);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
