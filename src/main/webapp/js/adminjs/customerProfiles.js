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
    const username = document.getElementById('searchUsername').value;
    const email = document.getElementById('searchEmail').value;
    const id = document.getElementById('searchId').value;

    let queryString = '';
    if (username) queryString += 'username=' + encodeURIComponent(username) + '&';
    if (email) queryString += 'email=' + encodeURIComponent(email) + '&';
    if (id) queryString += 'id=' + encodeURIComponent(id) + '&';

    window.location.href = 'customerView?userName='+username+'&email='+email+'&Id='+id;
}
function resetFilters() {
    // Redirect to the servlet to refresh the customer list
    window.location.href = 'customerView';
}