<%-- 
    Document   : courseinformationdetail
    Created on : Sep 19, 2024, 12:17:24 PM
    Author     : laptop acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>course Information Detail</title>
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
        <link href="css/course_information_detail.css" rel="stylesheet">
    </head>
    <%
        
    String fullName = "";
    String email = "";
    String phone = "";
    String gender = "";
    // Lấy tất cả cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("fullName".equals(cookie.getName())) {
                fullName = URLDecoder.decode(cookie.getValue()); // Lấy giá trị cookie userCurent
            } else if ("userEmai".equals(cookie.getName())) {
                email = URLDecoder.decode(cookie.getValue()); // Lấy giá trị cookie email
            } else if ("pass".equals(cookie.getName())) {
                phone = URLDecoder.decode(cookie.getValue()); // Lấy giá trị cookie pass
            } else if ("userGender".equals(cookie.getName())) {
                gender = URLDecoder.decode(cookie.getValue()); // Lấy giá trị cookie gender
            }
        }
    }
    %>
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
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <p class="m-0 fw-bold" style="font-size: 25px;"><img src="img/icon.png" alt="" height="50px">Secret<span
                        style="color: #fb873f;">Coder</span></p>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="index.html" class="nav-item nav-link active">Home</a>
                    <a href="about.html" class="nav-item nav-link">About</a>
                    <a href="courses.html" class="nav-item nav-link">Courses</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu fade-down m-0">
                            <a href="team.html" class="dropdown-item">Our Team</a>
                            <a href="testimonial.html" class="dropdown-item">Testimonial</a>

                        </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                    <a href="login.html" class="nav-item nav-link"><i class="fa fa-user"></i></a>
                    <a href="#" class="nav-item nav-link">

                        <div id="google_translate_element">
                        </div>


                    </a>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->

        <!-- Header Start -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Course</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="index.html">Home</a></li>
                                <li class="breadcrumb-item"><a class="text-white" href="courses.html">Course</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">${course.courseName}</li>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->


        <!-- Course Detail Start -->
        <div class="container-xxl py-2">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9 wow fadeInUp">

                        <div class="container">
                            <div class="row g-5 justify-content-center">

                                <div class="col-lg-12 wow fadeInUp" data-wow-delay="0.3s">
                                    <h2>${course.courseName}</h2>
                                    <p>
                                    </p>
                                    <div class="d-flex">
                                        <small><i class="fa fa-star text-warning"></i>
                                            4.6</small>
                                        <small style="margin-left: 15px;"><i class="fa fa-user-graduate"></i> 5.8L+
                                            Learners
                                        </small>
                                        <small style="margin-left: 15px;"><i class="fa fa-user"></i>Beginner</small>
                                        <small style="margin-left: 15px;"><i class="fa fa-clock me-2"></i> 2.0 Hrs</small>

                                    </div>
                                    <div class="image-div text-left mt-3">
                                        <img src="img/testimonial-2.jpg" alt=""
                                             style="height: 40px; width: 40px; border-radius: 50%;">
                                        <span style="margin-left: 10px;"><b>Instructor Name</b> - Zoe Bachman</span>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="container-fluid wow fadeInUp mt-5 tabs">


                            <!-- Tab panes -->
                            <div class="tab-content mt-4">

                                <div class="tab-pane container active" id="Overview">
                                    <h2>About this Course</h2>
                                    <p>${course.description}</p>


                                    <h2 class="mt-4">
                                        Skills you'll gain
                                    </h2>

                                    <span class="badge rounded-pill text-white bg-primary px-4 py-2 m-2"
                                          style="font-size: 15px; font-weight: normal;">Structure pages with HTML</span>

                                    <span class="badge rounded-pill text-white bg-primary px-4 py-2 m-2"
                                          style="font-size: 15px; font-weight: normal;">Present data with tables</span>

                                    <span class="badge rounded-pill text-white bg-primary px-4 py-2 m-2"
                                          style="font-size: 15px; font-weight: normal;">Write cleaner HTML</span>


                                </div>

                                <div class="container" id="Curriculum">

                                    <h2 class="mt-4">
                                        Syllabus
                                    </h2>
                                    <div class="accordion accordion-flush" id="accordionFlushExample">
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                                    Elements and Structure

                                                </button>
                                            </h2>
                                            <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                                                <div class="accordion-body"><ul>
                                                        <li><i class="fa fa-video text-danger"></i> Introduction to HTML</li>
                                                        <li><i class="fa fa-video text-danger"></i> HTMl Document Standards</li>
                                                    </ul></div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                                    Tables
                                                </button>
                                            </h2>
                                            <div id="flush-collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                                                <div class="accordion-body"><ul>
                                                        <li><i class="fa fa-video text-danger"></i> HTML Tables</li>
                                                    </ul></div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                                    Forms
                                                </button>
                                            </h2>
                                            <div id="flush-collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                                                <div class="accordion-body"><ul>
                                                        <li><i class="fa fa-video text-danger"></i> HTML Forms</li>
                                                        <li><i class="fa fa-video text-danger"></i> Form Validation</li>
                                                    </ul></div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                                    Semantic HTML
                                                </button>
                                            </h2>
                                            <div id="flush-collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                                                <div class="accordion-body"><ul>
                                                        <li><i class="fa fa-video text-danger"></i> Semantic HTML</li>
                                                    </ul></div>
                                            </div>
                                        </div>
                                    </div>



                                </div>

                                <div class="container" id="Instructor">
                                    <h2 class="mt-4">About the Instructor</h2>
                                    <div class="image-div text-left mt-4">
                                        <div class="row">
                                            <div class="col-lg-3 col-md-6">
                                                <img src="img/testimonial-2.jpg" alt=""
                                                     style="height: 150px; width: 150px; border-radius: 50%;">
                                            </div>
                                            <div class="col-lg-9 col-md-6 mt-2">
                                                <h5>Zoe Bachman</h5>
                                                <p>Developer</p>
                                                <div class="row">
                                                    <div class="col-6">
                                                        <p><i class="fa fa-star"></i>
                                                            4.87 Instructor rating</p>
                                                    </div>
                                                    <div class="col-6">
                                                        <p> <i class="fa fa-check


                                                               "></i>
                                                            1,533 reviews</p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-6">
                                                        <p><i class="fa fa-user"></i>
                                                            20 Students</p>
                                                    </div>
                                                    <div class="col-6">
                                                        <p><i class="fa fa-video"></i>
                                                            29 courses</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="des mt-4 mb-5">
                                            Curriculum Director at Codecademy and creative technologist. She has designed a range of Codecademy courses, including Learn HTML, Learn C#, Learn Alexa, and the beginner-friendly Learn How to Code.
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>

                    </div>
                    <div class="col-lg-3 col-md-6 shadow wow fadeInUp" data-wow-delay="0.3s">

                        <div class="image text-center">
                            <img class="img-fluid mt-2" src="img/course-1.jpg" alt="" height="200px" width="500px">
                        </div>

                        <h4 class="mt-2 p-2">${course.salePrice}<small></small></h4>

                        <h4 class="mt-2 p-2">$
                            <small><del>${course.price}</del></small>
                        </h4>


                        <div class="buttons">

                            <!--                           <button 
                                class="text-decoration-none text-white btn p-3 w-100 mb-2" 
                                data-toggle="modal" 
                                data-target="#enrollModal">
                                ENROLL NOW
                            </button>-->
                            <a class="text-decoration-none text-white btn p-3 w-100 mb-2" data-bs-toggle="modal" data-bs-target="#enroll_modal">
                                ENROLL NOW</a>



                        </div>
                        <div class="list mt-2">
                            <div class="list1 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-clock"></i> Duration</p>
                                <p>${course.duration} Hrs</p>
                            </div>
                            <div class="list2 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-book"></i> Lectures</p>
                                <p>4</p>
                            </div>
                            <div class="list3 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-bolt"></i> Enrolled</p>
                                <p>240 students</p>
                            </div>
                            <div class="list4 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-google-translate"></i> Language</p>
                                <p>English</p>
                            </div>
                            <div class="list5 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-list"></i> Skill Level</p>
                                <p>Beginner</p>
                            </div>
                            <div class="list6 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-list"></i> Deadline</p>
                                <p>Life Time</p>
                            </div>
                            <div class="list7 d-flex justify-content-between pt-2 border-bottom">
                                <p><i class="fa fa-certificate"></i> Certificate</p>
                                <p>Yes</p>
                            </div>
                            <div class="button pt-4 text-center mb-4">
                                <i class="fa fa-share"></i><a href=""> Share this Course</a>
                            </div>
                        </div>


                    </div>
                </div>
            </div>

        </div>
        <!-- Course Detail End -->


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
                            <!--                            <form action="#">
                                                            <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="email"
                                                                   placeholder="Your email" required>
                                                            <button type="submit"
                                                                    class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2"><a
                                                                    href="mailto:keertidvcorai@gmail.com">Subscribe</a></button>
                                                        </form>-->
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
        <!-- Modal -->
        <div class="modal fade" id="enroll_modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="enrollModalLabel">User Registration Information</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="enrollForm" action="CourseRegisterController" method="post">
                            <input type="hidden" name="courseID" value="${course.courseID}"/>
                            <!-- Full Name -->
                            <div class="form-group">
                                <label for="userNameRegistration">Full Name</label>
                                <input type="text" class="form-control" id="userNameRegistration" name="userNameRegistration" placeholder="Enter your name" value="<%= fullName %>" required>                            </div>
                            <!-- Email -->
                            <div class="form-group">
                                <label for="userEmailRegistration">Email</label>
                                <input type="email" class="form-control" id="userEmailRegistration" name="userEmailRegistration" placeholder="Enter your email"  value="<%= email %>" required>                            </div>
                            <!-- Mobile -->
                            <div class="form-group">
                                <label for="userPhoneRegistration">Mobile</label>
                                <input type="text" class="form-control" id="userPhoneRegistration" name="userPhoneRegistration" placeholder="Enter your phone number" value="<%= phone %>" required>                            </div>
                            <!-- Gender -->
                            <div class="form-group">
                                <label for="userGenderRegistration">Gender</label>
                                <select class="form-control" id="userGenderRegistration" required>
                                    <option value="" disabled selected>Select your gender</option>
                                    <option value="male" <%= "male".equals(gender) ? "selected" : "" %>> Male</option>
                                    <option value="female" <%= "female".equals(gender) ? "selected" : "" %>>Female</option>
                                    <option value="other" <%= "other".equals(gender) ? "selected" : "" %>>Other</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="submitEnroll">Enroll</button>
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
