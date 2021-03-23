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
public class Magasin {
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMag ; 
    private String nomMag ;
    private int codePostaleMag ; 
    
    //Constructeurs. 

    public Magasin() {
    }
    
    public Magasin(String nomMag, int codePostaleMag) {
        this.nomMag = nomMag;
        this.codePostaleMag = codePostaleMag;
    }
    
    //Getters / Setters.

    public long getIdMag() {return idMag;}
    public void setIdMag(long idMag) { this.idMag = idMag;}
    public String getNomMag() {return nomMag;}
    public void setNomMag(String nomMag) { this.nomMag = nomMag;}
    public int getCodePostaleMag() {return codePostaleMag;}
    public void setCodePostaleMag(int codePostaleMag) { this.codePostaleMag = codePostaleMag;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Magasin{" + "idMag=" + idMag + ", nomMag=" + nomMag + ", codePostaleMag=" + codePostaleMag + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.idMag ^ (this.idMag >>> 32));
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
        final Magasin other = (Magasin) obj;
        if (this.idMag != other.idMag) {
            return false;
        }
        return true;
    }
}
