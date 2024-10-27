<link rel="stylesheet" href="css/registration.css">

<div class="container">
    <header>
        <img src="img/logo.png" width="98" height="31" alt="">
        <span class="form-title">Registration Form</span>
    </header>
    <div id="message-container"></div> <!-- Message container for alerts -->
    <form  id="registration-form">

            <div class="buttons">
                <button type="button" class="prevBtn" onclick="prevStep()">Previous</button>
                <button class="nextBtn" type="submit"  >

                    <i class="uil uil-navigator"></i>  Register
                </button>
            </div>
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/js/register.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        // Handle form submission
        $('#registration-form').on('submit', function(event) {
            event.preventDefault(); // Prevent default form submission

            // Gather form data
            var formData = {
                firstName: "John",
                lastName: "Doe",
                creditLimit: 5000.00,
                dateOfBirth: "1990-01-01",
                email: "j44@example.com11",
                phoneNumber: "1230",
                job: "Developer",
                address: {
                    street: "1233 Main St11",
                    city: "New Yo3rk11",
                    zip: "100031",
                    description: "Regular customer"
                },
                account: {
                    userName: "joh43",
                    password: "password1231"
                }
            };

            // Make AJAX request to register the user
            $.ajax({
                url: 'http://localhost:8083/customers', // Your API endpoint
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
