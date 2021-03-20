package neopro.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {
    // Propriétés
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idA ;
    private String libelleA;
    private String formatA;
    private float prixA  ;
    private String typePrixA  ;
    
    //Constructeurs 
    public Article() {}
    
    public Article(String libelleA, String formatA, float prixA, String typePrixA) {
        this.libelleA = libelleA;
        this.formatA = formatA;
        this.prixA = prixA;
        this.typePrixA = typePrixA;
    }
    
    //Getters / Setters
    public long getIdA() {return idA;}
    public void setIdA(long idA) { this.idA = idA;}
    public String getLibelleA() {return libelleA;}
    public void setLibelleA(String libelleA) { this.libelleA = libelleA;}
    public String getFormatA() {return formatA;}
    public void setFormatA(String formatA) {this.formatA = formatA;}
    public float getPrixA() {return prixA;}
    public void setPrixA(float prixA) { this.prixA = prixA;}
    public String getTypePrixA() {return typePrixA;}
    public void setTypePrixA(String typePrixA) {this.typePrixA = typePrixA;}
    
    //Méthodes 
    //Méthodes surchargées 

    @Override
    public String toString() {
        return "Article{" + "idA=" + idA + ", formatA=" + formatA + ", prixA=" + prixA + ", typePrixA=" + typePrixA + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.idA ^ (this.idA >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        final Article other = (Article) obj;
        if (this.idA != other.idA) {return false;}
        return true;
    }
    

 

  

   
    
    
}

