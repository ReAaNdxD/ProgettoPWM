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


	<%-- 	<%@ include file="/WEB-INF/preload.jsp" %>
	<%@ include file="/WEB-INF/header-scriptlet.jsp" %>
	<%@ include file="/WEB-INF/header.jsp" %> --%>



	<%-- 
	<%
		if (request.getAttribute("costo") == null) {
			response.sendRedirect("/");
			return;
		}
		if (request.getAttribute("carrello") == null) {
			response.sendRedirect("/");
			return;
		}
		if (request.getAttribute("indirizzi") == null) {
			response.sendRedirect("/");
			return;
		}
		if (request.getAttribute("metodi") == null) {
			response.sendRedirect("/");
			return;
		}
	%> --%>

	<!-- Start Checkout -->
	<section class="shop checkout section">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-12">
					<div class="checkout-form">
						<h2>Checkout</h2>
						<p>I tuoi dati</p>
						<div id="accordion" class="accordion-area">
							<div class="panel">
								<div class="panel-header" id="headingOne">
									<button class="panel-link" data-toggle="collapse"
										data-target="#collapse1" aria-expanded="false"
										aria-controls="collapse1">Indirizzo</button>
								</div>
								<div id="collapse1" class="collapse"
									aria-labelledby="headingOne" data-parent="#accordion">
									<div class="panel-body">
										<!-- Form -->
										<form class="form" method="post" action="#">
											<div class="row">
												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>Via</label> <input type="text" name="via"
															placeholder="" required="required">
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>Numero Civico</label> <input type="text"
															name="nCivico" placeholder="" required="required">
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>Città</label> <input type="text" name="citta"
															placeholder="" required="required">
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>Provincia</label> <input type="text" name="provincia"
															placeholder="" required="required">
													</div>
												</div>

												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>CAP</label> <input type="text" name="cap"
															placeholder="" required="required">
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>Regione</label> <input type="text" name="regione"
															placeholder="" required="required">
													</div>
												</div>
												<div class="col-lg-6 col-md-6 col-12">
													<div class="form-group">
														<label>Telefono</label> <input type="text" name="telefono"
															placeholder="" required="required">
													</div>
												</div>
											</div>
										</form>
										<!--/ End Form -->
									</div>
								</div>
							</div>
							<div class="panel">
								<div class="panel-header" id="headingTwo">
									<button class="panel-link" data-toggle="collapse"
										data-target="#collapse2" aria-expanded="false"
										aria-controls="collapse2">Descrizione Dettagliata</button>
								</div>
								<div id="collapse2" class="collapse"
									aria-labelledby="headingTwo" data-parent="#accordion">
									<div class="panel-body">
										<%-- 										<p><%=articolo.getDescrizioneDettagliata()%></p> --%>
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
							<h2>CARRELLO</h2>
							<div class="content">
								<ul>
									<%
										Carrello c = (Carrello) request.getAttribute("carrello");
									%>
									<li>Costo parziale<span>€</span><span id="spanu"><%=(double) request.getAttribute("costo")%></span></li>
									<%if(request.getAttribute("prime")==null){ 	System.out.println("if");%>
									<li class="firs">(+) Spedizione <span>€</span><span
										id="spanuccio">0</span></li>
									<li class="last">Totale<span>€</span><span id="spanuccia"><%=(double) request.getAttribute("costo")%></span></li>
									<%}else{%>
									<li class="firs">(+) Spedizione <span>€</span><span
										>0</span></li>
									<li class="last">Totale<span>€</span><span ><%=(double) request.getAttribute("costo")%></span></li>
								<%} %>
								</ul>
							</div>
						</div>
						<!--/ End Order Widget -->
						<!-- Order Widget -->
						<div class="single-widget">
							<h2>Spedizione </h2>
							<div class="content"
								style="margin-left: 10px; margin-block-start: 2em;">
								<%-- <div class="checkbox">
									<%
										int i = 1;
										Vector<MetodoSpedizione> metodi = (Vector<MetodoSpedizione>) request.getAttribute("metodi");
										for (MetodoSpedizione metodo : metodi) {
									%>
									<label class="checkbox-inline" for="<%=i%>"><input
										name="updates" id="<%=i%>"
										value="<%=metodo.getIdMetodoSpedizione()%>" type="checkbox"><%=metodo.getTipo()%></label>

									<%
										i++;
										}
									%>
								</div> --%>
								<%
									int i = 1;
									Vector<MetodoSpedizione> metodi = (Vector<MetodoSpedizione>) request.getAttribute("metodi");
									for (MetodoSpedizione metodo : metodi) {
								%>
								<label class="container"><input type="radio"
									name="radio" <%if (i == 1) {%> checked="checked" <%}%>
									value="<%=metodo.getIdMetodoSpedizione()%>"> <%=metodo.getTipo()%></label>

								<%
									i++;
									}
								%>
							</div>
						</div>
						<!--/ End Order Widget -->
						<!-- Payment Method Widget -->
						<div class="single-widget payement">
							<div class="content">
								
							</div>
						</div>
						<!--/ End Payment Method Widget -->
						<!-- Button Widget -->
						<div class="single-widget get-button">
							<div class="content">
								<div class="button">
									<a href="/privato/cliente/ordine/CheckOut" class="btn">Acquista </a>
								</div>
							</div>
						</div>
						<!--/ End Button Widget -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/ End Checkout -->

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


	<script type="text/javascript" src="/js/checkbox.js" charset="UTF-8"></script>
</body>
</html>
