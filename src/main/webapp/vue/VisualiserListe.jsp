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
                <%-- Carousel --%>
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style="background: #4ea8f4;">
                    <div id="carousContenu"  class="carousel-inner">
                        <div class="carousel-item active new_html_code">
                            <div class="carousel-caption">
                                <h4>Liste post'it</h4>
                            </div>
                        </div>

                        <%
                            List<Postit> listePostit = MethodesDAO.loadPostIt(idListeCourses);
                            for (Postit p : listePostit) {

                                // out.println("<p>" + p1.getContenuPos() + "</p>");
                        %>

                        <div class="carousel-item new_html_code">
                           
                            <div class="carousel-caption">
                                <button><%out.println(p.getContenuPos());%></button>

                            </div>
                        </div> 


                        <%
                            }
                        %>


                    </div>
                    <a id="btnCaroPrev" class="carousel-control-prev" href="#carouselExampleControls"  data-slide="prev" style="background: blue;" >
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a id="btnCaroNext" class="carousel-control-next" href="#carouselExampleControls" data-slide="next" style="background: blue;">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>



                <%-- Contenu --%>


                <div>
                    <br/>
                    <form action=CtrlPostIt method=get>
                        <div class="btnContainer" style="margin: 10px 20px;">
                            <input class="form-control" type="text" name="postit" style="width: 220px;">

                            <%
                                out.println("<input type=hidden  name=idListe value=" + idListeCourses + ">");
                            %>
                            <input type="submit" value="Ajouter Post-it" class="btn btn-info">       
                        </div>           
                    </form>
                </div>
                <div class="btnContainer">
                    <div class="atCenterHorizontal">
                        <%
                            out.println("<a role=\"button\" class=\"btn btn-info\"href=\"CtrlTransmettreListePanier?idListeCourses=" + idListeCourses + "\" >Ajouter au panier </a>");
                        %>
                    </div>
                    <a href="Accueil" class="btn btn-info">Retour</a>
                </div>



            </div>
            <%-- Produits --%>
            <div class="col-md-6">
                <h4>Liste des produits proposés </h4>
               
                    <form action='#' method='get' id="produitProposes">
                        
                        <% out.println("<input type=hidden name=ListeCourse value="+idListeCourses);%>
              
                        </form>
                
            </div>

            <%-- Boutton <Ajouter au panier> --%>


        </div>
    </div>
</body>
<%@include file="../layout/footerFix.jsp" %>
