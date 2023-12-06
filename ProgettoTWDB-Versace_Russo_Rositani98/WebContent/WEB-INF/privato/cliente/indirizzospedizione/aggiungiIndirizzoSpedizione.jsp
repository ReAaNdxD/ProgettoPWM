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

<title>Aggiungi Indirizzo Di Spedizione</title>


<%@include file="/WEB-INF/head.jsp" %>

<link rel="stylesheet" type="text/css"
	href="/css/AggiungiCartaCredito.css">

</head>
<body>

	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>
 
 	<%@include file="/WEB-INF/header.jsp"%>







	<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row">
					<div class="col-lg-12 col-12">
						<div class="form-main">
							<div class="title">
								<h3 style="color: #f7941d">Aggiungi Indirizzo Di Spedizione</h3>
							</div>
							<%
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
							%>
							<form id="form" class="form" method="post"
								action="/privato/cliente/indirizzospedizione/AggiungiIndirizzoSpedizione"
								onsubmit="return valida()">
								<div class="row">
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="regione" class="font-weight-bold">Regione</label>
											<input id="regione" placeholder="Regione" type="text"
												onkeypress="return alphaOnly(event)" name="regione" required>
											<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->
											<small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="provincia" class="font-weight-bold">Provincia</label>
											<input onkeypress="return /[a-z]/i.test(event.key)"
												id="provincia" placeholder="Provincia" type="text"
												maxlength="2" pattern=".{2,}" required
												title="Provincia deve avere 2 lettere" name="provincia"
												style="text-transform: uppercase"> <small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="citta" class="font-weight-bold">Città</label> <input
												id="citta" placeholder="Città" type="text" name="citta"
												onkeypress="return alphaOnly(event)" required> <small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="via" class="font-weight-bold">Via</label> 
											<input
												id="via" placeholder="Via" type="text"
												onkeypress="return alphaOnly(event)" name="via" required>
											<small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="numeroCivico" class="font-weight-bold">Numero
												Civico</label> <input id="numeroCivico" required
												placeholder="Numero Civico" type="text" maxlength="3"
												name="numeroCivico"> <small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="cap" class="font-weight-bold">CAP</label> <input
												id="cap" placeholder="CAP" type="text"
												onkeypress="return onlyNumbers(event)" name="cap"
												maxlength="5" pattern=".{5,}" required
												title="CAP deve avere 5 cifre"> <small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="numeroTelefonico" class="font-weight-bold">Telefono</label>
											<input id="numeroTelefonico" placeholder="Telefono"
												type="text" onkeypress="return onlyNumbers(event)"
												name="numeroTelefonico" maxlength="10" pattern=".{10,}"
												required title="Inserire un numero di telefono di 10 cifre">
											<small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button">
											<button class="btn" onclick="history.go(-1); return false;">Annulla</button>
											<button type="submit" class="btn">Aggiungi</button>
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



	<script type="text/javascript" charset="UTF-8"
		src="/js/IndirizzoSpedizione/funzioniIS.js"></script>

</body>
</html>