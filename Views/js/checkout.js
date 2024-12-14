$(document).ready(function () {
    let user = sessionStorage.getItem('userDetails');

    if (!user)
        window.location.href = 'login.html';

    user = JSON.parse(user);

    let cart = sessionStorage.getItem('cart');
    cart = cart ? JSON.parse(cart) : [];

    cart.forEach(function (product) {
        fetch('http://localhost:5088/api/Products/' + product.productID)
            .then(response => response.json())
            .then(data => {
                $('#orders').append(
                    `<div class="bg-white shadow-md rounded-lg overflow-hidden mb-4">
                    <div class="p-4">
                        <h2 class="text-xl font-semibold text-gray-800">${product.name}</h2>
                        <div class="flex justify-between items-center mt-4">
                            <span class="text-lg font-bold text-gray-900">Price: $${product.price}</span>
                            <span class="text-sm text-gray-500">Stock: ${product.quantity}</span>
                        </div>
                    </div>
                    <img src="${data.productPictureUrl}" alt="Product Image" class="w-full h-48 object-cover">
                </div>`
                );
            }).catch
            (error => {
                alert('Error: ' + error);
            });
    });


    $('#placeOrder').on('click', function () {
        if(cart.length === 0) {
            alert('Cart is empty!');
            return;
        }

        cart.forEach(function (product) {
            $.ajax({
                url: 'http://localhost:5088/api/Orders',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    productID: product.productID,
                    userID: user.userID,
                    quantity: product.quantity
                }),
                success: function () {
                    console.log('Order placed!');
                },
                error: function () {
                    alert('Error placing order!');
                }
            });
        });

        alert('Order placed!');
        cart = []
        sessionStorage.setItem('cart', JSON.stringify(cart));
        
        window.location.href = 'home.html';
    });

});