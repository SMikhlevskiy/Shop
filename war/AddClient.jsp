<%@ page contentType="text/html; encoding=utf-8"%>
<%@ page import="smikhlevskiy.ClientsView"%>
<html>
	<body>
		<h1>Add new Client</h1>
		<form action="/AddClient" method="POST">
			FIO
			<input type="text" name="FIO" value=""><br>
			phone 
			<input type="text" name="phone"><br>
			email
			<input type="text" name="email"><br>
			Married<br>
			<input type="radio" name="married" value="yes">Yes<br>
			<input type="radio" name="married" value="no">No<br>
			<button>Add</button>
		</form>
		<%! ClientsView clientsView = ClientsView.getInstance(); %>
		<%= clientsView %>
		
	</body>
</html>
