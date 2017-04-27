
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<style>
h1 {
	color: lightcoral;
}
</style>
<body bgcolor="black" align="center">
	<h1>Shopping Management System</h1>

	<button type="button" onclick="signup()">signup</button>
	<button type="button" onclick="login()">login</button>


	<script>
		function signup() {

			var httpRequest = new XMLHttpRequest();
			httpRequest.onreadystatechange = function() {
				if (this.readyState === 4 && this.status === 200) {

					window.location.href = "/signIn";
				}

			}

			httpRequest.open("GET", "/signIn", true);
			httpRequest.send();

		}
		function login() {

			httpRequest = new XMLHttpRequest();
			httpRequest.onreadystatechange = function() {
				if (this.readyState === 4 && this.status === 200) {

					//alert("login");
					window.location.href = "/loginPage";

				}

			}

			httpRequest.open("GET", "/loginPage", true);
			httpRequest.send();
		}
	</script>



</body>




</html>