<%-- 
    Document   : listeArticle
    Created on : 2021年3月29日, 上午9:40:34
    Author     : rende
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div>
    <% 

        int numCol = 3;
        int colCount = 0;

        for (Article a : listeArticles) {
    %>
    
    <%!
        //private float produitPromo;
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
                } else {
                    out.print("<span class='spanNonPromo'>&nbsp</span>");
                }
            %>
             <img class="card-img imgProduit float-left" src="${pageContext.request.contextPath}/image/<%out.print(a.getUrlImageArt());%>" alt="imageProduit">


             <div class="card-body">
                <a> <h5 class="card-title"><% out.print(a.getLibelleArt());%></h5></a>
                <P>
                    <span class="infosproduits"><% out.print(a.getFormatArt());%></span>
                    <% if (a.getCondArt() != null) {
                    %>
                    <span class="infosproduits"><% out.print(a.getCondArt()); %> </span>
                    <% } %>
                    <span >
                        <%
                            out.print(a.getPrixKgArt());
                        %>
                    </span>  
                </P>
                <p>
                    <% if (a.getNutriscoreArt() == NutriscoreArticle.A) {%>
                    <img class="card-img imgNutriScore" src="${pageContext.request.contextPath}/image/nutri_a.png" alt="imgNutriScore">
                    <%}%>
                    <% if (a.getNutriscoreArt() == NutriscoreArticle.B) {%>
                    <img class="card-img imgNutriScore" src="${pageContext.request.contextPath}/image/nutri_b.png" alt="imgNutriScore">
                    <%}%>
                    <% if (a.getNutriscoreArt() == NutriscoreArticle.C) {%>
                    <img class="card-img imgNutriScore" src="${pageContext.request.contextPath}/image/nutri_c.png" alt="imgNutriScore">
                    <%}%>
                    <% if (a.getNutriscoreArt() == NutriscoreArticle.D) {%>
                    <img class="card-img imgNutriScore" src="${pageContext.request.contextPath}/image/nutri_d.png" alt="imgNutriScore">
                    <%}%>
                    <% if (a.getNutriscoreArt() == NutriscoreArticle.E) {%>
                    <img class="card-img imgNutriScore" src="${pageContext.request.contextPath}/image/nutri_e.png" alt="imgNutriScore">
                    <%}%>  

                </p>
                <div>
                    <div class="prixProduit">                  
                        <%if (!listeArticlesPromo.contains(a)) {
                                out.print(a.getPrixArt() + " €");
                            } else {
                                //out.print("<span class='spanPrixAvecPromo'>" + (a.getPrixArt() - produitPromo) + " € </span><br>");
                                out.print("<span class='spanPrixSansPromo'>" + a.getPrixArt() + "€ </span>");

                            }%>
                    </div>
                    <div class="btnAccueil">
                        <a href="CtrlInserer?idArt=<%out.print(a.getIdArt());%>" 
                           class="btn btn-secondary btn-sm">Panier</a>
                        <%
                            if (request.getSession().getAttribute("idClient") != null) {
                                //long idClient=(long) request.getSession().getAttribute("idClient");
                                long idClient = ((Number) request.getSession().getAttribute("idClient")).longValue();
                                if (MethodesDAO.getListeCourses(idClient).size() != 0) {

                        %>
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <div class="btn-group dropend">
                                    <button type="button" class="btn btn-primary dropdown-toggle btn-sm " data-bs-toggle="dropdown" aria-expanded="false">
                                        Liste
                                    </button>
                                    <ul class="dropdown-menu">
                                        <%                                                    ArrayList<ListeCourses> listeListeCourses = MethodesDAO.getListeCourses(idClient);
                                            for (ListeCourses lc : listeListeCourses) {
                                                out.print("<li><a class=dropdown-item href=CtrlInserer?idListeCourses=" + a.getIdArt() + "," + lc.getIdLis() + ">" + lc.getNomLis() + "</a></li>");
                                            }
                                        %>
                                    </ul>
                                </div>    
                            </li>
                        </ul>
                        <%  }
                        } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%}
        if (colCount % numCol == 0) {
            out.print(" </div>");
        } else {
            int nb = colCount-(colCount/numCol)*numCol;
    %>
    <div class="col-md-4 cardProduit">    
    <%
            out.print(nb+"</div>");
        }
    %>
</div>

