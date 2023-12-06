<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
	<%@include file="/WEB-INF/head.jsp" %>
</head>
<body class="js">


	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>
	
	
	<%
		if(request.getAttribute("viewProducts")!=null)
			articoliCarrello = (Vector<ViewProduct>) request.getAttribute("viewProducts");
	%>

	<%@include file="/WEB-INF/header.jsp"%>
			
	<!-- Shopping Cart -->
	<div class="shopping-cart section">
		<div class="container">
		<%
			if(session.getAttribute("msg")!=null&&session.getAttribute("success")!=null){
				System.out.println("Entrato");
// 				response.sendRedirect("Cart");
				if((Boolean)session.getAttribute("success")){
					
			
		%>
		
		
			<div id="message" class="alert alert-success alert-dismissible"><%=((String)session.getAttribute("msg")) %></div>
		
		<%		
				}else{
		%>
			<div id="message" class="alert alert-danger alert-dismissible"><%=((String)session.getAttribute("msg")) %></div>
		<%
				}
				
				session.removeAttribute("msg");
				session.removeAttribute("success");
			} 
		%>
			<div class="row">
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
					
						<thead>
							<tr class="main-hading">
							
							<%
								int qtaT=0;
								if(articoliCarrello==null||articoliCarrello.size()==0){
							%>
									<th><span>Carrello vuoto</span></th>
									</tr>
									</thead>
									</table>
							<%
								}else {
									ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
							%>
								<th><span>Carrello</span></th>
							</tr>
						</thead>
						<form id="inc-btn" action="/privato/cliente/carrello/AddRemove"></form>
						<tbody>
							<%  int cont = 1;
								for(ViewProduct product : articoliCarrello){
							%>
							<tr>
								<td class="pdg-30 image center" data-title="No"><img src="https://via.placeholder.com/100x100" alt="#"></td>
								<td class="product-des" data-title="Description">
									<p class="product-name"><a href="/ProductDetails?id=<%=product.getIdArticolo()%>"><%= product.getNomeProdotto() %></a></p>
									<p class="product-des">Disponibilità : <%= product.getDisponibilita() %></p>
								</td>
								<td class="pdg-30 price" data-title="Price"><span><%=product.getPrezzoAcquisto() %>€ </span></td>
								<td class="pdg-30 qty" data-title="Qty"><!-- Input Order -->
									<div class="input-group">
										<div class="button minus">
											<button data-id="<%=product.getIdArticolo()%>" type="button" class="btn btn-primary btn-number" <% if(product.getQuantita()==1){ %>disabled="disabled" <%}%> data-type="minus" data-field="quant[<%=cont%>]">
												<i class="ti-minus"></i>
											</button>
										</div>
										<input type="text" name="quant[<%=cont %>]" class="input-number"  data-min="1" data-max="100" value="<%= product.getQuantita()%>">
										<div class="button plus">
											<button data-id="<%=product.getIdArticolo()%>" type="button" class="btn btn-primary btn-number" data-type="plus" data-field="quant[<%=cont %>]">
												<i class="ti-plus"></i>
											</button>
										</div>
									</div>
									<!--/ End Input Order -->
								</td>
								<%
									qtaT += product.getQuantita();
									double prezzoT = product.getQuantita()*product.getPrezzoAcquisto();
								%>
								<td class="pdg-30 total-amount" data-title="Total"><span><%=prezzoT %>€</span></td>
								<td class="pdg-30 action" data-title="Remove">
<%-- 									<input id="idArticle" type="hidden" name="id" value="<%=product.getIdArticolo() %>"> --%>
									<a href="RemoveArticle?id=<%=product.getIdArticolo() %>" class="action remove" type="button"><i class="ti-trash remove-icon"></i></a>
								</td>
							</tr>
							<%
									cont++;
								}
							%>
						</tbody>
					
					</table>
					<!--/ End Shopping Summery -->
				</div>
			</div>
			
			<div class="row">
				<div class="col-12">
					<!-- Total Amount -->
					<div class="total-amount">
						<div class="row">
							<div class="col-lg-4 col-md-7 col-12 snap-right">
								<div class="right">
								<%
										
									Double costoTotale = componeDAO.calcoloCosto(new Carrello(articoliCarrello.get(0).getIdCarrello()));
									
								%>
									<ul>
									<% 
										if(qtaT>1) {
									%>
											<li>Totale provvisorio<% out.write(" ("+qtaT+" articoli): ");%>
												<span><%= costoTotale %>€ </span>
											</li>
									<% 		
										}
										else{
									%>
											<li>Totale provvisorio<% out.write(" ("+qtaT+" articolo): ");%>
												<span><%= costoTotale %>€ </span>
											</li>
									<% } %>		
										
										
										<!-- Oppure -->
										
<!-- 									<li>Totale provvisorio -->
									<% 
// 																if(qtaT>1) 
// 																	out.write(" ("+qtaT+" articoli): ");
// 																else
// 																	out.write(" ("+qtaT+" articolo): ");
															%>
<%-- 												<span><%= formatter.format(costoTotale) %>€ </span> --%>
<!-- 									</li> -->									

									</ul>
									<div class="button5">
										<a href="/privato/cliente/ordine/RichiediCheckOut" class="btn">Procedi all'Ordine</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--/ End Total Amount -->
				</div>
			</div>
			<%
				} //}
			%>	
		</div>
	</div>
		</div>
	</div>
	
	<!--/ End Shopping Cart -->
							
	<%@include file="/WEB-INF/footer.jsp" %>																																				
	
	<!-- Jquery -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/jquery-migrate-3.0.0.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<!-- Popper JS -->
	<script src="/js/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="/js/bootstrap.min.js"></script>
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
	<script src="/js/js.js"></script>
</body>
</html>