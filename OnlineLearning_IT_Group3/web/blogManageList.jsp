<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="google-translate-customization" content="9f841e7780177523-3214ceb76f765f38-gc38c6fe6f9d06436-c">
    <title>Secret Coder : Online Courses</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="img/icon.png" rel="icon">
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
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
            <c:set var="allSelected" value="${sortColumns == null || sortColumns.isEmpty()}"/>
            <label><input type="checkbox" name="sortColumns" value="title" <c:if test="${allSelected || sortColumns.contains('title')}">checked</c:if>> Title</label>
            <label><input type="checkbox" name="sortColumns" value="status" <c:if test="${allSelected || sortColumns.contains('status')}">checked</c:if>> Status</label>
            <label><input type="checkbox" name="sortColumns" value="createdAt" <c:if test="${allSelected || sortColumns.contains('createdAt')}">checked</c:if>> Created At</label>
            <label><input type="checkbox" name="sortColumns" value="updatedAt" <c:if test="${allSelected || sortColumns.contains('updatedAt')}">checked</c:if>> Updated At</label>

            <button type="submit" class="btn btn-secondary btn-sm">Apply Filter & Sort</button>
        </form>

        <!-- Blog Table -->
        <table class="table table-striped table-bordered mt-3" style="width:100%">
            <thead>
                <tr>
                    <c:if test="${allSelected || sortColumns.contains('title')}"><th>Title</th></c:if>
                    <th>Content</th>
                    <c:if test="${allSelected || sortColumns.contains('status')}"><th>Status</th></c:if>
                    <c:if test="${allSelected || sortColumns.contains('createdAt')}"><th>Created At</th></c:if>
                    <c:if test="${allSelected || sortColumns.contains('updatedAt')}"><th>Updated At</th></c:if>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blog" items="${blogs}">
                    <tr>
                        <c:if test="${allSelected || sortColumns.contains('title')}"><td>${blog.title}</td></c:if>
                        <td>${blog.content}</td>
                        <c:if test="${allSelected || sortColumns.contains('status')}"><td>${blog.status}</td></c:if>
                        <c:if test="${allSelected || sortColumns.contains('createdAt')}"><td>${blog.createdAt}</td></c:if>
                        <c:if test="${allSelected || sortColumns.contains('updatedAt')}"><td>${blog.updatedAt}</td></c:if>
                        <td>
                            <a href="BlogManageController?action=viewDetail&id=${blog.blogId}" class="btn btn-info btn-sm">View</a>
                            <a href="BlogManageController?action=edit&id=${blog.blogId}" class="btn btn-warning btn-sm">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Pagination Controls -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}&searchQuery=${searchQuery}${sortColumnsParam}">Previous</a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item <c:if test='${i == currentPage}'>active</c:if>'">
                        <a class="page-link" href="?page=${i}&searchQuery=${searchQuery}${sortColumnsParam}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}&searchQuery=${searchQuery}${sortColumnsParam}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <%@include file="footer.jsp" %>
    <script src="js/main.js"></script>
</body>
</html>



