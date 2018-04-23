<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Welcome</title>

<link rel="stylesheet"
	href="css/bootstrap.min.css">

<style media="screen">
body {
	background-color: black;
	height: 100%;
}

.adjustHeader {
	height: 100px;
	width: 100%;
	background-color:pink;
}

.row {
	width: 100%;
	height: 100%;
}

.leftContainer {
	height: 240px;
	margin-top: 150px;
	margin-left: 200px;
	border: 2px solid blue;
	background-color: white;
}

.rightContainer {
	height: 100%;
	width: 100%;
	margin-left: 20px;
	background-color: black;
	color: white;
}

footer {
	color: white;
	width: 100%;
}

</style>

<script type="text/javascript">

  function logout(){
    if(confirm("Do you wish to logout?") == true){
      location.replace("LogoutPage.jsp");
    }
  }

  function changeTitle(name) {
      document.getElementById("changeMe").innerHTML = name;
  }

  </script>

</head>
<body>

	<% String profileName = (String) session.getAttribute("userName"); %>

	<header class="adjustHeader">
		<img src="css/img/banner.jpg" style="height:inherit; width:inherit;"/>
	</header>


	<form class="" action="LoginServletController" method="post">
		<div class="row">
			<div class="col-lg-3 col-md-5 col-sm-6 rounded leftContainer">
				<h2 class="text-center pt-2">Welcome <%=profileName%></h2>

				<p class="pl-3">Last login date and time: ${timestamp}</p>

				<button type="button" name="button" class="btn btn-link float-right"
					onclick="logout()">Logout</button>

				<p class="mt-5">
					<button type="button" value="" class="btn btn-link"
						onclick="changeTitle('Update Profile')">
						<a href="UpdateUserProfile.jsp" target="iframeChange">Update my profile</a>
					</button>
				</p>

				<p>
					<button type="button" value="" class="btn btn-link"
						onclick="changeTitle('My Profile')">
						<a href="ViewProfile.jsp" target="iframeChange">View my profile</a>
					</button>
				</p>
			</div>
			<div class="col-lg-6 rounded rightContainer">
				<h4 class="text-center pt-3" id="changeMe">${userName}'s Page</h4>
				<iframe name="iframeChange" class="mt-2 border border-dark"
					src="" width="100%" height="500px"></iframe>
			</div>
		</div>
	</form>


	<footer class="text-center mt-5">
	<p class="font-weight-light footerFont">&copy; Lahvin 2018</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="js/bootstrap.min.js"></script>
</body>
</html>
