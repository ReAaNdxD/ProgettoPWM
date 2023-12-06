<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.beans.CartaCreditoDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.CartaCredito"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>Modifica Carta Credito</title>
<%@include file="/WEB-INF/head.jsp" %>

<link rel="stylesheet" type="text/css"
	href="/css/AggiungiCartaCredito.css">
</head>
<body>


<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>
	
	<%@include file="/WEB-INF/header.jsp"%>

	<%-- 	<%
		if (session.getAttribute("cartacredito") == null)
			//E' sconsigliato fare nelle jsp response.....
			out.write("Errore generico riprova più tardi");
		else {
			CartaCredito c = (CartaCredito) session.getAttribute("cartacredito");
	%>

	<form id="form" class="form" method="post"
		action="ModificaCartaCredito">
		<label for="numeroCarta">Numero Carta</label> <input id="numeroCarta"
			type="text" value="<%=c.getNumeroCarta()%>"
			onkeypress="return onlyNumbers(event)" name="numeroCarta"
			maxlength="16">
		<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->
		<small></small><br> <label for="intestatario">Intestatario</label>
		<input onkeypress="return alphaOnly(event)" id="intestatario"
			placeholder="intestatario" type="text" name="intestatario"
			value="<%=c.getIntestatario()%>"> <small></small><br> <label
			for="dataScadenza">Data Di Scadenza</label> <input id="dataScadenza"
			placeholder="data di scadenza" type="date" name="dataScadenza"
			value="<%=c.getDataScadenza()%>"> <small></small><br>
		<button onclick="history.go(-1); return false;">Annulla</button>
		<button type="submit" onclick="return confermaModifica()">Modifica</button>
	</form>

	<%
		}
	%> --%>

	<%
		if (session.getAttribute("cartacredito") == null || session.getAttribute("idCliente") == null){
			response.sendRedirect("/login.jsp");
	        return;
		}
		else {
			CartaCredito c = (CartaCredito) session.getAttribute("cartacredito");
	%>

	<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row">
					<div class="col-lg-12 col-12">
						<div class="form-main">
							<div class="title">
								<h3 style="color: #f7941d">Modifica Carta Credito</h3>
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
								action="ModificaCartaCredito">
								<div class="row">
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="numeroCarta" class="font-weight-bold">Numero
												Carta</label> <input id="numeroCarta" placeholder="numero di carta"
												type="text" onkeypress="return onlyNumbers(event)"
												title="Il numero carta deve avere tra 13 e 16 cifre"
												name="numeroCarta" maxlength="16" pattern=".{13,16}"
												value="<%=c.getNumeroCarta()%>" />
											<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->
											<small> </small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="intestatario" class="font-weight-bold">Intestatario</label>
											<input id="intestatario" placeholder="intestatario"
												type="text" name="intestatario"
												onkeypress="return alphaOnly(event)"
												title="Metti un intestatario"
												value="<%=c.getIntestatario()%>" required></input> <small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="dataScadenza" class="font-weight-bold">Data
												Di Scadenza</label> <input id="dataScadenza"
												placeholder="data di scadenza" type="date"
												name="dataScadenza" value="<%=c.getDataScadenza()%>"
												required /> <small></small>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="codiceSicurezza" class="font-weight-bold">Codice
												Sicurezza</label> <input id="codiceSicurezza"
												placeholder="codice di sicurezza" type="text"
												name="codiceSicurezza" pattern=".{3,}" required
												title="Il codice sicurezza deve avere tre cifre"
												onkeypress="return onlyNumbers(event)" maxlength="3"
												value="<%=c.getCodiceSicurezza()%>" /><small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button">
											<button class="btn" onclick="history.go(-1); return false;">Annulla</button>
											<button type="submit" class="btn"
												onclick="return confermaModifica()">Modifica</button>
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
	<%
		}
	%>
	
	
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
	<script src="/js/CartaCredito/funzioniVisualizzaCC.js"></script>
	<script src="/js/active.js"></script>
	<script src="/js/myscript.js"></script>

	<script type="text/javascript" charset="UTF-8"
		src="/js/CartaCredito/funzioni.js"></script>
</body>
</html>