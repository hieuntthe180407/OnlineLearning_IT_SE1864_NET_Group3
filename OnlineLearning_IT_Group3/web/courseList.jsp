<%-- 
    Document   : courseList
    Created on : Sep 18, 2024, 1:43:51 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Secret Coder : Courses</title>
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
    <!-- Spinner Start -->
    <div id="spinner"
        class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <%@include file="header.jsp" %>

    <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Courses</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item"><a class="text-white" href="index.html">Home</a></li>
                            <li class="breadcrumb-item text-white active" aria-current="page">Courses</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


    



    <!-- Courses Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h6 class="section-title bg-white text-center px-3">Popular Courses</h6>
                <h1 class="mb-5" style="color: #fb873f;">Explore new and trending free online courses</h1>
            </div>
            <div class="row g-4 py-2"> <!-- 2 vi du nay de lam list nhet vao loop cho de so sanh -->
                <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="course-item shadow">
                        <div class="position-relative overflow-hidden text-light image">
                            <img class="img-fluid" src="img/course-1.jpg" alt="">
                            <div style="position:absolute;top: 15px;left: 16px; font-size:12px; border-radius:3px; background-color:#fb873f;"
                                class="px-2 py-1 fw-bold text-uppercase">FREE</div>

                        </div>
                        <div class="p-2 pb-0">

                            <h5 class="mb-1"><a href="single.html" class="text-dark">HTML Course for Beginners</a> </h5>
                        </div>
                        <div class="d-flex">
                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-star text-warning me-2"></i>
                                4.55</small>
                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-user-graduate me-2"></i>5.8L+
                                Learners
                            </small>
                            <small class="flex-fill text-center py-1 px-2"><i
                                    class="fa fa-user me-2"></i>Beginner</small>
                        </div>
                        <div class="d-flex">
                            <small class="flex-fill text-left p-2 px-2"><i class="fa fa-clock me-2"></i>2.0
                                Hrs</small>
                            <small class="py-1 px-2 fw-bold fs-6 text-center">₹ 0</small>
                            <small class=" text-primary py-1 px-2 fw-bold fs-6" style="float:right;"><a href="#">Enroll
                                    Now </a><i class="fa fa-chevron-right me-2 fs-10"></i></small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="course-item shadow">
                        <div class="position-relative overflow-hidden text-light image">
                            <img class="img-fluid" src="img/course-2.jpg" alt="">
                            <div style="position:absolute;top: 15px;left: 16px; font-size:12px; border-radius:3px; background-color:#0ed44c;"
                                class="px-2 py-1 fw-bold text-uppercase">PAID</div>

                        </div>
                        <div class="p-2 pb-0">

                            <h5 class="mb-1">Front End Development-CSS
                            </h5>
                        </div>
                        <div class="d-flex">
                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-star text-warning me-2"></i>
                                4.55</small>
                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-user-graduate me-2"></i>5.2L+
                                Learners
                            </small>
                            <small class="flex-fill text-center py-1 px-2"><i
                                    class="fa fa-user me-2"></i>Beginner</small>
                        </div>
                        <div class="d-flex">
                            <small class="flex-fill text-left p-2 px-2"><i class="fa fa-clock me-2"></i>4.0
                                Hrs</small>
                            <small class="py-1 px-2 fw-bold fs-6 text-center">₹ 199</small>
                            <small class=" text-primary py-1 px-2 fw-bold fs-6" style="float:right;"><a href="#">Enroll
                                    Now </a><i class="fa fa-chevron-right me-2 fs-10"></i></small>
                        </div>
                    </div>
                </div>
                
                    </div>
                </div>
            </div>

        
    
    <!-- Courses End -->


    <%@include file="footer.jsp" %>


    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>