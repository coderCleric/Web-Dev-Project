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
       
    public ListProducts() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        	out.print(Constants.PRE_CONTENT_TEMPLATE);

            Connection mycon = null;
            Statement sql_stmt = null;
            ResultSet records = null;
            try {	                       
                mycon = ConnectionHandler.getConnection();
                sql_stmt = mycon.createStatement();  
                records = sql_stmt.executeQuery("select * from cat_food");
         
                out.println("<html><head><title>Cat Food Listing</title></head><body>");
                out.println("<table border='1' cellpadding='6'>");
                out.println("<tr>");
                   
                out.println("<th>Cat Type</th>");
                out.println("<th>Food Name</th>");
                out.println("<th>Food Description</th>");
                out.println("<th>Price</th>");
                out.println("<th>Availability</th>");
                out.println("<th>&nbsp</th>");

                out.println("</tr>");
                
                while (records.next()) {                       
                        out.println("<tr>");
                        out.println("<td>" + records.getString("cat_type") + "</td>");
                        out.println("<td>" + records.getString("name")+ "</td>");
                        out.println("<td>" + records.getString("description") + "</td>");
                        out.println("<td>" + records.getString("customer_price") + "</td>");
                        out.println("<td>" + records.getString("number_stocked") + "</td>");
                        out.println("<form action='Cart' method='post'>");
                        out.println("<td><input type='submit' name='buy' value='Add to Cart'/></td>");
                        out.println("<input type='hidden' name='newProduct' value='"+records.getString("id")+"'/>");
                        
                        out.println("</form>"); 
                        out.println("</tr>");                    
                }
                out.println("</table><br />");

            }
            
            catch(Exception e) {e.printStackTrace();} 
            out.print(Constants.POST_CONTENT_TEMPLATE);
        }
}