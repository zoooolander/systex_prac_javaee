<%@page import="java.util.Date"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dump JSP</title>
<link href="<%=request.getContextPath()%>/style/myStyle.css"
	rel="stylesheet"></link>
</head>
<body>
	<h2>dump jsp</h2>
	<%= session.isNew() %><br/>
	<%= session.getId() %><br/>
	<%= new Date(session.getLastAccessedTime()) %><br/>
	<%= new Date(session.getCreationTime()) %><br/>
	<%= session.getMaxInactiveInterval() %><br/>
	<img src="../image/pic1.webp" width="500px">
	<table border='1' style="margin: auto;">
		<tbody>
			<%
			for (int i = 1; i < 9; i++) {
			%>
			<tr>
				<%
				for (int j = 1; j < 9; j++) {
				%>
				<td><%=i%>*<%=j%>=</td>
				<td><%=i * j%></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<br />
	<%
	Enumeration<String> headerNames = request.getHeaderNames();
	%>
	<table border='1' style="margin: auto; width: 60%; text-align: Left">
		<thread>
		<tr>
			<th>Header Name</th>
			<th>Header Value</th>
		</tr>
		</thread>
		<tbody>
			<%
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
			%>
			<tr>
				<td><%=headerName%></td>
				<td><%=headerValue%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<br />
	<a href="../">Go Home</a>
</body>
</html>