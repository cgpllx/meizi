package com.kubeiwu.service.administrator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kubeiwu.bean.Administrator;
import com.kubeiwu.bean.GirlInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.constant.SystemConstant;

/**
 * 登陆服务
 * 
 * @author Sumkor
 */
public class LoginService implements AdministratorService {
	public String handleRequest(HttpServletRequest req) {

		ResponseInfo<List<GirlInfo>> responseInfo = new ResponseInfo<List<GirlInfo>>();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		try {
			Administrator userInfo = new Administrator();
			userInfo.setPassword(password);
			userInfo.setUsername(username);
		
			int count = ADMINISTRATORDAO.count(userInfo);
			if (count == 0) {
				throw new Exception("用户不存在");
			}
			System.out.println(count);
			// 将登录验证成功的信息写入session
			// 1得到session
			HttpSession hs = req.getSession(true);
			// 修改session的存在时间，秒为单位
			hs.setMaxInactiveInterval(60 * 60 * 24);// 一天
			// 向session写入属性
			hs.setAttribute(SystemConstant.USER_KEY, userInfo);

			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("登陆成功");
		} catch (Exception e) {
			e.printStackTrace();

			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("账号或者密码错误");
		}

		return GSON.toJson(responseInfo);
	}

}