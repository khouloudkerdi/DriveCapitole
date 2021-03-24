<%-- 
    Document   : ListeArticle
    Created on : 2021年3月22日, 下午2:16:13
    Author     : 13520
--%>


<%@page import="neopro.metier.ListeCourses"%>
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
                    
                    <%  long id=1;
                        request.getSession().setAttribute("idClient", id);
                        
                        long idClient=(long) request.getSession().getAttribute("idClient");
                        List<Article> liste = MethodesDAO.listeArticle();
                        ArrayList<ListeCourses> ListeCourses=MethodesDAO.getListeCourses(idClient);
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
                            
                            out.println("<form action=CtrlInserer  method=get>");
                            
                            out.println("<br/><button type=submit class=btn btn-primary btn-sm name=idArt value=" + a.getIdArt() + ">Ajouter au panier</button><br/>");
                            
                            
                            
                            out.println("<div class=btn-group>");
                            out.println("<button class=btn btn-primary dropdown-toggle data-bs-toggle=dropdown >Ajouter à la liste de courses</button>");
                            out.println("<ul class=dropdown-menu>");
                            for (ListeCourses lc:ListeCourses){
                              out.println("<li><button type=submit class=btn btn-light name=btnListeCourses value="+lc.getIdLis()+","+a.getIdArt()+">"+lc.getNomLis()+"</button></li>");
                            }
                          
                            out.println("</ul>");
                            out.println("</div>");
                 
                            out.println("</form>");
                            
                            out.println("</div></div></div>");
                        }
                    %>
                
                </div>
               


            </div>                

        </div>
    </div>

<div class="btn-group">
  <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" name="btnArticle" >Ajouter à la liste de courses</button>
  <ul class="dropdown-menu">
      <li><button class="btn btn-light" name="btnListeCourses">Liste1</button></li>
  </ul>
</div>

</body>
<%@include file="../layout/footerFix.jsp" %>
