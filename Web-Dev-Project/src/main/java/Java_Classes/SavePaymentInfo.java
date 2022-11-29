package Java_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SavePaymentInfo")
public class SavePaymentInfo extends HttpServlet  {
	public SavePaymentInfo() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Connection mycon = Java_Classes.ConnectionHandler.getConnection();
		
		if (request.getParameter("saveInfo") != null) {
			String command = "insert into customers (name, email, debit_card_number, address) values ('"+request.getParameter("name")+"', '"+request.getParameter("email")+"', '" +request.getParameter("debit_card_number") + "', '" +request.getParameter("address") + "')";
		
			try {
				Statement sql_stmt = mycon.createStatement();
				sql_stmt.executeUpdate(command);
				out.print("<h1>Saved payment info successfully.</h1>");
			} catch(Exception e) {
				out.print("<h1>Encountered a problem while saving payment info</h1>");
				out.print(e.getMessage());
			}
			
			String getNewestCustomer = "select * from customers order by id desc limit 0, 1";
			Integer newestCustomerId = null;
			ResultSet records = null;
	        try {
	            Statement sql_stmt=null;
	            sql_stmt = mycon.createStatement();  
				records = sql_stmt.executeQuery(getNewestCustomer);
				while (records.next()) {
					newestCustomerId = Integer.parseInt(records.getString("id"));
				}

	        } catch (Exception e) {
	        	out.print(e.getMessage());
	        }
	        session.setAttribute("customerId", newestCustomerId);
		}
		
		Integer customerId = (Integer) session.getAttribute("customerId");
		out.print(Java_Classes.Constants.PRE_CONTENT_TEMPLATE);
        out.println("<title>Your payment info</title>");
        
        if (customerId == null) {
        	out.println("<h1>You have no payment info saved with us</h1>");
        } else {
        	out.println("<h1>We already have some payment info saved for you:</h1>");
            out.println("<table border='1' cellpadding='6' width='100%'>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Debit card number</th>");
            out.println("<th>Address</th>");
            out.println("</tr>");
			ResultSet records = null;
	        try {
	            Statement sql_stmt=null;
	            sql_stmt = mycon.createStatement();  
				records = sql_stmt.executeQuery("select * from customers WHERE id=" + customerId);
				out.println("<tr>");
				while (records.next()) {
					out.println("<tr>");
					out.println("<td>" + records.getString("name") + "</td>");
	                out.println("<td>" + records.getString("email")+ "</td>");
	                out.println("<td>" + records.getString("debit_card_number") + "</td>");
	                out.println("<td>" + records.getString("address") + "</td>");
	                out.println("</tr>");
				}
	        } catch (SQLException e) {
				e.printStackTrace();
			}
	        
			out.println("</table>");
        }
        out.println("<h1>Set your payment info below:</h1>");
        out.println("<table border='1' width='100%' height='50%'>");
        out.println("<tr><td>");
        out.println("<form action='SavePaymentInfo' method='post'>");
        out.println("<input type='hidden' name='saveInfo' value='saveNewInfo'>");
        out.println("Enter your name: <input type='text' name='name' required/><br><br>");
        out.println("Enter your email address: <input type='text' name='email' required/><br><br>");
        out.println("Enter your debit card number: <input type='number' name='debit_card_number' required/><br><br>");
        out.println("Enter your physical address: <input type='text' name='address' required/><br><br>");
        out.println("<input type='submit' name='buy' value='Save payment info'/>");
        out.println("</form></tr></td></table>");
        
		out.print(Java_Classes.Constants.POST_CONTENT_TEMPLATE);		
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}