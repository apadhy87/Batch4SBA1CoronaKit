<%@page import="com.iiht.evaluation.coronokit.model.OrderSummary"%>
<%@page import="com.iiht.evaluation.coronokit.model.KitDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<div align="center"><h1>Your Order Summary Details</h1></div>
		<br/>
	<hr/>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store");
		OrderSummary orderDetail = (OrderSummary)session.getAttribute("ordersummary");
	%>
	<div align="center">
		<table>
			<tbody>
				<tr><td>Order Id</td><td>:</td><td><%=orderDetail.getCoronaKit().getId()%></td></tr>
				<tr><td>Order Amount</td><td>:</td><td><%=orderDetail.getCoronaKit().getTotalAmount()%></td></tr>
				<tr><td>Ordered Products</td><td>:</td>
					<td>
						<table border="1">
							<thead">
								<th>Product Name</th>
								<th>Product Quantity</th>
								<th>Sub Total Cost</th>
							</thead>
							<tbody>
							<% for (KitDetail kit : orderDetail.getKitDetails()) {%>
								<tr>	
									<td><%=kit.getProduct().getProductName()%></td>
									<td><%=kit.getQuantity()%></td>
									<td><%=kit.getAmount()%></td>							
								</tr>
							<%}	%>					
							</tbody>
						</table>
					</td>
				</tr>
				<tr><td>Delivery Address</td><td>:</td>
					<td>
						<table>
							<tr><td><div  align="center"><%=orderDetail.getCoronaKit().getPersonName()%></div></td></tr>							
							<tr><td><div  align="center"><%=orderDetail.getCoronaKit().getDeliveryAddress()%></div></td></tr>
							<tr><td><div  align="center">Contact#:<%=orderDetail.getCoronaKit().getContactNumber()%></div></td></tr>
						</table>
					</td>
					
			</tbody>
		</table>
	</div>
	<hr/>
	<%
		session.invalidate();
	%>		
	<jsp:include page="footer.jsp"/>
</body>
</html>