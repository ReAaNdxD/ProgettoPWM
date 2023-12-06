<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.Compone"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.ClienteDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Il mio account</title>
<%@include file="/WEB-INF/head.jsp" %>
<link rel="stylesheet" href="/css/AggiungiCartaCredito.css">
</head>
<body class="js">

	<!-- Product Style -->

	<%
		if (session.getAttribute("idCliente") == null) {
			System.out.println("Sono qui");
			response.sendRedirect("/SigninCliente");
			return;
		}
		int id = (int) session.getAttribute("idCliente");
		ClienteDAO cDAO = new ClienteDAO();
		Cliente c = cDAO.get(new Cliente(id));
	%>
	
	
	<%@include file="/WEB-INF/preload.jsp" %>
	<%@ include file="/WEB-INF/header-scriptlet.jsp"%>
	<%@include file="/WEB-INF/header.jsp" %>

	<section class="product-area shop-sidebar shop section">
		<div class="container">
			<div class="row account">
				<div class="col-lg-3 col-md-4 col-4">
					<div class="shop-sidebar ">
						<!-- Single Widget -->
						<div class="single-widget category account">
							<h3 class="title">My account</h3>
							<ul class="categor-list">
								<li><a href="/privato/cliente/RichiediModificaCliente">Modifica
										Account</a></li>
								<li><a
									href="/privato/cliente/indirizzospedizione/VisualizzaIndirizziSpedizione">Visualizza
										i tuoi indirizzi</a></li>
								<li><a
									href="/privato/cliente/cartacredito/VisualizzaCarteCredito">Visualizza
										le tue carte di credito</a></li>
								<li><a href="/GoToPrime">Prime</a></li>
								<li><a href="/privato/cliente/ordine/VisualizzaOrdini">Visualizza i tuoi ordini</a></li>
								<li><a href="/Logout">Logout</a></li>
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
								<h3 style="color: #f7941d;">
									My Profile
									<svg xmlns="http://www.w3.org/2000/svg" width="20" height="16"
										fill="currentColor" class="bi bi-person-check-fill"
										viewBox="0 0 16 16">
                  <path fill-rule="evenodd"
											d="M15.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
                  <path
											d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                </svg>
								</h3>
							</div>
							<form class="form" method="post" action="mail/mail.php">
								<div class="row">
									<div class="col-12">
										<div class="form-group">
											<%-- <%
												Venditore venditore = new Venditore();
												VenditoreDAO venditoreDAO = new VenditoreDAO();
												if (session.getAttribute("Admin") != null) {
													venditore = venditoreDAO.get(new Venditore(1));
												} else if (session.getAttribute("Venditore") != null) {
													venditore = (Venditore) session.getAttribute("Venditore");
												}
											%> --%>
											<label class="font-weight-bold">Nome</label> <input
												name="name" type="text" placeholder=""
												value="<%=c.getNome()%>" readonly="readonly">
										</div>
									</div>
									<div class=" col-12">
										<div class="form-group">
											<label class="font-weight-bold">Cognome</label> <input
												name="subject" type="text" placeholder=""
												value="<%=c.getCognome()%>" readonly="readonly">
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label class="font-weight-bold">Data di nascita</label> <input
												name="email" type="date" placeholder=""
												value="<%=c.getDataNascita()%>" readonly="readonly">
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label class="font-weight-bold">Email</label> <input
												name="email" type="email" placeholder=""
												value="<%=c.getEmail()%>" readonly="readonly">
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
	
	
		<%@ include file="/WEB-INF/footer.jsp" %>

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
    <!-- Waypoints JS -->
    <script src="/js/waypoints.min.js"></script>
    <!-- Countdown JS -->
    <script src="/js/finalcountdown.min.js"></script>
    <!-- Nice Select JS -->
    <script src="/js/nicesellect.js"></script>
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


</body>
</html>