<%-- 
    Document   : lessonAdd
    Created on : Sep 17, 2024, 6:21:49 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Lesson" %>
<!DOCTYPE html>
<html>
    <head>
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
</head>
<body>
    <script>
     

        // Function to preview image from URL input
        function previewImageFromURL(event) {
            const url = event.target.value;
            const preview = document.getElementById('imagePreview');

            if (url) {
                preview.src = url;
                preview.style.display = 'block';
            } else {
                preview.style.display = 'none';
            }
        }
    </script>
    <%@include file= "header.jsp" %>
    <!--Main container start -->
    <main class="ttr-wrapper">

        <%
                   int CourseID = Integer.parseInt(request.getParameter("CourseID"));
                 
                  Lesson l = (Lesson)request.getAttribute("lesson");
                
                  if (l!=null){
                 
      int LessonID = Integer.parseInt(request.getParameter("LessonID"));
   
        %>
        <!-- Neu trang nhan duoc lessonID thi nno se tro thanh trang update lesson -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Lesson Update</h1>

                    </div>
                </div>
            </div>
        </div>
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
        <div class="container-fluid">

            <div class="row">
                <!-- Form nhap thong tin lessons -->

                <div class="col-lg-12 m-b30">
                    <div class="widget-box">

                        <div class="widget-inner">
                            <form class="edit-profile m-b30 " action="lessonEdit" method="post">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="ml-auto">
                                            <h3>1. Basic info</h3>
                                        </div>
                                    </div>
                                    <input type="hidden" name="LessonID" value="<%= LessonID %>">
                                    <input type="hidden" name="CourseID" value="<%=CourseID%>">

                                    <div class="form-group col-6">
                                        <label class="col-form-label">Lesson Name</label>
                                        <div>
                                            <input class="form-control" type="text" name="lessonName" value="<%=l.getLessonName()%>">
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Lesson URL</label>
                                        <div>
                                            <input class="form-control" type="text" name="lessonUrl" value="<%=l.getLessonURL() %>">
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Lesson Number</label>
                                        <div>
                                            <input class="form-control" type="number" name="LessonNumber" value="<%=l.getLessonNumber()%>">
                                        </div>
                                    </div>
                                        <!-- Insert anh tu Internet -->

<div class="form-group col-6">
                                        <label for="imageURL" class="mt-3">Enter Image URL:</label>
                                        <input type="text" id="imageURL" name="lessonImg" class="form-control" placeholder="Paste image URL here" oninput="previewImageFromURL(event)" value="<%=l.getLessonImg() %>">

                                        <div class="col-12 mt-3">
                                            <img id="imagePreview" src="#"  alt="Image Preview" style="max-width: 100px; display: none;" >
                                        </div>
</div>
                                        <div class="seperator"></div>

                                        <div class="col-12 m-t20">
                                            <div class="ml-auto m-b5">
                                                <h3>2. Description</h3>
                                            </div>
                                        </div>
                                        <div class="form-group col-12">
                                            <label class="col-form-label">Lesson description</label>
                                            <div>
                                                <input class="form-control" type="text" name="description" value="<%=l.getDescription() %>">
                                            </div>
                                        </div>

                                        <div class="col-12">

                                            <button type="submit" class="btn">Confirm</button>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>


            </div>

        </div>
        <%} else{
int MoocID = Integer.parseInt(request.getParameter("MoocID"));
        %>
        <!-- Neu trang khong nhan duoc lessonID thi no se tro thanh trang add lesson theo CourseID -->

        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Add lesson</h1>

                    </div>
                </div>
            </div>
        </div>
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
        <div class="container-fluid">

            <div class="row">
                <!-- Form nhap thong tin lesson -->

                <div class="col-lg-12 m-b30">
                    <div class="widget-box">

                        <div class="widget-inner">
                            <form class="edit-profile m-b30 " action="lessonEdit" method="post">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="ml-auto">
                                            <h3>1. Basic info</h3>
                                        </div>
                                    </div>
                                    <input type="hidden" name="CourseID" value="<%=CourseID%>">
                                    <input type="hidden" name="MoocID" value="<%=MoocID%>">
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Lesson Name</label>
                                        <div>
                                            <input class="form-control" type="text" name="lessonName" >
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Lesson URL</label>
                                        <div>
                                            <input class="form-control" type="text" name="lessonUrl" >
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Lesson Number</label>
                                        <div>
                                            <input class="form-control" type="number" name="LessonNumber">
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="imageURL" class="mt-3">Enter Image URL:</label>
                                        <input type="text" id="imageURL" name="lessonImg" class="form-control" placeholder="Paste image URL here" oninput="previewImageFromURL(event)">

                                        <div class="col-12 mt-3">
                                            <img id="imagePreview" src="#" alt="Image Preview" style="max-width: 100px; display: none;">
                                        </div>
                                    </div>

                                    <div class="seperator"></div>

                                    <div class="col-12 m-t20">
                                        <div class="ml-auto m-b5">
                                            <h3>2. Description</h3>
                                        </div>
                                    </div>
                                    <div class="form-group col-12">
    <label class="col-form-label">Lesson description</label>
    <div>
        <textarea class="form-control" name="description" rows="3" style="resize: vertical;" name="description"></textarea>
    </div>
</div>

                                    <div class="col-12">

                                        <button type="submit" class="btn">Confirm</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


            </div>

        </div>


        <%}%>
    </main>
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
