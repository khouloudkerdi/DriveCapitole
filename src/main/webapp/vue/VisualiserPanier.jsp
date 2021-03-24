<%-- 
    Document   : VisualiserPanier
    Created on : 22 mars 2021, 11:55:33
    Author     : 21911890
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyPanier">
    <h3>Visualisation du panier  </h3>
    <div class="container-fluid">
        <div class="row">
            <div class="row" >
                <div class="col-md-8">
                    <h4> Panier </h4>
                    <% for (int i = 0; i < 3; i++) {%>

                    <div class="panierProduit">
                        <div class="row">
                            <div class="col-md-3"> <img src="${pageContext.request.contextPath}/image/noix.JPG"></div>
                            <div class="col-md-3" > Titre produit </div>
                            <div class="col-md-3"></div> 
                            <div class="col-md-3 colPrixQte">
                                <div><span class="produitPrix">...€ </span></div>
                                <div class="produitOptions">
                                    <button type="button" action="moins" class="btn btn-secondary btn-sm">-</button>
                                    <span class="qteProduit">1</span>
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
                            <span class="valeurPanier">... € </span>    
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
