package com.kubeiwu.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.HeaderUtils;
import utils.Utils;

import com.kubeiwu.service.Service;
import com.kubeiwu.service.groupimageinfo.DeleteGroupImageByIdService;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoDetailsService;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoListService;

/**
 * 列表查询
 * 
 * @author cgp
 *
 */
@SuppressWarnings("serial")
public class GroupImageInfoDetailsServlet extends HttpServlet {
	Service service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		ServletOutputStream pwout = resp.getOutputStream();

		try {
			byte[] result = HeaderUtils.buildBytes(req, resp, service);
			String accept_encoding = req.getHeader("Accept-Encoding");
			if (accept_encoding != null
					&& accept_encoding.equalsIgnoreCase("gzip")) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		service = new GroupImageInfoDetailsService();
	}
}
