<%-- 
    Document   : updateCourse
    Created on : Nov 8, 2024, 11:10:02 AM
    Author     : DTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Course</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <%@ include file="header.jsp" %>
 <script>
    // Function to preview image from file upload
    function previewImage(event) {
        const file = event.target.files[0];
        const preview = document.getElementById('imagePreview');

        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
            };
            reader.readAsDataURL(file);
        }
    }
    </script>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Update Course</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <strong>Error!</strong> ${error}
            </div>
        </c:if>

        <form action="updateCourse" method="post" enctype="multipart/form-data">
            <input type="text" hidden="" name="courseId" value="${course.getCourseID()}">
            
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
                <input type="file" id="courseImg" name="courseImg" class="form-control"onchange="previewImage(event)">
                <div class="col-12 mt-3">
    <img id="imagePreview" src="#" alt="Image Preview" style="max-width: 100px; display: none;">
</div>
            </div>

            <div class="form-group">
                <label for="courseName">Course Name:</label>
                <input type="text" id="courseName" name="courseName" class="form-control" value="${course.getCourseName()}" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control" required>${course.getDescription()}</textarea>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Update Course</button>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>
</html>

