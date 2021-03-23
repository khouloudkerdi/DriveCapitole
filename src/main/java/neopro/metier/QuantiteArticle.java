/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author khoul
 */
@Entity
public class QuantiteArticle {
    
    // Propriétés.
    @EmbeddedId
    private QuantiteArticleID id;
    private int quantiteArt;
    
    //Constructeurs.

    public QuantiteArticle() {
    }

    public QuantiteArticle(int quantiteArt) {
        this.quantiteArt = quantiteArt;
    }
    
    //Getters /Setters.

    public QuantiteArticleID getId() {return id;}
    public void setId(QuantiteArticleID id) { this.id = id;}
    public int getQuantiteArt() {return quantiteArt;}
    public void setQuantiteArt(int quantiteArt) { this.quantiteArt = quantiteArt;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "QuantiteArticle{" + "id=" + id + ", quantiteArt=" + quantiteArt + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final QuantiteArticle other = (QuantiteArticle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
