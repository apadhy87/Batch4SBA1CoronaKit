<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.iiht.evaluation.coronokit.model.KitDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>

<h1>Corona Prevention Kit</h1>
<h2>Getting Started...</h2>

<% 
List<ProductMaster>  ProductList=(List<ProductMaster>) request.getAttribute("products");
%>


<table border="1" width="100%">
		<thead>
			<th>NAME</th>
			<th>DESCRIPTION</th>
			<th>PRICE</th>
			<th>ADD ITEMS</th>
			
		</thead>
		
		
		<tbody>
			<% for(ProductMaster product : ProductList) { %>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getProductDescription()%></td>
				<td><%=product.getCost()%></td>
         		<td><a href="user?action=addnewitem&id=<%=product.getId()%>">Add to cart</a>
         		
				</td>
			</tr>
			<% } %>
			
		</tbody>
		
	</table>
	
<%
if(session.getAttribute("ShoppingCart")!=null)
{
	List<KitDetail>  existingKits=(List<KitDetail>)session.getAttribute("ShoppingCart");
	int totalIteminCart=0;
	 for(int k=0;k<existingKits.size();k++)
	  {
		 totalIteminCart+=existingKits.get(k).getQuantity();
	  }
%>	
</br>
	<div>
	<a href="user?action=showkit">Go to Cart</a><b>(<%= totalIteminCart%>)</b>
  </div>
</br> 
 
  <div>
	<a href="user?action=placeorder"><button>Place order</button></b>
  </div>
 
<% } %>
	
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>