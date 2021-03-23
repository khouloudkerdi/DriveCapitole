<%@page import="neopro.dao.MethodesDAO"%>
<%@page import="neopro.metier.Categorie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.Rayon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<div style="width:100px; position: fixed;margin-top: 100px;">
<ul id="menu" class="nav flex-column">
    
    <li class="nav-item">
        <div class="btn-group dropend">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="width:100px">
                Voir tout
            </button>
        </div>
    </li>
    
    <%
       ArrayList<Rayon> listR = (ArrayList)MethodesDAO.getListRayon();
       
       for (Rayon r : listR){
            out.println("<li class=\"nav-item\">");
            out.println("<div class=\"btn-group dropend\">");
            out.println("<button type=\"button\" class=\"btn btn-primary dropdown-toggle\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\" style=\"width:100px\">");
            out.println(r.getLibelleRay());
            out.println("</button>");
            out.println("<ul class=\"dropdown-menu\">");
            out.println("<li><a class=\"dropdown-item\" href=\"#\">");
            out.println("Voir tout");
            out.println("</a></li>");
            for (Categorie c : r.getCategories()){
                out.println("<li><a class=\"dropdown-item\" href=\"#\">");
                out.println(c.getLibelleCat());
                out.println("</a></li>");
            }
            out.println("</ul></div></li>");
        }
    %>

</ul>
</div>