<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fullstack Website</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .courses img {
                height: 200px;
                object-fit: cover;
            }
            footer {
                background-color: #343a40;
                color: white;
                padding: 20px 0;
            }
            footer a {
                color: white;
            }
        </style>
    </head>
    <body>

        <!-- Header -->
        <%@include file="header.jsp" %>

        <!-- Slider Section -->
        <div id="courseSlider" class="carousel slide mt-4" data-bs-ride="carousel">
            <div class="carousel-inner">
                <c:forEach items="${sliders}" var="slider" varStatus="status">
                    <div class="carousel-item ${status.first ? 'active' : ''}">
                        <img src="${slider.imageUrl}" class="d-block w-100" alt="${slider.title}">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>${slider.title}</h5>
                            <p><a href="${slider.backLink}" class="btn btn-primary">Learn More</a></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#courseSlider" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#courseSlider" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <!-- Featured Courses Section -->
        <section class="courses py-5">
            <div class="container">
                <h2 class="text-left mb-4">Featured Courses</h2>
                <div class="row">
                    <c:forEach items="${featuredCourses}" var="course">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <img src="${course.courseImg}" class="card-img-top" alt="${course.courseName}">
                                <div class="card-body">
                                    <h5 class="card-title">${course.courseName}</h5>
                                    <p class="card-text">${course.description}</p>
                                    <a href="courseDetailUserView?courseId=${course.courseID}" class="btn btn-primary">View Course</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- Hot Posts Section -->
        <section class="posts py-5">
            <div class="container">
                <h2 class="text-left mb-4">Hot Posts</h2>
                <div class="row">
                    <c:forEach items="${hotPosts}" var="post">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <img src="${post.featuredImage}" class="card-img-top" alt="${post.title}">
                                <div class="card-body">
                                    <h5 class="card-title">${post.title}</h5>
                                    <p class="card-text">Published on: ${post.createdAt}</p>
                                    <a href="BlogUserViewDetailController?blogId=${post.blogId}" class="btn btn-primary">Read More</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- Footer Section -->
        <footer class="text-center">
            <div class="container">
                <p>&copy; 2024 Fullstack Development. All rights reserved.</p>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
                    <li class="list-inline-item"><a href="#">Terms of Service</a></li>
                    <li class="list-inline-item"><a href="#">Contact</a></li>
                </ul>
            </div>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
