/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author khoul
 */
@Embeddable
public class AvoirQuantitePanierID implements Serializable{
    
    // Propriétés.
    
    private long idArt ;
    private long idPan;
    
    //Constructeurs.

    public AvoirQuantitePanierID() {
    }

    public AvoirQuantitePanierID(long idArt, long idPan) {
        this.idArt = idArt;
        this.idPan = idPan;
    }
    
    //Getters /Setters.

    public long getIdArt() {return idArt;}
    public void setIdArt(long idArt) { this.idArt = idArt;}
    public long getIdPan() {return idPan;}
    public void setIdPan(long idPan) { this.idPan = idPan;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.idArt ^ (this.idArt >>> 32));
        hash = 67 * hash + (int) (this.idPan ^ (this.idPan >>> 32));
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
        final AvoirQuantitePanierID other = (AvoirQuantitePanierID) obj;
        if (this.idArt != other.idArt) {
            return false;
        }
        if (this.idPan != other.idPan) {
            return false;
        }
        return true;
    }
    
    
}
