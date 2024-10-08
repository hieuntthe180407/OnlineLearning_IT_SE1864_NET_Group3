<%-- 
    Document   : courseEdit
    Created on : Sep 30, 2024, 1:54:11 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.*" %>

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
function previewImage(event) {
    const imageInput = event.target;
    const imagePreview = document.getElementById('imagePreview');
    
    if (imageInput.files && imageInput.files[0]) {
        const file = imageInput.files[0];
        const imageURL = URL.createObjectURL(file); // Create a URL for the file
        
        imagePreview.src = imageURL; // Use the URL as the source for the image preview
        imagePreview.style.display = 'block'; // Show the image preview
    } else {
        imagePreview.style.display = 'none'; // Hide image preview if no file selected
    }
}
</script>

        <%@include file= "header.jsp" %>
        <!--Main container start -->
	<main class="ttr-wrapper">
             <% List<Category> list = (List<Category>) request.getAttribute("listCa");
                 Course c = (Course)request.getAttribute("Course");
                if (c!=null){
                 
    int courseID = Integer.parseInt(request.getParameter("courseID"));
    
            %>
            <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Course Edit</h1>
                    
                </div>
            </div>
        </div>
    </div>
		<div class="container-fluid">
                    
			<div class="row">
				<!-- Your Profile Views Chart -->
                                
				<div class="col-lg-12 m-b30">
					<div class="widget-box">
						
						<div class="widget-inner">
                                                    <form class="edit-profile m-b30 " action="courseEdit" method="post">
								<div class="row">
									<div class="col-12">
										<div class="ml-auto">
											<h3>1. Category</h3>
										</div>
									</div>
                                                                    <div class="col-12">
   


                                                                    <input type="hidden" name="CourseID" value="<%= courseID %>">
									<div class="form-group col-6">
	<select id="categorySelect" name="category" class="form-control">
        <option value="">-- Select a Category --</option>
       <% 
            
            if (list != null) {
                for (Category ca : list) {
        %>
                    <option name = "CategoryID" value="<%= ca.getCategoryID() %>"><%= ca.getCategoryName() %></option>
        <% 
                }
            }


        %>
    </select>
										
									</div>
                                                                                
                                                                                <div class="ml-auto">
											<h3>2.Course Name</h3>
										</div>
    <img src="img/Course/course1.jpg" />
									</div>
                                                                    
									<div class="form-group col-6">
										
										<div>
											<input class="form-control" type="text" name="courseName" value="<%=c.getCourseName()%>">
										</div>
									</div>
									
									
									<div class="seperator"></div>
									
									<div class="col-12 m-t20">
										<div class="ml-auto m-b5">
											<h3>3. Description</h3>
										</div>
									</div>
									<div class="form-group col-12">
										
										<div>
											<input class="form-control" type="text" name="description" value="<%=c.getDescription()%>">
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
                
		
            <%} else{%>
           <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Add Course</h1>
                    
                </div>
            </div>
        </div>
    </div>
                    <div class="container-fluid">
                    
			<div class="row">
				<!-- Your Profile Views Chart -->
                                
				<div class="col-lg-12 m-b30">
					<div class="widget-box">
						
						<div class="widget-inner">
                                                    <form class="edit-profile m-b30 " action="courseEdit" method="post">
								<div class="row">
									<div class="col-12">
										<div class="ml-auto">
											<h3>1. Category</h3>
										</div>
									</div>
                                                                    
									<div class="form-group col-6">
										
										<select id="categorySelect" name="category" class="form-control">
        <option value="">-- Select a Category --</option>
       <% 
            
            if (list != null) {
                for (Category ca : list) {
        %>
                    <option value="<%= ca.getCategoryID() %>"><%= ca.getCategoryName() %></option>
        <% 
                }
            }


        %>
    </select>
									</div>
                                                                     
                                                                         <div class="col-12 m-t20">       
                                                                                <div class="ml-auto">
											<h3>2.Course Name</h3>
										</div>
									</div>
                                                                    
									<div class="form-group col-6">
										
										<div>
											<input class="form-control" type="text" name="courseName" value="">
										</div>
									</div>
									<div class="col-12">
    <label for="imageUpload">Upload Image:</label>
    <input type="file" id="imageUpload" name="image" accept="image/*" class="form-control" onchange="previewImage(event)">
</div>

<div class="col-12 mt-3">
    <img id="imagePreview" src="#" alt="Image Preview" style="max-width: 100px; display: none;">
</div>
									
									<div class="seperator"></div>
									
									<div class="col-12 m-t20">
										<div class="ml-auto m-b5">
											<h3>3. Description</h3>
										</div>
									</div>
									<div class="form-group col-12">
										
										<div>
											<input class="form-control" type="text" name="description" value="">
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

