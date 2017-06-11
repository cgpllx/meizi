package work;

import java.awt.List;

import com.kubeiwu.bean.Administrator;
import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.dao.AdministratorDao;
import com.kubeiwu.dao.GroupImageInfoDao;

public class Delete {
	public static void main(String[] args) {
		GroupImageInfoDao groupImageInfoDao = new GroupImageInfoDao();
		RequestListPara requestListPara=new RequestListPara();
		requestListPara.setCategory_code(380);
		requestListPara.setDbIndex(1);
		requestListPara.setPageCount(300);
		java.util.List<GroupImageInfo> lists=groupImageInfoDao.queryGroupImageInfoList(requestListPara);
		System.out.println("groupImageInfo="+lists.size());
		for(GroupImageInfo groupImageInfo:lists){
			System.out.println("groupImageInfo="+groupImageInfo.getId());
			groupImageInfoDao.delete(groupImageInfo.getId());
		}
	}
	

	public static void main1(String[] args) {
		GroupImageInfoDao messageDao = new GroupImageInfoDao();
		messageDao.open10RecordsByCategoryCode(70);
	}

	public static void logintext() {
		AdministratorDao administratorDao = new AdministratorDao();
		Administrator message = new Administrator();
		message.setPassword("cgp888");
		message.setUsername("cgpllx");

		int count = administratorDao.count(message);
		System.out.println(count);
	}
}
