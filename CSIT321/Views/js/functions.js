/*
 * This file contains all the functions that are used in the application
 * 'It's a global thing' :)
 */

export function redirectToProductPage(productID) {
  fetch(`http://localhost:5088/api/Products/${productID}`)
    .then((response) => response.json())
    .then((data) => {
      sessionStorage.setItem("product", JSON.stringify(data));
      window.location.href = "product_detail.html";
    })
    .catch((error) => {
      console.log(error);
    });
}

export function displayProductDetail(htmlObject, product) {
  htmlObject.append(
    `<div class="bg-white p-6 rounded-lg shadow-lg max-w-xs mx-auto">
            <img class="h-40 w-full object-cover mb-4 rounded" src="${product.productPictureUrl}" alt="${product.name}">
            <h2 class="text-xl font-semibold mb-2">${product.name}</h2>
            <p class="text-gray-700 mb-4">${product.description}</p>
            <p class="text-green-500 font-semibold">${product.price} Php</p>
            <p class="text-gray-500 mb-4">Stock: ${product.stock}</p>
            <button class="bg-green-700 hover:bg-green-500 text-white font-bold py-2 px-4 rounded buy-now-button" data-product='${product.productID}'>Buy Now</button>
        </div>`
  );
}

export function displayOrders(htmlObject, order) {
  //change later
  console.log(order);
  var currUser;
  order.forEach(function (item) {
    htmlObject.append(item.createdAt);
  });
}
