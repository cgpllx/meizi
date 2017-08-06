package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.ADInfo;
import com.kubeiwu.bean.AppInfo;

public class AppInfoDao implements IAppInfo,Dao{

	@Override
	public List<AppInfo> queryAppInfo(String appApplicationId) {
		List<AppInfo> adInfos = new ArrayList<AppInfo>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			IAppInfo adInfo = sqlSession.getMapper(IAppInfo.class);
			 adInfos = adInfo.queryAppInfo(appApplicationId);
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return  adInfos;
	}

}
