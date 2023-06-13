<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style_admin.css">
<title>Create New User</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${user != null}">
			 		Edit User
			 	</c:if>
			<c:if test="${user == null}">
			 		Create New User
			 	</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post"
				style="max-width: 350px; margin: 0 auto;">
				<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post"
				style="max-width: 350px; margin: 0 auto;">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Email :</td>
				<td align="left"><input type="email" id="email" size="20"
					required minlength="10" maxlength="30" name="email"
					value="${user.email}" /></td>
			</tr>
			<tr>
				<td align="right">FullName :</td>
				<td align="left"><input type="text" id="fullname" size="20"
					required minlength="5" maxlength="30" name="fullname"
					value="${user.fullName}" /></td>
			</tr>
			<tr>
				<td align="right">Password :</td>
				<td align="left"><input type="password" id="password" required
					minlength="4" maxlength="16" size="20" name="password"
					value="${user.password}" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="history.go(-1);">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>