<%-- 
    Document   : error
    Created on : Apr 25, 2023, 2:42:41 AM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<h1>An error occurred</h1>
	<p><%= exception.getMessage() %></p>
</body>
</html>
