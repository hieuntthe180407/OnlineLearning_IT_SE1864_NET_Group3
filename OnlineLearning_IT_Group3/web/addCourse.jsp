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
        <form action="addCourse" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="courseName" class="form-label">Course Name</label>
                <input type="text" class="form-control" id="courseName" name="courseName" required>
            </div>
            <div class="mb-3">
                <label for="duration" class="form-label">Duration</label>
                <input type="number" class="form-control" id="duration" name="duration" required>
            </div>
            <div class="mb-3">
                <label for="report" class="form-label">Report</label>
                <input type="number" class="form-control" id="report" name="report" required>
            </div>
            <div class="mb-3">
                <label for="courseImg" class="form-label">Image</label>
                <input type="file" class="form-control" id="courseImg" name="courseImg" accept="image/*" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" class="form-control" id="price" name="price" required>
            </div>
            <div class="mb-3">
                <label for="salePrice" class="form-label">Sale Price</label>
                <input type="number" class="form-control" id="salePrice" name="salePrice">
            </div>
            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select class="form-select" id="status" name="isActive" required>
                    <option value="true">Active</option>
                    <option value="false">Inactive</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Course</button>
            <a href="courseList.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <%@ include file="footer.jsp" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>

</html>

