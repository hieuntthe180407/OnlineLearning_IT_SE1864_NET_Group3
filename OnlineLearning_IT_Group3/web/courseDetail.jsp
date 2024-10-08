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
