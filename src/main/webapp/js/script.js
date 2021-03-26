
function panierProduitOptions(){
    const produitOptions= document.querySelectorAll(".produitOptions");
    produitOptions.forEach((node)=>{
        const spanQte = node.querySelector('.qteProduit');
        const btnQtePlus = node.querySelector("button[action='plus']");
        const btnQteMoins = node.querySelector("button[action='moins']");       	

                
        // Ajouter une quantite au panier
        btnQtePlus.addEventListener("click",function (){        
           spanQte.innerHTML = parseInt(spanQte.innerHTML)+1; 
           
//            // Objet XMLHttpRequest.
//            var xhr = new XMLHttpRequest();
//            // Requête au serveur avec les paramètres éventuels.
////            xhr.open("GET","CtrlSupprimerProduitPanier?ida="+ida+"&idp="+idp);
//            xhr.open("GET","CtrlAjouterProduitPanier?ida=1&idp=1");
//            // Envoie de la requête.
//            xhr.send();

            
        });
        
//        btnQteMoins.addEventListener("click",function (){
//            // Objet XMLHttpRequest.
//            var xhr = new XMLHttpRequest();
//            // Requête au serveur avec les paramètres éventuels.            
//            xhr.open("GET","CtrlSupprimerProduitPanier?ida=1&idp=1");
//            // Envoie de la requête.
//            xhr.send(); 
            
            var spanQteValeur = parseInt(spanQte.innerHTML);
            if(spanQteValeur -1 >=0){
                spanQte.innerHTML = parseInt(spanQteValeur)-1;
                if (spanQteValeur -1 ===0){
                    panierSuppressionProduit();
              }
            }                 
        });  
}


function panierSuppressionProduit(){
    const panierProduits= document.querySelectorAll(".panierProduit");
    var parentNode= null;
    var childNode = null;
    panierProduits.forEach((node)=>{
         const spanQte = node.querySelector('.qteProduit');
         if(parseInt(spanQte.innerHTML)===0){
          childNode = node;
          parentNode = node.parentNode;
         }            
     });
      if(parentNode!==null && childNode!==null){
             parentNode.removeChild(childNode);
         }    
}

function produitQteMoins (){
    var ida=document.getElementById("ida").innerHTML;
    // appeler la servlet 
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.            
    xhr.open("GET","CtrlSupprimerProduitPanier?ida="+ida+"&idp=1");
    // Envoie de la requête.
    xhr.send(); 
}
function produitQtePlus (){
//    var par = this.object;
    
    var ida=document.getElementById("ida").innerHTML;
    // appeler la servlet 
    // Objet XMLHttpRequest.
    var xhr = new XMLHttpRequest();
    // Requête au serveur avec les paramètres éventuels.
    xhr.open("GET","CtrlAjouterProduitPanier?ida="+ida+"&idp=1");
    // Envoie de la requête.
    xhr.send();
}

 document.addEventListener("DOMContentLoaded", () => {
//        panierProduitOptions();
    document.getElementById("moins").addEventListener("click",produitQteMoins);
    document.getElementById("plus").addEventListener("click",produitQtePlus);

    });



