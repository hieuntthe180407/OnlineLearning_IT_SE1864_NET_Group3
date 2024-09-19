<%-- 
    Document   : resetPassword
    Created on : Sep 19, 2024, 7:58:42 PM
    Author     : DTC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.Token" %>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <style>
            body{
                background-color: #f1f2f3;

            }
            .mainDiv {
                display: flex;
                min-height: 100%;
                align-items: center;
                justify-content: center;
                background-color: #f1f2f3;
                font-family: 'Open Sans', sans-serif;
            }
            .cardStyle {
                width: 500px;
                border-color: white;
                background: #fff;
                padding: 36px 0;
                border-radius: 4px;
                margin: 30px 0;
                box-shadow: 0px 0 2px 0 rgba(0,0,0,0.25);
            }
            #signupLogo {
                max-height: 100px;
                margin: auto;
                display: flex;
                flex-direction: column;
            }
            .formTitle{
                font-weight: 600;
                margin-top: 20px;
                color: #2F2D3B;
                text-align: center;
            }
            .inputLabel {
                font-size: 12px;
                color: #555;
                margin-bottom: 6px;
                margin-top: 24px;
            }
            .inputDiv {
                width: 70%;
                display: flex;
                flex-direction: column;
                margin: auto;
            }
            input {
                height: 40px;
                font-size: 16px;
                border-radius: 4px;
                border: none;
                border: solid 1px #ccc;
                padding: 0 11px;
            }
            input:disabled {
                cursor: not-allowed;
                border: solid 1px #eee;
            }
            .buttonWrapper {
                margin-top: 40px;
            }
            .submitButton {
                width: 70%;
                height: 40px;
                margin: auto;
                display: block;
                color: #fff;
                background-color: #065492;
                border-color: #065492;
                text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
                box-shadow: 0 2px 0 rgba(0, 0, 0, 0.035);
                border-radius: 4px;
                font-size: 14px;
                cursor: pointer;
            }
            .submitButton:disabled,
            button[disabled] {
                border: 1px solid #cccccc;
                background-color: #cccccc;
                color: #666666;
            }

            #loader {
                position: absolute;
                z-index: 1;
                margin: -2px 0 0 10px;
                border: 4px solid #f3f3f3;
                border-radius: 50%;
                border-top: 4px solid #666666;
                width: 14px;
                height: 14px;
                -webkit-animation: spin 2s linear infinite;
                animation: spin 2s linear infinite;
            }

            @keyframes spin {
                0% {
                    transform: rotate(0deg);
                }
                100% {
                    transform: rotate(360deg);
                }
            }
            .save:hover{
                color:#fff;

            }

        </style>
    </head>
    <body>

        <%
                    String email = request.getParameter("email");
                    String token = request.getParameter("token");
                    boolean isValid = Token.isTokenValid(token);
        %>
        <%
        if(isValid){%>
        <div class="mainDiv mt-3">
            <div class="cardStyle">
                <form action="${pageContext.request.contextPath}/resetPassword" method="post" name="signupForm" id="signupForm">
                    <div style="text-align: center">
                        <img class="logo" src="" alt="Logo" style="width: 150px">
                    </div>
                    <input type="hidden" name="email" id="email" value="<%=email%>">
                    <h2 class="formTitle">
                        Create New Password
                    </h2>
                    <div class="alert alert-danger " role="alert" id="errorAlert" style="display: none;">
                        <i class="fa-solid fa-triangle-exclamation " ></i> Error
                        <br>
                        <span id="errorText"></span>
                    </div>
                    <div class="inputDiv">
                        <label class="inputLabel" for="password">New Password</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword">
                    </div>
                    <div class="text-center mt-5">
                        <input type="button" onclick="validateForm()" value="Save Password" class="btn w-50 save"
                               style="background-color: #138496" >
                    </div>
                </form>
            </div>
        </div>  
        <%   }else{%>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'ERROR!',
                text: 'The password change deadline has expired. Please do it again!',
                confirmButtonText: 'Back To Homepage',
                allowOutsideClick: false,
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = 'http://localhost:9999/OnlineLearning_IT_Group3/login'; // Thay đổi địa chỉ trang khác
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                }
            });
        </script>
        <%
        }
        %>

        <script>

            function validateForm() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;

                if (password === confirmPassword) {
                    // If passwords match, submit the form
                    document.getElementById("signupForm").submit();
                } else {
                    // If passwords do not match, show error message
                    document.getElementById("errorAlert").style.display = "block";
                    document.getElementById("errorText").innerText = "Passwords do not match.";
                }
            }



        </script>
    </body>
</html>
