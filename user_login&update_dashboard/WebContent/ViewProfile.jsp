<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>UserPage</title>

  <link rel="stylesheet" href="css/bootstrap.min.css" >

  <style media="screen">

    body{
      background-color: #01011c;
    }

    .holdingCell{
      background-color:	#d87b8e;
      margin-top: 150px;
      width:500px;
    }

    .profilePicture{
      position:absolute;
      margin-top: 45px;
      border: 4px #4f0737 dotted;
    }

    .tc{
      color: #d8d8ab;
    }

  </style>
</head>
<body>

<%
		String userName = (String) session.getAttribute("userName");
		String userEmail = (String) session.getAttribute("emailHere");
		String userMobile = (String) session.getAttribute("userMobile");
		String userAddress = (String) session.getAttribute("userAddress");
		String userQual = (String) session.getAttribute("userQualification");
	%>


<div class="row justify-content-center">
    <img class="rounded-circle mx-auto profilePicture" src="ImageControllerServlet?emailAddress=<%=userEmail%>" onerror="this.src='css/img/blank.png'" style="width:200px; height:200px;" alt="">
</div>

<div class="row mt-5 justify-content-center" style="width:100%;">

  <div class="container holdingCell rounded">
      <div class="row pt-5">
        <div class="col text-center">
          <h4 class="tc">Name: <span class="font-italic font-weight-light tc"><%=userName %></span></h4>
        </div>
      </div>

      <div class="row">
        <div class="col text-center">
          <h4 class="tc">Email: <span class="font-italic font-weight-light tc"><%=userEmail %></span></h4>
        </div>
      </div>

      <div class="row">
        <div class="col text-center">
          <h4 class="tc">Mobile: <span class="font-italic font-weight-light tc"><%=userMobile %></span></h4>
        </div>
      </div>

      <div class="row">
        <div class="col text-center">
          <h4 class="tc">Address: <span class="font-italic font-weight-light tc"><%=userAddress %></span></h4>
        </div>
      </div>

      <div class="row">
        <div class="col text-center pb-2">
          <h4 class="tc">Qualification: <span class="font-italic font-weight-light tc"><%=userQual %></span></h4>
        </div>
      </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
