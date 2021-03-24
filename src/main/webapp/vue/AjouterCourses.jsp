<%-- 
    Document   : Courses
    Created on : 2021年3月22日, 上午10:19:54
    Author     : 13520
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.setAttribute("path", basePath);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Création de liste de courses </title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="${path}css/style.css " type="text/css" />
    </head>
    <body>
        <h1>Créer votre liste de courses!</h1>
        <form action="CtrlAjouterCrouses" method="get" class="row g-3">
            
            <div class="col-md-6">
              <label for="inputEmail4" class="form-label">Nom de  votre liste de courses :</label> 
              <input type="text" name="nouvelleliste" class="form-control" >
            </div>
            
            <div class="col-12">
            <button type="submit" class="btn btn-primary">Créer</button>
            </div>
             <div><font color="#FF0000">${requestScope.msg_ajouter}</font></div>
        </form>
        
         <a href="ListeCourses">Retour</a>
    </body>
</html>
