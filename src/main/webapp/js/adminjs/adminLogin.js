$(document).ready(function() {
    $('#loginform').on('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        // Get form data
        var username = $('#username').val();
        var password = $('#password').val();

        // Make AJAX request to login
        $.ajax({
            url: 'http://localhost:8083/admin/login', // Correct your API endpoint
            method: 'POST',
            contentType: 'application/json', // Sending JSON
            data: JSON.stringify({
                username: username,
                password: password
            }),
            success: function(response) {
                if (response.success) {
                    // Redirect after successful login
                    window.location.href = '../../admin/adminDashboard.jsp';
                } else {
                    // Show error message from response
                    showMessage(response.message, 'danger');
                }
            },
            error: function(xhr) {
                // Handle error response
                var errorMessage = xhr.responseJSON ? xhr.responseJSON.message : 'An unexpected error occurred.';
                showMessage('Invalid username or password', 'danger');
            }
        });
    });

    function showMessage(message, type) {
        var alertHtml = '<div class="alert alert-' + type + ' text-center" style="width: fit-content; margin: 0 auto;color: red;">' + message + '</div>';
        $('.card').prepend(alertHtml);
        setTimeout(function() {
            $('.alert').fadeOut('slow', function() {
                $(this).remove();
            });
        }, 3000);  // Automatically remove alert after 3 seconds
    }
});
