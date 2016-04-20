<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h1><trans:message code="index.wellcome" /></h1>
		<h2><trans:message code="index.simple" /></h2>
		<img src="./images/logo.jpg" class="img-rounded" width="608"
			height="472">

	</div>

	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title"><trans:message code="index.loginfree" /></div>

			</div>
			<div class="panel-body">


				<form method="post" action="./NormalLoginS" id="signupform"
					class="form-horizontal">

					<div class="form-group">
						<label for="email" class="col-md-3 control-label"><trans:message code="index.email" /></label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="email"
							 required>
						</div>
					</div>


					<div class="form-group">
						<label for="password" class="col-md-3 control-label"><trans:message code="index.password" /></label>
						<div class="col-md-9">
							<input type="password" id="pass1" class="form-control"
							
								name="password" required>
						</div>
					</div>

					<c:if test="${not empty errorMessage }">
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								<span style="color: red"> <c:out
										value="${errorMessage}" />
								</span>
							</div>
						</div>
					</c:if>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info">
								<i class="icon-hand-right"></i> <trans:message code="index.login" />
							</button>

							<span style="margin-left: 8px;"><trans:message code="index.donthavereg" /><a
								href="./index"><trans:message code="index.signuphere" /></a></span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>