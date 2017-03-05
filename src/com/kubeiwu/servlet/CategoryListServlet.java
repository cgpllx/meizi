package com.kubeiwu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kubeiwu.service.category.CategoryListService;

/**
 *
 *
 */
@SuppressWarnings("serial")
public class CategoryListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();

		CategoryListService listservice = new CategoryListService();
		out.write(listservice.handleRequest(req,resp));

		out.flush();
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
