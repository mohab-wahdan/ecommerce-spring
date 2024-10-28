<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chicly - Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>
<body>
<div class="login-container">
    <div id="message-container"></div> <!-- Message container for alerts -->

    <div class="login-logo">
        <img src="img/logo.png" alt="Chicly Logo">
    </div>
    <div class="login-form">
        <form id="loginform">
            <h3>SIGN IN</h3>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">
                    <i class="fas fa-sign-in-alt"></i> Login
                </button>
            </div>
            <a href="https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=http://localhost:8083/login/oauth2/code/google&response_type=code&client_id=500520249170-r75a36k8tkdqfpm0dka0tci28vq881ke.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+openid&access_type=offline">
              Sign in with Google
            </a> <br>
           <a href="https://www.facebook.com/v15.0/dialog/oauth?client_id=1056032482652267&redirect_uri=http://localhost:8083/login/oauth2/code/facebook&scope=email,public_profile&response_type=code">
           Sign in with Facebook
           </a>
            <div class="sign-up form-group text-center">
                <p>Not a member? <a href="registration.jsp">
                    register now <i class="fas fa-user-plus"></i></a></p>
            </div>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#loginform').on('submit', function(event) {
            event.preventDefault(); // Prevent default form submission

            // Get form data
            var username = $('#username').val();
            var password = $('#password').val();

            // Make AJAX request to login
            $.ajax({
                url: 'http://localhost:8083/auth/login', // Your API endpoint
                method: 'POST',
                contentType: 'application/json', // Sending JSON
                data: JSON.stringify({
                    username: username,
                    password: password
                }),
                success: function(response) {
                    const token = response['jwt-token']
                    const username = response['username']
                    const role = response['role']
                    sessionStorage.setItem("jwt-token",token);
                    sessionStorage.setItem("username",username);
                    sessionStorage.setItem("role",role);
                    if(role==="admin"){
                        window.location.href = '/admin/adminDashboard.jsp';
                    }
                    else{
                        localStorage.setItem("username", "yasmeenaa");
                        alert("Welcome "+ localStorage.getItem("username")+" !");
                        window.location.href = 'index.jsp';
                    }
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
            // Determine the alert type based on the message type
            var alertType = type === 'success' ? 'alert-success' : 'alert-danger';

            // Choose the appropriate icon based on the message type
            var iconClass = type === 'success' ? 'fas fa-check-circle' : 'fas fa-exclamation-triangle';

            // Insert the alert into the message container
            $('#message-container').html(`
                <div class="alert ${alertType} alert-dismissible fade show" role="alert" style="font-size: 1.1em; font-weight: bold;">
                    <i class="${iconClass}"></i> ${message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            `);
        }

    });
</script>
<script>
	function removeQueryParam() {
		const url = window.location.protocol + "//" + window.location.host + window.location.pathname;
		window.history.replaceState({}, document.title, url);
	}
</script>