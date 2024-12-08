$(document).ready(function () {
    var user = sessionStorage.getItem('userDetails');

    if (!user)
        window.location.href = 'login.html';

    var userParsed = JSON.parse(user);

    $('#username').html(userParsed.username);
    $('#first-name').html(userParsed.firstName);
    $('#last-name').html(userParsed.lastName);
    $('#phone-number').html(userParsed.phoneNumber);
    $('#address').html(userParsed.address);
    $('#is-staff').html(userParsed.isStaff.toString());
    $('#is-farmer').html(userParsed.isFarmer.toString());
    $('#profile-picture').attr('src', userParsed.profilePictureUrl);
});