<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="assets/styles/fonts.css" />
<title>SFS Music Store - Gestione Prodotti</title>
</head>

<body>

	<form action="gestione-prodotti" method="POST" enctype="multipart/form-data" style="display:flex;flex-direction:column;">
		<label for="nome">Nome</label>
		<input type="text" id="nome" name="nome" required/>
		
		<label for="descrizione">Descrizione</label>
		<input type="text" id="descrizione" name="descrizione" required/>
		
		<label for="tipo">Tipo:</label>
		<select name="tipo" id="tipo">
		  <option value="basso">Basso</option>
		  <option value="chitarra">Chitarra</option>
		  <option value="strumento-a-corda">Strumento a corda</option>
		  <option value="batteria">Batteria</option>
		  <option value="fiato">Fiato</option>
		  <option value="pianoforte">Pianoforte</option>
		</select>
		
		<label for="prezzo">Prezzo</label>
		<input type="text" id="prezzo" name="prezzo" required />
		
		<label for="quantita">Quantità</label>
		<input type="text" id="quantita" name="quantita" required />
		
		<label for="immagine">Immagine</label>
		<input type="file" id="immagine" name="immagine" required />
		
		<input type="submit" value="INVIA" />
	</form>

</body>
</html>