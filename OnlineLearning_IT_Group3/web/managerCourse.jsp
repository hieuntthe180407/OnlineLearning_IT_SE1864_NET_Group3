<%-- 
    Document   : managerCourse
    Created on : Oct 19, 2024, 1:11:11 PM
    Author     : DTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
  <body>
        <%@include file="header.jsp" %>

        <!-- Header Start -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Courses</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="index.html">Home</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">ManagerCourse</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->

        <div class="container-xxl py-5">
            <div class="container">
                <!-- Thêm ô tìm kiếm -->
                <div class="mb-3">
                    <input type="text" id="searchInput" placeholder="Search course..." class="form-control" oninput="searchCourses()" />
                </div>

                <!-- Thêm lựa chọn trạng thái -->
                <div class="mb-3">
                    <label><input type="radio" name="status" value="" checked onclick="searchCourses()">All</label>
                    <label><input type="radio" name="status" value="active" onclick="searchCourses()">Active</label>
                    <label><input type="radio" name="status" value="inactive" onclick="searchCourses()">UnActive</label>
                </div>

                <!-- Nút sắp xếp -->
                <button class="btn btn-info mb-3" onclick="sortCourses()">Sort by name</button>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name course</th>
                            <th>Thời lượng</th>
                            <th>Báo cáo</th>
                            <th>Image</th>
                            <th>Mô tả</th>
                            <th>Price</th>
                            <th>Giá khuyến mãi</th>
                            <th>Active</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${listCourse}">
                            <tr>
                                <td>${course.courseID}</td>
                                <td>${course.courseName}</td>
                                <td>${course.duration}</td>
                                <td>${course.report}</td>
                                <td><img src="${course.courseImg}" alt="Course Image" width="100" class="img-fluid rounded" /></td>
                                <td>${course.description}</td>
                                <td>${course.price}</td>
                                <td>${course.salePrice}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${course.isActive}">
                                            <span class="badge bg-success">Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">UnActive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="managerCourseDetail?id=${course.courseID}">
                                        <button>Detail</button>
                                    </a>

                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty listCourse}">
                            <tr>
                                <td colspan="9" class="text-center">There is no course.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>

                <!-- Phân trang -->
                <div class="d-flex justify-content-end align-items-center mb-2">
                    <button id="prevBtn" class="btn btn-secondary pagination-button" onclick="changePage(currentPage - 1)" disabled>Previous</button>
                    <div id="pagination" class="d-flex"></div> <!-- Hiển thị số trang -->
                    <button id="nextBtn" class="btn btn-secondary pagination-button" onclick="changePage(currentPage + 1)">Next </button>
                </div>
                <div id="pageInfo" class="text-center mt-2"></div> <!-- Hiển thị thông tin trang -->
            </div>
        </div>

        <%@include file="footer.jsp" %>

    </body>

</html>

