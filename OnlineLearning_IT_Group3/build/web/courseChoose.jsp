<%-- 
    Document   : coursedetail
    Created on : Sep 19, 2024, 12:17:24 PM
    Author     : laptop acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose Course</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <!-- Header -->
    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-3">
                <div class="mb-4">
                    <input type="text" id="subjectSearch" class="form-control" placeholder="Search subjects...">
                </div>
                <div class="mb-4">
                    <h5>Subject Categories</h5>
                    <ul class="list-group">
                        <li class="list-group-item">Category 1</li>
                        <li class="list-group-item">Category 2</li>
                        <li class="list-group-item">Category 3</li>
                    </ul>
                </div>
                <div class="mb-4">
                    <h5>Featured Subjects</h5>
                    <ul class="list-group">
                        <li class="list-group-item">Featured 1</li>
                        <li class="list-group-item">Featured 2</li>
                        <li class="list-group-item">Featured 3</li>
                    </ul>
                </div>
            </div>
            <!-- Main Content -->
            <div class="col-md-9">
                <h2>Choose a Course</h2>
                <div class="row" id="courseList">
                    <!-- Course items will be appended here by JavaScript -->
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Custom JS -->
    <script src="path/to/your/custom.js"></script>
</body>
</html>
