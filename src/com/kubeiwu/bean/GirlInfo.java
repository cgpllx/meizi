package com.kubeiwu.bean;

import java.util.List;

public class GirlInfo {
	private int id;
	private String title;
	private String fromurl;//来源地址
	private String category_name;
	private int category_code;//类型id
	private boolean status;//是否显示的状态1 显示  0不显示
	private List<GirlImage> girlImages;
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

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<GirlImage> getGirlImages() {
		return girlImages;
	}

	public void setGirlImages(List<GirlImage> girlImages) {
		this.girlImages = girlImages;
	}

}
