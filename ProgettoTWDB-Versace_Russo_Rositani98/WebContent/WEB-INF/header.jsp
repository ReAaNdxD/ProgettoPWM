<%@page pageEncoding="UTF-8"%>

    <!-- Header -->
    <header class="header shop">
        <div class="middle-inner">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2 col-md-2 col-sm-6 logo-ord">
                        <!-- Logo -->
                        <div class="logo">
                            <a href="/"><embed type="image/svg+xml" src="/images/logo.svg" ></a>
                        </div>
                        <!--/ End Logo -->
                        
                    </div>
                    <div class="col-lg-8 col-md-8 col-12 search-bar-ord">
                        <div class="search-bar-top">
                            <div class="search-bar">
                                <select id="category-elem">
                                    <option value="0" selected="selected">All Category</option>
                                    	<%
										    for(Categoria categoria : categoriaDAO.getAll()){
                                        %>
                                    		<option value="<%= categoria.getIdCategoria() %>"><%= categoria.getNome() %></option>
                                    	<%	
                                            } 
                                        %>
                                </select>
                                <form id="search-form" action="/Search">
                                    <input id="imp-search" name="search" placeholder="Search Products Here....." type="search">
                                    <button type="button" id="search" class="btnn"><i class="ti-search"></i></button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-2 col-sm-6 right-bar-ord">
                        <div class="right-bar">
                            <!-- Search Form -->
                            <div class="sinlge-bar">
                                <a href="/privato/cliente/VisualizzaAccount" class="single-icon"><i class="fa fa-user-circle-o"
                                        aria-hidden="true"></i></a>
                            </div>
                            <div class="sinlge-bar shopping">
                                
                                
                                <%
                                	// Se non Ã¨ autenticato metto zero altrimenti prendo il valore di articoli in carrello del liente 
                                	int totalCount=0;
                                	if(cliente!=null){
                                		
                                		totalCount= articoliCarrello.size();
                                		
                                %>
                                		<a href="/privato/cliente/carrello/Cart" class="single-icon"><i class="fa fa-shopping-cart"></i> 
                                			<span class="total-count"><%=totalCount%></span>
                                		</a>
                                <%
                                	}else {
                                %>
                                		<a href="/privato/cliente/carrello/Cart" class="single-icon"><i class="fa fa-shopping-cart"></i> 
                                			<span class="total-count">0</span>
                                		</a>
                                <%		
                                	}
                                %>
                                
                                
                                <!-- Shopping Item -->
                                <div class="shopping-item">
                                    <div class="dropdown-cart-header">
                                        <span><%=totalCount %> Item/s</span>
                                        <a href="/privato/cliente/carrello/Cart">View Cart</a>
                                    </div>
                                    <ul class="shopping-list">
                                    	
                                    	<%	
                                    		if(cliente!=null){
                                    			for(ViewProduct item : articoliCarrello){
                                    			
                                    			System.out.println(item);
                                    	%>
                                        <li>
                                            <a href="/privato/cliente/carrello/RemoveArticle?id=<%=item.getIdArticolo()%>" class="remove" title="Remove this item"><i
                                                    class="fa fa-remove"></i></a>
                                            <a class="cart-img" href="/ProductDetails?id=<%=item.getIdArticolo()%>"><img src="https://via.placeholder.com/70x70"
                                                    alt="#"></a>
                                            <h4><a href="/ProductDetails?id=<%=item.getIdArticolo()%>"><%= item.getNomeProdotto() %></a></h4>
                                            <p class="quantity"><%= item.getQuantita() %>x - <span class="amount">€<%= item.getPrezzoAcquisto() %></span></p>
                                        </li>
                                        <%
                                    			}
                                    		}
                                        %>
                                    </ul>
                                    <% 
                                    
                                    if(articoliCarrello!=null&&articoliCarrello.size()>0){
                                    %>
                                    <div class="bottom">
                                        
                                        <a href="/privato/cliente/ordine/RichiediCheckOut" class="btn animate">Checkout</a>
                                    </div>
                                    <%
                                    }
                                    %>
                                </div>
                                <!--/ End Shopping Item -->
                            </div>
                            	<% 
                                	if(cliente!=null){
                                %>
                                	<div class="sinlge-bar shopping">
                                		<a href="/Logout" class="single-icon"><i class="fa fa-power-off" aria-hidden="true"></i></a>
                                	</div>
                                <%		
                                	}else {
                                %>
                                	<div class="sinlge-bar shopping">
                                		<a href="/RichiediLoginVenditore" class="single-icon"><i class="fa fa-user-plus" aria-hidden="true"></i></a>
                                	</div>
                                <%		
                                	}
                                %>
                                
                                
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header Inner -->
        <div class="header-inner">
            <div class="container">
                <div class="cat-nav-head">
                    <div class="row">
                        <div class="col-lg-12 col-12">
                            <div class="menu-area">
                                <!-- Main Menu -->
                                <nav class="navbar navbar-expand-lg">
                                    <div class="navbar-collapse">
                                        <div class="nav-inner">
                                            <ul class="nav main-menu menu navbar-nav">
                                                <li <%if(item_nav==1) out.write("class=\"active\"");%>><a href="/">Home</a></li>
                                                
	                                             <%
	                                             
	                                             	
												    for(Categoria categoria : categoriaDAO.getAll()){
			                                     %>
			                                    		<li <%if(ref!=null&&category_url!=null&&category_url==categoria.getIdCategoria()) out.write("class=\"active\"");%>><a href="/Search?category=<%= categoria.getIdCategoria() %>&ref=nav"><%= categoria.getNome() %></a></li>
			                                    <%	
			                                    	}
			                                    %>
			                                    </ul>
                                        </div>
                                    </div>
                                </nav>
                                <!--/ End Main Menu -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ End Header Inner -->
    </header>
    <!--/ End Header -->