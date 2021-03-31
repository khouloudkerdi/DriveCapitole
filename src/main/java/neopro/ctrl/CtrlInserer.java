package neopro.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;

public class CtrlInserer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("idClient") == null) {
            String idA = request.getParameter("idArt");
            // Conversion using parseLong(String) method
            long idArt = Long.parseLong(idA);
            // Connexion
            // Ajouter 'idArt' au session
            request.getSession().setAttribute("idArt", idArt);
            // Chainage ver la connexion
            request.getRequestDispatcher("Connexion").forward(request, response);
        } else if (request.getParameter("idListeCourses") != null) {
            String l = request.getParameter("idListeCourses");
            String[] ls = l.split(",");
            Long idArticle = Long.parseLong(ls[0]);
            Long idListeC = Long.parseLong(ls[1]);
            MethodesDAO.ajouterArticleListeCourse(idArticle, idListeC);
            response.sendRedirect("Accueil");
        } else {
            String idA = request.getParameter("idArt");
            // Conversion using parseLong(String) method
            long idArt = Long.parseLong(idA);

            // Ajouter au panier    
            long idClient = (long) request.getSession().getAttribute("idClient");
            MethodesDAO.insererArticlePanier(idArt, MethodesDAO.loadPanierClient(idClient));
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
