<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<%
if(!session.getAttribute("user").toString().equals("admin") || session.getAttribute("user")==null)
{
	response.sendRedirect("index.jsp");
}
%>

<%  
   List<ProductMaster>  ProductList=(List<ProductMaster>) request.getAttribute("products");
%>

<a href="admin?action=newproduct"><button>Add New Product</button></a>

</br>

<table border="1" width="100%">
		<thead>
			<th>NAME</th>
			<th>PRICE</th>
			<th>DESCRIPTION</th>
			<th>EDIT</th>
			<th>DELETE</th>
		</thead>
		
		<tbody>
			<% for(ProductMaster product : ProductList) { %>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getCost()%></td>
				<td><%=product.getProductDescription()%></td>
				<td><a href="admin?action=editproduct&id=<%=product.getId()%>&name=<%=product.getProductName()%>&description=<%=product.getProductDescription()%>&price=<%=product.getCost()%>">Edit</a></td>
				<td><a href="admin?action=deleteproduct&id=<%=product.getId()%>">Delete</a></td>
			</tr>
			<% } %>
		</tbody>
		
	</table>

<hr/>	
	<jsp:include page="footer.jsp"/>
	<jsp:include page="logoutlink.jsp"/>
</body>
</html>