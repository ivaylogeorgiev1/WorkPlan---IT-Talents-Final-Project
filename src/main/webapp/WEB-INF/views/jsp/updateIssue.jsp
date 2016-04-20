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

						<div class="panel-title">Issue details</div>

					</div>
					<div class="panel-body">


						<spring:form method="post" action="./UpdateIssueInDB"
							class="form-horizontal" commandName="issue">

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Select
									project </label>
								<div class="col-lg-3 col-md-12">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"> <c:if test="${not empty project }">
							${project.name}
							</c:if> <b class="caret"></b></a>
										<ul class="dropdown-menu">
											<c:forEach var="project2" items="${projects}">
												<c:if test="${project.id!=project2.id }">
													<li><a
														href="./SelectProjectForUpdateIssue?projectId=${project2.id}">${project2.name}</a></li>
												</c:if>
											</c:forEach>
										</ul></li>
								</div>
							</div>


							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Issue
									type </label>
								<div class="col-md-9">
									<spring:select name="type" path="type" class="form-control">
										<option value="${issue.type}">${issue.type}</option>


										<c:if test="${issue.type != 'Task'}">
											<option value="Task">Task</option>
										</c:if>
										<c:if test="${issue.type != 'Sub-Task'}">
											<option value="Sub-Task">Sub-Task</option>
										</c:if>
										<c:if test="${issue.type != 'Bug'}">
											<option value="Bug">Bug</option>
										</c:if>
										<c:if test="${issue.type != 'Story'}">
											<option value="Story">Story</option>
										</c:if>
										<c:if test="${issue.type != 'Epic'}">
											<option value="Epic">Epic</option>
										</c:if>






									</spring:select>
								</div>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Estimate
									in hours </label>
								<div class="col-md-9">
									<spring:input type="double" class="form-control"
										path="estimate" />
								</div>
							</div>

							<div class="form-group">
								<label for="summary" class="form-control"
									class="col-md-3 control-label"> Summary </label>

								<spring:textarea name="summary" path="summary"
									class="form-control" rows="5" cols="15" required="true"></spring:textarea>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Reporter
								</label>
								<div class="col-md-9">
									<spring:select name="reporter" path="reportedID"
										class="form-control">

										<c:forEach var="userCurrent" items="${usersByOrg}">
											<c:if test="${userCurrent.id==issue.reportedID }">
												<option value="${userCurrent.id}">${userCurrent.username}</option>
											</c:if>
										</c:forEach>


										<c:forEach var="user2" items="${usersByOrg}">
											<c:if test="${issue.reportedID!=user2.id }">
												<option value="${user2.id}">${user2.username}</option>
											</c:if>
										</c:forEach>
									</spring:select>
								</div>
							</div>

							<div class="form-group">
								<label for="summary" class="form-control"
									class="col-md-3 control-label"> Description </label>

								<spring:textarea name="description" path="description"
									class="form-control" rows="5" cols="15"></spring:textarea>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Priority
								</label>
								<div class="col-md-9">
									<spring:select name="priority" path="prioriy"
										class="form-control">

										<option value="${issue.prioriy}">${priorityName}</option>

										<c:if test="${issue.prioriy != 3}">
											<option value="3">Normal</option>
										</c:if>

										<c:if test="${issue.prioriy != 4}">
											<option value="4">High</option>
										</c:if>

										<c:if test="${issue.prioriy != 5}">
											<option value="5">Highest</option>
										</c:if>

										<c:if test="${issue.prioriy != 2}">
											<option value="2">Low</option>
										</c:if>

										<c:if test="${issue.prioriy != 1}">
											<option value="1">Lowest</option>
										</c:if>

									</spring:select>
								</div>
							</div>


							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Type
									of the link for the linked issue </label>
								<div class="col-md-9">
									<spring:select name="linktype" path="connectedType"
										class="form-control">

										<c:if test="${not empty issue.connectedType}">
											<option value="${issue.connectedType}">${issue.connectedType}</option>
										</c:if>

										<option value="N/A"></option>


										<c:if test="${issue.connectedType != 'Blocks'}">
											<option value="Blocks">Blocks</option>
										</c:if>

										<c:if test="${issue.connectedType != 'Is blocked by'}">
											<option value="Is blocked by">Is blocked by</option>
										</c:if>

										<c:if test="${issue.connectedType != 'Duplicates'}">
											<option value="Duplicates">Duplicates</option>
										</c:if>

										<c:if test="${issue.connectedType != 'Is duplicated by'}">
											<option value="Is duplicated by">Is duplicated by</option>
										</c:if>

										<c:if test="${issue.connectedType != 'Caused by'}">
											<option value="Caused by">Caused by</option>
										</c:if>

										<c:if test="${issue.connectedType != 'Relates to'}">
											<option value="Relates to">Relates to</option>
										</c:if>




									</spring:select>
								</div>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Linked
									issue </label>
								<div class="col-md-9">
									<spring:select name="assignee" path="connectedToID"
										class="form-control">

										<c:if test="${not empty issue.connectedToID}">
											<c:forEach var="issueFor" items="${issuesForProject}">
												<c:if test="${issue.connectedToID==issueFor.id}">
													<option value="${issueFor.id}">${issueFor.issueKey}
														${issueFor.summary}</option>
												</c:if>
											</c:forEach>
										</c:if>


										<option value="0"></option>


										<c:forEach var="issueFor" items="${issuesForProject}">
											<c:if test="${issue.connectedToID != issueFor.id}">
												<option value="${issueFor.id}">${issueFor.issueKey}
													${issueFor.summary}</option>
											</c:if>
										</c:forEach>



									</spring:select>
								</div>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Assignee
								</label>
								<div class="col-md-9">

									<spring:select name="assignee" path="assigneeID"
										class="form-control">

										<c:if test="${not empty issue.assigneeID}">
											<c:forEach var="user2" items="${usersByOrg}">
												<c:if test="${issue.assigneeID==user2.id }">
													<option value="${user2.id}">${user2.username}</option>
												</c:if>
											</c:forEach>
										</c:if>

										<option value="0"></option>

										<c:forEach var="user2" items="${usersByOrg}">
											<c:if test="${issue.assigneeID!=user2.id }">
												<option value="${user2.id}">${user2.username}</option>
											</c:if>
										</c:forEach>

									</spring:select>
								</div>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label">Sprint
								</label>
								<div class="col-md-9">
									<spring:select name="sprintId" path="sprintID"
										class="form-control">


										<c:if test="${not empty issue.sprintID}">
											<c:forEach var="sprint" items="${sprintsForProject}">
												<c:if test="${issue.sprintID==sprint.id}">
													<option value="${sprint.id}">${sprint.name}</option>
												</c:if>
											</c:forEach>
										</c:if>



										<option value="0"></option>



										<c:forEach var="sprint" items="${sprintsForProject}">
											<c:if test="${issue.sprintID!=sprint.id}">
												<option value="${sprint.id}">${sprint.name}</option>
											</c:if>
										</c:forEach>
									</spring:select>
								</div>
							</div>


							<div class="form-group">
								<!-- Button -->
								<div class="col-md-offset-3 col-md-9">
									<button id="btn-signup" type="submit" class="btn btn-info">
										<i class="icon-hand-right"></i> Update issue
									</button>

								</div>
							</div>
						</spring:form>
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