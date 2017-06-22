package utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kubeiwu.service.Service;

public class HeaderUtils {
	public static byte[] buildBytes(HttpServletRequest req,
			HttpServletResponse resp, Service service)
			throws UnsupportedEncodingException {
		byte[] result;
		String encryption = req.getHeader("encryption");
		String debug = req.getParameter("debug");
		if (!"1".equals(debug)&&(null == encryption	|| "1".equals(encryption))) {
			result = AESUtil.encode(service.handleRequest(req, resp)).getBytes(
					"UTF-8");
			resp.setHeader("encryption", "1");
		} else {
			result = service.handleRequest(req, resp).getBytes("UTF-8");
		}
		return result;
	}
}
