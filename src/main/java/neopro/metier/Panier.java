/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author khoul
 */
@Entity
public class Panier {
    
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPan ;
    
    //Constructeurs.

    public Panier() {
    }
    
    //Getters /Setters.
    public long getIdPan() {return idPan;}
    public void setIdPan(long idPan) { this.idPan = idPan;}
    
    //Methodes.
    //Methodes Surchargées.
   
    @Override
    public String toString() {
        return "Panier{" + "idPan=" + idPan + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.idPan ^ (this.idPan >>> 32));
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
        final Panier other = (Panier) obj;
        if (this.idPan != other.idPan) {
            return false;
        }
        return true;
    }
    
    
}
