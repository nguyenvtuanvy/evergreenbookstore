<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Payment Receipt" name="pageTitle" />
</jsp:include>
<body onload="window.print();">
	<div class="container">
		<jsp:directive.include file="receipt.jsp" />
	</div>
</body>
</html>