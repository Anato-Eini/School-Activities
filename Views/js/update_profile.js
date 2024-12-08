$(document).ready(function () {
    var user = sessionStorage.getItem('userDetails');

    if (!user)
        window.location.href = 'login.html';

    user = JSON.parse(user);

    $('#username').val(user.username);
    $('#firstName').val(user.firstName);
    $('#lastName').val(user.lastName);
    $('#phoneNumber').val(user.phoneNumber);
    $('#address').val(user.address);

});