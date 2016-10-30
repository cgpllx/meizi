package com.kubeiwu.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kubeiwu.service.Service;
import com.kubeiwu.service.category.CategoryListService;
import com.kubeiwu.service.girdinfo.GroupImageInfoDetailsService;
import com.kubeiwu.service.sourcecategory.SourceCategoryListService;

/**
 * 大的类别
 * */
@SuppressWarnings("serial")
public class SourceCategoryListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		ServletOutputStream pwout = resp.getOutputStream();

		Service listservice = new SourceCategoryListService();
		byte[] result = listservice.handleRequest(req).getBytes("UTF-8");
		String accept_encoding = resp.getHeader("Accept-Encoding");
		if (accept_encoding != null && accept_encoding.equalsIgnoreCase("gzip")) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			GZIPOutputStream gout = new GZIPOutputStream(out);
			gout.write(result);
			gout.close();
			result = out.toByteArray();
			resp.setHeader("Content-Encoding", "gzip");
			resp.setHeader("Content-Length", result.length + "");
		}

		pwout.write(result);
		pwout.flush();
		pwout.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
