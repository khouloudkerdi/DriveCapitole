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
<%@include file="../layout/navbar.jsp" %>
<%@include file="../layout/menu.jsp" %>
        <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.setAttribute("path", basePath);
        %>
    <body>
        <div id="contenuAccueil">

        <h1>Liste d'article</h1><br/>
        <div class="card"  >
        <div class="row row-cols-1 row-cols-md-3 g-4 containerCard" >
            
            <% 
                List<Article> liste=MethodesDAO.listeArticle();
                
                for (Article a : liste){
                out.println("<div class=col>");
                out.println("<div class=card h-100>");
                out.println("<img src="+basePath+"css/image/neo.JPG class=card-img-top >");
                out.println("<div class=card-body>");
                out.println("<h5 class=card-title>"+a.getLibelleArt()+"</h5>");
                out.println("<p class=card-text ><font size=1 color=grey> "+a.getDescriptionArt()+"</font></p>");
                out.println("<font size=10 color=red>"+a.getPrixArt()+" €</font> &nbsp "+a.getTypePrix()+"&nbsp&nbsp"+ a.getCondArt()+" <br/>");
                out.println("<font size=2>Format: "+a.getFormatArt()+"</font>");
                if (a.isBioArt()){
                    out.println("&nbsp<font size=3 color=green>BIO</font> ");
                }
                if (a.getNutriscoreArt()!=null){
                   out.println("<font size=2>&nbsp  Nutriscore: "+a.getNutriscoreArt()+"</font>");
                }
                out.println("<br/><button type=button class=btn btn-primary btn-sm value="+a.getIdArt()+">Ajouter au panier</button><br/>");
                out.println("<button type=button class=btn btn-primary btn-sm>Ajouter à la liste de courses</button>");
                out.println("</div></div></div>");
                }
                

            %>
            
            <div class="col"  >
            <div class="card h-100">
            <img src="${path}css/image/neo.JPG" class="card-img-top" >
              <div class="card-body">
                <div class="card-des">
                <h5 class="card-title">Card title</h5>
                <p class="card-text" style=" width:300px;height: 120px; overflow: hidden; text-overflow: ellipsis;"><font size="1" color="grey"> Testée : 
                    Pliable et donc facile à ranger après utilisation.</font></p>
                </div>
                <font size="10" color="red">60€</font> unitaire/kg <br/>
                <button type="button" class="btn btn-primary btn-sm">Ajouter au panier</button>
                <br/><br/>
            
            
                <button type="button" class="btn btn-primary btn-sm">Ajouter à la liste de courses</button>
              </div>
            </div>
            </div>
          
        </div>
        </div>
    </body>
<%@include file="../layout/footerFix.jsp" %>
