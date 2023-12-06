<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="it.unirc.db.ecommerce.views.ViewProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Carrello"%>
<%@page import="it.unirc.db.ecommerce.beans.CarrelloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ArticoloDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Articolo"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page
	import="it.unirc.db.ecommerce.beans.join.ArticoloComponeCarrelloDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/head.jsp" %>
<link rel="stylesheet" href="/css/product.css">
</head>
<body class="js">

	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>

	<%
		GridProduct articolo = null;
		if (request.getAttribute("product") != null) {
			articolo = (GridProduct) request.getAttribute("product");
		}
	%>

	<%@include file="/WEB-INF/header.jsp"%>

	<%
		if (request.getAttribute("msg") != null)
			out.write((String) request.getAttribute("msg"));
	%>


	<section class="product-area shop-sidebar shop section shopping-cart">
		<div class="container">
			<div id="message"></div>
			<div class="row">
				<!-- Images -->
				<div class="col-lg-6">
					<div class="product-pic-zoom">
						<img class="product-big-img" src="https://via.placeholder.com/1000x1358"
							alt="">
					</div>
					<div class="product-thumbs" tabindex="1"
						style="overflow: hidden; outline: none;">
						<div class="product-thumbs-track">
							<div class="pt active"
								data-imgbigurl="https://via.placeholder.com/1000x1358">
								<img src="https://via.placeholder.com/116x116" alt="">
							</div>
							<div class="pt" data-imgbigurl="https://via.placeholder.com/1000x1358">
								<img src="https://via.placeholder.com/116x116" alt="">
							</div>
							<div class="pt" data-imgbigurl="https://via.placeholder.com/1000x1358">
								<img src="https://via.placeholder.com/116x116" alt="">
							</div>
							<div class="pt" data-imgbigurl="https://via.placeholder.com/1000x1358">
								<img src="https://via.placeholder.com/116x116" alt="">
							</div>
						</div>
					</div>
				</div>

				<!-- Description -->
				<div class="col-lg-6 order-3">
					<div class="product_description">
						<div class="product_name"><%=articolo.getNomeProdotto()%></div>
						<%-- Nome Prodotto --%>

						<div class="product_brand">
							<a class="brand-link"
								href="QueryResult?search=<%=articolo.getMarca().replaceAll("\\s+", "+")%>">Marca:
								<%=articolo.getMarca()%></a>
						</div>

						<hr>

						<div class="product_text d-flex">
							<p>Prezzo:</p>
							<div class="product_price"><%=articolo.getPrezzo()%>€
							</div>
						</div>

						<div class="product_text">
							<p>
								<%
									if (articolo.getDisponibilita() > 0)
										out.write("<span class=\"p-available\"> Disponibilità immediata</span>");
									else
										out.write("<span class=\"p-not-available\"> Non Disponibile</span>");
								%>
							</p>
						</div>
						<div class="product_text">
							<p>
								Venduto da:
								<%=articolo.getNomeAzienda()%></p>
						</div>


						<%
							if (articolo.getDisponibilita() > 0) {
						%>
						<div class="order_info d-flex flex-row">
							<form action="AddToCart" class="d-flex">
								<input id="idArticle" type="hidden" name="id"
									value="<%=articolo.getIdArticolo()%>">
								<!-- Product Quantity -->
								<div class="qty">
									<div class="input-group input-group-w">
										<div class="button minus">
											<button type="button" class="btn btn-primary btn-number"
												disabled="disabled" data-type="minus" data-field="qty">
												<i class="ti-minus"></i>
											</button>
										</div>
										<input type="text" name="qty" class="input-number"
											data-min="1" data-max="100" value="1">
										<div class="button plus">
											<button type="button" class="btn btn-primary btn-number"
												data-type="plus" data-field="qty">
												<i class="ti-plus"></i>
											</button>
										</div>
									</div>
								</div>
								<div class="button_container">
									<button type="submit" class="button cart_button btn-color">Add
										to Cart</button>
								</div>
							</form>
						</div>
						<%
							}
						%>

						<div id="accordion" class="accordion-area">
							<div class="panel">
								<div class="panel-header" id="headingOne">
									<button class="panel-link" data-toggle="collapse"
										data-target="#collapse1" aria-expanded="false"
										aria-controls="collapse1">Descrizione</button>
								</div>
								<div id="collapse1" class="collapse"
									aria-labelledby="headingOne" data-parent="#accordion">
									<div class="panel-body">
										<p><%=articolo.getDescrizioneBreve()%></p>
									</div>
								</div>
							</div>
							<div class="panel">
								<div class="panel-header" id="headingTwo">
									<button class="panel-link" data-toggle="collapse"
										data-target="#collapse2" aria-expanded="false"
										aria-controls="collapse2">Descrizione Dettagliata</button>
								</div>
								<div id="collapse2" class="collapse"
									aria-labelledby="headingTwo" data-parent="#accordion">
									<div class="panel-body">
										<p><%=articolo.getDescrizioneDettagliata()%></p>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>


	<%@include file="/WEB-INF/footer.jsp"%>

	<%@include file="/WEB-INF/js.jsp"%>

	<script src="/js/jquery.zoom.min.js"></script>
	<script src="/js/main.js"></script>
	<script src="js/myscript.js"></script>
	<script src="/js/js.js"></script>

</body>
</html>