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
                margin: 20px 0;
                font-size: 16px;
                text-align: left;
            }

            th, td {
                padding: 12px 15px;
                border: 1px solid #dddddd;
            }

            th {
                background-color: #f2f2f2;
                color: #333;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tr:hover {
                background-color: #f1f1f1;
            }

            video, img {
                display: block;
                margin: 0 auto;
            }

            button {
                background-color: #4CAF50; /* Green */
                color: white;
                padding: 8px 12px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            button:hover {
                background-color: #45a049;
            }

            a button {
                background-color: #008CBA;
            }

            a button:hover {
                background-color: #005f6b;
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
        <%@include file= "header.jsp" %>
        <div class="container">
            <h1>Questions List</h1>
            <form action="QuestionListServlet" method="post">
                <!-- Filter Section -->
                <div class="filter-section">
                    <!-- Tìm question qua Title  -->
                    <input type="text" name="questionTitle" value="${param.questionTitle}" id="search-content" placeholder="Search Title...">
                    <input type="text" name="questionCourse" value="${param.questionCourse}" id="search-course" placeholder="Search by course...">
                    <select id="questionType" name="questionType">
                        <option value="">Question type</option>
                        <option value="Multiple Choice" ${param.questionType == 'Multiple Choice' ? 'selected' : ''}>Multiple Choice</option>
                        <option value="Essay" ${param.questionType == 'Essay' ? 'selected' : ''}>Essay</option>
                    </select>

                    <select id="level" name="questionLevel">
                        <option value="">Level</option>
                        <option value="easy" ${param.questionLevel == 'easy' ? 'selected' : ''}>Easy</option>
                        <option value="medium" ${param.questionLevel == 'medium' ? 'selected' : ''}>Medium</option>
                        <option value="hard" ${param.questionLevel == 'hard' ? 'selected' : ''}>Hard</option>
                    </select>
                    <select id="status" name="questionStatus">
                        <option value="">Status</option>
                        <option value="visible" ${param.questionStatus == 'visible' ? 'selected' : ''}>Visible</option>
                        <option value="hidden" ${param.questionStatus == 'hidden' ? 'selected' : ''}>Hidden</option>
                    </select>
                    <input type="text" name="questionPerPage" value="${param.questionPerPage}" id="search-course" placeholder="Number question per page">
                    <button type="submit">Add filter</button>
            </form>


            <!-- Questions Per Page and Column Visibility -->
            <form action="ShowPageServlet" method="post">            
                <div class="settings-section">
                    <label>Columns to display:</label>
                    <label><input type="checkbox" name="visibleCol" value="id" <c:if test="${visibleColumns.contains('id')}">checked</c:if>> ID</label>
                    <label><input type="checkbox" name="visibleCol" value="title" <c:if test="${visibleColumns.contains('title')}">checked</c:if>> Title</label>
                    <label><input type="checkbox" name="visibleCol" value="course" <c:if test="${visibleColumns.contains('course')}">checked</c:if>> Course</label>
                    <label><input type="checkbox" name="visibleCol" value="type" <c:if test="${visibleColumns.contains('type')}">checked</c:if>> Type</label>
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
                <c:if test="${visibleColumns.contains('title')}"><th>Title</th></c:if>
                <c:if test="${visibleColumns.contains('course')}"><th>Course</th></c:if>
                <c:if test="${visibleColumns.contains('type')}"><th>Type</th></c:if>
                <c:if test="${visibleColumns.contains('level')}"><th>Level</th></c:if>
                <c:if test="${visibleColumns.contains('status')}"><th>Status</th></c:if>
                <c:if test="${visibleColumns.contains('actions')}"><th>Actions</th></c:if>
                </tr>
            <c:forEach items="${listQuestion}" var="q">
                <tr>
                    <c:if test="${visibleColumns.contains('id')}"> <td>${q.questionId}</td></c:if>
                    <c:if test="${visibleColumns.contains('title')}">
                        <td>${q.questionTitle}
                            <c:if test="${not empty q.questionImgOrVideo}">
                                <c:choose>
                                    <c:when test="${fn:endsWith(q.questionImgOrVideo, '.mp4')}">
                                        <video style="width: 200px;" controls src="${q.questionImgOrVideo}" type="video/mp4"></video>
                                        </c:when>
                                        <c:otherwise>
                                        <img style="width: 200px;" src="${q.questionImgOrVideo}" alt="No image"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </td>
                    </c:if>

                    <c:if test="${  visibleColumns.contains('course')}"><td>${q.course.courseName}</td></c:if>
                    <c:if test="${  visibleColumns.contains('type')}"><td>${q.questionType}</td></c:if>
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
                            <form action="QuestionDetailServlet">
                                <input type="hidden" name="questionId" value="${q.questionId}">
                                <button type="submit">Edit</button>
                            </form>

                        </td>
                    </c:if>


                </tr>
            </c:forEach>

        </table>

        <!-- Import question -->
        <a href="ImportServlet"><button type="button">Import Question</button>
        </a>


        <!-- Pagination -->
        <div class="pagination">
            <c:if test="${page > 1}">
                <a href="?page=${page - 1}&questionTitle=${param.questionTitle}&questionCourse=${param.questionCourse}&questionType=${param.questionType}&questionLevel=${param.questionLevel}&questionStatus=${param.questionStatus}&questionPerPage=${param.questionPerPage}">Previous</a>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="?page=${i}&questionTitle=${param.questionTitle}&questionCourse=${param.questionCourse}&questionType=${param.questionType}&questionLevel=${param.questionLevel}&questionStatus=${param.questionStatus}&questionPerPage=${param.questionPerPage}" class="${i == page ? 'active' : ''}">${i}</a>
            </c:forEach>
            <c:if test="${page < totalPages}">
                <a href="?page=${page + 1}&questionTitle=${param.questionTitle}&questionCourse=${param.questionCourse}&questionType=${param.questionType}&questionLevel=${param.questionLevel}&questionStatus=${param.questionStatus}&questionPerPage=${param.questionPerPage}">Next</a>
            </c:if>
        </div>


    </body>

</html>
