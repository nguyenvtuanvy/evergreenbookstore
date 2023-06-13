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
    <title>Manage Categorys - Evergreen Bookstore Administration</title>
  </head>

  <body>
    <jsp:include page="header.jsp"></jsp:include>

    <div align="center">
      <h2 class="pageheading">Category Management</h2>
      <h3>
        <a href="category_form.jsp">Create new Categorys</a>
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
          <th>Name</th>
          <th>Action</th>
        </tr>

        <c:forEach var="c" items="${listcategory}" varStatus="status">
          <tr>
            <td>${status.index+1}</td>
            <td>${c.categoryId}</td>
            <td>${c.name}</td>
            <td>
              <a href="edit_category?id=${c.categoryId}">Edit</a>
              <a href="javascript:void(0);" class="deletelink" id="${c.categoryId}">Delete</a>
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
            cateid = $(this).attr("id");
            if (confirm("Are you sure you want to delete the user ID " + cateid + "?")){
              	window.location = "delete_category?id=" + cateid;
            }
          });
        });
      });
    </script>
  </body>
</html>
