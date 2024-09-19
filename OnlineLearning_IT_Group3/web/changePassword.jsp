<%-- 
    Document   : changePassword
    Created on : 19 thg 9, 2024, 15:54:17
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                
 String error = (String) request.getAttribute("errorChangePassWord");
            
        %>
        <%@include file= "header.jsp" %>
        <div class="container-xxl py-2 mt-4">
            <div class="container">
                <div class="row g-4 wow fadeInUp" data-wow-delay="0.5s ">
                    <center>
                        <form action="changePassword" method="post" class="shadow p-4" style="max-width: 550px;">
                            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                                <h1 class="mb-5 bg-white text-center px-3">Change password</h1>

                            </div>
                            <div class="row g-3">
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="password"   name="oldPassword" class="form-control" id="email" placeholder="Email Address">
                                        <label for="email">Old Password</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="password" name="newPassword" class="form-control" id="password" placeholder="Password">
                                        <label for="password">New Password</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="password" name="confirmPassword" class="form-control" id="password" placeholder="Password">
                                        <label for="password">Confirm Password</label>
                                    </div>
                                </div>
                                <h3 style="color: red"> <%=(error != null) ? error : ""%> </h3>


                                <div class="col-12">
                                    <button class="btn text-light w-100 py-3" type="submit">Change Password</button>
                                </div>
                            </div>
                        </form>
                    </center>     
                </div>
            </div>
        </div>
    </body>
</html>
