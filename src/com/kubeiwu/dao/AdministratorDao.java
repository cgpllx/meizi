package com.kubeiwu.dao;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.Administrator;

/**
 * 数据库Message表操作
 * 
 * @author Sumkor
 */
public class AdministratorDao implements Dao, IAdministrator {

	@Override
	public int count(Administrator message) {
		SqlSession sqlSession = BACCESS.getSqlSession(false);

		IAdministrator imessage = sqlSession.getMapper(IAdministrator.class);
		int result = 0;
		try {
			result = imessage.count(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

}