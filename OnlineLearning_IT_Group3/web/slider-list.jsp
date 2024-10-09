<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
    <head>
        <title>Slider Management</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    </head>
    <body>
        <div class="container">
            <h2>Sliders List</h2>
            <a href="slider-form.jsp" class="btn btn-primary">Add New Slider</a>
            <table id="sliderTable" class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Media</th>
                        <th>Title</th>
                        <th>BackLink</th>
                        <th>Description</th>
                        <th>Publish</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="slider" items="${sliders}">
                        <tr>
                            <td>${slider.sliderID}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:contains(slider.imageUrl, '.mp4') || fn:contains(slider.imageUrl, '.avi') || fn:contains(slider.imageUrl, '.mov')}">
                                        <video width="100" controls>
                                            <source src="${slider.imageUrl}" type="video/mp4">
                                            Your browser does not support the video tag.
                                        </video>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${slider.imageUrl}" width="100" alt="alt"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${slider.title}</td>
                            <td>${slider.backLink}</td>
                            <td>${slider.description}</td>
                            <td>${slider.publish ? "Visible" : "Hidden"}</td>
                            <td>
                                <a href="SliderController?action=edit&id=${slider.sliderID}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="javascript:void(0);" onclick="confirmDelete(${slider.sliderID})" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
        <script>
                                    $(document).ready(function () {
                                        $('#sliderTable').DataTable();
                                    });

                                    function confirmDelete(sliderID) {
                                        if (confirm('Are you sure you want to delete this slider?')) {
                                            window.location.href = 'SliderController?action=delete&id=' + sliderID;
                                        }
                                    }
        </script>
    </body>
</html>
