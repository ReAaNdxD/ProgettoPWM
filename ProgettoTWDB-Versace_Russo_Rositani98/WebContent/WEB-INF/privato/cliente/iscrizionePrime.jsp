<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Iscrizione a Prime</title>
<%@include file="/WEB-INF/head.jsp" %>

</head>

<body>



	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>



	<%@include file="/WEB-INF/header.jsp"%>

	<%
		if (request.getAttribute("salva") != null) {
	%>
	<div class="error-page">
		<div class="error-inner">
			<h5 class="h2" style="color: black; text-align: center;">
				<%
					out.append("Riprova l'iscrizione");
				%>
			</h5>
		</div>
	</div>
	<%
		}
	%>


	<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row">
					<div class="col-lg-12 col-12 center">
						<div class="form-main">
							<div class="title">
								<h3 style="color: #f7941d">Iscrizione a Prime</h3>
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
							<form method="get" id="form" class="form"
								action="/privato/cliente/IscrizionePrime?name=<%=request.getParameter("name")%>">
								<div class="row center">

<!-- 									<div class="card"> -->
<!-- 										<h2 style="padding-left: 30px;">Iscrizione a Prime</h2> -->
								
										<ul>
											<li><label><input type="radio" name="name" id="one" checked
												value="mensile" /> <span for="one">Mensile </span><span>€3,99
													al mese</span>
												<div class="check"></div></label></li>
											<li><label><input type="radio" name="name" id="two"
												value="annuale" /> <span for="two">Annuale </span><span>€36
													all'anno</span>
												<div class="check"></div></label></li>
										<li><button class="btn btn-warning" type="submit" style="width:100%;">Iscrizione</button></li>
										</ul>
<!-- 									</div> -->
									<!-- 									<div class="col-lg-6 col-12"> -->
									<!-- 										<div class="form-group"> -->
									<!-- 											<label for="numeroCarta" class="font-weight-bold">Numero -->
									<!-- 												Carta</label> <input id="numeroCarta" placeholder="numero di carta" -->
									<!-- 												type="text" onkeypress="return onlyNumbers(event)" -->
									<!-- 												title="Il numero carta deve avere tra 13 e 16 cifre" -->
									<!-- 												name="numeroCarta" maxlength="16" pattern=".{13,16}" /> -->
									<!-- 											small element serve per mostrare un messaggio d'errore nel caso 
<!-- 					in cui gli input siano errati -->
<!-- 									--> 
									<!-- 											<small></small> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
									<!-- 									<div class="col-lg-6 col-12"> -->
									<!-- 										<div class="form-group"> -->
									<!-- 											<label for="intestatario" class="font-weight-bold">Intestatario</label> -->
									<!-- 											<input id="intestatario" placeholder="intestatario" -->
									<!-- 												type="text" name="intestatario" -->
									<!-- 												onkeypress="return alphaOnly(event)" -->
									<!-- 												title="Metti un intestatario" required></input> <small></small> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
									<!-- 									<div class="col-lg-6 col-12"> -->
									<!-- 										<div class="form-group"> -->
									<!-- 											<label for="dataScadenza" class="font-weight-bold">Data -->
									<!-- 												Di Scadenza</label> <input id="dataScadenza" -->
									<!-- 												placeholder="data di scadenza" type="date" -->
									<!-- 												name="dataScadenza" required /> <small></small> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
									<!-- 									<div class="col-lg-6 col-12"> -->
									<!-- 										<div class="form-group"> -->
									<!-- 											<label for="codiceSicurezza" class="font-weight-bold">Codice -->
									<!-- 												Sicurezza</label> <input id="codiceSicurezza" -->
									<!-- 												placeholder="codice di sicurezza" type="text" -->
									<!-- 												name="codiceSicurezza" pattern=".{3,}" required -->
									<!-- 												title="Il codice sicurezza deve avere tre cifre" -->
									<!-- 												onkeypress="return onlyNumbers(event)" maxlength="3" /><small></small> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
									<!-- 									<div class="col-12"> -->
									<!-- 										<div class="form-group button"> -->
									<!-- 											<button class="btn" onclick="history.go(-1); return false;">Annulla</button> -->
									<!-- 											<button type="submit" class="btn">Aggiungi</button> -->
									<!-- 										</div> -->
									<!-- 									</div> -->
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<!-- 	<div> -->



<!-- 		<div> -->
<!-- 			<div class="form-check"> -->
<!-- 				<input name="gruppo1" type="radio" id="radio1" checked><label -->
<!-- 					for="radio1">Radio di esempio 1</label> -->
<!-- 			</div> -->
<!-- 			<div class="form-check"> -->
<!-- 				<input name="gruppo1" type="radio" id="radio2"><label -->
<!-- 					for="radio2">Radio di esempio 2</label> -->
<!-- 			</div> -->
<!-- 		</div> -->

<!-- 		<form method="get" id="form" class="form" -->
<%-- 			action="/privato/cliente/IscrizionePrime?name=<%=request.getParameter("name")%>"> --%>
<!-- 			<div class="card"> -->
<!-- 				<h2 style="padding-left: 30px;">Iscrizione a Prime</h2> -->

<!-- 				<ul> -->
<!-- 					<li><input type="radio" name="name" id="one" checked -->
<!-- 						value="mensile" /> <label for="one">Mensile</label><small>€3,99 -->
<!-- 							al mese</small> -->
<!-- 						<div class="check"></div></li> -->
<!-- 					<li><input type="radio" name="name" id="two" value="annuale" /> -->
<!-- 						<label for="two">Annuale</label><small>€36 all'anno</small> -->
<!-- 						<div class="check"></div></li> -->
<!-- 				</ul> -->
<!-- 				<input type="hidden" name="hidden" value="return radio();"> -->
<!-- 				<button class="btn btn-warning" type="submit">Iscrizione</button> -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 	</div> -->

	<!--/ End Shopping Cart -->

	<%@include file="/WEB-INF/footer.jsp"%>

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
	<script src="/js/js.js"></script>
</body>

</html>