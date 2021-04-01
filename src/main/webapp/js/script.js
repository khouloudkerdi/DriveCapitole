
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


function listeProduitProposes(e) {

    //var contenuPos = document.getElementById("carousContenu").getElementsByClassName ('active')[0].getElementsByTagName('h4')[0].innerHTML;
    var divProduitProposes = document.getElementById("produitProposes");
    // divProduitProposes.innerHTML="<p>"+contenuPos+"</p>";
    if (e.target.textContent !== "Previous" || e.target.textContent !== "Next") {
        var xhr = new XMLHttpRequest();
        //divProduitProposes.innerHTML="";
        // Requête au serveur avec les paramètres éventuels.
        xhr.open("GET", "ServletPostit?postit=" + e.target.textContent);
        // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
        xhr.onload = function ()
        {
            // Si la requête http s'est bien passée.
            if (xhr.status === 200)
            {

                var listeArticle = xhr.responseXML.getElementsByTagName("Article");
                if (listeArticle.length > 0) {

                    for (i = 0; i < listeArticle.length; i++)
                    {
                        var idArt = listeArticle[i].getElementsByTagName("IdArt")[0];
                        var LibelleArt = listeArticle[i].getElementsByTagName("LibelleArt")[0];
                        var imgArt = listeArticle[i].getElementsByTagName("UrlImageArt")[0];
                        var prixArt = listeArticle[i].getElementsByTagName("PrixKgArt")[0];
                        divProduitProposes.insertAdjacentHTML("beforeend", "<div class='row'>\n\
                    <div class='col-md-3'><input type='radio' name='article' value ='" + idArt.firstChild.nodeValue + "'>\n\
                     <img src='/Drive_NeoPro/image/" + imgArt.firstChild.nodeValue + "'>\n\
                    </div>\n\
                    <div class='col-md-6'>" + LibelleArt.firstChild.nodeValue + "</div>\n\
                    <div class='col-md-3'>" + prixArt.firstChild.nodeValue + "</div></div>");
                    }
                    divProduitProposes.insertAdjacentHTML("beforeend", "<button class='btn btn-info' type='submit'>Choisir</button>");


                }


            } else {
                divProduitProposes.innerHTML = "";
            }
        };

        // Envoie de la requête.
        xhr.send();

    } else {
        divProduitProposes.innerHTML = "";
    }
    //divProduitProposes.innerHTML="<p>"+e.target.textContent+"</p>";
    // Objet XMLHttpRequest.

}


document.addEventListener("DOMContentLoaded", () => {
    panierProduitOptions();
    document.getElementById("carousContenu").addEventListener("click", function () {
        var allBtn = this.querySelectorAll('button');
        for (var i = 0; i < allBtn.length; i++) {
            allBtn[i].addEventListener("click", function (e) {
                listeProduitProposes(e);
            });
        }
    });
    // document.getElementById("btnCaroNext").addEventListener("click",listeProduitProposes);
    // document.getElementById("btnCaroPrev").addEventListener("click",listeProduitProposes);
});



