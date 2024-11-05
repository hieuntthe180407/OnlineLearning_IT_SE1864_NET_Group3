<%-- 
    Document   : courseDetailTeacher
    Created on : Nov 4, 2024, 8:58:51 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="dal.*"%>
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
            Integer itemsPerPage = (Integer) request.getAttribute("itemsPerPage");  
    if (itemsPerPage == null) {
        itemsPerPage = 10;  // Default to 10 items per page
    }
        %>
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown"><%=c.getCourseName()%></h1>

                    </div>
                </div>
            </div>
        </div>
        <!-- hien thi thong bao loi(neu co) -->
        <% String err = request.getParameter("err");
         if(err!=null)
         {
        %>
        <h3 style="color: red; text-align: center; font-weight: bold;">
            <%= err %>
        </h3>
        <%
    }
        %>

        <!-- Course Detail started -->
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

                                    <div class="image-div text-left mt-3">
                                        <img src="img/testimonial-2.jpg" alt=""
                                             style="height: 40px; width: 40px; border-radius: 50%;">
                                        <span style="margin-left: 10px;"><b>Instructor Name</b> <%=c.getUserId().getFullName()%></span>
                                    </div>
                                </div>
                            </div>
                        </div>


                                    <!-- The about -->
                        <div class="container-fluid wow fadeInUp mt-5 tabs">
                            <div class="tab-content mt-4">

                                <div class="tab-pane container active" id="Overview">
                                    <h2>About this Course</h2>
                                    <p><%= c.getDescription()%></p>

                                </div>

                                <div class="container" id="Curriculum">
                                    <!-- Lay list review la list lesson  -->
                                    <%    List<Lesson> listl = (List<Lesson>) request.getAttribute("listl");
                                          List<Review> listr = (List<Review>) request.getAttribute("listr");
                                          //neu list lesson ma null thi in ra empty list
                                          if ( listl == null || listl.size() == 0 ) {
                                              out.println("Empty list ");
                                          } else {
                                    
                                    
                                  
                                   
                                    %>
                                    <h2 class="mt-4">
                                        Syllabus
                                        <a type="button" href="lessonEdit.jsp?CourseID=<%= c.getCourseID() %>"> Add</a>
                                    </h2>
                                    <!--Form de nhap so luong lesson moi trang va che do hien thi
                                    chi hien thi ten hoac ca ten va anh theo kem-->
                                    <form action="courseDetail" method="GET">
                                        <input type="hidden" name="courseID" value="<%= c.getCourseID() %>">
                                        <label for="itemsPerPage">Lessons per page:</label>
                                        <input type="number" name="itemsPerPage" id="itemsPerPage" value="<%= itemsPerPage %>" min="1"  />
                                        <label for="displayOption">Choose display mode:</label>
                                        <select name="displayOption" id="displayOption" >
                                            <option value="both" <%= "both".equals(request.getParameter("displayOption")) ? "selected" : "" %>>Image and Name</option>
                                            <option value="nameOnly" <%= "nameOnly".equals(request.getParameter("displayOption")) ? "selected" : "" %>>Name Only</option>
                                        </select>
                                        <input type="submit" value="submit"/>
                                    </form>





                                    <% String displayOption = request.getParameter("displayOption");
   if (displayOption == null) {
       displayOption = "both"; // mac dinh hien thi ra tat ca
   }
                                    %>

                                    <ul>
                                        <% for (Lesson l : listl) { %>
                                        <li>
                                            <i class="fa fa-video text-danger"></i>
                                            <!-- neu user chon chi hien thi ca ten va anh -->
                                            <% if ("both".equals(displayOption)) { %>
                                            <%= l.getLessonName() %>
                                            <a href="lessonEdit?LessonID=<%= l.getLessonID() %>&CourseID=<%= c.getCourseID() %>">
                                                <button type="button">Update</button>
                                            </a>
                                                <!-- Disabled va Active button -->
                                            <% if (l.getStatus().equals("Active")) { %>
                                            <a type="button" href="editStatusLesson?LessonID=<%= l.getLessonID() %>&status=Disabled&courseID=<%= c.getCourseID() %>">Disabled</a>
                                            <% } else if (l.getStatus().equals("Disabled")) { %>
                                            <a type="button" href="editStatusLesson?LessonID=<%= l.getLessonID() %>&status=Active&courseID=<%= c.getCourseID() %>">Active</a>
                                            <% } %>
                                            <br>
                                            <img src="img/lesson/image1.jpg" alt="<%= l.getLessonName() %>" />
                                            <!-- neu user chon chi hien thi ten -->
                                            <% } else if ("nameOnly".equals(displayOption)) { %>
                                            <%= l.getLessonName() %>
                                            <a href="lessonEdit?LessonID=<%= l.getLessonID() %>&CourseID=<%= c.getCourseID() %>">
                                                <button type="button">Update</button>
                                            </a>
                                                <!-- Disabled va Active button -->
                                            <% if (l.getStatus().equals("Active")) { %>
                                            <a type="button" href="editStatusLesson?LessonID=<%= l.getLessonID() %>&status=Disabled&courseID=<%= c.getCourseID() %>">Disabled</a>
                                            <% } else if (l.getStatus().equals("Disabled")) { %>
                                            <a type="button" href="editStatusLesson?LessonID=<%= l.getLessonID() %>&status=Active&courseID=<%= c.getCourseID() %>">Active</a>
                                            <% } %>
                                            <% } %>

                                        </li>
                                        <% } %>
                                    </ul>


                                    <!-- lay thong tin page hien tai -->
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
                                            <li><a href="courseDetail?page=<%= currentPage - 1 %>&courseID=<%= c.getCourseID()%>&itemsPerPage=<%=itemsPerPage%>&displayOption=<%=displayOption%>">&laquo; Previous</a></li>
                                                <%
                                                    }

                                                    for (int i = 1; i <= totalPages; i++) {
                                                        if (i == currentPage) {
                                                %>
                                            <li><span><%= i %></span></li>
                                                    <%
                                                            } else {
                                                    %>
                                            <li><a href="courseDetail?page=<%= i %>&courseID=<%= c.getCourseID()%>&itemsPerPage=<%=itemsPerPage%>&displayOption=<%=displayOption%>"><%= i %></a></li>
                                                <%
                                                        }
                                                    }

                                                    if (currentPage < totalPages) {
                                                %>
                                            <li><a href="courseDetail?page=<%= currentPage + 1 %>&courseID=<%= c.getCourseID()%>&itemsPerPage=<%=itemsPerPage%>&displayOption=<%=displayOption%>">Next &raquo;</a></li>
                                                <%
                                                    }
                                                %>
                                        </ul>
                                    </div>     
                                </div>

                            </div>
                        </div>
                        <%}%>





                        <!-- Thong tin giang vien -->
                        <div class="container" id="Instructor">
                            <h2 class="mt-4">About the Instructor</h2>
                            <div class="image-div text-left mt-4">
                                <div class="row">
                                    <div class="col-lg-3 col-md-6">
                                        <img src="<%=c.getUserId().getAvatar()%>" alt=""
                                             style="height: 150px; width: 150px; border-radius: 50%;">
                                    </div>
                                    <div class="col-lg-9 col-md-6 mt-2">
                                        <h5><%=c.getUserId().getFullName()%></h5>



                                    </div>
                                </div>

                                <div class="des mt-4 mb-5">
                                    <%=c.getUserId().getAbout()%>
                                </div>
                            </div>
                        </div>





                    </div>

                </div>
            </div>
        </div>
        <!-- Enroll tag end -->

        <!-- Course Detail end -->







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