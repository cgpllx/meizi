package com.kubeiwu.service.girdinfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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

/**
 * 列表相关的业务功能 servlet不与dao层直接交互
 * 
 * @author cgp
 */
public class GroupImageInfoListService implements PublicService, ExclusionStrategy {
	Gson GSON = new GsonBuilder().setExclusionStrategies(this).create();

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface GroupImageInfoList_exclude {

	}

	public List<GroupImageInfo> queryGirlInfoList(int category_code, int currentPage, int pageCount) {

		RequestListPara parameter = new RequestListPara();// 参数封装
		parameter.setCategory_code(category_code);
		parameter.setDbIndex((currentPage - 1) * pageCount);
		parameter.setPageCount(pageCount);

		return GROUPIMAGEINFODAO.queryGroupImageInfoList(parameter);
	}

	public int getTotalCount(int categoryId) {
		return GROUPIMAGEINFODAO.count(categoryId);
	}

	public String handleRequest(HttpServletRequest req) {

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
				currentPage = 3;
			}

			List<GroupImageInfo> groupImageInfos = queryGirlInfoList(category_code, currentPage, pageCount);
			Paging<List<GroupImageInfo>> paging = new Paging<List<GroupImageInfo>>(getTotalCount(category_code),currentPage,pageCount);
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

		// GSON.toJson(jsonElement)
		return GSON.toJson(responseInfo);
	}

	public static void main(String[] args) {
		GroupImageInfoDao messageDao = new GroupImageInfoDao();
		GroupImageInfo id = messageDao.queryGroupImageInfoByFromUrl("http://m.mm131.com/mingxing/38.html");
		System.out.println(id.getId());
		id.setTitle("dddd");
		id.setCategory_code(10);
		id.setFromurl("ddd");
		id.setCoverimage("ddd");
		int count = messageDao.updateOne(id);
		System.out.println(count);
		// ListService listService = new ListService();
		// List<GirlInfo> lists = listService.queryGirlInfoList(1, 1, 20);
		// System.out.println(lists.size());
		// System.out.println(lists.get(0).getGirlImages());
		// GirlInfoDao messageDao = new GirlInfoDao();
		// GirlInfo message = new GirlInfo();
		// message.setCategory_code(1);
		// message.setFromurl("再次测试ddddddd");
		// message.setTitle("title标题");
		// message.setCoverimage("coverimage");
		// List<GirlImage> girlImages = new ArrayList<GirlImage>();
		// GirlImage girlImage = new GirlImage();
		// // girlImage.setGirlinfo_id(2);
		// girlImage.setImageurl("再次测试皮赖难过ddddd");
		// girlImages.add(girlImage);
		// girlImages.add(girlImage);
		// // girlImages.add(girlImage);
		// message.setGirlImages(girlImages);
		// messageDao.insertOne(message);
		// GirlInfo girlInfo=messageDao.queryGirlInfoById(1);
		// System.out.println(new Gson().toJson(girlInfo));

	}

	public static void logintext() {
		AdministratorDao administratorDao = new AdministratorDao();
		Administrator message = new Administrator();
		message.setPassword("cgp888");
		message.setUsername("cgpllx");

		int count = administratorDao.count(message);
		System.out.println(count);
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