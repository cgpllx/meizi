package com.kubeiwu.service.groupimageinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.constant.SystemConstant;

public class OpenGroupImageByIdService implements PublicService {
	public static final Gson GSON = new Gson();
	public static final String ID = "id";
//	category_code
	@Override
	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {
		ResponseInfo<Object> responseInfo = new ResponseInfo<Object>();
		if (req.getSession().getAttribute(SystemConstant.USER_KEY) == null) {
			responseInfo.setCode(ResponseCode.NOLOGIN_CODE);
			responseInfo.setDesc("未登陆");
		} else {
			try {
				String id = req.getParameter(ID);
				int groupImageInfoId = Integer.parseInt(id);
				if (groupImageInfoId < 0) {
					throw new Exception("groupImageInfoId <0");
				}
				System.out.println("girlInfoId=" + groupImageInfoId);
				int code = GROUPIMAGEINFODAO.openGroupImageById(groupImageInfoId);
				if (code != -1) {
					responseInfo.setDesc("操作成功");
				} else {
					responseInfo.setDesc("操作失败");
				}
				responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			} catch (Exception e) {
				e.printStackTrace();
				responseInfo.setCode(ResponseCode.ERROR_CODE);
				responseInfo.setDesc("参数数据异常");
			}
		}
		return GSON.toJson(responseInfo);
	}
}
