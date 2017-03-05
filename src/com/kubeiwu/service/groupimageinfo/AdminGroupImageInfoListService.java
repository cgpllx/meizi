package com.kubeiwu.service.groupimageinfo;


import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.Paging;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.constant.SystemConstant;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoListService.GroupImageInfoList_exclude;

/**
 * 列表相关的业务功能 servlet不与dao层直接交互
 * 
 * @author cgp
 */
public class AdminGroupImageInfoListService implements PublicService, ExclusionStrategy {
	Gson GSON = new GsonBuilder().setExclusionStrategies(this).create();

	// @Target(ElementType.FIELD)
	// @Retention(RetentionPolicy.RUNTIME)
	// public @interface GroupImageInfoList_exclude {
	//
	// }

	public List<GroupImageInfo> queryGirlInfoList(int category_code, int currentPage, int pageCount) {

		RequestListPara parameter = new RequestListPara();// 参数封装
		parameter.setCategory_code(category_code);
		parameter.setDbIndex((currentPage - 1) * pageCount);
		parameter.setPageCount(pageCount);

		return GROUPIMAGEINFODAO.adminQueryGroupImageInfoList(parameter);
	}

	public int getTotalCount(int categoryId) {
		return GROUPIMAGEINFODAO.adminCount(categoryId);
	}

	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {
		ResponseInfo<Paging<List<GroupImageInfo>>> responseInfo = new ResponseInfo<Paging<List<GroupImageInfo>>>();
 
		if (req.getSession().getAttribute(SystemConstant.USER_KEY) == null) {// no
			responseInfo.setCode(ResponseCode.NOLOGIN_CODE);
			responseInfo.setDesc("未登陆");
		} else {

			String category = req.getParameter("category");
			String page = req.getParameter("page");
			String count = req.getParameter("count");

			try {
				int category_code;
				int pageCount;
				int currentPage;

				if (category == null) {
					category_code = 70;
				} else {
					Pattern pattern = Pattern.compile("[0-9]{1,9}");// 只能为0-9，最长9位数，因为是int
					if (!pattern.matcher(category).matches()) {
						throw new Exception("category错误  只能是0-9");
					}
					category_code = Integer.parseInt(category);
				}
				if (count != null && !count.equals("")) {
					pageCount = Integer.parseInt(count);
				} else {
					pageCount = DEFAULT_PAGE_COUNT;
				}
				if (page != null && !page.equals("")) {
					currentPage = Integer.parseInt(page);
				} else {
					currentPage = 3;
				}

				List<GroupImageInfo> groupImageInfos = queryGirlInfoList(category_code, currentPage, pageCount);
				Paging<List<GroupImageInfo>> paging = new Paging<List<GroupImageInfo>>(getTotalCount(category_code), currentPage, pageCount);
				paging.setData(groupImageInfos);
				paging.setTotalCount(getTotalCount(category_code));
				responseInfo.setData(paging);
				responseInfo.setCode(ResponseCode.SUCCESS_CODE);
				responseInfo.setDesc("正确处理");
			} catch (Exception e) {
				e.printStackTrace();

				responseInfo.setCode(ResponseCode.ERROR_CODE);
				responseInfo.setDesc("参数错误");
			}
		}
		// GSON.toJson(jsonElement)
		return GSON.toJson(responseInfo);
	}

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		return arg0.getAnnotation(GroupImageInfoList_exclude.class) != null;
	}
}