/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

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
    private String adresseMag ;
    
     //Réferences. Relation Appartenir
    @OneToMany (mappedBy ="magasin" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<Client> clients = new HashSet<>(0);
    
    //Relation Proposer
    @OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idCre")
    private Map<Creneau, Proposer> creneaux = new HashMap<>();
    
    //Constructeurs. 

    public Magasin() {
    }

    public Magasin(String nomMag, int codePostaleMag, String adresseMag) {
        this.nomMag = nomMag;
        this.codePostaleMag = codePostaleMag;
        this.adresseMag = adresseMag;
    }
    
    //Getters / Setters.

    public long getIdMag() {return idMag;}
    public void setIdMag(long idMag) { this.idMag = idMag;}
    public String getNomMag() {return nomMag;}
    public void setNomMag(String nomMag) { this.nomMag = nomMag;}
    public int getCodePostaleMag() {return codePostaleMag;}
    public void setCodePostaleMag(int codePostaleMag) { this.codePostaleMag = codePostaleMag;}
    public String getAdresseMag() { return adresseMag; }
    public void setAdresseMag(String adresseMag) { this.adresseMag = adresseMag;}
    public Set<Client> getClients() {return clients;}
    public void setClients(Set<Client> clients) { this.clients = clients;}
    public Map<Creneau, Proposer> getCreneaux() {return creneaux;}
    public void setCreneaux(Map<Creneau, Proposer> creneaux) { this.creneaux = creneaux;}
   
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Magasin{" + "idMag=" + idMag + ", nomMag=" + nomMag + ", codePostaleMag=" + codePostaleMag + ", adresseMag=" + adresseMag + ", clients=" + clients + '}';
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
