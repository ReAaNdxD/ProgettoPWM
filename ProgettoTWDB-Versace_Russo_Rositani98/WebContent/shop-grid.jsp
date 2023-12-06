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
<%@page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="it.unirc.db.ecommerce.beans.Cliente"%>
<!DOCTYPE html>
<html lang="zxx">
<head>

<%@include file="/WEB-INF/head.jsp" %>
<link rel="stylesheet" href="/css/product.css">



</head>
<body class="js">

	<%@include file="/WEB-INF/preload.jsp"%>

	<%@include file="/WEB-INF/header-scriptlet.jsp"%>

	<%
		// 		Vector<GridProduct> queryResult = articoloDAO.getAllAvailableProducts("rtx",0);
		SottocategoriaDAO sottocategoriaDAO = new SottocategoriaDAO();
		String search = (String) request.getParameter("search");

		HashMap<String, Object> param = (HashMap<String, Object>) request.getAttribute("param");
		System.out.println(search);

		// In teoria devo prenderlo da request dato che passo la jsp viene chiamata attraverso un 
		// forward dalla Servlet che si occupa della ricerca 

		Vector<GridProduct> queryResult = (Vector<GridProduct>) request.getAttribute("queryResult");

		for (GridProduct prodotto : queryResult)
			System.out.println("Prodotto - :" + prodotto);
		
		boolean isFilter;
		String removeParam;
		
	%>

	<%@include file="/WEB-INF/header.jsp"%>



	<!-- Product Style -->
	<section class="product-area shop-sidebar shop section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-4 col-12">
					<div class="shop-sidebar">
						<!-- Single Widget -->
						<div class="single-widget category">
							<h3 class="title">Categories</h3>
							<ul class="categor-list">

								<%
									isFilter = false;
									boolean c = false, s = false;
									removeParam = "";
									String removeParam2 = "";
									for (String i : param.keySet()) {
										if (i.equalsIgnoreCase("category")) {
											isFilter = true;
											c = true;
											removeParam2 += i + "=" + param.get(i) + "&";
										} else if (i.equalsIgnoreCase("subcategory")) {
											s = true;
											isFilter = true;
										} else if (i.equalsIgnoreCase("brand")) {
											removeParam += i + "=" + param.get(i) + "&";
											removeParam2 += i + "=" + param.get(i) + "&";
											// 										isFilter=true;
										} else if (i.equalsIgnoreCase("priceLb") || i.equalsIgnoreCase("priceUb")) {
											//											isFilter=true;
											removeParam += i + "=" + param.get(i) + "&";
											removeParam2 += i + "=" + param.get(i) + "&";
										}

										System.out.println("Valore di Param " + i);
									}

									if (removeParam.length() > 0 && isFilter) {
										removeParam = removeParam.substring(0, removeParam.length() - 1);
										if (c && s) {%>
											<li><a href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=removeParam %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Categoria</a></li>
										<%	if (removeParam2.length() > 0) {
												removeParam2 = removeParam2.substring(0, removeParam2.length() - 1);%>
												<li><a href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=removeParam2%>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Sottocategoria</a></li>
												
										<%	}
										} else if (c) {%>
										
										<li><a href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=removeParam %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Categoria</a></li>
										<%}
									} else if (isFilter) {
										if (c && s) {%>
											<li><a href="Search?<%if(search!=null) out.write("search="+search); %><%if(ref!=null) out.write("ref="+ref); %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Categoria</a></li>
											<%
											if (removeParam2.length() > 0) {
												removeParam2 = removeParam2.substring(0, removeParam2.length() - 1);
												%>
												<li><a href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=removeParam2%>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Sottocategoria</a></li>
											<%}
										} else if (c) {
											%>
											<li><a href="Search?<%if(ref!=null) out.write("ref="+ref); %><%if(search!=null) out.write("search="+search); %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Categoria</a></li>
										<% }
									}

									for (Categoria categoria : categoriaDAO.getAll()) {
										int idCategory = 0;
										int idSubcategory = 0;

										String queryC = "";
										for (String i : param.keySet()) {
											if (i.equalsIgnoreCase("brand")) {
												queryC += i + "=" + param.get(i) + "&";
											} else if (i.equalsIgnoreCase("priceUb")) {
												queryC += i + "=" + param.get(i) + "&";
											} else if (i.equalsIgnoreCase("priceLb")) {
												queryC += i + "=" + param.get(i) + "&";
											} else if (i.equalsIgnoreCase("category")) {
												idCategory = Integer.parseInt((String) param.get(i));
											} else if (i.equalsIgnoreCase("subcategory")) {
												idSubcategory = Integer.parseInt((String) param.get(i));
											}
										}
								%>
								
										<li><a <%if(categoria.getIdCategoria()==idCategory) out.write("class=\"active-color\""); %>	href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=queryC%>category=<%=categoria.getIdCategoria()%>"><%=categoria.getNome()%></a></li>

								<%
										for (Sottocategoria sottocategoria : sottocategoriaDAO
													.getSottocategorieByIdCategoria(categoria.getIdCategoria())) {
	
												String queryS = "";
												for (String i : param.keySet()) {
													if (i.equalsIgnoreCase("brand")) {
														queryS += i + "=" + param.get(i) + "&";
													} else if (i.equalsIgnoreCase("priceUb")) {
														queryS += i + "=" + param.get(i) + "&";
													} else if (i.equalsIgnoreCase("priceLb")) {
														queryS += i + "=" + param.get(i) + "&";
													}
	
												}
								%>
											<li class="subcategory"><a <%if(sottocategoria.getIdSottocategoria()==idSubcategory) out.write("class=\"active-color\""); %> href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=queryS%>category=<%=categoria.getIdCategoria()%>&subcategory=<%=sottocategoria.getIdSottocategoria()%>"><%=sottocategoria.getNome()%></a></li>
								<%
										}
									}
								%>
							</ul>
						</div>
						<!--/ End Single Widget -->

						<!-- Shop By Brand -->
						<div class="single-widget">
							<h3 class="title">Marche</h3>
							<ul class="categor-list">
							
							<%
								isFilter=false;
								removeParam="";
								for (String i : param.keySet()) {
									if (i.equalsIgnoreCase("category")) {
										removeParam+= i + "=" + param.get(i) +"&";
									} else if (i.equalsIgnoreCase("subcategory")) {
										removeParam+= i + "=" + param.get(i) +"&";
									} else if (i.equalsIgnoreCase("brand")) {
										isFilter=true;
									} else if (i.equalsIgnoreCase("priceLb") || i.equalsIgnoreCase("priceUb")) {
										removeParam+= i + "=" + param.get(i) +"&";
									}
									System.out.println("Valore di Param " + i);
								}
										
								if(removeParam.length()>0 && isFilter){
									removeParam=removeParam.substring(0,removeParam.length()-1);
								%>
									<li><a href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=removeParam %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Marca</a></li>
								<%
								} else if (isFilter){
								
								%>
									<li><a href="Search?<%if(ref!=null&&search!=null) out.write("ref="+ref+"&"); else if(ref!=null) out.write("ref="+ref);%><%if(search!=null) out.write("search="+search); %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Marca</a></li>
								<%
								} 
								
										
								for (String marca : (Vector<String>) request.getAttribute("brands")) {

									String queryM = "";
									String brand ="";
									for (String i : param.keySet()) {
										if (i.equalsIgnoreCase("category")) {
											System.out.println("Dentro CAtegoria");
											queryM += i + "=" + param.get(i) + "&";
										} else if (i.equalsIgnoreCase("subcategory")) {
											queryM += i + "=" + param.get(i) + "&";
										} else if (i.equalsIgnoreCase("priceLb")) {
											queryM += i + "=" + param.get(i) + "&";
										} else if (i.equalsIgnoreCase("priceUb")) {
											queryM += i + "=" + param.get(i) + "&";
										} else if (i.equalsIgnoreCase("brand")) {
											brand = param.get(i)+"";
										}

										System.out.println("Valore di Param " + i);
									}
									
// 									if(queryM.length()>0)
// 										queryM=queryM.substring(0,queryM.length()-1);

							%>
									<li><a <%if(marca.equalsIgnoreCase(brand)) out.write("class=\"active-color\""); %> href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=queryM%>brand=<%=marca%>"><%=marca%></a></li>
							<%
								}
							%>

							</ul>
						</div>
						<!-- Shop By Brand -->


						<!-- Shop By Price -->
						<div class="single-widget range">
							<h3 class="title">Shop by Price</h3>
							<div class="price-filter">
								<div class="price-filter-inner contact-us">
								<%
									isFilter=false;
									removeParam="";
									for (String i : param.keySet()) {
										if (i.equalsIgnoreCase("category")) {
											removeParam+= i + "=" + param.get(i) +"&";
										} else if (i.equalsIgnoreCase("subcategory")) {
											removeParam+= i + "=" + param.get(i) +"&";
										} else if (i.equalsIgnoreCase("brand")) {
											removeParam+= i + "=" + param.get(i) +"&";
										} else if (i.equalsIgnoreCase("priceLb") || i.equalsIgnoreCase("priceUb")) {
											isFilter=true;
										}
	
										System.out.println("Valore di Param " + i);
									}
									
									
										
									if(removeParam.length()>0 && isFilter){
										removeParam=removeParam.substring(0,removeParam.length()-1);
									%>
										<a href="Search?<%if(search!=null) out.write("search="+search+"&"); %><%if(ref!=null) out.write("ref="+ref+"&"); %><%=removeParam %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Prezzo</a>
									<%
									
									}else if (isFilter){
									%>	
										<a href="Search?<%if(ref!=null) out.write("ref="+ref+"&"); %><%if(search!=null) out.write("search="+search); %>" class="back-filter"><span class="s-back-arrow"></span>Qualsiasi Prezzo</a>
									<%
									}
									
									%>
						
									<form id="form" class="form" action="Search" method="get">
									
									<%if(search!=null){ %>
										<input type="hidden" name="search" value="<%=search%>">
										
									<%} %>	
									<%if(ref!=null){ %>
										<input type="hidden" name="ref" value="<%=ref%>">
										
									<%} %>
										<%
											for (String i : param.keySet()) {
												if (i.equalsIgnoreCase("category")) {
													out.write("<input type=\"hidden\" name=\"" + i + "\" value=\"" + param.get(i) + "\">");
												} else if (i.equalsIgnoreCase("subcategory")) {
													out.write("<input type=\"hidden\" name=\"" + i + "\" value=\"" + param.get(i) + "\">");
												} else if (i.equalsIgnoreCase("brand")) {
													out.write("<input type=\"hidden\" name=\"" + i + "\" value=\"" + param.get(i) + "\">");
												}

												System.out.println("Valore di Param " + i);
											}

											int currentPage = (int) request.getAttribute("currentPage");
											if (request.getAttribute("currentPage") != null)
												out.write("<input type=\"hidden\" name=\"currentPage\" value=\"" + currentPage + "\">");
										%>
										<div class="form-group" >
											<div class="row just">
												<div class="col-4 widget-price">
													<input class="adjust" id="lb" type="text" name="priceLb" placeholder="Min €">
												</div>
												<div class="col-4 widget-price">
													<input class="adjust" id="ub" type="text" name="priceUb" placeholder="Max €">
												</div>
												<div class="col-4 widget-price">
													<button id="price" class="btn pdg" type="button">Vai</button>
												</div>
											</div>
											
										</div>
									</form>
									
								</div>
							</div>
						</div>
						<!--/ End Shop By Price -->


						<!--/ End Single Widget -->
					</div>
				</div>
				<div class="col-lg-9 col-md-8 col-12">


					<div class="row">
						<%
							for (GridProduct prodotto : queryResult) {
						%>
						<div class="col-lg-3 col-md-4 col-12">
							<div class="single-product">
								<div class="product-img">
									<a href="ProductDetails?id=<%=prodotto.getIdArticolo()%>">
										<img class="default-img"
										src="https://via.placeholder.com/550x750" alt="#"> <img
										class="hover-img" src="https://via.placeholder.com/550x750"
										alt="#">
									</a>
								</div>
								<div class="product-content">
									<h3>
										<a href="ProductDetails?id=<%=prodotto.getIdArticolo()%>"><%=prodotto.getNomeProdotto()%></a>
									</h3>
									<div class="product-price">
										<span><%=prodotto.getPrezzo()%>€</span>
									</div>
								</div>
							</div>
						</div>
						<%
							}
						%>

						<!-- Pagination -->

						<div class="col-lg-12 col-md-12 col-12">
							<nav>
								<%
									removeParam="";
									for (String i : param.keySet()) {
										if (i.equalsIgnoreCase("category")) {
											removeParam+= i + "=" + param.get(i) +"&";
										} else if (i.equalsIgnoreCase("subcategory")) {
											removeParam+= i + "=" + param.get(i) +"&";
										} else if (i.equalsIgnoreCase("brand")) {
											removeParam+= i + "=" + param.get(i) +"&";
										} else if (i.equalsIgnoreCase("priceLb") || i.equalsIgnoreCase("priceUb")) {
											removeParam+= i + "=" + param.get(i) +"&";
										}
	
										System.out.println("Valore di Param " + i);
									}
									
