package com.kubeiwu.service.sourcecategory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubeiwu.bean.Category;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.bean.SourceCategory;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.CategoryDao;
import com.kubeiwu.dao.SourceCategoryDao;
import com.kubeiwu.service.Service;

/**
 * 列表相关的业务功能 servlet不与dao层直接交互
 */
public class SourceCategoryListService implements Service {

	public static final SourceCategoryDao CATEGORYDAO = new SourceCategoryDao();

	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {

		ResponseInfo<List<SourceCategory>> responseInfo = new ResponseInfo<List<SourceCategory>>();

		try {
			List<SourceCategory> data = CATEGORYDAO.querySourceCategoryList();
			responseInfo.setData(data);
			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
		} catch (Exception e) {
			e.printStackTrace();
			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("服务器异常");
		}
		
		return GSON.toJson(responseInfo);
	}

 

}