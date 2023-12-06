<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Venditore"%>
<%@page import="it.unirc.db.ecommerce.beans.VenditoreDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Il mio account</title>
<link rel="icon" type="image/svg" href="/images/logo.svg">
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
<link rel="stylesheet" href="/css/responsive.css">
<link rel="stylesheet" href="/css/mystyle.css">
</head>
<body>
<%int item=0; %>
<%@include file="/WEB-INF/checkVenditore.jsp" %>
<%@include file="/WEB-INF/privato/venditore/header.jsp" %>
	<!-- Product Style -->
	<section class="product-area shop-sidebar shop section">
		<div class="container-fluid">
			<div class="row account">
				<div class="col-lg-3 col-md-4 col-4">
					<div class="shop-sidebar ">
						<!-- Single Widget -->
						<div class="single-widget category account">
							<h3 class="title">My account</h3>
							<ul class="categor-list">
								<li><a href="RichiediModificaVenditore">Modifica Account</a></li>
								<li><a href="/privato/prodotto/VisualizzaProdottiInVendita">Prodotti in vendita</a></li>
								<li><a href="/privato/prodotto/RichiediAggiungiProdottoInVendita">Aggiungi prodotto in vendita </a></li>
								<li><a href="/privato/prodotto/RichiediAggiungiProdotto">Aggiungi prodotto</a></li>
								<li><a href="/privato/prodotto/VisualizzaProdotto">Visualizza prodotti</a></li>
								<%
								if (session.getAttribute("Admin") != null) {
								%>
								<li><a href="/privato/prodotto/RichiediCreaCategoria">Crea Categoria</a></li>
								<li><a href="/privato/prodotto/RichiediCreaSottocategoria">Crea Sottocategoria</a></li>
								<%
								}
								%>
								<li><a href="/Logout">Esci</a></li>
							</ul>
						</div>
						<!--/ End Single Widget -->

					</div>
				</div>

				<!--/ End Product Style 1  -->

				<div class="col-lg-8 col-8 contact-us">
					<div class="contact-head">
						<div class="form-main">
							<div class="title text-center">
								<h3 style="color:#f7941d;">My Profile <svg xmlns="http://www.w3.org/2000/svg" width="20" height="16" fill="currentColor" class="bi bi-person-check-fill" viewBox="0 0 16 16">
  								<path fill-rule="evenodd" d="M15.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
  								<path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								</svg>
								</h3>
							</div>
							<form class="form" method="post" action="mail/mail.php">
								<div class="row">
									<div class="col-12">
										<div class="form-group">
											<%
											Venditore venditore = new Venditore();
											if (session.getAttribute("Admin") != null) {
												venditore = (Venditore)request.getAttribute("seller");
											} else if (session.getAttribute("Venditore") != null) {
												venditore = (Venditore) session.getAttribute("Venditore");
											}
											%>
											<label class="font-weight-bold">Nome azienda</label> <input
												name="name" type="text" placeholder=""
												value="<%=venditore.getNomeAzienda()%>" readonly="readonly">
										</div>
									</div>
									<div class=" col-12">
										<div class="form-group">
											<label class="font-weight-bold">Partita Iva</label> <input
												name="subject" type="text" placeholder=""
												value="<%=venditore.getPIVA()%>" readonly="readonly">
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label class="font-weight-bold">Email</label> <input
												name="email" type="email" placeholder=""
												value="<%=venditore.getEmail()%>" readonly="readonly">
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
		<%@include file="/WEB-INF/footer.jsp" %>
	
	<!--/ End Contact -->
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
	<!-- Ytplayer JS -->
	<script src="/js/ytplayer.min.js"></script>
	<!-- Flex Slider JS -->
	<script src="js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script src="/js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script src="/js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script src="/js/easing.js"></script>
	<!-- Active JS -->
	<script src="/js/active.js"></script>
</body>
</html>