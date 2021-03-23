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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author khoul
 */
@Entity (name="Categorie")
public class Categorie implements Serializable{
    
    // Propriétés.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCat")
    private long idCat;
    private String libelleCat ;
    
   //Relation "Rattacher "
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRayon")   
    private Rayon rayon ;
    
    //Réferences. Relation Posseder 
    @OneToMany (mappedBy ="categorie" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<Article> articles = new HashSet<>(0);

    //Constructeurs. 

    public Categorie() {
    }

    public Categorie(String libelleCat ,Rayon r) {
        this.libelleCat = libelleCat;
        this.rayon = r ;
    }
    
    //Getters / Setters.

    public long getIdCat() {return idCat;}
    public void setIdCat(long idCat) { this.idCat = idCat;}
    public String getLibelleCat() {return libelleCat;}
    public void setLibelleCat(String libelleCat) { this.libelleCat = libelleCat;}
    public Rayon getRayon() {return rayon;}
    public void setRayon(Rayon rayon) { this.rayon = rayon;}

    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Categorie{" + "idCat=" + idCat + ", libelleCat=" + libelleCat + ", rayon=" + rayon + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.idCat ^ (this.idCat >>> 32));
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
        final Categorie other = (Categorie) obj;
        if (this.idCat != other.idCat) {
            return false;
        }
        return true;
    }
    
    
    
    
}
