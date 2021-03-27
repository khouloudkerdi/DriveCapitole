<%-- 
    Document   : Courses
    Created on : 2021年3月22日, 上午10:19:54
    Author     : 13520
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Création de liste de courses </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css " type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300&display=swap" rel="stylesheet">   
    </head>
    <body id="bodyNouvelleListe">
        <div class="container"> 
            <div class="row">
                <div class="col-xs-6 col-sm-3"></div>
                <div class="col-xs-6 col-sm-6">
                    <h3>Nouvelle liste de courses </h3>
                </div>
                <div class="col-xs-6 col-sm-3"></div> 
            </div>
            <div class="row">
                <div class="col-xs-6 col-sm-3"></div>

                <div class="col-xs-6 col-sm-6">
                    <form action="CtrlAjouterCrouses" method="get">
                        <div class="form-group">
                            <label for="nomListe" class="control-label">Nom de  votre liste de courses :</label> 
                            <input id="nomListe" type="text" name="nouvelleliste" class="form-control" >        
                        </div>

                        <div class="btnContainer">
                            <div>
                                <button type="submit" class="btn btn-info">Créer</button>  
                            </div>
                            <div>
                                <a href="ListeCourses" class="btn btn-info">Retour</a>
                            </div>    
                        </div>
                    </form>
                    <font color="#FF0000">${requestScope.msg_ajouter}</font>
                </div>
                <div class="col-xs-6 col-sm-3"></div>
            </div>

            
        </div>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script> 
    <script src="${pageContext.request.contextPath}/js/script.js"></script>

</html>

