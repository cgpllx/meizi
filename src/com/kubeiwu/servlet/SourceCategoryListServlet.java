package com.kubeiwu.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kubeiwu.service.Service;
import com.kubeiwu.service.category.CategoryListService;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoDetailsService;
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
		byte[] result = listservice.handleRequest(req,resp).getBytes("UTF-8");
		String accept_encoding = resp.getHeader("Accept-Encoding");
		if (accept_encoding != null && accept_encoding.equalsIgnoreCase("gzip")) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			GZIPOutputStream gout = new GZIPOutputStream(out);
			gout.write(result);
			gout.close();
			result = out.toByteArray();
			resp.setHeader("Content-Encoding", "gzip");
			resp.setHeader("Content-Length", result.length + "");
			  //Last-Modified:页面的最后生成时间 
			resp.setDateHeader("Last-Modified",new Date().getTime());
		    //Expires:过时期限值 12 小时
			resp.setDateHeader("Expires", new Date().getTime() + 1000*60*60*12);
		    //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
			resp.setHeader("Cache-Control", "public");
		    //Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存
			resp.setHeader("Pragma", "Pragma"); 
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
