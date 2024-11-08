<%-- 
    Document   : createMooc
    Created on : Nov 8, 2024, 2:10:23 PM
    Author     : DTC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Secret Coder: Courses</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css">
        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>


    </head>

    <body>
        <%@include file="header.jsp" %>

        <!-- Header Start -->
        <div class="container-fluid bg-primary py-5 mb-5 page-header">
            <div class="container py-5">
                <div class="row justify-content-center">
                    <div class="col-lg-10 text-center">
                        <h1 class="display-3 text-white animated slideInDown">Courses</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center">
                                <li class="breadcrumb-item"><a class="text-white" href="index.html">Home</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">ManagerCourse</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->

        <div class="container-xxl py-5">
            <div class="container">


                <form action="CreateMooc" method="post">
                    <input type="text" value="${courseid}" name="courseid" hidden="">
                    <div style="margin-top: 30px">
                        <strong >Mooc Number</strong><br/>
                        <input style="width: 500px; background-color: #dddddd" type="text" name="moocnumber" value="${lastnumber}" readonly="" >
                    </div>
                    <div style="margin-top: 30px">
                        <strong>Mooc Name</strong><br/>
                        <input style="width: 500px;" type="text" name="moocname">
                    </div>

                    <div class="text-center mt-3 mb-3" style="margin-left: -598px">
                        <button
                            class="button"
                            >Create New Mooc&nbsp;
                        </button>
                    </div>
                </form>


            </div>
        </div>

        <%@include file="footer.jsp" %>

    </body>

</html>

