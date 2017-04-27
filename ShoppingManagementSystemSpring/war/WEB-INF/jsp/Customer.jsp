<html>
<style>
h1 {
	color: lightcoral;
}
</style>
<body>

	<center>
		<h1>Customer Menu</h1>
		<div id="form">

			<button type="button" onclick="buy()" value="Products To Buy">click
				to buy</button>
			<button type="button" onclick="showCart();show()" value="view cart">
				click to see cart</button>
			<button type="button" onclick="logout()">logout</button>

		</div>

		<table id="dtable" border=1>


		</table>

		<div id="mydiv" style="visibility: hidden">
			<button type="button" onclick="menu()">menu</button>
			<button type="button" onclick="logout()">logout</button>
		</div>
		<script>
			function show() {

				document.getElementById("mydiv").style.visibility = "visible";
			}
			function buy() {
				window.location.href = "/ShowProduct";
			}
			function showCart() {

				document.getElementById("form").innerHTML = " ";
				document.getElementById("dtable").innerHTML = " ";
				var tab = document.getElementById("dtable");
				var row = tab.insertRow(0);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);

				cell1.innerHTML = "product";
				cell2.innerHTML = "quantity";

				httpRequest = new XMLHttpRequest();
				httpRequest.onreadystatechange = function() {
					if (httpRequest.readyState === XMLHttpRequest.DONE) {
						if (httpRequest.status === 200) {
							var obj = httpRequest.responseText;
							var object = JSON.parse(obj);
							for ( var i in object) {
								var tab = document.getElementById("dtable");
								var row = tab.insertRow();
								var cell1 = row.insertCell(0);
								var cell2 = row.insertCell(1);
								cell1.innerHTML = i;
								cell2.innerHTML = object[i];

							}
						}
					}

				};
				httpRequest.open("GET", "/showCart", true)
				httpRequest.send();

			};
			function addCart() {
				window.location.href = "/goCart";
			};
			function buyProduct() {
				alert("bought");
			};
			function logout() {
				//session.invalidate();
				window.location.href = "/"
			};
			function menu() {
				window.location.href = "/customerMenu";
			}
		</script>


	</center>
</body>
</html>