<%-- 
    Document   : choixCreneauMagasin
    Created on : 26 mars 2021, 09:48:44
    Author     : khoul
--%>

<%@page import="neopro.metier.Magasin"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
    <body>
        <h1>Choix magasin et Créneau :</h1>
        Code postal :<input type="Text" name="codepostal" id="codepostal" >
        <button class="btn btn-outline-success"  id="validerchoix">Valider</button>
       
        <div id="lmagasins"></div>
    </body>
<%@include file="../layout/footerFix.jsp" %>

