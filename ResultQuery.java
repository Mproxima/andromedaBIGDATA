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
import java.sql.*;
import javax.servlet.http.HttpSession;

public class ResultQuery extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResultQuery</title>");            
            out.println("</head>");
            out.println("<body>");
            
            HttpSession session=request.getSession();                               
            String uid= session.getAttribute("uid").toString();
            if(uid!=null||uid!="")
            {
             try
            {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
            PreparedStatement ps=conn.prepareStatement("Select * From Postqueries");                       
            ResultSet rs=ps.executeQuery();
            out.println("<table border='1'>");
            
            
            while(rs.next())
            {
                out.println("<tr><th>Query</th><th>User</th></tr>");
                out.println("<tr>");
                out.println("<td>"+rs.getString("query"));                
                out.println("</td>");
                out.println("<td>"+rs.getString("uid"));                                
                out.println("</td>");                
                out.println("</tr>");
                int qid=rs.getInt("qid");
                out.println("<tr>");
                //code to get answers
                out.println("<td>");
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps1=conn1.prepareStatement("Select * From Postanswer Where qid=?");
                ps1.setInt(1,qid);
                ResultSet rs1=ps1.executeQuery();                
                out.println("<table border='1'>");
                
                while(rs1.next())
                {
                out.println("<tr><th>Answer</th><th>Expert</th></tr>");
                out.println("<form method='post' action='SaveLike'>");
                out.println("<tr>");
                

                //code to get like and dislike counter value
                int ansid=rs1.getInt("ansid");
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn11=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps11=conn11.prepareStatement("Select * From LikesData Where ansid=?");
                ps11.setInt(1,ansid);
                ResultSet rs11=ps11.executeQuery();                
                int likes=0;
                int dislikes=0;
                if(rs11.next())
                {
                likes=rs11.getInt("likecounter");
                dislikes=rs11.getInt("dislikecounter");
                }
                conn11.close();
                //code end to get like and dislike counter value
                
                
                out.println("<td>"+rs1.getString("qans"));                
                
                out.println("<input type='text' name='tb' value="+rs1.getInt("ansid")+">");
                out.println("</td>");
                out.println("<td>"+rs1.getString("uid"));                                
                out.println("</td>");
                
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type='submit' value='Like' name='btn'>"+likes);
                
                out.println("</input>");
                
                out.println("<input type='submit' value='Dislike' name='btn'>"+dislikes);
                out.println("</input>");
                out.println("</td>");                
                out.println("<td>");
                out.println("</td>");               
                
                out.println("</tr>");
                out.println("</form>");
                }
                
                out.println("</table>");
                
                
               out.println("</td>");
               out.println("<td>");
               out.println("</td>");
               out.println("</tr>");
            }
            out.println("</table>");
            }catch(Exception ex)
            {
            out.println(ex.getMessage());
            }
            }else
            {
            response.sendRedirect("Login");
            }
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
