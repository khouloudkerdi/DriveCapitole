<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <body id="header_body" >
	<header >

            <div class="mainbar_logo">
                <img src="${pageContext.request.contextPath}/css/image/neo.JPG" alt="logo_neopro" width="80px" height="55px"/>
            </div>
            

            <nav id="navbar_search" class="navbar navbar-light justify-content-center" >
                <div class="container-fluid justify-content-center">
                    <form class="d-flex" id="search_form" action="" method="GET">
                        <input id="search-input" class="form-control me-2" type="search" placeholder="Rechercher un article ..." aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Rechercher</button>
                    </form>
                </div>
            </nav>
            
            <div id="navbar_btn_group" class="btn-group" role="group" aria-label="Basic example">
                <a class="btn btn-primary">Compte</a>
                <a class="btn btn-primary">Listes</a>
                <a href="Panier" class="btn btn-primary">Panier</a>
            </div>
            
        </header>
    </body>
