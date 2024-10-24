<%@ include file="header.jsp" %>

<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="userProfile.jsp"><i class="fa icon_profile"></i> User Profile</a>
                    <span class="order-history-title">Update Profile</span>
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
                <div class="form-group">
                    <label for="userName">Username:</label>
                    <input type="text" class="form-control" id="userName" name="userName" onblur="checkUserName();" value="${sessionScope.user.userName}">
                    <span class="error-message" id="usernameerror"></span>
                </div>
                <div class="card-text">
                    <label for="firstName">First Name:</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${sessionScope.user.firstName}">
                </div>
                <div class="card-text">
                    <label for="lastName">Last Name:</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${sessionScope.user.lastName}">
                </div>

                <div class="card-text">
                    <label for="creditLimit">Credit Limit:</label>
                    <input type="number" class="form-control" id="creditLimit" name="creditLimit" step="0.01" value="${sessionScope.user.creditLimit}" onblur="checkCreditLimit();">
                    <span class="error-message" id="crediterror"></span>
                </div>

                <div class="card-text">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" id="email" name="email"  onblur="checkEmail();" value="${sessionScope.user.email}">
                    <span class="error-message" id="emailerror"></span>
                </div>
                <div class="card-text">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" onblur="checkPhoneNumber();" value="${sessionScope.user.phoneNumber}">
                    <span class="error-message" id="phoneerror"></span>
                </div>
                <div class="card-text">
                    <label for="job">Job:</label>
                    <input type="text" class="form-control" id="job" name="job" value="${sessionScope.user.job}">
                </div>
                <div class="input-field">
                    <label for="city">City:</label>
                    <select id="city" name="city" class="form-control" value="${sessionScope.user.address.city}">
                    </select>
                </div>
                <div class="card-text">
                    <label for="street">Street:</label>
                    <input type="text" class="form-control" id="street" name="street" value="${sessionScope.user.address.street}">
                </div>
                <div class="card-text">
                    <label for="zip">Zip:</label>
                    <input type="text" class="form-control" id="zip" name="zip" value="${sessionScope.user.address.zip}">
                </div>
                <div class="card-text">
                    <label for="description">Description:</label>
                    <input type="text" class="form-control" id="description" name="description" value="${sessionScope.user.address.description}">
                </div>
            </div>
        </div>
        <br>
        <button id="updateBtn" type="button" class="site-btn" onclick="submitForm()">
            <i class="fas fa-edit"></i> Update profile
        </button>
    </form>

    <p data-userid="${sessionScope.user.id}" id="userIdSession" hidden="hidden"></p>
</div>

<script>
    function submitForm() {
        // Get the user ID from the hidden element
        const userId = document.getElementById('userIdSession').getAttribute('data-userid');

        // Collect form data
        const formData = {
            userName: document.getElementById('userName').value,
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            creditLimit: document.getElementById('creditLimit').value,
            email: document.getElementById('email').value,
            phoneNumber: document.getElementById('phoneNumber').value,
            job: document.getElementById('job').value,
            street: document.getElementById('street').value,
            zip: document.getElementById('zip').value,
            description: document.getElementById('description').value,
            city: document.getElementById('city').value
        };

        // AJAX POST request to update customer details by user ID
        $.ajax({
            url: '${pageContext.request.contextPath}/user/' + userId,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                // Show success message
                $('#alertPlaceholder').html('<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                    '<i class="fas fa-check-circle"></i> Profile updated successfully!' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button></div>');
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
</script>



</div>
<script>
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

    <style>
        .dropdown-checkbox {
            position: relative;
        }
        .dropdown-menu-checkbox {
            display: none;
            position: absolute;
            z-index: 1000;
            width: 100%;
            background-color: #fff;
            box-shadow: 0 0.25rem 0.75rem rgba(0, 0, 0, 0.1);
            padding: 10px;
            max-height: 200px;
            overflow-y: auto;
        }
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
        .form-group {
            margin-bottom: 15px;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
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
