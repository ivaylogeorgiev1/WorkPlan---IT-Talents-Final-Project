<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finished sprints</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.min.css" />

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
			<!-- /#page-content-wrapper -->
			<div class="container" style="width: 1000px">

				<div class="text-center">

					<h3>Finished sprints in this project</h3>


				</div>


				<div>
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<c:forEach var="sprint" items="${finishedSprints}">

								<div id="loginbox" style="margin-top: 20px;"
									class="mainbox col-md-6 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

									<div class="panel panel-info">
										<div class="panel-heading">

											<div class="panel-title">
												<h3>
													<a href="SprintInfo?id=${sprint.id}"> ${sprint.name}</a>
												</h3>
												Finished on: ${sprint.finishedOn }
											</div>


										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>