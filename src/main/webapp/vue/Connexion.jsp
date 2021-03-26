<%-- 
    Document   : Connexion
    Created on : 2021年3月26日, 上午9:06:14
    Author     : 13520
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <h1>S'identifier </h1>
        <form action="CtrlConnexion" method="get">
            <label>Adresse e-mail : </label> <input type="text" name="mail"  ><br/>
            <label>Mot de passe : </label><input type="text" name="motDePasse" ><br/>
            <button type="submit">Se connecter</button>
        </form>
        <div><font color="#FF0000">${requestScope.msg_connexion}</font></div>
    </body>
</html>
