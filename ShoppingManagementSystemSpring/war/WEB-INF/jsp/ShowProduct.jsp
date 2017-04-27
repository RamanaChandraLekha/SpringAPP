<%@ page
	import="com.google.appengine.api.datastore.DatastoreService,javax.servlet.http.HttpServletRequest"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory,com.google.appengine.api.datastore.Entity,com.google.appengine.api.datastore.Key,com.google.appengine.api.datastore.PreparedQuery,com.google.appengine.api.datastore.Query"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body id="body">
	<center>
		<h2>Products</h2>
		<div id="page">
			<table border="1">
				<tr>

					<td><b>Product</b></td>
					<td><b>Quantity</b></td>
					<td><b>Add to Cart</b></td>
					<td><b>Buy Product</b></td>
				</tr>

				<%!Key pid;
	String product;
	long quantity;%>
				<%
					Query query = new Query("Products");
					DatastoreService data = DatastoreServiceFactory.getDatastoreService();
					PreparedQuery pq = data.prepare(query);

					for (Entity e : pq.asIterable()) {
						product = (String) e.getProperty("ProductName");
						quantity = (Long) e.getProperty("Quantity");
						pid = e.getKey();
				%>
				<tr id=<%=product%>>

					<td><%=product%></td>
					<td><%=quantity%></td>
					<td><input type="button" value="Add to cart"
						onclick="addToCart(event)"></td>
					<td><input type="button" value="Buy Product"
						onclick="buyProduct(event)"></td>
				</tr>

				<%
					}
				%>

			</table>
		</div>
		<div id="mydiv"></div>
	</center>

</body>

<script type="text/javascript">
	//var questionId, answer;

	function createObj() {
		product = event.target.parentElement.parentElement.getAttribute('id');
		var quantity = prompt("enter quantity you want");

		obj = {
			productName : product,
			productQuantity : quantity
		};

		httpRequest = new XMLHttpRequest();
	}
	function addToCart(event) {
		createObj();
		httpRequest.onreadystatechange = function() {

			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					alert("products are added to cart");

					document.getElementById("page").innerHTML = " ";
					document.getElementById("mydiv").innerHTML = httpRequest.responseText;
				}
			}

		};
		httpRequest.open("POST", "/addProductsCart", true);
		httpRequest.setRequestHeader("Content-Type",
				"application/json; charset=utf8");

		httpRequest.send(JSON.stringify(obj));

	};
	function buyProduct(event) {
		createObj();
		httpRequest.onreadystatechange = function() {

			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					alert("thanks for buying products");

					document.getElementById("page").innerHTML = " ";
					document.getElementById("mydiv").innerHTML = httpRequest.responseText;
				}
			}

		};
		httpRequest.open("POST", "/buy", true);
		httpRequest.setRequestHeader("Content-Type",
				"application/json; charset=utf8");

		httpRequest.send(JSON.stringify(obj));

	};
</script>
</html>