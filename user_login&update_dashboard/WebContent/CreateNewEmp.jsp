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
<body>

	<div class="container mt-5 text-center pb-2 rounded">
		<form class="form-horizontal" action="RegistrationController"
			enctype="multipart/form-data" method="post">
			<h3 class="pb-3 pt-3 text-muted text-center">User Registration</h3>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Name">Enter name</label>
				<div class="col-md-6 mx-auto">
					<input id="Name" name="reg_name" type="text" placeholder="Name"
						class="form-control input-md" required>${NAMEnotification}
				</div>
			</div>

			 <div class="form-group">
				<label class="col-md-4 control-label" for="NRIC">Enter NRIC</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="text" name="reg_nric"
						value="" placeholder="NRIC" required>${NRICnotification}
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Gender">Select gender</label>
				<div class="col-md-6 mx-auto">
					<input type="radio" name="reg_gender" value="M"> M 
					<input type="radio" name="reg_gender" value="F"> F
					${Gendernotification}
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="DOB">Enter date
					of birth</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="text" name="reg_dob"
						value="" placeholder="DOB" required>${DOBnotification}
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Address">Enter
					address</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="text" name="reg_address"
						value="" placeholder="Address" required>${Addnotification}
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Country"> Select
					country</label>
				<div class="col-md-6 mx-auto">
					<select class="form-control input-md" name="reg_country">
						<option name="Argentina">Argentina</option>
						<option name="Australia">Australia</option>
						<option name="Austria">Austria</option>
						<option name="Bangladesh">Bangladesh</option>
						<option name="Belgium">Belgium</option>
						<option name="Brazil">Brazil</option>
						<option name="Brunei Darussalam">Brunei Darussalam</option>
						<option name="Cambodia">Cambodia</option>
						<option name="Canada">Canada</option>
						<option name="Chile">Chile</option>
						<option name="China">China</option>
						<option name="Colombia">Colombia</option>
						<option name="Costa Rica">Costa Rica</option>
						<option name="Cuba">Cuba</option>
						<option name="Cyprus">Cyprus</option>
						<option name="Czech Republic">Czech Republic</option>
						<option name="Denmark">Denmark</option>
						<option name="Dominica">Dominica</option>
						<option name="Dominican Republic">Dominican Republic</option>
						<option name="Ecuador">Ecuador</option>
						<option name="Egypt">Egypt</option>
						<option name="Estonia">Estonia</option>
						<option name="Ethiopia">Ethiopia</option>
						<option name="Faroe Islands">Faroe Islands</option>
						<option name="Fiji">Fiji</option>
						<option name="Finland">Finland</option>
						<option name="France">France</option>
						<option name="French Polynesia">French Polynesia</option>
						<option name="Gabon">Gabon</option>
						<option name="Gambia">Gambia</option>
						<option name="Germany">Germany</option>
						<option name="Greece">Greece</option>
						<option name="Greenland">Greenland</option>
						<option name="Honduras">Honduras</option>
						<option name="Hong Kong">Hong Kong</option>
						<option name="Hungary">Hungary</option>
						<option name="Iceland">Iceland</option>
						<option name="India">India</option>
						<option name="Indonesia">Indonesia</option>
						<option name="Iran">Iran (Islamic Republic of)</option>
						<option name="Iraq">Iraq</option>
						<option name="Ireland">Ireland</option>
						<option name="Israel">Israel</option>
						<option name="Italy">Italy</option>
						<option name="Jamaica">Jamaica</option>
						<option name="Japan">Japan</option>
						<option name="Kazakhstan">Kazakhstan</option>
						<option name="Kenya">Kenya</option>
						<option name="Korea">Korea, Republic of</option>
						<option name="Kuwait">Kuwait</option>
						<option name="Latvia">Latvia</option>
						<option name="Lebanon">Lebanon</option>
						<option name="Lesotho">Lesotho</option>
						<option name="Liberia">Liberia</option>
						<option name="Liechtenstein">Liechtenstein</option>
						<option name="Lithuania">Lithuania</option>
						<option name="Luxembourg">Luxembourg</option>
						<option name="Macau">Macau</option>
						<option name="Macedonia">Macedonia, The Former Yugoslav
							Republic of</option>
						<option name="Madagascar">Madagascar</option>
						<option name="Malaysia">Malaysia</option>
						<option name="Maldives">Maldives</option>
						<option name="Mexico">Mexico</option>
						<option name="Micronesia">Micronesia, Federated States of</option>
						<option name="Moldova">Moldova, Republic of</option>
						<option name="Monaco">Monaco</option>
						<option name="Mongolia">Mongolia</option>
						<option name="Morocco">Morocco</option>
						<option name="Mozambique">Mozambique</option>
						<option name="Myanmar">Myanmar</option>
						<option name="Nepal">Nepal</option>
						<option name="Netherlands">Netherlands</option>
						<option name="Netherlands Antilles">Netherlands Antilles</option>
						<option name="New Caledonia">New Caledonia</option>
						<option name="New Zealand">New Zealand</option>
						<option name="Nigeria">Nigeria</option>
						<option name="Norway">Norway</option>
						<option name="Pakistan">Pakistan</option>
						<option name="Paraguay">Paraguay</option>
						<option name="Peru">Peru</option>
						<option name="Philippines">Philippines</option>
						<option name="Pitcairn">Pitcairn</option>
						<option name="Poland">Poland</option>
						<option name="Portugal">Portugal</option>
						<option name="Puerto Rico">Puerto Rico</option>
						<option name="Qatar">Qatar</option>
						<option name="Reunion">Reunion</option>
						<option name="Romania">Romania</option>
						<option name="Russia">Russian Federation</option>
						<option name="Rwanda">Rwanda</option>
						<option name="Saudi Arabia">Saudi Arabia</option>
						<option name="Senegal">Senegal</option>
						<option name="Seychelles">Seychelles</option>
						<option name="Sierra">Sierra Leone</option>
						<option name="Singapore" selected="selected">Singapore</option>
						<option name="Slovakia">Slovakia (Slovak Republic)</option>
						<option name="Slovenia">Slovenia</option>
						<option name="Solomon Islands">Solomon Islands</option>
						<option name="Somalia">Somalia</option>
						<option name="South Africa">South Africa</option>
						<option name="Spain">Spain</option>
						<option name="Sri Lanka">Sri Lanka</option>
						<option name="Swaziland">Swaziland</option>
						<option name="Sweden">Sweden</option>
						<option name="Switzerland">Switzerland</option>
						<option name="Syria">Syrian Arab Republic</option>
						<option name="Taiwan">Taiwan, Province of China</option>
						<option name="Tajikistan">Tajikistan</option>
						<option name="Thailand">Thailand</option>
						<option name="Togo">Togo</option>
						<option name="Tonga">Tonga</option>
						<option name="Trinidad and Tobago">Trinidad and Tobago</option>
						<option name="Tunisia">Tunisia</option>
						<option name="Turkey">Turkey</option>
						<option name="Turkmenistan">Turkmenistan</option>
						<option name="Ukraine">Ukraine</option>
						<option name="United Arab Emirates">United Arab Emirates</option>
						<option name="United Kingdom">United Kingdom</option>
						<option name="United States">United States</option>
						<option name="Uruguay">Uruguay</option>
						<option name="Uzbekistan">Uzbekistan</option>
						<option name="Venezuela">Venezuela</option>
						<option name="Vietnam">Viet Nam</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Qualification">Select
					Qualification</label>
				<div class="col-md-6 mx-auto">
					<select class="form-control input-md" name="reg_qualification">
						<option name="Dip" selected="selected">Diploma</option>
						<option name="Deg">Degree</option>
						<option name="Masters">Masters</option>
						<option name="PHD">Phd</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Certificate">Certificate</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="file" name="reg_cert"
						value="" placeholder="">${CERTnotification}
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Department">Select
					department</label>
				<div class="col-md-6 mx-auto">
					<select class="form-control input-md" name="reg_department">
						<option name="IT">IT</option>
						<option name="Finance" selected="selected">Finance</option>
						<option name="HR">HR</option>
						<option name="Production">Production</option>
						<option name="RandD">Research and Development</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Email">Enter
					email address</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="email" name="reg_email"
						value="" placeholder="Email" required>${emailNotification}
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="Mobile">Enter
					mobile</label>
				<div class="col-md-6 mx-auto">
					<input class="form-control input-md" type="text" name="reg_mobile"
						value="" placeholder="Mobile" required>${mobileNotification}
				</div>
			</div>

			<input type="submit" name="" value="Register!"
				class="rounded btn btn-primary">
		</form>
	</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="js/bootstrap.min.js"></script>
</body>
</html>