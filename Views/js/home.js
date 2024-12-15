import * as functions from "./functions.js";

$(document).ready(function () {
  var user = sessionStorage.getItem("userDetails");

  if (!user) {
    window.location.href = "login.html";
  }

  sessionStorage.removeItem("product");

  let cart = sessionStorage.getItem("cart");

  user = JSON.parse(user);

  $('#profile-picture').attr('src', user.profilePictureUrl);
  
    fetch('http://localhost:5088/api/userDTOs/farmers')
        .then((response) => response.json())
        .then((data) => {
            data.forEach(function (farmer) {
                $('#farmers').append(
                    `<div class="flex flex-col items-center text-center space-y-1">
                        <img
                        src="${farmer.profilePictureUrl}"
                        alt="Farmer"
                        class="h-20 w-20 rounded-full object-cover"
                        />
                        <p class="text-[#436850] text-sm font-medium">${farmer.username}</p>
                    </div>`
                )
            });
        }
        );


  if (user.isFarmer == true) {
    $("#farmer-buttons").append(
      `<a
        href="sell_item.html"
        class="text-sm/6 font-semibold text-[#436850] hover:text-gray-900"
      >Sell Items</a>
      <a
        href="farmer.html"
        class="text-sm/6 font-semibold text-[#436850] hover:text-gray-900"
      >&nbsp&nbsp&nbspOrders</a>`
    );
  }

  /*             <button id="myProducts" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-blue-200">My Products</button>` */
  $("#orders").on("click", function () {
    window.location.href = "farmer.html";
  });

  fetch("http://localhost:5088/api/Products")
    .then((response) => response.json())
    .then((data) => {
      data.forEach(function (product) {
        functions.displayProductDetail($("#products"), product);
      });

      $(".buy-now-button").on("click", function () {
        functions.redirectToProductPage($(this).data("product"));
      });
    })
    .catch((error) => {
      console.log(error);
    });

  $("#logout").on("click", function () {
    sessionStorage.removeItem("userDetails");
    sessionStorage.removeItem("cart");
    window.location.href = "login.html";
  });

  $("#profile-picture").on("click", function () {
    window.location.href = "profile.html";
  });

  $("#sellButton").on("click", function () {
    window.location.href = "sell_item.html";
  });

  $("#cart-count").html(cart ? JSON.parse(cart).length : 0);

  $("#cart-count").on("click", function () {
    window.location.href = "checkout.html";
  });

  const mobileMenuButton = document.getElementById("mobile-menu-button");
  const mobileMenu = document.getElementById("mobile-menu");
  const closeMobileMenuButton = document.getElementById("close-mobile-menu");

  mobileMenuButton.addEventListener("click", () => {
    mobileMenu.classList.toggle("hidden");
  });

  closeMobileMenuButton.addEventListener("click", () => {
    mobileMenu.classList.add("hidden");
  });
});
