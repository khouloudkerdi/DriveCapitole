/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function panierProduitOptions(){
    const produitOptions= document.querySelectorAll(".produitOptions");
    produitOptions.forEach((node)=>{
        const spanQte = node.querySelector('.qteProduit');
        const btnQtePlus = node.querySelector("button[action='plus']");
        const btnQteMoins = node.querySelector("button[action='moins']");
        
        btnQtePlus.addEventListener("click",function (){
          
            spanQte.innerHTML = parseInt(spanQte.innerHTML)+1;
        });
        btnQteMoins.addEventListener("click",function (){
            var spanQteValeur = parseInt(spanQte.innerHTML);
                if(spanQteValeur -1 >=0){
                    spanQte.innerHTML = parseInt(spanQteValeur)-1;
                    if (spanQteValeur -1 ===0){
                        panierSuppressionProduit();
                  }
                }               
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

