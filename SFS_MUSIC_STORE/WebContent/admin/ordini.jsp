<%@page import="beans.OrderBean"%>
<%@page import="beans.ProductBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
List<OrderBean> obs = (List<OrderBean>) request.getAttribute("ordini");
if (obs == null) {
	response.sendRedirect("ordini");
	return;
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SFS Music Store - Ordini</title>
<link href="../assets/styles/i-miei-ordini.css" rel="stylesheet" />
<link href="../assets/styles/fonts.css" rel="styleheet" />
</head>
<body>

	<jsp:include page="/includes/header.jsp"></jsp:include>

	<section>
		<h1>I miei ordini</h1>
		<div class="items-wrapper">
					<%
					if (obs != null)
						for (OrderBean ob : obs) {
					%>
					<div class="card-prodotto">
						<div class="image-prodotto">
							<a class="link-img-prodotto"
								href="pagina-prodotto?id=<%=ob.getPb().getId()%>"> <img
								src="${pageContext.request.contextPath}/getImage?id=<%=ob.getPb().getId()%>" alt="immagine" />
							</a>
						</div>
						<div class="info-prodotto">
							<div class="prodotto-title"><%=ob.getPb().getNome()%></div>
							<span class="data-indirizzo"><%=ob.getData()%></span>
							<span class="data-indirizzo"><%=ob.getIndirizzo()%></span>
							<span class="data-indirizzo">Codice Utente: <%=ob.getIdUtente()%></span>
							<div class="prezzo-prodotto">
								<span><%=ob.getPb().getPrezzo()%>&euro;</span>
								<span>| x<%=ob.getPb().getQuantita()%></span>
							</div>
						</div>
					</div>
					<%
					}
					%>
		</div>	
	</section>

	<jsp:include page="/includes/footer.jsp"></jsp:include>

</body>
</html>