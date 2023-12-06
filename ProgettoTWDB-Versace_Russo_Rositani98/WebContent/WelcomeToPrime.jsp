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
<%@ page import="it.unirc.db.ecommerce.beans.ClientePrimeDAO"%>
<%@ page import="it.unirc.db.ecommerce.beans.ClientePrime"%>
<%@ page import="java.lang.Boolean"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Prime</title>
<link rel="icon" type="image/svg" href="/images/logo.svg">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<link rel="stylesheet" href="/css/Prime/WelcomeToPrime.css">
<link rel="stylesheet" href="/css/animated.css">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
	crossorigin="anonymous"></script>
	
	
	<!-- Bootstrap -->
<!-- <link rel="stylesheet" href="/css/bootstrap.css"> -->
<!-- Magnific Popup -->
<!-- <link rel="stylesheet" href="/css/magnific-popup.min.css"> -->
<!-- Font Awesome -->
<link rel="stylesheet" href="/css/font-awesome.css">
<!-- Fancybox -->
<!-- <link rel="stylesheet" href="/css/jquery.fancybox.min.css"> -->
<!-- Themify Icons -->
<link rel="stylesheet" href="/css/themify-icons.css">
<!-- Nice Select CSS -->
<link rel="stylesheet" href="/css/niceselect.css">
<!-- Animate CSS -->
<!-- <link rel="stylesheet" href="/css/animate.css"> -->
<!-- Flex Slider CSS -->
<!-- <link rel="stylesheet" href="/css/flex-slider.min.css"> -->
<!-- Owl Carousel -->
<!-- <link rel="stylesheet" href="/css/owl-carousel.css"> -->
<!-- Slicknav -->
<!-- <link rel="stylesheet" href="/css/slicknav.min.css"> -->

<!-- Eshop StyleSheet -->
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/responsive.css">
<link rel="stylesheet" href="/css/reset.css">
</head>

