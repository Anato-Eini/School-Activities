$(document).ready(function () {
    let user = sessionStorage.getItem('userDetails');
    if (!user)
        window.location.href = 'login.html';

    user = JSON.parse(user);
    let product = sessionStorage.getItem('product');

    if (!product)
        window.location.href = 'home.html';

    product = JSON.parse(product);

    let cart = sessionStorage.getItem('cart');
    cart = cart ? JSON.parse(cart) : [];

    $('#name').html(product.name);
    $('#description').html(product.description);
    $('#price').html(product.price);
    $('#stock').html(product.stock);
    $('#productPictureUrl').attr('src', product.productPictureUrl);

    if (user.userID === product.userID) {
        $('#owner').append(
            `<button id="editButton" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-blue-200">Edit</button>
            <button id="deleteButton" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded text-red-200">Delete</button>`
        );
    }

    $('#editButton').on('click', function () {
        sessionStorage.setItem('product', JSON.stringify(product));
        window.location.href = 'edit_product.html';
    });

    fetch('http://localhost:5088/api/Ratings/product/' + product.productID)
        .then(response => response.json())
        .then(data => {
            data.forEach(function (rating) {
                $('#comments').prepend(
                    `<div class="bg-gray-200 border border-gray-300 p-2 rounded-lg mb-2">
                        <p class="text-lg font-bold">${rating.username}</p>
                        <p>${rating.content == null ? "" : rating.content}</p>
                        <p>Rating: ${rating.stars}</p>` +
                            (rating.imageUrl == null ? "" : `<img src="${rating.imageUrl}" alt="Product Image" class="w-full h-auto rounded-lg mt-2">`) + 
                        `</div>`
                );
            })
        })
        .catch(error => {
            console.log(error);
        });

    $('#deleteButton').on('click', function () {
        $.ajax({ url: `http://localhost:5088/api/Products/${product.productID}`,
            type: 'DELETE',
            success: function () {
                alert('Product deleted!');
                window.location.href = 'home.html';
            },
            error: function () {
                alert('Error deleting product!');
            }
        })
    });

    $('#ratingForm').on('submit', function (e) {
        e.preventDefault();

        var formData = new FormData(this);
        formData.append('userID', user.userID);
        formData.append('productID', product.productID);

        $.ajax({
            url: 'http://localhost:5088/api/Ratings',
            type: 'POST',
            contentType: false,
            processData: false,
            data: formData,
            success: function (response) {
                alert('Rating added!');
                window.location.reload();
            },
            error: function (xhr) {
                if (xhr.status === 409) {
                    alert("You have already created a rating!")
                } else {
                    alert('Error adding rating!')
                }
            }
        });
    });

    let currentQuantity = 1;

    $('#add-quantity').on('click', function () {
        if(product.stock > currentQuantity)
            currentQuantity++;
        $('#quantity-count').html(currentQuantity);
    });

    $('#dec-quantity').on('click', function () {
        if(currentQuantity > 1)
            currentQuantity--;
        $('#quantity-count').html(currentQuantity);
    });

    $('#addToCartButton').on('click', function () {
        let productInCart = cart.find(p => p.productID === product.productID);

        if (productInCart) {

            if (productInCart.quantity + currentQuantity > product.stock) {
                alert('Not enough stock!');
                return;
            }
            productInCart.quantity += currentQuantity;
        } else {
            cart.push({
                productID: product.productID,
                quantity: currentQuantity
            });
        }

        sessionStorage.setItem('cart', JSON.stringify(cart));
        sessionStorage.removeItem('product');

        alert('Product added to cart');

        currentQuantity = 1;
        $('#quantity-count').html(currentQuantity);

        window.location.reload();
    });
});