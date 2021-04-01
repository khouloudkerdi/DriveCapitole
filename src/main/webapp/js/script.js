
function panierProduitOptions() {
    const produitOptions = document.querySelectorAll(".produitOptions");
    produitOptions.forEach((node) => {
        const spanQte = node.querySelector('.qteProduit');
        const ida = node.querySelector('.ida');
        const idp = node.querySelector('.idp');
        const btnQtePlus = node.querySelector("button[name='plus']");
        const btnQteMoins = node.querySelector("button[name='moins']");

        // open objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();

        btnQtePlus.addEventListener("click", function () {
            // appeler un servlet
            xhr.open("GET", "CtrlAjouterProduitPanier?ida=" + ida.innerHTML + "&idp=" + idp.innerHTML);
            xhr.onload = function ()
            {
                // Si la requête http s'est bien passée.
                if (xhr.status === 200)
                {
                    // Recharge la page actuelle
                    document.location.reload();
                }
            };
            // Envoie la requête.
            xhr.send();
        });

        btnQteMoins.addEventListener("click", function () {
            // appeler un servlet
            xhr.open("GET", "CtrlSupprimerProduitPanier?ida=" + ida.innerHTML + "&idp=" + idp.innerHTML);
            xhr.onload = function ()
            {
                // Si la requête http s'est bien passée.
                if (xhr.status === 200)
                {
                    // Recharge la page actuelle
                    document.location.reload();
                }
            };
            // Envoie la requête.
            xhr.send();
        });
    });
}


function listeMagasins() {
    // recueperer l'element 'lmagasins'
    var elt = document.getElementById("lmagasins");
    // recueperer l'element 'codepostal'
    var inputCP = document.getElementById("codepostal");
    // console.log(inputCP.value);

    if (inputCP.value === "") {
        // Vider la liste des magasins
        elt.innerHTML = "";
    } else {
        // open objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();
        // Requête au serveur avec les paramètres éventuels.
        xhr.open("GET", "CtrlChoixMagasin?codePostal=" + inputCP.value);
        // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
        xhr.onload = function () {
            //Si la requête http s'est bien passée.
            if (xhr.status === 200)
            {
                // mettre les données recuperés dans un fichier 
                var donnee = xhr.responseXML;

                // recueperer les magasins
                var listeMag = donnee.getElementsByTagName("Magasin");
                // remplacer lmagasins par ""   
                elt.innerHTML = "";

                // mettre les noms dans l'element 
                for (i = 0; i < listeMag.length; i++)
                {
                    // recuperer les informations d'un magasin
                    var nomMag = listeMag[i].getElementsByTagName("nomMagasin")[0];
                    var idMag = listeMag[i].getElementsByTagName("idMagasin")[0];
                    var adrMag = listeMag[i].getElementsByTagName("adrMagasin")[0];
                    // ajouter un choix de magasin
                    elt.insertAdjacentHTML('beforeend', "<div class=\"magInfo\">\n\
\n\                     <div class=\"row\">\n\
                        <div class=\"col-md-8\">\n\
                        <div class=\"recapPanierInfo nomMag\">" + nomMag.firstChild.nodeValue + "</div>\n\
                        <div class=\"recapPanierInfo\">" + adrMag.firstChild.nodeValue + "</div>\n\
                        </div>\n\
                        <div class=\"col-md-4 atCenterAll\">\n\
                        <button name=\"choixMagasin\" class=\"btn btn-info btnChoixMag\" value=\"" + idMag.firstChild.nodeValue + "\">choisir</button>\n\
\n\                     </div>\n\
                        </div>");
                }
                // ajouter event listener aux boutons 'choisir'
                var tab = elt.getElementsByTagName("button");
                for (i = 0; i < tab.length; i++) {
                    tab[i].addEventListener("click", validerChoixMag);
                }
            }
        };
        // Envoie de la requête.
        xhr.send();
    }
}

