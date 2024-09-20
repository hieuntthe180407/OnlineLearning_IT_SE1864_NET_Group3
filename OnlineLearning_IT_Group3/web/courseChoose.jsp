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
    <title>Course Details</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <!-- Header -->
    <%@include file="header.jsp" %>

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
                <div class="card">
                    <div class="card-body">
                        <h2 id="courseTitle">Course Title</h2>
                        <h5 id="courseTagline">Course Tagline</h5>
                        <p id="courseBriefInfo">Brief info about the course...</p>
                        <div class="mb-4">
                            <h4>Price Options</h4>
                            <p>Original Price: <span id="originalPrice">$200</span></p>
                            <p>Sale Price: <span id="salePrice">$150</span></p>
                        </div>
                        <div class="mb-4">
                            <h4>Product Description</h4>
                            <p id="courseDescription">Detailed description of the course...</p>
                        </div>
                        <button class="btn btn-primary" id="registerButton">Register</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="footer.jsp" %>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Custom JS -->
    <script src="path/to/your/custom.js"></script>
    <script>
        $(function(){
            $("#header").load("path/to/your/header.html");
            $("#footer").load("path/to/your/footer.html");
        });

        // Function to display course details
        function showCourseDetails() {
            const courseData = {
                title: "Advanced JavaScript",
                tagline: "Master JavaScript with advanced concepts",
                briefInfo: "This course covers advanced topics in JavaScript...",
                originalPrice: "$200",
                salePrice: "$150",
                description: "In this course, you will learn about closures, prototypes, and more..."
            };

            document.getElementById('courseTitle').innerText = courseData.title;
            document.getElementById('courseTagline').innerText = courseData.tagline;
            document.getElementById('courseBriefInfo').innerText = courseData.briefInfo;
            document.getElementById('originalPrice').innerText = courseData.originalPrice;
            document.getElementById('salePrice').innerText = courseData.salePrice;
            document.getElementById('courseDescription').innerText = courseData.description;
        }

        // Call the function to display course details
        showCourseDetails();
    </script>
</body>
</html>
