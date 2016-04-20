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
				class="mainbox col-lg-offset-2 col-lg-9  col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

				<div class="panel panel-info">
					<div class="panel-heading">

						<div class="panel-title">
							<div class="text-center">
								<h2>
									Sprint:
									<c:out value="${sessionScope.sprint.name}">
									</c:out>
								</h2>
							</div>
						</div>
					</div>
					<div class="panel-body">


						<div class="container col-lg-12 col-md-10">

							<c:if test="${sessionScope.activeSprint==sessionScope.sprint.id}">
								<form action="./CompleteServlet" method="get">
									<button name="sprintID" value="${sprint.id}"
										class="btn btn-success">Complete sprint</button>
								</form>
							</c:if>
							<div class="row">
								<div class="col-lg-4 col-md-4">
									<h4>ToDo</h4>


									<c:if test="${empty listWithActivitiesToDoInSprint }">
										<div class="text-center">
											<h4>No issues with this status</h4>
										</div>
									</c:if>
									<c:forEach var="activity"
										items="${sessionScope.listWithActivitiesToDoInSprint}">
										<c:set scope="request" var="type" value="${activity.type}"></c:set>
										<c:set scope="request" var="priority"
											value="${activity.prioriy}"></c:set>
										<c:set scope="request" var="id" value="${activity.id}"></c:set>
										<c:set scope="request" var="issueKey"
											value="${activity.issueKey}"></c:set>
										<c:set scope="request" var="estimate"
											value="${activity.estimate}"></c:set>
										<c:set scope="request" var="assigneeId"
											value="${activity.assigneeID}"></c:set>
										<c:set scope="request" var="summary"
											value="${activity.summary}"></c:set>

										<jsp:include page="smallIssueNoSummary.jsp"></jsp:include>
									</c:forEach>
								</div>
								<div class="col-lg-4 col-md-4">
									<h4>InProgress</h4>
									<c:if test="${empty listWithActivitiesInProgressInSprint }">
										<div class="text-center">
											<h4>No issues with this status</h4>
										</div>
									</c:if>
									<c:forEach var="activity"
										items="${sessionScope.listWithActivitiesInProgressInSprint}">
										<c:set scope="request" var="type" value="${activity.type}"></c:set>
										<c:set scope="request" var="priority"
											value="${activity.prioriy}"></c:set>
										<c:set scope="request" var="id" value="${activity.id}"></c:set>
										<c:set scope="request" var="issueKey"
											value="${activity.issueKey}"></c:set>
										<c:set scope="request" var="estimate"
											value="${activity.estimate}"></c:set>
										<c:set scope="request" var="assigneeId"
											value="${activity.assigneeID}"></c:set>
										<c:set scope="request" var="summary"
											value="${activity.summary}"></c:set>

										<jsp:include page="smallIssueNoSummary.jsp"></jsp:include>
									</c:forEach>
								</div>
								<div class="col-lg-4 col-md-4">
									<h4>Done</h4>

									<c:if test="${empty listWithActivitiesDoneInSprint }">
										<div class="text-center">
											<h4>No issues with this status</h4>
										</div>
									</c:if>
									<c:forEach var="activity"
										items="${sessionScope.listWithActivitiesDoneInSprint}">
										<c:set scope="request" var="type" value="${activity.type}"></c:set>
										<c:set scope="request" var="priority"
											value="${activity.prioriy}"></c:set>
										<c:set scope="request" var="id" value="${activity.id}"></c:set>
										<c:set scope="request" var="issueKey"
											value="${activity.issueKey}"></c:set>
										<c:set scope="request" var="estimate"
											value="${activity.estimate}"></c:set>
										<c:set scope="request" var="assigneeId"
											value="${activity.assigneeID}"></c:set>
										<c:set scope="request" var="summary"
											value="${activity.summary}"></c:set>

										<jsp:include page="smallIssueNoSummary.jsp"></jsp:include>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<c:if test="${empty sessionScope.sprint.finishedOn}">
				<div id="loginbox" style="margin-top: 50px;"
					class="mainbox col-md-6 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

					<div class="panel panel-info">
						<div class="panel-heading">

							<div class="panel-title">All issues not in any sprint</div>
						</div>
						<div class="panel-body">
							<c:if test="${not empty sessionScope.activitiesNotInSprint }">
								<c:forEach var="activity"
									items="${sessionScope.activitiesNotInSprint}">

									<c:set scope="request" var="type" value="${activity.type}"></c:set>
									<c:set scope="request" var="priority"
										value="${activity.prioriy}"></c:set>
									<c:set scope="request" var="summary"
										value="${activity.summary}"></c:set>
									<c:set scope="request" var="id" value="${activity.id}"></c:set>
									<c:set scope="request" var="issueKey"
										value="${activity.issueKey}"></c:set>
									<c:set scope="request" var="estimate"
										value="${activity.estimate}"></c:set>
									<c:set scope="request" var="assigneeId"
										value="${activity.assigneeID}"></c:set>
									<c:set scope="request" var="status" value="${activity.status}"></c:set>

									<jsp:include page="smallIssueWithButton.jsp"></jsp:include>
								</c:forEach>
							</c:if>

							<c:if test="${empty sessionScope.activitiesNotInSprint }">
								<div class="text-center">
									<h4>
										No issues not in Sprint. <a href="./CreateIssue">Create a
											new one ?</a>
									</h4>
								</div>
							</c:if>


						</div>
					</div>
				</div>
			</c:if>

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