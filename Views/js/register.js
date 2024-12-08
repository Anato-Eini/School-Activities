$(document).ready(function () {
    $('#register-form').submit(function (e) {
        e.preventDefault();

        var formData = new FormData(this);

        $.ajax({
            url: 'http://localhost:5088/api/UserDTOs',
            type: 'POST',
            contentType: false, 
            processData: false, 
            data: formData,
            success: function (response) {
                if (response !== null) {
                    window.location.href = 'login.html';
                } else {
                    $('#register-error').html(response);
                }
            },
            error: function (response) {
                $('#register-error').html(response);
            }
        });
    });
});