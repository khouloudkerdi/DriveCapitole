package neopro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;

public class CtrlFinaliser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse((String)request.getSession().getAttribute("date"));
            long idCre = MethodesDAO.getIdCreByHeure((String)request.getSession().getAttribute("creneau"));
            long idCli = (long) request.getSession().getAttribute("idClient");
            long idMag = (long) request.getSession().getAttribute("idMag");
            int pointGagne = (int) request.getSession().getAttribute("pointGagne");
            
            MethodesDAO.updateNbPlaceDispoCre(idCre, idMag, date);
            MethodesDAO.updateEtatPanier(idCli);
            MethodesDAO.updatePoints(idCli, pointGagne);
            
            request.getSession().removeAttribute("idMag");
            request.getSession().removeAttribute("creneau");
            request.getSession().removeAttribute("date");
            request.getSession().removeAttribute("pointGagne");
            
            MethodesDAO.ajouterPanier(idCli);
            
            response.sendRedirect("PagePersonnelle");
        } catch (ParseException ex) {
            Logger.getLogger(CtrlFinaliser.class.getName()).log(Level.SEVERE, null, ex);
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
