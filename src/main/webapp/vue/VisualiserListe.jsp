<%-- 
    Document   : VisualiserPanier
    Created on : 22 mars 2021, 11:55:33
    Author     : 21911890
--%>

<%@page import="neopro.metier.Client"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.Article"%>
<%@page import="neopro.dao.MethodesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  %>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyPanier">
    <form action="CtrlValiderPanier" method="GET">
        <a href="CtrlMenu?method=Liste" role="button" aria-expanded=false aria-controls="collapseExample" class="btn btn-warning" style="margin-left: 10px;margin-top: 5px;" >Retour</a>
        <div class="container">
            <%-- Titre --%>
            <div class="row headerPanier">
                <% long idListeCourses = Long.parseLong((String) request.getParameter("idListe"));
                    out.println("<h3> Liste de courses : " + MethodesDAO.loadListeCourses(idListeCourses).getNomLis() + "</h3>");
                %>
            </div>
            
            <%-- Contenu --%>
            <div class="row" >
                <%-- Produits --%>
                <div class="col-md-8">
                    <h4>Détails de la liste de courses</h4>
                    <%
                        ArrayList<Article> listeArt = MethodesDAO.articleListeCourses(idListeCourses);
                        List<Article> listeArticlesPromo = MethodesDAO.listePromo();
                        for (Article a : listeArt) {
                    %>
                    <%!
                        private float produitPromo;
                    %>
                    <div class="panierProduit">
                        <div class="row">
                            <%-- promo & image --%>
                            <div class="col-md-3 colPrixQte"> 
                                <div class="row"> 
                                    <%if (listeArticlesPromo.contains(a)) {
                                            out.print("<span class='spanPromo'>Promotion</span></br>");
                                        }
                                    %>
                                </div>
                                <div class="row atCenterAll">
                                    <div><img src="${pageContext.request.contextPath}/image/<% out.print(a.getUrlImageArt()); %>"></div>
                                </div>
                            </div>
                            <%-- infos produit --%>
                            <div class="col-md-9" >
                                <div class="row styleLib" style="height: 50px;"><% out.print(a.getLibelleArt()); %></div>
                                <div class="row">
                                    <div class="col-sm-2 styleFontGris">
                                        <span class="cadreProduit"><% out.print(a.getFormatArt()); %></span>
                                    </div>
                                    <div class="col-sm-4">
                                        <%if (!listeArticlesPromo.contains(a)) {
                                                out.print(a.getPrixArt() + " €");
                                            } else {
                                                out.print("<span class='spanPrixSansPromo'>" + a.getPrixArt() + "€ </span>");
                                                out.print("<span class='spanPrixAvecPromo'>" + (a.getPrixArt() - produitPromo) + " € </span>");
                                            }%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>

                <%-- Boutton <Ajouter au panier> --%>
                <div class="col-md-4">
                    <h4 style="font-size: medium;">* Voulez-vous ajouter tous les articles à votre panier ?<h4>
                    <div class="atCenterHorizontal">
                        <%
                            out.println("<a role=\"button\" aria-expanded=false aria-controls=\"collapseExample\" class=\"btn btn-primary\"href=\"CtrlTransmettreListePanier?idListeCourses=" + idListeCourses + "\" >Ajouter au panier </a>");
                        %>
                    </div>
                </div>
                    
            </div>
        </div>
    </form>
</body>
<%@include file="../layout/footerFix.jsp" %>
