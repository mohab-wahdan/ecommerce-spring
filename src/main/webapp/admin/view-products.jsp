<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Products - Chicly Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="../css/admincss/viewProducts.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="container mt-5">
    <div class="mb-4">
        <a href="/admin/adminDashboard.jsp" class="btn btn-black">
            <i class="fas fa-arrow-left"></i> Back to Dashboard</a>
    </div>
    <div class="header-container">
        <h2 class="header-font">Manage Products</h2>
    </div>
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
    <div class="row">
        <div class="col-md-3">
            <h4>Filter Products</h4>
            <form id="filterForm">
                <div class="form-group">
                    <label for="subcategory">Subcategory</label>
                    <select class="form-control" id="subcategory" name="subcategory">
                        <option value="">Select Subcategory</option>
                        <c:forEach var="subcategory" items="${sessionScope.subcategories}">
                            <option value="${subcategory.name}">${subcategory.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="button" id="filterBtn" class="btn btn-black btn-block" onclick="filterProducts()">
                    <i class="fas fa-filter"></i> Apply Filters</button>
                <button class="btn btn-black btn-block" onclick="resetFilters()">
                    <i class="fas fa-redo"></i> Reset</button>
                <button type="button" class="btn btn-teal btn-block mt-2" onclick="addNewProduct()">
                    <i class="fas fa-plus"></i> Add New Product</button>

            </form>
        </div>

        <div class="col-md-9">
            <div class="row" id="products">
                <!-- I will show all customers here -->
            </div>
        </div>
    </div>
</div>
<script src="../js/adminjs/view-products.js"></script>>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>