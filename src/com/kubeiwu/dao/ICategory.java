package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.Category;

/**
 * @author Sumkor 与Category配置文件相对应的接口
 */
public interface ICategory {
	public List<Category> queryCategoryList(); 
}
