<%-- 
    Document   : forgotPassword
    Created on : Sep 19, 2024, 6:58:28 PM
    Author     : DTC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Secret Coder : Forgot Password</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!<!-- pop up -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.14.0/dist/sweetalert2.all.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.14.0/dist/sweetalert2.min.css" rel="stylesheet">
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
        <link href="../lib/animate/animate.min.css" rel="stylesheet">
        <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="../css/style.css" rel="stylesheet">


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


        <!-- Navbar Start -->
        <%@include file= "header.jsp" %>
        <!-- Navbar End -->

        <!-- Header Start -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Forgot Password</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="index.html">Home</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">Forgot Password</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->



        <!-- Login Start -->
        <div class="container-xxl py-2 mt-4">
            <div class="container">

                <div class="row g-4 wow fadeInUp" data-wow-delay="0.5s ">
                    <center>
                        <form action="${pageContext.request.contextPath}/forgot" method="post" class="shadow p-4" style="max-width: 550px;">
                            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                                <h1 class="mb-5 bg-white text-center px-3">Forgot Password</h1>

                            </div


                            <div class="row g-3">


                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="email" name="email" class="form-control" id="email" placeholder="Email Address">
                                        <label for="email">Email Address</label>
                                    </div>
                                </div>



                                <div class="col-12">
                                    <button class="btn text-light w-100 py-3" type="submit">Send</button>
                                </div>


                            </div>
                        </form>
                    </center>     


                </div>
            </div>
        </div>
        <!-- Login End -->
        <c:if test="${not empty mess}">
            <script>
                // Khi trang được tải xong, đoạn mã này sẽ thực thi
                Swal.fire({
                    // Tiêu đề của thông báo (modal)
                    title: 'Notification',

                    // Nội dung của thông báo, giá trị của 'mess' được mã hóa XML để tránh rủi ro về bảo mật
                    text: '${fn:escapeXml(mess)}', // Đoạn mã này sẽ hiển thị giá trị của biến 'mess'

                    // Biểu tượng loại thông báo, trong trường hợp này là "info" (thông tin)
                    icon: 'info',

                    // Văn bản hiển thị trên nút xác nhận
                    confirmButtonText: 'OK'
                })
                        // Sử dụng Promise để xử lý khi người dùng nhấn vào nút xác nhận (OK)
                        .then((result) => {
                            // Nếu người dùng nhấn nút "OK", hệ thống sẽ chuyển hướng đến trang login
                            if (result.isConfirmed) {
                                // Chuyển hướng đến trang đăng nhập
                                window.location.href = '${pageContext.request.contextPath}/login';
                            }
                        });
            </script>

        </c:if>

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-4 col-md-6">
                        <h4 class="text-white mb-3">Quick Link</h4>
                        <p><a class="text-light" href="about.html">About Us</a></p>
                        <p><a class="text-light" href="contact.html">Contact Us</a></p>
                        <p><a class="text-light" href="">Privacy Policy</a></p>
                        <p><a class="text-light" href="">Terms & Condition</a></p>
                        <p><a class="text-light" href="">FAQs & Help</a></p>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <h4 class="text-white mb-3">Contact</h4>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, Bangalore, Karnataka</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+91 8683045908</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>secretcoder@gmail.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-6">
                        <h4 class="text-white mb-3">Subscribe to our Newsletter</h4>
                        <p>Subscribe now and join our growing community of learners committed to lifelong education! </p>
                        <div class="position-relative mx-auto" style="max-width: 400px;">
                            <form action="#">
                                <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="email"
                                       placeholder="Your email" required>
                                <button type="submit"
                                        class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2"><a
                                        href="mailto:keertidvcorai@gmail.com">Subscribe</a></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="copyright">
                    <div class="row">
                        <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                            &copy; <a class="border-bottom" href="index.html">Secret Coder</a>, All Right Reserved.

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->


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

