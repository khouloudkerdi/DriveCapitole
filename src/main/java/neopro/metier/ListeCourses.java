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
public class ListeCourses {
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLis;
    private String NomLis ;
    
    //Constructeurs.

    public ListeCourses() {
    }

    public ListeCourses(String NomLis) {
        this.NomLis = NomLis;
    }
    
    //Getters / Setters.

    public long getIdLis() {return idLis;}
    public void setNomLis(String NomLis) { this.NomLis = NomLis;}
    public String getNomLis() {return NomLis;}
    public void setIdLis(long idLis) { this.idLis = idLis;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "ListeCourses{" + "idLis=" + idLis + ", NomLis=" + NomLis + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.idLis ^ (this.idLis >>> 32));
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
        final ListeCourses other = (ListeCourses) obj;
        if (this.idLis != other.idLis) {
            return false;
        }
        return true;
    }
    
    
    
}
