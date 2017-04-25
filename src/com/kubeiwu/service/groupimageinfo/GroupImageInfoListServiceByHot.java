package com.kubeiwu.service.groupimageinfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubeiwu.bean.Administrator;
import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.Paging;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.AdministratorDao;
import com.kubeiwu.dao.GroupImageInfoDao;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoListService.GroupImageInfoList_exclude;

/**
 * 列表相关的业务功能 servlet不与dao层直接交互
 * 
 * @author cgp
 */
public class GroupImageInfoListServiceByHot implements PublicService, ExclusionStrategy {
	Gson GSON = new GsonBuilder().setExclusionStrategies(this).create();

//	@Target(ElementType.FIELD)
//	@Retention(RetentionPolicy.RUNTIME)
//	public @interface GroupImageInfoList_exclude {
//
//	}

	public List<GroupImageInfo> queryGirlInfoList(int category_code, int currentPage, int pageCount) {

		RequestListPara parameter = new RequestListPara();// 参数封装
		parameter.setCategory_code(category_code);
		parameter.setDbIndex((currentPage - 1) * pageCount);
		parameter.setPageCount(pageCount);

		return GROUPIMAGEINFODAO.queryGroupImageInfoListByHot(parameter);
	}

	public int getTotalCount(int categoryId) {
		return GROUPIMAGEINFODAO.countOfHot();
	}

	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {

		// ResponseInfo<List<GroupImageInfo>> responseInfo = new
		// ResponseInfo<List<GroupImageInfo>>();

		ResponseInfo<Paging<List<GroupImageInfo>>> responseInfo = new ResponseInfo<Paging<List<GroupImageInfo>>>();

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
				currentPage = 1;
			}

			List<GroupImageInfo> groupImageInfos = queryGirlInfoList(category_code, currentPage, pageCount);
			Paging<List<GroupImageInfo>> paging = new Paging<List<GroupImageInfo>>(getTotalCount(category_code), currentPage, pageCount);
			paging.setData(groupImageInfos);
			paging.setTotalCount(getTotalCount(category_code));
			responseInfo.setData(paging);
			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
			//handleupdate(req, category_code);
			resp.setHeader("Cache-Control", "max-age=36000");
			resp.setHeader("Date", Utils.toGMTString());
		} catch (Exception e) {
			e.printStackTrace();
			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("参数错误");
		}
		return GSON.toJson(responseInfo);
	}

	void handleupdate(HttpServletRequest req,int categoryCode) {
		ServletContext servletContext = req.getServletContext();
		Object lastDay = servletContext.getAttribute(categoryCode+"");// 上次更新的时间 day
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int date = c.get(Calendar.DATE);
		String nowDay = String.valueOf(date);
		if (lastDay != null && lastDay.equals(nowDay)) {// 今天已经更新了
		} else {
			int code = GROUPIMAGEINFODAO.open10RecordsByCategoryCode(categoryCode);
			if (code != -1) {
				servletContext.setAttribute(categoryCode+"", nowDay);
			}
			System.out.println("更新了10条 code＝"+code);
		}
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