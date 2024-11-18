<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Montserrat', sans-serif;
        }

        .breadcrumb-option {
            margin: 20px 0;
        }

        .breadcrumb__links {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .breadcrumb__links a, .breadcrumb__links span {
            font-size: 18px;
            color: #bb1818;
            text-transform: uppercase;
            text-decoration: none;
        }

        .breadcrumb__links a:hover {
            text-decoration: underline;
        }

        .user-profile-title {
            ont-family: 'Montserrat', sans-serif; /* Use a nice modern font */
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

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 30px;
        }

        .user-info-title {
            font-size: 22px;
            color: #333;
            font-weight: 700;
            border-bottom: 2px solid #bb1818;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .user-info-card-title {
            font-size: 16px;
            color: #555;
            font-weight: 600;
            text-transform: uppercase;
            display: inline-block;
            width: 180px;
        }

        p {
            font-size: 18px;
            margin: 10px 0;
            color: #333;
            display: flex;
            align-items: center;
        }

        span {
            flex-grow: 1;
        }

        .site-btn {
            font-size: 16px;
            color: #fff;
            background-color: #bb1818;
            padding: 12px 30px;
            text-transform: uppercase;
            font-weight: 700;
            border: none;
            border-radius: 50px;
            transition: all 0.3s ease-in-out;
            display: inline-block;
            margin-top: 20px;
        }

        .site-btn:hover {
            background-color: #9c1414;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
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

<div class="container">
    <div class="card">
        <div class="card-body">
            <h4 class="user-info-title">User Information</h4>
            <p><strong class="user-info-card-title">Username</strong><span id="username"></span></p>
            <p><strong class="user-info-card-title">First Name</strong><span id="firstName"></span></p>
            <p><strong class="user-info-card-title">Last Name</strong><span id="lastName"></span></p>
            <p><strong class="user-info-card-title">Credit Limit</strong><span id="creditLimit"></span></p>
            <p><strong class="user-info-card-title">Email</strong><span id="email"></span></p>
            <p><strong class="user-info-card-title">Phone Number</strong><span id="phoneNumber"></span></p>
            <p><strong class="user-info-card-title">Job</strong><span id="job"></span></p>
            <p><strong class="user-info-card-title">Address</strong><span id="address"></span></p>
        </div>
    </div>

    <a href="updateProfile.jsp" class="site-btn">
        <i class="fas fa-edit"></i> Update Profile
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        var userId = sessionStorage.getItem("id");

        $.ajax({
            url: '/customers/' + userId,
            type: 'GET',
            success: function(data) {
                $('#username').text(data.account.userName);
                $('#firstName').text(data.firstName);
                $('#lastName').text(data.lastName);
                $('#creditLimit').text(data.creditLimit);
                $('#email').text(data.email);
                $('#phoneNumber').text(data.phoneNumber);
                $('#job').text(data.job);
                $('#address').text(
                    `${data.address.street}, ${data.address.city}, ${data.address.zip}, ${data.address.description}`
                );
            },
            error: function(error) {
                console.error("Error fetching user data:", error);
            }
        });
    });
</script>

<%@ include file="footer.jsp" %>
</body>
</html>
