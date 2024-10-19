<%-- 
    Document   : lesson
    Created on : Sep 17, 2024, 5:15:51 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
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
        
        <%
            Course c = (Course)request.getAttribute("Course");
        %>
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Edit Course Detail</h1>
                    
                </div>
            </div>
        </div>
    </div>
        <div class="container-xxl py-2">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 wow fadeInUp">

                <div class="container">
                    <div class="row g-5 justify-content-center">

                        <div class="col-lg-12 wow fadeInUp" data-wow-delay="0.3s">
                            <h2><%=c.getCourseName()%></h2>
                            <p>
                                <%=c.getDescription()%>
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
         <div class="tab-content mt-4">

                        <div class="tab-pane container active" id="Overview">
                            <h2>About this Course</h2>
                            <p><%= c.getDescription()%></p>

                        </div>
        
         <div class="container" id="Curriculum">
                            <h2 class="mt-4">
                                Syllabus
                            </h2>
              <% 
                    List<Mooc> listm = (List<Mooc>) request.getAttribute("listm");
                    List<Lesson> listl = (List<Lesson>) request.getAttribute("listl");
                    List<Review> listr = (List<Review>) request.getAttribute("listr");
                    if (listm == null || listm.size() == 0 || listl == null || listl.size() == 0 ) {
                        out.println("Empty list ");
                    } else {
                        for (Mooc m : listm) {
                           
                %>  

                            
                            <div class="accordion accordion-flush" id="accordionFlushExample">
                                
                                
                                
                                <div class="accordion-item">
                                  <h2 class="accordion-header">
                                     
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                        <%= m.getMoocName()%>  <a href="lessonEdit.jsp?MoocID=<%= m.getMoocID()%>"><input type="button" value="Add"/></a>
                                    
                                    </button>
                                        
                                        
                                  </h2>
                                    
                                    
                                  <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="
                                       
                                    <div class="accordion-body"><ul>
                                          <% for(Lesson l : listl) {
                                            if(l.getMoocID()== m.getMoocID()){
                                        %>
                                        <li><i class="fa fa-video text-danger"></i>
                                            
                                            <%= l.getLessonName() %>
                                            <a href="lessonEdit?LessonID=<%= l.getLessonID() %>">
                                                <button type="button">Update</button></a>
                                                
                                                    <%if(l.getStatus().equals("Active")){   %>
                                                    <a type="button" href="editStatusLesson?LessonID=<%= l.getLessonID() %>&status=Deactive&courseID=<%=c.getCourseID()%>" > Deactive</a>
                                                <%} else if(l.getStatus().equals("Deactive")) {%>
                                                    <a type="button" href="editStatusLesson?LessonID=<%= l.getLessonID() %>&status=Active&courseID=<%=c.getCourseID()%>" > Active</a>
                                                    <%}%>
                                               
                                                <br> 
                                                <img src="img/lesson/image1.jpg"/>
                                        </li>
                                        
                                        <%}}%>
                                        
                                    </ul>
                                   <% 
    int currentPage = (Integer) request.getAttribute("currentPage");
    int totalPages = (Integer) request.getAttribute("totalPages");
%>

<!-- Pagination Controls -->
<div class="pagination">
    <ul>
        <%
            if (currentPage > 1) {
        %>
        <li><a href="courseDetail?page=<%= currentPage - 1 %>&courseID=<%= c.getCourseID()%>">&laquo; Previous</a></li>
        <%
            }

            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
        %>
        <li><span><%= i %></span></li>
        <%
                } else {
        %>
        <li><a href="courseDetail?page=<%= i %>&courseID=<%= c.getCourseID()%>"><%= i %></a></li>
        <%
                }
            }

            if (currentPage < totalPages) {
        %>
        <li><a href="courseDetail?page=<%= currentPage + 1 %>&courseID=<%= c.getCourseID()%>">Next &raquo;</a></li>
        <%
            }
        %>
    </ul>
</div>     
                                  </div>
                                  
                                  </div>
                                </div>
                                    <%}}%>
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
                
                <h4 class="mt-2 p-2">Free <small></small></h4>
                
                <h4 class="mt-2 p-2">$
                    <small><del>20</del></small>
                </h4>
                

                <div class="buttons">
                    
                    <a href="#"
                        class="text-decoration-none text-white btn p-3 w-100 mb-2">ENROLL NOW</a>
                    
                    
                   
                </div>
                <div class="list mt-2">
                    <div class="list1 d-flex justify-content-between pt-2 border-bottom">
                        <p><i class="fa fa-clock"></i> Duration</p>
                        <p>2.0 Hrs</p>
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

 <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
        <div class="container">
            <div class="text-center">
                <h1 class=" bg-white text-center px-3" style="color: #fb873f;">Success stories</h1>
                <p class="mb-5">Can Secret Coder courses help your career? Our learners tell us how.</p>
            </div>
            
            
            
            <div class="owl-carousel testimonial-carousel position-relative">
                <%if (listr == null || listr.size() == 0 ) {
                        out.println("No REVIEW ");
                    } else {
                        for (Review r : listr) {%>
                <div class="testimonial-item text-center">
                    <img class="border rounded-circle p-2 mx-auto mb-3" src="img/testimonial-1.jpg"
                        style="width: 80px; height: 80px;">
                    <h5 class="mb-0">Sarah K.</h5>
                    <div class="testimonial-text bg-light text-center p-4">
                        <p class="mb-0"><%=r.getReviewContent()%></p>
                    </div>
                </div>
               <%}}%>
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
