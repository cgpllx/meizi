package com.kubeiwu.service;

import javax.servlet.http.HttpServletRequest;

import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.GirlInfoDao;

public class GirlDetailsService implements Service {

	@Override
	public String handleRequest(HttpServletRequest req) {
		ResponseInfo<GirlInfo> responseInfo = new ResponseInfo<GirlInfo>();

		String id = req.getParameter("id");

		try {
			int girlInfoId = Integer.parseInt(id);
			GirlInfoDao messageDao = new GirlInfoDao();
			GirlInfo girlInfo = messageDao.queryGirlInfoById(girlInfoId);
			responseInfo.setData(girlInfo);

			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
		} catch (Exception e) {
			e.printStackTrace();

			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("参数错误");
		}

		return GSON.toJson(responseInfo);
	}
}
