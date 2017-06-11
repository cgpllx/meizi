package com.kubeiwu.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
 








import com.kubeiwu.service.groupimageinfo.GroupImageInfoListService;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoListServiceByHot;
import com.kubeiwu.service.groupimageinfo.GroupImageInfoListServiceByNew;

/**
 * 每天第一次访问后会更新部分数据，达到自动更新数据
 *
 */
@SuppressWarnings("serial")
public class GroupImageInfoListByNewServlet extends HttpServlet {
	GroupImageInfoListServiceByNew listservice ;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		ServletOutputStream pwout = resp.getOutputStream();
		try {
			byte[] result = listservice.handleRequest(req,resp).getBytes("UTF-8");
			// resp.setHeader("encryption", "1");
			String accept_encoding = req.getHeader("Accept-Encoding");
			System.out.println("gzip=" + accept_encoding);
			if (accept_encoding != null && accept_encoding.equalsIgnoreCase("gzip")) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				System.out.println("gzip=" + accept_encoding);
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
	
	@Override
	public void init() throws ServletException {
		super.init();
		  listservice = new GroupImageInfoListServiceByNew();
	}
	
}
