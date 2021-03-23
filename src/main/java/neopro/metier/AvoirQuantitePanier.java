/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author khoul
 */
@Entity
public class AvoirQuantitePanier implements Serializable {
    
    // Propriétés.
    @EmbeddedId
    private AvoirQuantitePanierID id;
    private int quantite;
    
    @ManyToOne
    @JoinColumn(name = "idPan", insertable = false, updatable = false)
    private Panier panier;
    
    @ManyToOne
    @JoinColumn(name = "idArt", insertable = false, updatable = false)
    private Article article;
    
    //Constructeurs.

    public AvoirQuantitePanier() {
    }

    public AvoirQuantitePanier(AvoirQuantitePanierID id, int quantite) {
        this.id = id;
        this.quantite = quantite;
    }
    
    //Getters /Setters.

    public AvoirQuantitePanierID getId() {return id;}
    public void setId(AvoirQuantitePanierID id) { this.id = id;}
    public int getQuantite() {return quantite;}
    public void setQuantite(int quantite) { this.quantite = quantite;}
    public Panier getPanier() {return panier;}
    public void setPanier(Panier panier) { this.panier = panier;}
    public Article getArticle() {return article;}
    public void setArticle(Article article) { this.article = article;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "AvoirQuantitePanier{" + "id=" + id + ", quantite=" + quantite + ", panier=" + panier + ", article=" + article + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AvoirQuantitePanier other = (AvoirQuantitePanier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
