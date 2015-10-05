package com.kubeiwu.service.girdinfo;

import java.io.InputStreamReader;
import java.io.Reader;

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
		// req.getSession().g // login
			responseInfo.setCode(ResponseCode.NOLOGIN_CODE);
			responseInfo.setDesc("未登陆");
		} else {
			try {
				Reader reader = new InputStreamReader(req.getInputStream(), "utf-8");
				GirlInfo girlInfo = GSON.fromJson(reader, GirlInfo.class);
				if (girlInfo == null) {
					throw new Exception("message==null");
				}
				GirlInfo db_girlInfo = GIRLINFODAO.queryGirlInfoByFromUrl(girlInfo.getFromurl());
				if(db_girlInfo==null){
					GIRLINFODAO.insertOne(girlInfo);
					responseInfo.setDesc("提交成功");
				}else{
					girlInfo.setId(db_girlInfo.getId());
					int count=GIRLINFODAO.updateOne(girlInfo);
					if(count>0){
						responseInfo.setDesc("修改成功");
					}else{
						responseInfo.setDesc("修改失败");
					}
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
