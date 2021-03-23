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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author khoul
 */
@Entity(name="Client")
@SuppressWarnings("PersistenceUnitPresent")
public class Client implements Serializable{
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCli;
    private String nomCli ;
    private String prenomCli;
    private String email;
    private String motdepasse;
    
    //Relation AvoirLabel
    @ManyToMany 
    @JoinTable ( name="Preferer",
                 joinColumns = @JoinColumn(name="idCli"),
                 inverseJoinColumns = @JoinColumn (name="idPre"))
    private Set<Preferences> preferences = new HashSet<>(0);
    
    //Réferences. Relation Disposer
    @OneToMany (mappedBy ="client" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<Panier> paniers = new HashSet<>(0);
    
     //Réferences. Relation Faire
    @OneToMany (mappedBy ="client" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<ListeCourses> listeCourses = new HashSet<>(0);
    
    
    //Constructeurs.

    public Client() {
    }

    public Client( String NomCli, String PrenomCli, String email, String Motdepasse) {
        this.nomCli = NomCli;
        this.prenomCli = PrenomCli;
        this.email = email;
        this.motdepasse = Motdepasse;
    }
    
    //Getters / Setters.

    public long getIdCli() {return idCli;}
    public void setIdCli(long idCli) { this.idCli = idCli;}
    public String getNomCli() { return nomCli;}
    public void setNomCli(String NomCli) { this.nomCli = NomCli;}
    public String getPrenomCli() {return prenomCli;}
    public void setPrenomCli(String PrenomCli) { this.prenomCli = PrenomCli;}
    public String getEmail() {return email;}
    public void setEmail(String email) { this.email = email;}
    public String getMotdepasse() {return motdepasse;}
    public void setMotdepasse(String Motdepasse) { this.motdepasse = Motdepasse;}
    public Set<Preferences> getPreferences() { return preferences;}
    public void setPreferences(Set<Preferences> preferences) { this.preferences = preferences;}
    public Set<Panier> getPaniers() {return paniers;}
    public void setPaniers(Set<Panier> paniers) { this.paniers = paniers;}
    public Set<ListeCourses> getListeCourses() {return listeCourses;}
    public void setListeCourses(Set<ListeCourses> listeCourses) { this.listeCourses = listeCourses;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Client{" + "idCli=" + idCli + ", nomCli=" + nomCli + ", prenomCli=" + prenomCli + ", email=" + email + ", motdepasse=" + motdepasse + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.idCli ^ (this.idCli >>> 32));
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
        final Client other = (Client) obj;
        if (this.idCli != other.idCli) {
            return false;
        }
        return true;
    }
    
    
}
