<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.Compone"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.views.Order"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualizza Ordini</title>
<%@include file="/WEB-INF/head.jsp"%>
</head>
<body>
</head>
<body class="js">


	<%
		if (request.getAttribute("ordini") == null) {
			out.write("Errore");
			return;
		}
		Vector<Order> ordini = (Vector<Order>) request.getAttribute("ordini");
	%>

	<%@include file="/WEB-INF/preload.jsp"%>
	<%@ include file="/WEB-INF/header-scriptlet.jsp"%>
	<%@include file="/WEB-INF/header.jsp"%>


	<div class="shopping-cart section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
						<thead>
							<tr class="main-hading">
								<%
									if (ordini == null || ordini.size() == 0) {
										System.out.println("Dentro if ordini");
								%>
								<th><span>Non hai ordini</span></th>
							</tr>
						</thead>
					</table>
					<%
						} else {
							System.out.println("Dentro else ordini");
					%>
						<th class="text-center">NUMERO ORDINE</th>
						<th class="text-center">COSTO</th>
						<th class="text-center">DATA</th>
						<th class="text-center">SPEDIZIONE</th>
						<th class="text-center">STATO</th>
						<th class="text-center"></th>
					<%
						}
					%>
					</tr>
					</thead>
					<tbody>
						<%
							for (Order o : ordini) {
						%>
						<tr>
							<td style="text-align: center;" data-title="NUMERO ORDINE"><%=o.getNumOrd()%></td>
							<td style="text-align: center;" data-title="COSTO">€<%=o.getCostTot()%></td>
							<td style="text-align: center;" data-title="DATA"><%=o.getData()%></td>
							<td style="text-align: center;" data-title="SPEDIZIONE"><%=o.getTipo()%></td>
							<td style="text-align: center;" data-title="STATO"><%=o.getStato()%></td>
							<td>
								<div class="button">
									<a
										href="/privato/cliente/ordine/VisualizzaArticoliOrdine?ord=<%=o.getNumOrd()%>"><button
											type="button" class="btn btn-primary ">Vedi Articoli</button></a>
								</div>
							</td>
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
	</div>

	<%@ include file="/WEB-INF/footer.jsp"%>

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