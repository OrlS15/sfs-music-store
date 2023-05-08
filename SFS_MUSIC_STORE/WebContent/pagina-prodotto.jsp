<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SFS Music Store - Prodotto</title>
<link href="assets/styles/productPage.css" rel="stylesheet">
<link href="assets/styles/fonts.css" rel="styleheet">
</head>
<body>
<jsp:include page="./includes/header.jsp"></jsp:include>
<section>
	<div class="global">
		<div class="imageBox">IMMAGINE</div>
		<div class="infoBox">
			<div class="basicText">Chitarra fighissima!</div>
			<div class="info">
				<p>Corpo: Cenere</p>
				<p>Manico imbullonato: acero</p>
				<p>Tastiera: acero</p>
				<p>Intarsi tastiera a pois neri</p>
				<p>Scala: 648 mm (25,5")</p>
			</div>
			<div>
				<button class="buyButton">
				<span>ACQUISTA</span>
				<img src="assets/icons/addToCart">	
				</button>
				<p>Restanti: 4</p>
			</div>
		</div>
	</div>
</section>
<jsp:include page="./includes/footer.jsp"></jsp:include>
</body>
</html>