<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.beans.CartaCreditoDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.CartaCredito"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualizza Carte Di Credito</title>
<%@include file="/WEB-INF/head.jsp" %>

</head>
<body>

	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>
	<%
		/* Vector<CartaCredito> carte = null; /*         if (request.getAttribute("carte") == null) {
		out.write("Errore generico");
		return;
		} else { */
		/*
		carte = (Vector<CartaCredito>)	request.getAttribute("carte"); */
		if (session.getAttribute("idCliente") == null) {
			response.sendRedirect("/SigninCliente");
			return;
		}
		if (request.getAttribute("carte") == null) {
			out.write("Errore generico...riprova più tardi");
			return;
		}
		Vector<CartaCredito> carte = (Vector<CartaCredito>) request.getAttribute("carte");
	%>
	
	<%@include file="/WEB-INF/header.jsp"%>





	<%-- <table style="color: grey">
		<tr>
			<td style="text-align: center;"><strong>Numero Carta</strong></td>
			<td style="text-align: center;"><strong>Data Di
					Scadenza</strong></td>
			<td style="text-align: center;"><strong>Intestatario</strong></td>
		</tr>
		<%
			for (CartaCredito c : carte) {
		%>
		<tr>
			<td style="text-align: center;"><%=c.getNumeroCarta()%></td>
			<td style="text-align: center;"><%=c.getDataScadenza()%></td>
			<td style="text-align: center;"><%=c.getIntestatario()%></td>

			<td><a
				href="/privato/studente/RichiediModificaCarta?id=<%=c.getIdCartaCredito()%>"><button
						type="button">Modifica</button></a></td>

			<!--  
				<td><form name="rimuovi"
						action="/privato/studente/RimuoviStudente" method="get">
						<input type="hidden" name="matricola"
							value="<%=s.getMatricola()%>"> <input type="submit"
							value="Rimuovi">
					</form></td>
 -->
			<td><a
				href="/privato/cliente/cartacredito/RimuoviCartaCredito?id=<%=c.getIdCartaCredito()%>"><button
						type="button" onclick="return confermaRimozione()">Rimuovi</button></a></td>
			<td><a
				href="/privato/cliente/cartacredito/RichiediModificaCartaCredito?id=<%=c.getIdCartaCredito()%>"><button
						type="button">Modifica</button></a></td>
		</tr>
		<%
			}
		%>
 --%>


<!-- 
	</table>
 -->



	<!-- Shopping Cart Section -->

	<div class="shopping-cart section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
						<thead>
							<tr class="main-hading">
								<th class="text-center">NUMERO CARTA</th>
								<th class="text-center">DATA DI SCADENZA</th>
								<th class="text-center">INTESTATARIO</th>
								<th class="text-center"></th>
								<th class="text-center"></th>
							</tr>
						</thead>
						<tbody>
							<%
								for (CartaCredito c : carte) {
							%>
							<tr>
								<td style="text-align: center;" data-title="NUMERO CARTA"><%=c.getNumeroCarta()%></td>
								<td style="text-align: center;" data-title="DATA DI SCADENZA"><%=c.getDataScadenza()%></td>
								<td style="text-align: center;" data-title="INTESTATARIO"><%=c.getIntestatario()%></td>
								<td>
									<div class="button">
										<a
											href="/privato/cliente/cartacredito/RimuoviCartaCredito?id=<%=c.getIdCartaCredito()%>"><button
												type="button" class="btn btn-primary "
												onclick="return confermaRimozione()">Rimuovi</button></a>
									</div>
								</td>


								<td><div class="button">
										<a
											href="/privato/cliente/cartacredito/RichiediModificaCartaCredito?id=<%=c.getIdCartaCredito()%>"><button
												type="button" class="btn btn-primary ">Modifica</button></a>
									</div></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					
					<!--/ End Shopping Summery -->
				</div>
			</div>
		</div>
		
		<br>
		<div align="center">
			<a type="button"
				href="/privato/cliente/cartacredito/RichiediAggiungiCartaCredito"
				class="btn btn-primary">Aggiungi carta</a>
		</div>
	</div>
	
	<%@include file="/WEB-INF/footer.jsp"%>
	<%-- 	<%@include file="/WEB-INF/js.jsp"%> --%>

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
	<script src="/js/CartaCredito/funzioniVisualizzaCC.js"></script>
	<script src="/js/active.js"></script>
	<script src="/js/myscript.js"></script>


</body>
</html>