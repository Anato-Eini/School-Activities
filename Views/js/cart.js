$(document).ready(function () {
    fetch('http://localhost:5088/api/Orders')
        .then(response => response.json())
        .then(data => {
            //change later
            data.forEach(function (product) {
                functions.displayOrderDetail($("#orders"), order);
            })
            //change later
            $('.buy-now-button').on('click', function () {
                functions.redirectToProductPage($(this).data('product'));
            });

        })
        .catch(error => {
            console.log(error);
        });
})