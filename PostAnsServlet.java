/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class PostAnsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostAnsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
        
            int qid = Integer.parseInt( request.getParameter("tb"));
            //out.println("Value="+values);
        
            out.println("<form method='post' action='PostSuccessServlet'>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Query Id");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type='text' id='qid'  name='qid' value="+qid);
            out.println("</input>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>Query Answer");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type='text' id='qans' name='qans' ");
            out.println("</input>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type='submit' value='Post Answer' ");
            out.println("</input>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
