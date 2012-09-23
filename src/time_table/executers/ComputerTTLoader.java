package time_table.executers;

import time_table.data_base.ComputerTTTracsaction;
import time_table.objects.ComputerTimeTable;

public class ComputerTTLoader {
	private static ComputerTTTracsaction trans = new ComputerTTTracsaction();

	public static void loadComputerTimeTable(ComputerTimeTable tt) {
		trans.insertComputerTimeTableInfo(tt);
	}

	public static void removeComputerTimeTableInfo(ComputerTimeTable compTTData) {
		trans.deleteFromComputerTimeTable();
		compTTData.removeAllInfo();
	}
}
