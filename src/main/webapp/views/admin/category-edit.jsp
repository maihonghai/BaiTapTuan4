<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/category/update"
	method="post" enctype="multipart/form-data">
	<input type="text" id="cateid" name="cateid" value="${cate.cateid }" hidden="hidden"><br>
	<label for="catename">Category name:</label><br> 
	<input type="text" id="catename" name="catename" value="${cate.catename }"><br>
	<label for="images">Images:</label><br>
	<c:if test="${cate.icon.substring(0,5) != 'https'}">
		<c:url value="/image?fname=${cate.icon }" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${cate.icon.substring(0,5) == 'https'}">
		<c:url value="${cate.icon }" var="imgUrl"></c:url>
	</c:if>
	<img id = "imagess" height="150" width="200" src="${imgUrl }"/>
	 <input type="file" onchange="chooseFile(this)" id="images" name="images" value="${cate.icon }"><br> 
	
	<p>Status:</p>
	<input type="radio" id="ston" name="status" value="1"${cate.status==1?'checked':''}>
	<label for="html">Đang hoạt động</label><br>
	<input type="radio" id="stoff" name="status" value="0"${cate.status!=1?'checked':''}>
	<label for="css">Khóa</label><br>
	
	
	<br> <input type="submit" value="Update">
</form>
