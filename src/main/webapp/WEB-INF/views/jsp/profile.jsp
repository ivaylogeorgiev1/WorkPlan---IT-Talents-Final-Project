
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>

	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title"><trans:message code="profile.details" /></div>

			</div>
			<div class="panel-body">


				<form method="post" action="./UpdateProfile"
					class="form-horizontal">

					<div class="form-group">
						<label for="oranization" class="col-md-3 control-label"><trans:message code="profile.details" /></label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="username"
								value="<c:out value="${organization.name}" />" disabled>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label"><trans:message code="profile.fullname" /></label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="fullname"
								value="<c:out value="${user.fullname}"/>">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label"><trans:message code="profile.username" /></label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="username"
								value="<c:out value="${user.username}"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><trans:message code="profile.email" /></label>
						<div class="col-md-9">
							<input type="email" class="form-control" name="email"
								value="<c:out value="${user.email}"/>">
						</div>
					</div>


					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info"
								name="action" value="details">
								<i class="icon-hand-right"></i><trans:message code="profile.update" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<c:if test="${not empty errorMessage }">
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<span style="color: red"> <c:out value="${errorMessage}" />
				</span>
			</div>
		</div>
	</c:if>

	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title"><trans:message code="profile.updatepass" /></div>

			</div>
			<div class="panel-body">


				<form method="post" action="./UpdateProfile" id="signupform"
					class="form-horizontal">




					<div class="form-group">
						<label for="password" class="col-md-3 control-label"><trans:message code="profile.oldpass" /></label>
						<div class="col-md-9">
							<input type="password" class="form-control" name="oldPassword"
								placeholder="Old Password" required>
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="col-md-3 control-label">
							<trans:message code="profile.newpass" /></label>
						<div class="col-md-9">
							<input type="password" class="form-control" name="newPassword"
								placeholder=" New Password" required>
						</div>
					</div>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info"
								name="action" value="password">
								<i class="icon-hand-right"></i> <trans:message code="profile.updatepassbutton" />
							</button>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title"><trans:message code="profile.avatar" /></div>

			</div>
			<div class="panel-body">


				<form method="post" action="./UpdateProfile" id="signupform"
					class="form-horizontal" role="form" enctype="multipart/form-data">
					<div class="form-group">
					
						<label for="password" class="col-md-3 control-label">
							<trans:message code="profile.currentavatar" /> </label>
						<div class="col-md-9">
							<div class="text-center">
								<img src="./ImageServlet?path=<c:out value="${user.avatarPath}"/>"
									class="img-rounded" width="100">

							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="avatars" class="col-md-3 control-label"><trans:message code="profile.deftavatar" /></label>
						<div class="col-md-9">

							<div class="col-md-1">
								<h6>1</h6>
								<img src="./images/avatars/Avatar1.jpg" class="img-rounded"
									width="45" height="45">
							</div>
							<div class="col-md-1">
								<h6>2</h6>
								<img src="./images/avatars/Avatar2.jpg" class="img-rounded"
									width="45" height="45">
							</div>
							<div class="col-md-1">
								<h6>3</h6>
								<img src="./images/avatars/Avatar3.jpg" class="img-rounded"
									width="45" height="45">
							</div>

							<div class="col-md-1">
								<h6>4</h6>
								<img src="./images/avatars/Avatar4.jpg" class="img-rounded"
									width="45" height="45">
							</div>
							<div class="col-md-1">
								<h6>5</h6>
								<img src="./images/avatars/Avatar5.jpg" class="img-rounded"
									width="45" height="45">
							</div>
							<div class="col-md-1">
								<h6>6</h6>
								<img src="./images/avatars/Avatar6.jpg" class="img-rounded"
									width="45" height="45">
							</div>
						</div>
						<div class="col-md-9">
							<label for="label" class="col-md-3 control-label"><trans:message code="profile.elavatar" /></label>

							<div class="col-md-9">
								<select class="form-control" id="sel1" name="avatarID">
									<option value="0"></option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
								</select>
							</div>

						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-3 control-label"><trans:message code="profile.uploadavatar" /></label>
						<div class="col-md-9">
							<input type="file" class="form-control" name="avatar">
						</div>
					</div>



					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info"
								name="action" value="avatar">
								<i class="icon-hand-right"></i><trans:message code="profile.continue" />
							</button>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>