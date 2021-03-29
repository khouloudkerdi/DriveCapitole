<%-- 
    Document   : ListeArticle
    Created on : 2021年3月22日, 下午2:16:13
    Author     : 13520
--%>


<%@page import="neopro.metier.NutriscoreArticle"%>
<%@page import="neopro.metier.ListeCourses"%>
<%@page import="java.util.List"%>
<%@page import="neopro.metier.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyAccueil">
    <div class="container">
        <div class="row">
            <%@include file="../layout/navbar.jsp" %>
        </div>
        <div class="row">
            <div class="col-md-2">
                <%@include file="../layout/menu.jsp" %>
            </div>
            <div id ="col-md-10" class="col-md-10">

                <%  //String search = request.getParameter("searchWord");
                    List<Article> liste_articles = (List<Article>)request.getAttribute("liste_articles");
                    List<Article> listeArticlesPromo = MethodesDAO.listePromo();
                    List<Article> listeArticlesNonPromo = MethodesDAO.listeNonPromo();
                    //List<Article> listeSearch = MethodesDAO.listRecherche(search);
                    List<Article> listeArticles = new ArrayList();
                    listeArticles.addAll(liste_articles);
                    int numCol = 3;
                    int colCount = 0;

                    for (Article a : listeArticles) {

                %>

                <%!
                    private float produitPromo;
                %>

                <% if (colCount % numCol == 0) {
                        out.print("<div class='row' style='width:100%;'>");
                        colCount++;
                    } %>
                <div class="col-md-4 cardProduit">


                    <div class="card">
                        <%if (listeArticlesPromo.contains(a)) {
                                out.print("<span class='spanPromo'>Promotion </span>");
                                produitPromo = MethodesDAO.calculerPrixPromo(a.getIdArt());
                                ;
                            }   %>

                        <img class="card-img imgProduit" src="${pageContext.request.contextPath}/image/<%out.print(a.getUrlImageArt());%>" alt="Vans">
                        <div class="card-body">
                             <a> <h5 class="card-title"><% out.print(a.getLibelleArt());%></h5></a><br>
                            <P class="card-subtitle mb-2 text-muted">
                                <span class="infosproduits"><% out.print(a.getFormatArt());%></span>
                                <% if(a.getCondArt()!=null)
                                    {
                                %>
                                <span class="infosproduits"><% out.print(a.getCondArt()); %> </span>
                                <% } %>
                                <% 
                                    out.print(a.getPrixKgArt()); 
                                %>
                            </P>
                            <p>
                                <% if (a.getNutriscoreArt()==NutriscoreArticle.A){%>
                                <img class="card-img imgProduit" src="${pageContext.request.contextPath}/image/nutri_a.png" alt="Vans">
                                <%}%>
                                <% if (a.getNutriscoreArt()==NutriscoreArticle.B){%>
                                <img class="card-img imgProduit" src="${pageContext.request.contextPath}/image/nutri_b.png" alt="Vans">
                                <%}%>
                                <% if (a.getNutriscoreArt()==NutriscoreArticle.C){%>
                                <img class="card-img imgProduit" src="${pageContext.request.contextPath}/image/nutri_c.png" alt="Vans">
                                <%}%>
                                <% if (a.getNutriscoreArt()==NutriscoreArticle.D){%>
                                <img class="card-img imgProduit" src="${pageContext.request.contextPath}/image/nutri_d.png" alt="Vans">
                                <%}%>
                                <% if (a.getNutriscoreArt()==NutriscoreArticle.E){%>
                                <img class="card-img imgProduit" src="${pageContext.request.contextPath}/image/nutri_e.png" alt="Vans">
                                <%}%>  
                                
                            </p>
                            <p class="card-text">  </p>
                            <div class="buy d-flex justify-content-between align-items-center">
                                <div class="price text-success">
                                    <h5 class="mt-4">
                                        <%if (!listeArticlesPromo.contains(a)) {
                                                out.print(a.getPrixArt() + " €");
                                            } else {

                                                out.print("<span class='spanPrixSansPromo'>" + a.getPrixArt() + "€ </span>");
                                                out.print("<span class='spanPrixAvecPromo'>" + (a.getPrixArt() - produitPromo) + " € </span>");

                                            }%>
                                    </h5></div>
                                <a href="CtrlInserer?idArt=<%out.print(a.getIdArt());%>" class="btn btn-secondary mt-3"><i class="fas fa-shopping-cart"></i>Panier</a>
                                <%                                    
                                    if (request.getSession().getAttribute("idClient")!=null ){       
                                        //long idClient=(long) request.getSession().getAttribute("idClient");
                                        long idClient = ((Number) request.getSession().getAttribute("idClient")).longValue();
                                        if (MethodesDAO.getListeCourses(idClient).size()!=0){
                                        
                                %>
                                <ul class="nav flex-column mt-3">
                                    <li class="nav-item">
                                        <div class="btn-group dropend">
                                            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                Liste
                                            </button>
                                            <ul class="dropdown-menu">
                                                <%
                                                    ArrayList<ListeCourses> listeListeCourses=MethodesDAO.getListeCourses(idClient);   
                                                    for (ListeCourses lc: listeListeCourses){
                                                        out.print("<li><a class=dropdown-item href=CtrlInserer?idListeCourses="+a.getIdArt()+","+lc.getIdLis()+">"+lc.getNomLis()+"</a></li>");
                                                    }
                                                %>
                                            </ul>
                                        </div>    
                                    </li>
                                </ul>
                                <%  } } %>
                            </div>
                        </div>
                    </div>
                </div>
                <%}
                    if (colCount % numCol == 0) {
                        out.print(" </div>");
                    }
                %>
                </div>
            </div>
        </div>
</body>
<%@include file="../layout/footerFix.jsp" %>

