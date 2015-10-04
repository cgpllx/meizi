package com.kubeiwu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kubeiwu.bean.GirlImage;
import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.RequestListPara;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.GirlInfoDao;

/**
 * 登陆服务
 * 
 * @author Sumkor
 */
public class LoginService implements Service{

 

	public String handleRequest(HttpServletRequest req) {

		ResponseInfo<List<GirlInfo>> responseInfo = new ResponseInfo<List<GirlInfo>>();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		try {
			 
			// 将登录验证成功的信息写入session
			// 1得到session
			HttpSession hs = req.getSession(true);
			// 修改session的存在时间，秒未单位
			hs.setMaxInactiveInterval(60 * 60 * 24);//一天
			// 向session写入属性
			hs.setAttribute("pass", "OK");

			// 将ServletContext的visitTimes所对应的值++
//			String times = this.getServletContext().getAttribute("visitTimes").toString();
//			// 对times的值++，再重新放回ServletContext
//			this.getServletContext().setAttribute("visitTimes", (Integer.parseInt(times) + 1) + "");
		 

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