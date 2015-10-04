package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.RequestListPara;

/**
 * @author Sumkor
 * 与Message配置文件相对应的接口
 */
public interface IGirlInfo {
	//对应XML中id(queryMessageList),传入的参数,返回类型
	public List<GirlInfo> queryGirlInfoList(RequestListPara parameter);//根据条件查询，需传入Page信息,不包含image集合
	public GirlInfo queryGirlInfoById(int id);//根据id查询
	public void insertOne(GirlInfo message);
//	public int count(GirlInfo girlInfo);
//	public void deleteOne(int id);
//	public void deleteBatch(List<Integer> id);
//	public void updateOne(GirlInfo message);
}
