package com.kubeiwu.service.groupimageinfo;

import com.kubeiwu.dao.GroupImageInfoDao;
import com.kubeiwu.service.Service;

public interface PublicService extends Service {
	GroupImageInfoDao GROUPIMAGEINFODAO = new GroupImageInfoDao();
	
	
}
