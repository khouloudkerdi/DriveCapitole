
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

   
    var divProduitProposes = document.getElementById("produitProposes");
   
    if (e.target.textContent !== "Previous" || e.target.textContent !== "Next") {
        var xhr = new XMLHttpRequest();
        var inputIdListe= document.getElementById("idListe").value;
        var idPost = e.target.value;
        // Requête au serveur avec les paramètres éventuels.
        xhr.open("GET", "ServletPostit?postit=" + e.target.textContent);
        // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
        xhr.onload = function ()
        {
            // Si la requête http s'est bien passée.
            if (xhr.status === 200)
            {

                var listeArticle = xhr.responseXML.getElementsByTagName("Article");
                divProduitProposes.innerHTML="";
               
                if (listeArticle.length > 0) {
                    
                    for (i = 0; i < listeArticle.length; i++)
                    {
                        var idArt = listeArticle[i].getElementsByTagName("IdArt")[0];
                        var LibelleArt = listeArticle[i].getElementsByTagName("LibelleArt")[0];
                        var imgArt = listeArticle[i].getElementsByTagName("UrlImageArt")[0];
                        var prixArt = listeArticle[i].getElementsByTagName("PrixKgArt")[0];
                        divProduitProposes.insertAdjacentHTML("beforeend", "\
                    <input type='hidden' name='idPostIt' value='"+idPost+"'>\n\
                    <input type='hidden' name='ListeCourse' value='"+inputIdListe+"'>\n\
                    <div class='row' style=' margin-bottom:10px; background-color: white;font-size: 16px;'>\n\
                    <div class='col-md-4'><input type='radio' name='article' value ='" + idArt.firstChild.nodeValue + "'style='margin-right: 5px;font-size: 12px;'>\n\
                     <img src='/Drive_NeoPro/image/" + imgArt.firstChild.nodeValue + "'>\n\
                    </div>\n\
                    <div class='col-md-4'style='margin-top: 5px;font-size: 16px;'>" + LibelleArt.firstChild.nodeValue + "</div>\n\
                    <div class='col-md-4'style='margin-top: 5px;font-size: 16px;'>" + prixArt.firstChild.nodeValue + "</div></div>");
                    }
                    divProduitProposes.insertAdjacentHTML("beforeend", "<div class='row justify-content-center'><button class='btn btn-info'style='width: 100px;'; type='submit'>Choisir</button></div>");
                    

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