// 									if(removeParam.length()>0)
// 										removeParam=removeParam.substring(0, removeParam.length()-1);
								%>
								<ul class="pagination flex-pag">
									<c:if test="${currentPage != 1}">
										<li class="page-item"><a class="page-link"
											href="Search?<%if(ref!=null) out.write("ref="+ref+"&"); %><%if(search!=null) out.write("search="+search+"&"); %><%=removeParam %>currentPage=${currentPage-1}">Previous</a>
										</li>
									</c:if>

									<c:forEach begin="1" end="${noOfPages}" var="i">
										<c:choose>
											<c:when test="${currentPage eq i}">
												<li class="page-item"><a
													class="page-link active pag-color"> ${i} <span
														class="sr-only">(current)</span></a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="Search?<%if(ref!=null) out.write("ref="+ref+"&"); %><%if(search!=null) out.write("search="+search+"&"); %><%=removeParam %>currentPage=${i}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${currentPage lt noOfPages}">
										<li class="page-item"><a class="page-link"
											href="Search?<%if(ref!=null) out.write("ref="+ref+"&"); %><%if(search!=null) out.write("search="+search+"&"); %><%=removeParam %>currentPage=${currentPage+1}">Next</a>
										</li>
									</c:if>
								</ul>
							</nav>
						</div>
						<!--/ End pagination -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/ End Product Style 1  -->


	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="ti-close" aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row no-gutters">
						<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<!-- Product Slider -->
							<div class="product-gallery">
								<div class="quickview-slider-active">
									<div class="single-slider">
										<img src="https://via.placeholder.com/569x528" alt="#">
									</div>
									<div class="single-slider">
										<img src="https://via.placeholder.com/569x528" alt="#">
									</div>
									<div class="single-slider">
										<img src="https://via.placeholder.com/569x528" alt="#">
									</div>
									<div class="single-slider">
										<img src="https://via.placeholder.com/569x528" alt="#">
									</div>
								</div>
							</div>
							<!-- End Product slider -->
						</div>
						<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
							<div class="quickview-content">
								<h2>Flared Shift Dress</h2>
								<div class="quickview-ratting-review">
									<div class="quickview-ratting-wrap">
										<div class="quickview-ratting">
											<i class="yellow fa fa-star"></i> <i
												class="yellow fa fa-star"></i> <i class="yellow fa fa-star"></i>
											<i class="yellow fa fa-star"></i> <i class="fa fa-star"></i>
										</div>
										<a href="#"> (1 customer review)</a>
									</div>
									<div class="quickview-stock">
										<span><i class="fa fa-check-circle-o"></i> in stock</span>
									</div>
								</div>
								<h3>$29.00</h3>
								<div class="quickview-peragraph">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit. Mollitia iste laborum ad impedit pariatur esse optio
										tempora sint ullam autem deleniti nam in quos qui nemo ipsum
										numquam.</p>
								</div>
								<div class="size">
									<div class="row">
										<div class="col-lg-6 col-12">
											<h5 class="title">Size</h5>
											<select>
												<option selected="selected">s</option>
												<option>m</option>
												<option>l</option>
												<option>xl</option>
											</select>
										</div>
										<div class="col-lg-6 col-12">
											<h5 class="title">Color</h5>
											<select>
												<option selected="selected">orange</option>
												<option>purple</option>
												<option>black</option>
												<option>pink</option>
											</select>
										</div>
									</div>
								</div>
								<div class="quantity">
									<!-- Input Order -->
									<div class="input-group">
										<div class="button minus">
											<button type="button" class="btn btn-primary btn-number"
												disabled="disabled" data-type="minus" data-field="quant[1]">
												<i class="ti-minus"></i>
											</button>
										</div>
										<input type="text" name="quant[1]" class="input-number"
											data-min="1" data-max="1000" value="1">
										<div class="button plus">
											<button type="button" class="btn btn-primary btn-number"
												data-type="plus" data-field="quant[1]">
												<i class="ti-plus"></i>
											</button>
										</div>
									</div>
									<!--/ End Input Order -->
								</div>
								<div class="add-to-cart">
									<a href="#" class="btn">Add to cart</a> <a href="#"
										class="btn min"><i class="ti-heart"></i></a> <a href="#"
										class="btn min"><i class="fa fa-compress"></i></a>
								</div>
								<div class="default-social">
									<h4 class="share-now">Share:</h4>
									<ul>
										<li><a class="facebook" href="#"><i
												class="fa fa-facebook"></i></a></li>
										<li><a class="twitter" href="#"><i
												class="fa fa-twitter"></i></a></li>
										<li><a class="youtube" href="#"><i
												class="fa fa-pinterest-p"></i></a></li>
										<li><a class="dribbble" href="#"><i
												class="fa fa-google-plus"></i></a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal end -->

	<%@include file="/WEB-INF/footer.jsp"%>
	<%-- 	<%@include file="/WEB-INF/js.jsp"%> --%>

	<!-- Jquery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-migrate-3.0.0.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<!-- Popper JS -->
	<script src="js/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Color JS -->
	<script src="js/colors.js"></script>
	<!-- Slicknav JS -->
	<script src="js/slicknav.min.js"></script>
	<!-- Owl Carousel JS -->
	<script src="js/owl-carousel.js"></script>
	<!-- Magnific Popup JS -->
	<script src="js/magnific-popup.js"></script>
	<!-- Fancybox JS -->
	<script src="js/facnybox.min.js"></script>
	<!-- Waypoints JS -->
	<script src="js/waypoints.min.js"></script>
	<!-- Countdown JS -->
	<script src="js/finalcountdown.min.js"></script>
	<!-- Nice Select JS -->
	<script src="js/nicesellect.js"></script>
	<!-- Ytplayer JS -->
	<script src="js/ytplayer.min.js"></script>
	<!-- Flex Slider JS -->
	<script src="js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script src="js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script src="js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script src="js/easing.js"></script>
	<!-- Active JS -->
	<script src="js/active.js"></script>
	<script src="js/myscript.js"></script>



</body>
</html>