<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
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

    <style>
        .dropdown-checkbox.open .dropdown-menu-checkbox {
            display: block;
        }
        .site-btn {
            font-size: 14px;
            color: #ffffff;
            background: #ca1515;
            font-weight: 600;
            border: none;
            text-transform: uppercase;
            display: inline-block;
            padding: 12px 30px;
            border-radius: 50px;
        }
        body {
            background-color: #f9f9f9;
        }
        .card {
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        .card-body {
            padding: 20px;
        }
        .card-title {
            font-size: 24px;
            color: #333;
        }
        p {
            font-size: 18px;
        }
        .site-btn {
            font-size: 14px;
            color: #ffffff;
            background: #ca1515;
            font-weight: 600;
            border: none;
            text-transform: uppercase;
            display: inline-block;
            padding: 12px 30px;
            border-radius: 50px;
        }
        .user-update-field{
            font-size: 15px;
            margin-right: 10px; /* Space between title and value */
            background-color: #f8f9fa;
            padding: 7px 17px;
            border-radius: 15px;
            text-transform: uppercase;
            font-weight: bold;
            letter-spacing: 1px;
            display: inline-block;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            /*width: 200px; !* Set fixed width to align all labels *!*/
        }
        .form-group {
            margin-bottom: 15px;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
        .update-profile-title {
            font-family: 'Montserrat', sans-serif; /* Use a nice modern font */
            font-weight: 700; /* Make the text bold */
            font-size: 32px; /* Larger font size */
            background-color: #bb1818 !important; /* A bold red color matching the theme */
            color: #f8f9fa !important; /* Light background for contrast */
            padding: 10px 20px; /* Add padding for spacing */
            border-radius: 50px; /* Rounded corners */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            display: inline-block; /* Keeps it inline but with block properties */
            text-transform: uppercase; /* Makes the text uppercase */
            letter-spacing: 2px; /* Adds spacing between letters */
        }
    </style>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="userProfile.jsp"><i class=" fa icon_profile"></i> User Profile</a>
                    <span class="update-profile-title"><i class="fas fa-edit"></i> Update Profile</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container mt-5">
    <div id="alertPlaceholder"></div> <!-- Placeholder for success/error messages -->

    <form id="updateForm">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <!-- Left Column -->
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="userName" class="user-update-field">Username:</label>
                            <input type="text" class="form-control" id="userName" name="userName" onblur="checkUserName();" value="">
                            <span class="error-message" id="usernameerror"></span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="user-update-field">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" value="">
                        </div>
                        <div class="form-group">
                            <label for="email" class="user-update-field">Email:</label>
                            <input type="text" class="form-control" id="email" name="email" onblur="checkEmail();" value="">
                            <span class="error-message" id="emailerror"></span>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="user-update-field">First Name:</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" value="">
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="user-update-field">Last Name:</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" value="">
                        </div>
                        <div class="form-group">
                            <label for="creditLimit" class="user-update-field">Credit Limit:</label>
                            <input type="number" class="form-control" id="creditLimit" name="creditLimit" step="0.01" value="" onblur="checkCreditLimit();">
                            <span class="error-message" id="crediterror"></span>
                        </div>
                    </div>

                    <!-- Right Column -->
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="phoneNumber" class="user-update-field">Phone Number:</label>
                            <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" onblur="checkPhoneNumber();" value="">
                            <span class="error-message" id="phoneerror"></span>
                        </div>
                        <div class="form-group">
                            <label for="job" class="user-update-field">Job:</label>
                            <input type="text" class="form-control" id="job" name="job" value="">
                        </div>
                        <div class="form-group">
                            <label for="city" class="user-update-field">City:</label>
                            <select id="city" name="city" class="form-control"></select>
                        </div>
                        <div class="form-group">
                            <label for="street" class="user-update-field">Street:</label>
                            <input type="text" class="form-control" id="street" name="street" value="">
                        </div>
                        <div class="form-group">
                            <label for="zip" class="user-update-field">Zip:</label>
                            <input type="text" class="form-control" id="zip" name="zip" value="">
                        </div>
                        <div class="form-group">
                            <label for="description" class="user-update-field">Description:</label>
                            <input type="text" class="form-control" id="description" name="description" value="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <button id="updateBtn" type="button" class="site-btn" onclick="submitForm()">
            <i class="fas fa-edit"></i> Update profile
        </button>
    </form>
</div>

    <script>
        $(document).ready(function () {
            populateValues();

        });

        function submitForm() {
            // Get the user ID from the hidden element
            const userId = 4;

            // Collect form data
            const formData = {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                creditLimit: document.getElementById('creditLimit').value,
                dateOfBirth: "1990-01-01",
                email: document.getElementById('email').value,
                phoneNumber: document.getElementById('phoneNumber').value,
                job: document.getElementById('job').value,
                address: {
                    street: document.getElementById('street').value,
                    city: document.getElementById('city').value,
                    zip: document.getElementById('zip').value,
                    description: document.getElementById('description').value
                },
                account: {
                    userName: document.getElementById('userName').value,
                    password: document.getElementById('password').value
                }
            };

            // AJAX POST request to update customer details by user ID
            $.ajax({
                url: 'http://localhost:8083/customers/' + userId,
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    // Show success message
                    $('#alertPlaceholder').html(`<div class="alert alert-success alert-dismissible fade show" role="alert">` +
                        `<i class="fas fa-check-circle"></i> Profile updated successfully!` +
                        `<button type="button" class="close" data-dismiss="alert" aria-label="Close">` +
                        `<span aria-hidden="true">&times;</span></button></div>`);
                },
                error: function(xhr) {
                    // Show error message
                    $('#alertPlaceholder').html('<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                        '<i class="fas fa-exclamation-triangle"></i> Failed to update profile!' +
                        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                        '<span aria-hidden="true">&times;</span></button></div>');
                }
            });
        }
    function populateValues(){
        const userId = 4; // Get userId from localStorage or another source
        if (userId) {
            $.ajax({
                url: 'http://localhost:8083/customers/' + userId,
                type: 'GET',
                success: function (data) {
                    // Populate form fields with response data
                    $('#userName').val(data.account.userName);
                    $('#password').val(data.account.password);
                    $('#firstName').val(data.firstName);
                    $('#lastName').val(data.lastName);
                    $('#creditLimit').val(data.creditLimit);
                    $('#email').val(data.email);
                    $('#phoneNumber').val(data.phoneNumber);
                    $('#job').val(data.job);

                    // Address details
                    $('#street').val(data.address.street);
                    $('#city').val(data.address.city);
                    $('#zip').val(data.address.zip);
                    $('#description').val(data.address.description);
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching user data:", error);
                    // You could also display an error message to the user here
                }
            });
        }
    }
    function toggleDropdown() {
        document.querySelector('.dropdown-checkbox').classList.toggle('open');
    }

    // Close the dropdown if clicked outside of it
    document.addEventListener('click', function(event) {
        var isClickInside = document.querySelector('.dropdown-checkbox').contains(event.target);
        if (!isClickInside) {
            document.querySelector('.dropdown-checkbox').classList.remove('open');
        }
    });
    function removeQueryParam() {
        const url = window.location.protocol + "//" + window.location.host + window.location.pathname;
        window.history.replaceState({}, document.title, url);
    }
</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/updateProfile.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
<%@ include file="footer.jsp" %>
</html>
