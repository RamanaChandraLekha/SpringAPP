<!doctype html>
<html>
<body>
	<div id="form">

		Product: <input type="text" name="product" id="product" required /> <br>
		quantity: <input type="number" name="quantity" id="quantity" required />
		<button type="button" onclick="func()">submit</button>

	</div>
	<div id="mydiv"></div>
	<script>
		function func() {

			var product = document.getElementById("product").value;
			var quantity = document.getElementById("quantity").value;

			obj = {
				productName : product,
				productQuantity : quantity
			};

			httpRequest = new XMLHttpRequest();
			httpRequest.onreadystatechange = function() {
				if (httpRequest.readyState === XMLHttpRequest.DONE) {
					if (httpRequest.status === 200) {

						alert("products are added")

						window.location.href = "/shopkeeper";

					} else {
						alert("sorry products are not added try again");
						window.location.href = "/AddProductPage";

					}

				}

			};

			httpRequest.open("POST", "/addProducts", true);
			httpRequest.setRequestHeader("Content-Type",
					"application/json; charset=utf8");

			httpRequest.send(JSON.stringify(obj));

		};
	</script>

</body>
</html>