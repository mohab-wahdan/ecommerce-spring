$(document).ready(function() {
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