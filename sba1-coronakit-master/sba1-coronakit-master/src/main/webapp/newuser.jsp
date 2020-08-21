<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h1>Corona Prevention Kit</h1>
<form action="user?action=insertuser" method="post">
		<div>
			<div><label for="username">Enter Name(*)</label> </div>
			<div><input type="text" id="username" name="username"> </div>
		</div>
		<div>
			<div><label for="email">Enter Email id(*)</label> </div>
			<div><input type="text" id="email" name="email"> </div>
		</div>
		<div>
			<div><label for="phone">Enter Phone Number(*)</label> </div>
			<div><input type="number" id="phone" name="phone"> </div>
		</div>
		</br>
		<div>
			<div><input type="submit" value="Order Corona Kit"> </div>
		</div>
	</form>


<hr/>	
	<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>