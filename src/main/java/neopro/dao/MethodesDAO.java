package neopro.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import neopro.metier.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
    
    public static double calculerPrixPromo(Long idArt){
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Article a = session.get(Article.class,idArt);
            Float prix = a.getPrixArt();
            
            String hql = "select p.pourcentagePro "
                    + "from Promotion p, AvoirPromo ap "
                    + "where ap.article.idArt = :id "
                    + "and p.idPro = ap.promotion.idPro "
                    + "and ap.dateDebut < now() "
                    + "and ap.datefin > now()";
            Query query = session.createQuery(hql);
            query.setParameter("id",a.getIdArt());
            List<Integer> list = query.list();
            Integer pourcentage = list.get(0);
            Float ecof = prix * (pourcentage.floatValue()/100);
            BigDecimal eco1 = new BigDecimal(ecof); 
            double eco = eco1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
            return eco;
        }
    }
    
        public static Integer getPromoPourcentage(Long idArt){
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Article a = session.get(Article.class,idArt);
            Float prix = a.getPrixArt();
            
            String hql = "select p.pourcentagePro "
                    + "from Promotion p, AvoirPromo ap "
                    + "where ap.article.idArt = :id "
                    + "and p.idPro = ap.promotion.idPro "
                    + "and ap.dateDebut < now() "
                    + "and ap.datefin > now()";
            Query query = session.createQuery(hql);
            query.setParameter("id",a.getIdArt());
            List<Integer> list = query.list();
            Integer nbPourcentage = list.get(0);
            return nbPourcentage;
        }
    }
    
    public static List<Article> listePref() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession())
            {
                Transaction t = session.beginTransaction();
                Set<Preferences> liste1 = session.get(Client.class, 1l).getPreferences();
                List<Article> liste2= new ArrayList<Article>();
                for(Preferences r:liste1){
                    if(r.getIdArt()>0){
                        Article a = session.get(Article.class, r.getIdArt());
                        liste2.add(a);
                    }                     
                }
                return liste2;
            }        
    
    }
     //Fonction qui recupère la liste des articles d'un panier pour un client donné
    public static ArrayList<Article> listeArtcilesPanierClient(long idcli)
  {
      try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
          /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Client c=session.get(Client.class, idcli);   
            Set<Panier> l_paniers = c.getPaniers() ;
            ArrayList<Article> lc=new ArrayList<>();
            for (Panier panier: l_paniers){
                String etat = panier.getEtatPan().toString();
                if(etat =="EnCours")
                {   
                    long idp = panier.getIdPan();
                    Panier p =session.get(Panier.class, idp);
                    Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
                    for (Map.Entry mapentry : listeA.entrySet()) {
                             
                             lc.add((Article) mapentry.getKey());
                }
                    
                    System.out.println(idp);
                    System.out.println(panier.toString());
                }
            }
            return lc; 
  }}
    
    //Fonction pour recuperer le montant d'un panier
    public static double montantPanier(long idcli)
     {
      try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
          /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            
            Client c=session.get(Client.class, idcli);   
            Set<Panier> l_paniers = c.getPaniers() ;
            float montant = 0 ;
            ArrayList<Article> lc=new ArrayList<>();
            for (Panier panier: l_paniers){
                String etat = panier.getEtatPan().toString();
                if(etat =="EnCours")
                {   
                    long idp = panier.getIdPan();
                    Panier p =session.get(Panier.class, idp);
                    Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
                    for (Map.Entry mapentry : listeA.entrySet()) {   
                             lc.add((Article) mapentry.getKey());
                             Article a=(Article) mapentry.getKey();
                             AvoirQuantitePanier aqp=(AvoirQuantitePanier ) mapentry.getValue();
                             montant = montant +a.getPrixArt()*aqp.getQuantite();
                }
                    
                }
            }
             BigDecimal montantDecim = new BigDecimal(montant); 
             double montantfinal = montantDecim.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
             return montantfinal;
            }
      
    }
    
    // Fonction pour recuperer la quantite d'un article dans un panier 
    public static int QuantiteArticlePanier(long idp,  long idArt)
     {
      try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
          /*----- Ouverture d'une transaction -----*/
            Transaction t = session.beginTransaction();
            Panier p =session.get(Panier.class, idp);
            Article a =session.get(Article.class, idArt);
            Map<Article, AvoirQuantitePanier> listeA = p.getPaniers();
            int quantite = 0 ;
            for (Map.Entry mapentry : listeA.entrySet()) 
            {
                if(mapentry.getKey().equals(a))
                {   AvoirQuantitePanier aqp=(AvoirQuantitePanier ) mapentry.getValue();
                    quantite=aqp.getQuantite();
                    
                }
            }
                 System.out.println(quantite);
                  return quantite; 
                  
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
                 BigDecimal montantDecim = new BigDecimal(montant); 
                 double eco = montantDecim.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
                 return eco;  
                  
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
    
    
    
}
