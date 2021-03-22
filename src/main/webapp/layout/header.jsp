<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/style.css " type="text/css" />
    </head>
    
    <body>
	<header style="display: flex; background-color:ivory; ">

            <div class="mainbar_logo">
                <img src="../css/image/neo.JPG" alt="logo_neopro" width="80px" height="55px"/>
            </div>
            

            <nav class="navbar navbar-light justify-content-center" style="margin-right: 80px; width:100% ">
                <div class="container-fluid justify-content-center">
                    <form class="d-flex" id="search_form" action="" method="GET">
                        <input class="form-control me-2" type="search" placeholder="Rechercher un article ..." aria-label="Search" style="position: relative;width: 100%;">
                        <button class="btn btn-outline-success" type="submit">Rechercher</button>
                    </form>
                </div>
            </nav>
            
            <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-primary">Compte</button>
                <button type="button" class="btn btn-primary">Listes</button>
                <button type="button" class="btn btn-primary">Panier</button>
            </div>
            
        </header>

	<script type="text/JavaScript" src=""></script>
    </body>
   
