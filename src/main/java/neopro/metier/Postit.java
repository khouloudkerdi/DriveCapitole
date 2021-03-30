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
@Entity(name ="Postit")
public class Postit {
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPos;
    private String contenuPos ;
    //Relation  "ContenirPostit" 
    @ManyToMany(mappedBy ="postits")
    private Set<ListeCourses> listeCourses = new HashSet<>(0);
    
    //Constructeurs.

    public Postit() {
    }

    public Postit( String contenuPos) {
        this.contenuPos = contenuPos;
    }
    
    //Getters / Setters.
    
    public long getIdPos() {return idPos;}
    public void setIdPos(long idPos) { this.idPos = idPos;}
    public String getContenuPos() {return contenuPos;}
    public void setContenuPos(String contenuPos) { this.contenuPos = contenuPos;}
    public Set<ListeCourses> getListeCourses() {return listeCourses;}
    public void setListeCourses(Set<ListeCourses> listeCourses) { this.listeCourses = listeCourses;}
    
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Postit{" + "idPos=" + idPos + ", contenuPos=" + contenuPos + ", listeCourses=" + listeCourses + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.idPos ^ (this.idPos >>> 32));
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
        final Postit other = (Postit) obj;
        if (this.idPos != other.idPos) {
            return false;
        }
        return true;
    }
    
    
    
}
