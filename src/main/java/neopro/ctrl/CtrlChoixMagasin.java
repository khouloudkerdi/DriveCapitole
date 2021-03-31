/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.ctrl;

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
import neopro.metier.Magasin;

/**
 *
 * @author khoul
 */
public class CtrlChoixMagasin extends HttpServlet {

   
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
                           
                        String code = request.getParameter("codePostal");
                         System.out.println("--------------------------------"+code+"--------------------");
			List<Magasin> lMagasins = MethodesDAO.getListeMagasin("31000");
                                 
				for (Magasin mag : lMagasins){
                                    out.println("<Magasin>");
                                    
                                    out.println("<idMagasin>"+mag.getIdMag()+"</idMagasin>");
                                    out.println("<nomMagasin>"+mag.getNomMag()+"</nomMagasin>");
                                    out.println("<adrMagasin>"+mag.getAdresseMag()+"</adrMagasin>");
                                    out.println("</Magasin>");
                                }
			out.println("</donnees>");
			}}
             
 
    }

  
 


