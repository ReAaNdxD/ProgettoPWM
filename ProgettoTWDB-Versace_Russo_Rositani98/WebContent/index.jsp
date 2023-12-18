<!-- Vado a integrare la direttiva  -->
<%@ taglib prefix="s" uri="/struts-tags" %>

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


<!DOCTYPE html>
<html lang="zxx">

<head>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Potta+One&display=swap" rel="stylesheet">
    <%@include file="/WEB-INF/head.jsp" %>
</head>

<body class="js">

	<%@include file="/WEB-INF/preload.jsp" %>

	<%
	
		CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArticoloDAO articoloDAO = new ArticoloDAO();
//      ArticoloComponeCarrelloDAO componeDAO = new ArticoloComponeCarrelloDAO();
//      CarrelloDAO carrelloDAO = new CarrelloDAO();
//         Cliente cliente = new Cliente(2);
//         Vector<ViewProduct> articoliCarrello = articoloDAO.getAllArticoliCliente(cliente);
// 		Cliente cliente=null;
// 		if (session.getAttribute("idCliente") != null) {
// 			cliente = new Cliente((int) session.getAttribute("idCliente"));// == null
// 		}
	    
// 	    Vector<ViewProduct> articoliCarrello = articoloDAO.getAllArticoliCliente(cliente);
	    
	    Cliente cliente=null;
	    if(session.getAttribute("idCliente")!=null)
	    	cliente = new Cliente((int) session.getAttribute("idCliente"));// == null
	    Vector<ViewProduct> articoliCarrello=null;
	    if(cliente!=null){
	    	articoliCarrello = articoloDAO.getAllArticoliCliente(cliente);
	    }
//      Carrello carrello=null;
//      if(session.getAttribute("cliente")!=null){
//      	carrello = (Carrello) session.getAttribute("cliente");
		int item_nav = 1;
		String ref = (String) request.getParameter("ref");
		Integer category_url=null;
		if(request.getParameter("category")!=null)
			category_url = Integer.parseInt(request.getParameter("category"));
//      }
		
		Vector<GridProduct> randomProducts = articoloDAO.getAllRandomProducts();

	%>



	<%@include file="/WEB-INF/header.jsp" %>	

    <!-- Slider Area -->
    <section class="hero-slider">
        <!-- Single Slider -->
        <div class="single-slider">
            
        </div>
        <!--/ End Single Slider -->
    </section>
    <!--/ End Slider Area -->

    <!-- Start Small Banner  -->
    <section class="small-banner section">
        <div class="container-fluid">
            <div class="row">
                <!-- Single Banner  -->
                <div class="col-lg-6 col-md-6 col-12">
                    <div class="single-banner  informatica">
                        <div class="content">
                            <a href="Search?category=1&ref=nav">Informatica</a>
                        </div>
                    </div>
                </div>
                <!-- /End Single Banner  -->
                <!-- Single Banner  -->
                <div class="col-lg-6 col-md-6 col-12">
                    <div class="single-banner elettronica">
                        <div class="content ">
                            <a href="Search?category=2&ref=nav">Elettronica</a>
                        </div>
                    </div>
                </div>
                <!-- /End Single Banner  -->
            </div>
        </div>
    </section>
    <!-- End Small Banner -->

    <!-- Start Most Popular -->
    <div class="product-area most-popular section">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-title">
                        <h2>Items</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="owl-carousel popular-slider">
                    
                    <%
							for (GridProduct prodotto : randomProducts) {
						%>
                        <!-- Start Single Product -->
                        <div class="single-product">
                            <div class="product-img">
                                <a href="ProductDetails?id=<%=prodotto.getIdArticolo()%>">
                                    <img class="default-img" src="https://via.placeholder.com/550x750" alt="#">
                                </a>
                            </div>
                            <div class="product-content">
                                <h3><a href="ProductDetails?id=<%=prodotto.getIdArticolo()%>"><%=prodotto.getNomeProdotto()%></a></h3>
                                <div class="product-price">
                                    <span><%=prodotto.getPrezzo()%>€</span>
                                </div>
                            </div>
                        </div>
                        <!-- End Single Product -->
                        <%
							}
						%>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Most Popular Area -->

    <%@ include file="/WEB-INF/footer.jsp" %>

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