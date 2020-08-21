<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>

<%
if(!session.getAttribute("user").toString().equals("admin") || session.getAttribute("user")==null)
{
	response.sendRedirect("index.jsp");
}
%>
<jsp:include page="header.jsp"/>
<hr/>

<form action="admin?action=insertproduct" method="post">

 <div>
  <div><label> Name</label></div> 
  <div><input type="text" name="productName"></input></div>
  
  <div><label> Description</label></div>
  <div><input type="text" name="productDescription"></input><div></div></div>
  
  <div><label> Cost</label></div>
  <div><input type="number" name="price"></input></div>
  
  </br>
  <div><input type="submit" value="Create"></div>
 
 </div>
 </form>

<hr/>	
	<jsp:include page="footer.jsp"/>
	<jsp:include page="logoutlink.jsp"/>
</body>
</html>