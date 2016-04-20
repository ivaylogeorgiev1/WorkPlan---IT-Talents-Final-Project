<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<style type="text/css">
.top-buffer {
	margin-top: 50px;
}
</style>
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
				<div class="top-buffer">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<img src="./ImageServlet?path=Reports/type.jpeg" weight="500px"
								height="500px" />
						</div>
					</div>
				</div>
				<div class="top-buffer">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<img src="./ImageServlet?path=Reports/status.jpeg" weight="500px"
								height="500px" />
						</div>
					</div>
				</div>
				<div class="top-buffer">
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<img src="./ImageServlet?path=Reports/days.jpeg" weight="500px"
								height="500px" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>