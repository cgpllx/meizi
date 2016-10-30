package com.kubeiwu.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Service {// 不导出实体中没有用@Expose注解的属性
	Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	int DEFAULT_PAGE_COUNT = 20;

	String handleRequest(HttpServletRequest req);
}
