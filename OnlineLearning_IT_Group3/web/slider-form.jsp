<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>${slider != null ? "Edit Slider" : "Add New Slider"}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>${slider != null ? "Edit Slider" : "Add New Slider"}</h2>
    <c:if test="${not empty errors}">
        <div class="alert alert-danger" role="alert">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form action="SliderController" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sliderID" value="${slider.sliderID}">

        <div class="form-group">
            <label for="imageUrl">Media Upload:</label>
            <input type="hidden" name="old-media" value="${slider.imageUrl}">
            <input type="file" class="form-control" id="imageUrl" name="imageUrl" accept="image/*,video/*" onchange="previewMedia(event)">
        </div>

        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title" value="${slider.title}">
        </div>

        <div class="form-group">
            <label for="backLink">BackLink:</label>
            <input type="text" class="form-control" id="backLink" name="backLink" value="${slider.backLink}">
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" name="description" value="${slider.description}">
        </div>

        <div class="form-group">
            <label for="publish">Publish:</label>
            <select class="form-control" id="publish" name="publish">
                <option value="true" ${slider.publish ? "selected" : ""}>Yes</option>
                <option value="false" ${!slider.publish ? "selected" : ""}>No</option>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>
</body>
</html>
