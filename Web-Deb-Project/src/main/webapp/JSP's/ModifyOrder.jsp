<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Java_Classes.ConnectionHandler, java.sql.*" %>
<!DOCTYPE html>

<!-- Check if we need to update the table with the new value -->
<%
	boolean changeAttempted = false;
	boolean editSuccessful = false;

	if(request.getParameter("edit") != null) {
		changeAttempted = true;
		editSuccessful = ConnectionHandler.editOrder(request);
	}
%>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Modify Order</title>
	</head>
	
	<body>
		
		<!-- Quick 'n dirty list of orders -->
		<table border="1">
			<tr>
				<td>id</td>
				<td>name</td>
				<td>email</td>
				<td>card number</td>
				<td>state</td>
				<td>address</td>
				<td>order contents</td>
			</tr>
			<%
				Connection mycon = ConnectionHandler.getConnection();
				Statement sql_stmt = mycon.createStatement();
				ResultSet results = sql_stmt.executeQuery("select * from orders");
				while(results.next()) {
					out.println("<tr><td>" + results.getString("id") + "</td><td>" + results.getString("name") + "</td><td>" + 
				results.getString("email") + "</td><td>" + results.getString("debit_card_number") + "</td><td>" + 
							results.getString("state") + "</td><td>" + results.getString("address") + "</td><td>"+results.getString("order_contents")+"</td></tr>");
				}
			%>
		</table>
	
		<!-- The form for editing an order -->
		<h3>Modify Order</h3>
		<form action = "ModifyOrder.jsp" method = "post">
			<input type = "hidden" name = "edit">
			<label>ID</label>
			<input type = "text" name = "id"><br>
			
			<!-- The radio buttons -->
			<input type = "radio" name = "action_type" value = "Ordered">
			<label>Set as Ordered</label><br>
			<input type = "radio" name = "action_type" value = "Shipped">
			<label>Set as Shipped</label><br>
			<input type = "radio" name = "action_type" value = "Delivered">
			<label>Set as Delivered</label><br>
			<input type = "radio" name = "action_type" value = "delete">
			<label>Delete Order</label><br>
			<input type = "submit" value = "Submit">
		</form>
		
		<!-- The label for success and failure -->
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