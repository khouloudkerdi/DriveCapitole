<%-- 
    Document   : VisualiserPanier
    Created on : 22 mars 2021, 11:55:33
    Author     : 21911890
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.Article"%>
<%@page import="neopro.dao.MethodesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyPanier">
    <h3>Visualisation du panier  </h3>
    <div class="container">
        <div class="row">
            <div class="row" >
                <div class="col-md-8">
                    <h4> Panier </h4>
                    <% ArrayList<Article> listeArt = MethodesDAO.listeArtcilesPanierClient(1l); %>
                    <% float montant = MethodesDAO.montantPanier(1l); %>
                    <% for (Article a :listeArt){ %>
                    <% int qte = MethodesDAO.QuantiteArticlePanier(1l,a.getIdArt()); %>
                    <div class="panierProduit">
                        <div class="row">
                            <div class="col-md-3"> <img src="${pageContext.request.contextPath}/image/<% out.print(a.getUrlImageArt()); %>"></div>
                            <div class="col-md-3" ><% out.print(a.getLibelleArt()); %></div>
                            <div class="col-md-3"></div> 
                            <div class="col-md-3 colPrixQte">
                                <div><span class="produitPrix"><% out.print(a.getPrixArt()); %>€ </span></div>
                                <div class="produitOptions">
                                    <button type="button" action="moins" class="btn btn-secondary btn-sm">-</button>
                                    <span class="qteProduit"><% out.print(qte); %></span>
                                    <button type="button" action="plus" class="btn btn-success btn-sm">+</button>
                                </div>

                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="col-md-4">
                    <h4> Récapitulatif </h4>
                    <div class="recapPanier">
                        <div class="recapPanierInfo">
                            <span class="titreValeurPanier">Panier</span>
                            <span class="valeurPanier"><% out.print(montant);%> € </span>    
                        </div>
                        <div class="recapPanierInfo">
                            <span class="titreEconomiePanier">Economie</span>
                            <span class="valeurEconomiePanier">... € </span>    
                        </div>
                        <div class="recapPanierInfo">
                            <span class="titreTotalPanier">Total</span>
                            <span class="valeurTotalPanier">... € </span>    
                        </div>
                    </div>
                </div>


            </div>

        </div>


    </div>


</body>
<%@include file="../layout/footerFix.jsp" %>
