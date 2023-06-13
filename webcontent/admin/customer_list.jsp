<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="../css/style_admin.css" />
    <script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
    <title>Manage Customer - Evergreen Bookstore Administration</title>
  </head>
  <body>
    <jsp:include page="header.jsp"></jsp:include>

    <div align="center">
      <h2 class="pageheading">Customer Management</h2>
      <h3>
        <a href="new_customer">Create New Customer</a>
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
          <th>E-mail</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>City</th>
          <th>Country</th>
          <th>Registered Date</th>
          <th>Action</th>
        </tr>

        <c:forEach var="c" items="${listcustomer}" varStatus="status">
          <tr>
            <td>${status.index+1}</td>
            <td>${c.customerId}</td>
            <td>${c.email}</td>
            <td>${c.firstname}</td>
            <td>${c.lastname}</td>
            <td>${c.city}</td>
            <td>${c.countryName}</td>
            <td>
              <fmt:formatDate pattern="MM/dd/yyyy" value="${c.registerDate}" />
            </td>
            <td>
              <a href="edit_customer?id=${c.customerId}">Edit</a>
              <a href="javascript:void(0);" class="deletelink" id="${c.customerId}">Delete</a>
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
            customerid = $(this).attr("id");
            if (confirm("Are you sure you want to delete the customer ID " + customerid + "?" )) {
              	window.location = "delete_customer?id=" + customerid;
            }
          });
        });
      });
    </script>
  </body>
</html>
