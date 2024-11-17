$(document).ready(function () {
    // Setup AJAX with JWT token
    $.ajaxSetup({
        beforeSend: function (xhr) {
            const token = sessionStorage.getItem('jwt-token');
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        }
    });

    // Load all orders on page load
    loadOrders();

    // Function to load all orders
    function loadOrders() {
        $.ajax({
            url: '/orders',
            method: 'GET',
            success: function (orders) {
                renderOrders(orders);
            },
            error: function (error) {
                console.error('Error loading orders:', error);
                $('#ordersTable tbody').html('<tr><td colspan="5">Error loading orders</td></tr>');
            }
        });
    }

    // Function to render orders in the table
    function renderOrders(orders) {
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
                        <td>${new Date(order.createdAt).toLocaleDateString()}</td>
                        <td>${order.destination}</td>
                        <td>
                            <button class="btn btn-primary" onclick="updateStatus(${order.id})">Update</button>
                            <button class="btn btn-secondary" onclick="viewOrderDetails(${order.id})">View Details</button>
                        </td>
                    </tr>
                    <tr id="order-items-${order.id}"  style="display: none;">
                        <td colspan="5">
                            <ul id="items-list-${order.id}"></ul>
                        </td>
                    </tr>`;
            });
            $('#ordersTable tbody').html(ordersHtml);
        } else {
            $('#ordersTable tbody').html('<tr><td colspan="5">No orders found</td></tr>');
        }
    }

    // Filter orders by status
    $('#filterBtn').on('click', function () {
        const status = $('#status').val();
        $.ajax({
            url: `/orders/status/${status}`,
            method: 'GET',
            success: function (orders) {
                renderOrders(orders);
            },
            error: function (error) {
                console.error('Error filtering orders:', error);
            }
        });
    });
});


function updateStatus(orderId) {
    const newStatus = document.getElementById(`status-${orderId}`).value;

    $.ajax({
        url: `/orders/${orderId}/status/${newStatus}`,
        type: 'PATCH', // Changed from 'POST' to 'PATCH'
        data: JSON.stringify({ orderId: orderId }), // Send data as JSON
        contentType: 'application/json', // Set the content type to JSON
        success: function(response) {
            showNotification("Order status updated successfully!", "success");
        },
        error: function(xhr, status, error) {
            showNotification("Error updating order status.", "danger");
        }
    });
}
function showNotification(message) {
    const notification = document.getElementById('notification-container');
    notification.textContent = message;
    notification.classList.add('show');
    setTimeout(() => notification.classList.remove('show'), 3000); // Hide after 3 seconds
}
function viewOrderDetails(orderId) {
    console.log(orderId);
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
