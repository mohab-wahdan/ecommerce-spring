$(document).ready(function() {
    // Fetch customer data when the page loads
    $.ajax({
        url: "http://localhost:8083/customers", // Your API endpoint
        method: "GET",
        success: function(customers) {
            displayCustomers(customers);
        },
        error: function() {
            $("#customerList").html("<p class='text-danger'>Failed to load customers.</p>");
        }
    });
});

function displayCustomers(customers) {
    let customerHtml = "";
    if (customers.length === 0) {
        customerHtml = "<p>No customers found.</p>";
    } else {
        customers.forEach(function(customer) {
            customerHtml += `
            <div class="col-md-4 customer-list">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">${customer.firstName} ${customer.lastName}</h5>
                        <p class="card-text">Username: ${customer.userName}</p>
                        <p class="card-text">Email: ${customer.email}</p>
                        <p class="card-text">Phone: ${customer.phoneNumber}</p>
                        <p class="card-text">id: ${customer.id}</p>
                        <button class="btn btn-black" onclick="viewCustomerDetails('${customer.id}')">
                            <i class="fas fa-eye"></i> View Details</a>                   
                    </div>
                </div>
            </div>
            `;
        });
    }
    $("#customerList").html(customerHtml);
}
function loadAllCustomers() {
    $.ajax({
        url: "http://localhost:8083/customers", // Your API endpoint
        method: "GET",
        success: function(customers) {
            displayCustomers(customers);
        },
        error: function() {
            $("#customerList").html("<p class='text-danger'>Failed to load customers.</p>");
        }
    });
}

function viewCustomerDetails(customerId) {
    // Fetch customer details
    $.get(`/customers/customerId/${customerId}`, function(customer) {
        // Fetch orders related to the customer
        $.get(`/orders/customer/${customerId}`, function(orders) {

            // Store customer and orders in sessionStorage (or use localStorage or another method as per your needs)
            sessionStorage.setItem('customerDetails', JSON.stringify(customer));
            sessionStorage.setItem('customerOrders', JSON.stringify(orders));

            // Redirect to customerDetails.jsp
            window.location.href = `/admin/customerDetails.jsp?id=${customerId}`;
        });
    });
}
function searchCustomers() {
    const username = document.getElementById('searchUsername').value.trim(); // Get the username

    if (username) { // Ensure username is not empty
        $.ajax({
            url: `http://localhost:8083/customers/customerUsername/${username}`, // Make sure this matches your API endpoint
            method: "GET",
            success: function(customer) {
                if (customer) {
                    displayCustomers([customer]); // Pass the customer as an array to display
                } else {
                    $("#customerList").html("<p class='text-danger'>No customer found.</p>");
                }
            },
            error: function() {
                $("#customerList").html("<p class='text-danger'>Failed to load customer.</p>");
            }
        });
    } else {
        // Optionally reload all customers if the search field is empty
        loadAllCustomers();
    }
}

function resetFilters() {
    // Redirect to the servlet to refresh the customer list
    window.location.href = '/admin/customerProfiles.jsp';
}