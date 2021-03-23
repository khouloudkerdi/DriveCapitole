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
public class AvoirPromoID implements Serializable{
    
    // Propriétés.
    
    private long idPro ;
    private long idArt;
    
    //Constructeurs.

    public AvoirPromoID() {
    }

    public AvoirPromoID(long idPro, long idArt) {
        this.idPro = idPro;
        this.idArt = idArt;
    }
    
    //Getters /Setters.

    public long getIdPro() {return idPro;}
    public void setIdArt(long idArt) { this.idArt = idArt;} 
    public long getIdArt() {return idArt;}
    public void setIdPro(long idPro) { this.idPro = idPro;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.idPro ^ (this.idPro >>> 32));
        hash = 29 * hash + (int) (this.idArt ^ (this.idArt >>> 32));
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
        final AvoirPromoID other = (AvoirPromoID) obj;
        if (this.idPro != other.idPro) {
            return false;
        }
        if (this.idArt != other.idArt) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvoirPromoID{" + "idPro=" + idPro + ", idArt=" + idArt + '}';
    }
    
    
    
    
   
}
