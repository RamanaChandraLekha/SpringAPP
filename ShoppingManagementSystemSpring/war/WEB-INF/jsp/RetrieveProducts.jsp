<!doctype html>
<html>
  <body>
<div id="form">
  productname :
	<input type="text" name="productname" id="product" required />
	<br>
	<button type="button" onclick="submit()">submit</button>
	</div>
	<div id="mydiv">  </div>
	<script>
		function submit() {

			var product = document.getElementById("product").value;
			var param = "productName=" + product;
			httpRequest = new XMLHttpRequest();
			httpRequest.onreadystatechange = function() {
				if (httpRequest.readyState === XMLHttpRequest.DONE) {
					if (httpRequest.status === 200) {
						document.getElementById("form").innerHTML=" ";
						document.getElementById("mydiv").innerHTML=httpRequest.responseText;
						//alert("details are");
						
					} else {

						alert("sorry!try again");
					window.location.href = "/retrieve";

					}

				}

			};
			httpRequest.open("POST", "/retrieveProduct", true)
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			httpRequest.send(param);

		};
	</script>




  
  
  
  </body>
</html>