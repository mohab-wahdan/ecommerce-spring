<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            background-color: #f9f9f9;
        }

        .breadcrumb__links {
            margin-top: 20px;
            font-size: 16px;
        }

        .breadcrumb__links a {
            color: #333;
            text-decoration: none;
        }

        .breadcrumb__links span {
            font-weight: bold;
            color: #000;
        }

        .page-title-inner {
            background: url('order/img/page-titlebg.png') no-repeat center center/cover;
            color: white;
            padding: 50px 0;
            text-align: center;
        }

        .page-title-inner h1 {
            font-size: 36px;
            text-transform: uppercase;
        }

        .track-lines .single-tracking-inner {
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            margin-bottom: 30px;
            transition: transform 0.3s ease;
        }

        .track-lines .single-tracking-inner:hover {
            transform: translateY(-10px);
        }

        .single-tracking-inner h5 {
            font-size: 18px;
            margin-top: 15px;
            font-weight: 600;
        }

        .single-tracking-inner img {
            max-width: 50px;
        }

        .order-details-wrap {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            margin-top: 40px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        .order-details-heading h5 {
            font-size: 22px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .order-items-section table {
            width: 100%;
            background: #fff;
            margin-top: 20px;
        }

        .order-items-section table thead th {
            background-color: #ca1515;
            color: #fff;
            padding: 10px;
        }

        .order-items-section table tbody tr td {
            padding: 10px;
            font-size: 16px;
            text-align: center;
        }

        .order-deliverd-date {
            margin-top: 40px;
            font-size: 20px;
        }

        .order-deliverd-date span {
            font-weight: bold;
            color: #ca1515;
        }

        .order-details-wrap .shipping-to-area h5, .billing-to-area h5 {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 10px;
        }
        .order-history-title {
            font-family: 'Montserrat', sans-serif; /* Use a nice modern font */
            font-weight: 700; /* Make the text bold */
            font-size: 32px; /* Larger font size */
            background-color: #bb1818 !important; /* A bold red color matching the theme */
            color: #f8f9fa !important; /* Light background for contrast */
            padding: 10px 20px; /* Add padding for spacing */
            border-radius: 50px; /* Rounded corners */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            display: inline-block; /* Keeps it inline but with block properties */
            text-transform: uppercase; /* Makes the text uppercase */
            letter-spacing: 2px; /* Adds spacing between letters */
        }
    </style>
</head>
<%@ include file="header.jsp" %>
<body>
    <div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="index.jsp"><i class="fa icon_profile"></i>Home</a>
                    <a href="orderHistory.jsp"><i class="fas fa-clipboard-list"></i> Order History</a>
                    <span class="order-history-title"><i class="fas fa-truck"></i> Order Tracking</span>
                </div>
            </div>
        </div>
    </div>
</div>

<section class="page-title-inner">
    <div class="container">
        <h1>Track Your Order</h1>
    </div>
</section>

<section class="pt-100 pb-100">
    <div class="container">
        <div class="row track-lines">

        </div>

        <div class="row">
            <div class="col-12 text-center">
                <div class="order-deliverd-date">
                    <p>Your Order Will Be Delivered on <span> </span></p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="order-details-wrap">
                    <div class="order-details-heading">
                        <h5>Order Details</h5>
                    </div>
                    <div class="order-address-details">
                        <div class="order-no">
                            <p>Order No: <span> </span></p>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="shipping-to-area">
                                    <h5>Shipping To:</h5>
                                    <p></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="order-items-section mt-5">
                    <h2>Order Items</h2>
                    <table class="table table-bordered table-striped mt-3">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price/Item</th>
                        </tr>
                        </thead>
                        <tbody id="orderItemsTableBody">
                            <!-- Order items will be populated here -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>



<script>
  $(document).ready(function () {
         var customerId =sessionStorage.getItem("id");
        const urlParams = new URLSearchParams(window.location.search);
        const orderId = urlParams.get('orderId');
         $.ajaxSetup({
            beforeSend: function(xhr) {
                var token = sessionStorage.getItem('jwt-token');
                if (token) {
                    xhr.setRequestHeader('Authorization', 'Bearer ' + token);
                }
            }
        });
        // Step 1: Retrieve order IDs
        $.ajax({
            url: '/orders/customer/'+customerId,
            method: "GET",
            success: function (orders) {
                // You can add logic here to display the orders, if necessary
                console.log("Retrieved orders:", orders);
                  if (orders.length > 0) {
                     const order = orders[0]; // Get the first order (adjust as needed)
                     const orderId = order.id;
                     const orderStatus = order.status;
                     const orderCreatedAt = new Date(order.createdAt); // Convert to Date object

                     // Add 2 days to orderCreatedAt
                     const deliveryDate = new Date(orderCreatedAt);
                     deliveryDate.setDate(deliveryDate.getDate() + 2);

                     // Format the delivery date (if needed)
                     const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
                     const formattedDeliveryDate = deliveryDate.toLocaleDateString(undefined, options);
                     const orderCreatedAt2 = orderCreatedAt.toLocaleString();

                     // Populate order status
                     if (orderStatus === 'CANCELLED') {
                         $('.track-lines').html('<div class="canceled-status">Your order has been cancelled.</div>');
                     } else {
                         $('.track-lines').html(`
                             <div class="col-md-3 col-sm-3">
                                 <div class="single-tracking-inner text-center">
                                     <img src="order/img/icons/torder.png" alt="Order Placed">
                                     <h5>Order Placed</h5>
                                     <p>`+orderCreatedAt2+`</p> <!-- Displaying order created date -->
                                 </div>
                             </div>
                             <div class="col-md-3 col-sm-3">
                                 <div class="single-tracking-inner text-center">
                                     <img src="order/img/icons/tpacked.png" alt="Packed">
                                     <h5>Packed</h5>
                                     <p>`+orderCreatedAt2+`</p>
                                 </div>
                             </div>
                             <div class="col-md-3 col-sm-3">
                                 <div class="single-tracking-inner text-center">
                                     <img src="order/img/icons/ttransit.png" alt="In Transit">
                                     <h5>In Transit</h5>
                                     <p>`+formattedDeliveryDate+`</p> <!-- Using the calculated delivery date -->
                                 </div>
                             </div>
                             <div class="col-md-3 col-sm-3">
                                 <div class="single-tracking-inner text-center">
                                     <img src="order/img/icons/tdeliverd.png" alt="Delivered">
                                     <h5>Delivered</h5>
                                     <p>`+formattedDeliveryDate+`</p> <!-- Same date for delivery -->
                                 </div>
                             </div>
                         `);
                     }

                     // Populate the order delivery date
                     $('.order-deliverd-date span').text(formattedDeliveryDate);

                     // Populate order details
                     $('.order-no span').text(orderId);
                     $('.shipping-to-area p').text(order.destination);
                     fetchOrderItems(orderId);
                 }
            },
            error: function (xhr, status, error) {
                console.error("Error fetching orders:", error);
                alert("Failed to retrieve orders.");
            }
        });

        // Step 2: Fetch order items for a specific order ID
        function fetchOrderItems(orderId) {
            $.ajax({
                url: '/order-items/orderId/'+orderId,
                method: "GET",
                success: function (orderItems) {
                    // Clear existing rows
                    $('#orderItemsTableBody').empty();

                    // Step 3: Populate the table with order items
                    orderItems.forEach(item => {
                        const row = `
                            <tr>
                                <td>`+item.productName+` </td>  <!-- Assuming item has a productName field -->
                                <td> `+item.quantity+` </td>
                                <td>$ `+item.price+` </td> <!-- Assuming item has a price field -->
                            </tr>
                        `;
                        $('#orderItemsTableBody').append(row);
                    });
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching order items:", error);
                    alert("Failed to retrieve order items.");
                }
            });
        }
    });

</script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
<%@ include file="footer.jsp" %>
</html>
