<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admincss/addSubCategory.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="/admin/adminDashboard.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Dashboard</a>

    </div>
    <div class="header-container">
        <h2 class="header-font">Add New SubCategory</h2>
    </div>

    <form action="/subcategory" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label for="category">Main Category</label>
            <select class="form-control" id="category" name="category" required>
                <option value="">Select Main Category</option>
            </select>
        </div>

        <div class="form-group">
            <label for="subcategory">SubCategory Name</label>
            <input type="text" class="form-control" id="subcategory" name="subcategory" placeholder="Enter stock quantity" required>
        </div>


        <button id="addBtn" type="submit" class="btn btn-teal btn-block mt-2">
            <i class="fas fa-plus"></i> Add SubCategory
        </button>
    </form>
</div>
<script src="../js/adminjs/addSubCategory.js"></script>>

</body>
</html>
