/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author khoul
 */
@Entity (name="Panier")
@SuppressWarnings("PersistenceUnitPresent")
public class Panier implements Serializable{
    
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPan ;
    @Enumerated(EnumType.STRING)
    private EtatPanier etatPan;
    
    //Relation "Disposer"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCli")   
    private Client client ;
    
    //Relation AvoirQuantite 
    
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idArt")
    private Map<Article, AvoirQuantitePanier> paniers = new HashMap<>();
 
    
    //Constructeurs.

    public Panier() {
    }

    public Panier(EtatPanier etatPan, Client client) {
        this.etatPan = etatPan;
        this.client = client;
    }
    
    //Getters /Setters.
    public long getIdPan() {return idPan;}
    public void setIdPan(long idPan) { this.idPan = idPan;}
    public EtatPanier getEtatPan() {return etatPan;}
    public void setEtatPan(EtatPanier etatPan) { this.etatPan = etatPan;}
    public Client getClient() {return client;}
    public void setClient(Client client) { this.client = client;}
    public Map<Article, AvoirQuantitePanier> getPaniers() {return paniers;}
    public void setPaniers(Map<Article, AvoirQuantitePanier> paniers) { this.paniers = paniers;}

    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Panier{" + "idPan=" + idPan + ", etatPan=" + etatPan + '}';
    }
   
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.idPan ^ (this.idPan >>> 32));
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
        final Panier other = (Panier) obj;
        if (this.idPan != other.idPan) {
            return false;
        }
        return true;
    }
    
    
}
