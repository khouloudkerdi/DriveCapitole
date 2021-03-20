/*
 * Nom de classe : HibernateUtil
 * Description   : Chargement de la configuration et 
                   création de la SessionFactory.
 * Date          : 20/03/2001
 * Copyright     : nabil 
 */
package neopro.dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import neopro.metier.Article;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {

    /*----- Format de date -----*/
    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");

    public static void ajouterArticle() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Article a1 = new Article("eau", "litre", 0.5f, "unitaire");
            session.save(a1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    /**
     * Programme de test.
     */
    public static void main(String[] args) throws ParseException {
        /*----- Test -----*/
        TestHibernate.ajouterArticle();
       
        
        /*----- Exit -----*/
        System.exit(0);
    }

    /**
     * Affichage d'une liste de tableaux d'objets.
     */
    private static void affichage(List l) {
        Iterator e = l.iterator();
        while (e.hasNext()) {
            Object[] tab_obj = ((Object[]) e.next());

            for (Object obj : tab_obj) {
                System.out.print(obj + " ");
            }

            System.out.println("");
        }
    }

}
/*----- Fin de la classe TestHibernate -----*/
