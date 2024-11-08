<%-- 
    Document   : addCourse
    Created on : Oct 29, 2024, 4:49:35 PM
    Author     : DTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Course</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <%@ include file="header.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center mb-4">Add New Course</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <strong>Error!</strong> ${error}
            </div>
        </c:if>

        <form action="addCourse" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" name="category" class="form-control" required>
                     <c:forEach items="${listCategory}" var="category">
                        <option value="${category.getCategoryID()}">
                            ${category.getCategoryName()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="courseImg">Course Image:</label>
                <input type="file" id="courseImg" name="courseImg" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="courseName">Course Name:</label>
                <input type="text" id="courseName" name="courseName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control" required></textarea>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Create Course</button>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>


