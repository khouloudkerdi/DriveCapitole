package neopro.metier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

@Entity (name="Article")
@SuppressWarnings("PersistenceUnitPresent")
public class Article implements Serializable{
    
    // Propriétés
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idArt")
    private long idArt ;
    private String libelleArt;
    private String formatArt;
    private String urlImageArt  ;
    private String condArt  ;
    private String descriptionArt;
    @Enumerated(EnumType.STRING)
    private NutriscoreArticle nutriscoreArt;
    private float prixArt;
    private long eanArt;
    private boolean bioArt;
    private String typePrix ;
    
    //Relation "Posseder"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCat")   
    private Categorie categorie ;
    
    //Relation "Appartenir"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMar")   
    private Marque marque ;
    
    //Relation AvoirLabel
    @ManyToMany 
    @JoinTable ( name="AvoirLabel",
                 joinColumns = @JoinColumn(name="idArt"),
                 inverseJoinColumns = @JoinColumn (name="idLab"))
    private Set<Label> labels = new HashSet<>(0);
    
    //Relation AvoirPromotion 
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idPro")
    private Map<Promotion, AvoirPromo> promotions = new HashMap<>();
    
    //Relation AvoirQuantitePanier  
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idPan")
    private Map<Panier, AvoirQuantitePanier> paniers = new HashMap<>();
    
    //Relation ContenirArticle
    @ManyToMany 
    @JoinTable ( name="ContenirArticle",
                 joinColumns = @JoinColumn(name="idArt"),
                 inverseJoinColumns = @JoinColumn (name="idLis"))
    private Set<ListeCourses> listeCourses = new HashSet<>(0);
    
    //Constructeurs 
    public Article() {}

    public Article( String libelleArt, String formatArt, String urlImageArt, String condArt, String descriptionArt, NutriscoreArticle nutriscoreArt, float prixArt, long eanArt, boolean bioArt, String typePrix ,Categorie cat , Marque mr) {
        this.libelleArt = libelleArt;
        this.formatArt = formatArt;
        this.urlImageArt = urlImageArt;
        this.condArt = condArt;
        this.descriptionArt = descriptionArt;
        this.nutriscoreArt = nutriscoreArt;
        this.prixArt = prixArt;
        this.eanArt = eanArt;
        this.bioArt = bioArt;
        this.typePrix = typePrix;
        this.categorie= cat ;
        this.marque = mr ;
    }
    
    public Article(String l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Getters /Setters.

    public long getIdArt() {return idArt;}
    public void setIdArt(long idArt) { this.idArt = idArt;}
    public String getLibelleArt() {return libelleArt;}
    public void setLibelleArt(String libelleArt) { this.libelleArt = libelleArt;}
    public String getFormatArt() {return formatArt;}
    public void setFormatArt(String formatArt) { this.formatArt = formatArt;}
    public String getUrlImageArt() {return urlImageArt;}
    public void setUrlImageArt(String urlImageArt) { this.urlImageArt = urlImageArt;}
    public String getCondArt() {return condArt;}
    public void setCondArt(String condArt) { this.condArt = condArt;}
    public String getDescriptionArt() {return descriptionArt;}
    public void setDescriptionArt(String descriptionArt) { this.descriptionArt = descriptionArt;}
    public NutriscoreArticle getNutriscoreArt() {return nutriscoreArt;}
    public void setNutriscoreArt(NutriscoreArticle nutriscoreArt) { this.nutriscoreArt = nutriscoreArt;}
    public float getPrixArt() {return prixArt;}
    public void setPrixArt(float prixArt) { this.prixArt = prixArt;}
    public long getEanArt() {return eanArt;}
    public void setEanArt(long eanArt) { this.eanArt = eanArt;}
    public boolean isBioArt() {return bioArt;}
    public void setBioArt(boolean bioArt) { this.bioArt = bioArt;}
    public String getTypePrix() {return typePrix;}
    public void setTypePrix(String typePrix) { this.typePrix = typePrix;}
    public Categorie getCategorie() {return categorie;}
    public void setCategorie(Categorie categorie) { this.categorie = categorie;}
    public Marque getMarque() {return marque;}
    public void setMarque(Marque marque) { this.marque = marque;}
    public Set<Label> getLabels() {return labels;}
    public void setLabels(Set<Label> labels) { this.labels = labels;}
    public Map<Promotion, AvoirPromo> getPromotions() {return promotions;}
    public void setPromotions(Map<Promotion, AvoirPromo> promotions) { this.promotions = promotions;}
    public Map<Panier, AvoirQuantitePanier> getPaniers() {return paniers;}
    public void setPaniers(Map<Panier, AvoirQuantitePanier> paniers) {this.paniers = paniers;}
    public Set<ListeCourses> getListeCourses() {return listeCourses;}
    public void setListeCourses(Set<ListeCourses> listeCourses) { this.listeCourses = listeCourses;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Article{" + "idArt=" + idArt + ", libelleArt=" + libelleArt + ", formatArt=" + formatArt + ", urlImageArt=" + urlImageArt + ", condArt=" + condArt + ", descriptionArt=" + descriptionArt + ", nutriscoreArt=" + nutriscoreArt + ", prixArt=" + prixArt + ", eanArt=" + eanArt + ", bioArt=" + bioArt + ", typePrix=" + typePrix + ", categorie=" + categorie + ", marque=" + marque + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.idArt ^ (this.idArt >>> 32));
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
        final Article other = (Article) obj;
        if (this.idArt != other.idArt) {
            return false;
        }
        return true;
    }
       
}

