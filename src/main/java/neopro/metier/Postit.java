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
public class Postit {
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPos;
    private String contenuPos ;
    
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
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Postit{" + "idPos=" + idPos + ", contenuPos=" + contenuPos + '}';
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
