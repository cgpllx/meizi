package com.kubeiwu.dao;

import java.util.List;

import com.kubeiwu.bean.Image;

/**
 * @author cgp 与GirlImage配置文件相对应的接口
 */
public interface IImage {
	public void insertOne(Image image);
	public void insertBatch(List<Image> images);
	public void insertReplace(List<Image> images);
}
