<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/admincss/updateProduct.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="mt-4">
        <a href="/admin/view-products.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Products</a>
    </div>
    <div class="container mt-5">
        <div class="header-container">
            <h2 class="header-font">Update Product</h2>
        </div>

        <form id="updateProductForm" enctype="multipart/form-data">
            <input type="hidden" id="subProductId" name="subProductId">

            <div class="d-flex">
                <div class="product-image-container">
                    <label for="imagePreview">Current Product Image</label><br>
                    <img id="imagePreview" alt="Product Image" class="product-image">
                </div>

                <div class="ml-5 flex-fill">
                    <div class="form-group">
                        <label for="productName">Product Name</label>
                        <input type="text" class="form-control" id="productName" name="productName" disabled>
                    </div>

                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" class="form-control" id="price" name="price" step="0.01" onblur="checkPrice();" required>
                        <span class="error-message" id="priceerror"></span>
                    </div>

                    <div class="form-group">
                        <label for="color">Color</label>
                        <input type="text" class="form-control" id="color" name="color" disabled>
                    </div>

                    <div class="form-group">
                        <label for="size">Size</label>
                        <input type="text" class="form-control" id="size" name="size" disabled>
                    </div>

                    <div class="form-group">
                        <label for="quantity">Quantity</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" onblur="checkQuantity();" required>
                        <span class="error-message" id="quantityerror"></span>
                    </div>

                    <div class="form-group">
                        <label for="newImage">Upload New Image (Optional)</label>
                        <input type="file" class="form-control-file" id="newImage" name="newImage" accept="image/*" onchange="previewNewImage(event)">
                        <img id="newImagePreview" src="" alt="New Image Preview" style="display:none; margin-top:10px; max-width: 200px;">
                    </div>

                    <button id="updateBtn" type="button" class="btn btn-teal btn-block mt-2" onmouseover="checkErrors()" onclick="updateSubProduct()">
                        <i class="fas fa-edit"></i> Update
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="../js/adminjs/updateProduct.js"></script>>
</body>
</html>
