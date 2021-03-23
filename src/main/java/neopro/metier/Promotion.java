/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@SuppressWarnings("PersistenceUnitPresent")
public class Promotion implements Serializable{
    
    // Propriétés.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPro;
    private String typePro ;
    private int pourcentagePro ;
    private int  nbArticle ; 
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idArt")
    private Map<Article, AvoirPromo> promotions = new HashMap<>();
    
    //Constructeurs.

    public Promotion() {
    }

    public Promotion(String typePro, int pourcentagePro, int nbArticle) {
        this.typePro = typePro;
        this.pourcentagePro = pourcentagePro;
        this.nbArticle = nbArticle;
    }
    
    //Getters / Setters.

    public long getIdPro() {return idPro;}
    public void setIdPro(long idPro) { this.idPro = idPro;}
    public String getTypePro() {return typePro;}
    public void setTypePro(String typePro) { this.typePro = typePro;}
    public int getPourcentagePro() {return pourcentagePro;}
    public void setPourcentagePro(int pourcentagePro) { this.pourcentagePro = pourcentagePro;}
    public int getNbArticle() {return nbArticle;}
    public void setNbArticle(int nbArticle) { this.nbArticle = nbArticle;}
    public Map<Article, AvoirPromo> getPromotions() {return promotions;}
    public void setPromotions(Map<Article, AvoirPromo> promotions) { this.promotions = promotions;}
    
    //Méthodes.
    //Méthodes surchargées.

    @Override
    public String toString() {
        return "Promotion{" + "idPro=" + idPro + ", typePro=" + typePro + ", pourcentagePro=" + pourcentagePro + ", nbArticle=" + nbArticle + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.idPro ^ (this.idPro >>> 32));
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
        final Promotion other = (Promotion) obj;
        if (this.idPro != other.idPro) {
            return false;
        }
        return true;
    }
    
    
   
}
