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
		Connection mycon = ConnectionHandler.getConnection();
        
		if (customerCart == null) {
			customerCart = new HashMap<>();
			session.setAttribute("customerCart", customerCart);
		}
		Integer addedProductID = Integer.parseInt(request.getParameter("newProduct"));
		
		synchronized(customerCart) {
			if (addedProductID != null) {
				if (customerCart.containsKey(addedProductID)) {
					customerCart.put(addedProductID, customerCart.get(addedProductID) + 1);
				} else {
					customerCart.put(addedProductID, 1);	
				}
			}
			
			if (customerCart.size() == 0) out.println("<h1>Your cart is empty.</h1>");
			else {
                out.println("<html><head><title>Cat Food Listing</title></head><body>");
                out.println("<table border='1' cellpadding='6'>");
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
			                out.println("<td>" + customerCart.get(productID) + "</td>");
						}
						out.println("</tr>");
			        } catch (SQLException e) {
						e.printStackTrace();
					}
				}
				out.println("</table>");
			}
		}
		
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
}