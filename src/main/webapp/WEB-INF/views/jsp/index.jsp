<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>

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

		if (pass1 == "" || pass2 == "") {
			alert("One of the password fields is empty");
			document.getElementById("pass1").style.borderColor = "#E34234";
			document.getElementById("pass2").style.borderColor = "#E34234";
			return;
		}

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
	<div class="container">
		<div class="row">
			<div class="span2 offset10">
				<a href="?language=en"> <img src="./ImageServlet?path=en.jpg"
					alt="English" width="30" height="15" border="0">
				</a> <a href="?language=de"> <img src="./ImageServlet?path=de.jpg"
					alt="German" width="30" height="15" border="0">
				</a>
			</div>
		</div>
	</div>


	<div class="text-center">
		<h1>
			<trans:message code="index.wellcome" />
		</h1>
		<h2><trans:message code="index.simple" /></h2>
		<img src="./ImageServlet?path=logo.jpg" class="img-rounded"
			width="608" height="472">

	</div>

	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title"><trans:message code="index.free" /></div>

			</div>
			<div class="panel-body">


				<spring:form method="post" action="./index" commandName="user"
					class="form-horizontal">

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label"><trans:message code="index.fullName" />
						</label>
						<div class="col-md-9">
							<spring:input type="text" class="form-control" path="fullname"
								  required="true" />
						</div>
					</div>

					<c:if test="${not empty user.username }">
						<div class="form-group">
							<label for="firstname" class="col-md-3 control-label"><trans:message code="index.userName" /></label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="username"
									value="<c:out value="${user.username}" />" disabled>
							</div>
						</div>
					</c:if>

					<c:if test="${empty user.username }">
						<div class="form-group">
							<label for="firstname" class="col-md-3 control-label"><trans:message code="index.userName" /></label>
							<div class="col-md-9">
								<spring:input type="text" class="form-control" path="username"
									 required="true" />
							</div>
						</div>
					</c:if>

					<c:if test="${not empty user.email }">
						<div class="form-group">
							<label for="email" class="col-md-3 control-label"><trans:message code="index.email" /></label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="email"
									value="<c:out value="${user.email}" />" disabled>
							</div>
						</div>
					</c:if>


					<c:if test="${empty user.email }">
						<div class="form-group">
							<label for="email" class="col-md-3 control-label"><trans:message code="index.email" /></label>
							<div class="col-md-9">
								<spring:input type="email" class="form-control" path="email"
									 required="true" />
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<label for="password" class="col-md-3 control-label"><trans:message code="index.password" /></label>
						<div class="col-md-9">
							<spring:input type="password" id="pass1" class="form-control"
								 path="password" required="true" />
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="col-md-3 control-label">
							<trans:message code="index.reppassword" /></label>
						<div class="col-md-9">
							<input type="password" id="pass2" class="form-control"
								 name="repPassword"
								 required />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<a class="btn btn-info" onclick="myFunction()"><trans:message code="index.checkpassword" /></a>

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
								<i class="icon-hand-right"></i> <trans:message code="index.signup" />
							</button>

							<span style="margin-left: 8px;"><trans:message code="index.havereg" /><a
								href="./normalLogin"> <trans:message code="index.loginhere" /> </a></span>
						</div>
					</div>
				</spring:form>
			</div>
		</div>
	</div>


</body>
</html>