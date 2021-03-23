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
public class AvoirPromo implements Serializable{
    
    // Propriétés.
    @EmbeddedId
    private AvoirPromoID id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datefin;
    
    @ManyToOne
    @JoinColumn(name = "idPro", insertable = false, updatable = false)
    private Promotion promotion;
    
    @ManyToOne
    @JoinColumn(name = "idArt", insertable = false, updatable = false)
    private Article article;
    
    //Constructeurs.

    public AvoirPromo() {
    }

    public AvoirPromo(AvoirPromoID id, Date dateDebut, Date datefin) {
        this.id = id ;
        this.dateDebut = dateDebut;
        this.datefin = datefin;
    }
    
    //Getters /Setters.

    public AvoirPromoID getId() {return id;}
    public void setId(AvoirPromoID id) { this.id = id;}
    public Date getDateDebut() {return dateDebut;}
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut;}
    public Date getDatefin() {return datefin;}
    public void setDatefin(Date datefin) { this.datefin = datefin;}
    public Promotion getPromotion() {return promotion;}
    public void setPromotion(Promotion promotion) { this.promotion = promotion; }
    public Article getArticle() {return article;}
    public void setArticle(Article article) { this.article = article;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "AvoirPromo{" + "id=" + id + ", dateDebut=" + dateDebut + ", datefin=" + datefin + '}';
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
        final AvoirPromo other = (AvoirPromo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
