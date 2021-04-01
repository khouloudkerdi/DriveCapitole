
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

public class CtrlConnexion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("motDePasse");
        long noValide = 0;//Si le compte ou le mot de passe est incorrect, renvoyer 0 
        if (MethodesDAO.verifierCompte(mail, mdp) == noValide) {
            //retour à la page connexion si ce n'est pas correst
            request.setAttribute("msg_connexion", "Votre adresse e-mail ou mot de passe est incorrect ! ");
            request.setAttribute("mail", mail);
            request.getRequestDispatcher("Accueil").forward(request, response);
        } else {
            //retour à la page accueil si c'est correst
            long idClient = MethodesDAO.verifierCompte(mail, mdp);
            request.getSession().setAttribute("idClient", idClient);
            if (request.getSession().getAttribute("idArt") != null) {
                // Get idArt 
                long idArt = (long) request.getSession().getAttribute("idArt");
                // Ajouter l'article au panier           
                MethodesDAO.insererArticlePanier(idArt, MethodesDAO.loadPanierClient(idClient));
                request.getSession().removeAttribute("idArt");
            }
            MethodesDAO.getIdPanierByIdCli(idClient);
            response.sendRedirect("Accueil");
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
