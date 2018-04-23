<%@ page import="com.optimum.dao.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>

<link rel="stylesheet"
	href="css/bootstrap.min.css">
	
	<style media="screen">

body{
	font-family: sans-serif, Roboto;
}

.tableH{
	background-color:#1c2a48;
	color:white;
}
</style>

<script>

	function changeStatus(){
		
	}

</script>
</head>
<body>

	<%
		AccessDatabase AD = new AccessDatabase();
		
		int rowBreak = 1;
		
		ArrayList<String> nameList = AD.returnNames();
		ArrayList<String> emailList = AD.returnEmail();
		ArrayList<String> departmentList = AD.returnDepartment();
		ArrayList<String> statusList = AD.returnStatus();
	%>

	
	<table class="table table-hover">
		<thead class="text-center tableH">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Status</th>
				<th>Select</th>
			</tr>
		<tbody>
			<%
		while(rowBreak < nameList.size()){
		%>
		<form class="" action="StatusChangeController" method="post">
			<tr class="text-center">
				<td><%=nameList.get(rowBreak)%></td>
				<td><%=emailList.get(rowBreak)%><input type="hidden" name="get_email" value="<%=emailList.get(rowBreak)%>"></td>
				<td><%=statusList.get(rowBreak)%></td>
				<td><input type="radio" name="status_change" onclick="this.form.submit()" value="unlock">Unlock <input type="radio" onclick="this.form.submit()" name="status_change" value="lock"> Lock</td>
	    	</tr>
		</form>
			<%
		rowBreak++;
		}
		%>
		</tbody>

		</thead>
	</table>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="js/bootstrap.min.js"></script>
</body>
</html>
