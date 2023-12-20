<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crea un nuovo account</title>



<!-- Bootstrap -->
<link rel="stylesheet" href="/css/bootstrap.css">
<!-- Magnific Popup -->
<link rel="stylesheet" href="/css/magnific-popup.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="/css/font-awesome.css">
<!-- Fancybox -->
<link rel="stylesheet" href="/css/jquery.fancybox.min.css">
<!-- Themify Icons -->
<link rel="stylesheet" href="/css/themify-icons.css">
<!-- Nice Select CSS -->
<link rel="stylesheet" href="/css/niceselect.css">
<!-- Animate CSS -->
<link rel="stylesheet" href="/css/animate.css">
<!-- Flex Slider CSS -->
<link rel="stylesheet" href="/css/flex-slider.min.css">
<!-- Owl Carousel -->
<link rel="stylesheet" href="/css/owl-carousel.css">
<!-- Slicknav -->
<link rel="stylesheet" href="/css/slicknav.min.css">

<!-- Eshop StyleSheet -->
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsive.css">
<link rel="stylesheet" href="/css/AggiungiCartaCredito.css">


</head>
<body>
	<%-- 	<%
		if (request.getAttribute("errore") != null) {
			//Campi sbagliati
	%>

	<div>
		<h4 style="color: red; font-family: cursive;">ERRORE uno o più
			campi sono sbagliati :-(</h4>
	</div>
	<%
		}
		if (request.getAttribute("email") != null) {
			//L'email già esiste
	%>
	<div>
		<h4 style="color: red; font-family: cursive;">ERRORE l'email già
			esiste riprova con un altra email :-(</h4>
	</div>
	<%
		}
		if (request.getAttribute("salva") != null) {
			//Non è riuscito a salvarlo
	%>
	<div>
		<h4 style="color: red; font-family: cursive;">ERRORE Non siamo
			riusciti a salvare il tuo account :-(</h4>
	</div>
	<%
		}
	%>
	<div>
		<h2 style="font-family: inherit;">Crea un nuovo account</h2>
	</div>
	<form id="form" class="form" method="post" action="CreaNuovoCliente"
		onsubmit="return valida()">
		<label for="nome">Nome</label> <input id="nome" placeholder="Nome"
			type="text" onkeypress="return alphaOnly(event)" name="nome">
		<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->

		<small></small><br> <label for="cognome">Cognome</label> <input
			onkeypress="return alphaOnly(event)" id="cognome"
			placeholder="Cognome" type="text" name="cognome"><small></small><br>
		<label for="dataNascita">Data Di Nascita</label> <input
			id="dataNascita" placeholder="Data Di Nascita" type="date"
			name="dataNascita"> <small></small><br> <label
			for="email">Email</label> <input id="email" placeholder="Email"
			type="text" name="email"><small></small><br> <label
			for="password">Password</label> <input id="password" type="password"
			title="La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale"
			placeholder="Password" name="password"> <small>La
			password deve essere tra 8 e 15 caratteri e deve contenere almeno <br>una
			lettera maiuscola una minuscola e un carattere speciale
		</small><br> <label for="verificaPassword">Verifica Password</label> <input
			id="verificaPassword" placeholder="Riscrivi Password" type="password"
			name="verificaPassword"> <small></small><br> <br>
		<button onclick="history.go(-1); return false;">Annulla</button>
		<button type="submit">Crea il tuo account DAG</button>
	</form> --%>


	<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row">
					<div class="col-lg-12 col-12">
						<div class="form-main">
							<div class="title">
								<h3 style="color: #f7941d">Crea un nuovo account</h3>
							</div>
							<%
							/* request.setAttribute("errore", false);
							request.setAttribute("email", false);
							request.setAttribute("salva", false); */
							if (request.getAttribute("errore") != null) {
							%>
							<div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
										out.append("Alcuni campi sono sbagliati :-(");
										%>
									</h5>
								</div>
							</div>
							<%
							}
							if (request.getAttribute("email") != null) {
							//L'email già esiste
							%><div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
										out.append("L'email inserita è già in uso, inserisci un altra email");
										%>
									</h5>
								</div>
							</div>
							<%
							}
							if (request.getAttribute("salva") != null) {
							//Non è riuscito a salvarlo
							%><div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
										out.append("Non siamo riusciti a creare il tuo account riprova");
										%>
									</h5>
								</div>
							</div>
							<%
							}
							%>
							<form id="form" class="form" method="post"
								action="CreaNuovoCliente" onsubmit="return valida()">
								<div class="row">
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="nome" class="font-weight-bold">Nome</label> <input
												id="nome" placeholder="Nome" type="text"
												onkeypress="return alphaOnly(event)" name="nome">
											<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->

											<small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="cognome" class="font-weight-bold">Cognome</label>
											<input onkeypress="return alphaOnly(event)" id="cognome"
												placeholder="Cognome" type="text" name="cognome"><small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="dataNascita" class="font-weight-bold">Data
												Di Nascita</label> <input id="dataNascita"
												placeholder="Data Di Nascita" type="date" name="dataNascita">
											<small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="email" class="font-weight-bold">Email</label> <input
												id="email" placeholder="Email" type="text" name="email"><small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="password" class="font-weight-bold">Password</label>
											<input id="password" type="password"
												title="La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale"
												placeholder="Password" name="password"> <small
												class="font-italic font-weight-bold">La password
												deve essere tra 8 e 15 caratteri e deve contenere almeno <br>una
												lettera maiuscola una minuscola e un carattere speciale
											</small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="verificaPassword" class="font-weight-bold">Verifica
												Password</label> <input id="verificaPassword"
												placeholder="Riscrivi Password" type="password"
												name="verificaPassword"> <small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button">
											<button class="btn" onclick="history.go(-1); return false;">Annulla</button>
											<button class="btn" type="submit">Crea il tuo
												account DAG</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript" charset="UTF-8"
		src="/js/registration.js"></script>



</body>
</html>