<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Customer</title>
<link href="<%=request.getContextPath()%>>style/myStyle.css"
	rel="stylesheet"></link>
</head>
<body>
	<h2>Create Customer</h2>
	<form action="../admin/dump.view" method="get">
		<table border="0" style="margin: auto">
			<tbody>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" name="email" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="text" name="telephone" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" size="60" /></td>
				</tr>
				<tr>
					<td>Birthday</td>
					<td><input type="date" name="birth" /></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><input type="radio" name="gender" value="male" />male <input
						type="radio" name="gender" value="female" />female</td>
				</tr>
				<tr>
					<td>Hobbies</td>
					<td><input type="checkbox" id=music name="habit" value="music" /><label
						for="music">music</label> <input type="checkbox" id=exercise
						name="habit" value="exercise" /><label for="exercise">exercise</label>
						<input type="checkbox" id=reading name="habit" value="reading" /><label
						for="reading">reading</label></td>
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