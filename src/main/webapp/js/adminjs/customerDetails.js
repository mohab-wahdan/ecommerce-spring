$(document).ready(function() {
    $.ajaxSetup({
        beforeSend: function(xhr) {
            var token = sessionStorage.getItem('jwt-token');
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        }
    });
    const customerId = new URLSearchParams(window.location.search).get('id');
    const customer = JSON.parse(sessionStorage.getItem('customerDetails'));
    const orders = JSON.parse(sessionStorage.getItem('customerOrders'));
    console.log("Customer Data:", customer); // Should display customer data in the console
    console.log("Orders Data:", orders); // Should display orders data in the console
    if (customer) {
        // Populate customer details
        $('.card-title').text(`${customer.firstName} ${customer.lastName}`);
        $('.card-text').eq(0).text(`Username: ${customer.userName}`);
        $('.card-text').eq(1).text(`Email: ${customer.email}`);
        $('.card-text').eq(2).text(`Phone: ${customer.phoneNumber}`);
        $('.card-text').eq(3).text(`Address: ${customer.street}, ${customer.city}, ${customer.zip}`);
        $('.card-text').eq(4).text(`Description: ${customer.description}`);
    }

    if (orders && orders.length > 0) {
        let ordersHtml = '';
        orders.forEach(order => {
            ordersHtml += `
                <tr>
                    <td>${order.id}</td>
                    <td>
                        <select id="status-${order.id}" class="form-control">
                            <option value="PENDING" ${order.status === 'PENDING' ? 'selected' : ''}>PENDING</option>
                            <option value="WAITING" ${order.status === 'WAITING' ? 'selected' : ''}>WAITING</option>
                            <option value="COMPLETED" ${order.status === 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                            <option value="CANCELLED" ${order.status === 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                        </select>
                    </td>
                    <td>${order.createdAt}</td>
                    <td>${order.destination}</td>
                    <td>
                        <button class="btn btn-primary" onclick="updateStatus(${order.id})">Update</button>
                        <button class="btn btn-secondary" onclick="viewOrderDetails(${order.id})">View Order Details</button>
                    </td>
                </tr>
                <tr id="order-items-${order.id}" style="display: none;">
                    <td colspan="5">
                        <ul id="items-list-${order.id}"></ul>
                    </td>
                </tr>`;
        });
        $('#ordersTable tbody').html(ordersHtml);
    } else {
        $('#ordersTable tbody').html('<tr><td colspan="5">No orders found for this customer.</td></tr>');
    }
});

function updateStatus(orderId) {
    const newStatus = document.getElementById(`status-${orderId}`).value;

    $.ajax({
        url: `/orders/${orderId}/status/${newStatus}`,
        type: 'PATCH', // Changed from 'POST' to 'PATCH'
        data: JSON.stringify({ orderId: orderId }), // Send data as JSON
        contentType: 'application/json', // Set the content type to JSON
        success: function(response) {
         },
        error: function(xhr, status, error) {
         }
    });
}

function viewOrderDetails(orderId) {
    const itemsBlock = document.getElementById(`order-items-${orderId}`);
    const itemsList = document.getElementById(`items-list-${orderId}`);

    if (itemsBlock.style.display === "none") {
        $.ajax({
            url: `/order-items/orderId/${orderId}`,
            type: 'GET',
            success: function(response) {
                itemsList.innerHTML = '';
                if (response.length > 0) {
                    response.forEach(function(item) {
                        const listItem = `<li>Product: ${item.productName} | Quantity: ${item.quantity} | Price: ${item.price}</li>`;
                        itemsList.innerHTML += listItem;
                    });
                } else {
                    itemsList.innerHTML = '<li>No items found for this order.</li>';
                }
                itemsBlock.style.display = "table-row";
            },
            error: function(xhr, status, error) {
             }
        });

    } else {
        itemsBlock.style.display = "none";
    }
}
