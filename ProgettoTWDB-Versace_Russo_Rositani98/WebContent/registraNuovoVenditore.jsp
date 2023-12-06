<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Crea il tuo account da venditore</title>

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
<link rel="stylesheet" href="/css/responsive.css">
<link rel="stylesheet" href="/css/style.css">

</head>
<body>

<section id="contact-us" class="contact-us section">
		<div class="container">
			<div class="contact-head">
				<div class="row center">
					<div class="col-lg-8 col-12">
						<div class="form-main">
							<div class="title">
								<%
								if (request.getAttribute("erroreNome") != null) {
								%>
									<h4>Nome azienda vuoto</h4>
								<%
								request.setAttribute("erroreNome", null);
								}
								if (request.getAttribute("email") != null) {
									request.setAttribute("email", null);
								%>
									<h4>Email già esistente</h4>
								<%
									} 
								if(request.getAttribute("salva") != null) {
										request.setAttribute("salva", null);
								%>
										<h4>Ops, non siamo riusciti a creare l'account</h4>
								<%}
								if (request.getAttribute("emailErrata") != null) {
										request.setAttribute("emailErrata", null); %>
										<h4>Email non valida</h4>
									<%}
								if (request.getAttribute("errorePassword") != null) {
										request.setAttribute("emailPassword", null);%>
										<h4>La password non soddisfa i requisiti</h4>
									<%}
								if (request.getAttribute("errorePasswordVerifica") != null) {
										request.setAttribute("emailPasswordVerifica", null); %>
										<h4>La password non coincide</h4>
								<%}
								if (request.getAttribute("errorePIVA") != null) {
										request.setAttribute("errorePIVA", null); %>
										<h4>La p.Iva è sbagliata</h4>
									<%} %>
								<h3>Crea Il Tuo Account Venditore</h3>
							</div>
							<form class="form" action="CreaNuovoVenditore" method="post">
								<div class="row">
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label>Email<span>*</span></label><input type="email" name="email"
											 required>
										</div>
									</div>
									
									<div class="col-lg-6 col-12">
										<div class="form-group message">
											<label>Nome Azienda<span>*</span></label>
											<input type="text" name="nomeAzienda" maxlength="45"
												required ></label>
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group message">
											<label>Partita Iva<span>*</span></label>
											<input type="text" name="pIVA" maxlength="11"
													required onkeydown="return onlyNumbers(event)"
													 onkeyup="return onlyNumbers(event)" onkeypress="return onlyNumbers(event)">
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="password">Password<span>*</span></label>
											<input id="password" type="password"
												title="La password deve essere tra 8 e 15 caratteri e deve contenere almeno una lettera maiuscola, una minuscola e un carattere speciale"
												placeholder="Password" name="password"> 
										</div>
									</div>
									<div class="col-lg-6 col-12">
										<div class="form-group">
											<label for="verificaPassword">Verifica
												Password<span>*</span></label> <input id="verificaPassword"
												placeholder="Riscrivi Password" type="password"
												name="verificaPassword"> <small>La password
												deve essere tra 8 e 15 caratteri e deve contenere almeno una
												lettera maiuscola una minuscola e un carattere speciale
											</small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button">
											<button type="submit" class="btn ">Crea il tuo account</button>
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
</body>


<script language="javascript" type="text/javascript" src="/js/funzioni2.js"></script>
</html>