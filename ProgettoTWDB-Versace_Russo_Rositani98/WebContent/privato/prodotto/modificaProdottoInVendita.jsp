<%@page import="it.unirc.db.ecommerce.beans.Articolo"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script language="javascript" type="text/javascript"
	src="/js/funzioni2.js"></script>
<meta charset="UTF-8">
<title>Modifica prodotto in vendita</title>
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
	
	<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row center">
					<div class="col-lg-8 col-12">
						<div class="form-main">
							<div class="title">
								<%
								Articolo articolo =(Articolo) session.getAttribute("Articolo");
								Prodotto prodotto = (Prodotto)request.getAttribute("prodotto");
								Sottocategoria sottocategoria = (Sottocategoria)request.getAttribute("sottocategoria");
								Categoria categoria = (Categoria)request.getAttribute("categoria");
								if (request.getAttribute("errore") != null) {
								%>
								<h4>Uno o più campi errati</h4>
								<%
								} else if (request.getAttribute("presente") != null) {
								%>
								<h4>Il prodotto è gia stato venduto</h4>
								<%
								}
								%>
								<h3>Modifica il prezzo del tuo prodotto</h3>
							</div>
							<form class="form" action="ModificaProdottoInVendita"
								method="post">
								<div class="row center">
									<div class="col-lg-6  col-12">
										<div class="form-group">
											<label>Categoria <span>*</span></label><input type="text"
												id="categoria" name="categoria"
												placeholder="<%=categoria.getNome()%>" readonly="readonly">
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Sottocategoria<span>*</span></label> <input
												type="text" id="categoria" name="categoria"
												placeholder="<%=sottocategoria.getNome()%>"
												readonly="readonly">
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Prodotto<span>*</span></label><input type="text"
												readonly="readonly" id="prodotto" name="prodotto"
												placeholder="<%=prodotto.getNome()%>">
										</div>
									</div>

									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Prezzo<span>*</span></label> <input type="text"
												name="prezzo" id="prezzo" required
												placeholder="<%=articolo.getPrezzo()%>"
												onkeypress="return isNumberKey(this, event);" autocomplete="off">
										</div>
									</div>

									<div class="col-12">
										<div class="form-group button">
											<button type="submit" class="btn ">Modifica prodotto</button>
											<button type="reset" class="btn ">Annulla</button>

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