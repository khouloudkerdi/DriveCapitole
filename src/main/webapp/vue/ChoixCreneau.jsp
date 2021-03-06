<%-- 
    Document   : ConfirmRetrait
    Created on : 2021-3-31, 9:27:24
    Author     : Xinyan
--%>

<%@page import="neopro.metier.Creneau"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
                        if (request.getSession(false).getAttribute("idMag") != null) {
                            mag = MethodesDAO.getMagByIdMag((long) request.getSession(false).getAttribute("idMag"));
                            //out.print("idMag = "+mag);
                        } else {
                            mag = MethodesDAO.getMagByIdCli(idClient);
                            long idMag = mag.getIdMag();
                            request.getSession().setAttribute("idMag", idMag);
                        }

                    %>

                    <div class="recapPanier" id="divmonDrive">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="recapPanierInfo nomMag">
                                    <input type="hidden" id="idMag" name="idMag" value="<% out.print(mag.getIdMag());%>">
                                    <span class="titreValeurPanier"><% out.print(mag.getNomMag());%></span>
                                </div>
                                <div class="recapPanierInfo">
                                    <span class="titreValeurPanier"><% out.print(mag.getAdresseMag());%></span>         
                                </div>

                            </div>

                            <div class="col-md-4 atCenterAll">
                                <button class="btn btn-info btnChoixMag" id="btnModifierMag"  >Modifier</button>
                            </div>
                        </div>
                    </div>

                </div>
                <div id="rechercheMag" class="rechercheCP">
                    <div class="recapPanierInfo"> Code postal : <input type="Text" name="codepostal" id="codepostal" >
                        <button class="btn btn-info" id="validerchoix">Valider</button>
                        <button class="btn btn-secondary" id="annulerchoix">Annuler</button>
                    </div>
                    <div id="lmagasins"></div>
                </div>

                <%-- Choix du Creneau--%>
                
                <div>
                    <h4>Créneau</h4>

                    <div class="recapPanier" id="divmonDrive">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="recapPanierInfo">
                                    <p>Sélectionnez une date </p> 
                                    <%
                                        SimpleDateFormat formatt = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                        Date d = new java.util.Date();
                                        Calendar c = Calendar.getInstance();
                                        c.setTime(d);
                                        c.add(Calendar.DAY_OF_MONTH, 7);
                                        Date d2 = c.getTime();
                                        String strDate = formatt.format(d).toString();
                                        String strDate2 = formatt.format(d2).toString();

                                    %>
                                    <input type="date" id="choixdateCre" name="choixdateCre" value="<% out.print(strDate);%>" min="<% out.print(strDate);%>" max="<% out.print(strDate2);%>">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <% List<Creneau> listeC = MethodesDAO.getCreneau(mag.getIdMag(), strDate); %>
                                <div class="recapPanierInfo">

                                    <div id="afficherCrenaux"  style="display:<%if (listeC.size() != 0) {
                                            out.print("block");
                                        } else {
                                            out.print("none");
                                        } %>;">
                                        <p>Sélectionnez un créneau </P>   
                                        <select id="listeCreneau">
                                            <%
                                                for (Creneau cre : listeC) {
                                            %>
                                            <option value="<% out.print(cre.getIdCre());%>"><% out.print(cre.getHeure());%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" id="msgIndispo" style="display:<% if (listeC.size() != 0) {
                                out.print("none");
                            } else {
                                out.print("block");
                            } %>;">

                            <div class="recapPanierInfo msgDesole">
                                Désolé les Créneaux pour ce jour ne sont plus disponibles !
                            </div>     
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
                    </div>
                </div>
                <%--Button Confirmer--%>
                <div class="btnContainer">
                    <button type="button" id="btnConfirmerChoix" class="btn btn-info">Confirmer</button>
                    <a href="Panier" class="btn btn-info">Retour</a>
                </div>
                <div class="messageErreur">${requestScope.msg_erreur}</div>
            </div>
        </div>
    </div>


</body>
<script src="${pageContext.request.contextPath}/js/choixMagasinCreneau.js"></script>
<%@include file="../layout/footerFix.jsp" %>
