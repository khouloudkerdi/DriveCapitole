package neopro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neopro.dao.MethodesDAO;

public class CtrlAjouterProduitPanier extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {    
        /*----- Type de la réponse -----*/
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        /*----- Récupération des paramètres -----*/
        long ida = Long.parseLong(request.getParameter("ida"));        
        long idp = Long.parseLong(request.getParameter("idp"));

        /*----- Insertion de la BD -----*/
        MethodesDAO.insererArticlePanier(ida, idp);
            
//        request.getRequestDispatcher("VisualiserPanier").forward(request, response);
        response.sendRedirect("Panier");
        }     
    
    @Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }

        
    }
