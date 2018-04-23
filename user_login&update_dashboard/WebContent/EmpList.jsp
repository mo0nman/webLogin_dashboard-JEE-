<%@ page import="com.optimum.dao.*, java.util.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>

<link rel="stylesheet" href="css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<style media="screen">
body {
	font-family: sans-serif, Roboto;
}

.tableH {
	background-color: #1c2a48;
	color: white;
}
</style>

<script>
		$(document).ready(function() {
			$('#table_id').DataTable({
				"aLengthMenu" : [ [ 5, 10, 15, -1 ], [ 5, 10, 15, "All" ] ]
			});
		});
</script>


</head>
<body>

	<%
		AccessDatabase AD = new AccessDatabase();

		int rowBreak = 0;

		ArrayList<String> nameList = AD.returnNames();
		ArrayList<String> nricList = AD.returnNRIC();
		ArrayList<String> emailList = AD.returnEmail();
		ArrayList<String> departmentList = AD.returnDepartment();
		ArrayList<String> statusList = AD.returnStatus();
	%>

	<table class="table table-hover" style="" id="table_id">
		<thead class="text-center tableH">
			<tr>
				<th>Name</th>
				<th>NRIC</th>
				<th>Email</th>
				<th>Department</th>
				<th>Status</th>
			</tr>
		</thead>

		<tbody>
			<%
				while (rowBreak < nameList.size()) {
			%>
			<tr class="text-center">
				<td><%=nameList.get(rowBreak)%></td>
				<td><%=nricList.get(rowBreak)%></td>
				<td><%=emailList.get(rowBreak)%></td>
				<td><%=departmentList.get(rowBreak)%></td>
				<td><%=statusList.get(rowBreak)%></td>
			</tr>

			<%
				rowBreak++;
				}
			%>
		</tbody>

	</table>
</body>
</html>