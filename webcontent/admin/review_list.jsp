<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="../css/style_admin.css" />
    <script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
    <title>Manage Review - Evergreen Bookstore Administration</title>
  </head>

  <body>
    <jsp:include page="header.jsp"></jsp:include>

    <div align="center">
      <h2 class="pageheading">Review Management</h2>
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
          <th>Book</th>
          <th>Rating</th>
          <th>Headline</th>
          <th>Customer</th>
          <th>Review On</th>
          <th>Action</th>
        </tr>

        <c:forEach var="r" items="${listreview}" varStatus="status">
          <tr>
            <td>${status.index+1}</td>
            <td>${r.reviewId}</td>
            <td>${r.book.bookId}</td>
            <td>${r.rating}</td>
            <td>${r.headline}</td>
            <td>${r.customer.fullname}</td>
            <td>${r.reviewTime}</td>
            <td>
              <a href="edit_review?id=${r.reviewId}">Edit</a>
              <a href="javascript:void(0);" class="deletelink" id="${r.reviewId}">Delete</a>
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
            reviewid = $(this).attr("id");
            if (confirm("Are you sure you want to delete the user ID " + reviewid + "?")){
              	window.location = "delete_review?id=" + reviewid;
            }
          });
        });
      });
    </script>
  </body>
</html>
