<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style_admin.css">
<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>Manage Books - Evergreen Bookstore Administration</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Books Management</h2>
		<h3>
			<a href="new_book">Create New Books</a>
		</h3>
	</div>
	
	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<div align="center">
		<table border="1" cellPadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Update</th>
				<th>Action</th>
			</tr>

			<c:forEach var="b" items="${listbooks}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${b.bookId}</td>
					
					<td>
						<img src="data:img/jpg;base64,${b.base64Image}" width="84px" height="110px">
					</td>
					
					<td>${b.title}</td>
					<td>${b.author}</td>
					<td>${b.category.name}</td>
					<td>$ ${b.price}</td>
					<td><fmt:formatDate pattern='MM/dd/yyyy' value='${b.lastUpdateTime}' /></td>
					<td> 
						<a href="edit_book?id=${b.bookId}">Edit</a>
						<a href="javascript:void(0);" class="deletelink" id="${b.bookId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
	<script>
		$(document).ready(function () {
        	$(".deletelink").each(function () {
          		$(this).on("click", function () {
          			bookid = $(this).attr("id");
            		if (confirm('Are you sure you want to delete the book ID ' + bookid + '?')) {
    				window.location = 'delete_book?id='+bookid;
    				}
          		});
        	});
      	});
	</script>
</body>
</html>