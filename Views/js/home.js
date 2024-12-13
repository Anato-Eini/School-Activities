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

    let cart = [];

    fetch('http://localhost:5088/api/Products')
        .then(response => response.json())
        .then(data => {
            data.forEach(function (product) {
                    functions.displayProductDetail($("#products"), product);
            })

            $('.buy-now-button').on('click', function () {
                functions.redirectToProductPage($(this).data('product'));
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

    $('#goToCart').on('click', function () {
        window.location.href = 'checkout.html';
    })
});