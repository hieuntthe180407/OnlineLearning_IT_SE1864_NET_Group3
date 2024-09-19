<%-- 
    Document   : userProfile
    Created on : 17 thg 9, 2024, 00:46:30
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/userprofile.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>User Profile</title>
    </head>
    <%@include file= "header.jsp" %>
    <body>
    <c:set var="profile" value="${requestScope.profile}"/>
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center">
            <div class="col col-lg-9 col-xl-8">
                <div class="card">
                    <div class="rounded-top text-white d-flex flex-row" style="background-color: #000; height:200px;">
                        <div class="ms-4 mt-5 d-flex flex-column" style="width: 150px;">

                            <img src="${profile.avatar}"
                                 alt="Generic placeholder image" class="img-fluid img-thumbnail mt-4 mb-2"
                                 style="width: 150px; z-index: 1">

                            <a href="editProfile" class="btn btn-outline-dark text-body" style="z-index: 1;"
                               data-mdb-ripple-init data-mdb-ripple-color="dark">
                                Edit profile
                            </a>
                            <br>
                            <a href="#" class="btn btn-outline-dark text-body" style="z-index: 1;"
                               data-mdb-ripple-init data-mdb-ripple-color="dark">
                                Change password
                            </a>
                        </div>
                        <div class="ms-3" style="margin-top: 130px;">
                            <h5 style="color: white">${profile.fullName}</h5>
                            <p>Role: ${profile.role.getRoleName()}</p>
                        </div>
                    </div>
                    <div class="p-4 text-black bg-body-tertiary">
                        <div class="d-flex justify-content-end text-center py-1 text-body">
                            <div class=" p-3">
                                <div class="information-content">
                                    <div class="information-user-left">
                                        <ul class="information-content-left">
                                            <li class="information-content-item">Id:</li>
                                            <li class="information-content-item">Full name:</li>
                                            <li class="information-content-item">Date of birth:</li>
                                            <li class="information-content-item">Email:</li>
                                            <li class="information-content-item">Phone number:</li>
                                            <li class="information-content-item">Address:</li>
                                            <li class="information-content-item">Gender:</li>
                                        </ul>
                                    </div>
                                    <div information-user-right>
                                        <ul class="information-content-right">
                                            <li class="information-content-item">${profile.userID}</li>
                                            <li class="information-content-item">${profile.fullName}</li>
                                            <li class="information-content-item">${profile.dateOfBirth}</li>
                                            <li class="information-content-item">${profile.email}</li>
                                            <li class="information-content-item">${profile.phone}</li>
                                            <li class="information-content-item">${profile.address}</li>
                                            <li class="information-content-item">${profile.gender}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>

</body>
</html>
