<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link href="style/myStyle.css" rel="stylesheet"></link>
</head>
<body>
	<h1>Welcome to my Java EE playground</h1>
	<p>
		current time is:
		<%=SimpleDateFormat.getInstance().format(new Date())%></p>
	<p>
		<a href="admin/hello.aspx">Hello Servlet</a>
	</p>
	<p>
		<a href="admin/dump.view">Dump Servlet</a>
	</p>
	<p>
		<a href="customer/createCustomer.jsp">CreateCustomer</a>
	</p>
	<p>
		<a href="hello.jsp">Hello</a>
	</p>
	<p>
		<a href="admin/dump.jsp">Dump Servlet</a>
	</p>
	<p>
		<a href="customer/createCustomer1.jsp">測試交談時期</a>
	</p>
</body>
</html>