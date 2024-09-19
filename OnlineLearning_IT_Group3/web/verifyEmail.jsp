<%-- 
    Document   : verifyEmail
    Created on : 20 thg 9, 2024, 01:13:08
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <form action="verifyCode" method="post" class="shadow p-4" style="max-width: 550px;">
            <h1 class="mb-5">Verify Code</h1>
            <div class="form-floating">
                <input type="text" name="verificationCode" class="form-control" placeholder="Enter Verification Code" required>
            </div>
            <br>
            <div class="col-6">
                <button class="btn text-light w-100 py-3" type="submit">Verify</button>
            </div>
            <br>

        </form>
        <div class="col-6">
            <a href="https://mail.google.com/mail/u/0/#inbox" class="btn text-light w-100 py-3" target="_blank" rel="noopener noreferrer">Check my email</a>
        </div>
    </center>
</body>
</html>
