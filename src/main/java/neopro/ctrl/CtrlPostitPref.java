
package neopro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;


public class CtrlPostitPref extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        long idListe=Long.parseLong((String) request.getParameter("ListeCourse")); 
        if (request.getParameter("article")!=null){
            long idArticle=Long.parseLong((String) request.getParameter("article")); 
            String idPostit=(String) request.getParameter("idPostIt"); 
            String idPostit2="";
            idPostit2 +=idPostit.charAt(0);
            idPostit2 +=idPostit.charAt(1);
            idPostit2 +=idPostit.charAt(2);
            long idPostitL=Long.parseLong(idPostit2);
            request.getSession().setAttribute("idP", "111"+idPostit2+"111");
            
            MethodesDAO.ajouterArticleListeCourse(idArticle, idListe);
           MethodesDAO.supprimerPostit(idPostitL);
        }
        request.getRequestDispatcher("VisualiserListe?idListe=" + idListe).forward(request, response);
            
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
