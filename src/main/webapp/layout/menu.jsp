<%@page import="neopro.dao.MethodesDAO"%>
<%@page import="neopro.metier.Categorie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neopro.metier.Rayon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<div id="menu-div"> 
           <ul class="nav flex-column mt-3">
    
    <a class="btn btn-primary" href="Accueil" style="width:140px">Voir tout</a> 
    <%
        ArrayList<Rayon> listR = (ArrayList) MethodesDAO.getListRayon();
        for (Rayon r : listR) 
        {
    %>
    <!--<a class="btn btn-primary" href="CtrlFiltrageParRayon?idRay=<%//out.print(r.getIdRay()); %>">-->

  <li class="nav-item">
    <div class="btn-group dropend">
      <a class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="width:140px">
            <% out.print(r.getLibelleRay());%>
      </a>
      <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="CtrlFiltrageParRayon?idRay=<%out.print(r.getIdRay()); %>" >Voir tout</a></li> 
          <%
                for (Categorie c : r.getCategories()) { %>
                <li>
                    <a class="dropdown-item" href="CtrlFiltrageParCategorie?idRay=<%out.print(r.getIdRay()); %>&idCat=<%out.print(c.getIdCat());%>">
                    <%out.println(c.getLibelleCat());%>
                    </a>
                </li>
          <%  
              }
          %>
      </ul>
    </div>    
  </li>


    
    <%
       }
    %>
</ul>
</div>
