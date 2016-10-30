package com.kubeiwu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kubeiwu.bean.Image;
import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.RequestListPara;

/**
 * 数据库GirlInfo表操作 ------实现IGirlInfo只是为了使用他的方法声明
 */
public class GroupImageInfoDao implements Dao, IGroupImageInfo {

	/**
	 * 根据查询条件查询消息列表
	 * 
	 * @param command
	 * @param description
	 * @return
	 */
	@Override
	public List<GroupImageInfo> queryGroupImageInfoList(RequestListPara parameter) {
		List<GroupImageInfo> messageList = new ArrayList<GroupImageInfo>();
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();// 加载配置信息，Mybatis中相关的类Configuration

			// 动态代理,接口没有实现类.Mybatis为接口提供实现类,即用Proxy.newProxyInstance()创建代理实例,返回类型为Object,利用泛型强制转换
			IGroupImageInfo girlInfo = sqlSession.getMapper(IGroupImageInfo.class);
			// 代理实例调用接口方法时,并不会执行,而是触发MapperProxy.invoke(),其中包含sqlSession.selectList(namespace.id,parameter)
			// 至于为什么会包含,因为接口方法与(加载Mybatis的)配置信息对应得上,即 接口名.方法=namespace.id
			messageList = girlInfo.queryGroupImageInfoList(parameter);
			// Thread.sleep(100);
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
	@Override
	public GroupImageInfo queryGroupImageInfoById(int id) {
		GroupImageInfo girlInfo = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();

			IGroupImageInfo iGirlInfo = sqlSession.getMapper(IGroupImageInfo.class);
			girlInfo = iGirlInfo.queryGroupImageInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return girlInfo;
	}

	@Override
	public GroupImageInfo queryGroupImageInfoByFromUrl(String fromurl) {
		SqlSession sqlSession = null;
		try {
			sqlSession = BACCESS.getSqlSession();

			IGroupImageInfo iGirlInfo = sqlSession.getMapper(IGroupImageInfo.class);
			GroupImageInfo girlInfoId = iGirlInfo.queryGroupImageInfoByFromUrl(fromurl);
			return girlInfoId;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return null;
	}

	@Override
	public int updateOne(GroupImageInfo girlInfo) {
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = BACCESS.getSqlSession();
			IImage iGirlImage = sqlSession.getMapper(IImage.class);
			IGroupImageInfo iGirlInfo = sqlSession.getMapper(IGroupImageInfo.class);
			count = iGirlInfo.updateOne(girlInfo);
			
			List<Image> images = girlInfo.getImages();
			if (images != null && images.size() > 0) {
				for (Image girlImage : images) {
					girlImage.setGroupimageinfo_id(girlInfo.getId());
				}
				System.out.println("images=" + images);
				iGirlImage.insertReplace(images);
			}
			
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return count;
	}

	/**
	 * 单条插入 一对多关联插入
	 *
	 * @param message
	 */
	public void insertOne(GroupImageInfo message) {
		SqlSession sqlSession = BACCESS.getSqlSession(false);

		IImage iGirlImage = sqlSession.getMapper(IImage.class);
		IGroupImageInfo iGirlInfo = sqlSession.getMapper(IGroupImageInfo.class);

		try {
			iGirlInfo.insertOne(message);
			List<Image> images = message.getImages();
			if (images != null && images.size() > 0) {
				for (Image girlImage : images) {
					girlImage.setGroupimageinfo_id(message.getId());
				}
				System.out.println("images=" + images);
				iGirlImage.insertReplace(images);
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

	@Override
	public int count(int categoryId) {
		SqlSession sqlSession = BACCESS.getSqlSession(false);
		IGroupImageInfo iGirlInfo = sqlSession.getMapper(IGroupImageInfo.class);
		return iGirlInfo.count(categoryId);
	}

}