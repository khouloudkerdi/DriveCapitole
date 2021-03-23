/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author khoul
 */
@Entity (name="Rayon")
public class Rayon implements Serializable{
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRay")
    private long idRay;
    private String libelleRay ;
    
    //Réferences. Relation Rattacher  
    @OneToMany (mappedBy ="rayon" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<Categorie> categories = new HashSet<>(0);
    
    //Constructeurs.

    public Rayon() {
    }

    public Rayon(String libelleRay) {
        this.libelleRay = libelleRay;
    }
    
    //Getters / Setters.

    public long getIdRay() {return idRay;}
    public void setIdRay(long idRay) { this.idRay = idRay;}
    public String getLibelleRay() {return libelleRay;}
    public void setLibelleRay(String libelleRay) { this.libelleRay = libelleRay;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Rayon{" + "idRay=" + idRay + ", libelleRay=" + libelleRay + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.idRay ^ (this.idRay >>> 32));
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
        final Rayon other = (Rayon) obj;
        if (this.idRay != other.idRay) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
