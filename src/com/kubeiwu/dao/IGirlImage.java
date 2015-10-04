package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.GirlImage;

/**
 * @author Sumkor 与GirlImage配置文件相对应的接口
 */
public interface IGirlImage {
	public void insertOne(GirlImage girlImage);
	public void insertBatch(List<GirlImage> girlImages);
}
