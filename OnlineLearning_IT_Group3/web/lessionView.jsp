<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${Course.courseName} - Course Details</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            .sidebar {
                width: 250px;
                height: 100vh;
                background-color: #f8f9fa;
                position: fixed;
                overflow-y: auto;
            }
            .content {
                margin-left: 250px;
                padding: 20px;
                flex: 1;
            }
            .lesson-item {
                cursor: pointer;
                color: #007bff;
            }
            .lesson-item:hover {
                text-decoration: underline;
            }
            .chat-box {
                position: fixed;
                bottom: 0;
                right: 20px;
                width: 300px;
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                display: flex;
                flex-direction: column;
                max-height: 400px;
            }
            .chat-header {
                background-color: #007bff;
                color: #fff;
                padding: 10px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }
            .chat-messages {
                flex: 1;
                padding: 10px;
                overflow-y: auto;
            }
            .chat-input {
                display: flex;
                border-top: 1px solid #ddd;
            }
            .chat-input textarea {
                flex: 1;
                padding: 10px;
                border: none;
                border-radius: 0;
                resize: none;
            }
            .chat-input button {
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <!-- Sidebar for modules and lessons -->
            <div class="sidebar p-3">
                <h4>Course Material</h4>
                <div class="accordion" id="courseAccordion">
                    <c:forEach var="entry" items="${modules}">
                        <div class="card">
                            <div class="card-header" id="heading${entry.key}">
                                <h5 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${entry.key}" aria-expanded="true" aria-controls="collapse${entry.key}">
                                        ${entry.key}
                                    </button>
                                </h5>
                            </div>
                            <div id="collapse${entry.key}" class="collapse" aria-labelledby="heading${entry.key}" data-parent="#courseAccordion">
                                <div class="card-body">
                                    <ul>
                                        <c:forEach var="lesson" items="${entry.value}">
                                            <li class="lesson-item" onclick="showLessonDetails(${lesson.lessonID})">${lesson.lessonName}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="content">
                <h3 id="lessonTitle">Select a lesson</h3>
                <div id="lessonDetails">Please select a lesson from the list on the left to view its details.</div>
            </div>
        </div>

        <!-- Chat Box -->
        <div class="chat-box" id="chatBox">
            <div class="chat-header">Discussion</div>
            <div class="chat-messages" id="chatMessages"></div>
            <div class="chat-input">
                <textarea id="chatInput" rows="1" placeholder="Type your message..."></textarea>
                <button onclick="sendMessage()">Send</button>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
        <script>
                    let currentLessonID = null;

                    function showLessonDetails(lessonID) {
                        currentLessonID = lessonID; // Cập nhật bài học hiện tại
                        $.ajax({
                            url: 'LessonDetailController',
                            method: 'GET',
                            data: {lessonID: lessonID},
                            dataType: 'json',
                            success: function (response) {
                                console.log(response);
                                document.getElementById('lessonTitle').innerText = response.LessonName;
                                var lessonDetails =
                                        "<p><strong>Lesson Number:</strong> " + response.LessonNumber + "</p>" +
                                        "<p><strong>Description:</strong> " + response.Description + "</p>" +
                                        "<p><strong>Lesson URL:</strong> <a href='" + response.LessonURL + "' target='_blank'>View Lesson</a></p>";
                                document.getElementById('lessonDetails').innerHTML = lessonDetails;

                                document.getElementById('chatMessages').innerHTML = '';
                            },
                            error: function (xhr, status, error) {
                                console.error('Error:', error);
                                document.getElementById('lessonDetails').innerHTML = 'Failed to load lesson details.';
                            }
                        });
                    }

                    


        </script>
    </body>
</html>
