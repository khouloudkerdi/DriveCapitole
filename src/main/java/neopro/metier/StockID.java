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
public class StockID implements Serializable{
    
    // Propriétés.
    
    private long idMag ;
    private long idArt;
    
    //Constructeurs.

    public StockID() {
    }

    public StockID(long idMag, long idArt) {
        this.idMag = idMag;
        this.idArt = idArt;
    }
    
    //Getters /Setters.

    public long getIdMag() {return idMag;}
    public void setIdMag(long idMag) { this.idMag = idMag;}
    public long getIdArt() { return idArt;}
    public void setIdArt(long idArt) { this.idArt = idArt;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.idMag ^ (this.idMag >>> 32));
        hash = 83 * hash + (int) (this.idArt ^ (this.idArt >>> 32));
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
        final StockID other = (StockID) obj;
        if (this.idMag != other.idMag) {
            return false;
        }
        if (this.idArt != other.idArt) {
            return false;
        }
        return true;
    }
    
    
    
}
