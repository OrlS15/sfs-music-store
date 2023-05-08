<%@page import="others.Cart"%>
<%@page import="others.CartItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	Cart carrello = (Cart) request.getAttribute("carrello");
	if(carrello == null){
		response.sendRedirect("carrello");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="assets/styles/fonts.css" />
<link rel="stylesheet" href="assets/styles/carrello.css" />
<title>SFS Music Store - Carrello</title>
</head>
<body>
	<% for(CartItem ci :carrello.getProducts()) { %>
		<p><%=ci.getProductBean().getId()+" | "+ci.getQuantita() %>
	<% } %>
	

</body>
</html>