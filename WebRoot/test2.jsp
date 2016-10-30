<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
          String driver = "com.mysql.jdbc.Driver"; 
          String url = "jdbc:mysql://127.0.0.1:3306/104127a1e4cedb"; //数据库web
          String user = "104127a1e4cedb"; 
          String password = "ffvvvcc104127a1e4cedb"; 
          try { 
               Class.forName(driver); 
               Connection  conn = DriverManager.getConnection(url, user, password);

               if(!conn.isClosed()) 
                    out.println("数据库连接成功！"); 
               conn.close(); 
          } 
          catch(ClassNotFoundException e) { 
               out.println("找不到驱动程序"); 
               e.printStackTrace(); 
          } 
          catch(SQLException e) { 
            out.println("xxx"+e.getMessage()); 
               e.printStackTrace(); 
          } 
     
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
