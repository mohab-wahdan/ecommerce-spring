<%@ include file="header.jsp" %>
 <style>
        .notification {
            position: fixed;
            top: 0;
            width: 100%;
            background-color: #2df800; /* Red background for error */
            color: white;
            text-align: center;
            padding: 15px;
            z-index: 1000; /* Make sure it appears on top */
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 16px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease;
        }

        .notification.hidden {
            transform: translateY(-100%); /* Slide out of view */
        }

        .close-btn {
            margin-left: 20px;
            background: none;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
        }
        .pro-qtyy {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 120px;
            border: 1px solid #ddd;
            border-radius: 30px;
            overflow: hidden;
            background-color: #f9f9f9;
        }

        .pro-qtyy input {
            width: 50px;
            text-align: center;
            border: none;
            font-size: 16px;
            background-color: transparent;
            color: #333;
            font-weight: bold;
        }

        .qtybtn {
            background-color: #fff;
            border: none;
            cursor: pointer;
            padding: 8px 15px;
            font-size: 18px;
            font-weight: bold;
            user-select: none;
            transition: all 0.3s ease;
            color: #666;
        }

        .qtybtn:hover {
            background-color: #007bff;
            color: #fff;
        }

        .qtybtn:active {
            transform: scale(0.95);
        }

        .qtybtn.dec {
            border-radius: 50px 0 0 50px;
        }

        .qtybtn.inc {
            border-radius: 0 50px 50px 0;
        }


    </style>


    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <div id="notification" class="notification hidden">
        <span id="notification-message"></span>
        <button id="close-notification" class="close-btn">&times;</button>
    </div>


    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">

            <div id="productDetailsContainer"></div>


        </div>
    </section>
    <!-- Product Details Section End -->



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
<script>
$(document).ready(function() {
    const urlParams = new URLSearchParams(window.location.search);
    const subProductId = urlParams.get('product.id');

    // Fetch product details from the API
    $.ajax({
        url: 'http://localhost:8083/cartItems/subProduct/'+ subProductId ,
        type: 'GET',
        success: function(product) {
            // Populate the HTML with product details
            const productHtml = `
                <div class="row">
                    <div class="col-lg-6">
                        <div class="product__details__pic">
                            <div class="product__details__pic__left product__thumb nice-scroll">
                                <a class="pt" href="#product-2">
                                    <img src="`+ product.imageURL +`" alt="">
                                </a>
                            </div>
                            <div class="product__details__slider__content">
                                <div class="">
                                    <img data-hash="product-1" class="product__big__img" src="`+ product.imageURL +`" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="product__details__text">
                            <h3>`+ product.description +`</h3>
                            <div class="product__details__price">$ `+ product.price+` </div>
                            <p>`+ product.description +`</p>
                            <div class="product__details__button">
                                <div class="quantity">
                                    <span>Quantity:</span>
                                    <div class="pro-qtyy">
                                        <button type="button" class="qtybtn dec">-</button>
                                        <input type="text" id="quantity" value="1" min="1" max="200" readonly>
                                        <button type="button" class="qtybtn inc">+</button>
                                    </div>
                                </div>
                                <a class="cart-btn" onclick="addToCart(${product.id}, '${product.description}', '${product.imageURL}', ${product.price}, ${product.stock})"><span class="icon_bag_alt"></span> Add to cart</a>
                            </div>
                            <div class="product__details__widget">
                                <ul>
                                    <li>
                                        <span>Availability:</span>
                                        <div class="stock__checkbox">
                                            `+ product.stock +` In Stock
                                        </div>
                                    </li>
                                    <li>
                                        <span>Available Color:</span>
                                        <div class="size__btn">
                                            <label for="color-`+ product.color +`" class="active">
                                                <input type="radio" id="color-`+ product.color +`">
                                                `+ product.color +`
                                            </label>
                                        </div>
                                    </li>
                                    <li>
                                        <span>Available size:</span>
                                        <div class="size__btn">
                                            <label for="size-`+ product.size +`" class="active">
                                                <input type="radio" id="size-`+ product.size +`">
                                               `+ product.size +`
                                            </label>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="product__details__tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Description</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                    <h6>Description</h6>
                                    <p>`+ product.description +`</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;

            // Append the populated HTML to a container in your webpage
            $('#productDetailsContainer').html(productHtml); // Ensure you have a container with this ID
            $('.pro-qtyy .inc').click(function () {
                let quantityInput = $('#quantity');
                let currentQuantity = parseInt(quantityInput.val());
                let maxStock = parseInt(quantityInput.attr('max'));

                // Increase the quantity if it's less than max stock
                if (currentQuantity < maxStock) {
                    quantityInput.val(currentQuantity + 1);
                }
            });

            $('.pro-qtyy .dec').click(function () {
                let quantityInput = $('#quantity');
                let currentQuantity = parseInt(quantityInput.val());
                let minQuantity = parseInt(quantityInput.attr('min'));

                // Decrease the quantity if it's more than min quantity
                if (currentQuantity > minQuantity) {
                    quantityInput.val(currentQuantity - 1);
                }
            });
        },
        error: function(error) {
            console.error("Error fetching product details:", error);
            // Optionally, show an error message to the user
        }
    });
});

    </script>

 <%@ include file="footer.jsp" %>