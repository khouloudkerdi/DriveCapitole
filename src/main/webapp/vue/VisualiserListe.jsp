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
   
    <div class="container">
        <div class="row headerPanier">
            <% long idListeCourses=Long.parseLong((String) request.getParameter("idListe")); 
                out.println("<h3> Liste de courses : "+MethodesDAO.loadListeCourses(idListeCourses).getNomLis()+"</h3>");
            %>
        </div>
        
        <div class="row" >
            <div class="col-md-8">
                <h4>Détails liste de courses</h4>
                <%     
                    ArrayList<Article> listeArt=MethodesDAO.articleListeCourses(idListeCourses);
                    for (Article a : listeArt) { 
                %>

                <div class="panierProduit">
                    <div class="row">
                        <%--1ere partie: promo, image, cond--%>
                        <div class="col-md-3"> 
                            <div class="atCenterHorizontal"><img src="${pageContext.request.contextPath}/image/<% out.print(a.getUrlImageArt()); %>"></div>
                            <div class="styleFontGris atCenterHorizontal"><% out.print(a.getCondArt()); %></div>
                        </div>

                        <%--2eme partie: libelle, format, typePromo(pourcentage), montantTotalArticle--%>
                        <div class="col-md-6" >
                            <div class="styleLib"><% out.print(a.getLibelleArt()); %></div>
                            <div class="row">
                                <div class="col-sm-6 styleFontGris"><% out.print(a.getFormatArt()); %></div>
                      
                            </div>
                        </div>

                    </div>
                </div>
                <%
                    }
                %>
            </div>
            
            <div class="col-md-4">
                <%--Button Valider--%>
                <div class="btnContainer">
                    <%
                        out.println("<a role=\"button\" aria-expanded=false aria-controls=\"collapseExample\" class=\"btn btn-info\"href=\"CtrlTransmettreListePanier?idListeCourses="+idListeCourses+"\" >Ajouter au panier </a>");
                    %>
                     <a href="CtrlMenu?method=Liste" class="btn btn-info" >Retour</a>
                </div>
          
            </div>
            
         
        </div>
    </div>
    </form>

</body>
<%@include file="../layout/footerFix.jsp" %>
