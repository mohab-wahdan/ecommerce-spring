<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Profiles - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admincss/customerProfiles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="mb-4">
        <a href="adminDashboard.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Dashboard</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Customer Profiles</h2>
    </div>

    <!-- Search Filters -->
    <div class="row mb-2">
        <div class="col-md-8"> <!-- Changed to col-md-8 for more space -->
            <input type="text" id="searchUsername" class="form-control" placeholder="Search by Username">
        </div>
        <div class="col-md-4"> <!-- This will hold the button -->
            <button class="btn btn-black btn-block" onclick="resetFilters()">
                <i class="fas fa-redo"></i> Reset
            </button>
        </div>
    </div>

    <div class="row mb-4">
        <div class="col-md-12">
            <button class="btn btn-teal btn-block" onclick="searchCustomers()">
                <i class="fas fa-search"></i> Search
            </button>
        </div>
    </div>

    <!-- Customer Profiles -->
    <div class="row" id="customerList">
        <!-- I will show all customers here -->
    </div>
</div>
<script src="../js/adminjs/customerProfiles.js"></script>
</body>
</html>
