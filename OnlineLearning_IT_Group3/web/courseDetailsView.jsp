<%-- 
    Document   : courseDetails
    Created on : Nov 7, 2024, 7:59:00 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fullstack Website</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .courses img {
                height: 200px;
                object-fit: cover;
            }
            footer {
                background-color: #343a40;
                color: white;
                padding: 20px 0;
            }
            footer a {
                color: white;
            }
        </style>
    </head>
    <body>

        <!-- Header -->
        <%@include file="header.jsp" %>

<div class="container mt-5">
    <h2>Course Detail</h2>
    <div class="card mt-3">
        <img src="${course.courseImg}" class="card-img-top" alt="${course.courseName}">
        <div class="card-body">
            <h3 class="card-title">${course.courseName}</h3>
            <p class="card-text">${course.description}</p>
            <p><strong>Duration:</strong> ${course.duration} hours</p>
            <p><strong>Price:</strong> $${course.price}</p>
            <p><strong>Sale Price:</strong> $${course.salePrice}</p>
        </div>
    </div>

    <h4 class="mt-5">Lessons</h4>
    <table class="table table-bordered mt-3">
        <thead>
            <tr>
                <th>Lesson Number</th>
                <th>Lesson Name</th>
                <th>Description</th>
                <th>Link</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="lesson" items="${lessons}">
            <tr>
                <td>${lesson.lessonNumber}</td>
                <td>${lesson.lessonName}</td>
                <td>${lesson.description}</td>
                <td><a href="${lesson.lessonURL}" target="_blank">Watch</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<footer class="text-center">
            <div class="container">
                <p>&copy; 2024 Fullstack Development. All rights reserved.</p>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
                    <li class="list-inline-item"><a href="#">Terms of Service</a></li>
                    <li class="list-inline-item"><a href="#">Contact</a></li>
                </ul>
            </div>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
