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
                if (response !== null && response.token) {
                    localStorage.setItem('authToken', response.token);
                    window.location.href = 'home.html'; 
                } else {
                    $('#login-error').html(response);
                }
            },
            error: function (xhr, status, error) {
                $('#login-error').html('An error occurred: ' + error);
            }
        });
    });
});