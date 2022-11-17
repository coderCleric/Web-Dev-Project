package Java_Classes;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHandler {
	private static String databaseName = "test";
	private static String databasePassword = "test";
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
			return null;
		}
	}
}
