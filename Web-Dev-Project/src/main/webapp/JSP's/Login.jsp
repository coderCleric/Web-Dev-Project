<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
	</head>
	
	<body>
		<!-- The login form -->
		<form action = "LoginHandler.jsp" method = "post">
			<label>Username: </label>
			<input type = "text" name = "givenUsername"><br>
			<label>Password: </label>
			<input type = "password" name = "givenPassword"><br>
			<button type = "submit">Login</button><br>
		</form>
		
	</body>
</html>