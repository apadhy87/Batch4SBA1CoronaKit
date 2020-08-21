<%@page import="com.iiht.evaluation.coronokit.model.KitDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<h1>Corona Prevention Kit</h1>
<h2>Cart...</h2>
<%
List<KitDetail>  shoppingKart= (List<KitDetail>)session.getAttribute("ShoppingCart");
%>
<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Amount</th>
			<th>Modify Quantiry</th>
			
		</thead>
		
		
		<tbody>
			<% for(KitDetail kit : shoppingKart) { %>
			<tr>
				<td><%=kit.getProduct().getProductName()%></td>
				<td><%=kit.getProduct().getCost()%></td>
				<td><%=kit.getQuantity() %></td>
         		<td><%=kit.getAmount() %></td>
         		<td><a href="user?action=addnewitem&id=<%=kit.getProduct().getId()%>">Add item</a>/<a href="user?action=deleteitem&id=<%=kit.getProduct().getId()%>">Remove Item</a></td>
			</tr>
				<% } %>
			
		</tbody>
		
	</table>
<%
int totaAmount=0;
int totalNoOfItems=0;
for(int k=0;k<shoppingKart.size();k++)
 {
	totaAmount+=shoppingKart.get(k).getAmount();
	totalNoOfItems+=shoppingKart.get(k).getQuantity();
 }
%>
</br>
<div>
	<b>Total Amount:<%=totaAmount %></b>
</div>
</br>
<div>
	<b>Total Number of items:<%=totalNoOfItems %></b>
</div>
</br>
<div>
	<b><a href="user?action=showproducts">Continue Shopping...</a></b>
</div>
</br>

<div>
	<a href="user?action=placeorder"><button>Place order</button></b>
</div>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>