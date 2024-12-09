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
    $('#isFarmer').prop('checked', user.isFarmer);

    $('#update-profile-form').submit(function (e) {
        e.preventDefault();

        var formData = new FormData(this);
        formData.set('isFarmer', $('#isFarmer').is(':checked'));

        $.ajax({
            url: 'http://localhost:5088/api/UserDTOs/' + user.userID,
            type: 'PUT',
            contentType: false, 
            processData: false, 
            data: formData,
            success: function (response) {
                sessionStorage.setItem('userDetails', JSON.stringify(response));
                window.location.href = 'profile.html';
            },
            error: function (response) {
                $('#error-messages').html(response);
            }
        });

    });
});