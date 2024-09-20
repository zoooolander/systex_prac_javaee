<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="<%=request.getContextPath()%>/style/myStyle.css"
	rel="stylesheet"></link>
<body>
	<h2>Create Customer</h2>
	<form action="createCustomer.do" method="post">
	<input type="hidden" name="action" value="cc2"/>
	<table border="0" style="margin: auto">
			<tbody>
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
</body>
</html>