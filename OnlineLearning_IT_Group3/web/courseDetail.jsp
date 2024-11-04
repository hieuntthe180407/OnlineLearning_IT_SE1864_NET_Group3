<%-- 
    Document   : lesson
    Created on : Sep 17, 2024, 5:15:51 PM
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



                        <div class="container-fluid wow fadeInUp mt-5 tabs">
                            <div class="tab-content mt-4">

                                <div class="tab-pane container active" id="Overview">
                                    <h2>About this Course</h2>
                                    <p><%= c.getDescription()%></p>

                                </div>

                                <div class="container" id="Curriculum">

                                    <%    List<Lesson> listl = (List<Lesson>) request.getAttribute("listl");
                                          List<Review> listr = (List<Review>) request.getAttribute("listr");
                                          User user = (User) session.getAttribute("acc");
                                          if ( listl == null || listl.size() == 0 ) {
                                              out.println("Empty list ");
                                          } else {
                                    
                                    
                                    %>
                                    <h2 class="mt-4">
                                        Syllabus

                                    </h2>
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
                                           displayOption = "both"; // Default to showing both if not set
                                       }
                                    %>

                                    <ul>
                                        <% for (Lesson l : listl) {
                                            if (l.getStatus().equals("Active")) {
                                        %>
                                        <li>
                                            <i class="fa fa-video text-danger"></i>

                                            <% if ("both".equals(displayOption)) { %>
                                            <%= l.getLessonName() %>
                                            <br>
                                            <img src="img/lesson/image1.jpg" alt="<%= l.getLessonName() %>" />
                                            <% } else if ("nameOnly".equals(displayOption)) { %>
                                            <%= l.getLessonName() %>
                                            <% } %>

                                        </li>
                                        <% }} %>
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
                    <!-- Enroll tag start -->
                    <div class="col-lg-3 col-md-6 shadow wow fadeInUp" data-wow-delay="0.3s">

                        <div class="image text-center">
                            <img class="img-fluid mt-2" src="img/course-1.jpg" alt="" height="200px" width="500px">
                        </div>

                        <h4 class="mt-2 p-2"><%=c.getSalePrice()%> <small></small></h4>

                        <h4 class="mt-2 p-2">$
                            <small><del></del></small>
                        </h4>


                        <div class="buttons">
                            <% EnrollDAO e = new EnrollDAO();

                            if(user !=null && e.Enrolled(user.getUserID(),c.getCourseID())){
                            %>
                            <a href="lessonList?courseID=<%=c.getCourseID()%>" class="text-decoration-none text-white btn p-3 w-100 mb-2">ENROLL NOW </a> 
                            <%}else{%>   
                            <a href="Enroll.jsp?CourseID=<%=c.getCourseID()%>&Price=<%=c.getSalePrice()%>&CourseName=<%=c.getCourseName()%>"
                               class="text-decoration-none text-white btn p-3 w-100 mb-2">ENROLL NOW</a>
                            <%}%>


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

                        </div>


                    </div>
                </div>
            </div>
        </div>
        <!-- Enroll tag end -->

        <!-- Course Detail end -->
        <!-- Reviews  -->
        <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container">
                <div class="text-center">
                    <h1 class=" bg-white text-center px-3" style="color: #fb873f;">Reviews</h1>
                    <p class="mb-5">Can Secret Coder courses help your career? Our learners tell us how.</p>
                </div>


                <!-- Hien thi cac reivew cua user -->
                <div class="owl-carousel testimonial-carousel position-relative">
                    <% if (listr == null || listr.size() == 0) {
                        out.println("----------------No REVIEW for this Course yet------------- ");
                    } else {
                        for (Review r : listr) { %>
                    <div class="testimonial-item text-center">
                        <img class="border rounded-circle p-2 mx-auto mb-3" src="<%=r.getUserID().getAvatar()%>" style="width: 80px; height: 80px;">
                        <h5 class="mb-0"><%=r.getUserID().getFullName()%></h5>

                        <!-- Star Rating Display -->
                        <div class="star-rating mb-3">
                            <% int rating = r.getRating(); 
                            for (int i = 5; i >= 1; i--) {
                                if (i <= rating) { %>
                            <span class="filled-star" style="color: #FFD700;">★</span> <!-- Filled star -->
                            <% } else { %>
                            <span class="empty-star">☆</span> <!-- Unfilled star -->
                            <% }
                    } %>
                        </div>

                        <div class="testimonial-text bg-light text-center p-4">
                            <p class="mb-0"><%= r.getReviewContent() %></p>
                        </div>
                    </div>
                    <% } 
    } %>
                </div>

                <!-- form nhap review -->
                <div class="col-lg-12 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                    <form action="ReviewAdd" method="get">
                        <div class="row g-3">
                            <input type="hidden" name="CourseID" value="<%= c.getCourseID() %>">

                            <!-- Star Rating Section -->
                            <div class="col-12">
                                <label for="rating">Your Rating</label>
                                <div id="rating" class="star-rating">
                                    <input type="radio" id="star5" name="rating" value="5" required />
                                    <label for="star5" title="5 stars">★</label>
                                    <input type="radio" id="star4" name="rating" value="4" />
                                    <label for="star4" title="4 stars">★</label>
                                    <input type="radio" id="star3" name="rating" value="3" />
                                    <label for="star3" title="3 stars">★</label>
                                    <input type="radio" id="star2" name="rating" value="2" />
                                    <label for="star2" title="2 stars">★</label>
                                    <input type="radio" id="star1" name="rating" value="1" />
                                    <label for="star1" title="1 star">★</label>
                                </div>
                            </div>

                            <!-- Review Content Section -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <textarea class="form-control" required placeholder="Leave a review here" id="message" style="height: 150px" name="ReviewContent"></textarea>
                                    <label for="review">Your Review</label>
                                </div>
                            </div>

                            <!-- Submit Button -->
                            <div class="col-12">
                                <button class="btn btn-primary w-100 py-3" type="submit">Send Review</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- Style rating star  -->
                <style>
                    .star-rating {
                        direction: rtl;
                        font-size: 2rem;
                        unicode-bidi: bidi-override;
                    }
                    .star-rating input[type="radio"] {
                        display: none;
                    }
                    .star-rating label {
                        color: #ccc;
                        cursor: pointer;
                    }
                    .star-rating input[type="radio"]:checked ~ label {
                        color: #f90;
                    }
                    .star-rating label:hover,
                    .star-rating label:hover ~ label {
                        color: #fc0;
                    }
                </style>






            </div>
        </div>                              
        <!-- Reviews end -->                      
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
