<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Work Plan 1.0</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.min.css" />

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="bootstrap/css/simple-sidebar.css" rel="stylesheet">
<script type="text/javascript">
	function myFunction() {
		var pass1 = document.getElementById("pass1").value;
		var pass2 = document.getElementById("pass2").value;
		var ok = true;
		if (pass1 != pass2) {
			alert("Passwords Do not match");
			document.getElementById("pass1").style.borderColor = "#E34234";
			document.getElementById("pass2").style.borderColor = "#E34234";
			ok = false;
		} else {
			document.getElementById("pass1").style.borderColor = "";
			document.getElementById("pass2").style.borderColor = "";
			alert("Passwords Match!!!");
		}
		return ok;
	}
</script>
</head>
<body>



	<div class="text-center">
		<h1>Welcome to Work Plan</h1>
		<h2>We make multitasking a simple thing !!!</h2>
		<img src="./images/logo.jpg" class="img-rounded" width="608"
			height="472">


	</div>

	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title">Login as a worker user</div>

			</div>
			<div class="panel-body">


				<form method="post" action="./LoginS2" id="signupform"
					class="form-horizontal" role="form">

					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label" >User
							name</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="username"
								placeholder="<c:out value="${user.username}" />" value="<c:out value="${user.username}" />" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-3 control-label" >Email</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="email" value="<c:out value="${user.email}" />"
								placeholder="<c:out value="${user.email}" />" disabled>
						</div>
					</div>
					
					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Full
							name </label>
						<div class="col-md-9" >
							<input type="text" class="form-control" name="fullname"
								 placeholder="Full name" required>
						</div>
					</div>


					<div class="form-group">
						<label for="password" class="col-md-3 control-label">Password</label>
						<div class="col-md-9">
							<input type="password" id="pass1" class="form-control"
								name="password" placeholder="Password" required>
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="col-md-3 control-label">
							Repeat Password</label>
						<div class="col-md-9">
							<input type="password" id="pass2" class="form-control"
								name="repPassword" placeholder=" Repeat Password" required>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<a class="btn btn-info" onclick="myFunction()">Check is
								passwords match</a>

						</div>
					</div>
					
					<c:if test="${not empty errorMessage }">
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								<span style="color: red"> <c:out
										value="${ errorMessage }" />
								</span>
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info">
								<i class="icon-hand-right"></i> Sugn Up
							</button>

							<span style="margin-left: 8px;">Have registration?<a
								href="./login.html"> Log In Here </a></span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>