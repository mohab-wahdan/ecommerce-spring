
$(document).ready(function() {
    $.ajaxSetup({
        beforeSend: function(xhr) {
            var token = sessionStorage.getItem('jwt-token');
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        }
    });
    const urlParams = new URLSearchParams(window.location.search);
    const data = urlParams.get('data');
    if (data) {
    const subProduct = JSON.parse(decodeURIComponent(data));
    $('#subProductId').val(subProduct.id);
    $('#productName').val(subProduct.productName);
    $('#price').val(subProduct.price);
    $('#color').val(subProduct.color);
    $('#size').val(subProduct.size);
    $('#quantity').val(subProduct.stock);
    $('#imagePreview').attr('src', "/"+subProduct.imageURL).show(); // Show current product image
}
});
function updateSubProduct() {
    const subProductId = document.getElementById('subProductId').value;
    const price = document.getElementById('price').value;
    const quantity = document.getElementById('quantity').value;
    const newImage = document.getElementById('newImage').files[0];

    const formData = new FormData();
    formData.append('price', price);
    formData.append('quantity', quantity);
    if (newImage) {
        formData.append('newImage', newImage); // Attach the image file itself
    }

    $.ajax({
        url: `/subProducts/${subProductId}`,
        type: 'PUT',
        data: formData,
        contentType: false,  // Prevent jQuery from setting the Content-Type header
        processData: false,  // Prevent jQuery from processing the data
        cache: false,
        success: function () {
            window.location.href = '/admin/view-products.jsp';
        },
        error: function (xhr, status, error) {
            console.error('Update failed:', error);
            alert('An error occurred while updating the product.');
        }
    });
}

// Preview the new image before submission
function previewNewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('newImagePreview');
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
    var updateBtn = document.getElementById("updateBtn");

    if (document.getElementById('quantityerror').textContent !=="") {
        updateBtn.disabled = true; // Enable the button if the condition is met
    } else if(document.getElementById('priceerror').textContent !==""){
        updateBtn.disabled = true; // Disable the button if the condition is not met
    }else{
        updateBtn.disabled = false;
    }
}