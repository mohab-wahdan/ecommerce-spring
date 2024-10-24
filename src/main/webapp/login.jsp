<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/login.css">
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
            <div class="sign-up form-group text-center">
                <p>Not a member? <a href="registration.jsp">
                    signup now <i class="fas fa-user-plus"></i></a></p>
            </div>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>
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
                url: 'http://localhost:8083/customers/login', // Your API endpoint
                method: 'POST',
                contentType: 'application/json', // Sending JSON
                data: JSON.stringify({
                    username: username,
                    password: password
                }),
                success: function(response) {
                    // Handle success response
                    if (response.success) {
                        // Redirect or show a success message
                        window.location.href = '/index.jsp'; // Redirect after successful login
                    } else {
                        // Show error message
                        showMessage(response.message, 'danger');
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


