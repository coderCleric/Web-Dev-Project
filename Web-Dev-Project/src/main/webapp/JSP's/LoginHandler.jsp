<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Java_Classes.ConnectionHandler, java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login Attempt</title>
	</head>
	
	<body>
		<!-- Evaluate the login info -->
		<%
			//Get the username & password
			String givenUsername = request.getParameter("givenUsername");
			String givenPassword = request.getParameter("givenPassword");
			
			//Get the table column for the entered username
			Connection mycon = ConnectionHandler.getConnection();
			Statement sql_stmt = mycon.createStatement();
			ResultSet results = sql_stmt.executeQuery("select * from employees where username = '" + givenUsername + "'");
			
			//If the login info is valid, store the corresponding user ID in the session
			boolean loginValid = false;
			while(results.next()) {
				if(results.getString("password").equals(givenPassword)) {
					session.setAttribute("userID", results.getString("id"));
					loginValid = true;
					break;
				}
			}
			
			//Print a message, telling them if they were successful
			if(loginValid)
				out.println("<h1>Login Successful!<h1><br>");
			else
				out.println("<h1>Login Failed...<h1><br>");
		%>
		
		<!-- Make the buttons to return to the homepage or login screen -->
		<a href = "Homepage"><button>Return to Homepage</button></a>
		<a href = "Login"><button>Return to Login</button></a>
	</body>
</html>