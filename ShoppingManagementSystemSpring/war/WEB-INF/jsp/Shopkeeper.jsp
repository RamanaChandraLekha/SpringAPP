

<%-- <%
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%> --%>
<html>

<style>
h1{
color:lightcoral;}</style>
<body><center>
<h1 align="center"> Shopkeeper Menu</h1>
	<div id="menu">
		<button type="button" onclick="addProduct()" value="add">AddProduct</button>
	
		<button type="button" onclick="view();show()">view products</button>
		
		<button type="button" onclick="deleted()" value="delete">Delete</button>
	
		<button type="button" onclick="retrieve()" value="retrieveDetails">RetrieveProductDetails</button>

		<button type="button" onclick="logout()">logout</button>
	</div>
	
	<table id="dtable" border="1" width="100" height="100">
	</table>
	<div id="mydiv" style="visibility:hidden">
	<button type="button" onclick="menu()">menu</button>
	<button type="button" onclick="logout()">logout</button>
	</div>
	<script>
	function show()
	{
		
		document.getElementById("mydiv").style.visibility="visible";
	}
	function menu()
	{
		
		window.location.href="/shopkeeper";
	}
		function addProduct() {

			window.location.href = "/AddProductPage";

		};
		function view() {
			document.getElementById("menu").innerHTML = " ";
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

			}};
			httpRequest.open("GET", "/view", true);
			httpRequest.send();

		};

		function logout() {

			alert("sucessfully logged out");
			window.location.href = "/display";

		};
		
		function deleted()
		{
			
			window.location.href = "/deleteProductPage";
			
			
		};
		function retrieve()
		{
			
			window.location.href = "/retrieve";
			
			
		};
		
		
	</script>








</center>
</body>
</html>