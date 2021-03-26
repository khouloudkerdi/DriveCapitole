<%@page import="neopro.dao.MethodesDAO"%>
<%@page import="neopro.metier.Categorie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.Rayon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<div id="menu-div">
<ul class="nav flex-column">   
    <li class="nav-item">
        <div class="btn-group dropend">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="width:120px">
                Voir tout
            </button>
        </div>
    </li>
    
    <%
        ArrayList<Rayon> listR = (ArrayList) MethodesDAO.getListRayon();

        for (Rayon r : listR) {
            out.println("<li class=\"nav-item\">");
            out.println("<div class=\"btn-group dropend\">");
            out.println("<form action=CtrlFiltrageParRayon method=GET>");
            out.println("<button type=\"submit\" class=\"btn btn-primary aria-expanded=\"false\" style=\"width:120px\">");
            out.println(r.getLibelleRay());
            out.println("</button>");
            out.println("<input type='hidden' id='idRay' name='idRay' value=" + r.getIdRay() + " />");
            out.println("</form>");
            out.println("<ul class=\"dropdown-menu\">");
            out.println("<li><a class=\"dropdown-item\" href=\"#\">");
            out.println("Voir tout");
            out.println("</a></li>");
            for (Categorie c : r.getCategories()) {
                out.println("<li><a class=\"dropdown-item\" href=\"#\">");
                out.println(c.getLibelleCat());
                out.println("</a></li>");
            }
            out.println("</ul></div></li>");
        }
    %>

</ul>
</div>
