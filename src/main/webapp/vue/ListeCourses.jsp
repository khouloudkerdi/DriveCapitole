<%-- 
    Document   : ListeCourses
    Created on : 2021年3月22日, 上午10:38:33
    Author     : 13520
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.ListeCourses"%>
<%@page import="java.util.Set"%>
<%@page import="neopro.dao.MethodesDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyListeCourses">
    <div class="container">
        <div class="row">
            <div class="col-xs-6 col-sm-1"></div>
            <div class="col-xs-6 col-sm-10">
                <h3>Vos listes de courses</h3>
            </div>
            <div class="col-xs-6 col-sm-1"></div> 
        </div>
        <div class="row">
            <div class="col-xs-6 col-sm-1"></div>
            <div class="col-xs-6 col-sm-10">
                <form action="CtrlGererCrouses" method="get">
                    <table class="table table-striped table-bordered table-hover" >
                        <tr><th>Liste</th> <th>Nom liste de courses</th> <th>Action</th></tr>
                                <%
                                    //long idClient = (long) request.getSession().getAttribute("idClient");
                                    long idClient = ((Number) request.getSession().getAttribute("idClient")).longValue();
                                    ArrayList<ListeCourses> lc = MethodesDAO.getListeCourses(idClient);
                                    for (ListeCourses courses : lc) {
                                        out.println("<tr> <th><input type=checkbox name=supprimer value=" + courses.getIdLis() + "></th>  ");
                                        if (MethodesDAO.articleListeCourses(courses.getIdLis()).size() == 0) {
                                            out.println("<th>" + courses.getNomLis() + "</th>");
                                        } else {
                                            out.println("<th><a href=VisualiserListe?idListe=" + courses.getIdLis() + ">" + courses.getNomLis() + " </a></th>");
                                        }

                                        out.println("<th> <a href=\"CtrlTransmettreListePanier?idListeCourses=" + courses.getIdLis() + "\" >ajouter au panier </a> </th></tr>");
                                    }
                                %>
                    </table>
                    <div class="btnContainer">
                        <div>
                            <a class="btn btn-info" href="AjouterCourses">Nouvelle liste</a>
                        </div>
                        <div > 
                            <button type="submit" class="btn btn-info">Supprimer</button>
                        </div>

                        <div>
                            <a class="btn btn-info" href="Accueil">Retour</a>
                        </div>        

                    </div>
                </form>


            </div>
            <div class="col-xs-6 col-sm-1">
                <div><font color="#FF0000">${requestScope.msg_sup}</font></div>
            </div>

        </div>
    </div>

</body>
<%@include file="../layout/footerFix.jsp" %>
