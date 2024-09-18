<%-- 
    Document   : lessonAdd
    Created on : Sep 17, 2024, 6:21:49 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <%@include file= "header.jsp" %>
        <!--Main container start -->
	<main class="ttr-wrapper">
		<div class="container-fluid">
			<div class="db-breadcrumb">
				<h4 class="breadcrumb-title">Add listing</h4>
				<ul class="db-breadcrumb-list">
					<li><a href="#"><i class="fa fa-home"></i>Home</a></li>
					<li>Add listing</li>
				</ul>
			</div>	
			<div class="row">
				<!-- Your Profile Views Chart -->
				<div class="col-lg-12 m-b30">
					<div class="widget-box">
						<div class="wc-title">
							<h4>Add listing</h4>
						</div>
						<div class="widget-inner">
							<form class="edit-profile m-b30">
								<div class="row">
									<div class="col-12">
										<div class="ml-auto">
											<h3>1. Basic info</h3>
										</div>
									</div>
									<div class="form-group col-6">
										<label class="col-form-label">Course title</label>
										<div>
											<input class="form-control" type="text" value="">
										</div>
									</div>
									<div class="form-group col-6">
										<label class="col-form-label">Course title</label>
										<div>
											<input class="form-control" type="text" value="">
										</div>
									</div>
									<div class="form-group col-6">
										<label class="col-form-label">Course start</label>
										<div>
											<input class="form-control" type="text" value="">
										</div>
									</div>
									<div class="form-group col-6">
										<label class="col-form-label">Course expire</label>
										<div>
											<input class="form-control" type="text" value="">
										</div>
									</div>
									<div class="form-group col-6">
										<label class="col-form-label">Teacher name</label>
										<div>
											<input class="form-control" type="text" value="">
										</div>
									</div>
									<div class="seperator"></div>
									
									<div class="col-12 m-t20">
										<div class="ml-auto m-b5">
											<h3>2. Description</h3>
										</div>
									</div>
									<div class="form-group col-12">
										<label class="col-form-label">Course description</label>
										<div>
											<textarea class="form-control"> </textarea>
										</div>
									</div>
									<div class="col-12 m-t20">
										<div class="ml-auto">
											<h3 class="m-form__section">3. Add Item</h3>
										</div>
									</div>
									<div class="col-12">
										<table id="item-add" style="width:100%;">
											<tr class="list-item">
												<td>
													<div class="row">
														<div class="col-md-4">
															<label class="col-form-label">Course Name</label>
															<div>
																<input class="form-control" type="text" value="">
															</div>
														</div>
														<div class="col-md-3">
															<label class="col-form-label">Course Category</label>
															<div>
																<input class="form-control" type="text" value="">
															</div>
														</div>
														<div class="col-md-3">
															<label class="col-form-label">Course Category</label>
															<div>
																<input class="form-control" type="text" value="">
															</div>
														</div>
														<div class="col-md-2">
															<label class="col-form-label">Close</label>
															<div class="form-group">
																<a class="delete" href="#"><i class="fa fa-close"></i></a>
															</div>
														</div>
													</div>
												</td>
											</tr>
										</table>
									</div>
									<div class="col-12">
										<button type="button" class="btn-secondry add-item m-r5"><i class="fa fa-fw fa-plus-circle"></i>Add Item</button>
										<button type="reset" class="btn">Save changes</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Your Profile Views Chart END-->
			</div>
		</div>
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
