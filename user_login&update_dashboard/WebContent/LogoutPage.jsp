<%@ page language="java" import="java.util.Date"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Logout</title>

<link rel="stylesheet"
	href="css/bootstrap.min.css">


<style media="screen">
@font-face{
	font-family: "Berkshire Swash";
  src: url("css/fonts/BerkshireSwash-Regular.ttf") format("truetype");
}

.adjustHeader {
	height: 180px;
	width: 100%;
	background-color:pink;
}

body {
	background-color: black;
}

.main {
	height: 100%;
	width: 100%;
	margin-top: 180px;
	border-radius: 10px;
}

.BSfont {
	color: white;
	font-family: 'Berkshire Swash', cursive;
}

.footerFont {
	color:white;
	margin-top:70px;
}
</style>

<!-- To prevent user from clicking back -->
<script>
	history.pushState(null, null, '');
	window.addEventListener('popstate', function(event) {
	 history.pushState(null, null, '');
	});
</script>

</head>

<body>
		<% Date createdTime = (Date) session.getAttribute("lastTimestamp"); 
		System.out.println(createdTime);
		   Date currentTime = new Date(session.getLastAccessedTime());
		System.out.println(currentTime);
		   long time = currentTime.getTime() - createdTime.getTime();
		   System.out.println(time);
		   long hours = time/(60*60*1000) % 24;
		   long minutes = time/(60*1000) % 60;
		   long seconds = (time/1000) % 60;
		%>


	<header class="adjustHeader">
		<img src="css/img/banner.jpg" style="height:inherit; width:inherit;"/>
	</header>
	
	<div class="container main">

		<div class="row justify-content-center">
			<h2 class="BSfont">LOGOUT SUCCESSFUL</h2>
		</div>

		<div class="row justify-content-center">
			<p class="font-italic mt-5" style="color: white;">
				You were logged in for:
				<%=hours%> hours,
				<%=minutes%> minutes and
				<%=seconds%> seconds..
			</p>
		</div>

	<%
		session.invalidate();
	%>

		<div class="row justify-content-center mt-5">
			<p class="font-weight-bold">
				<a href="Login_Page.jsp">Back to the login page I guess..</a>
			</p>
		</div>
	</div>
	<footer class="text-center footerFont">
		<p style="color: white;" class="font-weight-light ">&copy;
			Lahvin 2018</p>
	</footer>
</body>
</html>
