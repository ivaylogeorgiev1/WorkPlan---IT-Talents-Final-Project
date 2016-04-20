


	<div class="container" style="width: 1000px">
		<div class="row">
			<div class="row">
				<h4>
					<a href="./SelectProject?projectId=${activity.projectID}">${projecName}</a>/<a
						href="./Issue?id=${activity.id}">${activity.issueKey}</a>
				</h4>
			</div>
			<div class="row" align="center">
				<h3>${activity.summary}</h3>
			</div>
			<div class="col-lg-8 col-md-6">

				<div class="col-lg-12 col-md-12">
					<form action="./StatusServlet" method="get" style="display: inline">
						<button class="btn btn-default" value="ToDo" name="status">To
							Do</button>
					</form>
					<form action="./StatusServlet" method="get" style="display: inline">
						<button class="btn btn-default" value="InProgress" name="status">In
							Progress</button>
					</form>
					<form action="./StatusServlet" method="get" style="display: inline">
						<button class="btn btn-default" value="Done" name="status">Done</button>
					</form>

					
					<div class="row">
						<!-- Horizontal  rule  start-->


						<div style="width: 100%; text-align: center;">
							<span style="width: 45%; display: inline; float: left;"><hr /></span>
							<span style="display: inline; float: center;"><b>Details</b></span>
							<span style="width: 45%; display: inline; float: right;"><hr /></span>
						</div>

						<!-- Horizontal  rule  end-->
						<div class="row">
							<div class="col-lg-3 col-md-6" align="left">Type of Issue:</div>
							<div class="col-lg-3 col-md-6" align="left">${activity.type}</div>
							<div class="col-lg-3 col-md-6" align="left">Status:</div>
							<div class="col-lg-3 col-md-6" align="left">${activity.status}</div>
						</div>
						<div class="row">
							<div class="col-lg-3 col-md-6" align="left">Estimate in
								hours:</div>
							<div class="col-lg-3 col-md-6" align="left">${activity.estimate}</div>
							<div class="col-lg-3 col-md-6" align="left">In Sprint:</div>
							<div class="col-lg-3 col-md-6" align="left">
								<c:if test="${activity.sprintID!=0&& not empty sprintName}">
									<a href="./SprintInfo?id=${activity.sprintID}">${sprintName}</a>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-3 col-md-6" align="left">Priority</div>
							<div class="col-lg-3 col-md-6" align="left">${priorityName}</div>
						</div>
						<div class="row">
							<div class="col-lg-3 col-md-6" align="left">Linked Issue:</div>
							<div class="col-lg-3 col-md-6" align="left">
								<c:if
									test="${activity.connectedToID!=0&& not empty connectedKey}">
									<a href="./Issue?id=${activity.connectedToID}">${connectedKey}</a>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-3 col-md-6" align="left">Link type:</div>
							<div class="col-lg-3 col-md-6" align="left">${activity.connectedType}</div>
						</div>
					</div>
					<div class="row">
						<!-- Horizontal  rule  start-->
						<div style="width: 100%; text-align: center;">
							<span style="width: 42%; display: inline; float: left;"><hr /></span>
							<span style="display: inline; float: center;"><b>Description</b></span>
							<span style="width: 42%; display: inline; float: right;"><hr /></span>
						</div>
						<!-- Horizontal  rule  end-->

					</div>

					<div class="row">
						<div style="width: 100%; text-align: center;">
							${sessionScope.activity.description}</div>
					</div>

				</div>
			</div>
			<div class="col-lg-4 col-md-6" align="right">
				<div class="col-lg-12 col-md-12">
				<form action="./UpdateIssue" method="get" style="display: inline">
						<button class="btn btn-default" name="issueId"
							value="${activity.id}">Edit</button>
					</form>
					
					<div class="row">
						<!-- Horizontal  rule  start-->
						<div style="width: 100%; text-align: center;">
							<span style="width: 100px; display: inline; float: left;"><hr /></span>
							<span style="display: inline; float: center;"><b>People</b></span>
							<span style="width: 100px; display: inline; float: right;"><hr /></span>
						</div>
						<!-- Horizontal  rule  end-->
						<div class="row">
							<div class="col-lg-6 col-md-6" align="left">Assignee:</div>
							<div class="col-lg-6 col-md-6" align="left">${assignee}</div>
						</div>
						<div class="row">
							<div class="col-lg-6 col-md-6" align="left">Reporter:</div>
							<div class="col-lg-6 col-md-6" align="left">${reporter}</div>
						</div>

						<div class="row">
							<!-- Horizontal  rule  start-->
							<div style="width: 100%; text-align: center;">
								<span style="width: 110px; display: inline; float: left;"><hr /></span>
								<span style="display: inline; float: center;"><b>Date
										Created</b></span> <span
									style="width: 110px; display: inline; float: right;"><hr /></span>
							</div>
							<!-- Horizontal  rule  end-->
						</div>

						<div class="row">
							<div style="width: 100%; text-align: center;">
								${activity.createdOnString}</div>
						</div>
						<div class="row">
							<!-- Horizontal  rule  start-->
							<div style="width: 100%; text-align: center;">
								<span style="width: 100px; display: inline; float: left;"><hr /></span>
								<span style="display: inline; float: center;"><b>Date
										Finished</b></span> <span
									style="width: 100px; display: inline; float: right;"><hr /></span>
							</div>

							<!-- Horizontal  rule  end-->
						</div>
						<div class="row">
							<div style="width: 100%; text-align: center;">
								${activity.finishedOnString}</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /#page-content-wrapper -->

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


