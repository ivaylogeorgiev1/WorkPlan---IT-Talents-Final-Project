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
			<div class="text-center col-lg-offset-2 col-lg-8 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<h3><trans:message code="index.wellcome" />
				
				<br><trans:message code="home.worklosgandcomments" /></h3>

			</div>


			<div class="row">
				<div id="loginbox" style="margin-top: 50px;"
					class="mainbox col-lg-6 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

					<div class="panel panel-info">
						<div class="panel-heading">

							<div class="panel-title">All work logs for your
								organization</div>
						</div>
						<div class="panel-body">

							<c:if test="${empty sessionScope.worklogs }">
								<div class="text-center">
									<h4>No work logs for this organization</h4>
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
				<div id="loginbox" style="margin-top: 50px;"
					class="mainbox col-lg-6 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

					<div class="panel panel-info">
						<div class="panel-heading">

							<div class="panel-title">All comments for your organization</div>
						</div>
						<div class="panel-body">

							<c:if test="${empty sessionScope.comments }">
								<div class="text-center">
									<h4>No comments for this organization</h4>
								</div>
							</c:if>

							<c:forEach var="commentUserEntry"
								items="${sessionScope.comments}">

								<c:if test="${commentUserEntry.key.isWorkLog==0 }">


									<c:set scope="request" var="fullname"
										value="${commentUserEntry.value.fullname}"></c:set>
									<c:set scope="request" var="createdon"
										value="${commentUserEntry.key.createdOn}"></c:set>
									<c:set scope="request" var="text"
										value="${commentUserEntry.key.text}"></c:set>
									<c:set scope="request" var="userid"
										value="${commentUserEntry.key.userID}"></c:set>
									<jsp:include page="smallComment.jsp"></jsp:include>
								</c:if>


							</c:forEach>



						</div>
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