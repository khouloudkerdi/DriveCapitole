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
import java.util.HashSet;
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
import neopro.metier.Postit;
import neopro.metier.Preferences;
import neopro.metier.Promotion;
import neopro.metier.Rayon;
import neopro.metier.TypePreference;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class TestHibernate {

    /*----- Format de date -----*/
    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");

    //Function pour ajouter un rayon.
    public static void ajouterRayon() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Rayon r1 = session.get(Rayon.class, 1l);
            Categorie c1 = new Categorie("Legume surgelé", r1);
            session.save(c1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter une marque.
    public static void ajouterMarque() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = new Client("durond", "Chloe", "Chloe@gmail.com", "1234", 20);
            System.out.println("client-----------------------" + c1);
            session.save(c1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter une preference.
    public static void ajouterPreferences() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Preferences p1 = new Preferences(TypePreference.Produit, 1l);
            session.save(p1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un magasin
    public static void ajouterMagasin() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Magasin m1 = new Magasin("M2", 31200);
            session.save(m1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un Label
    public static void ajouterLabel() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Label l = new Label("l");
            session.save(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un article 
    public static void ajouterArticle() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Categorie c1 = session.get(Categorie.class, 1l);
            Marque m1 = session.get(Marque.class, 2l);

            Article a1 = new Article("l", "f", "u", "c", "d", NutriscoreArticle.A, 1, 1, true, "t", "1", c1, m1);
            System.out.println("Article Insere:-------------" + a1);
            session.save(a1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un postit
    public static void ajouterPostit() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            ListeCourses l1 = session.get(ListeCourses.class, 64l);
            Postit p1 = new Postit("p", l1);
            System.out.println(p1.toString());
            session.save(p1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un Label a un article 
    public static void ajouterLabelArticle(long id_art, long id_lab) {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Label l = session.get(Label.class, id_lab);
            Article a = session.get(Article.class, id_art);
            System.out.println("infoLabel" + l.toString());
            System.out.println("infoArticle" + a.toString());
            l.getArticles().add(a);
            a.getLabels().add(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un Label a un article 
    public static void ajouterArticleListeCourse(long id_art, long id_lis) {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            ListeCourses l = session.get(ListeCourses.class, id_lis);
            Article a = session.get(Article.class, id_art);
            l.getArticles().add(a);
            a.getListeCourses().add(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    //Function pour ajouter une preference a un client

    public static void ajouterPrefClient(long id_cli, long id_pref) {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Preferences p = session.get(Preferences.class, id_pref);
            Client c = session.get(Client.class, id_cli);
            System.out.println("infopreference" + p.toString());
            System.out.println("infoClient" + c.toString());
            p.getClients().add(c);
            c.getPreferences().add(p);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un promo
    public static void ajouterPromotion() throws ParseException {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Promotion p1 = new Promotion("type", 2, 2);
            System.out.println("infoPromotion-----------" + p1.toString());
            session.save(p1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un promo a un article 
    //long idPro, long idArt,Date datDebut , Date dateFin
    public static void ajouterPromoArticle() throws ParseException {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            AvoirPromo a = new AvoirPromo(new AvoirPromoID(1l, 1l), DF.parse("23-03-2021"), DF.parse("30-03-2021"));
            System.out.println("-------------objet a ----------" + a.toString());
            System.out.println("-------------objet id ----------" + a.getId().toString());
            session.save(a);
            Promotion promo = session.get(Promotion.class, 1l);
            System.out.println("-------promo-->" + promo.toString());
            Article article = session.get(Article.class, 1l);
            System.out.println("--------article-->" + article.toString());
            promo.getPromotions().put(article, a);
            article.getPromotions().put(promo, a);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un promo a un article 
    //long idPro, long idArt,Date datDebut , Date dateFin
    public static void ajouterArticlePanier() throws ParseException {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            AvoirQuantitePanier a = new AvoirQuantitePanier(new AvoirQuantitePanierID(1l, 1l), 1);
            session.save(a);
            Panier panier = session.get(Panier.class, 1l);
            Article article = session.get(Article.class, 1l);
            panier.getPaniers().put(article, a);
            article.getPaniers().put(panier, a);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Function pour ajouter un panier
    public static void ajouterPanier() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = session.get(Client.class, 1l);
            Panier p1 = new Panier(EtatPanier.EnCours, c1);
            session.save(p1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

//Function pour ajouter une liste courses 
    public static void ajouterListeCourses() {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = session.get(Client.class, 1l);
            ListeCourses l1 = new ListeCourses("achat", c1);
            session.save(l1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    //Fonction pour recuperer le montant total d'un article dans un panier d'un client 
    public static double montantTotaleArticlePanier(long idp, long idArt) {
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Panier p = session.get(Panier.class, idp);
            Article a = session.get(Article.class, idArt);
            Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
            float montant = 0;
            for (Map.Entry mapentry : listeA.entrySet()) {
                if (mapentry.getKey().equals(a)) {
                    AvoirQuantitePanier aqp = (AvoirQuantitePanier) mapentry.getValue();
                    montant = aqp.getQuantite() * a.getPrixArt();

                }
            }
            System.out.println("MontantArticlePanier----------------------" + montant);
            BigDecimal eco1 = new BigDecimal(montant);
            double eco = eco1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            return eco;

        }
    }
    //Recuperation de la liste d'articles par Categorie.

    public static List<Article> ListeArticlesParCategorie(String idRay, String idCat) {
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            // Caster identifiant Rayon et Categorie.
            long idRayon = Long.parseLong(idRay);
            long idCategorie = Long.parseLong(idCat);
            // On recupere l'objet Rayon.
            Rayon rayon = session.get(Rayon.class, idRayon);
            // On recupere la liste des categories d'un Rayon.
            Set<Categorie> l_categories = rayon.getCategories();
            //Creation de liste d'articles 
            List<Article> listearticles = new ArrayList<Article>();
            // Parcourir la liste de categorie d'un rayon et s'il existe la categorie recherche on ajoute
            // ses articles a la liste d'articles.
            for (Categorie c : l_categories) {
                if (c.getIdCat() == idCategorie) {
                    Categorie cat = session.get(Categorie.class, c.getIdCat());
                    Set<Article> l_articles = cat.getArticles();
                    for (Article a : l_articles) {
                        listearticles.add(a);
                    }
                }
            }

            return listearticles;
        }

    }

    //Recuperation de la liste d'articles par rayon.
    public static List<Article> ListeArticlesParRayon(String idRay) {
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            long id = Long.parseLong(idRay);
            Rayon rayon = session.get(Rayon.class, id);
            Set<Categorie> l_categories = rayon.getCategories();
            List<Article> listearticles = new ArrayList<Article>();
            for (Categorie c : l_categories) {

                Set<Article> l_articles = c.getArticles();

                for (Article a : l_articles) {
                    listearticles.add(a);
                }
            }

            return listearticles;
        }
    }
// Recupertaion des labels d'un article 

    public static List<Label> getLabelsArticle(long idArt) {
        Set<Label> labArticle = new HashSet<>(0);
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Article art = session.get(Article.class, idArt);
            List<Label> listelabels = new ArrayList<Label>();
            labArticle = art.getLabels();
            for (Label label : labArticle) {
                listelabels.add(label);
            }
            return listelabels;
        }

    }

    // Recupertaion de la liste des magasins 
    public static List<Magasin> getListeMagasin(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            int cp = Integer.parseInt(code);
            List<Magasin> listeMAg = session.createQuery("from Magasin m where m.codePostaleMag =\"" + cp + "\" ").list();
            for (Magasin m : listeMAg) {
                System.out.println(m.getNomMag());
            }
            return listeMAg;
        }
    }

    /**
     * Programme de test.
     */
    public static void main(String[] args) throws ParseException {
        /*----- Test -----*/
        // TestHibernate.ajouterPromoArticle(1l,2l,DF.parse("23-03-2021"),DF.parse("30-03-2021"));
        //TestHibernate.ajouterArticle();
        //TestHibernate.ListeArticlesParRayon("2" );
        // TestHibernate.ListeArticlesNonPromoParRayon(1);
        /*
        List<Article> liste = new ArrayList<>();
        try ( Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //Article a1 = session.get(Article.class, 2l);
            Article a2 = session.get(Article.class, 2l);
            Article a3 = session.get(Article.class, 3l);
            Article a4 = session.get(Article.class, 4l);
            Article a5 = session.get(Article.class, 5l);
            //Article a6 = session.get(Article.class, 6l);
            Article a7 = session.get(Article.class, 7l);
            Article a8 = session.get(Article.class, 8l);
            Article a9 = session.get(Article.class, 9l);
            Article a10 = session.get(Article.class, 10l);
            Article a11 = session.get(Article.class, 11l);
            Article a12 = session.get(Article.class, 12l);
            
            //liste.add(a1);
            liste.add(a2);
            liste.add(a3);
            liste.add(a4);
            liste.add(a5);
            //liste.add(a6);
            liste.add(a7);
            liste.add(a8);
            liste.add(a9);
            liste.add(a10);
            liste.add(a11);
            liste.add(a12);
        }
        for (Article a : MethodesDAO.produitPostIt(liste, 1l)) {
            System.out.println("article"+a.getIdArt());
        }*/
        
        for (Article a:MethodesDAO.postitArticleRechercher("chat")){
            System.out.println(a.getIdArt());
        };

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
