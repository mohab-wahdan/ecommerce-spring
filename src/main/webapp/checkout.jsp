 <%@ include file="header.jsp" %>


    <style>
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

    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                        <span class="order-history-title">Shopping Cart</span>
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
                        <h5>Billing detail</h5>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>First Name <span>*</span></p>
                                    <input type="text" value="${user.firstName}" readonly>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Last Name <span>*</span></p>
                                    <input type="text" value="${user.lastName}" readonly>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="checkout__form__input">
                                    <p>City <span>*</span></p>
                                    <input type="text" value="${user.address.city}" readonly>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Address <span>*</span></p>
                                    <input type="text" value="${user.address.street}" readonly>
                                </div>
                                <div class="checkout__form__input">
                                    <p>Postcode/Zip <span>*</span></p>
                                    <input type="text" value="${user.address.zip}" readonly>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Phone <span>*</span></p>
                                    <input type="text" value="${user.phoneNumber}" readonly>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="checkout__form__input">
                                    <p>Email <span>*</span></p>
                                    <input type="text" value="${user.email}" readonly>
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
                                        <c:forEach var="entry" items="${cart.items}">
                                            <li>${entry.value} x ${entry.key.productName} <span>$ ${entry.key.price}</span></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="checkout__order__total">
                                    <ul>
                                        <li>Subtotal <span>$  </span></li>
                                    </ul>
                                </div>

                                <button type="submit" class="site-btn">Place oder</button>
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
     $(document).on("click", ".site-btn",checkout);
     let totalQuantity = localStorage.getItem('totalQuantity') || 0;
     let totalPrice = localStorage.getItem('totalPrice') || 0;
});

function checkout(e){
    e.preventDefault();  // Prevent the form from submitting normally

    const customerId = 4;  // Set customer ID as needed
    const cartItemsUrl ='http://localhost:8083/cartItems/'+customerId;
    const checkoutUrl = 'http://localhost:8083/checkout/'+customerId;

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
                success: function (response) {
                    console.log("Order placed successfully:", response);
                    alert("Order placed successfully!");
                },
                error: function (xhr, status, error) {
                    console.error("Error placing order:", error);
                    alert("Failed to place order.");
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
