 package com.kubeiwu.bean;

import com.google.gson.annotations.Expose;

public class Category {
	@Expose
	private int category_code;
	
	@Expose
	private String category_name;
	
	private String category_pinyin;
	
	@Expose
	private String category_en_name;
	
	@Expose
	private String category_icon;

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_pinyin() {
		return category_pinyin;
	}

	public void setCategory_pinyin(String category_pinyin) {
		this.category_pinyin = category_pinyin;
	}

	public String getCategory_en_name() {
		return category_en_name;
	}

	public void setCategory_en_name(String category_en_name) {
		this.category_en_name = category_en_name;
	}

	public String getCategory_icon() {
		return category_icon;
	}

	public void setCategory_icon(String category_icon) {
		this.category_icon = category_icon;
	}

	
}
