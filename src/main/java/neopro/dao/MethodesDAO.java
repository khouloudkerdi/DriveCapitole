package neopro.dao;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import neopro.metier.Article;
import neopro.metier.Client;
import neopro.metier.ListeCourses;
import neopro.metier.Rayon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Map;
import neopro.metier.Article;
import neopro.metier.AvoirQuantitePanier;
import neopro.metier.AvoirQuantitePanierID;
import neopro.metier.Panier;
import neopro.metier.Rayon;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MethodesDAO {
    
    //Function pour obtenir tous les rayons est les categories
    public static List<Rayon> getListRayon() throws ParseException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            /**     HQL     */
            List<Rayon> liste1 = session.createQuery("from Rayon").list();
            return liste1;
        }        
    }
    
    public static List<Article> listeArticle() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
            {

                Transaction t = session.beginTransaction();
                    
                List<Article> liste1 = session.createQuery("from Article ").list();
                return liste1;
      
            }
    }
    
    public static ArrayList<ListeCourses> getListeCourses(long id ) throws ParseException{
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
          /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c=session.get(Client.class, id);            
            Set<ListeCourses> l_courses = c.getListeCourses();
            ArrayList<ListeCourses> lc=new ArrayList<>();
            for (ListeCourses courses: l_courses){
                lc.add(courses);
            }
            return lc;         
     }
    }
    public static void ajouterListeCourses(long idClient,String NomListe) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = session.get(Client.class, idClient);
            ListeCourses l1 = new ListeCourses (NomListe,c1);
            session.save(l1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
     public static void supprimerListeCourses(long id) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();          
            ListeCourses l1 = session.get(ListeCourses.class,id);
            session.delete(l1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
      //Function pour obtenir tous les rayons est les categories
  

    
    public static List<Article> listeArticlePromo(){
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
            Transaction t = session.beginTransaction();
            List<Article> liste1 = session.createQuery("from Article a, AvoirPromo ap where a.idArt = ap.idArt").list();
            return liste1;
        }
    }
    
      public static void ajouterArticleListeCourse( long id_art,long id_lis ) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            ListeCourses l = session.get(ListeCourses.class,id_lis);
            Article a = session.get(Article.class,id_art);
            l.getArticles().add(a);
            a.getListeCourses().add(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
      
        public static void insererArticlePanier(long idA,long idpanier) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
            {
                Transaction t = session.beginTransaction();
                AvoirQuantitePanier qtePanier=null;
                Panier p=session.get(Panier.class, idpanier);
                Article a=session.get(Article.class, idA);
                boolean existe=false;
                Map<Article, AvoirQuantitePanier> listeArticle=p.getPaniers();
                
                for(Map.Entry<Article, AvoirQuantitePanier> entry : listeArticle.entrySet()){
	            if (entry.getKey().equals(a)){
                        qtePanier=entry.getValue();
                        existe=true;                 
                    }     
                }              
                if (existe){
                    int qte=qtePanier.getQuantite();
                    qte=qte+1;
                    qtePanier.setQuantite(qte);
                    listeArticle.put(a, qtePanier);
                    p.setPaniers(listeArticle);
                    session.save(p);                 
                    t.commit();
                }else{
                    AvoirQuantitePanier aqp = new AvoirQuantitePanier(new AvoirQuantitePanierID(idA, idpanier), 1);
                    session.save(aqp);                 
                    t.commit(); // Commit et flush automatique de la session.
                }
            }
        }
         
        public static long loadPanierClient(long idCli) {
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
            {
                Transaction t = session.beginTransaction();
                Client c=session.get(Client.class, idCli);
                Set<Panier> listeP=c.getPaniers();
                for (Panier p:listeP){
                    if (p.getEtatPan().toString().equals("EnCours")){
                        return p.getIdPan();
                    }
                }
                return 0;
            }
                
        }
  
}
     

