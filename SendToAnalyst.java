
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.sqoop.Sqoop;
import org.apache.sqoop.SqoopOptions;
import org.apache.sqoop.tool.ImportAllTablesTool;
import org.apache.sqoop.tool.ImportTool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class SendToAnalyst extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendToAnalyst</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Start Process");
            //code to send data in hdfs using sqoop
            try {
                // org.apache.sqoop.SqoopOptions options=new  org.apache.sqoop.SqoopOptions();
                //  out.println("Start Process in1");
                //   options.setConnectString("jdbc:mysql://localhost:3306/TechDB --username root --password root --table Postqueries");
                out.println("Start Process In");
importToHive();
                out.println("Start Process End");

              //  Configuration config = new Configuration();
              //  config.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
              //  config.addResource(new Path("/usr/local/hadoop/etc/hadoop/hdfs-site.xml"));

             //   String connectionstring = "jdbc:mysql://localhost:3306/TechDB --username root --password root --table Postqueries";

             //       String[] cmd ={"import", "--connect jdbc:mysql://localhost:3306/TechDB--username root
   //  "--password", password,"--hadoop-home", "/usr/local/hadoop","--table",<tableName>,   "--hive-import","--create-hive-table", "--hive-table",<tableName>,"-target-dir",
    //       "hdfs://localhost:54310/user/hive/warehouse","-m", "1","--delete-target-dir"};
                
                //String cmd[] = {"import", "--connect jdbc:mysql://localhost/universal", "--username", "root", "--password", "root", "--table", "authors", "--m 1", "--driver", "com.mysql.jdbc.Driver"};
     //           Sqoop.runTool(cmd, config);

//options.setConnectionString("jdbc:vertica://serverName:5433/DBname");
//options.setTableName("TechDB.Postqueries");// vertica schema.table
//options.setWhereClause("");
//options.setUsername("root");
//options.setPassword("root");
//options.setUsername("root");
//options.setNumMappers(1);
//options.setSqlQuery("select * from Postqueries");// vertica sql query
//options.setTargetDir("/taj/");// On Hive DataBase
//int ret = new ImportTool().run(options);
//ImportTool ob=new ImportAllTablesTool();
//org.apache.sqoop.SqoopOptions op=new org.apache.sqoop.SqoopOptions();
                //         org.apache.sqoop.tool.ImportTool obj1=new org.apache.sqoop.tool.ImportTool();
                //obj1.run((com.cloudera.sqoop.SqoopOptions) op);
                out.println("Done");
            } catch (Exception ex) {
                out.println(ex.getMessage());
            }

            /*       org.apache.sqoop.SqoopOptions options = new org.apache.sqoop.SqoopOptions();
        options.setConnectString("jdbc:oracle:thin:@192.168.0.129:1521/xe");
        options.setTableName("test3");
        options.setColumns(new String[]{"firstname", "lastname"});
        //options.setWhereClause("id>10");     // this where clause works when importing whole table, ie when setTableName() is used
        options.setUsername("***");
        options.setPassword("*****");
        options.setSplitByCol("firstname");
        //options.setDirectMode(true);    // Make sure the direct mode is off when importing data to HBase
        options.setNumMappers(8);         // Default value is 4
        options.setSqlQuery("SELECT * FROM test3");
          //  int ret = new ImportTool().run(options);
            org.apache.sqoop.tool.ImportTool obj1=new org.apache.sqoop.tool.ImportTool();
           int k= obj1.run(options);*/
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {

            out.println(ex.getMessage());
        }

    }
    
    public static void importToHive() throws Exception{

    Configuration config = new Configuration(); 
    config.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
    config.addResource(new Path("/usr/local/hadoop/etc/hadoop/hdfs-site.xml"));
   /* String[] cmd ={"import", "--connect","jdbc:mysql://localhost/universal","--username", "root",
     "--password", "root","--hadoop-home", "/usr/local/hadoop","--table","authors"};//,   "--hive-import","--create-hive-table", "--hive-table","authors","-target-dir",
       //    "hdfs://localhost:54310/user/hive/warehouse","-m", "1","--delete-target-dir"};
*/
   String cmd[] = {"import", "--connect jdbc:mysql://localhost/TechDB", "--username", "root", "--password", "root", "--table", "Postqueries", "--m 1", "--driver", "com.mysql.jdbc.Driver"};
    Sqoop.runTool(cmd,config);
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
