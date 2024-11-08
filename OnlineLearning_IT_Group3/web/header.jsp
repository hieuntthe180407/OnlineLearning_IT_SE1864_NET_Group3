<%-- 
    Document   : header
    Created on : Sep 13, 2024, 2:52:41 PM
    Author     : trong
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="google-translate-customization" content="9f841e7780177523-3214ceb76f765f38-gc38c6fe6f9d06436-c">
        </meta>

        <title>Secret Coder : Online Courses</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/icon.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
            rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

    </head>

    <body>
        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="home" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <p class="m-0 fw-bold" style="font-size: 25px;"><img src="img/icon.png" alt="" height="50px">IT Online<span
                        style="color: #fb873f;">Learning</span></p>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="#" class="nav-item nav-link active">Home</a>
                    <a href="#" class="nav-item nav-link">About</a>
                    <a href="/OnlineLearning_IT_Group3/courseList" class="nav-item nav-link">Courses</a>
                    <a href="blogs" class="nav-item nav-link">Blog</a>
                    <a href="Admin.jsp" class="nav-item nav-link">Admin</a>
                </div>
            </div>
            <a href="#" class="nav-item nav-link">Contact</a>

            <div class="nav-item dropdown" style="position: relative;">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-user"></i>
                </a>
                <ul class="dropdown-menu" aria-labelledby="userDropdown" 
                    style="position: absolute; right: 0; max-width: 250px; white-space: nowrap; padding: 10px; margin: 0; overflow: hidden;">
                    <c:choose>
                        <c:when test="${not empty sessionScope.acc}">
                            <li>
                                <a class="dropdown-item" href="MyCourse">My Course</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="userProfile">Welcome, ${sessionScope.acc.fullName}</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a class="dropdown-item" href="/OnlineLearning_IT_Group3/login.jsp">Login</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>

            <a href="#" class="nav-item nav-link">

                <div id="google_translate_element">
                </div>


            </a>
        </div>
    </div>
</nav>
<!-- Navbar End -->
</body>
</html>
