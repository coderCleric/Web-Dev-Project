<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Java_Classes.ConnectionHandler, java.sql.*" %>
<!DOCTYPE html>

<!-- Check if we need to update the table with the new value -->
<%
	boolean changeAttempted = false;
	boolean editSuccessful = false;

	//It's an edit request
	if(request.getParameter("edit") != null) {
		changeAttempted = true;
		editSuccessful = ConnectionHandler.editProduct(request);
	}
	
	//It's an add request
	if(request.getParameter("add") != null) {
		changeAttempted = true;
		editSuccessful = ConnectionHandler.addProduct(request);
	}
	
	//It's a delete request
	if(request.getParameter("delete") != null) {
		changeAttempted = true;
		editSuccessful = ConnectionHandler.deleteProduct(request);
	}
%>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Modify Product</title>
	</head>
	
	<body>
		<!-- Quick 'n dirty list of products -->
		<table border="1">
			<tr>
				<td>id</td>
				<td>cat type</td>
				<td>specialty</td>
				<td>customer price</td>
				<td>wholesale price</td>
				<td>name</td>
				<td>description</td>
				<td>number stocked</td>
			</tr>
			<%
				Connection mycon = ConnectionHandler.getConnection();
				Statement sql_stmt = mycon.createStatement();
				ResultSet results = sql_stmt.executeQuery("select * from cat_food");
				while(results.next()) {
					out.println("<tr><td>" + results.getString("id") + "</td><td>" + results.getString("cat_type") + "</td><td>" + 
				results.getString("specialty") + "</td><td>" + results.getString("customer_price") + "</td><td>" + 
							results.getString("wholesale_price") + "</td><td>" + results.getString("name") +  "</td><td>" + 
				results.getString("description") + "</td><td>" + results.getString("number_stocked") + "</td></tr>");
				}
			%>
		</table>
		

		<!-- Form for editing a product -->
		<h3>Edit Product</h3>
		<form action = "ModifyProduct.jsp" method = "post">
			<input type = "hidden" name = "edit">
			<label>ID</label>
			<input type = "text" name = "id"><br>
			<label>Cat Type</label>
			<input type = "text" name = "cat_type"><br>
			<label>Specialty</label>
			<input type = "text" name = "specialty"><br>
			<label>Customer Price</label>
			<input type = "text" name = "customer_price"><br>
			<label>Wholesale Price</label>
			<input type = "text" name = "wholesale_price"><br>
			<label>Name</label>
			<input type = "text" name = "name"><br>
			<label>Description</label>
			<input type = "text" name = "description"><br>
			<label>Number Stocked</label>
			<input type = "text" name = "number_stocked"><br>
			<input type = "submit" value = "Submit">
		</form>
		
		<!-- Form for adding a product -->
		<h3>Add Product</h3>
		<form action = "ModifyProduct.jsp" method = "post">
			<input type = "hidden" name = "add">
			<label>Cat Type</label>
			<input type = "text" name = "cat_type"><br>
			<label>Specialty</label>
			<input type = "text" name = "specialty"><br>
			<label>Customer Price</label>
			<input type = "text" name = "customer_price"><br>
			<label>Wholesale Price</label>
			<input type = "text" name = "wholesale_price"><br>
			<label>Name</label>
			<input type = "text" name = "name"><br>
			<label>Description</label>
			<input type = "text" name = "description"><br>
			<label>Number Stocked</label>
			<input type = "text" name = "number_stocked"><br>
			<input type = "submit" value = "Submit">
		</form>
		
		<!-- Form for deleting a product -->
		<h3>Delete Product</h3>
		<form action = "ModifyProduct.jsp" method = "post">
			<input type = "hidden" name = "delete">
			<label>ID</label>
			<input type = "text" name = "id"><br>
			<input type = "submit" value = "Submit">
		</form>
		
		<!-- Display text if the edit was successful -->
		<%
			if(changeAttempted) {
				if(editSuccessful)
					out.println("<h1 style = 'color:green'>Edit Successful!</h1>");
				else
					out.println("<h1 style = 'color:red'>Edit Failed!</h1>");
			}
		%>
	</body>
</html>