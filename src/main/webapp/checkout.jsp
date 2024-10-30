 <%@ include file="header.jsp" %>


    <style>
        .action-btn {
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
            margin-bottom: 15px; /* Add margin for space between buttons */

        }
        .checkout-title {
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
        #loadingOverlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5); /* Semi-transparent black */
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 9999; /* Ensure it’s above other elements */
        }

        .spinner {
            color: #fff;
            font-size: 1.5em;
        }

    </style>

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="index.jsp"><i class="fa fa-home"></i> Home</a>
                        <span class="checkout-title"><i class="fas fa-money"></i> Checkout</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <c:set var="cart" value="${sessionScope.cart}" />
    <c:set var="user" value="${sessionScope.user}" />
    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <form  class="checkout__form">
                <div class="row">
                    <div class="col-lg-8">
                        <div id="loadingOverlay" style="display: none;">
                            <div class="spinner">Please wait while placing order ...</div>
                        </div>
                        <h5>Billing detail</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>First Name <span>*</span></p>
                                    <input type="text" name="firstName" readonly>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Last Name <span>*</span></p>
                                    <input type="text" name="lastName" readonly>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>City <span>*</span></p>
                                    <input type="text" name="city" readonly>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Address <span>*</span></p>
                                    <input type="text" name="street" readonly>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Postcode/Zip <span>*</span></p>
                                    <input type="text" name="zip" readonly>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Phone <span>*</span></p>
                                    <input type="text" name="phoneNumber" readonly>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="text" name="email" readonly>
                                </div>
                            </div>
                            <div class="col-lg-12">

                            </div>
                        </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="checkout__order">
                                <h5>Your order</h5>
                                <div class="checkout__order__product">
                                    <ul>
                                        <li>
                                            <span class="top__text">Product</span>
                                            <span class="top__text__right">Total</span>
                                        </li>
                                        <ul id="listOfProd"></ul>
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                    <ul>
                                        <li>Subtotal <span id="subtotalValue">$  </span></li>
                                    </ul>
                                </div>
                                <button type="button" class="action-btn" id="payOnline">Pay online</button> <br>
                                <button type="button" class="action-btn" id="payCash">Pay Cash On Delivery</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- Checkout Section End -->

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->
<script>
$(document).ready(function () {
     populateCustomerValues();
     populateCart();
     $(document).on("click","#payCash",checkout);
     $(document).on("click","#payOnline",onlinePayment);
});
function onlinePayment(e){
      e.preventDefault();
      checkout2();
}
function checkout2(){
    const customerId = sessionStorage.getItem("id");
    const cartItemsUrl ='/cartItems/'+customerId;
    const checkoutUrl = '/checkout/'+customerId;

    // Fetch cart items
    $.ajax({
        url: cartItemsUrl,
        method: "GET",
        success: function (cartItems) {
            // Prepare request body
            const requestBody = cartItems.map(item => ({
                subProductId: item.subProductId,
                quantity: item.quantity
            }));

            // Send to checkout endpoint
            $.ajax({
                url: checkoutUrl,
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(requestBody),
                beforeSend: function () {
                    // Show loading overlay
                    $("#loadingOverlay").show();
                },
                success: function (response) {
                    console.log("Order placed successfully:", response);
                    alert("Order placed successfully!");
                    const totalPrice = sessionStorage.getItem("totalPrice");
                     if (totalPrice) {
                         const payUrl ='${pageContext.request.contextPath}/pay/'+totalPrice;
                         window.location.href = payUrl; // Redirect to the pay URL
                     } else {
                         alert("Total price is not set.");
                     }
                },
                error: function (xhr, status, error) {
                    console.error("Error placing order:", error);
                    alert("Failed to place order.");
                },
                complete: function () {
                    // Hide loading overlay
                    $("#loadingOverlay").hide();
                }
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching cart items:", error);
            alert("Failed to retrieve cart items.");
        }
    });
}
function populateCart(){
     const userId = sessionStorage.getItem("id");
     var subtotal=0;
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
                    success: function(item) {
                        // Process and display sub-product details
                       $('#listOfProd').append(`
                           <li>`+cartItem.quantity+` x `+item.productName+` <span>$ `+cartItem.quantity*item.price+`</span></li>
                       `);
                       subtotal+=(cartItem.quantity*item.price);
                       $('#subtotalValue').text(subtotal.toFixed(2));
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

function populateCustomerValues(){
    const customerId = sessionStorage.getItem("id");

    // AJAX request to fetch customer data
    $.ajax({
        url: '/customers/'+customerId,
        method: "GET",
        success: function(data) {
            // Populate fields with returned data
            $('input[name="firstName"]').val(data.firstName);
            $('input[name="lastName"]').val(data.lastName);
            $('input[name="city"]').val(data.address.city);
            $('input[name="street"]').val(data.address.street);
            $('input[name="zip"]').val(data.address.zip);
            $('input[name="phoneNumber"]').val(data.phoneNumber);
            $('input[name="email"]').val(data.email);
        },
        error: function(error) {
            console.log("Error fetching customer data:", error);
        }
    });
}
function checkout(e){
    e.preventDefault();  // Prevent the form from submitting normally

    const customerId = sessionStorage.getItem("id");
    const cartItemsUrl ='/cartItems/'+customerId;
    const checkoutUrl = '/checkout/'+customerId;

    // Fetch cart items
    $.ajax({
        url: cartItemsUrl,
        method: "GET",
        success: function (cartItems) {
            // Prepare request body
            const requestBody = cartItems.map(item => ({
                subProductId: item.subProductId,
                quantity: item.quantity
            }));

            // Send to checkout endpoint
            $.ajax({
                url: checkoutUrl,
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(requestBody),
                beforeSend: function () {
                    // Show loading overlay
                    $("#loadingOverlay").show();
                },
                success: function (response) {
                    console.log("Order placed successfully:", response);
                    window.location.href = '/shop.jsp';
                },
                error: function (xhr, status, error) {
                    console.error("Error placing order:", error);
                    alert("Failed to place order.");
                },
                complete: function () {
                    // Hide loading overlay
                    $("#loadingOverlay").hide();
                }
            });


        },
        error: function (xhr, status, error) {
            console.error("Error fetching cart items:", error);
            alert("Failed to retrieve cart items.");
        }
    });
    }



</script>
        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/main.js"></script>
    <%@ include file="footer.jsp" %>
