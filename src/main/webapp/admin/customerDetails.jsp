<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details - Chicly Admin</title>

    <!-- External CSS and Font Awesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admincss/customerDetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="customerProfiles.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Profiles
        </a>
    </div>

    <!-- Header Container -->
    <div class="header-container mb-4">
        <h2 class="header-font">Customer Details</h2>
    </div>

    <!-- Notifications Section -->
    <div id="notification-container" class="position-fixed top-0 left-0 right-0 p-3 z-index-9999">
        <!-- Notifications will appear here -->
    </div>

    <!-- Customer Details Card -->
    <div class="card shadow mb-4">
        <div class="card-header bg-primary text-white">
            <h4  id="customerName" style="font-size: 1.5rem;"></h4>
        </div>
        <div class="card-body">
            <h5 class="card-title mb-4">User Information</h5>
            <p><strong class="user-info-card-title">Username: </strong><span id="customerUsername"></span></p>
            <p><strong class="user-info-card-title">Email: </strong><span id="customerEmail"></span></p>
            <p><strong class="user-info-card-title">Phone Number: </strong><span id="customerPhone"></span></p>
            <p><strong class="user-info-card-title">Address: </strong><span id="customerAddress"></span></p>
            <p><strong class="user-info-card-title">Description: </strong><span id="customerDescription"></span></p>
        </div>
    </div>

    <!-- Orders List Section -->
    <div class="mt-4">
        <h3 class="mb-4 text-dark">Order History</h3>
        <div class="table-responsive">
            <table id="ordersTable" class="table table-striped table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>Order ID</th>
                    <th>Status</th>
                    <th>Created Date</th>
                    <th>Destination</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <!-- Orders will be populated here via JavaScript -->
                </tbody>
            </table>
        </div>
    </div>

</div>

<!-- External JavaScript -->
<script src="../js/adminjs/customerDetails.js"></script>

</body>

</html>
