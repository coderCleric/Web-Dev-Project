package Java_Classes;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;

public class ConnectionHandler {
	//private static String databaseName = "project2";
	//private static String databasePassword = "cba321";
	private static String databaseName = "project2";
	private static String databasePassword = "cba321";
	private static Connection savedConnection = null;
	
	//Sets up and returns the connection to the database
	public static Connection getConnection() {
		//Return the saved connection if it exists
		if(savedConnection != null)
			return savedConnection;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,"root", databasePassword);
            savedConnection = mycon;
            return mycon;
		} catch(Exception e) {
			System.out.println("Connection handler failed");
			e.printStackTrace();
			return null;
		}
	}
	
	//Edits a product in the database
	//Returns true if the edit was successful, and false otherwise
	public static boolean editProduct(HttpServletRequest request) {
		String command = "UPDATE cat_food SET ";
		
		//Check that they entered an id
		if(!request.getParameter("id").equals("")) {
			boolean isFirst = true;
			
			//Check if what needs updated, value by value
			//Cat type
			String currentParam = request.getParameter("cat_type");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "cat_type = '" + currentParam + "'";
				isFirst = false;
			}

			//Specialty
			currentParam = request.getParameter("specialty");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "specialty = '" + currentParam + "'";
				isFirst = false;
			}

			//Customer price
			currentParam = request.getParameter("customer_price");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "customer_price = '" + currentParam + "'";
				isFirst = false;
			}

			//Wholesale price
			currentParam = request.getParameter("wholesale_price");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "wholesale_price = '" + currentParam + "'";
				isFirst = false;
			}

			//Name
			currentParam = request.getParameter("name");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "name = '" + currentParam + "'";
				isFirst = false;
			}

			//Description
			currentParam = request.getParameter("description");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "description = '" + currentParam + "'";
				isFirst = false;
			}

			//Number stocked
			currentParam = request.getParameter("number_stocked");
			if(!currentParam.equals("")) {
				if(!isFirst)
					command += ", ";
				command += "number_stocked = '" + currentParam + "'";
				isFirst = false;
			}
			
			//Add the ending bit
			command += " WHERE (id = '" + request.getParameter("id") + "')";
			
			//Actually complete the request
			try {
				Connection mycon = ConnectionHandler.getConnection();
				Statement sql_stmt = mycon.createStatement();
				sql_stmt.executeUpdate(command);
				return true;
			}catch(Exception e) {
				return false;
			}
		}
		else
			return false;
	}
	
	//Adds a product to the database
	//Returns true if the addition was successful, and false otherwise
	//NOTE: This function does not explicitly set the id, so auto-increment should be on at the database
	public static boolean addProduct(HttpServletRequest request) {
		//Construct the command
		String command = "INSERT INTO cat_food (cat_type, specialty, customer_price, wholesale_price, name, description, number_stocked) VALUES (";
		
		//Cat type
		String currentParam = request.getParameter("cat_type");
		if(currentParam.equals(""))
			return false;
		else
			command += "'" + currentParam + "'";

		//Specialty
		currentParam = request.getParameter("specialty");
		if(currentParam.equals(""))
			return false;
		else
			command += ", '" + currentParam + "'";

		//Customer Price
		currentParam = request.getParameter("customer_price");
		if(currentParam.equals(""))
			return false;
		else
			command += ", '" + currentParam + "'";

		//Wholesale price
		currentParam = request.getParameter("wholesale_price");
		if(currentParam.equals(""))
			return false;
		else
			command += ", '" + currentParam + "'";

		//Name
		currentParam = request.getParameter("name");
		if(currentParam.equals(""))
			return false;
		else
			command += ", '" + currentParam + "'";

		//Description
		currentParam = request.getParameter("description");
		if(currentParam.equals(""))
			return false;
		else
			command += ", '" + currentParam + "'";

		//Number stocked
		currentParam = request.getParameter("number_stocked");
		if(currentParam.equals(""))
			return false;
		else
			command += ", '" + currentParam + "'";
		
		//Finish the command
		command += ");";
		
		//Actually complete the request
		try {
			Connection mycon = ConnectionHandler.getConnection();
			Statement sql_stmt = mycon.createStatement();
			sql_stmt.executeUpdate(command);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	//Deletes a product in the database
	//Returns true if the delete was successful, and false otherwise
	public static boolean deleteProduct(HttpServletRequest request) {		
		//Construct the request based on the given ID
		String id = request.getParameter("id");
		String command = "DELETE FROM cat_food WHERE id = '" + id + "'";
			
		//Actually complete the request
		try {
			Connection mycon = ConnectionHandler.getConnection();
			Statement sql_stmt = mycon.createStatement();
			sql_stmt.executeUpdate(command);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	//Performs the specified action on the orders table
	//Returns true if the edit was successful, and false otherwise
	public static boolean editOrder(HttpServletRequest request) {
		String action = request.getParameter("action_type");
		String command = "";

		//Special case for the delete
		if(action.equals("delete")) {
			command = "DELETE FROM orders WHERE id = '" + request.getParameter("id") + "'";
		}
		
		//If it's anything but delete
		else {
			command = "UPDATE orders SET state = '" + action + "' WHERE (id = '" + request.getParameter("id") + "');";
		}
		
		//Actually complete the request
		try {
			Connection mycon = ConnectionHandler.getConnection();
			Statement sql_stmt = mycon.createStatement();
			sql_stmt.executeUpdate(command);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
