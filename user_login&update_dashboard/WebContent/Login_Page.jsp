<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet"
	href="css/bootstrap.min.css">

<style media="screen">

@font-face{
  font-family: "Berkshire Swash";
  src: url("css/fonts/BerkshireSwash-Regular.ttf") format("truetype");
}

body {
	background-color: black;
}

.adjustHeader {
	height: 180px;
	width: 100%;
	background-color:pink;
}

.main {
	height: 100%;
	width: 500px;
	margin-top: 80px;
	border-radius: 5px;
}

.form-group {
	width: 350px;
	margin-bottom: 60px;
}

h2 {
	color: white;
	font-family: 'Berkshire Swash', cursive;
}

.footerFont {
	color: white;
	margin-top: 70px;
}

.FPtext {
	color: white;
	margin-left: 182px;
}
</style>

<!-- To prevent user from clicking back -->
<script>
	history.pushState(null, null, '');
	window.addEventListener('popstate', function(event) {
	 history.pushState(null, null, '');
	});
</script>

<title>Login</title>

</head>
<body>

	<header class="adjustHeader">
		<img src="css/img/banner.jpg" style="height:inherit; width:inherit;"/>
	</header>
	
	<div class="main mx-auto">
		<form class="form-group mx-auto" action="LoginServletController"
			method="post">
			<h2 class="text-center pt-5">Sign In</h2>

			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="email" id="inputEmail" name="log_id" class="form-control"
				placeholder="Email" required="" autofocus=""> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" name="pass_id"
				class="form-control" placeholder="Password" required="" autofocus="">

			<button class="btn btn-lg btn-primary btn-block" type="submit"
				name="Login_Submit">Login!</button>
			<p class="pl-2">${lockedAlert}
			<p>
		</form>


		<button type="button"
			class="btn btn-link font-italic font-weight-light text-center FPtext"
			data-toggle="modal" data-target="#passwordRecover">Forgot
			Password?</button>

		<form class="form-group" action="recoveryController" method="post">
			<div id="passwordRecover" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="text-center">Password Recovery</h3>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body mx-auto">
							<input type="email" style="border-radius: 5px;"
								name="recover_user_email"
								placeholder="Enter email for password recovery" required>${rec}
							<button type="submit"
								class="mx-auto btn btn-sm btn-primary btn-block"
								name="recoverP_submit">Submit</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<footer class="text-center">
	<p class="font-weight-light footerFont">&copy; Lahvin 2018</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="js/bootstrap.min.js"></script>
</body>
</html>
