<%-- 
    Document   : Connexion
    Created on : 2021年3月26日, 上午9:06:14
    Author     : 13520
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/headerFix.jsp" %>
<body id="bodyConnexion">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 d-flex flex-column justify-content-center">
                <div class="row">
                    <div class="col-sm-8 mx-auto">
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

                            <div class="btnContainer">
                                <div>
                                    <button class="btn btn-info" type="submit">Se connecter</button>
                                </div>
                                <div>
                                    <a class="btn btn-info" href="Accueil">Retour</a>
                                </div>      
                            </div>  
                        </form>

                        <div><font color="#FF0000">${requestScope.msg_connexion}</font></div>
                    </div>
                </div>
            </div>  
        </div>
    </div>

</body>
<%@include file="../layout/footerFix.jsp" %>
