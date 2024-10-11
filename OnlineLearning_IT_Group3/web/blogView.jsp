<%-- 
    Document   : blogForm
    Created on : Oct 9, 2024, 9:57:58 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Detail</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Blog Details</h1>
    <c:if test="${not empty blog}">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${blog.title}</h5>
                <p class="card-text">${blog.content}</p>
                <p><strong>Status:</strong> ${blog.status}</p>
                <p><strong>Updated At:</strong> ${blog.updatedAt}</p>
                <p><strong>Created At:</strong> ${blog.createdAt}</p>
                <a href="BlogManageController?action=edit&id=${blog.blogId}" class="btn btn-primary">Edit</a>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty message}">
        <div class="alert alert-info mt-3">
            ${message}
        </div>
    </c:if>

    <a href="home.jsp" class="btn btn-secondary mt-3">Back to Home</a>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>

