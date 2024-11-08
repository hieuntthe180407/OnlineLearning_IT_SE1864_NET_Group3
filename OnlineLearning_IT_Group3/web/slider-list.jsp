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
        <div class="container">
            <h2>Sliders List</h2>
            <a href="slider-form.jsp" class="btn btn-primary">Add New Slider</a>

            <form method="get" action="SliderController" class="my-3">
                <input type="hidden" name="action" value="list">
                <input type="text" name="search" value="${search}" placeholder="Search..." class="form-control mb-2" />

                <!-- Check if columns is null or empty to select all by default -->
                <c:set var="allSelected" value="${columns == null || columns.isEmpty()}"/>
                
                <label>
                    <input type="checkbox" name="columns" value="title" 
                        <c:if test="${allSelected || columns.contains('title')}">checked</c:if>> Title
                </label>
                <label>
                    <input type="checkbox" name="columns" value="backLink" 
                        <c:if test="${allSelected || columns.contains('backLink')}">checked</c:if>> BackLink
                </label>
                <label>
                    <input type="checkbox" name="columns" value="description" 
                        <c:if test="${allSelected || columns.contains('description')}">checked</c:if>> Description
                </label>
                <label>
                    <input type="checkbox" name="columns" value="publish" 
                        <c:if test="${allSelected || columns.contains('publish')}">checked</c:if>> Publish
                </label>
                <label>
                    <input type="checkbox" name="desc" value="desc" 
                        <c:if test="${param.desc != null && param.desc == 'desc'}">checked</c:if>> Desc
                </label>

                <button type="submit" class="btn btn-secondary btn-sm">Apply Filter & Sort</button>
            </form>

            <!-- Slider table -->
            <table class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Media</th>
                        <c:if test="${allSelected || columns.contains('title')}"><th>Title</th></c:if>
                        <c:if test="${allSelected || columns.contains('backLink')}"><th>BackLink</th></c:if>
                        <c:if test="${allSelected || columns.contains('description')}"><th>Description</th></c:if>
                        <c:if test="${allSelected || columns.contains('publish')}"><th>Publish</th></c:if>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="slider" items="${sliders}">
                        <tr>
                            <td>${slider.sliderID}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:contains(slider.imageUrl, '.mp4') || fn:contains(slider.imageUrl, '.avi') || fn:contains(slider.imageUrl, '.mov')}">
                                        <video width="100" controls>
                                            <source src="${slider.imageUrl}" type="video/mp4">
                                            Your browser does not support the video tag.
                                        </video>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${slider.imageUrl}" width="100" alt="alt"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <c:if test="${allSelected || columns.contains('title')}"><td>${slider.title}</td></c:if>
                            <c:if test="${allSelected || columns.contains('backLink')}"><td>${slider.backLink}</td></c:if>
                            <c:if test="${allSelected || columns.contains('description')}"><td>${slider.description}</td></c:if>
                            <c:if test="${allSelected || columns.contains('publish')}"><td>${slider.publish ? "Visible" : "Hidden"}</td></c:if>
                            <td>
                                <a href="SliderController?action=edit&id=${slider.sliderID}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="javascript:void(0);" onclick="confirmDelete(${slider.sliderID})" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination Controls -->
            <nav>
                <ul class="pagination">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <li class="page-item <c:if test='${i == currentPage}'>active</c:if>'">
                            <a class="page-link" href="SliderController?action=list&page=${i}&search=${search}&columns=${fn:join(paramValues.columns, ',')}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>

        <script>
            function confirmDelete(sliderID) {
                if (confirm('Are you sure you want to delete this slider?')) {
                    window.location.href = 'SliderController?action=delete&id=' + sliderID;
                }
            }
        </script>
        <%@include file="footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>