<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Blog</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            .preview-img {
                max-width: 200px;
                margin-top: 10px;
                display: none;
            }
        </style>
        <script src="https://cdn.tiny.cloud/1/qnmf6c0u3j7wk6wsljsqwke06htozhifzb9v9fs3pw2ed7vx/tinymce/7/tinymce.min.js" referrerpolicy="origin"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Add New Blog</h1>
            <form action="BlogManageController" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="add">

                <div class="form-group">
                    <label for="category">Category</label>
                    <select class="form-control" id="category" name="categoryId" required>
                        <c:forEach var="category" items="${categoryBlogs}">
                            <option value="${category.categoryId}">
                                ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="content">Content</label>
                    <textarea class="form-control tinymce" id="content" name="content" rows="10"></textarea>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select class="form-control" id="status" name="status">
                        <option value="Draft">Draft</option>
                        <option value="Published">Published</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="featuredImage">Featured Image</label>
                    <input type="file" class="form-control" id="featuredImage" name="featuredImage" onchange="previewImage(event)">
                    <div>
                        <img id="preview" class="preview-img" alt="Image Preview">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Add Blog</button>
            </form>

            <a href="BlogManageController" class="btn btn-secondary mt-3">Cancel</a>
        </div>

        <script>
            function previewImage(event) {
                const preview = document.getElementById('preview');
                preview.style.display = 'block';
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        preview.src = e.target.result;
                    }
                    reader.readAsDataURL(file);
                }
            }

            document.querySelector("form").addEventListener("submit", function (e) {
                const content = tinymce.get("content").getContent();
                if (!content) {
                    alert("Content is required");
                    e.preventDefault();
                }
            });

            tinymce.init({
                selector: '.tinymce',
                plugins: 'advlist autolink lists link image charmap print preview hr anchor pagebreak media',
                toolbar_mode: 'floating'
            });
        </script>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
