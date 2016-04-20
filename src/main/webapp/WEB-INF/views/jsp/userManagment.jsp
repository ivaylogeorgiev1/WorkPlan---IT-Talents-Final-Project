<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>

<div id="loginbox" style="margin-top: 50px;"
	class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

	<div class="panel panel-info">
		<div class="panel-heading">

			<div class="panel-title">Create new user</div>

		</div>
		<div class="panel-body">
			<form method="post" action="./SendMailS" class="form-horizontal"
				role="form">
				<c:if test="${not empty errorMessage }">
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<span style="color: red"> <c:out value="${ errorMessage }" />
							</span>
						</div>
					</div>
				</c:if>

				<div class="form-group">
					<div class="col-md-6">
						<input type="text" class="form-control" name="username"
							placeholder="Username" required>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" name="email"
							placeholder="E-mail" required>
					</div>
				</div>


				<div class="form-group">
					<!-- Button -->
					<div class="col-md-offset-3 col-md-9">
						<button id="btn-signup" type="submit" class="btn btn-info">
							<i class="icon-hand-right"></i> Create new user
						</button>


					</div>
				</div>
			</form>


		</div>
	</div>

</div>
<div id="loginbox" style="margin-top: 50px;"
	class="mainbox col-md-6 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

	<div class="panel panel-info">
		<div class="panel-heading">

			<div class="panel-title">All currtent users for your
				organization</div>

		</div>
		<div class="panel-body">

			<div class="form-group">

				<div class="form-group">
					<div class="col-md-12">

						<label class="col-xl-6 col-md-6 control-label"> User name:
							
						</label> <label class="col-xl-6 col-md-6 control-label"> E-mail: 
						</label>
					</div>
				</div>
				<c:forEach var="employee" items="${usersForOrg}">

					<c:set scope="request" var="username" value="${employee.username}"></c:set>
					<c:set scope="request" var="email" value="${employee.email}"></c:set>

					<jsp:include page="smallUser.jsp"></jsp:include>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
