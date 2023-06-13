<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}/admin/"><img src="../images/BookstoreAdminLogo.png" alt="logo_admin" /></a>
	</div>

	<div>
		Welcom, <c:out value="${sessionScope.useremail}"></c:out> | <a href="logout">Logout</a> <br> <br>
	</div>

	<div id="headermenu">
		<div class="menu_item col">
			<a href="list_users"> <img
				src="../images/user.png"
				style="width: 50px; height: 50px" /><br />Users
			</a>
		</div>
		<div class="menu_item">
			<a href="list_category"> <img
				src="../images/category.png"
				style="width: 50px; height: 50px" /><br />Categories
			</a>
		</div>
		<div class="menu_item">
			<a href="list_books"> <img
				src="../images/book.png"
				style="width: 50px; height: 50px" /><br />Books
			</a>
		</div>
		<div class="menu_item">
			<a href="list_customer"> <img
				src="../images/customer.png"
				style="width: 50px; height: 50px" /><br />Customers
			</a>
		</div>
		<div class="menu_item">
			<a href="list_review"> <img
				src="../images/review.png"
				style="width: 50px; height: 50px" /><br />Reviews
			</a>
		</div>
		<div class="menu_item">
			<a href="list_order"> <img
				src="../images/order.png"
				style="width: 50px; height: 50px" /><br />Orders
			</a>
		</div>
	</div>
</div>