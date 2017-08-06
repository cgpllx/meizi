package com.kubeiwu.service.groupimageinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

import com.kubeiwu.bean.AppInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.AppInfoDao;
import com.kubeiwu.service.Service;

public class AppInfoService implements Service {
	AppInfoDao appInfoDao = new AppInfoDao();

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		ResponseInfo<AppInfo> responseInfo = new ResponseInfo<AppInfo>();
		try {
			String appApplicationId = req.getParameter("applicationId");

			List<AppInfo> adInfos = appInfoDao.queryAppInfo(appApplicationId);
			AppInfo appInfo = null;
			if (adInfos != null && adInfos.size() > 0) {
				appInfo = adInfos.get(0);
			}

			responseInfo.setData(appInfo);
			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");

			// 添加缓存 10fei
			resp.setHeader("Cache-Control", "max-age=6000");
			resp.setHeader("Date", Utils.toGMTString());
		} catch (Exception e) {
			e.printStackTrace();
			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("参数错误");
		}

		return GSON.toJson(responseInfo);
	}

}
