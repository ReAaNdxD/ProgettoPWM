<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="it.unirc.db.ecommerce.views.GridProduct"%>
<%@ page import="java.util.Vector"%>
<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@ page import="it.unirc.db.ecommerce.views.ViewProductSeller"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page import="it.unirc.db.ecommerce.beans.VenditoreDAO"%>
<%@ page import="java.util.Vector"%>
<%@ page import="it.unirc.db.ecommerce.beans.Venditore"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Prodotti in vendita</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<link rel="icon" type="image/svg" href="/images/logo.svg">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
	crossorigin="anonymous"></script>

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
<link rel="stylesheet" href="/css/product.css">
<link rel="stylesheet" href="/css/mystyle.css">

</head>
<body>
	<%
	int item = 1;
	%>
	<%@include file="/WEB-INF/checkVenditore.jsp"%>
	<%@include file="/WEB-INF/privato/venditore/header.jsp"%>

	<%
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	SottocategoriaDAO sottocategoriaDAO = new SottocategoriaDAO();
	String search=null;
	if(request.getParameter("search")!=null)
		search = (String) request.getParameter("search");
	 
	NumberFormat formatter = new DecimalFormat("#0.00");
	Vector<ViewProductSeller> prodottiVenduti = null;
	Vector<GridProduct> queryResult = (Vector<GridProduct>) request.getAttribute("queryResult");
	if (queryResult == null) {
		VenditoreDAO venditoreDAO = new VenditoreDAO();
		if (session.getAttribute("Venditore") != null) {
			Venditore seller = (Venditore) session.getAttribute("Venditore");
			prodottiVenduti = venditoreDAO.prodottiVenduti(seller);
		} else if ((session.getAttribute("Admin") != null)) {
			prodottiVenduti = venditoreDAO.prodottiVenduti(new Venditore(1));
		} else {
			prodottiVenduti = venditoreDAO.prodottiVenduti(new Venditore(1));
		}
	}
	/* if(session.getAttribute("Venditore")!=null)	{
			Venditore venditore= (Venditore)session.getAttribute("Venditore");
			VenditoreDAO vDAO=new VenditoreDAO();
			Vector<ViewProductSeller> prodotti= vDAO.prodottiVenduti(venditore); */
	%>
	<section class="product-area shop-sidebar shop section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-4 col-12">
					<div class="shop-sidebar">
						<!-- Single Widget -->
						<div class="single-widget category">
							<h3 class="title">Categoria</h3>
							<ul class="categor-list">
								<li><a href="QueryResult"><b>All</b></a></li>
								<%
								for (Categoria c : categoriaDAO.getAll()) {
								%>
								<li class="category""><a
									href="QueryResult?category=<%=c.getIdCategoria()%>"><b><%=c.getNome()%></b></a></li>
								<%
								for (Sottocategoria s : sottocategoriaDAO.getSottocategorieByCategoria(c)) {
								%>
								<li class="subcategory" style="margin-left: 22px;"><a
									href="QueryResult?subcategory=<%=s.getIdSottocategoria()%>"><%=s.getNome()%></a></li>
								<%
								}
								}
								%>
							</ul>
						</div>
						<!--/ End Single Widget -->
					</div>
				</div>

				<div class="col-lg-9 col-md-8 col-12">
					<div class="row">
						<%
						if (queryResult == null) {
							for (ViewProductSeller prodotto : prodottiVenduti) {
						%>
						<div class="col-lg-3 col-md-4 col-12">
							<div class="single-product">
									<div class="product-img">
									
									<a href="/privato/prodotto/RichiediModificaProdottoInVendita?articolo=<%=prodotto.getIdArticolo()%>">
											<img  class="default-img" 
											src="https://via.placeholder.com/550x750">
									
									</a>
<%-- 										<input type="hidden" name="articolo" value="<%=prodotto.getIdArticolo()%>">  --%>
									</div>
									<div class="product-content">
										<h3>
											<a href="/privato/prodotto/RichiediModificaProdottoInVendita?articolo=<%=prodotto.getIdArticolo()%>"><%=prodotto.getNomeProdotto() %>
												</a>
										</h3>
										<div class="product-price">
											<span><%=formatter.format(prodotto.getPrezzo())%>€</span>
										</div>
									</div>
							</div>
						</div>

						<!-- 							<div class="col-lg-3 col-md-4 col-12"> -->
						<!-- 								<div class="single-product"> -->
						<!-- 								<form id="form" action="/privato/prodotto/RichiediModificaProdottoInVendita" method="post"> -->
						<!-- 									<div class="product-img"> -->
						<%-- 										<input type="hidden" name="articolo" value="<%=prodotto.getIdArticolo()%>"> --%>
						<!-- 										<input type="image" class="default-img"   name="submit" src="https://via.placeholder.com/550x750"> -->

						<!-- 									</div> -->
						<!-- 									<div class="product-content"> -->
						<!-- 										<h3> -->
						<%-- 											<a href="javascript: " onclick="document.getElementById('form').submit();"><%=prodotto.getNomeProdotto()%></a> --%>
						<!-- 										</h3> -->
						<!-- 										<div class="product-price"> -->
						<%-- 											<span><%=formatter.format(prodotto.getPrezzo())%>€</span> --%>
						<!-- 										</div> -->
						<!-- 									</div> -->
						<!-- 									</form> -->
						<!-- 								</div> -->
						<!-- 							</div> -->

						<%
						}
						%>
						<!-- Pagination -->

						<div class="col-lg-12 col-md-12 col-12">
							<nav>
								<ul class="pagination flex-pag">
									<c:if test="${currentPage > 1}">
										<li class="page-item"><a class="page-link"
											href="QueryResult?search=<%=search%>&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
													href="QueryResult?search=<%=search%>&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${currentPage lt noOfPages}">
										<li class="page-item"><a class="page-link"
											href="QueryResult?search=<%=search%>&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
										</li>
									</c:if>
								</ul>
							</nav>
						</div>

						<!--/ End pagination -->
						<%
						} else {
						for (GridProduct prodotto : queryResult) {
						%>

						<div class="col-lg-3 col-md-4 col-12">
							<div class="single-product">
									<div class="product-img">
										<a href="/privato/prodotto/RichiediModificaProdottoInVendita?articolo=<%=prodotto.getIdArticolo()%>">
											<img  class="default-img" 
											src="https://via.placeholder.com/550x750">
									
									</a>
									</div>
									<div class="product-content">
										<h3>
											<a><%=prodotto.getNomeProdotto()%></a>
										</h3>
										<div class="product-price">
											<span><%=formatter.format(prodotto.getPrezzo())%>€</span>
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
								<ul class="pagination flex-pag">
									<c:if test="${currentPage != 1}">
										<li class="page-item"><a class="page-link"
											href="QueryResult?<% if(search!=null) out.write("search="+search+"&"); %>currentPage=${currentPage-1}">Previous</a>
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
													href="QueryResult?<% if(search!=null) out.write("search="+search+"&"); %>currentPage=${i}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:if test="${currentPage lt noOfPages}">
										<li class="page-item"><a class="page-link"
											href="QueryResult?<% if(search!=null) out.write("search="+search+"&"); %>currentPage=${currentPage+1}">Next</a>
										</li>
									</c:if>
								</ul>
							</nav>
						</div>

						<!--/ End pagination -->
						<%
						}
						%>


					</div>
				</div>

			</div>
		</div>
	</section>
	<%@include file="/WEB-INF/footer.jsp"%>
	<script type="text/javascript" src="/js/productjs.js"></script>
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
	<!-- Ytplayer JS -->
	<script src="/js/ytplayer.min.js"></script>
	<!-- Flex Slider JS -->
	<script src="js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script src="/js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script src="/js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script src="/js/easing.js"></script>
	<!-- Active JS -->
	<script src="/js/active.js"></script>
</body>
</html>