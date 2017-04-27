<!doctype html>
<html>
<body>

	productname :
	<input type="text" name="productname" id="product" required />
	<br>
	<button type="button" onclick="submit()">submit</button>
	<p id="mydiv"></p>
	<script>
		function submit() {

			var product = document.getElementById("product").value;
			var param = product;
			
			httpRequest = new XMLHttpRequest();
			httpRequest.onreadystatechange = function() {
				if (httpRequest.readyState === XMLHttpRequest.DONE) {
					if (httpRequest.status === 200) {
						
				
						var obj=httpRequest.responseText;
						if(obj.status==true)
							{
							alert("product is deleted");
						window.location.href = "/shopkeeper";}
						else{
							alert("product is not available");
							window.location.href = "/deleteProductPage";
						}

					} else {
                        alert("product is not deleted")
						window.location.href = "/deleteProductPage";

					}

				}

			};
			httpRequest.open("DELETE", "/deleteProduct?product="+param, true)
			/* httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded'); */
			httpRequest.send();

		};
	</script>




	
</body>
</html>