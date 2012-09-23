package time_table.executers;


import time_table.data_base.GroupsTransaction;
import time_table.objects.GroupListInfo;

public class GroupsInfoLoader {
	private static GroupsTransaction trans = new GroupsTransaction();
	
	public static void setGroupsInfo(GroupListInfo groupsData) {
		trans.insertGroupsInfoData(groupsData);
	}
	public static void removeGroupsInfo(GroupListInfo groupsData){
		trans.deleteFromGroup();
		groupsData.removeAll();
	}
}
