package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.ADInfo;
import com.kubeiwu.bean.Category;

/**
 * @author  与Category配置文件相对应的接口
 */
public interface IADInfo {
	public List<ADInfo> queryADInfoList(); 
}
