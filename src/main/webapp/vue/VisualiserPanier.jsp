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
    <%-- <a href="Accueil" style="padding-left: 10px;padding-top: 5px;">Retour</a> --%>
    <div class="container">
        <div class="row headerPanier">
            <h3>Votre panier</h3>
        </div>
        
        <div class="row" >
            <div class="col-md-8">
                <h4>Détails panier</h4>
                <% long idcli = 1;
                    ArrayList<Article> listeArt = MethodesDAO.listeArtcilesPanierClient(idcli);
                    Client client = MethodesDAO.infosClient(idcli);
                %>
                <% long idp = 1l; %> 
                <% float montant = MethodesDAO.montantPanier(idp); %>
                <% List<Article> listeArticlesPromo = MethodesDAO.listePromo(); 
                   float montantEconomieTotal=0f;
                   
                %>
                <% for (Article a : listeArt) { %>
                <%! private float produitPromo;
                    private int pourcentage;
                   
                %>
                <%  int qte = MethodesDAO.QuantiteArticlePanier(idcli, a.getIdArt()); %>
                <% float montantArticle = MethodesDAO.montantTotaleArticlePanier(idp, a.getIdArt());

                %>
                <div class="panierProduit">
                    <div class="row">
                        <%--1ere partie: promo, image, cond--%>
                        <div class="col-md-3"> 
                            <%if (listeArticlesPromo.contains(a)) {
                                    out.print("<span class='spanPromo atCenterHorizontal'>Promotion</span></br>");
                                    produitPromo = MethodesDAO.calculerPrixPromo(a.getIdArt());
                                    pourcentage = MethodesDAO.getPromoPourcentage(a.getIdArt());
                                }
                            %>
                            <div class="atCenterHorizontal"><img src="${pageContext.request.contextPath}/image/<% out.print(a.getUrlImageArt()); %>"></div>
                            <div class="styleFontGris atCenterHorizontal"><% out.print(a.getCondArt()); %></div>
                        </div>

                        <%--2eme partie: libelle, format, typePromo(pourcentage), montantTotalArticle--%>
                        <div class="col-md-6" >
                            <div class="styleLib"><% out.print(a.getLibelleArt()); %></div>
                            <%if (listeArticlesPromo.contains(a)) {
                                    out.print("<div class='row'>");
                                    out.print("<div class='col-sm-6'> </div>");
                                    out.print("<div class='col-sm-6 spanPromo atCenterHorizontal'>" + pourcentage + "% d'économies</div>");
                                    out.print("</div>");
                                }   %>
                            <div class="row">
                                <div class="col-sm-6 styleFontGris"><% out.print(a.getFormatArt()); %></div>
                                <div class="col-sm-6 atCenterHorizontal"><% out.print(montantArticle); %>€</div>
                            </div>
                        </div>

                        <%--3eme partie: button, qte, montantEcoTotalArticle--%>
                        <div class="col-md-3 colPrixQte">
                            <div class="atCenterAll">
                                <div class="produitOptions">
                                    <button type="button" action="moins" class="btn btn-secondary btn-sm">-</button>
                                    <span class="qteProduit"><% out.print(qte); %></span>
                                    <button type="button" action="plus" class="btn btn-success btn-sm">+</button>
                                </div>
                            </div>
                            <%if (listeArticlesPromo.contains(a)) {
                                    float montanteco = produitPromo * qte;
                                    BigDecimal montantEcoDecim = new BigDecimal(montanteco);
                                    float eco = montantEcoDecim.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                                    out.print("<div class='atCenterHorizontal'><span class='spanPromo'>" + eco + " € d'éco!</span></div>");
                                    montantEconomieTotal = montantEconomieTotal + eco;
                                }
                            %>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            
            <div class="col-md-4">
                
                <%--Partie Récapitulatif--%>
                <h4>Paiement</h4>
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
                <%--Button Valider--%>
                <div class="atCenterHorizontal">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
                <div class="messageErreur">${requestScope.msg_erreur}</div>
            </div>
        </div>
    </div>
    </form>

</body>
<%@include file="../layout/footerFix.jsp" %>