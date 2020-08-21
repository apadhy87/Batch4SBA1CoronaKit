<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% if(session.getAttribute("user").toString().equals("admin") && session.getAttribute("user")!=null)
{%>
<a href="admin?action=logout" >Log out</a>
<%} %>
</body>
</html>