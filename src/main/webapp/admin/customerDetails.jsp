<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Details - Chicly Admin</title>
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
            <i class="fas fa-arrow-left"></i> Back to Profiles</a>
    </div>

    <!-- Header Container -->
    <div class="header-container">
        <h2 class="header-font">Customer Details</h2>
    </div>

    <!-- Customer Details -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title"></h5>
            <p class="card-text"></p> <!-- Username -->
            <p class="card-text"></p> <!-- Email -->
            <p class="card-text"></p> <!-- Phone -->
            <p class="card-text"></p> <!-- Address -->
            <p class="card-text"></p> <!-- Description -->
        </div>
    </div>

    <!-- Orders List -->
    <!-- Orders List -->
    <h3 class="mb-4">Order History</h3>
    <table id="ordersTable" class="table table-striped">
        <thead>
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
<script src="../js/adminjs/customerDetails.js"></script>
</body>
</html>
