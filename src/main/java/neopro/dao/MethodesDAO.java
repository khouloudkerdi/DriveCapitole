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
import neopro.metier.Article;
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
}
     

