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
        <div class="container mt-5">
            <h2>Blog Detail</h2>
            <div class="card mt-3">
                <img src="${blog.featuredImage}" class="card-img-top" alt="${blog.title}">
                <div class="card-body">
                    <h3 class="card-title">${blog.title}</h3>
                    <p class="card-text">${blog.content}</p>
                    <p><strong>Status:</strong> ${blog.status}</p>
                    <p><strong>Created At:</strong> ${blog.createdAt}</p>
                    <p><strong>Updated At:</strong> ${blog.updatedAt}</p>
                </div>
            </div>
        </div>
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
