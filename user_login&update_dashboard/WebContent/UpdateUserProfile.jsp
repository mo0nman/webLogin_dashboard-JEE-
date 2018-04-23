<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="css/bootstrap.min.css">
<style media="screen">
body {
	background-color: black;
}

.container {
	background-color: white;
	width: 50%;
}

.form-control {
	width: 100%;
}
</style>

<script>
function refreshPage(){
	window.location.reload();
}

</script>

<body>

	<%
		String userName = (String) session.getAttribute("emailHere");
	%>

	<div class="container mt-5 text-center pb-2 pt-2 rounded">
		<form class="form-horizontal" action="UpdateParticularsController"
			enctype="multipart/form-data" method="post" onsubmit="refreshPage()"/>
			<h3 class="pb-3 pt-3 text-muted text-center">Update Profile</h3>


			<div class="form-group">
				<label class="col-md-4 control-label" for="ProfilePic">Upload
					Profile Pic</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="file" name="reg_pic"
						value="" placeholder="">${PICnotification}
					<div class="mt-2">
						<input type="submit" name="update" value="Update profile pic!"
							class="rounded btn btn-primary" onclick="this.form.submit()"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Address">Update
					address</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="text" name="reg_address"
						value="" placeholder="Address">${Addnotification}
				</div>
				<div class="mt-2">
					<input type="submit" name="update" value="Update address!"
						class="rounded btn btn-primary" onclick="this.form.submit()"/>
				</div>
			</div>


			<div class="form-group">
				<label class="col-md-4 control-label" for="Qualification">Update
					Qualification</label>
				<div class="col-md-6 mx-auto">
					<select class="form-control input-md" name="reg_qualification">
						<option name="Dip" selected="selected">Diploma</option>
						<option name="Deg">Degree</option>
						<option name="Masters">Masters</option>
						<option name="PHD">Phd</option>
					</select>
				</div>
				<div class="mt-2">
					<input type="submit" name="update" value="Update Qualification!"
						class="rounded btn btn-primary" onclick="this.form.submit()"/>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Certificate">Add
					Certificate</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="file" name="reg_cert"
						value="" placeholder="">${CERTnotification}
				</div>
				<div class="mt-2">
					<input type="submit" name="update" value="Update Certificate!"
						class="rounded btn btn-primary" onclick="this.form.submit()"/>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Mobile">Update
					mobile</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="text" name="reg_mobile"
						value="" placeholder="Mobile">${mobileNotification}
				</div>
				<div class="mt-2">
					<input type="submit" name="update" value="Update mobile!"
						class="rounded btn btn-primary" onclick="this.form.submit()"/>
				</div>
			</div>
		</form>
	</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="js/bootstrap.min.js"></script>
</body>
</html>