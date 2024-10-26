<%-- 
    Document   : userList
    Created on : Sep 15, 2024, 1:21:24 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.User"%>
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
        
        <!-- Header Start -->
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">UserList</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item"><a class="text-white" href="#">Admin</a></li>
                            <li class="breadcrumb-item text-white active" aria-current="page">UserList</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->
        
        <!-- search xog se ve servlet searchUser r quay lai trang nay -->
        <form action="searchUser" method="GET">
    <input type="text" name="query" placeholder="Search">
    <button type="submit">Search</button>
</form>

<form id="userFilterForm" action="filterUser" method="GET">
    <!-- Gender Dropdown -->
    <label for="gender">Gender:</label>
    <select name="gender" id="gender" onchange="document.getElementById('userFilterForm').submit()">
        <option value="">All Genders</option>
        <option value="male" ${param.gender == 'male' ? 'selected' : ''}>Male</option>
        <option value="female" ${param.gender == 'female' ? 'selected' : ''}>Female</option>
        <option value="other" ${param.gender == 'other' ? 'selected' : ''}>Other</option>
    </select>

    <!-- Role Dropdown -->
    <label for="role">Role:</label>
    <select name="role" id="role" onchange="document.getElementById('userFilterForm').submit()">
        <option value="">All Roles</option>
        <option value="admin" ${param.role == 'admin' ? 'selected' : ''}>Admin</option>
        <option value="user" ${param.role == 'user' ? 'selected' : ''}>User</option>
        <option value="expert" ${param.role == 'expert' ? 'selected' : ''}>Expert</option>
    </select>

    <!-- Status Dropdown -->
    <label for="status">Status:</label>
    <select name="status" id="status" onchange="document.getElementById('userFilterForm').submit()">
        <option value="">All Statuses</option>
        <option value="active" ${param.status == 'active' ? 'selected' : ''}>Active</option>
        <option value="banned" ${param.status == 'banned' ? 'selected' : ''}>Banned</option>
        <option value="pending" ${param.status == 'pending' ? 'selected' : ''}>Pending</option>
    </select>
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
    List<User> list = (List<User>) request.getAttribute("users");
    if (list == null || list.size() == 0) {
        out.println("<div class='empty-list'>Empty list of Users</div>");
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
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Gender</th>
            <th>Role</th>
            <th>More Detail</th>
        </tr>
        <%
            for (User u : list) {
                
        %>
        <tr>
            <td><%= u.getUserID() %></td>
            <td><%= u.getFullName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getPhone() %></td>
            <td><%= u.getAddress() %></td>
            <td><%= u.getGender() %></td>
            <td><%= u.getRole().getRoleName() %></td>
            <td><a href="userDetail?userId=<%=u.getUserID()%>"><input type="button" value="View"/></a></td>
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

<!-- Pagination Controls -->
<div class="pagination">
    <ul>
        <%
            if (currentPage > 1) {
        %>
        <li><a href="userList?page=<%= currentPage - 1 %>">&laquo; Previous</a></li>
        <%
            }

            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
        %>
        <li><span><%= i %></span></li>
        <%
                } else {
        %>
        <li><a href="userList?page=<%= i %>"><%= i %></a></li>
        <%
                }
            }

            if (currentPage < totalPages) {
        %>
        <li><a href="userList?page=<%= currentPage + 1 %>">Next &raquo;</a></li>
        <%
            }
        %>
    </ul>
</div>

<!-- Pagination Styles -->
<style>
.pagination ul {
    list-style-type: none;
    padding: 0;
}
.pagination ul li {
    display: inline;
    margin: 0 5px;
}
.pagination ul li a {
    text-decoration: none;
    padding: 8px 16px;
    background-color: #4CAF50;
    color: white;
    border-radius: 5px;
}
.pagination ul li span {
    padding: 8px 16px;
    background-color: #ddd;
    color: black;
    border-radius: 5px;
}
.pagination ul li a:hover {
    background-color: #45a049;
}
</style>



            
            
            
            
            
     <%@include file="footer.jsp" %>       
            
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
