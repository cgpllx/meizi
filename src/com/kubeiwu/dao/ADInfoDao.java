package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.ADInfo;

public class ADInfoDao implements IADInfo,Dao{

	@Override
	public List<ADInfo> queryADInfoList() {
		List<ADInfo> adInfos = new ArrayList<ADInfo>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			IADInfo adInfo = sqlSession.getMapper(IADInfo.class);
			adInfos = adInfo.queryADInfoList();
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
