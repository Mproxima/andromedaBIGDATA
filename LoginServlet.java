

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet 
{


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out=response.getWriter();
        
        try
        {
        String uid=request.getParameter("tbid");
        String upass=request.getParameter("tbpass");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
        PreparedStatement ps=conn.prepareStatement("Select * From Users Where uid=? and upass=?");
        ps.setString(1, uid);
        ps.setString(2, upass);
        
        ResultSet rs=ps.executeQuery();
        HttpSession session=request.getSession();                               
        
        if(rs.next())
        {
            String utype=rs.getString("utype");
            String exptech=rs.getString("exptech");              
              session.setAttribute("exptype",exptech );                          
              session.setAttribute("utype",utype );  
              String userid=rs.getString("uid");
              session.setAttribute("uid",userid );
              
            if(utype.equals("User"))
            {
            response.sendRedirect("UserHome.html");
            }
            if(utype.equals("Expert") )
            {         
              
  
            response.sendRedirect("ExpertHome.html");
            }
            if(utype.equals("Administrator"))
            {
            response.sendRedirect("AdminHome.html");
            }
        }
        else
        {
        out.println("Invalid Id/Password");
        }        
        
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
