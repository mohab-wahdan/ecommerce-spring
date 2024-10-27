$(document).ready(function() {
    $.ajaxSetup({
        beforeSend: function(xhr) {
            var token = sessionStorage.getItem('jwt-token');
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        }
    });
    $.ajax({
        url: '/category',
        method: 'GET',
        success: function(categories) {
            categories.forEach(function(category) {
                $('#category').append(new Option(category.name,category.id));
            });
        },
        error: function() {
            console.error('Error fetching categories.');
        }
    });
});