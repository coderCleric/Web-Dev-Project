package Java_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class connectDB
 */
@WebServlet("/ListProducts")
public class ListProducts extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
         response.setContentType("text/html");
            PrintWriter out2=response.getWriter();
            Connection mycon=null;
            Statement sql_stmt=null;
            ResultSet records=null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","cba321");
                sql_stmt = mycon.createStatement();  
                records = sql_stmt.executeQuery("select * from project2.cat_food");
                
         
                out2.println("<html><head><title>Cat Food Listing</title></head><body>");
                out2.println("<table border='1' cellpadding='6'>");
                out2.println("<tr>");
                   
                out2.println("<th>Cat Type</th>");
                out2.println("<th>Food Name</th>");
                out2.println("<th>Food Description</th>");
                out2.println("<th>Price</th>");
                out2.println("<th>Availability</th>");

                out2.println("</tr>");
                
                while (records.next()) {                       
                        out2.println("<tr>");
                        out2.println("<td>" + records.getString("cat_type") + "</td>");
                        out2.println("<td>" + records.getString("name")+ "</td>");
                        out2.println("<td>" + records.getString("description") + "</td>");
                        out2.println("<td>" + records.getString("customer_price") + "</td>");
                        out2.println("<td>" + records.getString("number_stocked") + "</td>");
                        out2.println("</tr>");                    
                }
                out2.println("</table><br />");

            }
            
            catch(Exception e) {e.printStackTrace();} 
        }
}