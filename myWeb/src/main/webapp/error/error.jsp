<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>this is an error handling page</title>
</head>
<body style="background-color: orange;">
	<h1>this is an error handling pageeee</h1>
	Root Cause: <%= exception.getMessage() %>
</body>
</html>