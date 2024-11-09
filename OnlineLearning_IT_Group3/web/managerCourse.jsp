<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Secret Coder: Courses</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css">
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

        <script>
            $(document).ready(function () {
                // Khởi tạo DataTable với các tùy chọn
                const table = $('#courseTable').DataTable({
                    "paging": true, // Bật phân trang
                    "lengthChange": true, // Cho phép thay đổi số lượng hiển thị trên mỗi trang
                    "searching": true, // Bật tính năng tìm kiếm
                    "ordering": true, // Bật tính năng sắp xếp
                    "info": true, // Hiển thị thông tin về số lượng dữ liệu
                    "autoWidth": false, // Tự động điều chỉnh chiều rộng của cột
                    "responsive": true       // Bật tính năng tương thích với các màn hình nhỏ
                });

                // Khi thay đổi trạng thái trong form, thực hiện lọc
                $('input[name="status"]').change(function () {
                    const status = $(this).val();  // Lấy giá trị trạng thái (active hoặc unactive)

                    if (status === "") {
                        // Nếu trạng thái là "All", hiển thị tất cả các mục
                        table.column(8).search('').draw();  // Cột trạng thái nằm ở vị trí 8
                    } else if (status === "publish") {
                        // Nếu chọn "Publish", lọc chỉ những khóa học có trạng thái "Publish"
                        table.column(8).search('Publish').draw();  // Tìm kiếm và lọc ở cột trạng thái "Publish"
                    } else if (status === "unpublish") {
                        // Nếu chọn "UnPublish", lọc chỉ những khóa học có trạng thái "UnPublish"
                        table.column(8).search('UnPublish').draw();  // Tìm kiếm và lọc ở cột trạng thái "UnPublish"
                    }
                });
            });

        </script>


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


                <!-- Nút Add New Course -->
                <div class="d-flex justify-content-between mb-3">
                    <div>
                        <label><input type="radio" name="status" value="" checked>All</label>
                        <label><input type="radio" name="status" value="publish">Publish</label>
                        <label><input type="radio" name="status" value="unpublish">UnPublish</label>
                    </div>
                    <a href="addCourse" class="btn btn-success">Add New Course</a>
                </div>
                <table id="courseTable" class="table table-bordered table-striped">
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
                            <th>Publish</th>
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
                                            <span class="badge bg-success">Publish</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">UnPublish</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="courseDetail?courseID=${course.courseID}">
                                        <button class="btn btn-primary">Detail</button>
                                    </a>
                                    <a href="mooc?courseID=${course.courseID}">
                                        <button class="btn btn-primary">Mooc</button>
                                    </a>
                                    <a href="updateCourse?courseID=${course.courseID}">
                                        <button class="btn btn-primary">Update Course</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty listCourse}">
                            <tr>
                                <td colspan="10" class="text-center">There is no course.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>

            </div>
        </div>

        <%@include file="footer.jsp" %>

    </body>

</html>
