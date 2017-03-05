package com.kubeiwu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kubeiwu.service.Service;
import com.kubeiwu.service.groupimageinfo.CloseGroupImageByIdService;
import com.kubeiwu.service.groupimageinfo.InsertService;
import com.kubeiwu.service.groupimageinfo.OpenGroupImageByCategoryCodeService;

/**
 * 登陆
 * 
 * @author cgp
 *
 */
@SuppressWarnings("serial")
public class OpenGroupImageInfoByCategoryCodeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();

		Service listservice = new OpenGroupImageByCategoryCodeService();
		out.write(listservice.handleRequest(req,resp));

		out.flush();
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
