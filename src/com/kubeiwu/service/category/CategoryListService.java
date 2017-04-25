package com.kubeiwu.service.category;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

import com.kubeiwu.bean.Category;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.CategoryDao;
import com.kubeiwu.service.Service;

/**
 * 列表相关的业务功能 servlet不与dao层直接交互
 * 
 * @author cgp
 */
public class CategoryListService implements Service {
	public static final CategoryDao CATEGORYDAO = new CategoryDao();

	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {

		ResponseInfo<List<Category>> responseInfo = new ResponseInfo<List<Category>>();

		String id = req.getParameter("id");

		try {
			int sourceCategory_code = Integer.parseInt(id);
			List<Category> data = CATEGORYDAO.queryCategoryList(sourceCategory_code);
			responseInfo.setData(data);
			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
			resp.setHeader("Cache-Control", "max-age=36000");
			resp.setHeader("Date", Utils.toGMTString());
		} catch (Exception e) {
			e.printStackTrace();
			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("服务器异常");
		}
		
		return GSON.toJson(responseInfo);
	}

}