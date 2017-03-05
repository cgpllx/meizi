package com.kubeiwu.service.groupimageinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;

public class GroupImageInfoDetailsService implements PublicService {
	@Override
	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {
		ResponseInfo<GroupImageInfo> responseInfo = new ResponseInfo<GroupImageInfo>();

		String id = req.getParameter("id");

		try {
			int girlInfoId = Integer.parseInt(id);

			GroupImageInfo girlInfo = GROUPIMAGEINFODAO.queryGroupImageInfoById(girlInfoId);
			responseInfo.setData(girlInfo);

			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
			
//			添加缓存 10小时
			resp.setHeader("Cache-Control", "max-age=36000");
			resp.setHeader("Date", Utils.toGMTString());
		} catch (Exception e) {
			e.printStackTrace();

			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("参数错误");
		}

		return GSON.toJson(responseInfo);
	}

}
