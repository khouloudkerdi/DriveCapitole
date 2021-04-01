/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;
import neopro.metier.Creneau;
import neopro.metier.Magasin;

/**
 *
 * @author khoul
 */
public class CtrlChoixCreneau extends HttpServlet {

   
    
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
        /*----- Type de la réponse -----*/
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter())
			{
			out.println("<?xml version=\"1.0\"?>");
                        
			out.println("<donnees>");
                           
                        String idM = request.getParameter("idMag");
                        long idMag= Long.parseLong(idM);
                        String date = request.getParameter("date");
                      
			List<Creneau> lcreneaux = MethodesDAO.getCreneau(idMag, date);
                                 
				for (Creneau cre : lcreneaux){
                                    out.println("<Creneau>");                                    
                                    out.println("<idCre>"+cre.getIdCre()+"</idCre>");
                                    out.println("<heure>"+cre.getHeure()+"</heure>");
                          
                                    out.println("</Creneau>");
                                }
			out.println("</donnees>");
			} catch (ParseException ex) {
            Logger.getLogger(CtrlChoixCreneau.class.getName()).log(Level.SEVERE, null, ex);
        }}
             
     
    

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
