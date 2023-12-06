<%@page import="it.unirc.db.ecommerce.beans.CartaCredito"%>
<%@page import="it.unirc.db.ecommerce.beans.IndirizzoSpedizione"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
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
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.beans.MetodoSpedizione"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<!-- Meta Tag -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name='copyright' content=''>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title Tag  -->
<title>Eshop - eCommerce HTML5 Template.</title>
<!-- Favicon -->
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- Web Font -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap"
	rel="stylesheet">

<!-- StyleSheet -->

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
<link rel="stylesheet" href="/css/product.css">



</head>
<body class="js">

	<%@ include file="/WEB-INF/preload.jsp"%>
	<%@ include file="/WEB-INF/header-scriptlet.jsp"%>
	<%@ include file="/WEB-INF/header.jsp"%>

	<%
		Vector<MetodoSpedizione> metodi = (Vector<MetodoSpedizione>) request.getAttribute("metodi");
		Vector<IndirizzoSpedizione> indirizzi = (Vector<IndirizzoSpedizione>) request.getAttribute("indirizzi");
		Vector<CartaCredito> carte = (Vector<CartaCredito>) request.getAttribute("carte");
	%>




	<!-- Start Checkout -->
	<section class="shop checkout section shopping-cart">
		<!-- Form -->
		<form class="form" method="post" action="CheckOut">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-12">
						<div class="checkout-form">
							<h2>Make Your Checkout Here</h2>
							<p>Please register in order to checkout more quickly</p>

							<%
								if (request.getAttribute("errore") != null) {
									//Indirizzi non è sbagliato
							%>
							<div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
											out.append("Alcuni campi dell'indirizzo sono sbagliati");
										%>
									</h5>
								</div>
							</div>
							<%
								}

								if (request.getAttribute("erroreC") != null) {
									//Alcuni campi di carta credito sono sbagliati
							%>
							<div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
											out.append("Alcuni campi della carta credito sono sbagliati");
										%>
									</h5>
								</div>
							</div>
							<%
								}

								if (request.getAttribute("salvaC") != null) {
									//Non è riuscito a salvare la carta credito
									session.setAttribute("preferitoIS", null);
							%>
							<div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
											out.append("Non siamo riusciti a salvare la carta di credito");
										%>
									</h5>
								</div>
							</div>
							<%
								}
								if (request.getAttribute("salvaO") != null) {
									//Non è riuscito a salvare la carta credito
							%><div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
											out.append("Non siamo riusciti a salvare l'ordine");
										%>
									</h5>
								</div>
							</div>
							<%
								}
							%>

							<div id="accordion" class="accordion-area">
								<div class="panel">
									<div class="panel-header" id="headingOne">
										<button type="button" class="panel-link"
											data-toggle="collapse" data-target="#collapse1"
											aria-expanded="false" aria-controls="collapse1">Indirizzo</button>
									</div>
									<div id="collapse1" class="collapse"
										aria-labelledby="headingOne" data-parent="#accordion">
										<div class="panel-body">
											<div class="container">
												<div class="row mrg-btm">
													<%
														if (indirizzi != null && !indirizzi.isEmpty()) {

															for (IndirizzoSpedizione indirizzo : indirizzi) {
													%>
													<label class="mt-3"> <input type="radio"
														name="idIndirizzo" value="<%=indirizzo.getIdIndirizzoSpedizione() %>" class="card-input-element d-none"
														id="demo1"> <span
														class="card card-body bg-light d-flex flex-row justify-content-between align-items-center"><%=indirizzo%></span>
													</label>

													<%
														}

														} else {
													%>

													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>Via</label> <input id="via" type="text"
																onkeypress="return alphaOnly(event)" name="via" required>
															<small></small>
														</div>
													</div>
													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>Numero Civico</label> <input id="numeroCivico"
																required type="text" maxlength="3" name="numeroCivico">
															<small></small>
														</div>
													</div>
													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>Città</label> <input id="citta" type="text"
																name="citta" onkeypress="return alphaOnly(event)"
																required> <small></small>
														</div>
													</div>
													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>Provincia</label> <input
																onkeypress="return /[a-z]/i.test(event.key)"
																id="provincia" type="text" maxlength="2" pattern=".{2,}"
																required title="Provincia deve avere 2 lettere"
																name="provincia" style="text-transform: uppercase">
															<small></small>
														</div>
													</div>

													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>CAP</label> <input id="cap" type="text"
																onkeypress="return onlyNumbers(event)" name="cap"
																maxlength="5" pattern=".{5,}" required
																title="CAP deve avere 5 cifre"> <small></small>
														</div>
													</div>
													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>Regione</label> <input id="regione" type="text"
																onkeypress="return alphaOnly(event)" name="regione"
																required> <small></small>
														</div>
													</div>
													<div class="col-lg-6 col-md-6 col-12">
														<div class="form-group">
															<label>Telefono</label> <input id="numeroTelefonico"
																type="text" onkeypress="return onlyNumbers(event)"
																name="numeroTelefonico" maxlength="10" pattern=".{10,}"
																required
																title="Inserire un numero di telefono di 10 cifre">
															<small></small>
														</div>
													</div>

													<%
														}
													%>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel">
									<div class="panel-header" id="headingTwo">
										<button type="button" class="panel-link"
											data-toggle="collapse" data-target="#collapse2"
											aria-expanded="false" aria-controls="collapse2">Carte
											di Credito</button>
									</div>
									<div id="collapse2" class="collapse"
										aria-labelledby="headingTwo" data-parent="#accordion">
										<div class="panel-body">

											<div class="container">
												<div class="row mrg-btm">
													<div class="container">
														<div class="row mrg-btm">
															<%
																if (carte != null && !carte.isEmpty()) {

																	for (CartaCredito carta : carte) {
															%>
															<label class="mt-3"> <input type="radio"
																name="idCarta" value="<%=carta.getIdCartaCredito() %>" class="card-input-element d-none"
																id="demo2"> <span
																class="card card-body bg-light d-flex flex-row justify-content-between align-items-center"><%=carta%></span>
															</label>

															<%
																}

																} else {
															%>

															<div class="col-lg-6 col-md-6 col-12">
																<div class="form-group">
																	<label for="intestatario">Intestatario</label> <input
																		id="intestatario" type="text" name="intestatario"
																		onkeypress="return alphaOnly(event)"
																		title="Metti un intestatario" required></input> <small></small>
																</div>
															</div>
															<div class="col-lg-6 col-md-6 col-12">
																<div class="form-group">
																	<label for="dataScadenza">Data Di Scadenza</label> <input
																		id="dataScadenza" type="date" name="dataScadenza"
																		required /> <small></small> <small></small>
																</div>
															</div>
															<div class="col-lg-6 col-md-6 col-12">
																<div class="form-group">
																	<label for="codiceSicurezza">Codice Sicurezza</label> <input
																		id="codiceSicurezza" type="text"
																		name="codiceSicurezza" pattern=".{3,}" required
																		title="Il codice sicurezza deve avere tre cifre"
																		onkeypress="return onlyNumbers(event)" maxlength="3" /><small></small>
																</div>
															</div>

															<%
																}
															%>
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel">
									<div class="panel-header" id="headingTwo">
										<button type="button" class="panel-link"
											data-toggle="collapse" data-target="#collapse3"
											aria-expanded="false" aria-controls="collapse3">Prodotti</button>
									</div>
									<div id="collapse3" class="collapse"
										aria-labelledby="headingTwo" data-parent="#accordion">
										<div class="panel-body">
											<!-- Shopping Summery -->
											<table class="table shopping-summery">
												<tbody>
													<%
														int cont = 1;
														int qtaT = 0;
														for (ViewProduct product : articoliCarrello) {
													%>
													<tr>
														<td class="image center max-height" data-title="No"><img
															class="mrg-auto"
															src="https://via.placeholder.com/100x100" style="width:250px;" alt="#"></td>
														<td class="product-des" data-title="Description">
															<p class="product-name ">
																<a class="font-sz-14" href="#"><%=product.getNomeProdotto()%></a>
															</p>
															<p class="product-des font-sz-12">
																Disponibilità :
																<%=product.getDisponibilita()%></p>
														</td>
														<td class="price" data-title="Price"><span><%=product.getPrezzoAcquisto()%>€
														</span></td>
														<td class="qty" data-title="Qty">
															<!-- Input Order -->
															<div class="input-group d-flex jstfy-center">
																<input type="text" class="input-number pdg-0 width-50"
																	data-min="1" data-max="100"
																	value="<%=product.getQuantita()%>">
															</div> <!--/ End Input Order -->
													</tr>
													<%
														cont++;
														}
													%>
												</tbody>

											</table>
											<!--/ End Shopping Summery -->
										</div>
									</div>
								</div>
							</div>


						</div>

					</div>
					<div class="col-lg-4 col-12">
						<div class="order-details">
							<!-- Order Widget -->
							<div class="single-widget">
								<h2>CART TOTALS</h2>
								<div class="content">
									<ul>
										<%
											Carrello c = (Carrello) request.getAttribute("carrello");
										%>
										<li>Costo parziale<span>€</span><span id="spanu"><%=(double) request.getAttribute("costo")%></span></li>
										<%
											if (request.getAttribute("prime") == null) {
												System.out.println("if");
										%>
										<li class="firs">(+) Spedizione <span>€</span><span
											id="spanuccio">0</span></li>
										<li class="last">Totale<span>€</span><span id="spanuccia"><%=(double) request.getAttribute("costo")%></span></li>
										<%
											} else {
										%>
										<li class="firs">(+) Spedizione <span>€</span><span>0</span></li>
										<li class="last">Totale<span>€</span><span><%=(double) request.getAttribute("costo")%></span></li>
										<%
											}
										%>
									</ul>
								</div>
							</div>
							<!--/ End Order Widget -->
							<!-- Order Widget -->
							<div class="single-widget">
								<h2>Spedizione</h2>
								<div class="content" style="margin-left: 10px; margin-top: 1em;">
									<%
										int i = 1;
										// 									Vector<MetodoSpedizione> metodi = (Vector<MetodoSpedizione>) request.getAttribute("metodi");
										for (MetodoSpedizione metodo : metodi) {
									%>
									<label class="container font-sz-13"><input type="radio"
										name="radio" <%if (i == 1) {%> checked="checked" <%}%>
										value="<%=metodo.getIdMetodoSpedizione()%>"> <%=metodo.getTipo()%></label>

									<%
										i++;
										}
									%>
								</div>
							</div>
							<!--/ End Order Widget -->
							<!-- Button Widget -->
							<div class="single-widget get-button">
								<div class="content">
									<div class="button">
										<button type="submit" class="btn">Acquista</button>
									</div>
								</div>
							</div>
							<!--/ End Button Widget -->
						</div>
					</div>
				</div>
			</div>

		</form>
		<!-- / End Form -->
	</section>
	<!--/ End Checkout -->

	<%@ include file="/WEB-INF/footer.jsp"%>

	<%-- 	<%@ include file="/WEB-INF/footer.jsp" %> --%>
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


	<script type="text/javascript" src="/js/checkbox.js" charset="UTF-8"></script>
</body>
</html>
