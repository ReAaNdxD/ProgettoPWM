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
<%@page import="it.unirc.db.ecommerce.views.Order"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.views.ViewOrderProducts"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualizza Articoli Di Un Ordine</title>
<%@include file="/WEB-INF/head.jsp" %>

</head>
<body class="js">


	<%
		if (request.getAttribute("prodotti") == null) {
			out.write("Errore");
			return;
		}
		Vector<ViewOrderProducts> prodotti = (Vector<ViewOrderProducts>) request.getAttribute("prodotti");
	%>
	
	
	<%@include file="/WEB-INF/preload.jsp" %>
	<%@ include file="/WEB-INF/header-scriptlet.jsp"%>
	<%@include file="/WEB-INF/header.jsp" %>


	<div class="shopping-cart section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
						<thead>
							<tr class="main-hading">
								<th class="text-center">NOME PRODOTTO</th>
								<th class="text-center">MARCA</th>
								<th class="text-center">NOME AZIENDA</th>
								<th class="text-center">PREZZO ACQUISTO</th>
								<th class="text-center">QUANTITA</th>
								<th class="text-center">COSTO TOTALE</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (ViewOrderProducts prodotto : prodotti) {
							%>
							<tr>
								<td style="text-align: center;" data-title="NOME PRODOTTO"><a
									href="/ProductDetails?id=<%=prodotto.getIdArticolo()%>"><%=prodotto.getNomeProdotto()%></a></td>
								<td style="text-align: center;" data-title="MARCA"><%=prodotto.getMarca()%></td>
								<td style="text-align: center;" data-title="NOME AZIENDA"><%=prodotto.getNomeAzienda()%></td>
								<td style="text-align: center;" data-title="PREZZO ACQUISTO">€<%=prodotto.getPrezzoAcquisto()%></td>
								<td style="text-align: center;" data-title="QUANTITA"><%=prodotto.getQuantita()%></td>
								<td style="text-align: center;" data-title="COSTO TOTALE">€<%=prodotto.getCostoTot()%></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/footer.jsp" %>

	<style>
a:hover {
	color: #0056b3 !important;
}
</style>

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