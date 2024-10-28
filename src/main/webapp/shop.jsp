<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="favicon.ico">
    <title>CHCILY</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/shop.css" type="text/css">


</head>

<body>
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

                        <!-- Price Filter -->
                        <div class="sidebar__filter">
                            <div class="section-title"><h4>Shop by price</h4>
                            </div>
                            <div class="filter-range-wrap">
                                    <div class="price-input">
                                        <label for="minamount">Min Price:</label>
                                        <input type="text" name="minPrice" id="minamount" value="${param.minPrice != null ? param.minPrice : 50}" /><br>
                                        <label for="maxamount">Max Price:</label>
                                        <input type="text" name="maxPrice" id="maxamount" value="${param.maxPrice != null ? param.maxPrice : 1500}" />
                                    </div>
                            </div>

                        </div>
                        <!-- Category Filter -->
                        <div class="sidebar__sizes mb-4">
                            <div class="section-title">
                                <h4>Shop by category</h4>
                            </div>
                            <div class="size__list" id="categories">

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
                            <button class="btn btn-outline-danger w-auto filter-btn" onclick="resetFilters()">
                                <i class="fas fa-redo"></i> Reset
                            </button>

                       <!-- Reset button MS7TOOOOOOOOOOOOOO-->

                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-9 col-md-9">
                <div class="product-list-wrapper">
                    <div class="row" id="product-list">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/js/main.js"></script>
<script src="/js/product-display.js"></script>
<script>
    function resetFilters() {
        // Redirect to the servlet to refresh the customer list
        window.location.href = '/shop.jsp';
    }
var customerId=4;
$(document).ready(function () {
    fetchCategories();
    fetchAllProducts();
    filterSubProducts();
    $(".buttonAddToCart").click(handleAddToCartClick);

});
function handleAddToCartClick(){
    // Get data attributes from the clicked button
    const customerId = 4; // Set your customer ID (e.g., from session or variable)
    const subProductId = $(this).data("id");
    const quantity = 1; // Set the quantity here (or get it from another element)

    // Define the data object for the request
    const requestData = {
        customerId: customerId,
        subProductId: subProductId,
        quantity: quantity
    };

    // Send the AJAX POST request
    $.ajax({
        url: "http://localhost:8083/cartItems",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(requestData), // Send the request data as JSON
        success: function (response) {
            VshowNotification("Product Added To Cart");
        },
        error: function (xhr, status, error) {
           WshowNotification("Error adding product to cart. Please try again.");
        }
    });
}


function filterSubProducts() {
    $('#filter-form').on('submit', function (event) {
        event.preventDefault();
    // Get the values from input fields (adjust selectors to match your HTML structure)
    const color = $('input[name="color"]:checked').val(); // Assuming radio buttons for color
    const size = $('input[name="size"]:checked').val();   // Assuming radio buttons for size
    const minPrice = $('#minamount').val();              // Assuming input field for minPrice
    const maxPrice = $('#maxamount').val();              // Assuming input field for maxPrice
    const gender = $('input[name="gender"]:checked').val(); // Assuming radio buttons for gender
    const category = $('input[name="category"]:checked').val(); // Assuming radio buttons for category
    const page = $('#page').val() || 1;                  // Assuming input field or default value for page

    // Build the data object
    const data = {
        color: color,
        size: size,
        minPrice: minPrice,
        maxPrice: maxPrice,
        gender: gender,
        category: category,
        page: page
    };

    // Send the AJAX POST request
    $.ajax({
        url: 'http://localhost:8083/subProducts/filter', // Adjust the URL if necessary (e.g., add context path)
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', // For form-encoded data
        data: data,
        success: function (response) {
            // Handle success - response is a list of SubProductDTOs
            console.log(response);
            renderProducts(response); // Function to render the products on the page
        },
        error: function (xhr, status, error) {
            // Handle error
            console.error('Error occurred while filtering products:', error);
        }
    });
    });
}

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

function fetchCategories() {
    $.ajax({
        url: 'http://localhost:8083/category', // API endpoint for categories
        type: 'GET',
        dataType: 'json',
        success: function (categories) {
            // Render the categories in the HTML
            renderCategories(categories);
        },
        error: function (xhr, status, error) {
            console.error('Error fetching categories:', error);
        }
    });
}

// Function to render categories into the HTML
function renderCategories(categories) {
    const categoriesContainer = $('#categories'); // Get the container
    categoriesContainer.empty(); // Clear existing content if any

    categories.forEach(function (category) {
    const size = category.name;
        const categoryHtml = `
                    <label for="`+category.name+`">
                        `+category.name+`
                        <input type="radio" name="category" id="`+category.name+`" value="`+category.name+`" />
                        <span class="checkmark"></span>
                    </label>

                `;
                categoriesContainer.append(categoryHtml);// Append the category HTML
    });
}

function renderProducts(products) {
    // Clear existing products and render new ones
    $('#product-list').empty(); // Clear the product list
    products.forEach(function (product) {
        $('#product-list').append(`
            <div class="col-lg-4 col-md-6">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg=`+ product.imageURL+`>
                        <ul class="product__hover">
                            <li><a href=`+ product.imageURL+` class="image-popup"><span class="arrow_expand"></span></a></li>
                            <li>
                            <a class="buttonAddToCart"
                               data-id="`+ product.id +`"
                               data-name="`+ product.description +`"
                               data-price="`+ product.price +`"
                               data-image="`+ product.imageURL +`"
                               data-stock="`+ product.stock +`">
                                <span class="icon_bag_alt"></span>
                            </a>
                            </li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6><a href="product-details.jsp?product.id=` + product.id + `" class="product-detail-button">`+product.description+`</a></h6>
                        <div class="product__price">$`+ product.price+`</div>
                    </div>
                </div>
            </div>
        `);
    });

    // Set background images for elements with the "set-bg" class
    $('.set-bg').each(function() {
        var bg = $(this).data('setbg'); // Get the image URL
        $(this).css('background-image', 'url(' + bg + ')'); // Set it as a background image
    });
}

</script>

<script src="js/main.js"></script>
<script src="js/product-display.js"></script>
<link rel="stylesheet" href="css/shop.css" type="text/css">
<%@ include file="footer.jsp" %>
