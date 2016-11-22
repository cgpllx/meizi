<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="org.apache.catalina.Session"%>
<%@page import="com.kubeiwu.bean.RequestListPara"%>
<%@page import="com.kubeiwu.dao.IGroupImageInfo"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.ibatis.io.Resources"%>
<%@page import="java.io.Reader"%>
<%@page import="com.kubeiwu.db.DBAccess"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.kubeiwu.bean.GroupImageInfo"%>
<%@page import="com.kubeiwu.service.groupimageinfo.GroupImageInfoListService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    
//           	GroupImageInfoListService listservice = new GroupImageInfoListService();
//           	List<GroupImageInfo> list=listservice.queryGirlInfoList(70, 1, 20);
//           	out.print(new Gson().toJson(list));
          	    SqlSessionFactory sqlSessionFactory;	
 	Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("com/kubeiwu/config/Configuration.xml");
		} finally {// 通过配置信息构建一个SqlSessionFactory
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		RequestListPara parameter=new RequestListPara();
		parameter.setCategory_code(70);
		parameter.setPageCount(20);
		parameter.setDbIndex(1);
		SqlSession s=sqlSessionFactory.openSession(false);
	IGroupImageInfo ig=	s.getMapper(IGroupImageInfo.class);
			List<GroupImageInfo> list= ig.queryGroupImageInfoList(parameter);;
				out.print(new Gson().toJson(list));
          	 
     
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    
  </body>
</html>
