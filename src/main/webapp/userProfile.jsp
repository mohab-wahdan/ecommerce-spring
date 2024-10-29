<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <style>
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
        /*p {*/
        /*    font-size: 18px;*/
        /*    color: #333;*/
        /*    display: flex; !* Use flexbox to align items on the same line *!*/
        /*    align-items: center; !* Vertically align items *!*/
        /*}*/
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
        .user-profile-title {
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
        .user-info-title {
            font-size: 20px;
            color: #ca1515; /* Bold red color for title */
            background-color: #f8f9fa;
            padding: 10px 20px;
            border-radius: 10px;
            text-transform: uppercase;
            font-weight: bold;
            letter-spacing: 1px;
            display: inline-block;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        p {
            font-size: 18px;
            color: #333;
            display: flex;
            align-items: center;
            margin: 10px 0; /* Add vertical spacing between rows */
        }

        .user-info-card-title {
            font-size: 16px;
            margin-right: 10px; /* Space between title and value */
            background-color: #f8f9fa;
            padding: 5px 10px;
            border-radius: 10px;
            text-transform: uppercase;
            font-weight: bold;
            letter-spacing: 1px;
            display: inline-block;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 180px; /* Set fixed width to align all labels */
        }

        span {
            display: inline-block;
            flex-grow: 1; /* Ensures that the data aligns to the same start point */
        }

    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="index.jsp"><i class="fa fa-home"></i> Home</a>
                    <span class="user-profile-title"><i class="fa fa-user"></i> User Profile</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title user-info-title">User Information</h4>
            <p><strong class="user-info-card-title">Username:</strong><strong><span id="username"></span></strong></p>
            <p><strong class="user-info-card-title">First Name:</strong><strong><span id="firstName"></span></strong></p>
            <p><strong class="user-info-card-title">Last Name:</strong><strong><span id="lastName"></span></strong></p>
            <p><strong class="user-info-card-title">Credit Limit:</strong><strong><span id="creditLimit"></span></strong></p>
            <p><strong class="user-info-card-title">Email:</strong><strong><span id="email"></span></strong></p>
            <p><strong class="user-info-card-title">Phone Number:</strong><strong><span id="phoneNumber"></span></strong></p>
            <p><strong class="user-info-card-title">Job:</strong><strong><span id="job"></span></strong></p>
            <p><strong class="user-info-card-title">Address:</strong><strong><span id="address"></span></strong></p>
        </div>
    </div>

    <!-- Update Button -->
    <div class="mt-4">
        <a href="updateProfile.jsp" class="site-btn">
            <i class="fas fa-edit"></i> Update Profile</a>
    </div>
</div>

<script>
    $(document).ready(function() {
        // Replace with the actual ID from the session or context
        var userId = sessionStorage.getItem("id");

        $.ajax({
            url: '/customers/' + userId,
            type: 'GET',
            success: function(data) {
                // Populate the fields with data from the response
                $('#username').text(data.account.userName);
                $('#firstName').text(data.firstName);
                $('#lastName').text(data.lastName);
                $('#creditLimit').text(data.creditLimit);
                $('#email').text(data.email);
                $('#phoneNumber').text(data.phoneNumber);
                $('#job').text(data.job);
                $('#address').text(data.address.street + ', ' + data.address.city + ', ' + data.address.zip + ', ' + data.address.description);
            },
            error: function(error) {
                console.error("Error fetching user data:", error);
                alert("Unable to load user information.");
            }
        });
    });
</script>

</body>
<%@ include file="footer.jsp" %>
</html>
