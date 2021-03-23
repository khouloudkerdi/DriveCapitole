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
public class Commande {
    
    //Proprietes.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCom ;
    private EtatCommandeEnum etatCom ;
    
    //Constructeurs.

    public Commande() {
    }

    public Commande(EtatCommandeEnum etatCom) {
        this.etatCom = etatCom;
    }
    
    //Getters /Setters.

    public long getIdCom() {return idCom;}
    public void setIdCom(long idCom) { this.idCom = idCom;}
    public EtatCommandeEnum getEtatCom() {return etatCom;}
    public void setEtatCom(EtatCommandeEnum etatCom) { this.etatCom = etatCom;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Commande{" + "idCom=" + idCom + ", etatCom=" + etatCom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.idCom ^ (this.idCom >>> 32));
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
        final Commande other = (Commande) obj;
        if (this.idCom != other.idCom) {
            return false;
        }
        return true;
    }
    
    
}
