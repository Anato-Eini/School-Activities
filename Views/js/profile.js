$(document).ready(function () {
    var user = sessionStorage.getItem('userDetails');

    if (!user) 
        window.location.href = 'login.html';

    $('#username').html(JSON.parse(user).username);
    $('#first-name').html(JSON.parse(user).firstName);
    $('#last-name').html(JSON.parse(user).lastName);
    $('#phone-number').html(JSON.parse(user).phoneNumber);
    $('#address').html(JSON.parse(user).address);
    $('#is-staff').html(JSON.parse(user).isStaff);
    $('#is-farmer').html(JSON.parse(user).isFarmer);
});