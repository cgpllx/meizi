package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.RequestListPara;

/**
 * @author cgp
 * 与Message配置文件相对应的接口IGroupImageInfo.java
 */
public interface IGroupImageInfo {
	//对应XML中id(queryMessageList),传入的参数,返回类型
	  List<GroupImageInfo> queryGroupImageInfoList(RequestListPara parameter)  ;//根据条件查询，需传入Page信息,不包含image集合
	  GroupImageInfo queryGroupImageInfoById(int id);//根据id查询
	  void insertOne(GroupImageInfo message);
	  GroupImageInfo queryGroupImageInfoByFromUrl(String fromurl);//根据id查询
	  int updateOne(GroupImageInfo message);
	  int count(int categoryId);
	
//	public int count(GirlInfo girlInfo);
//	public void deleteOne(int id);
//	public void deleteBatch(List<Integer> id);
}
