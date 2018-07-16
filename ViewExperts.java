
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class ViewExperts extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewExperts</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1'>");
            out.println("<th>User Id</th><th>Technology</th><th>City</th>");
            try
            {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
            PreparedStatement ps=conn.prepareStatement("Select uid,exptech,city From Users Where utype=?");
            ps.setString(1, "Expert");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                 out.println("<tr>");
                 
                 out.println("<td>"); 
                 out.println(rs.getString("uid"));
                 out.println("</td>");             
                 
                
                 out.println("<td>"); 
                 out.println(rs.getString("exptech"));
                 out.println("</td>");                
                 
               
                 out.println("<td>"); 
                 out.println(rs.getString("city"));
                 out.println("</td>"); 
                 
                 out.println("</tr>");
            }
            }catch(Exception ex)
            {
            out.println(ex.getMessage());
            }
            out.println("</table>");
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
