<!doctype html>
<html>
<body>
	<!-- <form id="form" method="post" action="addProductsCart"> --> 
	<div id="form">
	Product:
	<input type="text" name="product" id="product" required />
	<br> quantity:
	<input type="number" name="quantity" id="quantity" required /><br>
<!-- <button type="button" onclick="addCart(common)"> submit</button> -->
<button type="button" onclick="buy()"> Buy</button>
</div>
<p id="mydiv"> </p>
	<!-- </form>  -->
	<script>
	/* function common()
	{
		
		var product = document.getElementById("product").value;
		var quantity = document.getElementById("quantity").value;

		 productObj = "product=" + product + "&quantity="
				+ quantity;

		httpRequest = new XMLHttpRequest();
		
	} */
	/* 	function addCart(callback) {
callback();
			/* var product = document.getElementById("product").value;
			var quantity = document.getElementById("quantity").value;

			var productObj = "product=" + product + "&quantity="
					+ quantity;

			httpRequest = new XMLHttpRequest(); */
			/* httpRequest.onreadystatechange = function() {
				if (httpRequest.readyState === XMLHttpRequest.DONE) {
					if (httpRequest.status === 200) {

						document.getElementById("form").innerHTML=" ";
document.getElementById("mydiv").innerHTML=httpRequest.responseText;
alert("products are added to cart");
						//window.location.href = "/customerMenu"; 

					} else {
						alert("sorry products are not added try again");
						//window.location.href= "/customerMenu";

					}

				}

			};

			httpRequest.open("POST", "/addProductsCart", true);
			//httpRequest.setRequestHeader("Content-Type", "application/json; charset=utf8");
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			httpRequest.send(productObj);

		};
		 */
		
 	function buy()
		{
			var product = document.getElementById("product").value;
			var quantity = document.getElementById("quantity").value;

			 productObj = "product=" + product + "&quantity="
					+ quantity;

			httpRequest = new XMLHttpRequest();
			
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {

					document.getElementById("form").innerHTML=" ";
document.getElementById("mydiv").innerHTML=httpRequest.responseText;
alert("thankyou!for buying the products");
					//window.location.href = "/customerMenu"; 

				} else {
					alert("sorry products are not added try again");
					//window.location.href= "/customerMenu";

				}

			}

		};

		httpRequest.open("POST", "/buy", true);
		//httpRequest.setRequestHeader("Content-Type", "application/json; charset=utf8");
		httpRequest.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		httpRequest.send(productObj);

			
		};
	</script> 

</body>
</html>