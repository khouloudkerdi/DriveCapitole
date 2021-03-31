<%-- 
    Document   : ConfirmRetrait
    Created on : 2021-3-31, 9:27:24
    Author     : Xinyan
--%>

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
    <form action="" method="GET">
        <div class="container">

            <%-- Header Confirmation --%>
            <div class="row headerPanier">
                <h3>Récapitulatif</h3>
            </div>

            <%-- Contenu Confirmation --%>
            <div class="row" >
                <%-- Choix --%>
                <div class="col-md-8">
                    <%-- Choix du Magain --%>
                    <div>
                        <h4>Votre Drive</h4>
                        <%
                            long idClient = (long) request.getSession().getAttribute("idClient");
                            Magasin mag; 
                            if (request.getSession(false).getAttribute("idMag")!=null){
                                mag = MethodesDAO.getMagByIdMag((long)request.getSession(false).getAttribute("idMag"));
                            } else {
                                mag = MethodesDAO.getMagByIdCli(idClient);
                            }                           
                            
                        %>
                       
                        <div class="recapPanier row">
                            <div class="col-md-8">
                            <div class="recapPanierInfo">
                                <span class="titreValeurPanier"><% out.print(mag.getNomMag());%></span>
                            </div>
                            <div class="recapPanierInfo">
                                <span class="titreValeurPanier"><% out.print(mag.getAdresseMag());%></span>         
                            </div>
                        
                            </div>
                        
                            <div class="col-md-4 atCenterAll" style="height: 100%;">
                                <button class="btn btn-info" >Modifier</button>
                            </div>
</div>
                    </div>


                    <%-- Choix du Creneau--%>
                    <div class="row">
                        <h4>Créneau</h4>
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
                        </div>
                    </div>
                    <%--Button Confirmer--%>
                    <div class="btnContainer">
                        <button type="submit" class="btn btn-info">Confirmer</button>
                        <a href="Panier" class="btn btn-info">Retour</a>
                    </div>
                    <div class="messageErreur">${requestScope.msg_erreur}</div>
                </div>
            </div>
        </div>
    </form>

</body>
<%@include file="../layout/footerFix.jsp" %>