<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="it.unirc.db.ecommerce.beans.Prodotto"%>
<%@page import="it.unirc.db.ecommerce.beans.Sottocategoria"%>
<%@page import="it.unirc.db.ecommerce.beans.SottocategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.Categoria"%>
<%@page import="it.unirc.db.ecommerce.beans.CategoriaDAO"%>
<%@page import="it.unirc.db.ecommerce.beans.ProdottoDAO"%>
<%@ page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Visualizza prodotti</title>
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
<link rel="stylesheet" href="/css/mystyle.css">

<link rel="stylesheet" href="/css/visualizzaProdotti.css">

</head>
<body>
<%int item=2; %>
<%@include file="/WEB-INF/checkVenditore.jsp" %>
<%@include file="/WEB-INF/privato/venditore/header.jsp" %>
  <% 
				CategoriaDAO cDAO = new CategoriaDAO();
  Vector<Categoria> categorias;
  				if(request.getAttribute("categorie")!=null)
					categorias = (Vector<Categoria>) request.getAttribute("categorie");
  				else	
  					categorias = cDAO.getAll();
				/* Vector<Categoria> categoria = (Vector<Categoria>) request.getAttribute("categoria"); */
					Vector<Categoria> categoria = (Vector<Categoria>)request.getAttribute("categorie");
					if(request.getAttribute("erroreProdotto")!=null){
							%>
								<div class="alert alert-danger" role="alert">
 										 Non siamo riusciti ad eliminare il prodotto selezionato!
								</div>						
			<%         }
					/* try { */
					/*Vector<Sottocategoria> sottocategoria = scDAO.getAll();*/
			%>
			
    
    <div class="container-my">

        <div class="row-my">
            <div class="categoria ">
                <label for="categoria">Categoria</label><br>
                <select name="categoria" id="categoria" onclick="changeCategory() ">
                <option></option> 
                <%
				for (Categoria c : categorias) {
				%>
                <option value='<%=c.getIdCategoria()%>'><%=c.getNome()%>
				</option>
				<%
				}
				%>
			</select>
            </div>
            <div class="sottocategoria ">
                <label for="sottocategoria">Sottocategoria</label><br>
                <select name="sottocategoria" id="sottocategoria"  onchange="changeSubcategory()"></select>
            </div>
            
            <div class="prodotto ">
                <label for="prodotto">Prodotto</label><br>
                <input list="products-list" required id="prodotto" onkeypress="searchProduct()" onkeyup="searchProduct()" onkeydown="searchProduct()" autocomplete="off"	name="product" style="width: 171px;"> 
					<datalist id="products-list">							
					</datalist>
            </div>
        </div>

        <div class="row-my">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Modifica</th>
                        <th scope="col">Elimina</th>
                    </tr>
                </thead>
                <tbody id="body">
                 
                 
                </tbody>
            </table>
        </div> 
    </div>
    	<%@include file="/WEB-INF/footer.jsp" %>
    
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