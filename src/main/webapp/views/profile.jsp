<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ page import="vn.iotstar.models.UserModel"%>

<%
UserModel user = (UserModel) request.getAttribute("user");
%>
<h1>Profile</h1>
<form action="${pageContext.request.contextPath }/profile" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${user.id}" />
	<div>
		<label for="fullname">Full Name:</label> <input type="text"
			name="fullname" value="${user.fullName}" required /><br>
	</div>
	<div>
		<label for="phone">Phone:</label> <input type="text" name="phone"
			value="${user.phone}" required /><br>
	</div>
	<div>
		<label for="images">Upload Image:</label> <input type="file"
			name="images" accept="image/*" /><br>
	</div>

	<div>
		<button type="submit">Update</button>
	</div>
</form>


