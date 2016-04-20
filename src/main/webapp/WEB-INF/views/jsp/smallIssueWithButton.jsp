<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>




<div class="form-group">
	<div class="col-lg-12 col-md-12">

		<div class="col-lg-1 col-md-1">
			<img src="./ImageServlet?path=types/${type}.jpg" alt="Type"
				class="img-rounded" width="25" height="25" />
		</div>

		<div class="col-lg-1 col-md-1">
			<img src="./ImageServlet?path=priority/${priority}.jpg"
				alt="${priority}" class="img-rounded" width="25" height="25" />
		</div>
		<div class="col-lg-2 col-md-1">
			<a href="./IssueCon?id=${id}">${issueKey}</a>
		</div>

		<div class="col-lg-1 col-md-1">
			<div class="text-center">${status}</div>
		</div>

		<div class="col-lg-3 col-md-1">
			<div class="text-center">${summary}</div>
		</div>
		<div class="col-lg-1 col-md-1">
			<div class="text-center">${estimate}</div>
		</div>
		
		<div class="col-lg-1 col-md-1">
			<c:if test="${assigneeId>0 }">

				<img src="./ImageServletFromID?userId=${assigneeId}"
					class="img-rounded" width="30" height="30" alt="No assignee" />

			</c:if>
		</div>

		<div class="col-lg-2 col-md-1">
			<form action="./SprintInfo" method="get">
				<button name="activityID" value="${id}" class="btn btn-primary">Add
					to this Sprint</button>
				<input name="id" value="${sessionScope.sprint.id}" class="hidden" />
			</form>
		</div>


	</div>
</div>
