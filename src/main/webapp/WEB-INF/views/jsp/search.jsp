<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
input, select, option {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

input, select {
	width: 200px;
	height: 20px;
	border: 1px #ccc solid;
	vertical-align: top;
}

.login-page, h1 {
	text-align: center;
}

.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 50%;
	outline: 0px !important
}

.btn-circle.btn-xl {
	width: 70px;
	height: 70px;
	padding: 10px 16px;
	font-size: 24px;
	line-height: 1.33;
}

.top-buffer {
	margin-top: 20px;
}
</style>
<title>Search issue</title>
<script>
	function searchAll() {
		var input = $("#all").val();
		$.ajax({
			url : './RestAllActivitiesAssigneToUser',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function searchByIssueKey() {
		var input = $("#key").val();
		$.ajax({
			url : './RestKey',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function searchByDescOrSumm() {
		var input = $("#desc").val();
		$.ajax({
			url : './RestDescriptionSummary',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function searchByStatus() {
		var input = $("#status").val();
		$.ajax({
			url : './RestStatus',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function searchByType() {
		var input = $("#type").val();
		$.ajax({
			url : './RestType',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function searchByComment() {
		var input = $("#inputcomment").val();
		$.ajax({
			url : './RestServlet',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function searchByAssignees() {
		var input = $("#assignee").val();
		$.ajax({
			url : './RestAssignee',
			data : input,
			type : 'POST',
			success : function(result) {
				search(result);
			}
		});
	}
	function search(issues) {
		$("#datalist").empty();
		$
				.each(
						issues,
						function(index, issue) {

							$("#datalist")
									.append(
											"<div class='form-group'>"
													+ "<div class='col-lg-12 col-md-12'>"
													+

													"<div class='col-lg-1 col-md-1'>"
													+ "<img src='./ImageServlet?path=types/"
													+ issue.type
													+ ".jpg' alt='Type'"
													+ "class='img-rounded' width='25' height='25' />"
													+ "</div>"
													+

													"<div class='col-lg-1 col-md-1'>"
													+ "<img src='./ImageServlet?path=priority/"
													+ issue.prioriy
													+ ".jpg'"
													+ "alt='${priority}' class='img-rounded' width='25' height='25' />"
													+ "</div>"
													+ "<div class='col-lg-2 col-md-1'>"
													+ "<a href='./Issue?id="
													+ issue.id
													+ "'>"
													+ issue.issueKey
													+ "</a>"
													+ "</div>"
													+ "<div class='col-lg-4 col-md-1'>"
													+ "<div class='text-center'>"
													+ issue.summary
													+ "</div>"
													+ "</div>"
													+

													"<div class='col-lg-2 col-md-1'>"
													+ "<div class='text-center'>"
													+ issue.status
													+ "</div>"
													+ "</div>"
													+

													"<div class='col-lg-1 col-md-1'>"
													+ "<div class='text-center'>"
													+ issue.estimate
													+ "</div>"
													+ "</div>"
													+

													"<div class='col-lg-1 col-md-1'>"
													+ "<img src='./ImageServletFromID?userId="
													+ issue.assigneeID
													+ "'"
													+ "class='img-rounded' width='37' height='37' alt='No assignee' />"
													+ "</div>" +

													"</div>" + "</div>")
						})
	}
</script>
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
				<div class="row">
					<div class="row top-buffer">
						<div class="col-lg-12 col-md-12">
							<div class="col-lg-3 col-md-12">
								<label>Search for comment</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">
										<input onkeyup="searchByComment()" type="text"
											id="inputcomment" />
									</div>
								</div>
							</div>


							<div class="col-lg-3 col-md-12">
								<label>Search for assignee</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">

										<input onkeyup="searchByAssignees()" type="text" id="assignee" />
									</div>
								</div>
							</div>

							<div class="col-lg-3 col-md-12">

								<label>Search for type</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">

										<select onchange="searchByType()" id="type">
											<option></option>
											<option value="Bug">Bug</option>
											<option value="Task">Task</option>
											<option value="Sub-task">Sub-task</option>
											<option value="Story">Story</option>
											<option value="Epic">Epic</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-md-12">

								<label>Search for status</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">

										<select onchange="searchByStatus()" id="status">
											<option></option>
											<option value="ToDo">ToDo</option>
											<option value="InProgress">InProgress</option>
											<option value="Done">Done</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="row top-buffer">
						<div class="col-lg-12 col-md-12">
							<div class="col-lg-3 col-md-12">
								<label>Search for summary</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">
										<input onkeyup="searchByDescOrSumm()" type="text" id="desc" />
									</div>
								</div>
							</div>

							<div class="col-lg-6 col-md-12" align="center">
								<label>Show my Issues</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">
										<button onclick="searchAll()" id="all" type="button"
											class="btn btn-success btn-circle btn-xl">
											<i class="glyphicon glyphicon-list"></i>
										</button>
									</div>
								</div>
							</div>
							<div class="col-lg-3 col-md-12">
								<label>Search for issue key</label>
								<div class="row">
									<div class="col-lg-12 col-md-12">
										<input onkeyup="searchByIssueKey()" type="text" id="key" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<h3>
					<div align="center" id="datalist"></div>
				</h3>
			</div>
		</div>
	</div>
	<!-- /#wrapper -->
</body>
</html>