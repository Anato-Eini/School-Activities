$(document).ready(function () {
    let user = sessionStorage.getItem('userDetails');

    if (!user)
        window.location.href = 'login.html';

    user = JSON.parse(user);

    let cart = sessionStorage.getItem('cart');
    cart = cart ? JSON.parse(cart) : [];

    let fetchPromises = cart.map(async function (product) {
        try {
            const response = await fetch('http://localhost:5088/api/Products/' + product.productID);
            const data = await response.json();
            $('#orders').append(
                `<div class="bg-white shadow-md rounded-lg overflow-hidden mb-4">
                    <div class="p-4">
                        <h2 class="text-xl font-semibold text-gray-800">${data.name}</h2>
                        <div class="flex justify-between items-center mt-4">
                            <span class="text-lg font-bold text-gray-900">Price: $${data.price}</span>
                            <span class="text-sm text-gray-500">Stock: ${product.quantity}</span>
                        </div>
                        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mt-4 cancelOrder" data-product="${product.productID}">Cancel Order</button>
                    </div>
                    <img src="${data.productPictureUrl}" alt="Product Image" class="w-full h-48 object-cover">
                </div>`
            );
        } catch (error) {
            alert('Error: ' + error);
        }
    });

    Promise.all(fetchPromises).then(() => {
        $('.cancelOrder').on('click', function () {
            let productID = $(this).data('product');
            cart = cart.filter(product => product.productID !== productID);
            sessionStorage.setItem('cart', JSON.stringify(cart));
            window.location.reload();
        });
    });

    $('#placeOrder').on('click', function () {
        if (cart.length === 0) {
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
                    alert('Order placed!');
                },
                error: function () {
                    alert('Error placing order!');
                }
            });
        });

        cart = []
        sessionStorage.setItem('cart', JSON.stringify(cart));

        window.location.href = 'home.html';
    });
});