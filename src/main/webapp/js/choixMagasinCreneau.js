
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
                        <div class=\"row\">\n\
                        <div class=\"col-md-8\">\n\
                        <div class=\"recapPanierInfo nomMag\">" + nomMag.firstChild.nodeValue + "</div>\n\
                        <div class=\"recapPanierInfo\">" + adrMag.firstChild.nodeValue + "</div>\n\
                        </div>\n\
                        <div class=\"col-md-4 atCenterAll\">\n\
                        <button name=\"choixMagasin\" class=\"btn btn-info btnChoixMag\" value=\"" + idMag.firstChild.nodeValue + "\">choisir</button>\n\
                        </div>\n\
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

function listeCrenaux()
{
    // Recuperation de la date choisie
    var date = document.getElementById("choixdateCre").value;
    var idMag = document.getElementById("idMag").value;
    var elt = document.getElementById("listeCreneau");
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    xhr.open("GET", "CtrlChoixCreneau?idMag=" + idMag + "&date=" + date);
    // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
    xhr.onload = function () {
        //Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {
            // mettre les données recuperés dans un fichier 
            var donnee = xhr.responseXML;

            //Recueperer les magasins
            var listeCre = donnee.getElementsByTagName("Creneau");
            //remplacer lmagasins par ""   
            elt.innerHTML = "";
            // mettre les noms dans l'element 
            for (i = 0; i < listeCre.length; i++)
            {
                var idCre = listeCre[i].getElementsByTagName("idCre")[0];
                var heure = listeCre[i].getElementsByTagName("heure")[0];
                var text = "<option value =\"" + idCre + "\">" + heure.firstChild.nodeValue + "</option>";
                elt.insertAdjacentHTML('beforeend', text);
            }
            if (listeCre.length > 0)
            {
                document.getElementById("afficherCrenaux").style.display = "block";
                document.getElementById("msgIndispo").style.display = "none";
            } else {
                document.getElementById("afficherCrenaux").style.display = "none";
                document.getElementById("msgIndispo").style.display = "block";
            }
        }
    };
    // Envoie de la requête.
    xhr.send();
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

function confirmerChoixMagCre(){
    var date = document.getElementById("choixdateCre").value;
    var obj = document.getElementById("listeCreneau");
    var index = document.getElementById("listeCreneau").selectedIndex;
    //alert(index);
    var opt = obj[index].innerHTML;
    //alert('value:'+obj[index].value+'&option:'+obj[index].innerHTML);
    //var creneau = obj.options[index].value;
    //alert(creneau);
//    var i = creneau.nodeValue;
//    alert(i);
    //console.log(index);
    //alert(creneau);
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "CtrlConfirmChoix?date=" + date + "&creneau=" + opt);  
    xhr.onload = function () {
                //Si la requête http s'est bien passée.
        if (xhr.status === 200)
        {//alert("200");
            // Recharge la page actuelle
            window.location.href = "ConfirmRetrait";
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("validerchoix").addEventListener("click", listeMagasins);
    document.getElementById("annulerchoix").addEventListener("click", annulerChoixMag);
    document.getElementById("btnModifierMag").addEventListener("click", modifierMagasin);
    document.getElementById("choixdateCre").addEventListener("change", listeCrenaux);
    document.getElementById("btnConfirmerChoix").addEventListener("click",confirmerChoixMagCre);
});