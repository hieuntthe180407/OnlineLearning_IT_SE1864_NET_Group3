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
            String price = request.getParameter("Price");
            String cName = request.getParameter("CourseName");
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
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h1 class="mb-5 bg-white text-center px-3">You Gonna Pay <%=price%> $ </h1>

                </div>
                <div class="row g-4">
                    <div class="col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                        <h4><%=cName%></h4>
                        <img src="img/lesson/image1.jpg"/> 
                        </div>
                        <% if(user == null){ %>
                        
                        <div class="col-lg-6">
                            <h5 class="mb4" style="margin-top: 40px">Or You can participate in our 
                            modern IT online learning system by creating a new account</h5>
                            <a href="register"
                               class="text-decoration-none text-white btn p-3 w-100 mb-2">JOIN NOW</a>
                        </div>

                   

                    
                    <!-- input information form  for guest without login -->
                    <div class="col-lg-12 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                        <form action="PurchaseAdd" method="get">
                            <input type="hidden" name="CourseID" value="<%= cID %>">
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
                                 <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="Address" class="form-control" id="Address" name="Address" required placeholder="Your Address">
                                        <label for="Address">Your Address</label>
                                    </div>
                                </div>

                                <!-- Multiple phone number inputs -->
                                <div class="col-12">
    <div class="form-floating">
        <input type="tel" class="form-control" id="phone1" name="Phone" required placeholder="Phone number 1" oninput="updatePreferredPhone()" minlength="10" maxlength="12" pattern="\d{10,12}">
        <label for="phone1">Phone number 1(10-12 digits)</label>
    </div>
</div>
<div class="col-12">
    <div class="form-floating">
        <input type="tel" class="form-control" id="phone2" name="Phone" placeholder="Phone number 2" oninput="updatePreferredPhone()" minlength="10" maxlength="12" pattern="\d{10,12}">
        <label for="phone2">Phone number 2(10-12 digits)</label>
    </div>
</div>
<div class="col-12">
    <div class="form-floating">
        <input type="tel" class="form-control" id="phone3" name="Phone" placeholder="Phone number 3" oninput="updatePreferredPhone()" minlength="10" maxlength="12" pattern="\d{10,12}">
        <label for="phone3">Phone number 3(10-12 digits)</label>
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
                                    <button class="btn btn-primary w-100 py-3" type="submit">Submit Register</button>
                                </div>
                            </div>
                        </form>
                             <%}else{%>
                             <form action="PurchaseAdd" method="get">
                                 <input type="hidden" name="CourseID" value="<%= cID %>">
                                 <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">Submit Register</button>
                                </div>
                             </form>  
                             <%}%>
                    </div>
                </div>
            </div>       


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
            option.value = number; // Set the value to the phone number
            option.textContent = number; // Display the phone number as text
            selectDropdown.appendChild(option);
        }
    });
}
</script>


        </div>
       

        <%@include file= "footer.jsp" %>
    </body>
</html>
