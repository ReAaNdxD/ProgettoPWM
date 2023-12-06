<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.ClienteDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@ page import="it.unirc.db.ecommerce.beans.IndirizzoSpedizione"%>
<!DOCTYPE html>
<html>
<head>

<title>Indirizzi Di Spedizione</title>
<%@include file="/WEB-INF/head.jsp" %>
<!-- <script -->
<!-- 	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" -->
<!-- 	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" -->
<!-- 	crossorigin="anonymous"></script> -->
</head>
<body>

	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>
 
 	<%@include file="/WEB-INF/header.jsp"%>
	
	<%
		/*Non devo andare a controllare se si è autenticato in quanto ho messo questa jsp dentro WEB-INF => Non è visibile direttamente dal browser */
		if (request.getAttribute("indirizzi") == null) {
			out.write("Errore generico");
			return;
		}
		if (session.getAttribute("eliminazioneIS") != null) {
			//Non è riuscito ad effettuare la rimozione
			session.setAttribute("eliminazioneIS", null);
	%>

	<div>
		<h4 style="color: red; font-family: cursive;">ERRORE non siamo
			riusciti a rimuovere l'indirizzo :-(</h4>
	</div>
	<%
		}
		Vector<IndirizzoSpedizione> indirizzi = (Vector<IndirizzoSpedizione>) request.getAttribute("indirizzi");
	%>
	
	
	<!-- Shopping Cart Section -->

	<div class="shopping-cart section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
						<thead>
							<tr class="main-hading">
								<th class="text-center">Regione</th>
								<th class="text-center">Provincia</th>
								<th class="text-center">Città</th>
								<th class="text-center">Via</th>
								<th class="text-center">Numero Civico</th>
								<th class="text-center">CAP</th>
								<th class="text-center">Telefono</th>
								<th class="text-center"></th>
								<th class="text-center"></th>
							</tr>
						</thead>
						<tbody>
							<%
								for (IndirizzoSpedizione i : indirizzi) {
							%>
							<tr>
								<td style="text-align: center;" data-title="Regione"><%=i.getRegione()%></td>
								<td style="text-align: center;" data-title="Provincia"><%=i.getProvincia()%></td>
								<td style="text-align: center;" data-title="Città"><%=i.getCitta()%></td>
								<td style="text-align: center;" data-title="Via"><%=i.getVia()%></td>
								<td style="text-align: center;" data-title="NCivico"><%=i.getNCivico()%></td>
								<td style="text-align: center;" data-title="CAP"><%=i.getCap()%></td>
								<td style="text-align: center;" data-title="Telefono"><%=i.getTelefono()%></td>
								<td>
									<div class="button">
										<a
											href="/privato/cliente/indirizzospedizione/RimuoviIndirizzoSpedizione?id=<%=i.getIdIndirizzoSpedizione()%>"><button
												type="button" class="btn btn-primary "
												onclick="return confermaRimozione()">Rimuovi</button></a>
									</div>
								</td>


								<td><div class="button">
										<a
											href="/privato/cliente/indirizzospedizione/RichiediModificaIndirizzoSpedizione?id=<%=i.getIdIndirizzoSpedizione()%>"><button
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
				href="/privato/cliente/indirizzospedizione/RichiediAggiungiIndirizzoSpedizione"
				class="btn btn-primary"> Crea un nuovo indirizzo
				spedizione </a>
		</div>
	</div>
	
	<script type="text/javascript"
		src="/js/IndirizzoSpedizione/funzioniIS.js">
	</script>
	
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
	<script src="/js/active.js"></script>
	<script src="/js/myscript.js"></script>

</body>
</html>