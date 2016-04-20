<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue History</title>
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

					<form action="./IssueComments" method="get"
						style="display: inline">
						<button class="btn btn-default">Comments</button>
					</form>
					<form action="./IssueWorkLog" method="get" style="display: inline">
						<button class="btn btn-default">Work Log</button>
					</form>
					<form action="./IssueHistory" method="get" style="display: inline">
						<button class="btn btn-primary">History</button>
					</form>
				</div>



				<div id="loginbox" style="margin-top: 50px;"
					class="mainbox col-lg-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

					<div class="panel panel-info">
						<div class="panel-heading">

							<div class="panel-title">All logs for this issue</div>
						</div>
						<div class="panel-body">

							<c:if test="${empty logs }">
								<div class="text-center">
									<h4>No logs for this issue</h4>
								</div>
							</c:if>


							<div class="row">
								<c:forEach var="log" items="${logs}">
									<c:set scope="request" var="fullname"
										value="${log.userFullName}"></c:set>
									<c:set scope="request" var="createdon"
										value="${log.createdOnString}"></c:set>
									<c:set scope="request" var="text" value="${log.action}"></c:set>
									<c:set scope="request" var="userid" value="${log.userId}"></c:set>
								
								<jsp:include page="smallLog.jsp"></jsp:include>
								
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