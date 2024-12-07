$(document).ready(function() {
    $('#register-form').submit(function(e) {
        e.preventDefault();
        var formData = $(this).serializeArray();
        var jsonData = {};
        $.each(formData, function () {
            jsonData[this.name] = this.value;
        });
        $.ajax({
            url: 'http://localhost:5088/api/UserDTOs',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            success: function(response) {
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