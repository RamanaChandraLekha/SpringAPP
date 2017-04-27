<!-- <html>
<head>
<style>
body {s
color: lightcoral;
}

Button submit {
background-color: blue;
}
</style>
</head>
<body>


<h1 align="center">Login Form</h1>
//<form name="login" action="login" method="post">


 userName : <input type="text" name="username" value=""  id="name" required ></br> </br> 
PassWord : <input type="password" name="password" value="" id="password" required></br> </br> 
<button type="button" onclick="func()">submit</button>
<script >
function func()
{
	var name=document.getElementById("name").value;
	var pswd=document.getElementById("password").value;
	/* var userObj={username:name,password:pswd};
	var JsonObj = JSON.stringify(userObj); */
	var param = "uname=" + name + "&password=" + pswd;

	httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {

		if (httpRequest.readyState == XMLHttpRequest.DONE) {
			if (httpRequest.status == 200) {
				
				//window.location.href = "/loginPage";
			} else {
				alert('There was a problem with the request.');
				document.write(httpRequest.responseText);
				
			}

		}

		
	};
	httpRequest.open("POST", "/signup", true);
	
	httpRequest.setRequestHeader("Accept","application/json"); 
	/* httpRequest.setRequestHeader('Content-Type',
	'application/x-www-form-urlencoded'); */
	httpRequest.send(param);


	
	
	
	
	};
	



</script>


		

//</form>

</body>
</html>
 -->
 
 <html>
<head>
<style>
body {
color: lightcoral;
}

Button submit {
background-color: blue;
}
</style>
</head>
<body>


<h1 align="center">Login Form</h1>
<form name="login" action="login" method="post">


EmailId : <input type="email" name="mail" value=""  id="name" required ></br> </br> 
PassWord : <input type="password" name="password" value="" id="password" required></br> </br> 
<input type="submit" value="submit"/>
<input	type="reset" value="reset">


		

</form>

</body>
</html>

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 