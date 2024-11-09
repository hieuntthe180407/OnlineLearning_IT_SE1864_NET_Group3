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
                const table = $('#courseTable').DataTable({
                    "paging": true,
                    "lengthChange": true,
                    "searching": true,
                    "ordering": true,
                    "info": true,
                    "autoWidth": false,
                    "responsive": true
                });

                $('input[name="status"]').change(function () {
                    const status = $(this).val();

                    // Lọc dựa trên trạng thái
                    if (status === "") {
                        // Hiển thị tất cả
                        table.columns(8).search('').draw();
                    } else {
                        // Lọc theo trạng thái
                        const searchValue = status === "active" ? 'Active' : 'UnActive';
                        table.columns(8).search(searchValue).draw();
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
                        <label><input type="radio" name="status" value="active">Active</label>
                        <label><input type="radio" name="status" value="inactive">UnActive</label>
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
