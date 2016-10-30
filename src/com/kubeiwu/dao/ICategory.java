package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.Category;

/**
 * @author  与Category配置文件相对应的接口
 */
public interface ICategory {
	public List<Category> queryCategoryList(int sourceCategory_code); 
}
