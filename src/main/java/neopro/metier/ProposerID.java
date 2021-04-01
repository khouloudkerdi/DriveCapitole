/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author khoul
 */
@Embeddable
public class ProposerID implements Serializable{
     // Propriétés.
    
    private long idMag ;
    private long idCre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date ;
    
    //Constructeurs.

    public ProposerID() {
    }

    public ProposerID(long idMag, long idCre, Date date) {
        this.idMag = idMag;
        this.idCre = idCre;
        this.date = date;
    }
    
    //Getters /Setters.

    public long getIdMag() {return idMag;}
    public void setIdMag(long idMag) { this.idMag = idMag;}
    public long getIdCre() {return idCre;}
    public void setIdCre(long idCre) { this.idCre = idCre;}
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.idMag ^ (this.idMag >>> 32));
        hash = 89 * hash + (int) (this.idCre ^ (this.idCre >>> 32));
        hash = 89 * hash + Objects.hashCode(this.date);
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
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
}
