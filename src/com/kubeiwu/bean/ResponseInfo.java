package com.kubeiwu.bean;

import com.google.gson.annotations.Expose;

public class ResponseInfo<T> {
	@Expose
	private String code;
	@Expose
	private String desc;
	@Expose
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
