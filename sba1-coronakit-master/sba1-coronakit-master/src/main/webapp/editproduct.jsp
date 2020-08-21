<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
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
<%
int productId= Integer.parseInt(request.getParameter("id"));
String productName= request.getParameter("name");
String productDescription= request.getParameter("description");
int productPrice= Integer.parseInt(request.getParameter("price"));
%>

<form action="admin?action=updateproduct&id=<%=productId %>" method="post">
 <div>
  <div><label> Name</label></div> 
  <div><input type="text" name="productName" value=<%=productName%>></input></div>
  
  <div><label> Description</label></div>
  <div><input type="text" name="productDescription" value=<%=productDescription%>></input><div></div></div>
  
  <div><label> Cost</label></div>
  <div><input type="text" name="productPrice" value=<%=productPrice%>></input></div>
  
  </br>
  <div><input type="submit" value="Save"></div>
 
 </div>
 </form>


<hr/>	
	<jsp:include page="footer.jsp"/>
	<jsp:include page="logoutlink.jsp"/>
</body>
</html>