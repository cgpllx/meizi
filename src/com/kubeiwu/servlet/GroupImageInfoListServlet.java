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

import utils.AES;
import utils.AESUtil;
import utils.Base64Util;
import utils.EncryptUtil;
import utils.SimpleCrypto;

import com.kubeiwu.service.girdinfo.GroupImageInfoListService;

 
@SuppressWarnings("serial")
public class GroupImageInfoListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		ServletOutputStream pwout = resp.getOutputStream();

		GroupImageInfoListService listservice = new GroupImageInfoListService();
		try {
			byte[] result = AESUtil.encode(listservice.handleRequest(req)).getBytes("UTF-8");

			resp.setHeader("encryption", "1");
			
			String accept_encoding = req.getHeader("Accept-Encoding");
			System.out.println("gzip="+accept_encoding);
			if (accept_encoding != null && accept_encoding.equalsIgnoreCase("gzip")) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				System.out.println("gzip="+accept_encoding);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
