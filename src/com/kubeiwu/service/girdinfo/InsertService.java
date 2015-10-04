package com.kubeiwu.service.girdinfo;

import javax.servlet.http.HttpServletRequest;

import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.constant.SystemConstant;

public class InsertService implements GirdinfoService {

	@Override
	public String handleRequest(HttpServletRequest req) {
		ResponseInfo<Object> responseInfo = new ResponseInfo<Object>();
		if (req.getSession().getAttribute(SystemConstant.USER_KEY) == null) {// no
																				// login
			responseInfo.setCode(ResponseCode.NOLOGIN_CODE);
			responseInfo.setDesc("未登陆");
		} else {
			try {
				String girlInfojsonString = req.getParameter("girlinfojson");

				GirlInfo message = GSON.fromJson(girlInfojsonString, GirlInfo.class);
				if (message == null) {
					throw new Exception("message==null");
				}
				GIRLINFODAO.insertOne(message);
			} catch (Exception e) {
				e.printStackTrace();
				responseInfo.setCode(ResponseCode.ERROR_CODE);
				responseInfo.setDesc("参数数据异常");
			}
		}
		return GSON.toJson(responseInfo);
	}

}
