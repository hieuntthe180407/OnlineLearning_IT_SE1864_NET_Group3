<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>


<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Secret Coder : Courses</title>
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

        <style>
            #paginationNumbers .btn.active {
                background-color: #007bff; /* Active button color */
                color: white; /* Text color for active button */
            }
            .sidebar {
                padding: 20px;
                background-color: #f8f9fa; /* Light background for the sidebar */
                border-right: 1px solid #ddd; /* Border to separate the sidebar */
            }

            .sidebar h4 {
                margin-bottom: 15px; /* Spacing for section titles */
            }

            .sidebar .form-control {
                width: 100%; /* Full width for input */
            }

            .list-group-item {
                border: none; /* Remove border from list items */
            }


        </style>
    </head>

    <body>
        <!-- Spinner Start -->
        <div id="spinner"
             class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar Start -->

        <%@include file="header.jsp" %>
        <!-- Navbar End -->

        <!-- Header Start -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Courses</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="index.html">Home</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">Courses</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->


        <!-- Categories Start -->
        <div class="container-xxl py-5 category">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h6 class="section-title bg-white text-center px-3">Categories</h6>
                    <h1 class="mb-5" style="color: #fb873f;">Popular Topics to Explore</h1>
                </div>
                <div class="row g-2 m-2">

                    <c:forEach items="${listTop8Category}" var="listTop8Category">
                        <div class="col-lg-3 col-md-6  text-center">
                            <div class="content shadow p-3 mb-2 wow fadeInUp" data-wow-delay="0.3s">

                                <img src="${listTop8Category.image}" class="img-fluid" alt="categories"></i>

                                <h5 class="my-2">
                                    <a href="courseList?action=category&name=${listTop8Category.categoryName}" class="text-center">${listTop8Category.categoryName}</a>
                                </h5>
                            </div>
                        </div>

                    </c:forEach>


                </div>
            </div>
        </div>
        <!-- Categories End -->

        <!-- Sidebar Start -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3 col-md-4">
                    <div class="sidebar">
                        <h4 class="mb-4">Search Courses</h4>
                        <form action="courseList" method="get" class="mb-4">
                            <input name="action" value="search" hidden="">
                            <input type="text" name="text" class="form-control" placeholder="Search...">
                            <button type="submit" class="btn btn-primary mt-2">Search</button>
                        </form>

                        <h4 class="mb-4">Price Range</h4>
                        <form action="courseList" method="get">
                            <!-- Hidden input for the action -->
                            <input name="action" value="filterPrice" hidden="">

                            <div class="range-slider">
                                <input type="range" name="minPrice" class="form-range" 
                                       min="${minPrice}" max="${maxPrice}" 
                                       id="minPriceSlider" value="${minPrice}" oninput="updateRangeSlider()">
                                <input type="range" name="maxPrice" class="form-range" 
                                       min="${minPrice}" max="${maxPrice}" 
                                       id="maxPriceSlider" value="${maxPrice}" oninput="updateRangeSlider()">
                            </div>

                            <p>Selected Price Range: <span id="minPriceValue">${minPrice}</span> - <span id="maxPriceValue">${maxPrice}</span></p>

                            <button type="submit" class="btn btn-primary mt-2">Filter by Price</button>
                        </form>

                        <script>
                            // Update the displayed price range values and ensure the sliders don't overlap
                            function updateRangeSlider() {
                                let minSlider = document.getElementById('minPriceSlider');
                                let maxSlider = document.getElementById('maxPriceSlider');
                                let minPrice = Math.min(parseInt(minSlider.value), parseInt(maxSlider.value) - 1);
                                let maxPrice = Math.max(parseInt(maxSlider.value), parseInt(minSlider.value) + 1);

                                // Update slider values
                                minSlider.value = minPrice;
                                maxSlider.value = maxPrice;

                                // Update displayed values
                                document.getElementById('minPriceValue').textContent = minPrice;
                                document.getElementById('maxPriceValue').textContent = maxPrice;
                            }

                            // Initialize the range slider display values
                            updateRangeSlider();
                        </script>



                        <h4 class="mb-4">Categories</h4>
                        <ul class="list-group">
                            <c:forEach items="${listTop10Category}" var="category">
                                <li class="list-group-item"><a href="courseList?action=category&name=${category.categoryName}" class="text-dark">${category.categoryName}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="col-lg-9 col-md-8">
                    <!-- Courses Start -->
                    <div class="container py-5">
                        <div class="text-center" data-wow-delay="0.1s">
                            <h6 class="section-title bg-white text-center px-3">Popular Courses</h6>
                            <h1 class="mb-5" style="color: #fb873f;">Explore new and trending free online courses</h1>
                        </div>
                        <div class="row g-4 py-2" id="courseContainer">
                            <c:forEach items="${listCourse}" var="listCourse">
                                <div class="col-lg-3 col-md-6 course-item">
                                    <div class="shadow">
                                        <div class="position-relative overflow-hidden text-light image">
                                            <img class="img-fluid" src="${listCourse.courseImg}" alt="${listCourse.courseName}">
                                            <c:if test="${listCourse.price == 0}">
                                                <div style="position:absolute;top: 15px;left: 16px; font-size:12px; border-radius:3px; background-color:#fb873f;" class="px-2 py-1 fw-bold text-uppercase">FREE</div>
                                            </c:if>
                                        </div>
                                        <div class="p-2 pb-0">
                                            <h5 class="mb-1"><a href="courseDetail?courseID=${listCourse.courseID}" class="text-dark">${listCourse.courseName}</a></h5>
                                        </div>
                                        <div class="d-flex">
                                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-star text-warning me-2"></i>4.55</small>
                                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-user-graduate me-2"></i>5.8L+ Learners</small>
                                            <small class="flex-fill text-center py-1 px-2"><i class="fa fa-user me-2"></i>Begin</small>
                                        </div>
                                        <div class="d-flex">
                                            <small class="flex-fill text-left p-2 px-2"><i class="fa fa-clock me-2"></i>${listCourse.duration} Hrs</small>
                                            <small class="py-1 px-2 fw-bold fs-6 text-center">
                                                <span style="color:red; text-decoration: line-through;">
                                                    <!-- Chỉ hiển thị giá gốc nếu salePrice > 0 và price > 0 -->
                                                    <c:if test="${listCourse.salePrice > 0 && listCourse.price > 0}">
                                                        ${listCourse.price} VND
                                                    </c:if>
                                                </span>

                                                <!-- Chỉ hiển thị salePrice nếu salePrice > 0, ngược lại hiển thị price nếu price > 0 -->
                                                <c:if test="${listCourse.salePrice > 0}">
                                                    ${listCourse.salePrice} VND
                                                </c:if>
                                                <c:if test="${listCourse.salePrice == 0 && listCourse.price > 0}">
                                                    ${listCourse.price} VND
                                                </c:if>
                                            </small>

                                            <small class="text-primary py-1 px-2 fw-bold fs-6" style="float:right;"><a href="#">Enroll Now </a><i class="fa fa-chevron-right me-2 fs-10"></i></small>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- Pagination Controls -->
                        <div class="text-center my-4">
                            <button id="prevBtn" class="btn btn-primary" onclick="changePage(-1)">Previous</button>
                            <span id="pageInfo"></span>

                            <!-- Page Number Controls -->
                            <div id="paginationNumbers" class="d-inline-block mx-3"></div>

                            <button id="nextBtn" class="btn btn-primary" onclick="changePage(1)">Next</button>
                        </div>
                    </div>
                    <!-- Courses End -->
                </div>
            </div>
        </div>
        <!-- Sidebar End -->




        <script>
            const itemsPerPage = 12; // Số lượng mục hiển thị trên mỗi trang
            let currentPage = 1; // Trang hiện tại, bắt đầu từ trang đầu tiên

            const items = document.querySelectorAll('.course-item'); // Chọn tất cả các phần tử có class 'course-item' (mỗi phần tử đại diện cho một khóa học)
            const totalPages = Math.ceil(items.length / itemsPerPage); // Tính tổng số trang cần có dựa trên số lượng mục và số mục mỗi trang
            const prevBtn = document.getElementById('prevBtn'); // Lấy phần tử nút chuyển về trang trước
            const nextBtn = document.getElementById('nextBtn'); // Lấy phần tử nút chuyển đến trang sau
            const pageInfo = document.getElementById('pageInfo'); // Lấy phần tử hiển thị thông tin trang

            function updatePaginationNumbers() {
                const paginationNumbers = document.getElementById('paginationNumbers');
                paginationNumbers.innerHTML = ''; // Xóa các số trang hiện tại

                // Xác định số trang hiển thị dựa trên trang hiện tại
                const startPage = Math.max(1, currentPage - 1); // Trang bắt đầu, không nhỏ hơn 1
                const endPage = Math.min(totalPages, currentPage + 1); // Trang kết thúc, không lớn hơn tổng số trang

                // Luôn hiển thị trang đầu tiên
                if (startPage > 1) {
                    const firstPage = document.createElement('button'); // Tạo nút cho trang đầu
                    firstPage.className = 'btn btn-outline-primary mx-1'; // Thêm class cho nút
                    firstPage.innerText = '1'; // Đặt nội dung là số 1
                    firstPage.onclick = () => {
                        currentPage = 1; // Chuyển về trang đầu tiên
                        showPage(currentPage); // Hiển thị trang đầu tiên
                    };
                    paginationNumbers.appendChild(firstPage); // Thêm nút vào trong phần số trang

                    if (startPage > 2) { // Hiển thị dấu "..." nếu có khoảng trống giữa các trang
                        const ellipsis = document.createElement('span');
                        ellipsis.innerText = '...';
                        paginationNumbers.appendChild(ellipsis); // Thêm dấu "..." vào phần số trang
                    }
                }

                // Vòng lặp qua các số trang từ trang bắt đầu đến trang kết thúc
                for (let i = startPage; i <= endPage; i++) {
                    const pageNum = document.createElement('button'); // Tạo nút cho từng trang
                    pageNum.className = 'btn btn-outline-primary mx-1'; // Thêm class cho nút
                    pageNum.innerText = i; // Đặt nội dung là số trang
                    pageNum.onclick = () => {
                        currentPage = i; // Cập nhật trang hiện tại
                        showPage(currentPage); // Hiển thị trang hiện tại
                    };

                    // Đánh dấu trang hiện tại
                    if (i === currentPage) {
                        pageNum.classList.add('active'); // Thêm class 'active' cho nút của trang hiện tại
                    }

                    paginationNumbers.appendChild(pageNum); // Thêm nút vào trong phần số trang
                }

                // Luôn hiển thị trang cuối cùng
                if (endPage < totalPages) {
                    if (endPage < totalPages - 1) { // Hiển thị dấu "..." nếu có khoảng trống giữa các trang
                        const ellipsis = document.createElement('span');
                        ellipsis.innerText = '...';
                        paginationNumbers.appendChild(ellipsis); // Thêm dấu "..." vào phần số trang
                    }

                    const lastPage = document.createElement('button'); // Tạo nút cho trang cuối
                    lastPage.className = 'btn btn-outline-primary mx-1';
                    lastPage.innerText = totalPages; // Đặt nội dung là số trang cuối cùng
                    lastPage.onclick = () => {
                        currentPage = totalPages; // Chuyển đến trang cuối
                        showPage(currentPage); // Hiển thị trang cuối
                    };
                    paginationNumbers.appendChild(lastPage); // Thêm nút vào trong phần số trang
                }
            }

            function showPage(page) {
                // Ẩn tất cả các mục
                items.forEach((item, index) => {
                    item.style.display = 'none'; // Ban đầu ẩn tất cả các mục
                    // Hiển thị các mục của trang hiện tại
                    if (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) {
                        item.style.display = 'block'; // Hiển thị các mục thuộc trang hiện tại
                    }
                });

                // Cập nhật thông tin trang
                pageInfo.innerText = `Page ${page} of ${totalPages}`; // Hiển thị số trang hiện tại và tổng số trang

                // Kích hoạt/vô hiệu hóa nút
                prevBtn.disabled = page === 1; // Vô hiệu hóa nút "Trước" khi đang ở trang đầu
                nextBtn.disabled = page === totalPages; // Vô hiệu hóa nút "Tiếp theo" khi đang ở trang cuối

                // Cập nhật số trang trong phần phân trang
                updatePaginationNumbers();
            }

            function changePage(direction) {
                const newPage = currentPage + direction; // Xác định trang mới

                // Đảm bảo trang mới nằm trong giới hạn hợp lệ
                if (newPage >= 1 && newPage <= totalPages) {
                    currentPage = newPage; // Cập nhật trang hiện tại
                    showPage(currentPage); // Hiển thị trang mới
                }
            }

