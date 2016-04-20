<body>
	<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring"
		uri="http://www.springframework.org/tags/form"%>


	<!-- Search Navbar - START -->
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">WorkPlan</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./home">WorkPlan</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><trans:message code="header.dashboards" />
							<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="./home"><trans:message
										code="header.systemboard" /></a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><trans:message code="header.projects" />
							<b class="caret"></b></a>
						<ul class="dropdown-menu">

							<c:forEach var="project" items="${projects}">
								<li><a href="./SelectProject?projectId=${project.id}">${project.name}</a></li>
							</c:forEach>

							<c:if test="${empty projects}">

								<li><a><trans:message code="header.noprojects" /></a></li>


							</c:if>

							<c:if test="${sessionScope.user.admin == 1 }">
								<li class="divider"></li>
								<li><a href="./CreateProjectCon"><trans:message
											code="header.newproject" /></a></li>
								<li class="divider"></li>

							</c:if>
						</ul></li>


					<li><a href="./CreateIssue"><trans:message
								code="header.createissue" /></a></li>

					<c:if test="${sessionScope.user.admin == 1 }">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><trans:message
									code="header.adminsetings" /><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="./ManageUsersS"><trans:message
											code="header.manageusers" /></a></li>
							</ul></li>
					</c:if>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><trans:message code="header.profile" /><b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="./UpdateProfileCon"><trans:message
										code="header.profileupdate" /></a></li>
							<li class="divider"></li>
							<li><a href="./Logout"><trans:message
										code="header.logout" /></a></li>
							<li class="divider"></li>
						</ul></li>

				</ul>

				<div class="col-lg-3 col-sm-3 col-md-3 pull-right">
					<form action="./SearchPage" method="get" class="navbar-form"
						role="Search">
						<div class="input-group">
							<div class="input-group-btn">
								<button class="btn btn-default" type="submit">
									Search <i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</form>
				</div>



			</div>
		</nav>
	</div>
</body>
