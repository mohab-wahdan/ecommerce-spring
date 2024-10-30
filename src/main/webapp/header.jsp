<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chicly</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        const userLoginContainer = document.getElementById("user-login");
        const username = sessionStorage.getItem("username");

        if (username) {
            // Show dropdown menu with username and additional links
            userLoginContainer.innerHTML = `
            <div class="flex-container">
            <p class="user-title" >`+username+` </p>
               <div class="user-dropdown">
                   <a href="#" class="user-menu">
                        <i class="fa fa-user fa-2x"></i> <!-- User Icon -->
                        <span class="arrow-down"></span>
                    </a>
                   <ul class="user-submenu">
                       <li><a href="userProfile.jsp">Profile</a></li>
                       <li><a href="orderHistory.jsp">My Orders</a></li>
                       <li><a href="#" id="logout">Logout</a></li>
                   </ul>
               </div>
            </div>`;

            document.getElementById("logout").addEventListener("click", function() {
                sessionStorage.clear();
                userLoginContainer.innerHTML = `
                    <a href="login.jsp">Login</a>
                    <a href="registration.jsp">Register</a>`;
                    window.location.href = 'index.jsp';
                });
        } else {
            // Show Login and Register links if user is not logged in
            userLoginContainer.innerHTML = `
                <a href="login.jsp">Login</a>
                <a href="registration.jsp">Register</a>
            `;
        }
    });
</script>

<style>
        .flex-container {
            display: flex; /* Enable flexbox layout */
            align-items: center; /* Center items vertically */
        }

        .flex-container p {
            margin: 0 10px; /* Optional margin for spacing */
        }
    /* Custom Dropdown Menu */
    .user-dropdown {
        position: relative;
        display: inline-block;
    }

    .user-menu {
        font-size: 14px;
        color: #333;
        text-decoration: none;
        padding: 10px;
        display: flex;
        align-items: center;
        cursor: pointer;
    }

    /* User icon from Font Awesome or similar */
    .user-menu .icon_user {
        font-size: 18px; /* Adjust icon size */
        margin-right: 5px;
        color: #333;
    }
    .user-title{
        font-size: 16px;
        margin-right: 10px; /* Space between title and value */
        background-color: #f8f9fa;
        padding: 5px 10px;
        border-radius: 10px;
        text-transform: uppercase;
        font-weight: bold;
        letter-spacing: 1px;
        display: inline-block;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .user-menu .arrow-down {
        margin-left: 5px;
        border: solid #333;
        border-width: 0 2px 2px 0;
        display: inline-block;
        padding: 2px;
        transform: rotate(45deg);
        -webkit-transform: rotate(45deg);
    }

    .user-submenu {
        display: none;
        position: absolute;
        background-color: #fff;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        min-width: 160px;
        z-index: 1;
        right: 0; /* Aligns the dropdown to the right */
    }

    .user-submenu li {
        list-style-type: none;
        padding: 10px;
        border-bottom: 1px solid #eee;
        text-align: right;
    }

    .user-submenu li a {
        text-decoration:none ;
        text-align: right;
        color: #333;
        font-size: 14px;
        display: block;
    }

    .user-submenu li a:hover {
        background-color: #f1f1f1;
        color: #000;
    }

    /* Show dropdown on hover */
    .user-dropdown:hover .user-submenu {
        display: block;
    }

</style>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
<%--        <ul class="offcanvas__widget">--%>
<%--            <li><a href="shop-cart.jsp"><span class="icon_bag_alt"></span>--%>
<%--            </a></li>--%>
<%--        </ul>--%>
        <div class="offcanvas__logo">

            <a href="index.jsp"><img src="img/logo.png" width="98" height="31" alt=""></a>

        </div>
        <a href="shop-cart.jsp"></a><span class="icon_bag_alt"></span>

        <div id="mobile-menu-wrap">
            <div class="offcanvas__auth">
                <a href="login.jsp">Login  /</a>
                <a href="registration.jsp">   Register</a>
            </div>
        </div>

    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
 
                        <a href="index.jsp"><img src="img/logo.png" width="98" height="31" alt=""></a>
 
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="index.jsp">Home</a></li>
                            <li><a href="shop.jsp">Shop</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                    <div class="header__right__auth" id="user-login">

                    </div>
                    <ul class="header__right__widget">
                        <li><a href="shop-cart.jsp"><span class="icon_bag_alt"></span>
                        </a></li>
                    </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function() {
            const userLoginContainer = document.getElementById("user-login");
            const username = sessionStorage.getItem("username");

            if (username) {
                // Show dropdown menu with username and additional links
                userLoginContainer.innerHTML = `
            <div class="flex-container">
            <p class="user-title" >`+username+` </p>
               <div class="user-dropdown">
                   <a href="#" class="user-menu">
                        <i class="fa fa-user fa-2x"></i> <!-- User Icon -->
                        <span class="arrow-down"></span>
                    </a>
                   <ul class="user-submenu">
                       <li><a href="userProfile.jsp">Profile</a></li>
                       <li><a href="orderHistory.jsp">My Orders</a></li>
                       <li><a href="#" id="logout">Logout</a></li>
                   </ul>
               </div>
            </div>`;

                document.getElementById("logout").addEventListener("click", function() {
                    sessionStorage.clear();
                    userLoginContainer.innerHTML = `
                    <a href="login.jsp">Login</a>
                    <a href="registration.jsp">Register</a>`;
                    window.location.href = 'index.jsp';
                });
            } else {
                // Show Login and Register links if user is not logged in
                userLoginContainer.innerHTML = `
                <a href="login.jsp">Login</a>
                <a href="registration.jsp">/  Register</a>
            `;
            }
        });
    </script>

    <!-- Header Section End -->