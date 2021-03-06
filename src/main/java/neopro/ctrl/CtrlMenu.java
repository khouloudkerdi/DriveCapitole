/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;
import neopro.metier.Article;

/**
 *
 * @author 13520
 */
public class CtrlMenu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String m = request.getParameter("method");
            
        // Traitement.
        switch (m)
            {
            case "Liste" :
                if (request.getSession().getAttribute("idClient")!=null){
                    request.getRequestDispatcher("ListeCourses").forward(request, response);  
                }
                    break; 
//          
//            case "Connexion" :
//                if (request.getSession().getAttribute("idClient")==null){
//                    // Chainage vers la page Connexion.jsp
//                    request.getRequestDispatcher("Connexion").forward(request, response);
//                   
//                }else{
//                   request.getRequestDispatcher("Accueil").forward(request, response);
//                }
//               break;
            
            case "Panier" :
                if (request.getSession().getAttribute("idClient")!=null){
                     request.getRequestDispatcher("Panier").forward(request, response);
                }
                break;        
                
            case "Accueil" :
                // Recupere la liste des articles par rayon.
                List<Article> listeArticles = MethodesDAO.listeArticle();
                // Chainnage vers la vue "afficher.jsp"  avec la liste de messages 
                request.getSession().setAttribute("liste_articles", listeArticles);
                // Chainage vers "Acceuil.jsp"
                request.getRequestDispatcher("Accueil").forward(request, response);
                break;                 
            }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
