/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author khoul
 */
@Entity(name ="Label")
public class Label {
    
    // Propriétés.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLab;
    private String libelleLab ;
    
    //Relation  "AvoirLabel" 
    @ManyToMany(mappedBy ="labels")
    private Set<Article> articles = new HashSet<>(0);
    
    //Constructeurs.

    public Label() {
    }

    public Label( String libelleLab) {
        this.libelleLab = libelleLab;
    }
    
    //Getters / Setters.

    public long getIdLab() {return idLab;}
    public void setIdLab(long idLab) { this.idLab = idLab;}
    public String getLibelleLab() {return libelleLab;}
    public void setLibelleLab(String libelleLab) { this.libelleLab = libelleLab;}
    public Set<Article> getArticles() { return articles;}
    public void setArticles(Set<Article> articles) { this.articles = articles;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Label{" + "idLab=" + idLab + ", libelleLab=" + libelleLab + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.idLab ^ (this.idLab >>> 32));
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
        final Label other = (Label) obj;
        if (this.idLab != other.idLab) {
            return false;
        }
        return true;
    }
    
    
    
    
}
