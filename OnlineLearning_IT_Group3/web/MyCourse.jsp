<%-- 
    Document   : MyCourse
    Created on : Nov 6, 2024, 2:03:19 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.*" %>

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
         <%@include file= "header.jsp" %>
         <div class="container-xxl py-5">
        <div class="container">
            
            <div class="row g-4 py-2">
         <%
             
             List<Course> list = (List<Course>) request.getAttribute("list");
                                          
                                          if ( list == null || list.size() == 0 ) {
                                              out.println("You haven't enrolled in a course yet ");
                                          } else {
         for(Course c: list)
         {

         %>
         <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="course-item shadow">
                        <div class="position-relative overflow-hidden text-light image">
                            <img class="img-fluid" src="<%=c.getCourseImg()%>" alt="<%= c.getCourseName()%>">
                            <div style="position:absolute;top: 15px;left: 16px; font-size:12px; border-radius:3px; background-color:#fb873f;"
                                class="px-2 py-1 fw-bold text-uppercase">FREE</div>

                        </div>
                        <div class="p-2 pb-0">

                            <h5 class="mb-1"><a href="lessonList?courseID=<%=c.getCourseID()%>" class="text-dark"><%= c.getCourseName()%></a> </h5>
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
                            <small class="flex-fill text-left p-2 px-2"><i class="fa fa-clock me-2"></i> <%=c.getDuration()%></small>
                            
                 
                        </div>
                    </div>
                </div>
                                                    <%}}%>
         
                
            </div>

        </div>
    </div>
          <%@include file= "footer.jsp" %>
    </body>
</html>
