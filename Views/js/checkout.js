$(document).ready(function () {
  let user = sessionStorage.getItem("userDetails");

  if (!user) window.location.href = "login.html";

  user = JSON.parse(user);

  let cart = sessionStorage.getItem("cart");
  cart = cart ? JSON.parse(cart) : [];

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
            </tr>`
      );
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
        confirmButtonText: "Go to Shop",
        confirmButtonColor: "#436850",
        allowEscapeKey: true,
        customClass: "bg-[#FBFADA]",
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
          });
        },
      });
    });

    cart = [];
    sessionStorage.setItem("cart", JSON.stringify(cart));

    window.location.href = "home.html";
  });
});
