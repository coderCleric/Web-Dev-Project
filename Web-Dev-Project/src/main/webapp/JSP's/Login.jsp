<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	
	<body>
		<%
			out.print(Java_Classes.Constants.PRE_CONTENT_TEMPLATE);
		%>
		<!-- The login form -->
		<form action = "LoginHandler" method = "post">
			<label>Username: </label>
			<input type = "text" name = "givenUsername"><br>
			<label>Password: </label>
			<input type = "password" name = "givenPassword"><br>
			<button type = "submit">Login</button><br>
		</form>
		<%
			if(session.getAttribute("userID") != null) {
				out.println("<a href = 'NewAccount'><button>New Account</button></a>");
			}
		%>
		<%
			out.print(Java_Classes.Constants.POST_CONTENT_TEMPLATE);
		%>
		
	</body>
</html>