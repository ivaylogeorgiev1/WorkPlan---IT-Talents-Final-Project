<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue WorkLog</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.min.css" />

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container" style="width: 900px">
		<div class="row">
			<div class="col-lg-8 col-md-12">

				<!-- button for comment start   -->
				<div style="display: inline">

					<form action="./IssueComments" method="get" style="display: inline">
						<button class="btn btn-default">Comments</button>
					</form>
					<form action="./IssueWorkLog" method="get" style="display: inline">
						<button class="btn btn-primary">Work Log</button>
					</form>
					<form action="./IssueHistory" method="get" style="display: inline">
						<button class="btn btn-default">History</button>
					</form>
				</div>
				<hr />
				<a href="#Foo" class="btn btn-default" data-toggle="collapse">Add
					Work Log</a>
				<div class="row">
					<div id="Foo" class="collapse">
						<div id="loginbox" style="margin-top: 50px;"
							class="mainbox col-lg-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

							<div class="panel panel-info">
								<div class="panel-heading">

									<div class="panel-title">Add work log</div>

								</div>
								<div class="panel-body">



									<form action="./IssueWorkLog" method="POST">
										<div class="form-group">

											<input type="double" name="workHours" class="form-control"
												placeholder="Work hours" />


											<textarea rows="10" cols="80" name="commentContent"
												class="form-control" placeholder="Add your comment here..."></textarea>
										</div>

										<input type="submit" value="Save worklog"
											class="btn btn-warning" />
									</form>



								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div id="loginbox" style="margin-top: 50px;"
						class="mainbox col-lg-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

						<div class="panel panel-info">
							<div class="panel-heading">

								<div class="panel-title">All work logs for this issue</div>
							</div>
							<div class="panel-body">

								<c:if test="${empty sessionScope.worklogs }">
									<div class="text-center">
										<h4>No work logs for this issue</h4>
									</div>
								</c:if>

								<c:forEach var="commentUserEntry"
									items="${sessionScope.worklogs}">



									<c:set scope="request" var="fullname"
										value="${commentUserEntry.value.fullname}"></c:set>
									<c:set scope="request" var="createdon"
										value="${commentUserEntry.key.createdOn}"></c:set>
									<c:set scope="request" var="text"
										value="${commentUserEntry.key.text}"></c:set>
									<c:set scope="request" var="userid"
										value="${commentUserEntry.key.userID}"></c:set>
									<c:set scope="request" var="hours"
										value="${commentUserEntry.key.workHours}"></c:set>
									<c:set scope="request" var="issueid"
										value="${commentUserEntry.key.activityID}"></c:set>



									<jsp:include page="smallWorkLog.jsp"></jsp:include>



								</c:forEach>



							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>