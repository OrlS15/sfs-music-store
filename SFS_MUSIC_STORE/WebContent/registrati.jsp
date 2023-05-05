<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	List<String> errors = (List<String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="assets/styles/fonts.css" />
<link rel="stylesheet"
	href="assets/styles/LoginSignup.css" />
<title>SFS Music Store - Registrati</title>
</head>
<body>
	<jsp:include page="./includes/header.jsp"></jsp:include>
	<section>
		<div class="wrapper">
			<h1>REGISTRATI</h1>
			<% if(errors!=null){ %>
				<div class="errors-wrapper">
					<% for (String error : errors) { %>
					<span>- <%=error%></span>
					<% } %>
				</div>
			<% } %>
			<form action="registrati" method="POST">
				<label for="nome">Nome</label> <input type="text" name="nome"
					id="nome" required /> <label for="cognome">Cognome</label> <input
					type="text" name="cognome" id="cognome" required /> <label
					for="email">Email</label> <input type="email" name="email"
					id="email" required /> <label for="password">Password</label> <input
					type="password" name="password" id="password" required /> <input
					type="submit" value="REGISTRATI" />
			</form>
			<span class="no-account"> Hai già  un account? <a
				href="accedi.jsp">Accedi
					subito!</a>
			</span>
		</div>
	</section>
	<jsp:include page="./includes/footer.jsp"></jsp:include>
</body>
</html>
