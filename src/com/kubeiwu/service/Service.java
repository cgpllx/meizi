package com.kubeiwu.service;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public interface Service {
	Gson GSON = new Gson();
	int DEFAULT_PAGE_COUNT = 20;

	String handleRequest(HttpServletRequest req);
}
