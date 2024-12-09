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

});