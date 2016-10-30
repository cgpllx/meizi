package com.kubeiwu.bean;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.kubeiwu.service.girdinfo.GroupImageInfoListService.GroupImageInfoList_exclude;

public class GroupImageInfo {
	@Expose
	private int id;
	@Expose
	private String title;
	@GroupImageInfoList_exclude
	private String fromurl;//来源地址
	@GroupImageInfoList_exclude
	private int category_code;//类型id
	@Expose
	@GroupImageInfoList_exclude
	private List<Image> images;
	@Expose
	private String coverimage;//封面图片

	public String getCoverimage() {
		return coverimage;
	}

	public void setCoverimage(String coverimage) {
		this.coverimage = coverimage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFromurl() {
		return fromurl;
	}

	public void setFromurl(String fromurl) {
		this.fromurl = fromurl;
	}


	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}