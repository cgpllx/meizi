package com.kubeiwu.service.girdinfo;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubeiwu.bean.GroupImageInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.constant.SystemConstant;

public class InsertService implements PublicService {
	public static final Gson GSON =new Gson();
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
				GroupImageInfo groupImageInfo = GSON.fromJson(reader, GroupImageInfo.class);
				if (groupImageInfo == null) {
					throw new Exception("message==null");
				}
				System.out.println("groupImageInfo.getFromurl()"+groupImageInfo.getFromurl());
				GroupImageInfo db_groupImageInfo = GROUPIMAGEINFODAO.queryGroupImageInfoByFromUrl(groupImageInfo.getFromurl());
				if(db_groupImageInfo==null){
					GROUPIMAGEINFODAO.insertOne(groupImageInfo);
					responseInfo.setDesc("提交成功");
				}else{
					groupImageInfo.setId(db_groupImageInfo.getId());
					int count=GROUPIMAGEINFODAO.updateOne(groupImageInfo);
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
