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

    $('#logout').on('click', function () {
        sessionStorage.removeItem('userDetails');
        window.location.href = 'login.html';
    });

    $('#sellButton').on('click', function () {
        window.location.href = 'sell_item.html';
    });
});