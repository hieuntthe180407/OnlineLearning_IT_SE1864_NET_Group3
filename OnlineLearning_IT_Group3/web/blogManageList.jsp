<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
     <head>
         <meta charset="utf-8">
    <meta name="google-translate-customization" content="9f841e7780177523-3214ceb76f765f38-gc38c6fe6f9d06436-c">
    </meta>

    <title>Secret Coder : Online Courses</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/icon.png" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
        rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
    <%@include file="header.jsp" %>
        <div class="container mt-5">
            <h1 class="mb-4">Blog List</h1>
            <a href="BlogManageController?action=add" class="btn btn-info btn-sm">Add</a>

            <!-- Form for search and sorting -->
            <form method="get" action="BlogManageController" class="my-3">
                <input type="hidden" name="action" value="list">

                <!-- Search field -->
                <div class="form-group">
                    <input type="text" name="searchQuery" value="${searchQuery}" placeholder="Search by title or content" class="form-control" />
                </div>

                <!-- Sort options with checkboxes -->
                <label><input type="checkbox" name="sortColumns" value="title" <c:if test="${sortColumns != null && sortColumns.contains('title')}">checked</c:if>> Title</label>
                <label><input type="checkbox" name="sortColumns" value="status" <c:if test="${sortColumns != null && sortColumns.contains('status')}">checked</c:if>> Status</label>
                <label><input type="checkbox" name="sortColumns" value="createdAt" <c:if test="${sortColumns != null && sortColumns.contains('createdAt')}">checked</c:if>> Created At</label>
                <label><input type="checkbox" name="sortColumns" value="updatedAt" <c:if test="${sortColumns != null && sortColumns.contains('updatedAt')}">checked</c:if>> Updated At</label>

                    <button type="submit" class="btn btn-secondary btn-sm">Apply Filter & Sort</button>
                </form>

                <!-- Blog Table -->
                <table class="table table-striped table-bordered mt-3" style="width:100%">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Status</th>
                            <th>Created At</th>
                            <th>Updated At</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="blog" items="${blogs}">
                        <tr>
                            <td>${blog.title}</td>
                            <td>${blog.content}</td>
                            <td>${blog.status}</td>
                            <td>${blog.createdAt}</td>
                            <td>${blog.updatedAt}</td>
                            <td>
                                <a href="BlogManageController?action=viewDetail&id=${blog.blogId}" class="btn btn-info btn-sm">View</a>
                                <a href="BlogManageController?action=edit&id=${blog.blogId}" class="btn btn-warning btn-sm">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination Controls -->
            <!-- Pagination Controls -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage - 1}&searchQuery=${searchQuery}&sortColumns=${sortColumnsParam}">Previous</a>
                        </li>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item <c:if test='${i == currentPage}'>active</c:if>'">
                            <a class="page-link" href="?page=${i}&searchQuery=${searchQuery}&sortColumns=${sortColumnsParam}">${i}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${currentPage + 1}&searchQuery=${searchQuery}&sortColumns=${sortColumnsParam}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>

        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <%@include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>

