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
public class ProposerID implements Serializable{
     // Propriétés.
    
    private long idMag ;
    private long idCre;
    
    //Constructeurs.

    public ProposerID() {
    }
    
    public ProposerID(long idMag, long idCre) {
        this.idMag = idMag;
        this.idCre = idCre;
    }
    
    //Getters /Setters.

    public long getIdMag() {return idMag;}
    public void setIdMag(long idMag) { this.idMag = idMag;}
    public long getIdCre() {return idCre;}
    public void setIdCre(long idCre) { this.idCre = idCre;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.idMag ^ (this.idMag >>> 32));
        hash = 23 * hash + (int) (this.idCre ^ (this.idCre >>> 32));
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
        final ProposerID other = (ProposerID) obj;
        if (this.idMag != other.idMag) {
            return false;
        }
        if (this.idCre != other.idCre) {
            return false;
        }
        return true;
    }
    
}
