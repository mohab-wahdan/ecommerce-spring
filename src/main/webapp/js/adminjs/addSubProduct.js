$(document).ready(function () {
    // Fetch subcategories on page load
    $.ajax({
        url: '/enums/colors',
        method: 'GET',
        success: function (colors) {
            console.log(colors); // Check if this logs an array of subcategories
            colors.forEach(function (color) {
                $('#color').append(new Option(color));
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error fetching colors:', textStatus, errorThrown);
        }
    });
    $.ajax({
        url: '/enums/sizes',
        method: 'GET',
        success: function (sizes) {
            console.log(sizes); // Check if this logs an array of subcategories
            sizes.forEach(function (size) {
                $('#size').append(new Option(size));
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error fetching sizes:', textStatus, errorThrown);
        }
    });
    $.ajax({
        url: '/products',
        method: 'GET',
        success: function (products) {
            console.log(products); // Check if this logs an array of subcategories
            products.forEach(function (product) {
                $('#mainProduct').append(new Option(product.name,product.id));
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error fetching products:', textStatus, errorThrown);
        }
    });
    // Handle form submission
    $('#addBtn').on('click', function (event) {
        event.preventDefault(); // Prevent the default form submission

        // Create a FormData object from the form
        const formData = new FormData(document.getElementById('addProductForm'));

        // Send the FormData to the server using AJAX
        $.ajax({
            url: '/subProducts', // Ensure this matches your endpoint
            type: 'POST',
            processData: false, // Prevent jQuery from processing the data
            contentType: false, // Prevent jQuery from setting content type
            data: formData,
            success: function (response) {
                // Redirect or show success message
                console.log('Product added successfully:', response);
                window.location.href = '/admin/view-products.jsp'; // Redirect to the dashboard
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error adding product:', textStatus, errorThrown);
                alert('Failed to add product: ' + errorThrown);
            }
        });
    });
    $('#image').on('change', function(event) {
        const imagePreview = document.getElementById('imagePreview');
        imagePreview.src = URL.createObjectURL(event.target.files[0]);
        imagePreview.style.display = 'block'; // Show the preview
    });

});
//////////////////////////////////////
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('imagePreview');
        output.src = reader.result;
        output.style.display = 'block';
    }
    reader.readAsDataURL(event.target.files[0]);
}
function checkPrice() {
    var creditValue = document.getElementById("price").value;
    if (creditValue < 0) {
        document.getElementById("priceerror").innerText = "Price must be more than 0";
    } else {
        document.getElementById("priceerror").innerText = "";
    }

}
function checkQuantity() {
    var creditValue = document.getElementById("quantity").value;
    if (creditValue < 0) {
        document.getElementById("quantityerror").innerText = "Stock must be more than 0";
    } else {
        document.getElementById("quantityerror").innerText = "";
    }
}
function checkErrors() {
    var addBtn = document.getElementById("addBtn");

    if (document.getElementById('quantityerror').textContent !=="") {
        addBtn.disabled = true; // Enable the button if the condition is met
    } else if(document.getElementById('priceerror').textContent !==""){
        addBtn.disabled = true; // Disable the button if the condition is not met
    }else{
        addBtn.disabled = false;
    }
}