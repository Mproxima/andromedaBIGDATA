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

public class SaveLike extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
 
            PrintWriter out = response.getWriter() ;
        try
        {
        
            int ansid=Integer.parseInt(request.getParameter("tb"));
            String btnid=request.getParameter("btn");
            out.println("clicked button="+btnid+"and ansid="+ansid);
            if(btnid.equals("Like"))
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps=conn.prepareStatement("Select likecounter From LikesData Where ansid=?");
                ps.setInt(1,ansid );
                ResultSet rs=ps.executeQuery();
                if(rs.next())
                {
                int oldlikecounter=rs.getInt("likecounter");
                int newlikecounter=oldlikecounter+1;
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps1=conn1.prepareStatement("Update LikesData set likecounter=? Where ansid=?");
                ps1.setInt(1,newlikecounter );
                ps1.setInt(2,ansid );
                ps1.executeUpdate();
                conn1.close();
                }else
                {
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps1=conn1.prepareStatement("Insert Into LikesData(ansid,likecounter,dislikecounter)Values(?,?,?)");
                ps1.setInt(1,ansid );
                ps1.setInt(2,1 );
                ps1.setInt(3,0 );
                ps1.executeUpdate();
                conn1.close();
                }
            }
            
            if(btnid.equals("Dislike"))
            {
                out.println("dislike button clicked");
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps=conn.prepareStatement("Select dislikecounter From LikesData Where ansid=?");
                ps.setInt(1,ansid );
                ResultSet rs=ps.executeQuery();
                if(rs.next())
                {
                int olddislikecounter=rs.getInt("dislikecounter");
                out.println("dislike button clicked old value="+olddislikecounter);
                int newdislikecounter=olddislikecounter+1;
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps1=conn1.prepareStatement("Update LikesData set dislikecounter=? Where ansid=?");
                ps1.setInt(1,newdislikecounter );
                ps1.setInt(2,ansid );
                ps1.executeUpdate();
                out.println("dislike updated");
                conn1.close();
                }else
                {
                out.println("dislike button clicked new entry");
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
                PreparedStatement ps1=conn1.prepareStatement("Insert Into LikesData(ansid,likecounter,dislikecounter)Values(?,?,?)");
                ps1.setInt(1,ansid );
                ps1.setInt(2,0 );
                ps1.setInt(3,1 );
                ps1.executeUpdate();
                conn1.close();
                
                }
                
            }
            response.sendRedirect("ResultQuery");
        }catch(Exception ex)
        {
        out.println(ex.getMessage());
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
