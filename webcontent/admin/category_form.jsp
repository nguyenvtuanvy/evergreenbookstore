<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style_admin.css">
<title>Create New Category</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${category != null}">
			 		Edit Category
			 	</c:if>
			<c:if test="${category == null}">
			 		Create New Category
			 	</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${category != null}">
			<form action="update_category" method="post"
				style="max-width: 310px; margin: 0 auto;">
				<input type="hidden" name="categoryId"
					value="${category.categoryId}">
		</c:if>
		<c:if test="${category == null}">
			<form action="create_category" method="post"
				style="max-width: 310px; margin: 0 auto;">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Name :</td>
				<td align="left"><input type="text" id="name" size="20"
					required minlength="2" maxlength="20" name="name"
					value="${category.name}" /></td>
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
</html>