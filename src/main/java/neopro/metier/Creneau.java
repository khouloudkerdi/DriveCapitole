/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author khoul
 */
@Entity
public class Creneau {
    
    //Proprietes.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCre;
    private int nbPlaceDispoCre ; 
    private int nbPlaceTotal; 
    
    //Constructeurs.

    public Creneau() {
    }

    public Creneau(Date dateCre, int nbPlaceDispoCre, int nbPlaceTotal) {
        this.dateCre = dateCre;
        this.nbPlaceDispoCre = nbPlaceDispoCre;
        this.nbPlaceTotal = nbPlaceTotal;
    }
    
    //Getters /Setters.

    public long getIdCre() {return idCre;}
    public void setIdCre(long idCre) { this.idCre = idCre;}
    public Date getDateCre() {return dateCre;}
    public void setDateCre(Date dateCre) { this.dateCre = dateCre;}
    public int getNbPlaceDispoCre() {return nbPlaceDispoCre;}
    public void setNbPlaceDispoCre(int nbPlaceDispoCre) { this.nbPlaceDispoCre = nbPlaceDispoCre;}
    public int getNbPlaceTotal() { return nbPlaceTotal;}
    public void setNbPlaceTotal(int nbPlaceTotal) { this.nbPlaceTotal = nbPlaceTotal;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Creneau{" + "idCre=" + idCre + ", dateCre=" + dateCre + ", nbPlaceDispoCre=" + nbPlaceDispoCre + ", nbPlaceTotal=" + nbPlaceTotal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.idCre ^ (this.idCre >>> 32));
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
        final Creneau other = (Creneau) obj;
        if (this.idCre != other.idCre) {
            return false;
        }
        return true;
    }
    
    
    
    
}
