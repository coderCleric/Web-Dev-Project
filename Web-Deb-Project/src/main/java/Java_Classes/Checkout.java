package Java_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		session.setAttribute("customerCart", null);
		PrintWriter out = response.getWriter();

		out.print(Java_Classes.Constants.PRE_CONTENT_TEMPLATE);

		out.print("<h1>Checked out successfully, your cart is now empty.</h1>");
		out.print(Java_Classes.Constants.POST_CONTENT_TEMPLATE);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}