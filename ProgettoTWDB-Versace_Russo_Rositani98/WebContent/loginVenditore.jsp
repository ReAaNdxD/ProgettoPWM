<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Venditore</title>
<%@include file="/WEB-INF/head.jsp" %>
</head>
<body>
<%-- <%@include file="/WEB-INF/header.jsp" %> --%>
	<section id="contact-us" class="contact-us section">
		<div class="container" style="max-width: 600px">
			<div class="contact-head">
				<div class="row center">
					<div class="col-lg-12 col-12">
						<div class="form-main">
							<div>
								<h3 style="color: #f7941d; font-size: 30px;">Accedi</h3>
							</div>
							<%
							if(session.getAttribute("idCliente")!=null){
								response.sendRedirect("/");
								return;
							} else if(session.getAttribute("Venditore")!=null){
								response.sendRedirect("/privato/prodotto/VisualizzaProdottiInVendita");
								return;
							}
								
							if (request.getAttribute("errore") != null) {
						%>
							<div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D;">
										<%
										out.append("Alcuni campi sono sbagliati :-(");
									%>
									</h5>
								</div>
							</div>
							<%
							}
							if (request.getAttribute("erroreEmail") != null) {
								//L'email già esiste
						%><div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
										out.append("Email non valida");
									%>
									</h5>
								</div>
							</div>
							<%
							}
							if (request.getAttribute("password") != null) {
								//Non è riuscito a salvarlo
						%><div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
										out.append("Password non valida");
									%>
									</h5>
								</div>
							</div>
							<%
							}
						%>
							<form class="form" method="post" class="form-signin"
								action="/LoginVenditore">
								<div class="row">
									<div class=" col-12">
										<div class="form-group">
											<label for="email" class="font-weight-bold"
												style="font-size: 20px;">Email</label> <input id="email"
												placeholder="Email" type="text" name="email"
												value="<%Cookie[] cookies= request.getCookies();
																if(cookies!=null){
																	for(Cookie cookie: cookies){
																		if(cookie.getName().equals("emailDAGVenditore")){
																			out.write(cookie.getValue());
																		}
																	}
																} %>">
											<!-- small element serve per mostrare un messaggio d'errore nel caso 
												in cui gli input siano errati -->

											<small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label for="password" class="font-weight-bold"
												style="font-size: 20px;">Password</label> <input
												id="password" placeholder="Password" type="password" name="password"
												title="La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale"><small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-check text-center">
											<input id="checkbox1" type="checkbox" name="checkbox1"> <label
												for="checkbox1">Remember me</label>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button" style="text-align: center">
											<button class="btn" type="submit" style="width: 100%;">Login</button>
										</div>

									</div>
								</div>
							</form>
							<div class="container">
								<div class="text-center" style="margin-top: 20px">
									<a href="/RegistraNuovoVenditore">Sei un nuovo Venditore su DAG?
										Registrati</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<script type="text/javascript" charset="UTF-8"
		src="/js/registration.js"></script>
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