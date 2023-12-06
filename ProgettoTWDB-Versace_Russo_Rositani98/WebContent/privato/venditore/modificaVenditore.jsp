<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.unirc.db.ecommerce.beans.Venditore"%>
<%@page import="it.unirc.db.ecommerce.beans.VenditoreDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/svg" href="/images/logo.svg">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica il tuo account</title>
<!-- Web Font -->
<script language="javascript" type="text/javascript"
	src="/js/funzioni2.js"></script>
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
							Venditore venditore = new Venditore();
							if (request.getAttribute("venditore") != null) {
								venditore = (Venditore) request.getAttribute("venditore");
							} else {
								VenditoreDAO venditoreDAO = new VenditoreDAO();
								venditore = venditoreDAO.get(new Venditore(1));
							}
							if (request.getAttribute("errore") != null) {
							%>
							<h4>Uno o più campi sbagliati</h4>
							<%
							request.setAttribute("errore", null);
							}
							if (request.getAttribute("erroreEmail") != null) {
							request.setAttribute("erroreEmail", null);
							%>
							<h4>Non puoi modificare l'email</h4>
							<%
							}
							%>
							<h3>Modifica Il Tuo Account</h3>
						</div>
						<form class="form" action="ModificaVenditore" method="post">
							<div class="row">
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label>Email<span>*</span></label><input type="text"
											name="email" readonly="readonly"
											value="<%=venditore.getEmail()%>" required>
									</div>
								</div>
								<div class="col-lg-6 col-12">
									<div class="form-group">
										<label>Password<span>*</span></label> <input id="password"
											type="password" name="password" maxlength="45" required
											value="<%=venditore.getPassword()%>">
									</div>
								</div>
								<div class="col-12">
									<div class="form-group message">
										<label>Nome Azienda<span>*</span></label> <input type="text"
											name="nomeAzienda" maxlength="45" required
											value="<%=venditore.getNomeAzienda()%>"></label>
									</div>
								</div>
								<div class="col-12">
									<div class="form-group message">
										<label>Partita Iva<span>*</span></label> <input type="text"
											name="pIVA" maxlength="11" required
											value="<%=venditore.getPIVA()%>"
											onkeydown="return onlyNumbers(event)"
											onkeyup="return onlyNumbers(event)"
											onkeypress="return onlyNumbers(event)">
									</div>
								</div>
								<div class="col-12">
									<div class="form-group button">
										<button type="submit" class="btn ">Modifica il tuo
											account</button>
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
	<!-- Nice Select JS -->
	<script src="/js/nicesellect.js"></script>
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