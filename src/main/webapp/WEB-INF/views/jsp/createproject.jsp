<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">


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
	<li><a href="?language=en"> <img
			src="./ImageServlet?path=en.jpg" alt="English" width="30" height="15"
			border="0">
	</a> <a href="?language=de"> <img src="./ImageServlet?path=de.jpg"
			alt="German" width="30" height="15" border="0">
	</a></li>



	<div id="wrapper">

		<jsp:include page="sidebar.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-content-wrapper">



			<jsp:include page="header.jsp"></jsp:include>



			<div id="loginbox" style="margin-top: 50px;"
				class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

				<div class="panel panel-info">
					<div class="panel-heading">

						<div class="panel-title">SCRUM Project details</div>

					</div>
					<div class="panel-body">


						<form method="post" action="./CreateProjectServlet"
							class="form-horizontal">

							<div class="form-group">
								<label for="oranization" class="col-md-3 control-label">Project
									name</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="name"
										placeholder="Project name" required />
								</div>
							</div>



							<div class="form-group">
								<label class="col-md-3 control-label">Project key </label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="key"
										placeholder="Project key" required />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">Project leader</label>
								<div class="col-md-9">
									<select name="leader" class="form-control">
										<c:forEach var="employee" items="${employeesInOrg}">
											<option value="${employee.id}">
												<c:if test="${sessionScope.user.id==employee.id}">${employee.fullname}(admin)</c:if>
												<c:if test="${sessionScope.user.id!=employee.id}">${employee.fullname}</c:if></option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="form-group">
								<!-- Button -->
								<div class="col-md-offset-3 col-md-9">
									<button id="btn-signup" type="submit" class="btn btn-info"
										name="action">
										<i class="icon-hand-right"></i> Create Project
									</button>
									<br /> <label style="color: red">${sprintErrorName}</label>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">Workflow for this
									kind of project </label> <label class="col-md-6 control-label">Types
									of issues for this kind of project </label>

							</div>
							<div class="form-group">
								<div class="col-md-6">
									<img src="./ImageServlet?path=workflow.jpg" class="img-rounded"
										width="300">
								</div>
								<div class="col-md-6">
									<img src="./ImageServlet?path=issuetypes.jpg"
										class="img-rounded" width="150">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>

	</div>

	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>
</body>
</html>