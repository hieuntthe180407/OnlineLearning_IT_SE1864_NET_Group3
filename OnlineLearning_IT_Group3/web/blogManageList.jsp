<%-- 
    Document   : blogManageList
    Created on : Oct 27, 2024, 7:30:55 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Blog List</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Include DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Blog List</h1>
        <a href="BlogManageController?action=add" class="btn btn-info btn-sm">Add</a>
        <table id="blogTable" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blog" items="${blogs}">
                    <tr>
                        <td>${blog.title}</td>
                        <td>${blog.content}</td>
                        <td>${blog.status}</td>
                        <td>${blog.createdAt}</td>
                        <td>${blog.updatedAt}</td>
                        <td>
                            <a href="BlogManageController?action=viewDetail&id=${blog.blogId}" class="btn btn-info btn-sm">View</a>
                            <a href="BlogManageController?action=edit&id=${blog.blogId}" class="btn btn-warning btn-sm">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Include Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Include DataTables JS -->
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
    <!-- Initialize DataTable -->
    <script>
        $(document).ready(function() {
            $('#blogTable').DataTable({
                "lengthMenu": [5, 10, 25, 50],
                "pageLength": 10
            });
        });
    </script>
</body>
</html>