<body class="js">


	<%@include file="/WEB-INF/preload.jsp" %>
	<%@ include file="/WEB-INF/header-scriptlet.jsp"%>
	<%@include file="/WEB-INF/header.jsp" %>

	<!-- Devo controllare che abbia fatto il login -->
	<%
		/* session.setAttribute("elimina", false); */
		if (session.getAttribute("elimina") != null) {
	%>
	<h6
		style="text-align: center; color: whitesmoke; background-color: 212529; margin-bottom: 0;">
		Errore ma non siamo riusciti ad effettuare la sua eliminazione a Prime
		<br>Riprova tra qualche secondo
	</h6>
	<%
		}
		boolean visitatore = false;
		ClientePrime clientePrime = new ClientePrime();
		if (session.getAttribute("clientePrime") != null) {
			//Significa che si è appena iscritto
			clientePrime = (ClientePrime) session.getAttribute("clientePrime");
			//Lo invalido perchè non mi serve più
			ClienteDAO cDAO = new ClienteDAO();
			cliente = cDAO.get(new Cliente(clientePrime.getIdCliente()));
			System.out.println(cliente);
			System.out.println(clientePrime);
			//Lo elimino non mi serve più
			session.setAttribute("clientePrime", null);
		} else {//Pò essere che è già prime => Controllo che si prime se ha fatto il login altrimenti è solamente un visitatore
			if (session.getAttribute("idCliente") != null) {
				int idCliente = (int) session.getAttribute("idCliente");
				System.out.println(idCliente);
				Cliente cl = new Cliente(idCliente);
				ClientePrimeDAO cpDAO = new ClientePrimeDAO();
				if (cpDAO.isPrime(cl)) {
					System.out.println("E Prime");
					clientePrime = cpDAO.get(new ClientePrime(idCliente));
					ClienteDAO cDAO = new ClienteDAO();
					cliente = cDAO.get(cl);
					System.out.println(session.getAttribute("idCliente"));
					System.out.println(cliente);
				} else {
					System.out.println("Non Prime");
					//Se non è prime 
					visitatore = true;
				}
			} else {//Se non ha fatto il login è un visitatore
				visitatore = true;
			}
		}
		System.out.println(visitatore);
	%>
	<%
		if (visitatore == false) {
	%>
	<nav class=" navbar-dark bg-dark">
		<div class="nav_container">
			<div class="nav_item">
				<div class="nav_image">
					<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50"
						fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
							d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
                  </svg>
					<div class="profile_contenent">
						<div class="title">
							<h9>Prime</h9>
						</div>
						<h5>
							<%=cliente.getNome()%>
						</h5>
					</div>
				</div>
			</div>
			<div class="nav_item">
				<div class="nav_image">
					<div class="profile_contenent">
						<div class="row ">
							<div class="title">
								<h9>Piano Prime</h9>
							</div>
						</div>
						<div class="row">
							<%
								if (clientePrime.getMensile() == true) {
										out.write("<h6>Abbonamento mensile € 3.99 al mese</h6>");
									} else {
										out.write("<h6>Abbonamento annuale € 36 all anno</h6>");
									}
							%>
						</div>
					</div>
				</div>
			</div>
			<div class="nav_item">
				<div class="title">
					<h9>Data di scadenza</h9>
				</div>
				<h5>
					<%=clientePrime.getDataFineIscrizione()%>
				</h5>
			</div>
			<div class="nav_item btn_link d-grid">
				<a
					href="/privato/cliente/EliminaPrime?idPrime=<%=clientePrime.getIdCliente()%>"
					class="btn btn-dark" onclick="return conferma()">Disdici Prime</a>
			</div>
		</div>
		</div>
	</nav>
	<%
		}
	%>
	<!-- Row  -->
	<%
		if (visitatore == true) {
	%>
	<div class="btn_link d-grid">
		<a href="/RichiediIscrizionePrime" class="btn btn-dark">Iscriviti
			a Prime</a>
	</div>
	<%
		}
	%>
	<div class="center">
		<div>
			<span><p class="text-center" style="font-weight: bold;">
					Scopri tutti i vantaggi di Prime:</p></span>
		</div>
	</div>

	<!-- Carousel -->

	<div id="carousel" class="carousel slide" data-bs-ride="carousel">
		<ol class="carousel-indicators">
			<li data-bs-target="#carousel" data-bs-slide-to="0" class="active"></li>
			<li data-bs-target="#carousel" data-bs-slide-to="1"></li>
			<li data-bs-target="#carousel" data-bs-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active height"
				style="background-image: url(https://www.ragusaoggi.it/wp-content/uploads/2018/09/spedizione-web-corriere-pacco-pacchi.jpg);">
				<div class="carousel-caption  animated  bounceInDown">
					<h5>Spedizioni gratis</h5>
					<p>Consegne veloci illimitate, in 1 giorno su 2 milioni di
						prodotti e in 2-3 giorni su molti altri milioni</p>
				</div>
			</div>
			<div class="carousel-item height"
				style="background-image: url(https://i.pinimg.com/originals/0e/0f/1e/0e0f1ee5417d3c31a7ea06a4f1b3e5d9.jpg);">
				<div class="carousel-caption  animated  bounceInDown">
					<h5>Guarda film e serie TV</h5>
					<p>Gli iscritti ad Amazon Prime hanno accesso esclusivo e senza
						costi aggiuntivi a una raccolta di migliaia di film e serie TV.</p>
				</div>
			</div>
			<div class="carousel-item height"
				style="background-image: url(https://images.squarespace-cdn.com/content/v1/5b37cffe4cde7a161b9bf226/1531442181257-0X77A9TQTTR4QQBE1SPX/ke17ZwdGBToddI8pDm48kNvT88LknE-K9M4pGNO0Iqd7gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z5QPOohDIaIeljMHgDF5CVlOqpeNLcJ80NK65_fV7S1USOFn4xF8vTWDNAUBm5ducQhX-V3oVjSmr829Rco4W2Uo49ZdOtO_QXox0_W7i2zEA/dj.jpg?format=2500w);">
				<div class="carousel-caption animated  bounceInDown">
					<h5>Più di 2 milioni di brani e centinaia di playlist senza
						pubblicità</h5>
					<p>Ascolta più di 2 milioni di brani, e centinaia di playlist
						senza pubblicità . Riproduci i tuoi brani preferiti su tutti i
						tuoi dispositivi, scarica le canzoni per ascoltare anche offline.</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carousel" role="button"
			data-bs-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
		</a> <a class="carousel-control-next" href="#carousel" role="button"
			data-bs-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="visually-hidden">Next</span>
		</a>
	</div>
</body>

<script type="text/javascript">
	function conferma() {
		return confirm("Sicuro di voler terminare l'iscrizione a Prime?");
	}
</script>

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


</html>