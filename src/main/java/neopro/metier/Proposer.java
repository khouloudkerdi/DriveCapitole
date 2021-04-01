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
public class Proposer implements Serializable{
    
    // Propriétés.
    @EmbeddedId
    private ProposerID id;
    private int nbPlaceDispoCre ; 
    private int nbPlaceTotal; 
    
    @ManyToOne
    @JoinColumn(name = "idMag", insertable = false, updatable = false)
    private Magasin magasin;
    
    @ManyToOne
    @JoinColumn(name = "idCre", insertable = false, updatable = false)
    private Creneau creneau;
    
    @ManyToOne
    @JoinColumn(name = "date", insertable = false, updatable = false)
    private DateCreneau date;
    
    //Constructeurs.

    public Proposer() {
    }

    public Proposer(ProposerID id, int nbPlaceDispoCre, int nbPlaceTotal) {
        this.id = id;
        this.nbPlaceDispoCre = nbPlaceDispoCre;
        this.nbPlaceTotal = nbPlaceTotal;
    }
    //Getters /Setters.

    public ProposerID getId() {return id;}
    public void setId(ProposerID id) { this.id = id;}
    public int getNbPlaceDispoCre() {return nbPlaceDispoCre;}
    public void setNbPlaceDispoCre(int nbPlaceDispoCre) { this.nbPlaceDispoCre = nbPlaceDispoCre;}
    public int getNbPlaceTotal() { return nbPlaceTotal; }
    public void setNbPlaceTotal(int nbPlaceTotal) { this.nbPlaceTotal = nbPlaceTotal;}
    public Magasin getMagasin() {return magasin;}
    public void setMagasin(Magasin magasin) { this.magasin = magasin;}
    public Creneau getCreneau() {return creneau;}
    public void setCreneau(Creneau creneau) { this.creneau = creneau;}
    public DateCreneau getDate() { return date;}
    public void setDate(DateCreneau date) { this.date = date;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Proposer{" + "id=" + id  + ", nbPlaceDispoCre=" + nbPlaceDispoCre + ", nbPlaceTotal=" + nbPlaceTotal + ", magasin=" + magasin + ", creneau=" + creneau + '}';
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
        final Proposer other = (Proposer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
