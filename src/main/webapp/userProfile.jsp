<%@ include file="header.jsp" %>

<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="/"><i class="fa fa-home"></i> Home</a>
                    <span class="order-history-title">User Profile</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">User Information</h4>
            <p><strong>Username:</strong> <span id="username"></span></p>
            <p><strong>First Name:</strong> <span id="firstName"></span></p>
            <p><strong>Last Name:</strong> <span id="lastName"></span></p>
            <p><strong>Credit Limit:</strong> <span id="creditLimit"></span></p>
            <p><strong>Email:</strong> <span id="email"></span></p>
            <p><strong>Phone Number:</strong> <span id="phoneNumber"></span></p>
            <p><strong>Job:</strong> <span id="job"></span></p>
            <p><strong>Address:</strong> <span id="address"></span></p>
        </div>
    </div>

    <!-- Update Button -->
    <div class="mt-4">
        <a href="updatecustomer" class="site-btn">
            <i class="fas fa-edit"></i> Update Profile</a>
    </div>
</div>

<script>
    $(document).ready(function() {
        // Replace with the actual ID from the session or context
        var userId = '<%= session.getAttribute("userId") %>'; // Example of getting userId from session

        $.ajax({
            url: '${pageContext.request.contextPath}/user/' + userId,
            type: 'GET',
            success: function(data) {
                // Populate the fields with data from the response
                $('#username').text(data.userName);
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
        .order-history-title {
            font-family: 'Montserrat', sans-serif; /* Use a nice modern font */
            font-weight: 700; /* Make the text bold */
            font-size: 32px; /* Larger font size */
            color: #ca1515; /* A bold red color matching the theme */
            background-color: #f8f9fa; /* Light background for contrast */
            padding: 10px 20px; /* Add padding for spacing */
            border-radius: 50px; /* Rounded corners */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            display: inline-block; /* Keeps it inline but with block properties */
            text-transform: uppercase; /* Makes the text uppercase */
            letter-spacing: 2px; /* Adds spacing between letters */
        }
    </style>

<%@ include file="footer.jsp" %>
