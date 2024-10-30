<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chicly - Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/registration.css">
</head>
<body>
<div class="container">
    <header>
        <img src="img/logo.png" width="98" height="31" alt="">
        <span class="form-title">Registration Form</span>
    </header>
    <div id="message-container"></div> <!-- Message container for alerts -->
    <form  id="registration-form">
        <!-- Step 1 -->
        <div class="form-step" id="step-1">
            <div class="details personal">
                <div class="fields">
                    <div class="input-field">
                        <label>First Name</label>
                        <input type="text" id="firstName" name="firstName" required>
                    </div>
                    <div class="input-field">
                        <label>Last Name</label>
                        <input type="text" id="lastName" name="lastName" required>
                    </div>
                    <div class="input-field">
                        <label>Username</label>
                        <input type="text" id="userName" name="userName" onblur="CheckUserName()" required>
                        <span class="error-message" id="usernameerror"></span>
                    </div>
                    <div class="input-field">
                        <label>Password</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                </div>
            </div>
            <button type="button" class="nextBtn" onclick="nextStep()">
                Next <i class="uil uil-arrow-right"></i>
            </button>
        </div>

        <!-- Step 2 -->
        <div class="form-step" id="step-2" style="display:none;">
            <div class="details ID">
                <div class="fields">
                    <div class="input-field">
                        <label>Job</label>
                        <input type="text" id="job" name="job" required>
                    </div>
                    <div class="input-field">
                        <label>Email</label>
                        <input type="text" id="email" name="email" onblur="CheckEmail()" required>
                        <span class="error-message" id="emailerror"></span>
                    </div>
                    <div class="input-field">
                        <label>Phone Number</label>
                        <input type="number" id="phoneNumber" name="phoneNumber" onblur="CheckPhoneNumber()" required>
                        <span class="error-message" id="phoneerror"></span>
                    </div>
                    <div class="input-field">
                        <label>Credit Limit</label>
                        <input type="number" id="creditLimit" name="creditLimit" onblur="CheckCreditLimit()" required>
                        <span class="error-message" id="crediterror"></span>
                    </div>
                    <div class="input-field">
                        <label>Date of Birth</label>
                        <input type="date" id="dateOfBirth" name="dateOfBirth" required>
                    </div>
                </div>
            </div>
            <div class="buttons">
                <button type="button" class="prevBtn" onclick="prevStep()">
                    <i class="uil uil-arrow-left"></i> Previous
                </button>
                <button type="button" class="nextBtn" onclick="nextStep()">
                    Next <i class="uil uil-arrow-right"></i>
                </button>
            </div>
        </div>

        <!-- Step 3 -->
        <div class="form-step" id="step-3" style="display:none;">
            <div class="details ID">
                <div class="fields">

                    <div class="input-field">
                        <label>Street</label>
                        <input type="text" id="street" name="street" required>
                    </div>
                    <div class="input-field">
                        <label for="city">City</label>
                        <select id="city" name="city" required>
                            <!-- Options will be populated by JavaScript -->
                        </select>
                    </div>
                    <div class="input-field">
                        <label>Description</label>
                        <input type="text" id="description" name="description" required>
                    </div>
                    <div class="input-field">
                        <label>Zip Code</label>
                        <input type="number" id="zip" name="zip" required>
                    </div>
                </div>
            </div>
            <div class="buttons">
                <button type="button" class="prevBtn" onclick="prevStep()">Previous</button>
                <button class="nextBtn" type="submit" id="registerBtn" onmouseover="checkCondition()">
                    <span class="btnText">Register</span>
                    <i class="uil uil-navigator"></i>
                </button>
            </div>

        </div>
    </form>
</div>
 <script src="js/register.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script type="text/javascript">
    $(document).ready(function() {
        // Handle form submission
        $('#registration-form').on('submit', function(event) {
            event.preventDefault(); // Prevent default form submission

            // Gather form data
            var formData = {
                firstName: document.getElementById("firstName").value,
                lastName: document.getElementById("lastName").value,
                creditLimit: document.getElementById("creditLimit").value,
                dateOfBirth:  document.getElementById("dateOfBirth").value,
                email:  document.getElementById("email").value,
                phoneNumber:  document.getElementById("phoneNumber").value,
                job: document.getElementById("job").value,
                address: {
                    street: document.getElementById("street").value,
                    city: document.getElementById("city").value,
                    zip: document.getElementById("zip").value,
                    description: document.getElementById("description").value
                },
                account: {
                    userName: document.getElementById("userName").value,
                    password: document.getElementById("password").value
                }
            };

            // Make AJAX request to register the user
            $.ajax({
                url: '/customers', // Your API endpoint
                method: 'POST',
                contentType: 'application/json', // Specify JSON format
                data: JSON.stringify(formData), // Convert formData to JSON string
                success: function(response) {
                    window.location.href = 'index.jsp'; // Redirect on success
                },
                error: function(xhr) {
                    // Handle error response
                    var errorMessage = xhr.responseJSON ? xhr.responseJSON.message : 'An unexpected error occurred.';
                    showMessage(errorMessage, 'danger');
                }
            });

        });

        // Function to display messages
        function showMessage(message, type) {
            var alertType = type === 'success' ? 'alert-success' : 'alert-danger';
            $('#message-container').html(`
                <div class="alert ${alertType} alert-dismissible fade show" role="alert" style="font-size: 1.1em; font-weight: bold;">
                    <i class="${type == 'success' ? 'fas fa-check-circle' : 'fas fa-exclamation-triangle'}"></i> ${message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            `);
        }
    });


 </script>
</body>
</html>