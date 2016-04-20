<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>




<div class="form-group">
	<div class="col-lg-12 col-md-12">
		
		<div class="col-lg-1 col-md-1">
			<div class="text-center">${fullname}</div>
		</div>
		
		<div class="col-lg-5 col-md-1">
			<div class="text-center">${createdon}</div>
		</div>
		
		<div class="col-lg-5 col-md-1">
			<div class="text-center">${text}</div>
		</div>
		


		<div class="col-lg-1 col-md-1">
			
				<img src="./ImageServletFromID?userId=${userid}"
					class="img-rounded" width="30" height="30" alt="No assignee" />

		</div>



	</div>
</div>
