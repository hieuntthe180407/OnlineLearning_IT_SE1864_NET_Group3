<%-- 
    Document   : Admin
    Created on : Sep 17, 2024, 5:30:22 PM
    Author     : trong
--%>

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
    <%@include file="header.jsp" %>
 <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Admin Page</h1>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- Admin Page Content -->
    <div class="container my-5">
        
        <div class="row justify-content-center">
            <!-- User List Card -->
            <div class="col-md-4 mb-4">
                <div class="card h-100 text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-end">
                        <h5 class="card-title">User List</h5>
                        
                        <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
    <img src="img/admin/user-settings-icon.jpg" style="width: 250px; height: 250px;" />
</div>
                    </div>
                    <div class="card-footer">
                        <a href="userList" class="btn btn-primary w-100">View Users</a>
                    </div>
                </div>
            </div>

            <!-- Manage Course Card -->
            <div class="col-md-4 mb-4">
                <div class="card h-100 text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-end">
                        <h5 class="card-title">Manage Course</h5>
                        <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
    <img src="img/admin/course-settings-icon.png" style="width: 250px; height: 250px;" />
</div>
                    </div>
                    <div class="card-footer">
                        <a href="adminPendingCourse.jsp" class="btn btn-primary w-100">Manage Courses</a>
                    </div>
                </div>
            </div>

            <!-- List Questions Card -->
            <div class="col-md-4 mb-4">
                <div class="card h-100 text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-end">
                        <h5 class="card-title">List Questions</h5>
                         <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
    <img src="img/admin/list-question-icon.png" style="width: 250px; height: 250px;" />
</div>
                    </div>
                    <div class="card-footer">
                        <a href="QuestionListServlet" class="btn btn-primary w-100">List Questions</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100 text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-end">
                        <h5 class="card-title">Slider List</h5>
                         <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
    <img src="img/admin/list-question-icon.png" style="width: 250px; height: 250px;" />
</div>
                    </div>
                    <div class="card-footer">
                        <a href="SliderController" class="btn btn-primary w-100">Slider list</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100 text-center shadow-sm">
                    <div class="card-body d-flex flex-column justify-content-end">
                        <h5 class="card-title">Post List</h5>
                         <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
    <img src="img/admin/list-question-icon.png" style="width: 250px; height: 250px;" />
</div>
                    </div>
                    <div class="card-footer">
                        <a href="BlogManageController" class="btn btn-primary w-100">Post list</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp" %>

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
