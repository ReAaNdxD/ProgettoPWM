<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.ClienteDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="it.unirc.db.ecommerce.beans.Cliente"%>

<!DOCTYPE html>
<html>
<head>
<title>Modifica Cliente</title>
<%@include file="/WEB-INF/head.jsp" %>
<link rel="stylesheet" href="/css/AggiungiCartaCredito.css">
</head>
<body class="js">


	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>
	<%
		if (session.getAttribute("idCliente") == null || request.getAttribute("cliente") == null) {
			//Non ha fatto il login ed ha messo direttamente l'url della jsp 
			response.sendRedirect("/login.jsp");
			return;
		}
		Cliente c = (Cliente) request.getAttribute("cliente");
		/* if (request.getAttribute("errore") != null) {
			//Campi sbagliati
 */	%>
 
 	<%@include file="/WEB-INF/header.jsp"%>

<%-- 	<div>
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

		if (request.getAttribute("modifica") != null) {
			//Non è riuscito a modificarlo
	%>
	<div>
		<h4 style="color: red; font-family: cursive;">ERRORE Non siamo
			riusciti a modificare il tuo account :-(</h4>
	</div>
	<%
		}
	%> --%>
	<%-- <div>
		<h2 style="font-family: inherit;">Modifica il tuo account</h2>
	</div>
	<form id="form" class="form" method="post" action="ModificaCliente"
		onsubmit="return valida()">
		<label for="nome">Nome</label> <input id="nome" placeholder="Nome"
			type="text" onkeypress="return alphaOnly(event)" name="nome"
			value=<%=c.getNome()%>>
		<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->

		<small></small><br> <label for="cognome">Cognome</label> <input
			onkeypress="return alphaOnly(event)" id="cognome"
			placeholder="Cognome" type="text" name="cognome"
			value=<%=c.getCognome()%>><small></small><br> <label
			for="dataNascita">Data Di Nascita</label> <input id="dataNascita"
			placeholder="Data Di Nascita" type="date" name="dataNascita"
			value=<%=c.getDataNascita()%>> <small></small><br> <label
			for="email">Email</label> <input id="email" placeholder="Email"
			type="text" name="email" value=<%=c.getEmail()%>><small></small><br>
		<label for="password">Password</label> <input id="password"
			type="password"
			title="La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale"
			placeholder="Password" name="password" value=<%=c.getPassword()%>>
		<small>La password deve essere tra 8 e 15 caratteri e deve
			contenere almeno <br>una lettera maiuscola una minuscola e un
			carattere speciale
		</small><br> <label for="verificaPassword">Verifica Password</label> <input
			id="verificaPassword" placeholder="Riscrivi Password" type="password"
			name="verificaPassword"> <small></small><br> <br>
		<button onclick="history.go(-1); return false;">Annulla</button>
		<button type="submit">Modifica account</button>
	</form>
 --%>


	<section id="contact-us" class="contact-us section">
	<div class="container">
		<div class="contact-head">
			<div class="row">
				<div class="col-lg-12 col-12">
					<div class="form-main">
						<div class="title">
							<h3 style="color: #f7941d">Modifica il tuo account</h3>
						</div>
						<%
							/* request.setAttribute("errore", false);
							request.setAttribute("email", false);
							request.setAttribute("modifica", false); */
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
							if (request.getAttribute("modifica") != null) {
								//Non è riuscito a salvarlo
						%><div class="error-page">
							<div class="error-inner">
								<h5 class="h5" style="color: #F7941D">
									<%
										out.append("Non siamo riusciti a modifica il tuo account riprova");
									%>
								</h5>
							</div>
						</div>
						<%
							}
						%>
						<form id="form" class="form" method="post"
							action="ModificaCliente" onsubmit="return valida()">
							<div class="row">
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label for="nome" class="font-weight-bold">Nome</label> <input
											id="nome" placeholder="Nome" type="text"
											onkeypress="return alphaOnly(event)" name="nome"
											value=<%=c.getNome()%>>
										<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->

										<small></small>
									</div>
								</div>
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label for="cognome" class="font-weight-bold">Cognome</label>
										<input onkeypress="return alphaOnly(event)" id="cognome"
											placeholder="Cognome" type="text" name="cognome"
											value=<%=c.getCognome()%>><small></small>
									</div>
								</div>
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label for="dataNascita" class="font-weight-bold">Data
											Di Nascita</label> <input id="dataNascita"
											placeholder="Data Di Nascita" type="date" name="dataNascita"
											value=<%=c.getDataNascita()%>> <small></small>
									</div>
								</div>
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label for="email" class="font-weight-bold">Email</label> <input
											id="email" placeholder="Email" type="text" name="email"
											value=<%=c.getEmail()%>><small></small>
									</div>
								</div>
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label for="password" class="font-weight-bold">Password</label>
										<input id="password" type="password"
											title="La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale"
											placeholder="Password" name="password"
											value=<%=c.getPassword()%>> <small>La
											password deve essere tra 8 e 15 caratteri e deve contenere
											almeno <br>una lettera maiuscola una minuscola e un
											carattere speciale
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
										<button class="btn" type="submit">Modifica</button>
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

<%@include file="/WEB-INF/footer.jsp"%>
	<%-- 	<%@include file="/WEB-INF/js.jsp"%> --%>

	<!-- Jquery -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery-migrate-3.0.0.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<!-- Popper JS -->
	<script src="/js/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="/js/bootstrap.min.js"></script>
	<!-- Color JS -->
	<script src="/js/colors.js"></script>
	<!-- Slicknav JS -->
	<script src="/js/slicknav.min.js"></script>
	<!-- Owl Carousel JS -->
	<script src="/js/owl-carousel.js"></script>
	<!-- Magnific Popup JS -->
	<script src="/js/magnific-popup.js"></script>
	<!-- Fancybox JS -->
	<script src="/js/facnybox.min.js"></script>
	<!-- Waypoints JS -->
	<script src="/js/waypoints.min.js"></script>
	<!-- Countdown JS -->
	<script src="/js/finalcountdown.min.js"></script>
	<!-- Nice Select JS -->
	<script src="/js/nicesellect.js"></script>
	<!-- Ytplayer JS -->
	<script src="/js/ytplayer.min.js"></script>
	<!-- Flex Slider JS -->
	<script src="/js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script src="/js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script src="/js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script src="/js/easing.js"></script>
	<!-- Active JS -->
	<script src="/js/active.js"></script>
	<script src="/js/myscript.js"></script>



	<script type="text/javascript" src="/js/registration.js"></script>
</body>
</html>