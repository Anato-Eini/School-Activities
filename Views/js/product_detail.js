$(document).ready(function () {
    let user = sessionStorage.getItem("userDetails");
    if (!user) window.location.href = "login.html";

    user = JSON.parse(user);
    let product = sessionStorage.getItem("product");

    if (!product) window.location.href = "home.html";

    product = JSON.parse(product);

    let cart = sessionStorage.getItem("cart");
    cart = cart ? JSON.parse(cart) : [];

    $("#name").html(product.name);
    $("#description").html(product.description);
    $("#price").html(product.price);
    $("#stock").html(product.stock);
    $("#productPictureUrl").attr("src", product.productPictureUrl);

    if (user.userID === product.userID) {
        $("#owner").append(
            `<button id="editButton" class="bg-[#436850] text-white hover:bg-[#365c45] focus:ring-4 focus:outline-none focus:ring-[#365c45] font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-white dark:text-[#436850] dark:hover:bg-[#436850] dark:hover:text-white dark:focus:ring-[#365c45]">Edit</button>
            <button id="deleteButton" class="bg-[#436850] hover:bg-[#436850] text-white font-bold py-2 px-4 rounded text-white">Delete</button>`
        );
    }

    fetch("http://localhost:5088/api/Ratings/product/" + product.productID)
        .then((response) => response.json())
        .then((data) => {
            data.forEach(function (rating) {
                $("#comments").prepend(
                    `<div class="bg-gray-200 border border-gray-300 p-2 rounded-lg mb-2">
                        <p class="text-lg font-bold">${rating.username}</p>
                        <p>${rating.content == null ? "" : rating.content}</p>
                        <p>Rating: ${rating.stars}</p>` +
                        (rating.imageUrl == null
                            ? ""
                            : `<img 
                                src="${rating.imageUrl}" 
                                alt="Product Image" 
                                class="w-full h-auto rounded-lg mt-2">`) +
                        (rating.username == user.username) ? 
                            `<button 
                                class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" 
                                onclick="deleteRating(${rating.ratingID})">Delete</button>` : ""
                        `</div>`
                );
            });
        })
        .catch((error) => {
            console.log(error);
        });

    $("#ratingForm").on("submit", function (e) {
        e.preventDefault();

        var formData = new FormData(this);
        formData.append("userID", user.userID);
        formData.append("productID", product.productID);

        $.ajax({
            url: "http://localhost:5088/api/Ratings",
            type: "POST",
            contentType: false,
            processData: false,
            data: formData,
            success: function (response) {
                alert("Rating added!");
                window.location.reload();
            },
            error: function (xhr) {
                if (xhr.status === 409) {
                    alert("You have already created a rating!");
                } else {
                    alert("Error adding rating!");
                }
            },
        });
    });

    let currentQuantity = 1;

    $("#button-operation").prepend(
        product.userID !== user.userID
            ? `<div class="flex items-center mt-6"> 
            <span class="text-lg font-medium mr-4">Quantity</span>
            <div class="flex items-center border rounded-lg px-2">
                <button class="text-gray-700 text-lg font-bold" id="dec-quantity">-</button>
                <span class="px-4 text-gray-700" id="quantity-count">1</span>
                <button class="text-gray-700 text-lg font-bold" id="add-quantity">+</button>
            </div>
            </div>
            <button
            class="bg-green-700 text-white hover:bg-green-800 focus:ring-4 focus:outline-none focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mt-5"
            id="addToCartButton">
            Add to Cart
            </button>
            <button id="openModalButton"
            class="bg-[#436850] mt-5 text-white hover:bg-[#365c45] focus:ring-4 focus:outline-none focus:ring-[#365c45] font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-white dark:text-[#436850] dark:hover:bg-[#436850] dark:hover:text-white dark:focus:ring-[#365c45]">
            Add Rating
            </button>
            `
            : `<button id="editButton" class="bg-[#436850] hover:bg-green-500 text-white font-bold py-2 px-4 rounded text-white">Edit</button>
            <button id="deleteButton" class="bg-red-700 text-white hover:bg-red-500 font-bold py-2 px-4 rounded">Delete</button>
            `
    );

    $("#editButton").on("click", function () {
        sessionStorage.setItem("product", JSON.stringify(product));
        window.location.href = "edit_product.html";
    });

    $('#deleteButton').on('click', function () {
        $.ajax({
            url: `http://localhost:5088/api/Products/${product.productID}`,
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

    $('#add-quantity').on('click', function () {
        if (product.stock > currentQuantity)
            currentQuantity++;
        $('#quantity-count').html(currentQuantity);
    });

    $('#dec-quantity').on('click', function () {
        if (currentQuantity > 1)
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

        alert('Product added to cart');

        currentQuantity = 1;
        $('#quantity-count').html(currentQuantity);

        window.location.reload();
    });

    const openModalButton = document.getElementById("openModalButton");
    const closeModalButton = document.getElementById("closeModalButton");
    const ratingModal = document.getElementById("ratingModal");

    openModalButton.addEventListener("click", () => {
        ratingModal.classList.remove("hidden");
    });

    closeModalButton.addEventListener("click", () => {
        ratingModal.classList.add("hidden");
    });
});
