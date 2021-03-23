package neopro.dao;

import java.text.ParseException;
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
    
    
}
