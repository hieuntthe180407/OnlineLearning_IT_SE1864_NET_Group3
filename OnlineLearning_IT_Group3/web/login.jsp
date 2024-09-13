<%-- 
    Document   : login
    Created on : 13 thg 9, 2024, 13:43:25
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body class="d-flex vh-100 justify-content-center align-items-center" style="background-color: lightyellow;"  >
        <form action="" class="form-signin w-100 m-auto" style="max-width: 400px;">
            <h1 class="h3 mb-3 fw-normal">Login</h1>
            <%-- type username --%>
            <div class="form-floating">
                <input type="email" class="form-control" id="floatingInput">
                <label for="floatingInput">User name</label>
            </div>
            <%-- type password --%>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword">
                <label for="floatingPassword">Password</label> 
            </div>
            <br>
            <div class="d-flex gap-2">
                <button class="btn btn-primary w-50 py-2" type="submit">Login</button>
                <a href="signup.jsp" class="w-50">
                    <button class="btn btn-danger w-100 py-2" type="button">Sign up</button>
                </a>
            </div>
        </form>

    </body>
</html>
