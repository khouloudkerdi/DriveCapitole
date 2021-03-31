package neopro.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");
            /**
             * Ajout des classes
             */
            
            configuration.addAnnotatedClass(neopro.metier.Rayon.class);
            configuration.addAnnotatedClass(neopro.metier.Categorie.class);
            configuration.addAnnotatedClass(neopro.metier.Marque.class);
            configuration.addAnnotatedClass(neopro.metier.Magasin.class);
            configuration.addAnnotatedClass(neopro.metier.Label.class);
            configuration.addAnnotatedClass(neopro.metier.Article.class);
            configuration.addAnnotatedClass(neopro.metier.Promotion.class);
            configuration.addAnnotatedClass(neopro.metier.AvoirPromo.class);
            configuration.addAnnotatedClass(neopro.metier.AvoirPromoID.class);
            configuration.addAnnotatedClass(neopro.metier.Client.class);
            configuration.addAnnotatedClass(neopro.metier.Preferences.class);
            configuration.addAnnotatedClass(neopro.metier.Panier.class);
            configuration.addAnnotatedClass(neopro.metier.AvoirQuantitePanier.class);
            configuration.addAnnotatedClass(neopro.metier.AvoirQuantitePanierID.class);
            configuration.addAnnotatedClass(neopro.metier.ListeCourses.class);
            configuration.addAnnotatedClass(neopro.metier.Postit.class);
            configuration.addAnnotatedClass(neopro.metier.Creneau.class);
            configuration.addAnnotatedClass(neopro.metier.ProposerID.class);
            configuration.addAnnotatedClass(neopro.metier.Proposer.class);
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            /*----- Exception -----*/
            System.err.println("Initial SessionFactory creation failed.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

}
/*----- Fin de la classe HibernateUtil -----*/