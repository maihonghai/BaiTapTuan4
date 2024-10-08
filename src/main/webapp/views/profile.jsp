<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<h1>Profile</h1>
<form action="${pageContext.request.contextPath }/home/profile/update" method="post"
	enctype="multipart/form-data">
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
	<c:url value="/image?fname=${user.images }" var="imgUrl"></c:url>
		<label>Current Image:</label> <img id="imagess" height="150" width="200" src="${imgUrl }"/>
	</div>
	<div>
		<label>Upload New Image:</label> <input type="file"
			onchange="choosefile(this)" id="images" name="images"/>
	</div>

	<div>
		<button type="submit">Update</button>
	</div>
</form>

 <!-- Đặt hàm JavaScript ở đây, trước thẻ đóng body -->
    <script>
        function choosefile(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    document.getElementById('imagess').src = e.target.result;
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>


