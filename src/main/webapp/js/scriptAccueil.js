function ajouterPanierEtListe(){
    const btnAccueil = document.querySelectorAll(".btnAccueil");
    btnAccueil.forEach((node) => {
        const btnAddPanier = node.querySelector("button[name='btnPanierAccueil']");
        const inputArt = node.querySelector('.inputIdArt')
        const btnAddListe = node.querySelector("a[name='idL']");
        const inputListe = node.querySelector('.inputIdListe')

        // open objet XMLHttpRequest.
        var xhr = new XMLHttpRequest();

        btnAddPanier.addEventListener("click", function () {
            var idArt = inputArt.value;
            //alert(idArt);
            xhr.open("GET", "CtrlInserer?idArt=" + idArt);
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
        
        btnAddListe.addEventListener("click", function () {
            var idArt = inputArt.value;
            var idListe = inputListe.value;
            //alert(idListe);
            xhr.open("GET", "CtrlInserer?idListeCourses=" + idArt + "," + idListe);
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



document.addEventListener("DOMContentLoaded", () => {
    ajouterPanierEtListe();

});