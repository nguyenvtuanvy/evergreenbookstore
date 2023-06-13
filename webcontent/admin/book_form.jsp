<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style_admin.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="../css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<title>Create New Book</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null}">
			 		Edit Book
			 </c:if>
			<c:if test="${book == null}">
			 		Create New Book
			 </c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="bookId" value="${book.bookId}" />
		</c:if>
		<c:if test="${book == null}">
			<form action="create_book" method="post"
				enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td>Category :</td>
				<td><select name="category" >
						<c:forEach items="${listcategory}" var="category">
							<c:if test="${category.categoryId eq book.category.categoryId }">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne book.category.categoryId }">
								<option value="${category.categoryId}">
							</c:if>
							${category.name}
							</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td align="right">Title :</td>
				<td align="left"><input type="text" size="20"
					name="title" value="${book.title}" required minlength="10"
					maxlength="128" /></td>
			</tr>

			<tr>
				<td align="right">Author :</td>
				<td align="left"><input type="text" size="20"
					name="author" value="${book.author}" required minlength="3"
					maxlength="64" /></td>
			</tr>

			<tr>
				<td align="right">ISBN :</td>
				<td align="left"><input type="text" size="20"
					name="isbn" value="${book.isbn}" required minlength="3"
					maxlength="15" /></td>
			</tr>

			<tr>
				<td align="right">Publish Date :</td>
				<td align="left"><input type="date" size="20"
					required name="publishDate"
					value="<fmt:formatDate pattern='yyyy-MM-dd' value='${book.publishDate}' />" /></td>
			</tr>

			<tr>
				<td align="right">Book Image :</td>
				<td align="left"><c:if test="${book == null}">
						<input type="file" id="bookImage" name="bookImage"
							required />
						<br>
					</c:if> <c:if test="${book != null}">
						<input type="file" id="bookImage" name="bookImage" />
						<br>
					</c:if> <img id="thumbnail" alt="Image Preview"
					style="width: 20%; margin-top: 10px;"
					src="data:image/jpg;base64,${book.base64Image}" /></td>
			</tr>

			<tr>
				<td align="right">Price :</td>
				<td align="left"><input type="text" size="20"
					required name="price" value="${book.price}" /></td>
			</tr>

			<tr>
				<td align="right">Decription :</td>
				<td align="left"><textarea rows="5" cols="50"
						name="depcription" id="depcription" required>${book.depcription}</textarea></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>
					<button type="button" onclick="history.go(-1);">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#depcription').richText();

		$('#bookImage').change(function() {
			showImageThumbnail(this);
		});
	});

	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];

		var reader = new FileReader();

		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};

		reader.readAsDataURL(file);
	}
</script>

</html>