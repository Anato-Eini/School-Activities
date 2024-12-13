import * as functions from "./functions.js";


$(document).ready(function () {
    var user = sessionStorage.getItem('userDetails')
    user = JSON.parse(user)
    var orders = []


    $.ajax({
        url: 'http://localhost:5088/api/Orders/',
        type: 'GET',
        contentType: 'application/json',
        data: {userID : user.userID},
        success: function (response) {
            orders = response
            console.log(orders)
            var obj = document.getElementById('orders')
            functions.displayOrders(obj,orders)
        },
        error: function (error) {
            console.log(error)
        }
    });



})