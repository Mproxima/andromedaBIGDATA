
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class ViewQueryExpServlet extends HttpServlet 
{


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
            
        try (PrintWriter out = response.getWriter()) 
        {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewQueryExpServlet</title>");            
            out.println("</head>");
            out.println("<body>");
    //        out.println("<form method='post' action='PostAnsServlet'>");
           try
            {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/TechDB?zeroDateTimeBehavior=convertToNull","root","root");
            PreparedStatement ps=conn.prepareStatement("Select * From Postqueries Where qtype=?");
            HttpSession session=request.getSession();                               
            String exptype= session.getAttribute("exptype").toString();
            ps.setString(1,exptype );
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>QueryId</th>");
                out.println("<th>Query</th>");
                out.println("<th>Action</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<form method='post' action='PostAnsServlet'>");
                session.setAttribute("sesqid",rs.getString("qid"));
                out.println("<td>");                
                out.println("<input type='text' name='tb' value="+rs.getString("qid")+">");
                out.println("</td>");
                out.println("<td>"+rs.getString("query"));                
                out.println("</td>");
                out.println("<td><input type='submit' value='Post Answer' />");                
                out.println("</td>");
                out.println("</tr>");
                out.println("</form>");   
                out.println("</table>");
            }
            
            }catch(Exception ex)
            {
                out.println(ex.getMessage());
            }
                
            
            out.println("</body>");
          //  out.println("</form>");
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
