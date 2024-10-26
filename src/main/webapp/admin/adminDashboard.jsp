<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admincss/adminDashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="d-flex" id="wrapper">
    <div class="bg-black border-right" id="sidebar-wrapper">
        <div class="sidebar-heading text-white"><img src="../img/admin-logo.png" width="130" height="130" alt=""></div>
        <div class="list-group list-group-flush">
            <a href="/admin/addCategory.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-list"></i> Add Category
            </a>
            <a href="/admin/addSubCategory.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-tags"></i> Add SubCategory
            </a>
            <a href="/admin/addProduct.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-boxes"></i> Manage Products
            </a>
            <a href="/admin/view-products.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-cubes"></i> Manage SubProducts
            </a>
            <a href="/admin/customerProfiles.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-users"></i> Customer Profiles
            </a>
            <a href="/admin/adminLogin.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>
    </div>

    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-white border-bottom">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>
                <div class="ml-auto">
                    <img src="../img/logo.png" width="100" height="50" alt="Project Logo">
                    <p >Admin ${sessionScope.adminName}</p>
                </div>

            </div>
        </nav>

        <div class="container-fluid">
            <h1 class="mt-4">Welcome, Admin</h1>
            <p>Use the sidebar to manage the website.</p>
            <div class="container mt-4">
                <!-- Success/Error Message -->
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
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Product Statistics</h5>
                            <p>Total Categories: 4</p>
                            <p>Total Products: 45</p>
                            <p>Low Stock Alerts: 7</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Customer Statistics</h5>
                            <p>Total Customers: 62</p>
                            <p>New Customers (Last 7 Days): 15</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Orders</h5>
                            <p>Orders in Progress: 44</p>
                            <p>Completed Orders: 23</p>
                        </div>
                    </div>
                </div>
            </div>

            <h4>Notifications</h4>
            <div class="alert alert-warning" role="alert">
                Low Stock Alert: Red Looney Tunes x Olympic Collection T-shirt (5 units remaining).
            </div>
        </div>
    </div>
</div>

<script>
    // Toggle Menu Script
    document.getElementById("menu-toggle").addEventListener("click", function(e) {
        e.preventDefault();
        document.getElementById("wrapper").classList.toggle("toggled");
    });
    function removeQueryParam() {
        const url = new URL(window.location);
        url.searchParams.delete("successMessage");
        url.searchParams.delete("errorMessage");
        window.history.replaceState(null, "", url);
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>