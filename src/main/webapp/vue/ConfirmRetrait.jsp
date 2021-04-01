<%-- 
    Document   : ConfirmRetrait
    Created on : 2021-3-31, 9:27:24
    Author     : Xinyan
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="neopro.metier.Creneau"%>
<%@page import="neopro.metier.Magasin"%>
<%@page import="neopro.metier.Client"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.Article"%>
<%@page import="neopro.dao.MethodesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyPanier">
    <div class="container">

        <%-- Header Confirmation --%>
        <div class="row headerPanier">
            <h3>Confirmation de votre choix</h3>
        </div>

        <%-- Contenu Confirmation --%>
        <div class="row" >
            <%-- Choix --%>
            <div class="col-md-8">
                <h4>Votre choix du magasin et créneau</h4>
                <%-- Choix du Magain --%>
                <div class="panierProduit">
                    <div class="row">
                        <h4>Magasin</h4>
                        <%
                            long idMag = (long) request.getSession().getAttribute("idMag");
                            Magasin mag = MethodesDAO.getMagByIdMag((long) request.getSession(false).getAttribute("idMag"));
                        %>
                        <div class="recapPanierInfo nomMag atCenterAll">
                            <% out.print(mag.getNomMag());%>
                        </div>
                        <div class="recapPanierInfo atCenterAll">
                            <span class="titreValeurPanier"><% out.print(mag.getAdresseMag());%></span>         
                        </div>
                    </div>
                </div>
                <%-- Choix du Creneau--%>
                <div class="panierProduit">
                    <div class="row">
                        <h4>Créneau</h4>
                        <%
                            String date = (String) request.getSession().getAttribute("date");
                            String creneau = (String) request.getSession().getAttribute("creneau");
                        %>
                        <div class="recapPanierInfo nomMag atCenterAll">
                            <% out.print(date); %>
                        </div>
                        <div class="recapPanierInfo atCenterAll">
                            <% out.print(creneau); %>
                        </div>
                    </div>
                </div>
            </div>

            <%-- Detail Commande --%>
            <div class="col-md-4">
                <%--Partie Récapitulatif--%>
                <h4>Paiement</h4>
                <% long idcli = (long) request.getSession().getAttribute("idClient");
                    ArrayList<Article> listeArt = MethodesDAO.listeArtcilesPanierClient(idcli);
                    Client client = MethodesDAO.infosClient(idcli);
                %>
                <% long idp = MethodesDAO.getIdPanierByIdCli(idcli); %> 
                <% float montant = MethodesDAO.montantPanier(idcli); %>
                <% List<Article> listeArticlesPromo = MethodesDAO.listePromo();
                    float montantEconomieTotal = 0f;
                %>
                <% for (Article a : listeArt) { %>
                <%! private float produitPromo;
                %>
                <%  int qte = MethodesDAO.QuantiteArticlePanier(idp, a.getIdArt()); %>
                <% float montantArticle = MethodesDAO.montantTotaleArticlePanier(idp, a.getIdArt());
                %>
                <%if (listeArticlesPromo.contains(a)) {
                        produitPromo = MethodesDAO.calculerPrixPromo(a.getIdArt());
                        float montanteco = produitPromo * qte;
                        BigDecimal montantEcoDecim = new BigDecimal(montanteco);
                        float eco = montantEcoDecim.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                        montantEconomieTotal = montantEconomieTotal + eco;
                    }
                %>
                <% } %>
                <div class="recapPanier">
                    <div class="recapPanierInfo">
                        <span class="titreValeurPanier">Sous total :</span>
                        <span class="valeurPanier"><% out.print(montant);%> € </span>
                    </div>
                    <div class="recapPanierInfo">
                        <span class="titreEconomiePanier">Economie :</span>
                        <% BigDecimal montantEconomieTotalD = new BigDecimal(montantEconomieTotal);
                            float montantEcoTotalF = montantEconomieTotalD.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();%>
                        <span class="valeurEconomiePanier"><% out.print("<span class='spanPromo'>" + montantEcoTotalF + "€</span>");%>  </span>    
                    </div>
                    <div class="recapPanierInfo">
                        <span class="titreTotalPanier">Total à payer :</span>
                        <% float montantTotalPanier = montant - montantEconomieTotal;
                            BigDecimal montantTotalPanierD = new BigDecimal(montantTotalPanier);
                            float montantTotalAPayer = montantTotalPanierD.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();%>
                        <span class="valeurTotalPanier"> <% out.print(montantTotalAPayer);%> € </span>   
                    </div>
                </div>

                <%--Partie Fidélité--%>
                <h4>Avantages fidélités</h4>
                <div class="recapFidelite">
                    <div class="recapFideliteInfo">
                        <span class="titrePointsFidelite">Points disponibles :</span>
                        <span class="valeurPointsFidelite"><% out.print(client.getPointFedelitéCli()); %></span>                         
                    </div>
                    <div class="recapFideliteInfo">
                        <span class="titrePointsFidelite">Gain sur cette commande :</span>
                        <% float m = ((float) montantTotalPanier / 10);
                        %>
                        <span class="valeurPointsFidelite"><% out.print((int) (m));%></span>   
                        <% request.getSession().setAttribute("pointGagne", (int)m);%>
                    </div>
                </div>
                <%--Button Confirmer--%>
                <div class="btnContainer">
                    <%-- <a href="CtrlFinaliser" class="btn btn-info">Confirmer</a> --%>
                    <a class="btn btn-info" href="#" data-toggle="modal" data-target="#msgFinaliserCmd">
                        Confirmer
                    </a>                    
                    <a href="ChoixCreneau" class="btn btn-info">Retour</a>
                </div>
                <div class="messageErreur">${requestScope.msg_erreur}</div>
            </div>
        </div>
    </div>
<%@include file="../layout/msgFinaliserCommande.jsp" %>
</body>
<%@include file="../layout/footerFix.jsp" %>