package neopro.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import neopro.metier.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MethodesDAO {

    // Fonction pour obtenir tous les rayons est les categories
    public static List<Rayon> getListRayon() throws ParseException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            /**
             * HQL
             */
            List<Rayon> liste1 = session.createQuery("from Rayon").list();
            return liste1;
        }
    }

    // Fonction pour obtenir tous les articles.
    public static List<Article> listeArticle() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            List<Article> liste1 = session.createQuery("from Article ").list();
            return liste1;
        }
    }

    // Fonction pour obtenir tous les articles en promotion.
    public static List<Article> listePromo() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            List<Article> liste1 = session.createQuery("select distinct a "
                    + "from Article a, AvoirPromo p "
                    + "where a.idArt=p.article.idArt "
                    + "and p.dateDebut < now() "
                    + "and p.datefin > now() ").list();
            return liste1;
        }
    }

    // Fonction pour obtenir tous les articles qui ne sont pas en promotion.
    public static List<Article> listeNonPromo() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            List<Article> liste1 = session.createQuery("select distinct a "
                    + "from Article a, AvoirPromo p "
                    + "where a.idArt not in ("
                    + "select distinct a "
                    + "from Article a, AvoirPromo p "
                    + "where a.idArt=p.article.idArt "
                    + "and p.dateDebut < now() "
                    + "and p.datefin > now() )").list();
            return liste1;
        }
    }

    // Fonction pour calculer l'économie sur un article en promotion.
    public static float calculerPrixPromo(Long idArt) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Article a = session.get(Article.class, idArt);
            Float prix = a.getPrixArt();

            String hql = "select p.pourcentagePro "
                    + "from Promotion p, AvoirPromo ap "
                    + "where ap.article.idArt = :id "
                    + "and p.idPro = ap.promotion.idPro "
                    + "and ap.dateDebut < now() "
                    + "and ap.datefin > now()";
            Query query = session.createQuery(hql);
            query.setParameter("id", a.getIdArt());
            List<Integer> list = query.list();
            Integer pourcentage = list.get(0);
            Float ecof = prix * (pourcentage.floatValue() / 100);
            BigDecimal eco1 = new BigDecimal(ecof);
            float eco = eco1.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            return eco;
        }
    }

    // Fonction pour obtenir le pourcentage d'un article en promotion
    public static Integer getPromoPourcentage(Long idArt) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Article a = session.get(Article.class, idArt);
            Float prix = a.getPrixArt();

            String hql = "select p.pourcentagePro "
                    + "from Promotion p, AvoirPromo ap "
                    + "where ap.article.idArt = :id "
                    + "and p.idPro = ap.promotion.idPro "
                    + "and ap.dateDebut < now() "
                    + "and ap.datefin > now()";
            Query query = session.createQuery(hql);
            query.setParameter("id", a.getIdArt());
            List<Integer> list = query.list();
            Integer nbPourcentage = list.get(0);
            return nbPourcentage;
        }
    }

    // Fonction pour obtenir la liste de préférence d'un client (*ICI c'est CHOLE)
    public static List<Article> listePref(long idClient) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Set<Preferences> liste1 = session.get(Client.class, idClient).getPreferences();
            List<Article> liste2 = new ArrayList<Article>();
            for (Preferences r : liste1) {
                if (r.getIdArt() > 0) {
                    Article a = session.get(Article.class, r.getIdArt());
                    liste2.add(a);
                }
            }
            return liste2;
        }
    }

    // Fonction qui récupère la liste des articles d'un panier pour un client donné.
    public static ArrayList<Article> listeArtcilesPanierClient(long idcli) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idcli);
            Set<Panier> l_paniers = c.getPaniers();
            ArrayList<Article> lc = new ArrayList<>();
            for (Panier panier : l_paniers) {
                String etat = panier.getEtatPan().toString();
                if (etat == "EnCours") {
                    long idp = panier.getIdPan();
                    Panier p = session.get(Panier.class, idp);
                    Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
                    for (Map.Entry mapentry : listeA.entrySet()) {

                        lc.add((Article) mapentry.getKey());
                    }

                    System.out.println(idp);
                    System.out.println(panier.toString());
                }
            }
            return lc;
        }
    }

    // Fonction pour récupérer le montant d'un panier.
    public static float montantPanier(long idcli) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();

            Client c = session.get(Client.class, idcli);
            Set<Panier> l_paniers = c.getPaniers();
            float montant = 0;
            ArrayList<Article> lc = new ArrayList<>();
            for (Panier panier : l_paniers) {
                String etat = panier.getEtatPan().toString();
                if (etat == "EnCours") {
                    long idp = panier.getIdPan();
                    Panier p = session.get(Panier.class, idp);
                    Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
                    for (Map.Entry mapentry : listeA.entrySet()) {
                        lc.add((Article) mapentry.getKey());
                        Article a = (Article) mapentry.getKey();
                        AvoirQuantitePanier aqp = (AvoirQuantitePanier) mapentry.getValue();
                        montant = montant + a.getPrixArt() * aqp.getQuantite();
                    }

                }
            }
            BigDecimal montantDecim = new BigDecimal(montant);
            float montantfinal = montantDecim.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            return montantfinal;
        }
    }

    // Fonction pour récupérer le idPanier qui est enCours d'un client à partir de son idCli.
    public static long getIdPanierByIdCli(long idCli) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();

            String hql = "select p.idPan "
                    + "from Panier p "
                    + "where p.client.idCli = :id "
                    + "and p.etatPan = 'EnCours' ";
            Query query = session.createQuery(hql);
            query.setParameter("id", idCli);
            List<Long> list = query.list();
            Long idPan;
            if (list.size() == 0) {
                Client c1 = session.get(Client.class, idCli);
                Panier p1 = new Panier(EtatPanier.EnCours, c1);
                session.save(p1);
                t.commit();
                idPan = p1.getIdPan();
            } else {
                idPan = list.get(0);
            }
            return idPan;
        }
    }

    // Fonction pour récupérer la quantite d'un article dans un panier.
    public static int QuantiteArticlePanier(long idp, long idArt) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Panier p = session.get(Panier.class, idp);
            Article a = session.get(Article.class, idArt);
            Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
            int quantite = 0;
            for (Map.Entry mapentry : listeA.entrySet()) {
                if (mapentry.getKey().equals(a)) {
                    AvoirQuantitePanier aqp = (AvoirQuantitePanier) mapentry.getValue();
                    quantite = aqp.getQuantite();

                }
            }
            System.out.println(quantite);
            return quantite;
        }
    }

    // Fonction pour recuperer le montant total d'un article dans un panier d'un client.
    public static float montantTotaleArticlePanier(long idp, long idArt) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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
            BigDecimal montantDecim = new BigDecimal(montant);
            float eco = montantDecim.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            return eco;
        }
    }

    // Fonction pour supprimer un produit d'un panier
    public static void insererArticlePanier(long idA, long idP) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();

            // Recuperer l'assosiation 'AvoirQuantitePanier'
            AvoirQuantitePanier aqp = session.get(AvoirQuantitePanier.class, new AvoirQuantitePanierID(idA, idP));;
            // Recuperer l'objet 'Panier' par 'idpanier'
            Panier p = session.get(Panier.class, idP);
            // Recuperer l'objet 'Article' par 'idA' 
            Article a = session.get(Article.class, idA);

            // si ce produit n'existe pas dans le panier, on l'ajoute a la panier
            if (aqp == null) {
                AvoirQuantitePanier aqp1 = new AvoirQuantitePanier(new AvoirQuantitePanierID(idA, idP), 1);
                session.save(aqp1);
            } else {
                // si la quantite n'est pas de 0, on fait plus 1 de la quantite de ce produit du panier 
                aqp.setQuantite(aqp.getQuantite() + 1);
                p.getPaniers().put(a, aqp);
            }

            // enregistrer sur BD
            t.commit();
        }
    }

    // Fonction pour supprimer un produit d'un panier
    public static void supprimerArticlePanier(long idA, long idP) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();

            // Recuperer l'assosiation 'AvoirQuantitePanier'
            AvoirQuantitePanier aqp = session.get(AvoirQuantitePanier.class, new AvoirQuantitePanierID(idA, idP));;
            // Recuperer l'objet 'Panier' par 'idpanier'
            Panier p = session.get(Panier.class, idP);
            // Recuperer l'objet 'Article' par 'idA' 
            Article a = session.get(Article.class, idA);

            // calculer la quantite d'un produit dans ce panier
            int qte = aqp.getQuantite();
            qte = qte - 1;
            // si la quantite est de 0, on supprime ce ligne de contenue du panier
            if (qte == 0) {
                p.getPaniers().remove(a);
                a.getPaniers().remove(p);
                session.delete(aqp);
            } else {
                // si la quantite n'est pas de 0, on change la quantite de ce produit du panier                
                aqp.setQuantite(qte);
                p.getPaniers().put(a, aqp);
                session.save(p);
            }
            // enregistrer sur BD
            t.commit();
        }
    }

    public static void ajouterArticleListeCourse(long id_art, long id_lis) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            ListeCourses l = session.get(ListeCourses.class, id_lis);
            Article a = session.get(Article.class, id_art);
            l.getArticles().add(a);
            a.getListeCourses().add(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    // Fonction pour obtenir l'identifiant de panier d'un client
    public static long loadPanierClient(long idCli) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idCli);
            Set<Panier> listeP = c.getPaniers();
            for (Panier p : listeP) {
                if (p.getEtatPan().toString().equals("EnCours")) {
                    return p.getIdPan();
                }
            }
            return 0;
        }
    }

    // Fonction pour obtenir la liste de courses d'un client
    public static ArrayList<ListeCourses> getListeCourses(long id) throws ParseException {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, id);
            Set<ListeCourses> l_courses = c.getListeCourses();
            ArrayList<ListeCourses> lc = new ArrayList<>();
            for (ListeCourses courses : l_courses) {
                lc.add(courses);
            }
            return lc;
        }
    }

    // Fonction pour ajouter une liste de courses
    public static void ajouterListeCourses(long idClient, String NomListe) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c1 = session.get(Client.class, idClient);
            ListeCourses l1 = new ListeCourses(NomListe, c1);
            session.save(l1);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    // Fonction pour supprimer une liste de courses
    public static void supprimerListeCourses(long id) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            ListeCourses l = session.get(ListeCourses.class, id);
            ArrayList<Article> listeArticle = new ArrayList<>();
            for (Article b : l.getArticles()) {
                listeArticle.add(b);
                b.getListeCourses().remove(l);
            }
            for (Article a : listeArticle) {
                l.getArticles().remove(a);
            }
            session.delete(l);
            t.commit(); // Commit et flush automatique de la session.
        }
    }

    // Fonction pour obtenir les informations d'un client à partir de son id.
    public static Client infosClient(long id_cli) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, id_cli);
            System.out.println(c);
            return c;
        }
    }

    // calcule le nombre d'article dans un panier 
    public static long nbArt(long idCli) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            String hql = "select sum(aqp.quantite) "
                    + "from AvoirQuantitePanier aqp, Panier p "
                    + "where p.idPan = aqp.panier.idPan "
                    + "and p.client.idCli = :id "
                    + "and p.etatPan = 'EnCours' ";
            Query query = session.createQuery(hql);
            query.setParameter("id", idCli);
            List<Long> list = query.list();
            Long nbArt = list.get(0);
            if (nbArt == null) {
                nbArt = 0l;
            }
            return nbArt;
        }
    }

    // recherche dans libelle court des articles 
    public static List<Article> listRecherche(String search) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            String var = search;
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            // Récupération des la liste de recherche.          
            String hql = "select a from Article a where a.libelleArt like :rollNumber";
            Query query = session.createQuery(hql);
            query.setParameter("rollNumber", "%" + var + "%");
            List result = query.list();
            // Envoi du résultat de la requête.
            return result;
        }
    }

    //verifier l'adresse de mail et mot de passe et retourner l'identifiant de client
    //si l'adresse de mail et mot de passe n'est pas correst, retourner 0
    public static long verifierCompte(String mail, String mdp) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            List<Client> listeC = session.createQuery("from Client ").list();
            for (Client c : listeC) {
                if ((c.getEmail().equals(mail)) & (c.getMotdepasse().equals(mdp))) {
                    return c.getIdCli();
                }
            }
            long f = 0;
            return f;
        }
    }

    //Recuperation de la liste d'articles par rayon.
    public static List<Article> ListeArticlesParRayon(String idRay) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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

    //obtenir tous les produit d'une liste de courses
    public static ArrayList<Article> articleListeCourses(long idListe) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Set<Article> lc = session.get(ListeCourses.class, idListe).getArticles();
            ArrayList<Article> liste2 = new ArrayList<Article>();
            for (Article a : lc) {
                liste2.add(a);
            }
            return liste2;
        }
    }
    //Recuperation de la liste d'articles par Categorie.

    public static List<Article> ListeArticlesParCategorie(String idRay, String idCat) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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

    public static ListeCourses loadListeCourses(long idListe) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            return session.get(ListeCourses.class, idListe);
        }
    }
    // Recupertaion des labels d'un article 

    public static List<Label> getLabelsArticle(long idArt) {
        Set<Label> labArticle = new HashSet<>(0);
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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

    public static List<Article> listeDansListe(List<Article> grandListe, List<Article> petitListe) {
        List<Article> listeArticle = new ArrayList<Article>();
        boolean existe = false;
        for (Article gl : grandListe) {
            existe = false;
            for (Article pl : petitListe) {
                if (gl.equals(pl)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                listeArticle.add(gl);
            }
        }
        return listeArticle;
    }

    // Recupertaion de la liste des magasins 
    public static List<Magasin> getListeMagasin(String code) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            int cp = Integer.parseInt(code);
            List<Magasin> listeMAg = session.createQuery("from Magasin m where m.codePostaleMag =\"" + cp + "\" ").list();
            return listeMAg;
        }
    }

    public static List<Article> communeListe(List<Article> grandListe, List<Article> petitListe) {
        List<Article> listeArticle = new ArrayList<Article>();
        boolean existe = false;
        for (Article gl : grandListe) {
            existe = false;
            for (Article pl : petitListe) {
                if (gl.equals(pl)) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                listeArticle.add(gl);
            }
        }
        return listeArticle;
    }

    // Fonction pour récupérer le Magasin par defaut d'un client à partir de son idCli.
    public static Magasin getMagByIdCli(long idClient) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idClient);
            long idmag = c.getMagasin().getIdMag();
            Magasin mag = session.get(Magasin.class, idmag);

            return mag;
        }
    }

    // Fonction pour récupérer le Magasin par defaut d'un client à partir de son idCli.
    public static Magasin getMagByIdMag(long idMagasin) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Magasin mag = session.get(Magasin.class, idMagasin);
            return mag;
        }
    }

    public static List<Postit> loadPostIt(long idlisteCourses) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            List<Postit> listePostit = new ArrayList<Postit>();
            for (Postit p : session.get(ListeCourses.class, idlisteCourses).getPostit()) {
                listePostit.add(p);
            }
            return listePostit;
        }
    }

    public static void ajouterPostIt(long idListe, String nom) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            ListeCourses lc = session.get(ListeCourses.class, idListe);
            Postit pi = new Postit(nom, lc);
            session.save(pi);
            t.commit();
        }
    }

    public static List<Article> listeArticlePrefMarque(long idClient) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idClient);
            List<Article> listeR = new ArrayList<>();
            Marque m;
            for (Preferences p : c.getPreferences()) {
                long id = p.getIdMar();
                long f = 0;
                if (id != f) {
                    m = session.get(Marque.class, p.getIdMar());
                    for (Article a : m.getArticles()) {
                        listeR.add(a);
                    }
                }
            }
            return listeR;
        }
    }

    public static List<Article> listeArticlePrefCat(long idClient) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idClient);
            List<Article> listeR = new ArrayList<>();
            Categorie ca;
            for (Preferences p : c.getPreferences()) {
                long id = p.getIdCat();
                long f = 0;
                if (id != f) {
                    ca = session.get(Categorie.class, p.getIdCat());
                    for (Article a : ca.getArticles()) {
                        listeR.add(a);
                    }
                }
            }
            return listeR;
        }
    }

    public static List<Article> listeArticlePrefLabel(long idClient) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idClient);
            List<Article> listeR = new ArrayList<>();
            Label lb;
            for (Preferences p : c.getPreferences()) {
                long id = p.getIdLab();
                long f = 0;
                if (id != f) {
                    lb = session.get(Label.class, id);
                    for (Article a : lb.getArticles()) {
                        listeR.add(a);
                    }
                }
            }
            return listeR;
        }
    }

    public static List<Article> listeArticlePrefLNutri(long idClient) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idClient);
            List<Article> listeR = new ArrayList<>();
            for (Preferences p : c.getPreferences()) {
                if (p.getTypePre().equals(TypePreference.Nutriscore)) {
                    List<Article> listeTous = session.createQuery("from Article ").list();
                    for (Article a : listeTous) {
                        if (a.getNutriscoreArt() != null) {

                            if (a.getNutriscoreArt().toString().equals(p.getNutriscore())) {
                                listeR.add(a);
                            }
                        }
                    }
                }
            }
            return listeR;

        }
    }

    public static Article listeArticleHauteNutri(List<Article> listeRechercher) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            ArrayList<NutriscoreArticle> ordreScore = new ArrayList<>();
            ordreScore.add(NutriscoreArticle.A);
            ordreScore.add(NutriscoreArticle.B);
            ordreScore.add(NutriscoreArticle.C);
            ordreScore.add(NutriscoreArticle.D);
            ordreScore.add(NutriscoreArticle.E);
            for (int i = 0; i < 4; i++) {
                for (Article a : listeRechercher) {
                    if (a.getNutriscoreArt() != null) {
                        if (a.getNutriscoreArt().equals(ordreScore.get(i))) {
                            return a;
                        }
                    }

                }
            }
            return null;
        }
    }

    public static List<Article> produitPostIt(List<Article> listeRechercher, long idClient) {
        if (listeRechercher.size() <= 3) {
            return listeRechercher;
        }
        List<Article> listeR = new ArrayList<>();
        //Produit promotionnels 
        List<Article> listepromo = listePromo();
        //Produit préféré 
        List<Article> listeArticle = listePref(idClient);
        //Produit préféré et promotionnels
        List<Article> listeArticlePromo = communeListe(listeArticle, listepromo);
        //Produit de la marque  préférée 
        List<Article> listeMar = listeArticlePrefMarque(idClient);
        //Produit promotionnels de la marque  préférée
        List<Article> listeMarPromo = communeListe(listeMar, listepromo);
        //Produit du categorie  préférée 
        List<Article> listeCat = listeArticlePrefCat(idClient);
        //Produit promotionnels du categorie  préférée 
        List<Article> listeCatPromo = communeListe(listeCat, listepromo);
        //Produit du label préférée 
        List<Article> listeLabel = listeArticlePrefLabel(idClient);
        //Produits promotionnels du label préférée 
        List<Article> listeLabelPromo = communeListe(listepromo, listeLabel);
        //Produit de le nutriscore  préférée 
        List<Article> listeNutri = listeArticlePrefLNutri(idClient);
        //Produit promotionnels de le nutriscore  préférée 
        List<Article> listeNutriPromo = communeListe(listeNutri, listepromo);

        //exmainer s'il existe les articles préférées
        for (Article a : listeRechercher) {
            if (listeArticlePromo.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                listeR.add(a);
            }
        }

        for (Article a : listeRechercher) {
            if (listeArticle.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                listeR.add(a);
            }
        }

        //exmainer s'il existe une articles des maruqes préférées
        boolean marque = false;
        for (Article a : listeRechercher) {
            if (listeMarPromo.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                listeR.add(a);
                marque = true;
                break;
            }
        }
        if (!marque) {
            for (Article a : listeRechercher) {
                if (listeMar.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                    listeR.add(a);
                    marque = true;
                    break;
                }
            }
        }

        //exmainer s'il existe une articles de label  préférées
        boolean label = false;
        for (Article a : listeRechercher) {
            if (listeLabelPromo.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                listeR.add(a);
                label = true;
                break;
            }
        }
        if (!label) {
            for (Article a : listeRechercher) {
                if (listeLabel.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                    listeR.add(a);
                    label = true;
                    break;
                }
            }
        }

        //exmainer s'il existe une articles de nutriscore  préférées
        /*boolean nutriscore = false;
        for (Article a : listeRechercher) {
            if (listeNutriPromo.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                listeR.add(a);
                nutriscore = true;
                break;
            }
        }
        if (!nutriscore) {
            for (Article a : listeRechercher) {
                if (listeNutri.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                    listeR.add(a);
                    nutriscore = true;
                    break;
                }
            }
        }*/
        if (listeR.size() < 3 & listeArticleHauteNutri(listeRechercher) != null & !listeR.contains(listeArticleHauteNutri(listeRechercher))) {
            listeR.add(listeArticleHauteNutri(listeRechercher));
        }

        //exmainer s'il existe une articles des catégorie préférées
        boolean categorie = false;
        for (Article a : listeRechercher) {
            if (listeCatPromo.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                listeR.add(a);
                categorie = true;
                break;
            }
        }
        if (!categorie) {
            for (Article a : listeRechercher) {
                if (listeCat.contains(a) & listeR.size() < 3 & !listeR.contains(a)) {
                    listeR.add(a);
                    categorie = true;
                    break;
                }
            }
        }

        if (listeR.size() < 3) {
            for (Article a : listeRechercher) {
                if (!listeR.contains(a)) {
                    listeR.add(a);
                    if (listeR.size() == 3) {
                        break;
                    }
                }
            }
        }
        return listeR;
    }

    public static List<Article> postitArticleRechercher(String search) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            String var = search;
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            // Récupération des la liste de recherche.          
            String hqlArt = "select a from Article a where a.libelleArt like :rollNumber";
            Query queryArt = session.createQuery(hqlArt);
            queryArt.setParameter("rollNumber", "%" + var + "%");
            List<Article> resultArt = queryArt.list();
            // Récupération des la liste de recherche.          
            String hqlCat = "select c from Categorie c where c.libelleCat like :rollNumber";
            Query queryCat = session.createQuery(hqlCat);
            queryCat.setParameter("rollNumber", "%" + var + "%");
            List<Categorie> resultCat = queryCat.list();
            for (Categorie c : resultCat) {
                for (Article a : c.getArticles()) {
                    if (!resultArt.contains(a)) {
                        resultArt.add(a);
                    }
                }
            }
            // Envoi du résultat de la requête.
            return resultArt;
        }
    }

    // Fonction pour mettre à jour les points fedelité d'un client à partir de son idCli.
    public static void updatePoints(long idCli, int pointGagne) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idCli);
            int oldPoint = c.getPointFedelitéCli();
            int newPoint = oldPoint + pointGagne;
            System.out.println(newPoint);
            c.setPointFedelitéCli(newPoint);
            t.commit();
        }
    }

    // Fonction pour mettre à jour l'état d'un panier EnCour d'un client à partir de son idCli.
    public static void updateEtatPanier(long idCli) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idCli);
            String hql = "select p.idPan "
                    + "from Panier p "
                    + "where p.client.idCli = :id "
                    + "and p.etatPan = 'EnCours' ";
            Query query = session.createQuery(hql);
            query.setParameter("id", idCli);
            List<Long> list = query.list();
            long idPanierEnCour = list.get(0);
            System.out.println(idPanierEnCour);
            Panier p = session.get(Panier.class, idPanierEnCour);
            p.setEtatPan(EtatPanier.Finalisée);
            t.commit();
        }
    }
    // recuperer la liste des creneaus selon idMag et la date choisi 

    public static List<Creneau> getCreneau(long idMag, String strDate) throws ParseException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();

            Date today = new Date();
            // le format de jour
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strToday = dateFormat.format(today);
            Date JourNow = dateFormat.parse(strToday);
            Date JourChoisi = dateFormat.parse(strDate);

            // le format d'heure
            SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");
            String strHeure = heureFormat.format(today);
            Date heureNow = heureFormat.parse(strHeure);

            // transformer le string a une date
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
            List<Creneau> list;

            // HQL
            String hql = "select c "
                    + "from Proposer p, Creneau c "
                    + "where p.creneau.idCre = c.idCre "
                    + "and p.date = :date "
                    + "and p.magasin.idMag = :idMag "
                    + "and p.nbPlaceDispoCre>0 ";
            Query query = session.createQuery(hql);
            query.setParameter("idMag", idMag);
            query.setParameter("date", date);
            // le resultat de HQL
            list = query.list();
            List<Creneau> listCre = new ArrayList<>();

            if (JourChoisi.compareTo(JourNow) == 0) {
                for (Creneau c : list) {
                    Date heureDebut = heureFormat.parse(c.getHeure().substring(0, 5));
                    if (heureNow.compareTo(heureDebut) < 0) {
                        listCre.add(c);
                    }
                }
            } else {
                for (Creneau c : list) {
                    listCre.add(c);
                }
            }
            return listCre;
        }
    }

    public static Creneau getCreneauByIdCre(long idCre) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Creneau cre = session.get(Creneau.class, idCre);
            return cre;
        }
    }

    public static void supprimerPostit(long id) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Postit p = session.get(Postit.class, id);
            session.delete(p);
            t.commit();
        }
    }

    public static void updateNbPlaceDispoCre(long idCre, long idMag, Date date) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            String hql = "select p.nbPlaceDispoCre "
                    + "from Proposer p "
                    + "where p.date = :date "
                    + "and p.creneau.idCre = :idCre "
                    + "and p.magasin.idMag = :idMag";
            Query query = session.createQuery(hql);
            query.setParameter("date", date);
            query.setParameter("idMag", idMag);
            query.setParameter("idCre", idCre);
            List<Integer> list = query.list();
            Integer nb = list.get(0);
            Integer newNb = nb - 1;
            ProposerID idProposer = new ProposerID(idMag, idCre, date);
            session.get(Proposer.class, idProposer).setNbPlaceDispoCre(newNb);
            t.commit();
        }
    }

    // Fonction pour récupérer idCre à partir d'une heure.
    public static long getIdCreByHeure(String heure) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            String hql = "select c.idCre "
                    + "from Creneau c "
                    + "where c.heure = :h";
            Query query = session.createQuery(hql);
            query.setParameter("h", heure);
            List<Long> list = query.list();
            Long id = list.get(0);
            System.out.println(id);
            return id;
        }
    }

    // Fonction pour ajouter un nouveau panier pour un client.
    public static void ajouterPanier(long idCli) {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c = session.get(Client.class, idCli);
            Panier p = new Panier(EtatPanier.EnCours, c);
            session.save(p);
            t.commit(); // Commit et flush automatique de la session.
        }
    }
}
