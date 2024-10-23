<%@ include file="header.jsp" %>
<jsp:include page="common/VNotification.jsp"/>
<jsp:include page="common/WNotification.jsp"/>

<!-- Shop Section Begin -->
<section class="shop spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="shop__sidebar">
                    <!-- Filter Form -->
                    <form id="filter-form">
                        <!-- Displaying Searched Product Name -->
                        <c:if test="${not empty param.searchkeyword}">
                            <div class="section-title">
                                <h5>You searched for: ${param.searchkeyword} <span class="icon_search search-switch"></span></h5>
                            </div>
                        <!-- Hidden input field for searchkeyword if it exists -->
                            <input type="hidden" name="searchkeyword" value="${fn:escapeXml(param.searchkeyword)}" />
                        </c:if>
                        <!-- Price Filter -->
                        <div class="sidebar__filter">
                            <div class="section-title"><h4>Shop by price</h4>
                            </div>
                            <div class="filter-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"></div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <p>Price:</p>
                                        <input type="text" name="minPrice" id="minamount" value="${param.minPrice != null ? param.minPrice : 20}" />
                                        <input type="text" name="maxPrice" id="maxamount" value="${param.maxPrice != null ? param.maxPrice : 1500}" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Category Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by category</h4>
                            </div>
                            <div class="size__list">
                                <c:forEach var="category" items="${categories}">
                                    <label for="${category.name}">
                                            ${category.name}
                                        <input type="radio" name="category" id="${category.name}" value="${category.name}" ${param.category == category.name ? 'checked' : ''}>
                                        <span class="checkmark"></span>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- Gender Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by Gender</h4>
                            </div>
                            <div class="size__list">
                                <label for="male">
                                    Male
                                    <input type="radio" name="gender" id="male" value="male" ${param.gender == 'male' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="female">
                                    Female
                                    <input type="radio" name="gender" id="female" value="female" ${param.gender == 'female' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="unisex">
                                    Unisex
                                    <input type="radio" name="gender" id="unisex" value="unisex" ${param.gender == 'unisex' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>

                            </div>
                        </div>
                        <!-- Size Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list">
                                <label for="small">
                                    Small
                                    <input type="radio" name="size" id="small" value="small" ${param.size == 'small' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="medium">
                                    Medium
                                    <input type="radio" name="size" id="medium" value="medium" ${param.size == 'medium' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="large">
                                    Large
                                    <input type="radio" name="size" id="large" value="large" ${param.size == 'large' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xlarge">
                                    XLarge
                                    <input type="radio" name="size" id="xlarge" value="xlarge" ${param.size == 'xlarge' ? 'checked' : ''}>
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>

                        <!-- Color Filter -->
                        <div class="sidebar__color mb-4">
                            <div class="section-title">
                                <h4>Shop by color</h4>
                            </div>
                            <div class="color__checkbox">
                                <label for="red">
                                    <input type="radio" name="color" id="red" value="red" ${param.color == 'red' ? 'checked' : ''} />
                                    <span class="checkmark red-bg"></span>
                                </label>
                                <label for="black">
                                    <input type="radio" name="color" id="black" value="black" ${param.color == 'black' ? 'checked' : ''} />
                                    <span class="checkmark black-bg"></span>
                                </label>
                                <label for="white">
                                    <input type="radio" name="color" id="white" value="white" ${param.color == 'white' ? 'checked' : ''} />
                                    <span class="checkmark white-bg"></span>
                                </label>
                                <label for="blue">
                                    <input type="radio" name="color" id="blue" value="blue" ${param.color == 'blue' ? 'checked' : ''} />
                                    <span class="checkmark blue-bg"></span>
                                </label>
                                <label for="green">
                                    <input type="radio" name="color" id="green" value="green" ${param.color == 'green' ? 'checked' : ''} />
                                    <span class="checkmark green-bg"></span>
                                </label>
                                <label for="orange">
                                    <input type="radio" name="color" id="orange" value="orange" ${param.color == 'orange' ? 'checked' : ''} />
                                    <span class="checkmark orange-bg"></span>
                                </label>
                                <label for="violet">
                                    <input type="radio" name="color" id="violet" value="violet" ${param.color == 'violet' ? 'checked' : ''} />
                                    <span class="checkmark violet-bg"></span>
                                </label>
                                <label for="yellow">
                                    <input type="radio" name="color" id="yellow" value="yellow" ${param.color == 'yellow' ? 'checked' : ''} />
                                    <span class="checkmark yellow-bg"></span>
                                </label>
                                <label for="brown">
                                    <input type="radio" name="color" id="brown" value="brown" ${param.color == 'brown' ? 'checked' : ''} />
                                    <span class="checkmark brown-bg"></span>
                                </label>
                                <label for="pink">
                                    <input type="radio" name="color" id="pink" value="pink" ${param.color == 'pink' ? 'checked' : ''} />
                                    <span class="checkmark pink-bg"></span>
                                </label>
                                <label for="grey">
                                    <input type="radio" name="color" id="grey" value="grey" ${param.color == 'grey' ? 'checked' : ''} />
                                    <span class="checkmark grey-bg"></span>
                                </label>
                                <label for="navy">
                                    <input type="radio" name="color" id="navy" value="navy" ${param.color == 'navy' ? 'checked' : ''} />
                                    <span class="checkmark navy-bg"></span>
                                </label>
                                <label for="mint">
                                    <input type="radio" name="color" id="mint" value="mint" ${param.color == 'mint' ? 'checked' : ''} />
                                    <span class="checkmark mint-bg"></span>
                                </label>
                                <label for="sand">
                                    <input type="radio" name="color" id="sand" value="sand" ${param.color == 'sand' ? 'checked' : ''} />
                                    <span class="checkmark sand-bg"></span>
                                </label>
                            </div>
                        </div>

                        <div class="reset-btn-container">
                            <button type="submit" class="btn btn-outline-danger w-auto filter-btn">
                                <i class="fas fa-filter"></i> Apply Filters
                            </button>

                       <!-- Reset button MS7TOOOOOOOOOOOOOO-->

                        </div>
                    </form>
                </div>
            </div>


            <div id="product-list"></div>


            <div class="col-lg-9 col-md-9">
                <div class="product-list-wrapper">
                    <div class="row" id="product-list">
                        <!-- Products will be updated here -->
                        <c:forEach var="subProduct" items="${subProducts}">
                            <div class="col-lg-4 col-md-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" data-setbg="${subProduct.imageURL}">
                                        <c:if test="${subProduct.isNewArrival}">
                                            <div class="label new">New</div>
                                        </c:if>
                                        <ul class="product__hover">
                                            <li><a href="${subProduct.imageURL}" class="image-popup"><span class="arrow_expand"></span></a></li>
                                            <li><a class="buttonAddToCart" data-id="${subProduct.id}" data-name="${subProduct.productName}" data-price="${subProduct.price}" data-image="${subProduct.imageURL}" data-stock="${subProduct.stock}"><span class="icon_bag_alt"></span></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__item__text">
                                        <h6><a href="/product-details?product=${subProduct.id}" class="product-detail-button">${subProduct.productName}</a></h6>
                                        <div class="product__price">$ ${subProduct.price}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
$(document).ready(function () {
    fetchAllProducts();
    $('#filter-form').on('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission

        // Manually gather form data
        var filterData = {
           "color": "BLACK",
             "size": "MEDIUM",
             "maxPrice": 200,
             "minPrice": 50,
             "searchkeyword": "shirt",
             "gender": "MALE",
             "category": "shirts",
             "page": 1
        };

        // Make the AJAX request
        $.ajax({
            url: 'http://localhost:8083/subProducts/filter',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(filterData),
            success: function (data) {
                // Handle success: render products
                console.log(data);
                renderProducts(data); // Define this function to display products
            },
            error: function (xhr, status, error) {
                // Handle error
                console.error(error);
            }
        });
    });
});

function fetchAllProducts() {
    $.ajax({
        url: 'http://localhost:8083/subProducts', // Assuming this endpoint returns all products
        type: 'GET',
        success: function (data) {
            // Render all products
            renderProducts(data);
        },
        error: function (xhr, status, error) {
            // Handle error
            console.error(error);
        }
    });
}

function renderProducts(products) {
    // Clear existing products and render new ones
    $('#product-list').empty(); // Assuming you have a div to display products
    products.forEach(function (product) {
        $('#product-list').append('<div>' + product.description + ' - ' + product.price + '</div>');
    });
}
</script>









<script>
    var user = '<c:out value="${sessionScope.user}" escapeXml="true" />';

    $(document).ready(function () {
        // Event listener for all Add to Cart buttons
        $(".buttonAddToCart").click(function (e) {
            e.preventDefault(); // Prevent the default action of the anchor tag

            // Get product details from data attributes
            const productId = $(this).data("id");
            const productName = $(this).data("name");
            const productPrice = $(this).data("price");
            const productImage = $(this).data("image");
            const productStock = $(this).data("stock");

            // Create an object to send to the server
            const productData = {
                id: productId,
                productName: productName,
                price: productPrice,
                imageURL: productImage,
                stock: productStock,
                quantity: 1 // Default to 1, or you can let the user input the quantity
            };

            // Send product details via Ajax to the backend (Servlet)
            $.ajax({
                url: '/filterProducts', // URL of the servlet that handles adding to cart
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(productData),
                success: function (response) {
                    $('#notification')
                        .removeClass('alert-danger')
                        .addClass('alert-success')
                        .text(response.message)
                        .fadeIn().delay(3000).fadeOut();
                    // Optionally, update the cart UI with the updated cart count
                    $('.icon_bag_alt').siblings('.tip').text(response.cartItemCount);

                        saveCart();

                        VshowNotification("Product Added To Cart");

                    // Optionally, update the cart UI or display cart details
                    // Example: $('#cart-count').text(response.cartItemCount);
                },
                error: function (xhr, status, error) {
                    $('#notification')
                        .removeClass('alert-success')
                        .addClass('alert-danger')
                        .text('Error adding product to cart. Please try again.')
                        .fadeIn().delay(3000).fadeOut();
                    WshowNotification("Error adding product to cart. Please try again.");
                }
            });
        });
    });

    function saveCart() {
        // Send an AJAX request to get the CartService from the session
        $.ajax({
            url: "/cartlocal",
            type: "GET",
            success: function (response) {
                // Save the entire CartService object to localStorage
                localStorage.setItem("cartService", JSON.stringify(response.cart));
                console.log("CartService successfully saved to localStorage.");
            },
            error: function (xhr, status, error) {
                console.error("Error saving CartService:", error);
            }
        });
    }

</script>
<%@ include file="footer.jsp" %>
