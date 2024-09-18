<%-- 
    Document   : editProfile
    Created on : 18 thg 9, 2024, 22:56:24
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

        <title>Edit profile</title>
    </head>

    <body>
        <%@include file= "header.jsp" %>
    <c:set var="profile" value="${requestScope.profile}"/>

    <br>
    <div class="container">
        <div class="row g-5 justify-content-between">
            <div class="col-md-8 mx-auto">
                <!-- Title -->
                <h2 class="mb-3">Personal information</h2>
                <!-- Form START -->
                <form class="row g-3" action="editProfile" method="post">
                    <!-- Full name -->
                    <div class="col-12">
                        <div class="row g-xl-0 align-items-center">
                            <div class="col-lg-4">
                                <h6 class="mb-lg-0">Student full name <span class="text-danger">*</span></h6>
                            </div>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" name="fullName" value="${profile.fullName}">
                            </div>
                        </div>
                    </div>
                    <!-- Gender -->
                    <div class="col-12">
                        <div class="row g-xl-0 align-items-center">
                            <div class="col-lg-4">
                                <h6 class="mb-lg-0">Gender </h6>
                            </div>
                            <div class="col-lg-8">
                                <select name="gender"
                                        class="form-select js-choice z-index-9 rounded-3 border-0 bg-light"
                                        aria-label=".form-select-sm">
                                    <option value="male" <c:if test="${profile.gender == 'male'}">selected</c:if>>Male</option>
                                    <option value="female" <c:if test="${profile.gender == 'female'}">selected</c:if>>Female</option> 
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- Date of birth -->
                    <div class="col-12">
                        <div class="row g-xl-0 align-items-center">
                            <div class="col-lg-4">
                                <h6 class="mb-lg-0">Date of birth<span class="text-danger">*</span></h6>
                            </div>
                            <div class="col-lg-8">
                                <div class="row g-2 g-sm-4">
                                    <div class="col-12">
                                        <input type="date" class="form-control" id="day"
                                               value="${profile.dateOfBirth}" oninput="updateDays();" name="dateOFBirth" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Phone number -->
                    <div class="col-12">
                        <div class="row g-xl-0 align-items-center">
                            <div class="col-lg-4">
                                <h6 class="mb-lg-0">Phone number <span class="text-danger">*</span></h6>
                            </div>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" name="phone" value="${profile.phone}">
                            </div>
                        </div>
                    </div>
                    <!-- Address -->
                    <div class="col-12">
                        <div class="row g-xl-0">
                            <div class="col-lg-4">
                                <h6 class="mb-lg-0">Your address <span class="text-danger">*</span></h6>
                            </div>
                            <div class="col-lg-8">
                                <textarea name="address" class="form-control" rows="3" placeholder="${profile.address}"></textarea>
                            </div>
                        </div>
                    </div>

                    <!-- Avatar -->
                    <div class="col-12">
                        <div class="row g-xl-0 align-items-center">
                            <div class="col-lg-4">
                                <h6 class="mb-lg-0">Avatar</span></h6>
                            </div>
                            <div class="col-lg-8">
                                <input type="file" class="form-control" name="avatar" value="${profile.avatar}" accept=".jpg, .jpeg, .png">
                            </div>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="col-12 text-end">
                        <button class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
            </body>
            </html>
