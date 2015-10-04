package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.GirlImage;
import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.RequestListPara;

/**
 * 数据库GirlInfo表操作 ------实现IGirlInfo只是为了使用他的方法声明
 */
public class GirlInfoDao implements Dao, IGirlInfo {

	/**
	 * 根据查询条件查询消息列表
	 * 
	 * @param command
	 * @param description
	 * @return
	 */
	public List<GirlInfo> queryGirlInfoList(RequestListPara parameter) {
		List<GirlInfo> messageList = new ArrayList<GirlInfo>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();// 加载配置信息，Mybatis中相关的类Configuration

			// 动态代理,接口没有实现类.Mybatis为接口提供实现类,即用Proxy.newProxyInstance()创建代理实例,返回类型为Object,利用泛型强制转换
			IGirlInfo girlInfo = sqlSession.getMapper(IGirlInfo.class);
			// 代理实例调用接口方法时,并不会执行,而是触发MapperProxy.invoke(),其中包含sqlSession.selectList(namespace.id,parameter)
			// 至于为什么会包含,因为接口方法与(加载Mybatis的)配置信息对应得上,即 接口名.方法=namespace.id
			messageList = girlInfo.queryGirlInfoList(parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	/**
	 * 根据id查询
	 *
	 * @param message
	 * @return
	 */
	public GirlInfo queryGirlInfoById(int id) {
		GirlInfo girlInfo = new GirlInfo();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();

			IGirlInfo iGirlInfo = sqlSession.getMapper(IGirlInfo.class);
			girlInfo = iGirlInfo.queryGirlInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return girlInfo;
	}

	/**
	 * 单条插入 一对多关联插入
	 *
	 * @param message
	 */
	public void insertOne(GirlInfo message) {
		SqlSession sqlSession = BACCESS.getSqlSession(false);

		IGirlImage iGirlImage = sqlSession.getMapper(IGirlImage.class);
		IGirlInfo iGirlInfo = sqlSession.getMapper(IGirlInfo.class);

		try {
			iGirlInfo.insertOne(message);
			List<GirlImage> images = message.getGirlImages();
			if (images != null && images.size() > 0) {
				for (GirlImage girlImage : images) {
					girlImage.setGirlinfo_id(String.valueOf(message.getId()));
				}
				iGirlImage.insertBatch(images);
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 注意数据库引擎，不能使用myxxx 可以使用innodb
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

}