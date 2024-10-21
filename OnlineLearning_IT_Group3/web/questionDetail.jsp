<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <!-- Main form for question management -->
        <form action="QuestionDetail" method="POST" enctype="multipart/form-data">
            <h2>Question Detail</h2>

            <!-- Back button -->
            <a href="QuestionListServlet">            
                <button type="button" class="back-btn">Back</button>
            </a>
            <!-- Remove question -->
            <div class="remove-question-container">
                <button type="submit" class="remove-btn">Remove question</button>
            </div>

            <!-- Course Name Input -->
            <label for="course">Course Name:</label>
            <input type="text" id="course" name="course">

            <!-- Question Type Input (disabled) -->
            <label for="questionType">Question Type:</label>
            <input type="text" id="questionType" name="questionType" value="Multiple Choice" disabled>

            <!-- Status Selection -->
            <label for="status">Status:</label>
            <select id="status" name="status">
                <option value="Visible">Visible</option>
                <option value="Hidden">Hidden</option>
            </select>

            <!-- Question Content Textarea -->
            <label for="questionContent">Question Content:</label>
            <textarea name="questionContent" id="questionContent" required></textarea>

            <!-- Media Upload Input -->
            <label for="media">Upload Media:</label>
            <input type="file" id="media" name="media" accept="image/*,video/*,audio/*">

            <!-- Options Section -->
            <label>Options:</label>
            <div id="options">
                <div class="answer-option">
                    <input type="hidden" name="answerOptionId" value="0"> <!-- Use 0 for new options -->
                    <input type="text" name="answerOption" placeholder="Answer Option">
                    <div class="radio-container">
                        <input type="radio" name="correctAnswer" checked>
                        <label>Correct</label>
                        <button type="submit" class="remove-btn" formaction="removeServlet" name="removeId"
                                value="0">Remove</button>
                    </div>
                </div>
                <div class="answer-option">
                    <input type="hidden" name="answerOptionId" value="1"> <!-- Use 1 for new options -->
                    <input type="text" name="answerOption" placeholder="Answer Option">
                    <div class="radio-container">
                        <input type="radio" name="correctAnswer">
                        <label>Correct</label>
                        <button type="submit" class="remove-btn" formaction="removeServlet" name="removeId"
                                value="1">Remove</button>
                    </div>
                </div>
            </div>
            <button type="submit" class="add-btn" formaction="answerServlet">Add Option</button>


            <!-- Explanation Textarea -->
            <label for="explanation">Explanation:</label>
            <textarea id="explanation" name="explanation" rows="4"></textarea>

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