/*
* This file contains all the functions that are used in the application
* 'It's a global thing' :)
*/


export function redirectToProductPage(productID) {
    fetch(`http://localhost:5088/api/Products/${productID}`)
        .then(response => response.json())
        .then(data => {
            sessionStorage.setItem('product', JSON.stringify(data));
            window.location.href = 'product_detail.html';
        })
        .catch(error => {
            console.log(error);
        });
}