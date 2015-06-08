<%@ page contentType="text/html; encoding=utf-8"%>
<%@ page import="smikhlevskiy.PurchasesView"%>
<html>
	<body>
		<h1>Add new purchase</h1>
		<form action="/AddPurchase" method="POST">
			IDClient
			<input type="text" name="idClient" value=""><br>
			IDMerchandise 
			<input type="text" name="idMerchandise"><br>
			
			
			<button>Add Purchase</button>
		</form>
		<%! PurchasesView purchasesView = PurchasesView.getInstance(); %>
		<%= purchasesView %>
		
	</body>
</html>
