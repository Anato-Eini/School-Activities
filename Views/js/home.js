$(document).ready(function () {
    var user = sessionStorage.getItem('userDetails');
    if (!user) {
        window.location.href = 'login.html';
    }

    user = JSON.parse(user);

    if (user.isFarmer == true) {
        $('#sell-item').append(
            '<button id="sellButton" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded text-green-200">Sell Item</button>'
        );
    }
    var products = []
    //get all products ajax call
    $.ajax({
        url: 'http://localhost:5088/api/Products',
        type: 'GET',
        contentType: 'application/json',
        success: function (response) {
            products = response
            console.log(products)
        },
        error: function (error) {
            console.log(error)
        }
    });

    $("#products").empty();
    products.forEach(function (product) {
        const productHTML = `
            <div class="product-item border p-4 mb-4">
                <h3 class="text-lg font-bold">${product.name}</h3>
                <p class="text-gray-700">${product.description}</p>
                <p class="text-green-500 font-semibold">Price: $${product.price}</p>
            </div>
        `;

        $("#products").append(productHTML)
    });

    $('#logout').on('click', function () {
        sessionStorage.removeItem('userDetails');
        window.location.href = 'login.html';
    });

    $('#profile-picture').on('click', function () {
        window.location.href = 'profile.html';
    })

    $('#sellButton').on('click', function () {
        window.location.href = 'sell_item.html';
    });
});