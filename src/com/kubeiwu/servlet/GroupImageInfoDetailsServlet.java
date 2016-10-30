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
import com.kubeiwu.service.girdinfo.GroupImageInfoDetailsService;
import com.kubeiwu.service.girdinfo.GroupImageInfoListService;

/**
 * 列表查询
 * 
 * @author cgp
 *
 */
@SuppressWarnings("serial")
public class GroupImageInfoDetailsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		ServletOutputStream pwout = resp.getOutputStream();

		Service listservice = new GroupImageInfoDetailsService();
		byte[] result = listservice.handleRequest(req).getBytes("UTF-8");
		String accept_encoding = resp.getHeader("Accept-Encoding");
		System.out.println("gzip="+accept_encoding);
		if (accept_encoding != null && accept_encoding.equalsIgnoreCase("gzip")) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			System.out.println("gzip");
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
