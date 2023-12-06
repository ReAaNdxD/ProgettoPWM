<%@page pageEncoding="UTF-8"%>
    <!-- Header -->
    <header class="header shop">
        <div class="middle-inner">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2 col-md-2 col-sm-6 logo-ord">
                        <!-- Logo -->
                        <div class="logo">
                            <a href="/QueryResult"><embed type="image/svg+xml" src="/images/logo.svg" ></a>
                        </div>
                        <!--/ End Logo -->
                        
                    </div>
                    <div class="col-lg-8 col-md-8 col-12 search-bar-ord">
                        <div class="search-bar-top">
                            <div class="search-bar">
                                <form action="/QueryResult">
                                    <input name="search" placeholder="Search Products Here....." type="search">
                                    <button class="btnn"><i class="ti-search"></i></button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-2 col-sm-6 right-bar-ord">
                        <div class="right-bar plus">
                            <!-- Search Form -->
                            <div class="sinlge-bar">
                                <a href="/privato/venditore/VisualizzaVenditore" class="single-icon"><i class="fa fa-user-circle-o"
                                        aria-hidden="true"></i></a>
                               
                            </div>
                            <div class="sinlge-bar shopping">
                                <a href="/privato/prodotto/RichiediAggiungiProdottoInVendita" class="single-icon"><i class="ti-bag" aria-hidden="true"></i> </a>
                                <!--/ End Shopping Item -->
                                
                            </div>
                            <div class="sinlge-bar add">
                            
                            		<a href="/privato/prodotto/RichiediAggiungiProdotto" class="single-icon"><i class="fa fa-plus-circle" aria-hidden="true"></i> 
                            		
                            		
                            		
                            		
<!--                             		<svg xmlns="http://www.w3.org/2000/svg" width="21" height="21" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16"> -->
<!--   											<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/><path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/> -->
<!-- 									</svg> -->
  									</a>
                            </div>
                            <div class="sinlge-bar add">
                            
                             <a href="/Logout" class="single-icon"><i class="fa fa-power-off"
                                        aria-hidden="true"></i></a>
                            </div>
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
                                                <li <%if(item==1) out.write("class=\"active\""); %> ><a href="/privato/prodotto/VisualizzaProdottiInVendita">I tuoi prodotti</a></li>
                                                <li <%if(item==2) out.write("class=\"active\"");%> ><a href="/privato/prodotto/VisualizzaProdotto">Tutti i prodotti</a></li>
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