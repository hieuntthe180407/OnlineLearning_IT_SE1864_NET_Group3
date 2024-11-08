<%-- 
    Document   : blogList
    Created on : Sep 19, 2024, 8:21:35 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .blog-thumbnail {
                height: 200px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <%@ include file="header.jsp" %>

        <!-- Main Content -->
        <div class="container mt-4">
            <div class="row">
                <!-- Blog List -->
                <div class="col-md-8">
                    <h2>Blog Posts</h2>
                    <c:forEach items="${blogs}" var="blog">
                        <div class="card mb-3">
                            <img src="${blog.featuredImage}" class="card-img-top blog-thumbnail" alt="${blog.title}">
                            <div class="card-body">
                                <h5 class="card-title">${blog.title}</h5>
                                <p class="card-text">${fn:substring(blog.content, 0, 100)}...</p>
                                  <a href="BlogUserViewDetailController?blogId=${blog.blogId}" class="btn btn-primary">Read More</a>
                            </div>
                        </div>
                    </c:forEach>

                    <!-- Pagination -->
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="blogs?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                                    <a class="page-link" href="blogs?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="blogs?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>

                <!-- Sidebar -->
                <div class="col-md-4">
                    <!-- Search Box -->
                    <div class="mb-4">
                        <h4>Search</h4>
                        <form action="blogs" method="get">
                            <input value="${searchKeyword}" type="text" name="search" class="form-control" placeholder="Search...">
                            <button type="submit" class="btn btn-primary mt-2">Search</button>
                        </form>
                    </div>

                    <!-- Categories -->
                    <div class="mb-4">
                        <h4>Categories</h4>
                        <ul class="list-group">
                            <c:forEach items="${categories}" var="category">
                                <li class="list-group-item">
                                    <a href="blogs?category=${category.categoryId}">${category.categoryName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <!-- Latest Posts -->
                    <c:if test="${not empty latestBlogs}">
                        <div class="mb-4">
                            <h4>Latest Posts</h4>
                            <ul class="list-group">
                                <c:forEach items="${latestBlogs}" var="latestBlog" varStatus="status">
                                    <li class="list-group-item">
                                        <a href="blogDetails.jsp?id=${latestBlog.blogId}">
                                            <div class="d-flex align-items-center">
                                                <img src="${latestBlog.featuredImage}" alt="${latestBlog.title}" class="img-thumbnail me-3" style="max-width: 100px;">
                                                <div>${latestBlog.title}</div>
                                            </div>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <!-- Static Links -->
                    <div>
                        <h4>Contact</h4>
                        <ul class="list-group">
                            <li class="list-group-item"><a href="#">Contact Us</a></li>
                            <li class="list-group-item"><a href="#">About Us</a></li>
                            <li class="list-group-item"><a href="#">Privacy Policy</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
