$(document).ready(function () {
    let user = sessionStorage.getItem('userDetails');
    if (!user) 
        window.location.href = 'login.html';

    user = JSON.parse(user);
    let product = sessionStorage.getItem('product');

    if (!product) 
        window.location.href = 'home.html';

    console.log(product);

    product = JSON.parse(product);

    $('#name').html(product.name);
    $('#description').html(product.description);
    $('#price').html(product.price);
    $('#stock').html(product.stock);
    $('#productPictureUrl').attr('src', product.productPictureUrl);

    if (user.userID === product.userID) 
        $('#owner').append(
            '<button id="editButton" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-blue-200">Edit</button>'
        );

    $('#editButton').on('click', function () {
        sessionStorage.setItem('product', JSON.stringify(product));
        window.location.href = 'edit_product.html';
    });
    
});