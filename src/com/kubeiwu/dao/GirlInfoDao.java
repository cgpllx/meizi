package com.kubeiwu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;

import com.kubeiwu.bean.GirlImage;
import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.db.GrilDBAccess;

/**
 * 数据库Message表操作
 * 
 * @author Sumkor
 */
public class GirlInfoDao {
	GrilDBAccess dbAccess = new GrilDBAccess();

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
			sqlSession = dbAccess.getSqlSession();// 加载配置信息，Mybatis中相关的类Configuration

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
		GirlInfo message = new GirlInfo();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();

			IGirlInfo imessage = sqlSession.getMapper(IGirlInfo.class);
			message = imessage.queryGirlInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return message;
	}

	/**
	 * 单条插入 一对多关联插入
	 *
	 * @param message
	 */
	public void insertOne(GirlInfo message) {
		GrilDBAccess dbAccess = new GrilDBAccess();
		SqlSession sqlSession = dbAccess.getSqlSession(false);

		IGirlImage iGirlImage = sqlSession.getMapper(IGirlImage.class);
		IGirlInfo imessage = sqlSession.getMapper(IGirlInfo.class);

		try {
			imessage.insertOne(message);
			List<GirlImage> images = message.getGirlImages();
			if (images != null && images.size() > 0) {
				for (GirlImage girlImage : images) {
					girlImage.setGirlinfo_id(String.valueOf(message.getId()));
				}
				iGirlImage.insertBatch(images);
				// int i = 2 / 0; // 触发运行时异常
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//注意数据库引擎，不能使用myxxx  可以使用innodb
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	//
	// /**
	// * 查询条数
	// *
	// * @param message
	// * @return
	// */
	// public int count(GirlInfo message) {
	// DBAccess dbAccess = new DBAccess();
	// SqlSession sqlSession = null;
	// int result = 0;
	// try {
	// sqlSession = dbAccess.getSqlSession();
	// IGirlInfo imessage = sqlSession.getMapper(IGirlInfo.class);
	// result = imessage.count(message);
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (sqlSession != null) {
	// sqlSession.close();
	// }
	// }
	// return result;
	// }
	//
	// /**
	// * 单条删除
	// *
	// * @param id
	// */
	// public void deleteOne(int id) {
	// DBAccess dbAccess = new DBAccess();
	// SqlSession sqlSession = null;
	// try {
	// sqlSession = dbAccess.getSqlSession();
	//
	// // // 通过sqlSession执行SQL语句
	// // sqlSession.delete("Message.deleteOne", id);
	// IGirlInfo imessage = sqlSession.getMapper(IGirlInfo.class);
	// imessage.deleteOne(id);
	//
	// // mybatis有事务控制能力，不会自动提交，所以对于增删改 需要手动提交
	// sqlSession.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (sqlSession != null) {
	// sqlSession.close();
	// }
	// }
	// }
	//
	// /**
	// * 批量删除
	// *
	// * @param id
	// */
	// public void deleteBatch(List<Integer> ids) {
	// DBAccess dbAccess = new DBAccess();
	// SqlSession sqlSession = null;
	// try {
	// sqlSession = dbAccess.getSqlSession();
	// IGirlInfo imessage = sqlSession.getMapper(IGirlInfo.class);
	// imessage.deleteBatch(ids);
	// // mybatis有事务控制能力，不会自动提交，所以对于增删改 需要手动提交
	// sqlSession.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (sqlSession != null) {
	// sqlSession.close();
	// }
	// }
	// }
	//

	//
	// /**
	// * 单条修改
	// *
	// * @param message
	// */
	// public void updateOne(GirlInfo message) {
	// DBAccess dbAccess = new DBAccess();
	// SqlSession sqlSession = null;
	// try {
	// sqlSession = dbAccess.getSqlSession();
	// IGirlInfo imessage = sqlSession.getMapper(IGirlInfo.class);
	// imessage.updateOne(message);
	// sqlSession.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (sqlSession != null) {
	// sqlSession.close();
	// }
	// }
	// }

}