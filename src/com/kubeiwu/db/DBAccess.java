package com.kubeiwu.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author cgp 加载Mybatis配置文件 访问数据库
 */
public class DBAccess {
	public final SqlSessionFactory sqlSessionFactory;

	public DBAccess() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("com/kubeiwu/config/Configuration.xml");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 通过配置信息构建一个SqlSessionFactory
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public SqlSession getSqlSession() {
		return getSqlSession(false);
	}
	public SqlSession getSqlSession(boolean b) {
		SqlSession sqlSession = sqlSessionFactory.openSession(b);
		return sqlSession;
	}

}
