<%-- 
    Document   : choixCreneauMagasin
    Created on : 26 mars 2021, 09:48:44
    Author     : khoul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
    <body>
        <h1>Choix magasin et Créneau :</h1>
         <form class="d-flex" id="saisirCP" action="CtrlChoixMagasin" method="GET">
        Code postal :<input type="Text" name="codepostal" id="codepostal" >
        <button class="btn btn-outline-success" type="submit">Valider</button>
         </form>
    </body>
<%@include file="../layout/footerFix.jsp" %>

