<%-- 
    Document   : questionList
    Created on : 10 thg 10, 2024, 13:43:39
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Questions List</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 20px;
                color: #333;
            }

            .container {
                max-width: 1000px;
                margin: auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                font-size: 24px;
                margin-bottom: 20px;
            }

            .filter-section,
            .settings-section {
                display: flex;
                justify-content: space-between;
                margin-bottom: 15px;
                flex-wrap: wrap;
            }

            label {
                margin-right: 10px;
                font-weight: bold;
            }

            input,
            select,
            button {
                padding: 8px;
                margin-right: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #007bff;
                color: white;
                cursor: pointer;
                border: none;
            }

            button:hover {
                background-color: #0056b3;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 15px;
            }

            th,
            td {
                padding: 8px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .pagination {
                text-align: center;
                margin-top: 15px;
            }

            .pagination a {
                padding: 8px 12px;
                border: 1px solid #007bff;
                margin: 0 5px;
                border-radius: 4px;
                color: #007bff;
                text-decoration: none;
            }

            .pagination a.active,
            .pagination a:hover {
                background-color: #007bff;
                color: white;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h1>Questions List</h1>
            <form action="QuestionListServlet" method="post">
                <!-- Filter Section -->
                <div class="filter-section">
                    <!-- Tìm question qua content  -->
                    <input type="text" name="questionContent" value="" id="search-content" placeholder="Search content...">
                    <!-- Tìm question qua course  -->
                    <input type="text" name="questionCourse" value="" id="search-course" placeholder="Search by course...">
                    <select id="level" name="questionLevel">
                        <option value="">Level</option>
                        <option value="easy">Easy</option>
                        <option value="medium">Medium</option>
                        <option value="hard">Hard</option>
                    </select>
                    <select id="status" name="questionStatus">
                        <option value="">Status</option>
                        <option value="visible">Visible</option>
                        <option value="hidden">Hidden</option>
                    </select>
                    <!-- Questions Per Page -->
                    <div class="settings-section">
                        <input type="text" name="questionPerPage" value="" id="search-course" placeholder="Number question per page">
                    </div>
                    <button type="submit">Add filter</button>
            </form>


            <!-- Questions Per Page and Column Visibility -->
            <form action="ShowPageServlet" method="post">            
                <div class="settings-section">
                    <label>Columns to display:</label>
                    <label><input type="checkbox" name="visibleCol" value="id" <c:if test="${visibleColumns.contains('id')}">checked</c:if>> ID</label>
                    <label><input type="checkbox" name="visibleCol" value="content" <c:if test="${visibleColumns.contains('content')}">checked</c:if>> Content</label>
                    <label><input type="checkbox" name="visibleCol" value="course" <c:if test="${visibleColumns.contains('course')}">checked</c:if>> Course</label>
                    <label><input type="checkbox" name="visibleCol" value="level" <c:if test="${visibleColumns.contains('level')}">checked</c:if>> Level</label>
                    <label><input type="checkbox" name="visibleCol" value="status" <c:if test="${visibleColumns.contains('status')}">checked</c:if>> Status</label>
                    <label><input type="checkbox" name="visibleCol" value="actions" <c:if test="${visibleColumns.contains('actions')}">checked</c:if>> Actions</label>
                        <button type="submit">Apply</button>
                    </div>
                </form>

            </div>



            <!-- Questions Table -->
            <table>
                <tr>
                <c:if test="${visibleColumns.contains('id')}"><th>ID</th></c:if>
                <c:if test="${visibleColumns.contains('content')}"><th>Content</th></c:if>
                <c:if test="${visibleColumns.contains('course')}"><th>Course</th></c:if>
                <c:if test="${visibleColumns.contains('level')}"><th>Level</th></c:if>
                <c:if test="${visibleColumns.contains('status')}"><th>Status</th></c:if>
                <c:if test="${visibleColumns.contains('actions')}"><th>Actions</th></c:if>
                </tr>
            <c:forEach items="${listQuestion}" var="q">
                <tr>
                    <c:if test="${visibleColumns.contains('id')}"> <td>${q.questionId}</td></c:if>
                    <c:if test="${visibleColumns.contains('content')}">
                        <td>${q.questionContent}
                            <c:if test="${not empty q.questionImgOrVideo}">
                                <c:choose>
                                    <c:when test="${fn:endsWith(q.questionImgOrVideo, '.mp4')}">
                                        <video style="width: 80px;" controls src="${q.questionImgOrVideo}" type="video/mp4"></video>
                                        </c:when>
                                        <c:otherwise>
                                        <img style="width: 80px;" src="${q.questionImgOrVideo}" alt="No image"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </td>
                    </c:if>

                    <c:if test="${  visibleColumns.contains('course')}"><td>${q.course.courseName}</td></c:if>
                    <c:if test="${  visibleColumns.contains('level')}"><td>${q.level}</td></c:if>
                    <c:if test="${  visibleColumns.contains('status')}"><td>${q.status}</td></c:if>
                    <c:if test="${  visibleColumns.contains('actions')}">
                        <td>
                            <!-- Thay đổi status question qua  questionListShowHide -->
                            <form action="questionListShowHide" method="post">
                                <input type="hidden" name="questionId" value="${q.questionId}">
                                <button type="submit" name="actionQuestion" value="show">Show</button>
                                <button type="submit" name="actionQuestion" value="hide">Hide</button>
                            </form>
                            <a href="QuestionDetail"><button>Edit</button></a> <!-- Question Detail chưa có -->
                        </td>
                    </c:if>


                </tr>
            </c:forEach>

        </table>

        <!-- Import question -->
        <a href="ImportServlet"><button type="button">Import Question</button>
        </a>


        <!-- Pagination -->
        <div class="pagination" id="pagination">
            <!-- Ở page 1 không hiện Previous  -->
            <c:if test="${page > 1}">
                <a href="?page=${page - 1}&questionPerPage=${param.questionPerPage}">Previous</a>
            </c:if>
            <!-- Hiện tất cả các trang hiện có và add style active cho trang hiện tại  -->
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="?page=${i}&questionPerPage=${param.questionPerPage}" class="${i == page ? 'active' : ''}">${i}</a>
            </c:forEach>
            <!-- Ở page cuối không hiện Previous  -->
            <c:if test="${page < totalPages}">
                <a href="?page=${page + 1}&questionPerPage=${param.questionPerPage}">Next</a>
            </c:if>
        </div>
    </div>

</body>

</html>
