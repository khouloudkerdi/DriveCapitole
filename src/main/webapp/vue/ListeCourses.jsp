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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listes de Courses</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${path}css/style.css " type="text/css" />
    </head>
    <body>
       
        <h1>Votre liste de courses</h1>
        <form action="CtrlGererCrouses" method="get" class="row g-3">
        <table border="1" class="table table-striped" >
            <tr><th>Supprimez des liste</th> <th>Nom de liste de courses</th> <th></th></tr>
            <%
            long idClient=1;
            request.getSession().setAttribute("idClient", idClient);
            ArrayList<ListeCourses> lc=MethodesDAO.getListeCourses(idClient);
            for (ListeCourses courses:lc){
            out.println("<tr> <th><input type=checkbox name=supprimer value="+courses.getIdLis()+"></th> <th>"+courses.getNomLis()+"</th> <th> <a href=\"CtrlTransmettreListePanier?idListeCourses="+courses.getIdLis()+"\" >ajouter au panier </a> </th> </tr>");
            }
            %>
            
        </table>
        <div class="col-12">        
          <button type="submit" class="btn btn-primary">Supprimer</button>
        </div>
        </form>
        <div><font color="#FF0000">${requestScope.msg_sup}</font></div>
        <br/>
        <a class="btn btn-primary" data-bs-toggle="collapse" href="AjouterCourses" role="button" aria-expanded="false" aria-controls="multiCollapseExample1">Ajouter une nouvelle liste</a>
        <br/>
        <a href="Accueil">Retour</a>
       
    </body>
</html>
