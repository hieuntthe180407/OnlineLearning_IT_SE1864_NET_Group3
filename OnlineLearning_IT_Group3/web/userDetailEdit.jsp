<%-- 
    Document   : userDetailEdit
    Created on : Oct 8, 2024, 2:10:23 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
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
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            .container {
                width: 80%;
                margin: auto;
                border: 1px solid black;
                padding: 20px;
            }

            .header {
                text-align: center;
                font-size: 24px;
                margin-bottom: 20px;
            }

            .detail-panel {
                display: flex;
                border: 1px solid black;
                padding: 20px;
            }

            .avatar {
                width: 150px;
                height: 150px;
                background-color: gray;
                text-align: center;
                line-height: 150px;
                color: white;
                margin-right: 20px;
            }

            .info {
                flex: 1;
            }

            .info div {
                margin-bottom: 10px;
            }

            .info label {
                display: inline-block;
                width: 80px;
                font-weight: bold;
            }

            .info input {
                width: calc(100% - 90px);
                padding: 5px;
                margin-left: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <%User u = (User)request.getAttribute("user");
            String noti = (String)request.getAttribute("noti");
            if(noti != null){
        %>
        <h3 style="color: red; text-align: center; font-weight: bold;">
            <%= noti %>
        </h3>

        <%
        }
            
        %>
        <form  action="userAdminEdit" method="get">
            <div class="container">
                <input type="hidden" name="userId" value="<%= u.getUserID() %>">

                <div class="detail-panel">
                    <div class="avatar">AVATAR

                    </div>
                    <div class="info">
                        <div>
                            <label for="fullName">Full Name:</label>
                            <input type="text" id="fullName" disabled value="<%=u.getFullName()%>">
                        </div>
                        <div>
                            <label for="email">Email:</label>
                            <input type="email" id="email" disabled value="<%=u.getEmail()%>">
                        </div>
                        <div>
                            <label for="address">Address:</label>
                            <input type="text" id="address" disabled value="<%=u.getAddress()%>">
                        </div>
                        <div style="display: flex; gap: 10px;">
                            <div style="flex: 1;">
                                <label for="phone">Phone:</label>
                                <input type="text" id="phone" disabled style="width: 100%;" value="<%=u.getPhone()%>">
                            </div>
                            <div style="flex: 1;">
                                <label for="gender">Gender:</label>
                                <input type="text" id="gender" disabled style="width: 100%;"value="<%=u.getGender()%>">
                            </div>
                        </div>

                        <div style="display: flex; gap: 10px;">
                            <div style="flex: 1; display: flex; align-items: center; gap: 10px;">
                                <label for="role">Role:</label>
                                <input type="text" id="role" disabled style="width: 100%; flex: 1;" value="<%=u.getRole().getRoleName()%>">
                                <select id="roleSelect" name="role" class="form-control" style="width: auto;"> 
                                    <option value="<%=u.getRole().getRoleId()%>">Edit</option>
                                    <option value="1">Admin</option>
                                    <option value="2">Expert</option>
                                    <option value="3">User</option>
                                </select>
                            </div>
                            <div style="flex: 1; display: flex; align-items: center; gap: 10px;">
                                <label for="status">Status:</label>
                                <input type="text" id="status" disabled style="width: 100%; flex: 1;" value="<%=u.getStatus()%>">
                                <select id="statusSelect" name="status" class="form-control" style="width: auto;" > 
                                    <option value="<%=u.getStatus()%>">Edit</option>
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                    <option value="Pending">Pending</option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn">Confirm</button>
        </form>

        <%@include file="footer.jsp" %>
    </body>
</html>
