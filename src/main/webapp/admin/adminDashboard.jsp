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
            <a href="/admin/ordersView.jsp" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-clipboard-list order-items-container"></i> Manage Orders
            </a>
            <a href="../index.jsp" class="list-group-item list-group-item-action bg-black text-white" onclick="logout()">
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

            <div class="row" id="statistics">
                <!-- Product Statistics -->
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Product Statistics</h5>
                            <p id="totalCategories">Total Categories: </p>
                            <p id="totalProducts">Total Products: </p>
                            <p id="lowStockAlerts">Low Stock Alerts: </p>
                        </div>
                    </div>
                </div>

                <!-- Customer Statistics -->
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Customer Statistics</h5>
                            <p id="totalCustomers">Total Customers: </p>
                            <p id="newCustomers">New Customers (Last 7 Days): </p>
                        </div>
                    </div>
                </div>

                <!-- Orders Statistics -->
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Orders</h5>
                            <p id="ordersInProgress">Orders in Progress: </p>
                            <p id="completedOrders">Completed Orders: </p>
                            <p id="newOrders">New Orders (Last 5 Days): </p>
                        </div>
                    </div>
                </div>
            </div>

            <h4>Notifications</h4>
            <div class="alert alert-warning" id="lowStockNotification" role="alert">
                <!-- Low stock alert message will be populated here -->
            </div>

    </div>
</div>

<script>
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
    function logout(){
        sessionStorage.clear();
    }
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        // AJAX request to fetch statistics
        $.ajax({
            url: '/stats', // Replace with your API URL
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                // Populate Product Statistics
                $("#totalCategories").text('Total Categories: '+ data.totalCategories );
                $("#totalProducts").text( 'Total Products: '+data.totalProducts);
                $("#lowStockAlerts").text('Low Stock Alerts: '+data.numOfLowStock );

                // Populate Customer Statistics
                $("#totalCustomers").text('Total Customers: '+data.totalCustomers );
                $("#newCustomers").text('New Customers (Last 7 Days): '+data.newCustomers );

                // Populate Order Statistics
                $("#ordersInProgress").text('Orders in Progress: '+data.progressOrders );
                $("#completedOrders").text('Completed Orders: '+data.completedOrders );
                $("#newOrders").text('New Orders (Last 5 Days): '+data.numOfNewOrders );

                // Populate Low Stock Notification
                if (data.lowStock && data.lowStock.length > 0) {
                    const lowStockList = data.lowStock.join('<br>'); // Join items with <br> for new lines
                    $("#lowStockNotification").html('Low Stock Alert:<br>' + lowStockList);
                } else {
                    $("#lowStockNotification").hide(); // Hide the alert if no low stock
                }
            },
            error: function (xhr, status, error) {
                console.error("Error fetching data:", error);
            }
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>