<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<a href="${pageContext.request.contextPath}/"><img
		src="${pageContext.request.contextPath}/images/BookstoreLogo.png"
		alt="logo" class="img-fluid" /></a>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#topNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="topNavbar">
		<form action="search" method="get" class="form-inline">
			<input type="search" name="keyword" class="form-control mr-sm-2 mt-1"
				placeholder="keyword" /> <input type="submit" value="Search"
				class="btn btn-outline-success mt-1" />
		</form>
		<ul class="navbar-nav">
			<c:if test="${loggedcustomer == null }">
				<li class="nav-item">
					<a href="login" class="nav-link">Sign In</a> 
				</li>
				<li class="nav-item">
					<a href="register" class="nav-link">Register</a> 
				</li>
			</c:if>
			<c:if test="${loggedcustomer != null }">
				<li class="nav-item">
					<a href="view_profile" class="nav-link">Welcome, ${loggedcustomer.fullname}</a> 
				</li>
				<li class="nav-item">
					<a href="view_orders" class="nav-link">My Order</a> 
				</li>
				<li class="nav-item">
					<a href="logout" class="nav-link">Logout</a> 
				</li>
			</c:if>
			<li class="nav-item">
				<a href="view_cart" class="nav-link">Cart</a>
			</li>
		</ul>
	</div>
</nav>

<div>&nbsp;</div>
<div class="row justify-content-center">
	<c:forEach var="c" items="${listcategory}" varStatus="status">
		<a href="view_category?id=${c.categoryId}"> <b><font size="+1"><c:out
						value="${c.name}"></c:out></font></b>
		</a>
		<c:if test="${not status.last}">			
			&nbsp; | &nbsp;
			</c:if>
	</c:forEach>
</div>
