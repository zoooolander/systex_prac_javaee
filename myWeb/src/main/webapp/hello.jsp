<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error/error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!int a = 100;%>
	<%
	int a = 500;
	%>
	a =<%=a/0%><br /> current time is:
	<%=SimpleDateFormat.getInstance().format(new Date())%>
	<!-- html comment -->
	<%
	//java comment
	%>
	<%-- jsp comment --%>
	<br /> 
	User-agent:	<%=request.getHeader("User-Agent")%><br /> 
	User-agent:	<%	out.println(request.getHeader("User-Agent"));%><br/>
	User-agent: ${ header.User-Agent }<br/>
	User-agent: \${ header["User-Agent"]" }<br/>

</body>
</html>