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



@WebServlet("/Cart")
public class Cart extends HttpServlet {
	
	public Cart() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		HttpSession session = request.getSession();
		Map<Integer, Integer> customerCart = (HashMap<Integer, Integer>) session.getAttribute("customerCart");
		PrintWriter out = response.getWriter();
		Connection mycon = Java_Classes.ConnectionHandler.getConnection();
        
		if (customerCart == null) {
			customerCart = new HashMap<>();
			session.setAttribute("customerCart", customerCart);
		}
		
		String addedProductID = request.getParameter("newProduct");
		String modifiedProduct = request.getParameter("modifiedProduct");
		String modifiedQuantity = request.getParameter("modifiedQuantity");
		out.print(Java_Classes.Constants.PRE_CONTENT_TEMPLATE);
        out.println("<title>Your cart</title>");

		
		synchronized(customerCart) {
			if (addedProductID != null) {
				Integer addedProductID_int = Integer.parseInt(addedProductID);
				if (customerCart.containsKey(addedProductID_int)) {
					customerCart.put(addedProductID_int, customerCart.get(addedProductID_int) + 1);
				} else {
					customerCart.put(addedProductID_int, 1);	
				}
			}
			
			if (modifiedProduct != null && modifiedQuantity != null) {
				customerCart.put(Integer.parseInt(modifiedProduct), Integer.parseInt(modifiedQuantity));
				if (Integer.parseInt(modifiedQuantity) == 0)
					customerCart.remove(Integer.parseInt(modifiedProduct));
			}
		

			if (customerCart.size() == 0) out.println("<h1>Your cart is empty.</h1>");
			else {
                out.println("<table border='1' cellpadding='6' width='100%'>");
                out.println("<tr>");
                   
                out.println("<th>Cat Type</th>");
                out.println("<th>Food Name</th>");
                out.println("<th>Food Description</th>");
                out.println("<th>Price</th>");
                out.println("<th>Availability</th>");
                out.println("<th>Quantity in cart</th>");

                out.println("</tr>");
				
				for (Integer productID : customerCart.keySet()) {
					ResultSet records = null;
			        try {
			            Statement sql_stmt=null;
			            sql_stmt = mycon.createStatement();  
						records = sql_stmt.executeQuery("select * from project2.cat_food WHERE id=" + productID);
						out.println("<tr>");
						while (records.next()) {
							out.println("<td>" + records.getString("cat_type") + "</td>");
			                out.println("<td>" + records.getString("name")+ "</td>");
			                out.println("<td>" + records.getString("description") + "</td>");
			                out.println("<td>" + records.getString("customer_price") + "</td>");
			                out.println("<td>" + records.getString("number_stocked") + "</td>");
	                        out.println("<td><form action='Cart' method='post'>");
	                        out.println("<input type='hidden' name='modifiedProduct' value='"+productID+"'/>");
	                        out.println("<input type='number' name='modifiedQuantity' value='" + customerCart.get(productID) +"'/>");
	                        out.println("<input type='submit' name='buy' value='Update quantity in cart'/>");
	                        out.println("</form></td>");
						}
						out.println("</tr>");
			        } catch (SQLException e) {
						e.printStackTrace();
					}
				}
				out.println("</table>");
				out.print(Java_Classes.Constants.POST_CONTENT_TEMPLATE);
				
			}
		}
		
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
}