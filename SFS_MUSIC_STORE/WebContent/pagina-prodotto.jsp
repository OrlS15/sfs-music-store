<%@page import="beans.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<% 
	if(request.getParameter("id")==null){
		response.sendRedirect("index.jsp");
		return;
	}
	ProductBean pb = (ProductBean) request.getAttribute("product");
	if(pb == null){
		response.sendRedirect("pagina-prodotto?id="+request.getParameter("id"));
		return;
	}
%>
<!DOCTYPE html>

<html lang="it">
    <head>
        <meta charset="UTF-8" />      
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SFS Music Store - Prodotto</title>
        <link href="assets/styles/pagina-prodotto.css" rel="stylesheet" />
        <link href="assets/styles/fonts.css" rel="styleheet" />
    </head>
    <body>
        <jsp:include page="./includes/header.jsp"></jsp:include>
        <section>
            <div class="global">
                <div class="imageBox">
                    <img src="getImage?id=<%=pb.getId() %>" alt="Immagine del prodotto"/>
                </div>
                <div class="infoBox">
                    <div class="basicText"><%=pb.getNome() %></div>
                    <div class="info">
                        <%=pb.getDescrizione() %>
                    </div>
                    <div>
                        <a href="carrello?action=add&id=<%=pb.getId()%>&redirect=carrello" class="buyButton">
                            <span>ACQUISTA</span>
                            <img src="assets/icons/addToCart.svg" alt="Aggiungi al Carrello"/>
                        </a>
                        <p>Restanti: <%=pb.getQuantita() %></p>
                    </div>
                </div>
            </div>
        </section>
        
        <jsp:include page="./includes/footer.jsp"></jsp:include>
    </body>
    
</html>