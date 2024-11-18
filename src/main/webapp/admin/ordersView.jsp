<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders Management - Chicly Admin</title>

    <!-- External CSS and Font Awesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin-dashboard/css/ordersManagement.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div class="container mt-5">

    <!-- Back Button Section -->
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Dashboard
        </a>
    </div>

    <!-- Page Header Section -->
    <header class="header-container mb-4">
        <h2 class="header-font">Orders Management</h2>
    </header>

    <!-- Notifications Section -->
    <div id="notification-container" class="position-fixed top-0 left-0 right-0 p-3 z-index-9999">
        <!-- Notifications will appear here -->
    </div>

    <!-- Filter Section -->
    <section class="row mb-4">
        <div class="col-md-12">
            <div class="d-flex align-items-center">
                <!-- Filter by Status Label in Shape -->
                <span class="filter-label mr-3 mb-0">Filter by Status</span>

                <!-- Dropdown for Status -->
                <select class="form-control mr-3" id="status" name="status" style="max-width: 200px;">
                    <option value="PENDING">PENDING</option>
                    <option value="WAITING">WAITING</option>
                    <option value="COMPLETED">COMPLETED</option>
                    <option value="CANCELLED">CANCELLED</option>
                </select>

                <!-- Apply Filter Button -->
                <button type="button" id="filterBtn" class="btn btn-teal mt-2">
                    <i class="fas fa-filter"></i> Apply Filter
                </button>
            </div>
        </div>
    </section>

    <!-- Orders Table Section -->
    <section class="table-responsive">
        <table id="ordersTable" class="table table-striped">
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
    </section>
</div>

<!-- External JavaScript -->
<script src="../js/adminjs/ordersManagement.js"></script>
</body>

</html>
