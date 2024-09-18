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
        <div class="container">
            <div class="row g-5 justify-content-between">
                <div class="col-md-8 mx-auto">
                    <!-- Title -->
                    <h2 class="mb-3">Personal information</h2>
                    <!-- Form START -->
                    <form class="row g-3">
                        <!-- Full name -->
                        <div class="col-12">
                            <div class="row g-xl-0 align-items-center">
                                <div class="col-lg-4">
                                    <h6 class="mb-lg-0">Student full name <span class="text-danger">*</span></h6>
                                </div>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" name="fullName" value="Nguyen van a">
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
                                        <option>Male</option>
                                        <option>Female</option>
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
                                        <div class="col-2">
                                            <input type="number" class="form-control" id="day" placeholder="Day" min="1"
                                                   max="31" value="1" oninput="updateDays(); updateDate();">
                                        </div>

                                        <div class="col-2">
                                            <input type="number" class="form-control" id="month" placeholder="Month" min="1"
                                                   max="12" value="12" oninput="updateDays(); updateDate();">
                                        </div>

                                        <div class="col-3">
                                            <input type="number" class="form-control" id="year" placeholder="Year"
                                                   min="1900" value="2000"  oninput="updateDays(); updateDate();">
                                        </div>

                                        <div class="col-5">
                                            <input type="text" class="form-control" id="fullDate" placeholder="Date"
                                                   name="fullDate" readonly>
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
                                    <input type="text" class="form-control" name="phoneNumber">
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
                                    <textarea name="address" class="form-control" rows="3" placeholder=""></textarea>
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
                                    <input type="file" class="form-control" name="avatar" value="">
                                </div>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="col-12 text-end">
                            <button class="btn btn-primary">Submit</button>
                        </div>



                </div>

                </body>
                <script>

                    function isLeapYear(year) {
                        return (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
                    }

                    function updateDays() {
                        const year = document.getElementById('year').value;
                        const month = document.getElementById('month').value;
                        const dayInput = document.getElementById('day');

                        if (month && year) {
                            let maxDays;
                            switch (parseInt(month)) {
                                case 2: // February
                                    maxDays = isLeapYear(year) ? 29 : 28;
                                    break;
                                case 4:
                                case 6:
                                case 9:
                                case 11: // April, June, September, November
                                    maxDays = 30;
                                    break;
                                default: // All other months have 31 days
                                    maxDays = 31;
                            }
                            dayInput.max = maxDays;
                            if (dayInput.value > maxDays) {
                                dayInput.value = maxDays; // Adjust day input to maxDays if it exceeds
                            }
                        }
                    }

                    function updateDate() {
                        const day = document.getElementById('day').value;
                        const month = document.getElementById('month').value;
                        const year = document.getElementById('year').value;

                        let fullDate = '';

                        if (day) {
                            fullDate += `${day.padStart()}/`;
                        }
                        if (month) {
                            fullDate += `${month.padStart()}/`;
                        }
                        if (year) {
                            fullDate += `${year}`;
                        }

                        document.getElementById('fullDate').value = fullDate;
                    }

                    document.addEventListener('DOMContentLoaded', updateDate);



                </script>
                </html>
