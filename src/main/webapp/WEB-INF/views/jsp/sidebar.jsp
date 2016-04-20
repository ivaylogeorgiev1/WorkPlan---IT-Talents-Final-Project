<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">

		<div align="center">

			<font color="white"><trans:message code="sidebar.orgname" /></font>

		</div>

		<li class="sidebar-brand"><a href="./home"><c:out
					value="${sessionScope.organization.name}" /> </a></li>



		<c:if test="${sessionScope.user.admin == 1 }">
			<div align="center">

				<font color="white"><trans:message code="sidebar.admin" /></font>

			</div>
		</c:if>
		<c:if test="${sessionScope.user.admin == 0 }">
			<div align="center">

				<font color="white"><trans:message code="sidebar.employee" /></font>

			</div>
		</c:if>
		<div class="text-center">
			<img src="./ImageServlet?path=<c:out value="${user.avatarPath}"/>"
				class="img-rounded" width="100">

		</div>

		<li class="sidebar-brand"><a href="./UpdateProfileCon"><c:out
					value="${user.fullname}" /> </a></li>

		<div align="center">
			<c:if test="${not empty sessionScope.project}">
				<font color="white">Current Project</font>
			</c:if>

		</div>
		<li class="sidebar-brand"><a
			href="./SelectProject?projectId=${project.id}">${project.name}</a></li>



		<c:if test="${not empty sessionScope.activeSprint}">
			<li><a href="./SprintInfo?id=${sessionScope.activeSprint}"><trans:message
						code="sidebar.activesprint" /></a></li>
		</c:if>

		<c:if test="${not empty sessionScope.project}">
			<li><a href="./SelectProject?projectId=${project.id}"><trans:message
						code="sidebar.backlog" /></a></li>
			<li><a href="./AllReports"><trans:message
						code="sidebar.reports" /></a></li>
			<li><a href="./SearchPage"><trans:message
						code="sidebar.issues" /></a></li>
		</c:if>


	</ul>
</div>
<!-- /#sidebar-wrapper -->
