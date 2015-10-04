package com.kubeiwu.service.administrator;

import com.kubeiwu.dao.AdministratorDao;
import com.kubeiwu.service.Service;

public interface AdministratorService extends Service {
	AdministratorDao ADMINISTRATORDAO = new AdministratorDao();
}
