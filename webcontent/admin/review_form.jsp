<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style_admin.css">
<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>Edit Review</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Edit Category</h2>
	</div>

	<div align="center">
			<form action="update_review" method="post" id="reviewform">
			<input type="hidden" name="reviewId" value="${review.reviewId}">

		<table class="form">
			<tr>
				<td align="right">Book :</td>
				<td align="left">${review.book.bookId}</td>
			</tr>
			<tr>
				<td align="right">Rating :</td>
				<td align="left">${review.rating}</td>
			</tr>
			<tr>
				<td align="right">Customer :</td>
				<td align="left">${review.customer.fullname}</td>
			</tr>
			<tr>
				<td align="right">Headline :</td>
				<td align="left">
					<input type="text" size="60" name="headline" value="${review.headline}">
				</td>
			</tr>
			<tr>
				<td align="right">Comment :</td>
				<td align="left">
					<textarea rows="5" cols="70" name="comment" >${review.comment}</textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>
					<input type="button" onclick="history.go(-1);" value="Cancel" />
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#reviewform").validate({
		rules: {
			headline: "required",
			comment: "required"
		},
		
		messages: {
			headline: "Please enter headline",
			comment: "Please enter comment",
		}
	});
	
});
</script>

</html>