$(document).ready(function () {
    $('#login-form').on('submit', function (e) {
        e.preventDefault();
        var formData = $(this).serializeArray();
        var jsonData = {};

        $.each(formData, function () {
            jsonData[this.name] = this.value;
        });

        $.ajax({
            url: 'http://localhost:5088/api/UserDTOs/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            success: function (response) {
                sessionStorage.setItem('userDetails', JSON.stringify(response));
                window.location.href = 'home.html';
            },
            error: function (xhr, status, error) {
                $('#login-error').html('An error occurred: ' + error);
            }
        });
    });
});