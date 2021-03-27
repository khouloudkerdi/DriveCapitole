<%@page import="neopro.dao.MethodesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<header id="headerAccueil">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <img src="${pageContext.request.contextPath}/css/image/neo.JPG" alt="logo_neopro" width="80px" height="55px"/>
            </div>
            <div class="col-md-6">
                <nav id="navbar_search" class="navbar sticky-top navbar-light justify-content-center" >
                    <div class="container-fluid justify-content-center">
                        <form class="d-flex" id="search_form" action="Search" method="GET">
                            <input id="search-input" name="searchWord" class="form-control me-2" type="search" placeholder="Rechercher un article ..." aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Rechercher</button>
                        </form>
                    </div>
                </nav>
            </div>
            <div class="col-md-3">
                <div class="btn-group" role="group" aria-label="Basic example">  
                    <a class="btn btn-primary" href="CtrlMenu?method=Connexion">Compte</a>
                    <a class="btn btn-primary" href="CtrlMenu?method=Liste">Listes</a>                
                    <a href="Accueil" class="btn btn-primary">
                        <img src="${pageContext.request.contextPath}/css/image/accueil.png" alt="logo_neopro" width="45px" height="35px"/>
                    </a>
               
                <%
                    if (request.getSession().getAttribute("idClient") != null) {
                %>
                <div class="btn btn-primary contenant">
                    <a href="Panier"><img src="${pageContext.request.contextPath}/css/image/caddie.png" alt="logo_neopro" width="50px" height="40px"/></a>
                        <% long qte = MethodesDAO.nbArt(1); %>
                    <div class="texte_centrer"><% out.print(qte); %></div>
                </div>
                 </div>
                <% }%>
            </div>
        </div>
    </div>   
</div>
</header>

