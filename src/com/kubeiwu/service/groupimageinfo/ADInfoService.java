package com.kubeiwu.service.groupimageinfo;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

import com.kubeiwu.bean.ADInfo;
import com.kubeiwu.bean.ResponseInfo;
import com.kubeiwu.constant.ResponseCode;
import com.kubeiwu.dao.ADInfoDao;
import com.kubeiwu.service.Service;

public class ADInfoService implements Service {
	ADInfoDao ADINFODAO = new ADInfoDao();
	@Override
	public String handleRequest(HttpServletRequest req,HttpServletResponse resp) {
		ResponseInfo<ADInfo> responseInfo = new ResponseInfo<ADInfo>();
		try {
			List<ADInfo> girlInfo = ADINFODAO.queryADInfoList();
			if(girlInfo!=null&&girlInfo.size()>0){
				responseInfo.setData(girlInfo.get(0));
			}
			responseInfo.setCode(ResponseCode.SUCCESS_CODE);
			responseInfo.setDesc("正确处理");
			
			//添加缓存 10fei
			resp.setHeader("Cache-Control", "max-age=600");
			resp.setHeader("Date", Utils.toGMTString());
		} catch (Exception e) {
			e.printStackTrace();
			responseInfo.setCode(ResponseCode.ERROR_CODE);
			responseInfo.setDesc("参数错误");
		}

		return GSON.toJson(responseInfo);
	}

}
