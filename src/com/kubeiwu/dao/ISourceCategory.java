package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.Category;
import com.kubeiwu.bean.SourceCategory;

/**
 * 大类别
 */
public interface ISourceCategory {
	public List<SourceCategory> querySourceCategoryList() ; 
}
