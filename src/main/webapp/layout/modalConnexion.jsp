<div class="modal fade " id="modalConnexion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Connexion 
                  
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                  <form action="CtrlConnexion" method="get">
                            <div class="form-group">
                                <label class="control-label" for="mail">Adresse mail </label>
                                <input class="form-control" type="email" name="mail" id="mail" value="${param.mail}" required>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="motDePasse">Mot de passe</label>
                                <input class="form-control" type="password" name="motDePasse" id="motDePasse" required><br/>
                            </div>
                            <div class="btnContainer">
                                <div>
                                   <button class="btn btn-info" type="submit">Se connecter</button>
                                </div>
                                <div>
                                     <button type="button" class="btn btn-info" data-dismiss="modal">Retour</button>
                                </div>      
                            </div>  
                               
                        </form>
                </div>
            </div>
            
        </div>
    </div>
</div>
