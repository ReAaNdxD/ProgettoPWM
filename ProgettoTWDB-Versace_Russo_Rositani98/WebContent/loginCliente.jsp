<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>



<!-- Bootstrap -->
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
<link rel="stylesheet" href="/css/AggiungiCartaCredito.css">
<link rel="stylesheet" href="/css/bootstrap.css">

</head>
<body>

	<section id="contact-us" class="contact-us section">
		<div class="container" style="max-width: 600px">
			<div class="contact-head">
				<div class="row">
					<div class="col-lg-12 col-12">
						<div class="form-main">
							<div class="title text-center">
								<h3 style="color: #f7941d; font-size: 30px;">Accedi</h3>
							</div>
							<%
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
							if (request.getAttribute("email") != null) {
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
							if (request.getAttribute("login") != null) {
							//Non è registrato
							%><div class="error-page">
								<div class="error-inner">
									<h5 class="h5" style="color: #F7941D">
										<%
										out.append("Non esiste nessun account con i dati inseriti");
										%>
									</h5>
								</div>
							</div>
							<%
							}
							%>
							<s:form id="form" class="form form-signin" method="post"
								action="/Login">

								<div class="row">
									<div class=" col-12">
										<div class="form-group">
											<label for="email" class="font-weight-bold"
												style="font-size: 20px;">Email</label>
												<s:set var="emailClass" value="form-control form-group" />
											<s:textfield name="cliente.email" id="email"
												placeholder="Email" type="text" class="%{#emailClass}">
											</s:textfield>
											<!-- small element serve per mostrare un messaggio d'errore nel caso 
					in cui gli input siano errati -->
											<small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label for="password" class="font-weight-bold"
												style="font-size: 20px;">Password</label>
												<s:set var="emailClass" value="form-control form-group" />
											<s:password id="password" placeholder="Password"
												type="password" name="cliente.password" class="%{#emailClass}">
												
											</s:password>
											<small></small>
										</div>
									</div>
									<div class="col-12">
										<div class="form-check text-center">
											<input id="checkbox1" type="checkbox" name="checkbox1">
											<label for="checkbox1">Remember me</label>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group button" style="text-align: center">
											<button class="btn" type="submit" style="width: 100%;">Login</button>
										</div>
									</div>
								</div>
							</s:form>
							<div class="container">
								<div class="text-center" style="margin-top: 20px">
									<a href="/RegistraNuovoCliente">Sei nuovo su DAG?
										Registrati</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>




</body>
</html>