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

        /*end of Notification Style*/
        .pro-qtyy {
            display: flex;
            align-items: center;
            justify-content: center;
            width: fit-content;
        }

        .qty-btn {
            background-color: #f1f1f1;
            border: none;
            border-radius: 50%; /* Makes the buttons circular */
            width: 40px; /* Equal width and height for a perfect circle */
            height: 40px;
            font-size: 18px; /* Adjust font size to fit inside the circle */
            font-weight: bold;
            color: black;
            text-align: center;
            line-height: 40px; /* Centers the text vertically */
            cursor: pointer;
            margin: 0 5px;
            transition: background-color 0.3s ease; /* Smooth hover effect */
        }

        .qty-btn:focus {
            outline: none;
        }

        .qty-btn:hover {
            background-color: #e0e0e0; /* Change background on hover */
        }

        .quantity-input {
            width: 50px;
            height: 40px; /* Match the button height for symmetry */
            text-align: center;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .shopping-cart-title {
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
<!--Notification Section -->
    <jsp:include page="common/WarningNotification.jsp"/>
    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="index.jsp"><i class="fa fa-home"></i> Home</a>
                        <span class="shopping-cart-title"><i class="fas fa-shopping-cart"></i> Shopping Cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <c:set var="cart" value="${sessionScope.cart}" />
    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="cartItemsTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                        <a href="shop.jsp" class="primary-btn">Continue Shopping</a>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content" hidden="hidden">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        <ul>
                            <li>TotalQuantity <span class="totalQuantity"> </span></li>
                            <li>Subtotal <span class="finalTotalPrice">  </span></li>
                        </ul>
                        <a href="checkout.jsp" class="primary-btn"><i class="fa fa-dollar-sign"></i> Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->
<script>
$(document).ready(function() {
    fetchCartItemsAndDetails();
    $(document).on('click', '.icon_close',deleteItem);
    $(document).on('change', '.quantity-input',updateQuantity);
});

function updateCartTotals() {
    let totalQuantity = 0;
    let totalPrice = 0;

    $.each(cartItems, function(index, row) {
        const quantity = parseInt($(row).find(".quantity-input").val()) || 0;
        const price = parseFloat($(row).find(".total-price").text().replace("$", "")) || 0;

        totalQuantity += quantity;
        totalPrice += price;
    });

    // Update the Cart total section
    $(".totalQuantity").text(totalQuantity); // Total Quantity
    $(".finalTotalPrice").text("$"+totalPrice); // Total Price
    // Store the updated totals in localStorage
    localStorage.setItem('totalQuantity', totalQuantity);
    localStorage.setItem('totalPrice', totalPrice);
}

//Function to update quantity
function updateQuantity(){
    const $row = $(this).closest("tr");  // Get the row of the changed quantity input
    const subProductId = $row.data("product-id"); // Extract subProductId from data attribute
    const customerId = sessionStorage.getItem("id");
    const newQuantity = $(this).val();  // Get the new quantity value
    const price = parseFloat($row.find("td:nth-child(2)").text().replace('$', ''));
    const total = (price * newQuantity).toFixed(2); // Calculate new total price

    // Update the displayed total price in the table
    $row.find(".total-price").text('$' +total);

    // Update quantity on the server
    const updateUrl = '/cartItems/'+ customerId+'/'+subProductId;
    const requestBody = {
        quantity: newQuantity
    };

    // Send the PUT request to update quantity
    $.ajax({
        url: updateUrl,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(requestBody),
        success: function(response) {
            //alert("Quantity updated successfully!");
        },
        error: function(xhr, status, error) {
            console.error("Error updating quantity:", error);
            alert("Failed to update quantity.");
        }
    });
}

// Function to delete cart items
function deleteItem() {
    // Prevent default action if necessary
    const $row = $(this).closest('tr');  // Get the row of the clicked delete icon
    const subProductId = $row.data('product-id'); // Extract subProductId from data attribute
    const customerId =sessionStorage.getItem("id");

    // DELETE endpoint URL
    const deleteUrl = '/cartItems/'+ customerId+'/'+subProductId;

    // Confirm delete action with the user (optional)
    if (confirm("Are you sure you want to remove this item from the cart?")) {
        $.ajax({
            url: deleteUrl,
            method: "DELETE",
            success: function(response) {
                // Remove the row from the table
                $row.remove();
            },
            error: function(xhr, status, error) {
                console.error("Error deleting item:", error);
                alert("Failed to remove item from the cart.");
            }
        });
    }
}

// Function to get cart items by customer ID and then fetch sub-product details
function fetchCartItemsAndDetails() {
    const userId = sessionStorage.getItem("id"); ;
     $.ajax({
        url: '/cartItems/'+userId,
        type: 'GET',
        success: function(cartItems) {
            // Step 2: For each sub-product ID, fetch details
            cartItems.forEach(function(cartItem) {
                let subProductId = cartItem.subProductId; // Assuming cartItems contain subProductId

                $.ajax({
                    url: '/cartItems/subProduct/'+subProductId,
                    type: 'GET',
                    success: function(subProductDetails) {
                        // Process and display sub-product details
                        $('#cartItemsTableBody').append(`
                            <tr data-product-id="`+subProductDetails.id+`" class="cart-item-row">
                                <td>
                                <img src="`+subProductDetails.imageURL+`" style="width: 90px; height: 90px;" alt="">
                                <div class="cart__product__item__title">
                                    <h6> `+subProductDetails.description+`</h6>
                                </div>
                                </td>
                                <td>$ `+subProductDetails.price+`</td>
                                <td>
                                    <input type="text" value="`+cartItem.quantity+`" class="quantity-input" >
                                </td>
                                <td class="total-price">$ ` + (subProductDetails.price * cartItem.quantity).toFixed(2) + `</td>                                <td><span class="icon_close"></span></td>
                            </tr>
                        `);
                         updateCartTotals();
                    },
                    error: function(error) {
                        console.error(`Error fetching details for subProduct ID ${subProductId}:`, error);
                    }
                });
            });
        },
        error: function(error) {
            console.error(`Error fetching cart items for customer ID ${customerId}:`, error);
        }
    });
}
</script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
<%@ include file="footer.jsp" %>
</html>