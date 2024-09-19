<%-- 
    Document   : myCourse
    Created on : Sep 19, 2024, 12:19:51 AM
    Author     : laptop acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Courses</title>
    <!-- Favicon -->
    <link rel="icon" href="path/to/favicon.ico" type="image/x-icon">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="path/to/your/custom.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Custom JS -->
    <script src="path/to/your/custom.js"></script>
    <script>
        function filterCourses() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById('courseSearch');
            filter = input.value.toUpperCase();
            table = document.getElementById("courseTable");
            tr = table.getElementsByTagName('tr');
            for (i = 1; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }       
            }
        }
    </script>
</head>
<body>
    <!-- Include Header -->
    <jsp:include page="header.jsp" />

    <div class="container">
        <h1 class="my-4">My Access-Allowed Courses</h1>
        <input type="text" id="courseSearch" onkeyup="filterCourses()" class="form-control mb-3" placeholder="Search for courses..">
        <c:choose>
            <c:when test="${not empty allowedCourses}">
                <table id="courseTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Course Name</th>
                            <th>Description</th>
                            <th>Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${allowedCourses}">
                            <tr>
                                <td>${course.courseName}</td>
                                <td>${course.description}</td>
                                <td><a href="courseDetails.jsp?courseId=${course.courseID}" class="btn btn-primary">View Details</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>You do not have access to any courses at the moment.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>
