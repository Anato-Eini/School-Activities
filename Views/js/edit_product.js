$(document).ready(function () {
    let user = sessionStorage.getItem('userDetails');
    if (!user) 
        window.location.href = 'login.html';

    user = JSON.parse(user);

    let product = sessionStorage.getItem('product');
    if (!product) 
        window.location.href = 'home.html';

    product = JSON.parse(product);
    if (product.userID !== user.userID) 
        window.location.href = 'home.html';


    $('#productName').val(product.name);
    $('#description').val(product.description);
    $('#price').val(product.price);
    $('#stock').val(product.stock);

    $('#sell-item-form').submit(function (e) {
        e.preventDefault();

        var formData = new FormData(this);
        formData.append('productID', product.productID);

        $.ajax({
            url: 'http://localhost:5088/api/Products',
            type: 'PUT',
            contentType: false, 
            processData: false, 
            data: formData,
            success: function (response) {
                if (response !== null) {
                    sessionStorage.setItem('product', JSON.stringify(response));
                    window.location.href = 'product_detail.html';
                } else {
                    $('#edit-error').html(response);
                }
            },
            error: function (response) {
                $('#edit-error').html(response);
            }
        });
    });
});