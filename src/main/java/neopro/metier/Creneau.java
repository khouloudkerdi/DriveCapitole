/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
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
    private String heure; 
    
    //relation Proposer
    @OneToMany(mappedBy = "creneau", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idMag")
    private Map<Magasin, Proposer> creneaux = new HashMap<>();
     
    //Constructeurs.

    public Creneau() {
    }

    public Creneau(String heure) {
        this.heure = heure;
    }
    
    //Getters /Setters.

    public long getIdCre() {return idCre;}
    public void setIdCre(long idCre) { this.idCre = idCre;}
    public String getHeure() {return heure;}
    public void setHeure(String heure) { this.heure = heure;}
    public Map<Magasin, Proposer> getCreneaux() {return creneaux;}
    public void setCreneaux(Map<Magasin, Proposer> creneaux) { this.creneaux = creneaux;}
   
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Creneau{" + "idCre=" + idCre + ", heure=" + heure + '}';
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
