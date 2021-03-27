<%-- 
    Document   : Connexion
    Created on : 2021年3月26日, 上午9:06:14
    Author     : 13520
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
    <body id="bodyConnexion">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-6 col-sm-2 col-md-3"></div>
                <div class="col-xs-6 col-sm-8 col-md-6">
                   
                    <form action="CtrlConnexion" method="get">
                         <h1>S'identifier </h1>
                        <div class="form-group">
                            <label class="control-label" for="mail">Adresse mail </label>
                            <input class="form-control" type="email" name="mail" id="mail" value="${param.mail}">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="motDePasse">Mot de passe</label>
                            <input class="form-control" type="password" name="motDePasse" id="motDePasse" ><br/>
                        </div>
                        <div class="form-group">
                             <button class="btn btn-info" type="submit">Se connecter</button>
                             
                        </div>  
                    </form>
                         <a href="Accueil" role="button" aria-expanded=false aria-controls="collapseExample" class="btn btn-warning" >Retour àla page d'accueil</a>
                    <div><font color="#FF0000">${requestScope.msg_connexion}</font></div>
                </div>
                <div class="col-xs-6 col-sm-2 col-md-3"></div>
            </div>
        </div>

    </body>
   <%@include file="../layout/footerFix.jsp" %>
