/*
 * Nom de classe : HibernateUtil
 * Description   : Chargement de la configuration et 
                   création de la SessionFactory.
 * Date          : 20/03/2001
 * Copyright     : nabil 
 */
package neopro.dao;
import com.google.protobuf.TypeProto;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import neopro.metier.Article;
import neopro.metier.AvoirPromo;
import neopro.metier.AvoirPromoID;
import neopro.metier.AvoirQuantitePanier;
import neopro.metier.AvoirQuantitePanierID;
import neopro.metier.Categorie;
import neopro.metier.Client;
import neopro.metier.EtatPanier;
import neopro.metier.Label;
import neopro.metier.ListeCourses;
import neopro.metier.Magasin;
import neopro.metier.Marque;
import neopro.metier.NutriscoreArticle;
import static neopro.metier.NutriscoreArticle.A;
import neopro.metier.Panier;
import neopro.metier.Preferences;
import neopro.metier.Promotion;
import neopro.metier.Rayon;
import neopro.metier.TypePreference;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {

    /*----- Format de date -----*/
    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    
    //Function pour ajouter un rayon.
    public static void ajouterRayon() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Rayon r1 = new Rayon("Beaute");
            session.save(r1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    //Function pour ajouter une categorie.
    public static void ajouterCategorie() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Rayon r1 = session.get(Rayon.class, 1l);
            Categorie c1 = new Categorie("Legume surgelé",r1);
            session.save(c1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    //Function pour ajouter une marque.
    public static void ajouterMarque() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Marque m1 = new Marque("Bigard");
            session.save(m1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    //Function pour ajouter un Client.
    
    public static void ajouterClient() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = new Client("durond","Chloe","Chloe@gmail.com","1234", 20);
            System.out.println("client-----------------------"+c1);
            session.save(c1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    //Function pour ajouter une preference.
    
    public static void ajouterPreferences() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Preferences p1 = new Preferences(TypePreference.Produit,1l);
            session.save(p1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un magasin
    
    public static void ajouterMagasin() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Magasin m1 = new Magasin ("M2",31200);
            session.save(m1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
     //Function pour ajouter un Label
    
    public static void ajouterLabel() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Label l = new Label ("l");
            session.save(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    //Function pour ajouter un article 
    
    public static void ajouterArticle() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Categorie c1 = session.get(Categorie.class, 1l);
            Marque m1 = session.get(Marque.class, 2l);
            
            Article a1 = new Article ("Nestlé Naturnes carotte haricot vert potiron","pot","","c"
                    + "6x130g","NATURNES sélectionne avec le plus grand soin de bons ingrédients 100% d’origine naturelle* et préserve leur saveur pour faire découvrir à votre bébé le bon goût des différents ingrédients."
                            + "",NutriscoreArticle.A,1,1223,true,"ty",c1,m1);
            System.out.println("Article Insere:-------------"+a1);
            session.save(a1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
     //Function pour ajouter un Label a un article 
    
    public static void ajouterLabelArticle( long id_art,long id_lab ) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Label l = session.get(Label.class,id_lab);
            Article a = session.get(Article.class,id_art);
            System.out.println("infoLabel"+l.toString());
            System.out.println("infoArticle"+a.toString());
            l.getArticles().add(a);
            a.getLabels().add(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
      //Function pour ajouter un Label a un article 
    
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
     //Function pour ajouter une preference a un client
    
    public static void ajouterPrefClient( long id_cli,long id_pref ) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Preferences p = session.get(Preferences.class,id_pref);
            Client c = session.get(Client.class,id_cli);
            System.out.println("infopreference"+p.toString());
            System.out.println("infoClient"+c.toString());
            p.getClients().add(c);
            c.getPreferences().add(p);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
      //Function pour ajouter un promo
    
    public static void ajouterPromotion () throws ParseException
            {
            /*----- Ouverture de la session -----*/
            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
                    {
                    /*----- Ouverture d'une transaction -----*/
                    Transaction t = session.beginTransaction();
                    Promotion p1 = new Promotion("type",2,2);
                    System.out.println("infoPromotion-----------"+p1.toString());
                    session.save(p1);
                    t.commit(); // Commit et flush automatique de la session.
                    }
            }

      //Function pour ajouter un promo a un article 
    //long idPro, long idArt,Date datDebut , Date dateFin
    public static void ajouterPromoArticle() throws ParseException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            AvoirPromo a = new AvoirPromo(new AvoirPromoID(1l,1l),DF.parse("23-03-2021"),DF.parse("30-03-2021"));
            System.out.println("-------------objet a ----------"+a.toString());
            System.out.println("-------------objet id ----------"+a.getId().toString());
            session.save(a);
            Promotion promo = session.get(Promotion.class,1l);
            System.out.println("-------promo-->"+promo.toString());
            Article article = session.get(Article.class,1l );
              System.out.println("--------article-->"+article.toString());
            promo.getPromotions().put(article, a);
            article.getPromotions().put(promo, a);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
       //Function pour ajouter un promo a un article 
    //long idPro, long idArt,Date datDebut , Date dateFin
    public static void ajouterArticlePanier() throws ParseException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            AvoirQuantitePanier a = new AvoirQuantitePanier(new AvoirQuantitePanierID(1l,1l),1);
            session.save(a);
            Panier panier = session.get(Panier.class,1l);
            Article article = session.get(Article.class,1l );
            panier.getPaniers().put(article, a);
            article.getPaniers().put(panier, a);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    //Function pour ajouter un panier
    
    public static void ajouterPanier() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = session.get(Client.class, 1l);
            Panier p1 = new Panier (EtatPanier.EnCours,c1);
            session.save(p1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
//Function pour ajouter une liste courses 
    
    public static void ajouterListeCourses() {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = session.get(Client.class, 1l);
            ListeCourses l1 = new ListeCourses ("achat",c1);
            session.save(l1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
 //Fonction pour recuperer le montant total d'un article dans un panier d'un client 
   public static double montantTotaleArticlePanier(long idp,  long idArt)
     {
      try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
          /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Panier p =session.get(Panier.class, idp);
            Article a =session.get(Article.class, idArt);
            Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
            float montant = 0 ;
            for (Map.Entry mapentry : listeA.entrySet()) 
            {
                if(mapentry.getKey().equals(a))
                {   AvoirQuantitePanier aqp=(AvoirQuantitePanier ) mapentry.getValue();
                    montant=aqp.getQuantite() * a.getPrixArt();
                    
                }
            }
                 System.out.println("MontantArticlePanier----------------------"+montant);
                 BigDecimal eco1 = new BigDecimal(montant); 
                 double eco = eco1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
                 return eco; 
                  
       }
}
    

    /**
     * Programme de test.
     */
    public static void main(String[] args) throws ParseException {
        /*----- Test -----*/
      // TestHibernate.ajouterPromoArticle(1l,2l,DF.parse("23-03-2021"),DF.parse("30-03-2021"));
        long a=2;
        System.out.println(MethodesDAO.articleListeCourses(2));
      
        
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
