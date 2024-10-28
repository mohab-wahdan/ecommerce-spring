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
</head>
<body>
<%@ include file="header.jsp" %>
<section class="categories">
    <div class="container-fluid">
        <c:choose>
            <c:when test="${not empty sessionScope.successMessage}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" style="font-size: 1.1em; font-weight: bold;">
                    <i class="fas fa-check-circle"></i> ${sessionScope.successMessage}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="removeQueryParam()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <!-- Remove message after displaying -->
                <c:remove var="successMessage" scope="session"/>
            </c:when>
            <c:when test="${not empty sessionScope.errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" style="font-size: 1.1em; font-weight: bold;">
                    <i class="fas fa-exclamation-triangle"></i> ${sessionScope.errorMessage}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="removeQueryParam()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <!-- Remove message after displaying -->
                <c:remove var="errorMessage" scope="session"/>
            </c:when>
        </c:choose>
        <div class="row">

            <div class="col-lg-6 p-0">
                <div class="categories__item categories__large__item set-bg"
                     data-setbg="img/categories/female.jpg">
                    <div class="categories__text">
                        <h1>Women’s fashion</h1>
                        <p>Embrace your unique fashion journey and express yourself with our stunning collection. From chicly everyday essentials to bold statement pieces, we have everything you need.</p>
                        <a href="/shop.jsp">Shop now</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/clothes.jpg">
                            <div class="categories__text">
                                <h4>Clothing</h4>
                                <a href="/shop.jsp">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/male.jpg">
                            <div class="categories__text">
                                <h4>Men 's fashion</h4>
                                <a href="/shop.jsp">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/footwear.jpg">
                            <div class="categories__text">
                                <h4>Footwear</h4>
                                <a href="/shop.jsp">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 p-0">
                        <div class="categories__item set-bg" data-setbg="img/categories/accessories.jpg">
                            <div class="categories__text">
                                <h4>Accessories</h4>
                                <a href="/shop.jsp">Shop now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Banner Section Begin -->
<section class="banner set-bg" data-setbg="img/banner/banner-1.jpg">
    <div class="container">

        <div class="row">
            <div class="col-xl-7 col-lg-8 m-auto">
                <div class="banner__slider owl-carousel">
                    <!-- Success/Error Message -->
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>The Chloe Collection</span>
                            <h1>The Project Jacket</h1>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>The Chloe Collection</span>
                            <h1>The Project Jacket</h1>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                    <div class="banner__item">
                        <div class="banner__text">
                            <span>The Chloe Collection</span>
                            <h1>The Project Jacket</h1>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="services spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-car"></i>
                    <h6>Free Shipping</h6>
                    <p>For all oder over $99</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-money"></i>
                    <h6>Money Back Guarantee</h6>
                    <p>If good have Problems</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-support"></i>
                    <h6>Online Support 24/7</h6>
                    <p>Dedicated support</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-headphones"></i>
                    <h6>Payment Secure</h6>
                    <p>100% secure payment</p>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>