<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create successfully</title>
<link href="<%=request.getContextPath()%>>style/myStyle.css"
	rel="stylesheet"></link>
</head>
<body>
	<h2>creating customer successful</h2>
	<table border="1" style="margin:auto">
		<tbody>
			<tr><td>name</td><td>${ customer.name }</td></tr>
			<tr><td>email</td><td>${ customer.email }</td></tr>
			<tr><td>phone</td><td>${ customer.telephone }</td></tr>
			<tr><td>address</td><td>${ customer.address }</td></tr>
			<tr><td>birth</td><td>${ customer.birth }</td></tr>
			<tr><td>gender</td><td>${ customer.gender.equals('male')?'male':'female' }</td></tr>
			<tr><td>hobbies</td><td>${ customer.habitString }</td></tr>
		</tbody>
	</table>
	<br/>
	<a href="../">Go home</a>
	<% session.invalidate(); //create successfully and destroy the session %>
</body>
</html>