package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.ADInfo;
import com.kubeiwu.bean.Config;

public class ConfigDao implements IConfig,Dao{

	@Override
	public List<Config> queryConfigList() {
		List<Config> adInfos = new ArrayList<Config>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			IConfig iconfig = sqlSession.getMapper(IConfig.class);
			adInfos = iconfig.queryConfigList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return adInfos;
	}

}
