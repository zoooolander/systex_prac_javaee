<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Customer</title>
<link href="<%=request.getContextPath()%>/style/myStyle.css"
	rel="stylesheet"></link>
</head>
<body>
	<h2>Create Customer</h2>
	<%--start error report --%>
	<% LinkedList<String> errors = (LinkedList<String>)request.getAttribute("errors");%>
	<% if(errors!=null){ %>
		<ul style="colof: red; font-size:0.8em">
		<table border="0" style="margin: auto; text-align: Left">
			<% for(String error: errors){ %>
				<li><%= error %>
			<% } %>
		</table>
		</ul>
	<% } %>
	<form action="ccController.do" method="post">
		<table border="0" style="margin: auto">
			<tbody>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" value="${ param.name}" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" name="email" value="${ param.email}" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="telephone" value="${ param.telephone}" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" size="60" value="${ param.address}"/></td>
				</tr>
				<tr>
					<td>Birthday</td>
					<td><input type="date" name="birth" value="${ param.birth}"/></td>
				</tr>
				<tr>
					<td>Gender</td>
						<td><%
						String isMale = "";
						String isFemale = "";
						String gender = request.getParameter("gender");
						if ("male".equals(gender)) {
							isMale = "checked";
						} else {
							isFemale = "checked";
						}
						%> <input type="radio" name="gender" value="male" <%= isMale %>/>male 
							<input type="radio" name="gender" value="female" <%= isFemale %>/>female
						</td>
				</tr>
				<tr>
					<td>Hobbies</td>
					<td><%
					String musicSelected = "";
					String exerciseSelected = "";
					String readingSelected = "";
					String[] habits = request.getParameterValues("habit");
					if (habits != null) {
						for (String habit : habits) {
							if (habit.equals("music")) {
								musicSelected = "checked";
							}
							if (habit.equals("exercise")) {
								musicSelected = "checked";
							}
							if (habit.equals("reading")) {
								musicSelected = "checked";
							}
						}
					}
					%> <input type="checkbox" name="habit" value="music" <%= musicSelected %>/>music
						<input type="checkbox" name="habit" value="exercise"<%= exerciseSelected %> />exercise
						<input type="checkbox" name="habit" value="reading" <%= readingSelected%>/>reading
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="reset" value="reset" /> <input type="submit"
						value="submit" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>