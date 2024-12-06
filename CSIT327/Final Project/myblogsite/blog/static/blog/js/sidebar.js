$(document).ready(function () {
    $('#forum-filter').change(function () {
        window.location.href = `/home/${$(this).val()}/1`;
    })
});

