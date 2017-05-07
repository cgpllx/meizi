package com.kubeiwu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.RequestListPara;

/**
 * @author cgp 与Message配置文件相对应的接口IGroupImageInfo.java
 */
public interface IGroupImageInfo {
	// 对应XML中id(queryMessageList),传入的参数,返回类型
	List<GroupImageInfo> queryGroupImageInfoList(RequestListPara parameter);// 根据条件查询，需传入Page信息,不包含image集合

	List<GroupImageInfo> adminQueryGroupImageInfoList(RequestListPara parameter);// 根据条件查询，需传入Page信息,不包含image集合

	List<GroupImageInfo> queryGroupImageInfoListByWhere(String where);// 根据条件查询，需传入Page信息,不包含image集合
	
	List<GroupImageInfo> queryGroupImageInfoListByHot(RequestListPara parameter);// 根据条件查询，需传入Page信息,不包含image集合
	
	List<GroupImageInfo> queryGroupImageInfoListByNew(RequestListPara parameter);// 根据条件查询，需传入Page信息,不包含image集合

	GroupImageInfo queryGroupImageInfoById(int id);// 根据id查询

	void insertOne(GroupImageInfo message);

	GroupImageInfo queryGroupImageInfoByFromUrl(String fromurl);// 根据id查询

	int updateOne(GroupImageInfo message);

	int updatePixel(GroupImageInfo message);// piccount

	int updatePiccount(GroupImageInfo message);//
	
	int updateLocalcoverimage(GroupImageInfo message);//

	int count(int categoryId);
	
	int countOfHot();

	int adminCount(int categoryId);

	int closeGroupImageById(int groupImageInfoId);

	int openGroupImageById(int groupImageInfoId);

	int closeGroupImagesByCategoryCode(int categoryCode);

	int openGroupImagesByCategoryCode(int categoryCode);

	int open10RecordsByCategoryCode(int categoryCode);

	int openGroupImageByIds(List<String> ids);
	
	int delete(int id);//根据id删除

}
