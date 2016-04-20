<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>


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






			<div class="text-center">

				<div class="panel-title">SCRUM Project ${project.name} backlog</div>

			</div>

			<div class="container" class="col-lg-6 col-md-12">

				<div class="row" align="center">

					<div id="sprint" class="collapse">
						<div class="col-lg-3 col-md-12">
							<label>Sprint name</label>
						</div>
						<div class="col-lg-2 col-md-12">
							<form style="display: inline" action="./CreateSprint"
								method="get">
								<input type="text" name="sprintName" required /> <input
									type="submit" class="btn btn-primary" value="Create Sprint" />
							</form>
							
						</div>
					</div>
				</div>
				<div class="row" align="center">
					<button href="#sprint" class="btn btn-primary"
						data-toggle="collapse">New Sprint</button>
						<br /> <label style="color: red">${sprintErrorName}</label>
				</div>
			</div>


			<c:forEach var="entry" items="${activitiesBySprint}">

				<div id="loginbox" style="margin-top: 50px;"
					class="mainbox col-md-6 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

					<div class="panel panel-info">
						<div class="panel-heading">

							<div class="panel-title">
								<a href="./SprintInfo?id=${entry.key.id}">Sprint:${entry.key.name}
								</a>
							</div>
						</div>
						<div class="panel-body">

							<c:if test="${empty entry.value }">
								<div class="text-center">
									<h4>No issues in this Sprint.</h4>
								</div>
							</c:if>
							<c:forEach var="activity" items="${entry.value}">

								<c:set scope="request" var="type" value="${activity.type}"></c:set>
								<c:set scope="request" var="priority"
									value="${activity.prioriy}"></c:set>
								<c:set scope="request" var="summary" value="${activity.summary}"></c:set>
								<c:set scope="request" var="id" value="${activity.id}"></c:set>
								<c:set scope="request" var="issueKey"
									value="${activity.issueKey}"></c:set>
								<c:set scope="request" var="estimate"
									value="${activity.estimate}"></c:set>
								<c:set scope="request" var="status" value="${activity.status}"></c:set>

								<c:set scope="request" var="assigneeId"
									value="${activity.assigneeID}"></c:set>

								<jsp:include page="smallIssue.jsp"></jsp:include>
							</c:forEach>

							<c:if test="${sessionScope.activeSprint==entry.key.id}">
								<a href="./SprintInfo?id=${entry.key.id}"
									class="btn btn-warning">Running </a>
							</c:if>
							<c:if test="${empty sessionScope.activeSprint}">
								<a href="./SprintInfo?id=${entry.key.id}&active=true"
									class="btn btn-primary">Start Sprint </a>
							</c:if>


						</div>
					</div>
				</div>
			</c:forEach>

			<div id="loginbox" style="margin-top: 50px;"
				class="mainbox col-md-6 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

				<div class="panel panel-info">
					<div class="panel-heading">

						<div class="panel-title">All issues not in any sprint</div>
					</div>
					<div class="panel-body">

						<c:if test="${empty sessionScope.activitiesNotInSprint }">
							<div class="text-center">
								<h4>
									No issues not in Sprint. <a href="./CreateIssue">Create a
										new one ?</a>
								</h4>
							</div>
						</c:if>


						<c:if test="${not empty sessionScope.activitiesNotInSprint }">
							<c:forEach var="activity"
								items="${sessionScope.activitiesNotInSprint}">

								<c:set scope="request" var="type" value="${activity.type}"></c:set>
								<c:set scope="request" var="priority"
									value="${activity.prioriy}"></c:set>
								<c:set scope="request" var="summary" value="${activity.summary}"></c:set>
								<c:set scope="request" var="id" value="${activity.id}"></c:set>
								<c:set scope="request" var="issueKey"
									value="${activity.issueKey}"></c:set>
								<c:set scope="request" var="estimate"
									value="${activity.estimate}"></c:set>
								<c:set scope="request" var="assigneeId"
									value="${activity.assigneeID}"></c:set>
								<c:set scope="request" var="status" value="${activity.status}"></c:set>
								
								

								<jsp:include page="smallIssue.jsp"></jsp:include>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div>

					<a href="./FinishedSprints" class="btn btn-primary">Finished Sprints </a>

				</div>
			</div>


		</div>
	</div>
</body>
</html>