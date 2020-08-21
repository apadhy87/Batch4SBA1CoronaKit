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

<%
response.setHeader("Cache-Control", "no-cache, no-store");
List<KitDetail>  shoppingKart= (List<KitDetail>)session.getAttribute("ShoppingCart");
%>

<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Amount</th>
		</thead>
		
		
		<tbody>
			<% for(KitDetail kit : shoppingKart) { %>
			<tr>
				<td><%=kit.getProduct().getProductName()%></td>
				<td><%=kit.getProduct().getCost()%></td>
				<td><%=kit.getQuantity() %></td>
         		<td><%=kit.getAmount() %></td>
			</tr>
				<% } %>
			
		</tbody>
		
	</table>
	
<%
int totaAmount=0;
int totalNoOfItems=0;
int kitId=shoppingKart.get(shoppingKart.size()-1).getCoronaKitId();
for(int k=0;k<shoppingKart.size();k++)
 {
	totaAmount+=shoppingKart.get(k).getAmount();
	totalNoOfItems+=shoppingKart.get(k).getQuantity();
 }
session.setAttribute("totalamount", totaAmount);
session.setAttribute("totalNoOfItems", totalNoOfItems);
//can be fetched using any index as it is unique for the customer
session.setAttribute("Coronakitid", kitId);

%>
<br/>

<label><u><b>Order Details:</b></u></label>
<form action="user?action=saveorder" method="post">
		<div>
			<div><label for="Kitid"><b>Kit ID:</b><%=kitId %></label> </div>
			<div><label for="Quantity"><b>Number of items:</b><%=totalNoOfItems %></label> </div>
			<div><label for="amount"><b>Total Amount:</b><%=totaAmount %></label> </div>
		</div>
		<div>
			<div><label for="Address"><b>Enter your address(Mandatory)</b></label> </div>
			<div><textarea id="address" name="address" rows="7" cols="50"></textarea> </div>
		</div>
		</br>
		<div>
			<div><input type="submit" value="Confirm Order"> </div>
		</div>
</form>
</br>
<div>
	<b><a href="user?action=showproducts">Continue Shopping...</a></b>
</div>

</br>
	<div>
	<a href="user?action=showkit">Go to Cart</a><b>(<%= totalNoOfItems%>)</b>
  </div>
 

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>