function renderPagesNumber(total) {
    const paginationContainer = $('.pagination__option');
    paginationContainer.empty(); // Clear existing pagination
    const totalPages = Math.ceil(total / 6);
    for (let i = 1; i <= totalPages; i++) {
        console.log(`Creating page: ${i}`);
        paginationContainer.append(`
            <a href="#" class="page-link" data-page="${i}">${i}</a>
        `);
    }
}