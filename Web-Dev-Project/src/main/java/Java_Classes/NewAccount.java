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
@WebServlet("/NewAccount")
public class NewAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public NewAccount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        	out.print(Java_Classes.Constants.PRE_CONTENT_TEMPLATE);
     
            out.println("<head><title>Create New Account</title></head><body>");
            out.println("<form action = 'NewAccount' method = 'get'>");
            out.println("<label>Username: </label>\r\n"
            		+ "			<input type = \"text\" name = \"givenUsername\"><br>\r\n"
            		+ "			<label>Password: </label>\r\n"
            		+ "			<input type = \"password\" name = \"givenPassword\"><br>\r\n"
    				+ "			<label>Confirm Password: </label>\r\n"
            		+ "         <input type = \"password\" name = \"confirmedPassword\"><br>"
            		+ "			<button type = \"submit\">Create Account</button><br>");
            out.println("</form");
            
            //Check the login, if provided
            String confirmedPassword = request.getParameter("confirmedPassword");
            if(confirmedPassword != null) {
            	String givenUsername = request.getParameter("givenUsername");
            	String givenPassword = request.getParameter("givenPassword");
            	
            	//The user gave invalid info
            	if(givenUsername.equals("") || givenPassword.equals("") || !confirmedPassword.equals(givenPassword)) {
            		out.println("<h1>Account Creation Failed! Something was entered wrong!</h1>");
            	}
            	
            	//Check if the command succeeds
            	else if(!ConnectionHandler.addAccount(givenUsername, givenPassword)) {
            		out.println("<h1>Account Creation Failed! Command failed to execute!</h1>");
            	}
            	
            	//Print a success message if it works
            	else {
            		out.println("<h1>Account Creation Succeeded!</h1>");
            	}
            }
            out.print(Java_Classes.Constants.POST_CONTENT_TEMPLATE);
        }
}