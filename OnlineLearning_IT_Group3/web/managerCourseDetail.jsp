<%-- 
    Document   : managerCourseDetail
    Created on : Oct 19, 2024, 2:34:57 PM
    Author     : DTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mooc List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function showTable(tableId) {
            // Ẩn tất cả các bảng
            document.getElementById("moocTable").style.display = "none";
            document.getElementById("reviewTable").style.display = "none";

            // Hiện bảng tương ứng
            document.getElementById(tableId).style.display = "block";
        }
    </script>
</head>
    <body>
<%@ include file="header.jsp" %>
<div class="container-xxl py-5">
    <div class="container">
        <h2 class="text-center mb-4">Course Details</h2>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${course.courseName}</h5>
                <div class="row">
                    <div class="col-md-6">
                        <img src="${course.courseImg}" alt="${course.courseImg}" class="img-fluid mb-3" />
                    </div>
                    <div class="col-md-6">
                        <p class="card-text"><strong>Duration:</strong> ${course.duration}</p>
                        <p class="card-text"><strong>Report:</strong> ${course.report}</p>
                        <p class="card-text"><strong>ListPrice:</strong> ${course.getPrice()}</p>
                        <p class="card-text"><strong>SalePrice:</strong> ${course.getSalePrice()}</p>
                    </div>
                </div>
                <!-- Nút hiển thị bảng Mooc và Review -->
                <div class="mt-4">
                    <button class="btn btn-primary" onclick="showTable('moocTable')">Show Mooc List</button>
                    <button class="btn btn-primary" onclick="showTable('reviewTable')">Show Reviews</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bảng Mooc -->
<div class="container-xxl py-5" id="moocTable" style="display: none;">
    <div class="container">
        <h2 class="text-center mb-4">Mooc List</h2>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Mooc ID</th>
                    <th>Mooc Name</th>
                    <th>Mooc Number</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listMooc}" var="mooc">
                    <tr>
                        <td>${mooc.moocID}</td>
                        <td>${mooc.moocName}</td>
                        <td>${mooc.moocNumber}</td>
                        <td>
                            <button class="btn btn-warning btn-sm">Edit</button>
                            <button class="btn btn-danger btn-sm">Delete</button>
                            <button class="btn btn-info btn-sm">Detail</button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listMooc}">
                    <tr>
                        <td colspan="3" class="text-center">No Moocs found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<!-- Bảng Review -->
<div class="container-xxl py-5" id="reviewTable" style="display: none;">
    <div class="container">
        <h2 class="text-center mb-4">Reviews</h2>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Review ID</th>
                    <th>User ID</th>
                    <th>Rating</th>
                    <th>Time</th>
                    <th>Review Content</th>
                    <th>Reported</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listReviews}" var="review">
                    <tr>
                        <td>${review.reviewID}</td>
                        <td>${review.userID.fullName}</td>
                        <td>${review.rating}</td>
                        <td>${review.time}</td>
                        <td>${review.reviewContent}</td>
                        <td>${review.isReport == 1 ? 'Yes' : 'No'}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listReviews}">
                    <tr>
                        <td colspan="6" class="text-center">No reviews found.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>

