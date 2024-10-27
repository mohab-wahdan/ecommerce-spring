$(document).ready(function () {
    // Fetch subcategories on page load
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

    // Handle form submission
    $('#addBtn').on('click', function (event) {
        event.preventDefault();

        // Collect and validate form data
        const productData = {
            name: $('#productName').val(),
            description: $('#productDesc').val(),
            gender: $('#productGender').val(),
            subCategoryId: parseInt($('#subcategory').val())
        };

        // Validate form inputs
        if (!productData.name || !productData.description || !productData.gender || isNaN(productData.subCategoryId)) {
            alert('Please fill out all fields before submitting.');
            return;
        }

        // Send data as JSON to the server
        $.ajax({
            url: '/products',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(productData),
            success: function () {
                window.location.href = '/admin/adminDashboard.jsp'; // Redirect to the dashboard
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error adding product:', textStatus, errorThrown);
            }
        });
    });
});
