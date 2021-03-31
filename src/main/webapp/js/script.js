
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
function listeMagasins (){
	// Objet XMLHttpRequest.
         var elt = document.getElementById("lmagasins");
     
        var inputCP = document.getElementById("codepostal");
        console.log(inputCP.value);
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","CtrlChoixMagasin?codePostal="+inputCP.value);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function(){
            //Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
                            // mettre les données recuperés dans un fichier 
                        var donnee =  xhr.responseXML;
                        
                        //Recueperer les magasins
                        var listeMag = donnee.getElementsByTagName("Magasin");
                        //remplacer lmagasins par ""   
                        elt.innerHTML="";
                        
                        // mettre les noms dans l'element 
                        for(i = 0; i < listeMag.length; i++) 
                        {   
                            var nomMag = listeMag[i].getElementsByTagName("nomMagasin")[0];
                            var idMag = listeMag[i].getElementsByTagName("idMagasin")[0];
                            var adrMag = listeMag[i].getElementsByTagName("adrMagasin")[0];
                           elt.insertAdjacentHTML('beforeend', "<p>"+nomMag.firstChild.nodeValue+"<br>"+adrMag.firstChild.nodeValue+"</p>");
                           elt.insertAdjacentHTML('beforeend',"<button value=\""+idMag.firstChild.nodeValue+"\">choisir</button>");
			}
                        }
        };
	
	// Envoie de la requête.
	xhr.send();
	}
        
document.addEventListener("DOMContentLoaded", () => {
    panierProduitOptions();
document.getElementById("validerchoix").addEventListener("click",listeMagasins);
});



