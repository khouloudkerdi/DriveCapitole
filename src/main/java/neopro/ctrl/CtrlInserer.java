/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;

/**
 *
 * @author 13520
 */
public class CtrlInserer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        
        // Ajouter au panier
        long idClient=1;
        String idA = request.getParameter("idArt");
        if (idA!=null){
             //Conversion using parseLong(String) method
	    long idArt = Long.parseLong(idA);
            MethodesDAO.insererArticlePanier(idArt,MethodesDAO.loadPanierClient(idClient));
         }
        
         //// Ajouter à la liste de courses
        String l=(request.getParameter("btnListeCourses"));
        if (l!=null){
        String[] ls = l.split(",");
        Long idArticle= Long.parseLong(ls[1]);
        Long idListeC= Long.parseLong(ls[0]);
        MethodesDAO.ajouterArticleListeCourse(idArticle, idListeC);
        }
             response.sendRedirect("Accueil");
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
