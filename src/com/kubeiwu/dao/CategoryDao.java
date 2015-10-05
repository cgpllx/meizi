package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.Category;

public class CategoryDao implements Dao, ICategory {
	@Override
	public List<Category> queryCategoryList() {
		List<Category> categories = new ArrayList<Category>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();//

			ICategory girlInfo = sqlSession.getMapper(ICategory.class);
			categories = girlInfo.queryCategoryList();
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
