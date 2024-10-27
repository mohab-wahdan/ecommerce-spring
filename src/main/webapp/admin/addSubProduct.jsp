<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admincss/addProduct.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="container mt-5">
    <!-- Back Button -->
    <div class="mb-4">
        <a href="/admin/view-products.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Products</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Add New Product</h2>
    </div>

    <form id="addProductForm" enctype="multipart/form-data">
        <div class="form-group">
            <label for="mainProduct">Main Product</label>
            <select class="form-control" id="mainProduct" name="mainProduct" required>
                <option value="">Select Main Product</option>
            </select>
        </div>

        <div class="form-group">
            <label for="color">Color</label>
            <select class="form-control" id="color" name="color" required>
                <option value="">Select Color</option>
            </select>
        </div>

        <div class="form-group">
            <label for="size">Size</label>
            <select class="form-control" id="size" name="size" required>
                <option value="">Select Size</option>
            </select>
        </div>

        <div class="form-group">
            <label for="quantity">Stock</label>
            <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Enter stock quantity" onblur="checkQuantity();" required>
            <span class="error-message" id="quantityerror"></span>

        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" class="form-control" id="price" name="price" placeholder="Enter price" step="0.01" onblur="checkPrice();" required>
            <span class="error-message" id="priceerror"></span>

        </div>

        <div class="form-group">
            <label for="image">Product Image</label>
            <input type="file" class="form-control-file" id="image" name="image" accept="image/*" onchange="previewImage(event)" required>
            <img id="imagePreview" src="" alt="Image Preview" style="display:none; margin-top:10px; max-width: 200px;">
        </div>

        <button id="addBtn" type="submit" class="btn btn-teal btn-block mt-2">
            <i class="fas fa-plus"></i> Add New Product
        </button>
    </form>
</div>
<script src="../js/adminjs/addSubProduct.js"></script>>

</body>
</html>
