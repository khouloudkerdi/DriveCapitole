<%-- 
    Document   : ListeArticle
    Created on : 2021年3月22日, 下午2:16:13
    Author     : 13520
--%>


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
               
                <%  
                    String search = request.getParameter("searchWord");                    
                    List<Article> listeArticlesPromo = MethodesDAO.listePromo();
                    List<Article> listeArticlesNonPromo = MethodesDAO.listeNonPromo();
                    List<Article> listeSearch = MethodesDAO.listRecherche(search);
                    List<Article> listeArticles = new ArrayList();
                    listeArticles.addAll(listeSearch);                    

                    for (Article a : listeArticles) {
                        
                %>
                
                <%!
                    private int numCol = 3;
                    private int colCount = 0;
                    private float produitPromo;
                %>

                <% if (colCount % numCol == 0) {
                        out.print("<div class='row'>");
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
                            <a> <h5 class="card-title"><% out.print(a.getLibelleArt());%></h5></a>
                            <h6 class="card-subtitle mb-2 text-muted"> <b>Format : </b> <% out.print(a.getFormatArt());%> </h6>
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
                                    <a href="Panier" class="btn btn-info mt-3">Liste</a> 
                                </div>
                        </div>
                    </div>
                </div>
                <%}
                    if (colCount % numCol == 0) {
                        out.print(" </div>");
                    }
%></div></div></div>
</body>
<%@include file="../layout/footerFix.jsp" %>