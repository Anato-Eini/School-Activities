import * as functions from "./functions.js";

$(document).ready(function () {

    var user = sessionStorage.getItem('userDetails');
    if (!user) {
        window.location.href = 'login.html';
    }

    // product must be constantly cleaned up
    sessionStorage.removeItem('product');

    user = JSON.parse(user);

    if (user.isFarmer == true) {
        $('#sell-item').append(
            '<button id="sellButton" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded text-green-200">Sell Item</button>'
        );
    }

    fetch('http://localhost:5088/api/Products')
        .then(response => response.json())
        .then(data => {
            data.forEach(function (product) {
                $("#products").append(
                    `<div class="bg-white p-6 rounded-lg shadow-lg max-w-xs mx-auto">
                        <img class="h-40 w-full object-cover mb-4 rounded" src="${product.productPictureUrl}" alt="${product.name}">
                        <h2 class="text-xl font-semibold mb-2">${product.name}</h2>
                        <p class="text-gray-700 mb-4">${product.description}</p>
                        <p class="text-green-500 font-semibold">$${product.price}</p>
                        <p class="text-gray-500 mb-4">Stock: ${product.stock}</p>
                        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded buy-now-button" data-product='${product.productID}'>Buy Now</button>
                    </div>`
                );
            })
            $('.buy-now-button').on('click', function () {
                var productId = $(this).data('product');
                console.log(productId);
                console.log("clicked");
                functions.redirectToProductPage(productId);
            });
        })
        .catch(error => {
            console.log(error);
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