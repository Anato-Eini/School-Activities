$(document).ready(function() {
    $('#register-form').submit(function(e) {
        e.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            url: '',
            type: 'POST',
            data: formData,
            success: function(response) {
                if (response == 'success') {
                    window.location.href = 'login.html';
                } else {
                    $('#register-error').html(response);
                }
            }
        });
    });
});