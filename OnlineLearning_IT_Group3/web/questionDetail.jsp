<!DOCTYPE html>
<html lang="en">
    <%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="model.Question" %>
    <%@page import="model.Answer" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <title>Question Management</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            form {
                background: white;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 20px;
                width: 100%;
                max-width: 600px;
                max-height: 95vh;
                overflow-y: auto;
            }

            label {
                display: block;
                margin-top: 10px;
                color: #666;
            }

            input[type="text"],
            input[type="file"],
            select,
            textarea {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            .answer-option {
                display: flex;
                align-items: center;
                margin-top: 10px;
                padding: 10px;
                background: #f9f9f9;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .answer-option input[type="text"] {
                flex: 1;
                margin-right: 10px;
            }

            .btn,
            .remove-btn,
            .back-btn,
            .add-btn {
                margin-top: 10px;
                margin-left: 20px;
                padding: 10px 15px;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1rem;
            }

            .btn {
                background-color: #4CAF50;
            }

            .btn:hover {
                background-color: #45a049;
            }

            .remove-btn {
                padding: 10px 20px;
                background-color: #f44336;
                color: white;
                border-radius: 5px;
                cursor: pointer;
            }

            .remove-btn:hover {
                background-color: #e63929;
            }

            .back-btn {
                margin-left: -10px;
                padding: 10px 50px;
                background-color: blue;
            }

            .back-btn:hover {
                background-color: blue;
            }

            .add-btn {
                background-color: darkred;
            }

            .add-btn:hover {
                background-color: darkred;
            }

            .radio-container {
                display: flex;
                align-items: center;
                margin-left: 10px;
            }

            .remove-question-container {
                display: flex;
                justify-content: flex-end;
            }
        </style>
    </head>

    <body>
        <%
                
        String error = (String) request.getAttribute("errorDetail");
            
        %>
        <c:set var="q" value="${requestScope.questionDetailInfo}"/>
        <c:set var="answer" value="${requestScope.answerDetail}"/>

        <!-- Main form for question management -->
        <form action="QuestionDetailServlet" method="POST" enctype="multipart/form-data">
            <h2>Question Title</h2>
            <h2><input type="text" name="questionTitle" value="${param.questionTitle != null ? param.questionTitle : q.questionTitle}" required></h2>

            <!-- Back button -->
            <a href="QuestionListServlet">            
                <button type="button" class="back-btn">Back</button>
            </a>
            <!-- Remove question -->
            <input type="hidden" name="questionId" value="${param.questionId != null ? param.questionId : q.questionId}">

            <div class="remove-question-container">
                <button type="button" class="remove-btn">
                    <a href="deleteQuestionServlet?questionId=${param.questionId != null ? param.questionId : q.questionId}" class="remove-btn">Remove question</a>
                </button>
            </div>

            <!-- Course Name Input -->
            <label for="questionId">Question ID</label>
            <input type="text" id="questionId"  value="${param.questionId != null ? param.questionId : q.questionId}" disabled>


            <!-- Course Name Input -->
            <label for="course">Course Name:</label>
            <input type="text" id="course" name="questionCourse" value="${param.questionCourse != null ? param.questionCourse : q.course.getCourseName()}" required>

            <!-- Question Type Input (disabled) -->
            <label for="questionType">Question Type:</label>
            <input type="hidden" name="questionType" value="${param.questionType != null ? param.questionType : q.questionType}">
            <input type="text" id="questionType" name="questionType" value="${param.questionType != null ? param.questionType : q.questionType}" disabled>

            <!-- Status Selection -->
            <label for="status">Status:</label>
            <select id="status" name="status">
                <option value="Visible" ${param.status != null && param.status == 'Visible' ? 'selected' : (q.status == 'Visible' ? 'selected' : '')}>Visible</option>
                <option value="Hidden" ${param.status != null && param.status == 'Hidden' ? 'selected' : (q.status == 'Hidden' ? 'selected' : '')}>Hidden</option>
            </select>

            <!-- Level Selection -->
            <label for="Level">Level:</label>
            <select id="level" name="level">
                <option value="Easy" ${param.level != null && param.level == 'Easy' ? 'selected' : (q.level == 'Easy' ? 'selected' : '')}>Easy</option>
                <option value="Medium" ${param.level != null && param.level == 'Medium' ? 'selected' : (q.level == 'Medium' ? 'selected' : '')}>Medium</option>
                <option value="Hard" ${param.level != null && param.level == 'Hard' ? 'selected' : (q.level == 'Hard' ? 'selected' : '')}>Hard</option>
            </select>

            <!-- Question Content Textarea -->
            <label for="questionContent">Question Content:</label>
            <textarea name="questionContent" id="questionContent" required>${param.questionContent != null ? param.questionContent : q.questionContent}</textarea>

            <!-- Media Upload Input -->
            <label for="media">Upload Media:</label>
            <div class="mb-2">
                <c:if test="${q.questionImgOrVideo != null || param.oldMedia != null}">
                    <c:choose>
                        <c:when test="${fn:endsWith(param.oldMedia != null ? param.oldMedia : q.questionImgOrVideo, '.mp4')}">
                            <video style="width: 400px;" controls src="${param.oldMedia != null ? param.oldMedia : q.questionImgOrVideo}" type="video/mp4"></video>
                            </c:when>
                            <c:otherwise>
                            <img style="width: 400px;" src="${param.oldMedia != null ? param.oldMedia : q.questionImgOrVideo}" alt="No image"/>
                        </c:otherwise>
                    </c:choose>                
                </c:if>
            </div>
            <input type="file" id="media" name="media" accept=".png,.jpg,.mp4">
            <input type="hidden" name="oldMedia" value="${q.questionImgOrVideo != null ? q.questionImgOrVideo : param.oldMedia}">


            <!-- Options Section -->
            <label>Answer:</label>
            <div id="options">
                <c:choose>
                    <c:when test="${q.questionType == 'Essay'|| param.questionType == 'Essay'}">
                        <!-- Render textarea for Essay type -->
                        <label for="essayAnswer">Correct Answer:</label>
                        <input type="hidden" name="answerId" value="${param.answerId != null ? param.answerId : answer.answerId}">
                        <textarea id="essayAnswer" name="essayAnswer" placeholder="Enter your essay answer here..." rows="5" style="width: 100%;" required>${param.essayAnswer != null ? param.essayAnswer : answer.optionContent}</textarea>
                    </c:when>
                    <c:otherwise>
                        <!-- Loop through each answer option in listOption -->
                        <c:forEach var="option" items="${answerDetailInfo}">
                            <div class="answer-option">
                                <!-- Sử dụng param.answerId như là phương án dự phòng nếu option.answerId là null -->
                                <input type="hidden" name="answerOptionId" value="${param.answerOptionId != null ? param.answerOptionId : option.answerId}">
                                <input type="text" name="answerOption" placeholder="Answer Option" 
                                       value="${param.answerOption != null ? param.answerOption : option.optionContent}" required>
                                <div class="radio-container">
                                    <input type="radio" name="correctAnswer" value="${option.answerId}" 
                                           ${option.isCorrect ? 'checked' : ''}>                                    
                                    <label>Correct</label>
                                    <a href="removeOptionServlet?answerOptionId=${param.answerOptionId != null ? param.answerOptionId : option.answerId}&questionId=${param.questionId != null ? param.questionId : q.questionId}&correctAnswer=${option.isCorrect}" class="remove-btn">Remove</a>
                                </div>
                            </div>
                        </c:forEach>

                        <a href="addAnswerServlet?questionId=${param.questionId != null ? param.questionId : q.questionId}" class="add-btn">Add Option</a>
                    </c:otherwise>
                </c:choose> 
            </div>


            <!-- Explanation Textarea -->
            <label for="explanation">Explanation:</label>
            <textarea id="explanation" name="explanation" rows="4">${param.explanation != null ? param.explanation : q.explanation}</textarea>

            <!-- Error message -->
            <h3 style="color: red"> <%=(error != null) ? error : ""%> </h3>

            <!-- Save Question Button -->
            <button type="submit" class="btn">Save Question</button>
        </form>

        <script src="js/tinymce/js/tinymce/tinymce.min.js"></script>
        <script>
            tinymce.init({
                selector: 'textarea#questionContent',
                width: '100%',
                height: 300,
                plugins: ['advlist', 'autolink', 'link', 'image', 'lists', 'charmap', 'preview', 'anchor', 'pagebreak', 'searchreplace', 'wordcount', 'visualblocks', 'code', 'fullscreen', 'insertdatetime', 'media', 'table', 'emoticons', 'template', 'codesample'],
                toolbar: 'undo redo | styles | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullscreen | forecolor backcolor emoticons',
                menubar: 'favs file edit view insert format tools table',
                content_style: 'body{font-family:Helvetica,Arial,sans-serif; font-size:16px}'
            });

        </script>
    </body>

</html>