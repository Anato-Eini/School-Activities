// import Swal from "sweetalert2/dist/sweetalert2.js";
// import "sweetalert2/src/sweetalert2.css";

$(document).ready(function () {
  let user = sessionStorage.getItem("userDetails");

  if (!user) window.location.href = "login.html";

  user = JSON.parse(user);

  let cart = sessionStorage.getItem("cart");
  cart = cart ? JSON.parse(cart) : [];

  let total = 0;

  let fetchPromises = cart.map(async function (product) {
    try {
      const response = await fetch(
        "http://localhost:5088/api/Products/" + product.productID
      );
      const data = await response.json();
      $("#orders").append(
        `<tr>
              <td class="py-3 flex items-center space-x-4">
                <img
                  src="${data.productPictureUrl}"
                  alt="${data.name}"
                  class="h-12 w-12 rounded-md"
                />
                <div
                  class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4"
                >
                ${data.name}
                </div>
              </td>
              <td class="text-center text-gray-700">${data.price} Php/kg</td>
              <td class="text-center text-gray-700">${product.quantity}</td>
              <td class="text-right text-gray-700">${
                data.price * product.quantity
              }Php</td>
            <td class="text-right">
              <button
                class="cancelOrder bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                data-product="${product.productID}"
              >
                Cancel
              </button>
            </td>
            </tr>`
      );

      total += data.price * product.quantity;
      $("#total").html(total + "Php");
    } catch (error) {
      alert("Error: " + error);
    }
  });

  Promise.all(fetchPromises).then(() => {
    $(".cancelOrder").on("click", function () {
      let productID = $(this).data("product");
      cart = cart.filter((product) => product.productID !== productID);
      sessionStorage.setItem("cart", JSON.stringify(cart));
      window.location.reload();
    });
  });

  $("#placeOrder").on("click", function () {
    if (cart.length === 0) {
      // alert("Cart is empty!");
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Cart is empty!",
        confirmButtonText: "Let's go shopping!",
        confirmButtonColor: "#436850",
        allowEscapeKey: true,
        customClass: "bg-[#FBFADA]",
        willClose: () => {
          window.location.href = "home.html";
        },
      });
      return;
    }

    cart.forEach(function (product) {
      $.ajax({
        url: "http://localhost:5088/api/Orders",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
          productID: product.productID,
          userID: user.userID,
          quantity: product.quantity,
        }),
        success: function () {
          // alert("Order placed!");
          Swal.fire({
            icon: "success",
            title: "SUCCESS!",
            text: "Order successfully placed",
            confirmButtonText: "YEY!",
            confirmButtonColor: "#436850",
            allowEscapeKey: true,
            customClass: "bg-[#FBFADA]",
            preConfirm: () => {
              cart = [];
              sessionStorage.setItem("cart", JSON.stringify(cart));
              window.location.href = "home.html";
            },
          });
        },
        error: function () {
          // alert("Error placing order!");
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Error placing order",
            confirmButtonText: "Oh no...",
            confirmButtonColor: "#436850",
            allowEscapeKey: true,
            customClass: "bg-[#FBFADA]",
            preConfirm: () => {
              window.location.href = "home.html";
            },
          });
        },
      });
    });
  });

  $("#contact-number").html(user.phoneNumber);
  $("#customer-name").html(user.firstName + " " + user.lastName);
});
