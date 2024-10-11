<%-- 
    Document   : questionImport
    Created on : 4 thg 10, 2024, 21:22:14
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Import Questions</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .import-btn, .back-btn {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            margin-top: 10px;
        }

        .import-btn {
            background-color: #007bff;
            color: white;
        }

        .import-btn:hover {
            background-color: #0056b3;
        }

        .back-btn {
            background-color: #6c757d;
            color: white;
            margin-bottom: 20px;
        }
        .download-template {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }

        .download-template:hover {
            text-decoration: underline;
        }

        h3.error-message {
            color: red;
            text-align: center;
        }
    </style>
    <%
                
String error = (String) request.getAttribute("errorImport");
            
    %>
    <body>
        <div class="container">
            <h1>Import Questions</h1>
            <!-- Back Button -->
            <a href="QuestionListServlet">
                <button type="button" class="back-btn">Back</button>
            </a>
            
            <form action="ImportServlet" method="post" enctype="multipart/form-data">           
                <!-- Add to Course Input -->
                <div class="form-group">
                    <label for="">Add to Course:</label>
                    <input type="text" name="course" placeholder="Enter course name...">
                </div>

                <!-- Form Group for File Upload -->
                <div class="form-group">
                    <label for="">Select file to import (.xlsx):</label>
                    <input type="file" name="fileInput" accept=".xlsx, .xls" required>
                </div>
                <h3 style="color: red"> <%=(error != null) ? error : ""%> </h3>


                <!-- Import Button -->
                <button type="submit" class="import-btn">Import Questions</button>
            </form>
        </div>

        <div class="container">       
            <!-- Download Template -->
            <form action="ExportServlet" method="get">
                <button type="submit" class="import-btn">Download Sample File</button>
            </form>
        </div>


    </body>
</html>
