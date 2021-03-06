/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    
    //Relation "Faire"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCli")   
    private Client client ;
    
     //Relation  "ContenirArticle" 
    @ManyToMany(mappedBy ="listeCourses")
    private Set<Article> articles = new HashSet<>(0);

    //Réferences. Relation Contenir Postit
    @OneToMany (mappedBy ="listeCourses" , cascade =CascadeType.ALL ,fetch =FetchType.LAZY)
    private Set<Postit> postit = new HashSet<>(0);
    //Constructeurs.

    public ListeCourses() {
    }

    public ListeCourses(String NomLis, Client client) {
        this.NomLis = NomLis;
        this.client = client;
    }

    
    //Getters / Setters.

    public long getIdLis() {return idLis;}
    public void setNomLis(String NomLis) { this.NomLis = NomLis;}
    public String getNomLis() {return NomLis;}
    public void setIdLis(long idLis) { this.idLis = idLis;}
    public Client getClient() {return client;}
    public void setClient(Client client) { this.client = client;}
    public Set<Article> getArticles() {return articles;}
    public void setArticles(Set<Article> articles) { this.articles = articles;}
    public Set<Postit> getPostit() {return postit;}
    public void setPostit(Set<Postit> postit) { this.postit = postit;}
    
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
