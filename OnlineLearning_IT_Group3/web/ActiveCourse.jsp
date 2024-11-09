<%-- 
    Document   : ActiveCourse
    Created on : Nov 9, 2024, 5:14:18 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.Course"%>
<!DOCTYPE html>
<html>
     <head>
         <meta charset="utf-8">
    <meta name="google-translate-customization" content="9f841e7780177523-3214ceb76f765f38-gc38c6fe6f9d06436-c">
    </meta>

    <title>Secret Coder : Online Courses</title>
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
    </head>
    <body>
    <%@include file="header.jsp" %>
     <%
    Integer itemsPerPage = (Integer) request.getAttribute("itemsPerPage");  
    if (itemsPerPage == null) {
        itemsPerPage = 10;  // Default to 10 items per page
    }
%>
        <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Course List</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item"><a class="text-white" href="#">Admin</a></li>
                            <li class="breadcrumb-item text-white active" aria-current="page">Course List</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->
        <%
            String noti = (String)request.getAttribute("noti");
            if(noti != null){
        %>
        <!-- thong bao hien thi khi nguoi dung nhap thanh cong -->
        <h3 style="color: red; text-align: center; font-weight: bold;">
            <%= noti %>
        </h3>

        <%
        }
            
        %>
      
    <!-- Search course va loc course moi page -->
        
<form id="CourseFilterForm" action="ActiveCourse" method="GET">
    <label for="itemsPerPage">Course per page:</label>
    <input type="number" name="itemsPerPage" id="itemsPerPage" value="<%=itemsPerPage%>" />
    <input type="text" name="query" placeholder="Search">
    

    <!-- Submit Button -->
    <button type="submit">Search</button>
</form>

    <style>
    /* General Form Styles */
    form {
        margin: 20px 0;
        padding: 15px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    /* Search Form Styles */
    #searchForm {
        display: flex;
        justify-content: flex-start;
        align-items: center;
        gap: 10px;
    }
    
    #searchForm input[type="text"] {
        padding: 8px 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
        width: 200px;
    }

    #searchForm button {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 8px 15px;
        font-size: 16px;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    #searchForm button:hover {
        background-color: #45a049;
    }

    /* Filter Form Styles */
    #userFilterForm {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        align-items: center;
    }

    #userFilterForm label {
        font-size: 16px;
        margin-right: 10px;
        font-weight: bold;
    }

    #userFilterForm select {
        padding: 8px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 150px;
    }

    /* Dropdown Hover Effect */
    select:hover {
        border-color: #4CAF50;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        #searchForm, #userFilterForm {
            flex-direction: column;
            align-items: flex-start;
        }

        #searchForm input[type="text"] {
            width: 100%;
        }

        #userFilterForm select {
            width: 100%;
        }
    }
</style>
<!-- Lay list User tu db -->

<%
    List<Course> list = (List<Course>) request.getAttribute("list");
    if (list == null || list.size() == 0) {
        out.println("<div class='empty-list'>Empty list of Course</div>");
    } else {
%>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 16px;
            text-align: left;
        }
        table, th, td {
            border: 1px solid #dddddd;
        }
        th, td {
            padding: 12px;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .empty-list {
            color: red;
            font-weight: bold;
            margin-top: 20px;
        }
    </style>

    <table>
        <tr>
            <th>ID</th>
            <th>Course Name</th>
            <th>Is Published</th>
            <th>Teacher Name</th>
            <th>Teacher Email</th>
            <th>Go to Course</th>
            <th>Action</th>
            
        </tr>
        <%
            for (Course c : list) {
                
        %>
        <tr>
            <td><%= c.getCourseID() %></td>
            <td><%= c.getCourseName() %></td>
            <td><%= c.isIsActive() ? "Active" : "UnActive" %></td>
            <td><%= c.getUserId().getFullName() %></td>
            <td><%= c.getUserId().getEmail() %></td>
            <td>
                <a href="courseDetail?courseID=<%=c.getCourseID()%>"><input type="button" value="View"/></a>
                
            </td>
            <td>
                <a href="publishCourse?courseID=<%=c.getCourseID()%>&publish=1"><input type="button" value="Active"/></a>
                <a href="publishCourse?courseID=<%=c.getCourseID()%>&publish=0"><input type="button" value="UnActive"/></a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
<%
    }
%>

    <!-- Pagination Controls -->
    
   <% 
    int currentPage = (Integer) request.getAttribute("currentPage");
    int totalPages = (Integer) request.getAttribute("totalPages");
%>
<div class="pagination">
    <ul>
        <%
            if (currentPage > 1) {
        %>
        <li><a href="userList?page=<%= currentPage - 1 %>&itemsPerPage=<%= itemsPerPage %>">&laquo; Previous</a></li>
        <%
            }

            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
        %>
        <li><span><%= i %></span></li>
        <%
                } else {
        %>
        <li><a href="userList?page=<%= i %>&itemsPerPage=<%= itemsPerPage %>"><%= i %></a></li>
        <%
                }
            }

            if (currentPage < totalPages) {
        %>
        <li><a href="userList?page=<%= currentPage + 1 %>&itemsPerPage=<%= itemsPerPage %>">Next &raquo;</a></li>
        <%
            }
        %>
    </ul>
</div>

    <%@include file="footer.jsp" %>
    </body>
</html>
