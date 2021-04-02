<%-- 
    Document   : ListeArticle
    Created on : 2021年3月22日, 下午2:16:13
    Author     : 13520
--%>


<%@page import="neopro.metier.Client"%>
<%@page import="java.util.HashSet"%>
<%@page import="neopro.metier.Label"%>
<%@page import="java.util.Set"%>
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
            <%
                long id = ((Number) request.getSession().getAttribute("idClient")).longValue();
                //long id = (long) request.getSession().getAttribute("idClient");
                Client client = MethodesDAO.infosClient(id);

            %>
            <span style="margin-left: 5px;"><% out.print("Bonjour " + client.getPrenomCli());%></span>
        </div>
        <div class="row">
            <div class="col-md-2">
                <%@include file="../layout/menu.jsp" %>
            </div>
            <div class="col-md-10">
                <%                      List<Article> liste_articles = MethodesDAO.listeArticle();
                    List<Article> liste_articlesPref = MethodesDAO.listePref(id);
                    List<Article> listeArticlesPromo = MethodesDAO.listePromo();
                    List<Article> listeArticlesNonPromo = MethodesDAO.listeNonPromo();

                    List<Article> autresArticle = MethodesDAO.listeDansListe(liste_articles, liste_articlesPref);
                    List<Article> autresArticleNonPromo = MethodesDAO.listeDansListe(autresArticle, listeArticlesPromo);
                    List<Article> autresArticlePromo = MethodesDAO.listeDansListe(autresArticle, listeArticlesNonPromo);

                    List<Article> listeArticles = new ArrayList();
                    listeArticles.addAll(liste_articlesPref);
                    listeArticles.addAll(autresArticlePromo);
                    listeArticles.addAll(autresArticleNonPromo);
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
                        <div style="display: flex; ">
                            <%if (listeArticlesPromo.contains(a)) {
                                    out.print("<span class='spanPromo'>Promotion</span>");
                                    produitPromo = MethodesDAO.calculerPrixPromo(a.getIdArt());
                                } else if (!listeArticlesPromo.contains(a)) {
                                    out.print("<span class='spanNonPromo'>&nbsp</span>");
                                }
                            %>

                            <%if (liste_articlesPref.contains(a)) {
                                    out.print("<span class='spanPref'>&#10084;</i> </span>");

                                }   %>
                        </div>
                        <div class="atCenterHorizontal">
                            <img class="card-img imgProduit float-left" src="${pageContext.request.contextPath}/image/<%out.print(a.getUrlImageArt());%>" alt="imageProduit">

                            <%
                                List<Label> listeLabels = MethodesDAO.getLabelsArticle(a.getIdArt());
                                if (listeLabels.size() != 0) {
                                    for (Label l : listeLabels) { %>
                            <img class="imgLabel" src="${pageContext.request.contextPath}/image/<%out.print(l.getLibelleLab() + ".JPG");%>" >
                            <% }
                                }
                            %>

                        </div> 


                        <div class="card-body">
                            <a> <h5 class="card-title"><% out.print(a.getLibelleArt());%></h5></a>
                            <div class="label">
                                <span class="infosproduits"><% out.print(a.getFormatArt());%></span>
                                <% if (a.getCondArt() != null) {
                                %>
                                <span class="infosproduits"><% out.print(a.getCondArt()); %> </span>
                                <% } %>
                                <span class="spanPrixKGArt" >
                                    <%
                                        out.print(a.getPrixKgArt());
                                    %>
                                </span>  

                                <p class="nutriScore">
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
                            </div>

                            <div>
                                <div class="prixProduit">                  
                                    <%if (!listeArticlesPromo.contains(a)) {
                                            out.print(a.getPrixArt() + " €");
                                        } else {
                                            out.print("<span class='spanPrixAvecPromo'>" + (a.getPrixArt() - produitPromo) + " € </span>");
                                            out.print("<span class='spanPrixSansPromo'>" + a.getPrixArt() + "€ </span>");

                                        }%>
                                </div>
                                <div class="btnAccueil">
                                    <input class="inputIdArt" value="<%out.print(a.getIdArt());%>" type="hidden">
                                    <button name="btnPanierAccueil" class="btn btn-secondary btn-sm">Panier</button>
                                    <%

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
                                                    <%                                                        ArrayList<ListeCourses> listeListeCourses = MethodesDAO.getListeCourses(idClient);
                                                        for (ListeCourses lc : listeListeCourses) { %>
                                                        <input class="inputIdListe" value="<%out.print(lc.getIdLis());%>" type="hidden">
                                                    <%
                                                            out.print("<li><a class=\"dropdown-item\" name=\"idL\">" + lc.getNomLis() + "</a></li>");
                                                        }
                                                    %>
                                                </ul>
                                            </div>    
                                        </li>
                                    </ul>
                                    <%  }
                                    %>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
                <%}
                    if (colCount % numCol == 0) {
                        out.print(" </div>");
                    }%>
            </div>
        </div>
    </div>
    <%@include file="../layout/logOut.jsp" %>
    <script type="text/JavaScript" src="${pageContext.request.contextPath}/js/scriptAccueil.js"></script>
</body>
<%@include file="../layout/footerFix.jsp" %>
