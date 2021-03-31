/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author khoul
 */
@Entity(name ="Preferences")
public class Preferences {
    
    // Propriétés.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPre;
    @Enumerated(EnumType.STRING)
    private TypePreference TypePre ;
    private long idArt ;
    private long idMar;
    private long idCat;
    private long idLab;
    private String nutriscore ;
    
    //Relation  "Preferer" 
    @ManyToMany(mappedBy ="preferences")
    private Set<Client> clients = new HashSet<>(0);
    
    //Constructeurs.

    public Preferences() {
    }

    public Preferences( TypePreference TypePre, long idArt) {
        this.TypePre = TypePre;
        this.idArt = idArt;
 
    }
    
    //Getters /Setters.

    public long getIdPre() {return idPre;}
    public void setIdPre(long idPre) { this.idPre = idPre;}
    public TypePreference getTypePre() {return TypePre;}
    public void setTypePre(TypePreference TypePre) { this.TypePre = TypePre;}
    public long getIdArt() {return idArt;}
    public void setIdArt(long idArt) { this.idArt = idArt;}
    public long getIdMar() {return idMar;}
    public void setIdMar(long idMar) { this.idMar = idMar;}
    public long getIdCat() {return idCat;}
    public void setIdCat(long idCat) { this.idCat = idCat;}
    public long getIdLab() {return idLab;}
    public void setIdLab(long idLab) { this.idLab = idLab;}
    public String getNutriscore() {return nutriscore;}
    public void setNutriscore(String nutriscore) { this.nutriscore = nutriscore;}
    public Set<Client> getClients() {return clients;}
    public void setClients(Set<Client> clients) { this.clients = clients;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Preferences{" + "idPre=" + idPre + ", TypePre=" + TypePre + ", idArt=" + idArt + ", idMar=" + idMar + ", idCat=" + idCat + ", idLab=" + idLab + ", nutriscore=" + nutriscore + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.idPre ^ (this.idPre >>> 32));
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
        final Preferences other = (Preferences) obj;
        if (this.idPre != other.idPre) {
            return false;
        }
        return true;
    }
    
}
