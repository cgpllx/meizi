package com.kubeiwu.service.girdinfo;

import com.kubeiwu.dao.GirlInfoDao;
import com.kubeiwu.service.Service;

public interface GirdinfoService extends Service {
	GirlInfoDao GIRLINFODAO = new GirlInfoDao();
}
