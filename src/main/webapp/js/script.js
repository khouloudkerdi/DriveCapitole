
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

document.addEventListener("DOMContentLoaded", () => {
    panierProduitOptions();
    document.getElementById("validerchoix").addEventListener("click", listeMagasins);
    document.getElementById("annulerchoix").addEventListener("click", annulerChoixMag);
    document.getElementById("btnModifierMag").addEventListener("click", modifierMagasin);
});



