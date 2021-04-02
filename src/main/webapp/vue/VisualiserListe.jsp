<%-- 
    Document   : VisualiserPanier
    Created on : 22 mars 2021, 11:55:33
    Author     : 21911890
--%>

<%@page import="neopro.metier.Postit"%>
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
    <div class="container">
        <%-- Titre --%>
        <div class="row headerPanier">
            <% long idListeCourses = Long.parseLong((String) request.getParameter("idListe"));
                out.println("<h3> Liste de courses : " + MethodesDAO.loadListeCourses(idListeCourses).getNomLis() + "</h3>");
            %>
        </div>

        <%-- Contenu --%>
        <div class="row" >
            <div class="col-md-6">
                <h4>Liste des post'it </h4>
                 <form action=CtrlPostIt method=get>
                        <div class="btnContainer" style="margin: 10px 20px;">
                            <input class="form-control" type="text" name="postit" required  style="width: 220px;">

                            <%
                                out.println("<input type=hidden  name=idListe value=" + idListeCourses + ">");
                            %>
                            <input type="submit" value="Ajouter Post-it" class="btn btn-info">       
                        </div>           
                    </form>
              
                <div id="carousContenu">
                       
                        <%
                            List<Postit> listePostit = MethodesDAO.loadPostIt(idListeCourses);
                            for (Postit p : listePostit) {
                                 
                                // out.println("<p>" + p1.getContenuPos() + "</p>");
                        %>
                      
                        <button style="height: 100px;width: 100px; margin-right: 5px;" value="<%out.println(p.getIdPos());%>"><%out.println(p.getContenuPos());%></button>
                        
                        <%
                            }
                        %>

                    </div>
                   
            </div>
            <%-- Produits --%>
            <div class="col-md-6">
                <h4>Liste des produits proposés </h4>
                <% out.println("<input type='hidden' id='idListe' name='ListeCourse' value=" + idListeCourses + ">");%>
                <form action='CtrlPostitPref' method='get' id="produitProposes">

                </form>

            </div>
        </div>
        <div class="row" >
            <div class="col-md-8">
                <h4 style="margin-top: 10px;">Liste des produits </h4>
                <%
                    ArrayList<Article> listeArt = MethodesDAO.articleListeCourses(idListeCourses);
                    for (Article a : listeArt) {
                %>

                <div class="panierProduit">
                    <div class="row">
                        <%--1ere partie: promo, image, cond--%>
                        <div class="col-md-4"> 
                            <div class="atCenterHorizontal"><img src="${pageContext.request.contextPath}/image/<% out.print(a.getUrlImageArt()); %>"></div>
                            <div class="styleFontGris atCenterHorizontal"><% out.print(a.getCondArt()); %></div>
                        </div>

                        <%--2eme partie: libelle, format, typePromo(pourcentage), montantTotalArticle--%>
                        <div class="col-md-4" >
                            <div class="styleLib"><% out.print(a.getLibelleArt()); %></div>
                            <div class="row">
                                <div class="col-sm-6 styleFontGris"><% out.print(a.getFormatArt()); %></div>

                            </div>
                        </div>
                         <div class="col-md-4" >
                             <div style="color: red;"><% out.print(a.getPrixArt()+"€"); %></div>
                           <div><% out.print(a.getPrixKgArt()); %></div>
                            
                        </div>

                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
            <div class="d-flex justify-content-center" style="margin-bottom: 20px;">
                    <div class="atCenterHorizontal">
                        <%
                            out.println("<a role=\"button\" class=\"btn btn-info\"href=\"CtrlTransmettreListePanier?idListeCourses=" + idListeCourses + "\" >Ajouter au panier </a>");
                        %>
                    </div>
                    <a href="Accueil" class="btn btn-info" style="margin-left: 30px;">Retour </a>
                </div>
    </div>
</body>
<%@include file="../layout/footerFix.jsp" %>