// Hiển thị trang đầu tiên khi tải trang
            document.addEventListener('DOMContentLoaded', function () {
                showPage(currentPage); // Gọi hàm hiển thị trang
            });

        </script>

        <!-- Courses End -->


        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-4 col-md-6">
                        <h4 class="text-white mb-3">Quick Link</h4>
                        <p><a class="text-light" href="about.html">About Us</a></p>
                        <p><a class="text-light" href="contact.html">Contact Us</a></p>
                        <p><a class="text-light" href="">Privacy Policy</a></p>
                        <p><a class="text-light" href="">Terms & Condition</a></p>
                        <p><a class="text-light" href="">FAQs & Help</a></p>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <h4 class="text-white mb-3">Contact</h4>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, Bangalore, Karnataka</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+91 8683045908</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>secretcoder@gmail.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-6">
                        <h4 class="text-white mb-3">Subscribe to our Newsletter</h4>
                        <p>Subscribe now and join our growing community of learners committed to lifelong education! </p>
                        <div class="position-relative mx-auto" style="max-width: 400px;">
                            <form action="#">
                                <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="email"
                                       placeholder="Your email" required>
                                <button type="submit"
                                        class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2"><a
                                        href="mailto:keertidvcorai@gmail.com">Subscribe</a></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="copyright">
                    <div class="row">
                        <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                            &copy; <a class="border-bottom" href="index.html">Secret Coder</a>, All Right Reserved.

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
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