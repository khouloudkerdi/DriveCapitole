
function panierProduitOptions(){
    const produitOptions= document.querySelectorAll(".produitOptions");
    produitOptions.forEach((node)=>{
        const spanQte = node.querySelector('.qteProduit');
        const ida = node.querySelector('.ida');
        const idp = node.querySelector('.idp');
        const btnQtePlus = node.querySelector("button[name='plus']");
        const btnQteMoins = node.querySelector("button[name='moins']");
        
        // open objet XMLHttpRequest.
        var xhr = new XMLHttpRequest(); 
        
        btnQtePlus.addEventListener("click",function (){       
            // modifier la vue
            spanQte.innerHTML = parseInt(spanQte.innerHTML)+1;           
            
            // appeler un servlet
            xhr.open("GET","CtrlAjouterProduitPanier?ida="+ida.innerHTML+"&idp="+idp.innerHTML);

            // Envoie la requête.
            xhr.send();            
        });
        
        btnQteMoins.addEventListener("click",function (){
            // modifier la vue
            var spanQteValeur = parseInt(spanQte.innerHTML);
            if(spanQteValeur -1 >=0){
                spanQte.innerHTML = parseInt(spanQteValeur)-1;
                if (spanQteValeur -1 ===0){
                    panierSuppressionProduit();
                }
            }  
            
            // appeler un servlet
            xhr.open("GET","CtrlSupprimerProduitPanier?ida="+ida.innerHTML+"&idp="+idp.innerHTML);  
            
            // Envoie la requête.
            xhr.send();    
            
        });         
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
 document.addEventListener("DOMContentLoaded", () => {
        panierProduitOptions();

    });



