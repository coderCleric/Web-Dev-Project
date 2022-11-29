package Java_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class connectDB
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Checkout() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Map<Integer, Integer> customerCart = (HashMap<Integer, Integer>) session.getAttribute("customerCart");
		
		String order_contents = "";
		for (Integer productID : customerCart.keySet()) {
			ResultSet records = null;
	        try {
				Connection mycon = ConnectionHandler.getConnection();
	            Statement sql_stmt = null;
	            sql_stmt = mycon.createStatement();  
				records = sql_stmt.executeQuery("select * from project2.cat_food WHERE id=" + productID);
				while (records.next()) {
					order_contents += customerCart.get(productID).toString() + ":" + records.getString("name") + " ";
				}
	        } catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		out.print(Java_Classes.Constants.PRE_CONTENT_TEMPLATE);
		out.println("<title>Check out</title>");
		
		if (request.getParameter("checkoutWithSavedPaymentInfo") == null) {
			String command = "insert into orders (name, email, debit_card_number, state, address, order_contents) "
					+ "values ('"+request.getParameter("name")+"', '"+request.getParameter("email")+"', '" +request.getParameter("debit_card_number") + "', 'Ordered', '" + request.getParameter("address") + "', '" + order_contents + "')";
			
			try {
				Connection mycon = ConnectionHandler.getConnection();
				Statement sql_stmt = mycon.createStatement();
				sql_stmt.executeUpdate(command);
				session.setAttribute("customerCart", null);
				out.print("<h1>Checked out successfully, your cart is now empty.</h1>");
			} catch(Exception e) {
				out.print("<h1>Encountered a problem while checking out:</h1>");
				out.print(e.getMessage());
			}
		} else {
			out.print("<h1>Checking out with saved payment info...</h1>");
            
            String name = null;
            String email = null;
            String debit_card_number = null;
            String address = null;
            
			ResultSet records = null;
	        try {
				Connection mycon = ConnectionHandler.getConnection();
	            Statement sql_stmt=null;
	            sql_stmt = mycon.createStatement();  
				records = sql_stmt.executeQuery("select * from customers WHERE id=" + session.getAttribute("customerId"));
				while (records.next()) {
					name = records.getString("name");
					email = records.getString("email");
					debit_card_number = records.getString("debit_card_number");
					address = records.getString("address");
				}
				
				String command = "insert into orders (name, email, debit_card_number, state, address, order_contents) "
						+ "values ('"+name+"', '"+email+"', '" +debit_card_number+ "', 'Ordered', '" +address+ "', '" + order_contents + "')";
				
				try {
					sql_stmt.executeUpdate(command);
					session.setAttribute("customerCart", null);
					out.print("<h1>Checked out successfully, your cart is now empty.</h1>");
				} catch(Exception e) {
					out.print("<h1>Encountered a problem while checking out:</h1>");
					out.print(e.getMessage());
				}
				
	        } catch (SQLException e) {
				e.printStackTrace();
			}
	        
		}

		out.print(Java_Classes.Constants.POST_CONTENT_TEMPLATE);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}