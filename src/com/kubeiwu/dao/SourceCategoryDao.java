package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.Category;
import com.kubeiwu.bean.SourceCategory;

public class SourceCategoryDao implements Dao, ISourceCategory {
	@Override
	public List<SourceCategory> querySourceCategoryList() {
		List<SourceCategory> categories = new ArrayList<SourceCategory>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			ISourceCategory girlInfo = sqlSession.getMapper(ISourceCategory.class);
			categories = girlInfo.querySourceCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return categories;
	}

}
