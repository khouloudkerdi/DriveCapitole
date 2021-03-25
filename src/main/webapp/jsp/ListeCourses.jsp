<%-- 
    Document   : ListeCourses
    Created on : 2021年3月22日, 上午10:38:33
    Author     : 13520
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listes de Courses</title>
    </head>
    <body>
        <h1>Votre liste de courses</h1>
        
        <table border="1">
             <tr><th>Supprimez des liste</th> <th>Nom de liste de crouses</th> </tr>
            <tr> <th><input type=checkbox ></th> <th>Liste1</th> </tr>
        </table>
        <a href="CtrlGererCrouses?method=ajouter">Ajouter</a>
        <a href="CtrlGererCrouses?method=supprimer">Supprimer</a><br/>
        
        <a href="">Retour</a>
    </body>
</html>
