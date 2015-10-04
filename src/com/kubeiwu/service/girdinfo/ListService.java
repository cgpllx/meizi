package com.kubeiwu.service.girdinfo;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kubeiwu.bean.Administrator;
import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.AdministratorDao;
import com.kubeiwu.dao.GirlInfoDao;
import com.kubeiwu.service.Service;

/**
 * 列表相关的业务功能 servlet不与dao层直接交互
 * 
 * @author Sumkor
 */
public class ListService implements GirdinfoService{


	public List<GirlInfo> queryGirlInfoList(int category_code, int currentPage, int pageCount) {


		RequestListPara parameter = new RequestListPara();// 参数封装
		parameter.setCategory_code(category_code);
		parameter.setDbIndex((currentPage - 1) * pageCount);
		parameter.setPageCount(pageCount);

		return GIRLINFODAO.queryGirlInfoList(parameter);
	}

	public String handleRequest(HttpServletRequest req) {

		ResponseInfo<List<GirlInfo>> responseInfo = new ResponseInfo<List<GirlInfo>>();

		String category = req.getParameter("category");
		String page = req.getParameter("page");
		String count = req.getParameter("count");

		try {

			Pattern pattern = Pattern.compile("[0-9]{1,9}");// 只能为0-9，最长9位数，因为是int
			if (!pattern.matcher(page).matches()) {
				throw new Exception("page错误  只能是0-9");
			}

			int category_code = Integer.parseInt(category);
			int currentPage = Integer.parseInt(page);
			int pageCount;
			if (count != null && !count.equals("")) {
				pageCount = Integer.parseInt(count);
			} else {
				pageCount = DEFAULT_PAGE_COUNT;
			}

			List<GirlInfo> girlinfos = queryGirlInfoList(category_code, currentPage, pageCount);
			responseInfo.setData(girlinfos);

			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
		} catch (Exception e) {
			e.printStackTrace();

			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("参数错误");
		}

		return GSON.toJson(responseInfo);
	}

	public static void main(String[] args) {
		
		
		AdministratorDao administratorDao= new AdministratorDao();
		Administrator message=new Administrator();
		message.setPassword("cgp888");
		message.setUsername("cgpllx2");;
		int count=administratorDao.count(message);
		System.out.println(count);
//		ListService listService = new ListService();
//		List<GirlInfo> lists = listService.queryGirlInfoList(1, 1, 20);
//		System.out.println(lists.size());
//		System.out.println(lists.get(0).getGirlImages());
//		GirlInfoDao messageDao = new GirlInfoDao();
//		GirlInfo message = new GirlInfo();
//		message.setCategory_code(1);
//		message.setFromurl("再次测试ddddddd");
//		message.setTitle("title标题");
//		message.setCoverimage("coverimage");
//		List<GirlImage> girlImages = new ArrayList<GirlImage>();
//		GirlImage girlImage = new GirlImage();
//		// girlImage.setGirlinfo_id(2);
//		girlImage.setImageurl("再次测试皮赖难过ddddd");
//		girlImages.add(girlImage);
//		girlImages.add(girlImage);
//		// girlImages.add(girlImage);
//		message.setGirlImages(girlImages);
//		messageDao.insertOne(message);
		// GirlInfo girlInfo=messageDao.queryGirlInfoById(1);
		// System.out.println(new Gson().toJson(girlInfo));

	}
}