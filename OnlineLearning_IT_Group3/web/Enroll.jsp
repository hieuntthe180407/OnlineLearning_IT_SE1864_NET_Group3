<%-- 
    Document   : Enroll
    Created on : Oct 20, 2024, 1:11:07 AM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
   <head>
        
         <meta charset="utf-8">
    <meta name="google-translate-customization" content="9f841e7780177523-3214ceb76f765f38-gc38c6fe6f9d06436-c">
    </meta>

    <title>Secret Coder : Online Courses</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/icon.png" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
        rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <% User user = (User) session.getAttribute("acc");
            String cID = request.getParameter("CourseID");
        %>
         
       
        <%@include file= "header.jsp" %>
         <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Register Confirm</h1>
                    
                </div>
            </div>
        </div>
    </div>
         <% if(user == null){ %>
         <!-- input information form  for guest without login -->
        <div class="col-lg-6 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
            <form action="ReviewAdd" method="get">
    <div class="row g-3">
        <div class="col-md-6">
            <div class="form-floating">
                <input type="text" class="form-control" id="name" name="FullName" required placeholder="Your Name">
                <label for="name">Your Name</label>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-floating">
                <input type="email" class="form-control" id="email" name="Email" required placeholder="Your Email">
                <label for="email">Your Email</label>
            </div>
        </div>
        
        <!-- Multiple phone number inputs -->
        <div class="col-12">
            <div class="form-floating">
                <input type="text" class="form-control" id="phone1" name="Phone[]" required placeholder="Phone number 1" oninput="updatePreferredPhone()">
                <label for="phone1">Phone number 1</label>
            </div>
        </div>
        <div class="col-12">
            <div class="form-floating">
                <input type="text" class="form-control" id="phone2" name="Phone[]" placeholder="Phone number 2" oninput="updatePreferredPhone()">
                <label for="phone2">Phone number 2</label>
            </div>
        </div>
        <div class="col-12">
            <div class="form-floating">
                <input type="text" class="form-control" id="phone3" name="Phone[]" placeholder="Phone number 3" oninput="updatePreferredPhone()">
                <label for="phone3">Phone number 3</label>
            </div>
        </div>

        <!-- Dropdown to select preferred phone number -->
        <div class="col-12">
            <label for="preferredPhone">Preferred Phone Number</label>
            <select class="form-select" id="preferredPhone" name="PreferredPhone" required>
                <option value="">Select your preferred phone number</option>
            </select>
        </div>

        <div class="col-12">
            <div class="form-floating">
                <textarea class="form-control" required placeholder="Leave a message here" id="message" style="height: 150px"></textarea>
                <label for="message">Message</label>
            </div>
        </div>
        <div class="col-12">
            <button class="btn btn-primary w-100 py-3" type="submit">Submit Register</button>
        </div>
    </div>
</form>

<script>
    function updatePreferredPhone() {
        const phoneNumbers = [
            document.getElementById('phone1').value,
            document.getElementById('phone2').value,
            document.getElementById('phone3').value
        ];

        const selectDropdown = document.getElementById('preferredPhone');
        selectDropdown.innerHTML = '<option value="">Select your preferred phone number</option>'; // Clear existing options

        phoneNumbers.forEach((number, index) => {
            if (number) { // Only add the number if it's not empty
                const option = document.createElement('option');
                option.value = `phone${index + 1}`;
                option.textContent = number;
                selectDropdown.appendChild(option);
            }
        });
    }
</script>


                </div>
         <%}%>
        
        <%@include file= "footer.jsp" %>
    </body>
</html>
