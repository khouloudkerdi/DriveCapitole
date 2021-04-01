package neopro.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;
import neopro.metier.Article;

/**
 * Interroge la base de données et retourne la liste des citations sous format
 * XML.
 */
public class ServletPostit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*----- Type de la réponse -----*/
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /*----- Ecriture de la page XML -----*/
            out.println("<?xml version=\"1.0\"?>");
            out.println("<liste_article>");

            /*----- Récupération des paramètres -----*/
            String postit = request.getParameter("postit");
            //long idClient = Long.parseLong((String) request.getSession().getAttribute("idClient"));
            long idClient=1l;

            /*----- Lecture de liste de mots dans la BD -----*/
            List<Article> listeRecherche = MethodesDAO.postitArticleRechercher(postit);
            List<Article> ListeArticle = MethodesDAO.produitPostIt(listeRecherche, idClient);

            for (Article a : ListeArticle) {
                out.println("<Article>");
                out.println("<IdArt>" + a.getIdArt() + "</IdArt>");
                out.println("<LibelleArt>" + a.getLibelleArt() + "</LibelleArt>");
                out.println("<CondArt>" + a.getCondArt() + "</CondArt>"); 
                out.println("<FormatArt>" + a.getFormatArt()+ "</FormatArt>");
                out.println("<PrixKgArt>" + a.getPrixKgArt()+ "</PrixKgArt>");
                out.println("<TypePrix>" + a.getTypePrix() + "</TypePrix>");
                out.println("<UrlImageArt>" + a.getUrlImageArt()+ "</UrlImageArt>");
                out.println("</Article>");
            }

            out.println("</liste_article>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

} /*----- Fin de la servlet ServletCitation -----*/