function modifierMagasin()
{
    // faire apparaitre le bloc 'rechercheMag'
    document.getElementById("rechercheMag").style.display = "block";
    // faire dispaitre le bouton 'btnModifierMag'
    document.getElementById("btnModifierMag").style.display = "none";
}

function annulerChoixMag()
{
    // faire dispaitre le bloc 'rechercheMag'
    document.getElementById("rechercheMag").style.display = "none";
    // faire apparaitre le bouton 'btnModifierMag'
    document.getElementById("btnModifierMag").style.display = "block";
}

function validerChoixMag()
{
    // console.log(event.target.value);
    // open objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    xhr.open("GET", "CtrlSetMag?idMag=" + event.target.value);    
    xhr.onload = function () {
        //Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {
            // Recharge la page actuelle
            document.location.reload();
        }
    };
    // Envoie de la requête.
    xhr.send();

}
function listeProduitProposes(e){
    
    //var contenuPos = document.getElementById("carousContenu").getElementsByClassName ('active')[0].getElementsByTagName('h4')[0].innerHTML;
    var divProduitProposes = document.getElementById("produitProposes");
   // divProduitProposes.innerHTML="<p>"+contenuPos+"</p>";
    if(e.target.textContent!== "Previous" || e.target.textContent!== "Next"){
        var xhr = new XMLHttpRequest();
       // divProduitProposes.innerHTML="";
    // Requête au serveur avec les paramètres éventuels.
    xhr.open("GET", "ServletPostit?postit="+e.target.textContent);
    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function ()
    {
        // Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {   
           
           var listeArticle = xhr.responseXML.getElementsByTagName("Article");
           if (listeArticle.length>0){
           console.log("size---------"+listeArticle.length);
               for (i = 0; i < listeArticle.length; i++)
                {
                    var idArt =  listeArticle[i].getElementsByTagName("IdArt")[0];
                    var LibelleArt = listeArticle[i].getElementsByTagName("LibelleArt")[0];
                    var imgArt = listeArticle[i].getElementsByTagName("UrlImageArt")[0];
                    var prixArt = listeArticle[i].getElementsByTagName("PrixKgArt")[0];
                    divProduitProposes.insertAdjacentHTML("beforeend","<div class='row'>\n\
                    <div class='col-md-3'><input type='radio' name='article' value ='"+idArt.firstChild.nodeValue+"'>\n\
                     <img src='/Drive_NeoPro/image/"+imgArt.firstChild.nodeValue+"'>\n\
                    </div>\n\
                    <div class='col-md-6'>"+LibelleArt.firstChild.nodeValue+"</div>\n\
                    <div class='col-md-3'>"+prixArt.firstChild.nodeValue+"</div></div>");
                }
                divProduitProposes.insertAdjacentHTML("beforeend","<button class='btn btn-info' type='submit'>Choisir</button>");
                
                
           }
            
           
        }else{
            divProduitProposes.innerHTML="";
        }
    };

    // Envoie de la requête.
    xhr.send();
        
    }else{
         divProduitProposes.innerHTML="";
    }
     //divProduitProposes.innerHTML="<p>"+e.target.textContent+"</p>";
    // Objet XMLHttpRequest.
   
}
    
    




document.addEventListener("DOMContentLoaded", () => {
    panierProduitOptions();
    document.getElementById("carousContenu").addEventListener("click",function (){
         var allBtn = this.querySelectorAll('button');
            for (var i = 0; i < allBtn.length; i++) {
                allBtn[i].addEventListener("click",function (e){ 
                  listeProduitProposes(e);
                });
            }
    });
   // document.getElementById("btnCaroNext").addEventListener("click",listeProduitProposes);
    // document.getElementById("btnCaroPrev").addEventListener("click",listeProduitProposes);
    
    document.getElementById("validerchoix").addEventListener("click",listeMagasins);
    document.getElementById("annulerchoix").addEventListener("click",annulerChoixMag);
    document.getElementById("btnModifierMag").addEventListener("click",modifierMagasin);
    
   
});



