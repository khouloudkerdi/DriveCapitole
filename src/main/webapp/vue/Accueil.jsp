<%-- 
    Document   : ListeArticle
    Created on : 2021年3月22日, 下午2:16:13
    Author     : 13520
--%>


<%@page import="java.util.List"%>
<%@page import="neopro.metier.Article"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="../layout/headerFix.jsp" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("path", basePath);
%>
<body id="bodyAccueil">
    <div class="container">
        <div class="row">
            <%@include file="../layout/navbar.jsp" %>
        </div>

        <div class="row">
            <div class="col-md-2">
                <%@include file="../layout/menu.jsp" %>
            </div>
            <div class="col-md-10 containerArticle">

                <div class="row row-cols-1 row-cols-md-3 g-4 containerCard" >
                    <% List<Article> liste = MethodesDAO.listeArticle();
                        for (Article a : liste) {
                            out.println("<div class=col>");
                            out.println("<div class=card h-100>");
                            
                            out.println("<div class=card-body>");
                            out.println("<h5 class=card-title>" + a.getLibelleArt() + "</h5>");
                            out.println("<div class=div_image_size>");
                            out.println("<img src=" + basePath + "image/" + a.getUrlImageArt() + " class=card-img-top>");
                            out.println("</div>");
                            out.println("<font size=10 color=red>" + a.getPrixArt() + " €</font> &nbsp " + a.getTypePrix() + "&nbsp&nbsp" + a.getCondArt() + " <br/>");
                            out.println("<font size=2>Format: " + a.getFormatArt() + "</font>");
                            if (a.isBioArt()) {
                                out.println("&nbsp<font size=3 color=green>BIO</font> ");
                            }
                            if (a.getNutriscoreArt() != null) {
                                out.println("<font size=2>&nbsp  Nutriscore: " + a.getNutriscoreArt() + "</font>");
                            }
                            out.println("<br/><button type=button class=btn btn-primary btn-sm value=" + a.getIdArt() + ">Ajouter au panier</button><br/>");
                            out.println("<button type=button class=btn btn-primary btn-sm>Ajouter à la liste de courses</button>");
                            out.println("</div></div></div>");
                        }
                    %>
                    
                </div>
               


            </div>                

        </div>



</body>
<%@include file="../layout/footerFix.jsp" %>
