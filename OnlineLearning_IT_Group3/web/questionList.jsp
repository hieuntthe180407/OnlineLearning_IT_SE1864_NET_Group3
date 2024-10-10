<%-- 
    Document   : questionList
    Created on : 10 thg 10, 2024, 13:43:39
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                padding: 12px;
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
                    <input type="text" name="questionContent" id="search-content" placeholder="Search content...">
                    <input type="text" name="questionCourse" id="search-course" placeholder="Search by course...">
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
                        <select id="questions-per-page" name="questionPerPage">
                            <option value="5">5 per page</option>
                            <option value="10" selected>10 per page</option>
                            <option value="15">15 per page</option>
                        </select>
                    </div>

                    <!-- Questions Per Page and Column Visibility -->
                    <div class="settings-section">
                        <label>Columns to display:</label>
                        <label><input type="checkbox" name="visibleCol" value="id" checked> ID</label>
                        <label><input type="checkbox" name="visibleCol" value="content" checked> Content</label>
                        <label><input type="checkbox" name="visibleCol" value="course" checked> Course</label>
                        <label><input type="checkbox" name="visibleCol" value="level" checked> Level</label>
                        <label><input type="checkbox" name="visibleCol" value="status" checked> Status</label>
                        <label><input type="checkbox" name="visibleCol" value="actions" checked> Actions</label>
                    </div>
                    <button type="submit">Add filter</button>
                </div>


            </form>


            <!-- Questions Table -->
            <table id="questions-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Content</th>
                        <th>Course</th>
                        <th>Level</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody id="questions-body">
                    <tr>
                        <td>1</td>
                        <td>What is HTML?</td>
                        <td>Web Development</td>
                        <td>Easy</td>
                        <td>Visible</td>
                        <td>
                            <button>Show</button>
                            <button>Hide</button>
                            <button>Edit</button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>What is a variable?</td>
                        <td>Programming</td>
                        <td>Medium</td>
                        <td>Hidden</td>
                        <td>
                            <button>Show</button>
                            <button>Hide</button>
                            <button>Edit</button>
                        </td>
                    </tr>
                </tbody>
            </table>

            <!-- Import question -->
            <a href="ImportServlet"><button type="button">Import Question</button>
            </a>


            <!-- Pagination -->
            <div class="pagination" id="pagination">
                <a href="#" class="active">1</a>
                <a href="#">2</a>
                <a href="#">Next</a>
            </div>
        </div>

    </body>

</html>
