<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Homepage</title>
	</head>
	
	<body>
		<!-- The big table for the overall site layout -->
		<table style = "width:100%; height:100%; position:absolute; left:0; right:0; top:0; bottom:0">
		
			<!-- The header -->
			<tr>
				<td colspan = "2" style = "background-color:red">
					<h1 style="text-align:center">WCOO's Cat Food Emporium</h1>
					<h3 style="text-align:center">Quality cat food for great cats!</h3>
				</td>
			</tr>
			
			<tr style = "height:100%">
				<!-- The product preview table -->
				<td style = "width:80%; height:100%; background-color:blue">
				
				</td>
				
				<!-- The navigational sidebar -->
				<td style = "width:20%; height:100%; vertical-align:top; background-color:green">
					<a href = "Homepage">Homepage</a><br>
					<a href = "Login">Login</a><br>
					<a href = "ListProducts">Products List</a><br>
					<a href = "Cart">Cart</a>
					<!-- These links should only exist if the user is logged in -->
					<%
						if(session.getAttribute("userID") != null) {
							out.println("<br><a href = 'ModifyProduct'>Modify Products</a><br>");
							out.println("<a href = 'ModifyOrder'>Modify Orders</a>");
						}
					%>
				</td>
			</tr>
		</table>
	</body>
</html>