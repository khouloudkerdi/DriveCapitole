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
public class QuantiteArticleID implements Serializable{
    
// Propriétés.
    
    private long idArt ;
    private long idCom;
    
    //Constructeurs.

    public QuantiteArticleID() {
    }

    public QuantiteArticleID(long idArt, long idCom) {
        this.idArt = idArt;
        this.idCom = idCom;
    }
    
    //Getters /Setters.

    public long getIdArt() {return idArt;}
    public void setIdArt(long idArt) { this.idArt = idArt;}
    public long getIdCom() {return idCom;}
    public void setIdCom(long idCom) { this.idCom = idCom;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.idArt ^ (this.idArt >>> 32));
        hash = 61 * hash + (int) (this.idCom ^ (this.idCom >>> 32));
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
        final QuantiteArticleID other = (QuantiteArticleID) obj;
        if (this.idArt != other.idArt) {
            return false;
        }
        if (this.idCom != other.idCom) {
            return false;
        }
        return true;
    }
    
    
    
}
