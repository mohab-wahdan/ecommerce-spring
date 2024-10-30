$(document).ready(function() {

    $.ajaxSetup({
        beforeSend: function(xhr) {
            var token = sessionStorage.getItem('jwt-token');
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        }
    });
    fetchSubProducts();
    $.ajax({
        url: '/subcategory',
        method: 'GET',
        success: function (subcategories) {
            console.log(subcategories); // Check if this logs an array of subcategories
            subcategories.forEach(function (subcategory) {
                $('#subcategory').append(new Option(subcategory.name, subcategory.id));
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error fetching subcategories:', textStatus, errorThrown);
        }
    });
});

function fetchSubProducts() {
    $.ajax({
        url: '/subProducts', // Adjust this URL as needed to match your backend route
        type: 'GET',
        success: function(response) {
            renderSubProducts(response);
        },
        error: function(xhr, status, error) {
            console.error("Failed to load subproducts:", error);
        }
    });
}

function renderSubProducts(subProducts) {
    let productsContainer = "";
    if (subProducts.length === 0) {
        productsContainer += '<div class="col-md-12"><p class="text-center text-muted">No products found.</p></div>';
    } else {
        subProducts.forEach(subProduct => {
            productsContainer += `
                <div class="col-md-4 product-list">
                    <div class="card mb-4">
                        <img src="/${subProduct.imageURL}" class="card-img-top" alt="${subProduct.productName}">
                        <div class="card-body">
                            <h5 class="card-title">${subProduct.productName}</h5>
                            <p class="card-text">Price: ${subProduct.price}</p>
                            <p class="card-text">Colour: ${subProduct.color}</p>
                            <p class="card-text">Size: ${subProduct.size}</p>
                            <p class="card-text">Quantity: ${subProduct.stock}</p>
                            <button class="btn btn-black" onclick="updateProduct('${subProduct.id}')">
                                <i class="fas fa-edit"></i> Update
                            </button>
                            <button class="btn btn-red" onclick="deleteProduct('${subProduct.id}')">
                                <i class="fas fa-trash"></i> Delete
                            </button>
                        </div>
                    </div>
                </div>
            `;
        });
    }
    $("#products").html(productsContainer);
}
function updateProduct(subProductId) {
    $.get(`/subProducts/subproductId/${subProductId}`, function(subProduct) {
        // Redirect to the update product page with the subproduct ID
        window.location.href = `/admin/updateProduct.jsp?id=${subProductId}&data=${encodeURIComponent(JSON.stringify(subProduct))}`;
    });
}

function deleteProduct(subProductId) {
    $.ajax({
        url: `/subProducts/${subProductId}`,
        type: 'DELETE',
        success: function(response) {
            // Redirect to the view products page after successful deletion
            window.location.href = `/admin/view-products.jsp`;
        },
        error: function(xhr, status, error) {
            console.error("Failed to delete product:", error);
         }
    });
}


function filterProducts() {
    const subcategoryId = document.getElementById('subcategory').value; // Get the value of the subcategory input
    const filterBtn = document.getElementById('filterBtn'); // Reference to your filter button

    if (subcategoryId === "") {
        filterBtn.disabled = true; // Disable the button if the subcategory is empty
    } else {
        // Make an AJAX call to fetch products by subcategory ID
        $.ajax({
            url: '/subProducts/subcategoryId/' + encodeURIComponent(subcategoryId), // Adjusted endpoint
            method: 'GET',
            success: function(products) {
                renderSubProducts(products); // Call the function to render products
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error fetching products:', textStatus, errorThrown);
            }
        });
    }
}


function addNewProduct() {
    window.location.href = '/admin/addSubProduct.jsp';
}
function resetFilters() {
    window.location.href = '/admin/view-products.jsp';
}
function removeQueryParam() {
    const url = window.location.protocol + "//" + window.location.host + window.location.pathname;
    window.history.replaceState({}, document.title, url);
}