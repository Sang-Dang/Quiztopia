<%-- 
    Document   : login
    Created on : Apr 25, 2023, 2:08:10 AM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
			out.println("<p style='color:red'>" + error + "</p>");
		}
	%>
	<form action="LoginServlet" method="post">
		<label for="username">Username:</label><br>
		<input type="text" id="username" name="username"><br>
		<label for="password">Password:</label><br>
		<input type="password" id="password" name="password"><br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>