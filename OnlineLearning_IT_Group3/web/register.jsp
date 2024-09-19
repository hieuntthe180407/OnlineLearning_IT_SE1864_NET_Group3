<%-- 
    Document   : register
    Created on : 19 thg 9, 2024, 17:28:51
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <%@include file= "header.jsp" %>
    <center>
        <form action="login" method="post" class="shadow p-4" style="max-width: 550px;">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h1 class="mb-5 bg-white text-center px-3">Sign up</h1>
            </div>
            <div class="row g-3">
                <div class="col-12">
                    <div class="form-floating">
                        <input type="email"   name="username" class="form-control" id="email" placeholder="Email Address">
                        <label for="email">Email Address</label>
                    </div>
                </div>
                <div class="col-12 form-floating">
                    <select  name="gender" id="gender" class="form-select js-choice z-index-9 rounded-3 border-0 bg-light">
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>
                <div class="col-12">
                    <div class="form-floating">
                        <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                        <label for="password">Password</label>
                    </div>
                </div>

                <div class="col-12">
                    <button class="btn text-light w-100 py-3" type="submit">Login</button>
                </div>

                <div class="col-12 text-center">
                    <p>Have an account? <a class="text-decoration-none" href="login.html">Login</a></p>
                </div>
            </div>
        </form>
    </center> 
    <%@include file= "footer.jsp" %>

</body>
</html>
