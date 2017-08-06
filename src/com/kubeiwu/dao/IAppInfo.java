package com.kubeiwu.dao;



import java.util.List;

import com.kubeiwu.bean.AppInfo;

/**
 * @author  与Category配置文件相对应的接口
 */
public interface IAppInfo {
	public List<AppInfo> queryAppInfo(String appApplicationId); 
}
