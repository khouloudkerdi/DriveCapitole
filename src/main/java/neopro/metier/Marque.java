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

/**
 *
 * @author khoul
 */
@Entity (name="Marque")
public class Marque implements Serializable{
    
    // Propriétés.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMar")
    private long idMar;
    private String libelleMar ;
    
    //Réferences. Relation Appartenir
    @OneToMany (mappedBy ="marque" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<Article> articles = new HashSet<>(0);
    
    //Constructeurs.

    public Marque() {
    }

    public Marque(String libelleMar) {
        this.libelleMar = libelleMar;
    }
    
    //Getters / Setters.

    public long getIdMar() {return idMar;}
    public void setIdMar(long idMar) { this.idMar = idMar;}
    public String getLibelleMar() {return libelleMar;}
    public void setLibelleMar(String libelleMar) { this.libelleMar = libelleMar;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Marque{" + "idMar=" + idMar + ", libelleMar=" + libelleMar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.idMar ^ (this.idMar >>> 32));
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
        final Marque other = (Marque) obj;
        if (this.idMar != other.idMar) {
            return false;
        }
        return true;
    }
    
    
    
}
