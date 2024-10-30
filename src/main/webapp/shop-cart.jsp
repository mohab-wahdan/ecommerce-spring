<%@ include file="header.jsp" %>

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
                        <a href="shop.jsp" class="continue-btn">Continue Shopping</a>
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
                        <a href="checkout.jsp" class="proceed-btn"><i class="fa fa-dollar-sign" id="checkoutBtn"></i> Proceed to checkout</a>
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
document.getElementById("checkoutBtn").addEventListener("click", function(event) {
        const userId = sessionStorage.getItem("id");
        if (!userId) {
            event.preventDefault();
        }
});
function updateCartTotals() {
    let subtotal = 0;
    let totalQuantity = 0;

    // Loop through each product row and calculate totals
    document.querySelectorAll('.quantity-input').forEach(input => {
        const quantity = parseInt(input.value);
        const pricePerUnit = parseFloat(input.getAttribute('data-price'));

        subtotal += quantity * pricePerUnit;
        totalQuantity += quantity;
    });

    // Update the subtotal and total quantity in the HTML
    document.querySelector('.totalQuantity').textContent = totalQuantity; // Update total quantity
    document.querySelector('.finalTotalPrice').textContent = '$ ' + subtotal.toFixed(2); // Update subtotal
    sessionStorage.setItem("totalPrice",subtotal);
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
            updateCartTotals();
        },
        error: function(xhr, status, error) {
            console.error("Error updating quantity:", error);
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
            }
        });
    }
}

// Function to get cart items by customer ID and then fetch sub-product details
function fetchCartItemsAndDetails() {
   const userId_ = sessionStorage.getItem("id");
   if(userId_){ loggedIn(); }
   else{
       const cart = JSON.parse(localStorage.getItem("cart"));

       // Check if the cart has items and iterate over it
       if (cart) {
           cart.forEach((item, index) => {
               var id=item.subProductId;
               $.ajax({
                   url: '/cartItems/subProduct/'+id,
                   type: 'GET',
                   success: function(subProductDetails) {
                       // Process and display sub-product details
                       $('#cartItemsTableBody').append(`
                           <tr data-product-id="`+subProductDetails.id+`"  class="cart-item-row">
                               <td>
                               <img src="`+subProductDetails.imageURL+`" style="width: 90px; height: 90px;" alt="">
                               <div class="cart__product__item__title">
                                   <h6> `+subProductDetails.productName+`</h6>
                               </div>
                               </td>
                               <td>$ `+subProductDetails.price+`</td>
                               <td>
                                   <input type="text" value="`+item.quantity+`" data-price="`+subProductDetails.price+`" class="quantity-input" >
                               </td>
                               <td class="total-price">$ ` + (subProductDetails.price * item.quantity).toFixed(2) + `</td>
                               <td><span class="icon_close"></span></td>
                           </tr>
                       `);
                        updateCartTotals();
                   },
                   error: function(error) {
                       console.error(`Error fetching details for subProduct ID ${subProductId}:`, error);
                   }
               });
           });
       } else {
           console.log("The cart is empty.");
       }
   }
}
function cartFromLocal(){
    const cart = JSON.parse(localStorage.getItem("cart"));
    if (cart && cart.length > 0) {
        cart.forEach((item, index) => {
            const customerId = sessionStorage.getItem("id");
            const subProductId = item.subProductId; // Accessing item properties correctly
            const quantity = item.quantity; // Accessing item properties correctly

            if (customerId) {
                const requestData = {
                    customerId: customerId,
                    subProductId: subProductId,
                    quantity: quantity
                };

                // Send the AJAX POST request
                $.ajax({
                    url: "/cartItems",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(requestData), // Send the request data as JSON
                    success: function (response) {
                        localStorage.clear();
                    },
                    error: function (xhr, status, error) {
                    }
                });
            } else {
            }
        });
    }
    // else {
    //     const cartItemsTableBody = document.getElementById('cartItemsTableBody');
    //     cartItemsTableBody.innerText="Your cart is empty!";
    // }

}

function loggedIn(){
    cartFromLocal();
    const userId = sessionStorage.getItem("id");
     $.ajax({
        url: '/cartItems/'+userId,
        type: 'GET',
        success: function(cartItems) {
            if (cartItems.length === 0) {
                $('#cartItemsTableBody').html('<tr><td colspan="5">Your cart is empty.</td></tr>');
                return; // Exit the function if there are no items
            }
            cartItems.forEach(function(cartItem) {
                let subProductId = cartItem.subProductId; // Assuming cartItems contain subProductId

                $.ajax({
                    url: '/cartItems/subProduct/'+subProductId,
                    type: 'GET',
                    success: function(subProductDetails) {
                        // Process and display sub-product details
                        $('#cartItemsTableBody').append(`
                            <tr data-product-id="`+subProductDetails.id+`"  class="cart-item-row">
                                <td>
                                <img src="`+subProductDetails.imageURL+`" style="width: 90px; height: 90px;" alt="">
                                <div class="cart__product__item__title">
                                    <h6> `+subProductDetails.productName+`</h6>
                                </div>
                                </td>
                                <td>$ `+subProductDetails.price+`</td>
                                <td>
                                    <input type="text" value="`+cartItem.quantity+`" data-price="`+subProductDetails.price+`" class="quantity-input" >
                                </td>
                                <td class="total-price">$ ` + (subProductDetails.price * cartItem.quantity).toFixed(2) + `</td>
                                <td><span class="icon_close"></span></td>
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
        .continue-btn {
            font-family: 'Montserrat', sans-serif; /* Use a nice modern font */
            font-weight: 80; /* Make the text bold */
            font-size: 15px; /* Larger font size */
            background-color: #bb1818 !important; /* A bold red color matching the theme */
            color: #f8f9fa !important; /* Light background for contrast */
            padding: 10px 20px; /* Add padding for spacing */
            border-radius: 20px; /* Rounded corners */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            display: inline-block; /* Keeps it inline but with block properties */
            text-transform: uppercase; /* Makes the text uppercase */
            letter-spacing: 2px; /* Adds spacing between letters */

        }
        .proceed-btn {
            font-family: 'Montserrat', sans-serif; /* Use a nice modern font */
            font-weight: 80; /* Make the text bold */
            font-size: 15px; /* Larger font size */
            background-color: #bb1818 !important; /* A bold red color matching the theme */
            color: #f8f9fa !important; /* Light background for contrast */
            padding: 10px 20px; /* Add padding for spacing */
            border-radius: 20px; /* Rounded corners */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            display: inline-block; /* Keeps it inline but with block properties */
            text-transform: uppercase; /* Makes the text uppercase */
            letter-spacing: 2px; /* Adds spacing between letters */
            width: 300px; /* Set fixed width to align all labels */

        }
    </style>

    <%@ include file="footer.jsp" %>