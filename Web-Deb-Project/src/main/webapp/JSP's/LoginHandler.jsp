<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Java_Classes.ConnectionHandler" %>
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
			
			//Find if the username and password are valid (not done yet)
			
			//If they are, store the corresponding user ID in the session
			
			//Print a message, telling them if they were successful
			out.print(givenUsername + "<br>\n" + givenPassword);
		%>
	</body>
</html>