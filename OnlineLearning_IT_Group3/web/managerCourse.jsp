<%-- 
    Document   : managerCourse
    Created on : Oct 19, 2024, 1:11:11 PM
    Author     : DTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Secret Coder : Courses</title>
        <style>
            /* CSS để làm cho nút phân trang nhỏ hơn */
            .pagination-button {
                padding: 5px 10px;
                margin: 0 2px;
                font-size: 12px;
            }
        </style>
        <script>
            let currentPage = 1; // Trang hiện tại
            const rowsPerPage = 5; // Số dòng mỗi trang
            let filteredCourses = []; // Mảng chứa các khóa học đã lọc
            let sortDirection = 'asc'; // Hướng sắp xếp

            function displayTableRows() {
                const rows = document.querySelectorAll("tbody tr");
                const totalRows = filteredCourses.length;
                const totalPages = Math.ceil(totalRows / rowsPerPage);

                // Ẩn tất cả các dòng
                rows.forEach((row) => {
                    row.style.display = 'none';
                });

                // Tính toán chỉ số bắt đầu và kết thúc cho trang hiện tại
                const start = (currentPage - 1) * rowsPerPage;
                const end = start + rowsPerPage;

                // Hiển thị dòng cho trang hiện tại
                for (let i = start; i < end && i < totalRows; i++) {
                    const rowIndex = filteredCourses[i];
                    rows[rowIndex].style.display = 'table-row';
                }

                // Cập nhật trạng thái của nút
                document.getElementById('prevBtn').disabled = currentPage === 1;
                document.getElementById('nextBtn').disabled = currentPage === totalPages;

                // Cập nhật số trang
                updatePagination(totalPages);
            }

            function changePage(pageNumber) {
                currentPage = pageNumber;
                displayTableRows();
            }

            function updatePagination(totalPages) {
                const pagination = document.getElementById('pagination');
                pagination.innerHTML = ''; // Xóa nội dung hiện tại

                // Hiển thị trang đầu tiên
                if (currentPage > 1) {
                    const firstButton = document.createElement('button');
                    firstButton.textContent = '1';
                    firstButton.className = 'btn btn-secondary pagination-button';
                    firstButton.onclick = () => changePage(1);
                    pagination.appendChild(firstButton);
                }

                // Hiển thị ... nếu trang hiện tại lớn hơn 3
                if (currentPage > 3) {
                    const ellipsis = document.createElement('span');
                    ellipsis.textContent = '...';
                    pagination.appendChild(ellipsis);
                }

                // Tính toán chỉ số bắt đầu và kết thúc để hiển thị trang
                let startPage, endPage;

                if (totalPages <= 5) {
                    // Nếu tổng số trang nhỏ hơn hoặc bằng 5, hiển thị tất cả
                    startPage = 1;
                    endPage = totalPages;
                } else {
                    // Nếu tổng số trang lớn hơn 5
                    startPage = Math.max(2, currentPage - 1); // Bắt đầu từ trang hiện tại - 1
                    endPage = Math.min(totalPages - 1, startPage + 2); // Kết thúc là trang hiện tại + 1

                    // Đảm bảo trang đầu và cuối luôn hiển thị
                    if (currentPage === 2) {
                        endPage = startPage + 2;
                    } else if (currentPage === totalPages - 1) {
                        startPage = endPage - 2;
                    }
                }

                // Tạo nút số trang
                for (let i = startPage; i <= endPage; i++) {
                    const pageButton = document.createElement('button');
                    pageButton.textContent = i;
                    pageButton.className = (i === currentPage) ? 'btn btn-primary pagination-button' : 'btn btn-secondary pagination-button';
                    pageButton.onclick = () => changePage(i);
                    pagination.appendChild(pageButton);
                }

                // Hiển thị ... nếu trang hiện tại nhỏ hơn tổng trang - 2
                if (currentPage < totalPages - 2) {
                    const ellipsis = document.createElement('span');
                    ellipsis.textContent = '...';
                    pagination.appendChild(ellipsis);
                }

                // Hiển thị trang cuối cùng
                if (totalPages > 1) {
                    const lastButton = document.createElement('button');
                    lastButton.textContent = totalPages;
                    lastButton.className = 'btn btn-secondary pagination-button';
                    lastButton.onclick = () => changePage(totalPages);
                    pagination.appendChild(lastButton);
                }

                // Cập nhật thông tin trang
                document.getElementById('pageInfo').textContent = `Trang ${currentPage} của ${totalPages}`;
            }

            function searchCourses() {
                const searchTerm = document.getElementById('searchInput').value.toLowerCase();
                const selectedStatus = document.querySelector('input[name="status"]:checked')?.value;

                filteredCourses = [];

                const rows = Array.from(document.querySelectorAll("tbody tr"));

                rows.forEach((row, index) => {
                    const courseName = row.cells[1].textContent.toLowerCase();
                    const courseStatus = row.cells[8].textContent.trim(); // Trạng thái trong cột thứ 8

                    const matchesSearchTerm = courseName.includes(searchTerm);
                    const matchesStatus = !selectedStatus || (selectedStatus === "active" && courseStatus === "Hoạt động") ||
                            (selectedStatus === "inactive" && courseStatus === "Không hoạt động");

                    if (matchesSearchTerm && matchesStatus) {
                        filteredCourses.push(index);
                    }
                });

                currentPage = 1; // Reset về trang đầu khi tìm kiếm
                displayTableRows();
            }

            function sortCourses() {
                const rows = Array.from(document.querySelectorAll("tbody tr"));

                filteredCourses.sort((a, b) => {
                    const courseA = rows[a].cells[1].textContent.toLowerCase();
                    const courseB = rows[b].cells[1].textContent.toLowerCase();

                    if (sortDirection === 'asc') {
                        return courseA.localeCompare(courseB);
                    } else {
                        return courseB.localeCompare(courseA);
                    }
                });

                sortDirection = (sortDirection === 'asc') ? 'desc' : 'asc'; // Đảo chiều sắp xếp
                displayTableRows();
            }

            window.onload = function () {
                filteredCourses = Array.from(document.querySelectorAll("tbody tr")).map((_, index) => index); // Khởi tạo danh sách khóa học ban đầu
                displayTableRows();
            }
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

