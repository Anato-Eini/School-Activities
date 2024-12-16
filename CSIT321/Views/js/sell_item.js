$(document).ready(function () {
  var user = sessionStorage.getItem("userDetails");

  if (!user) {
    window.location.href = "login.html";
  }

  if (user.isFarmer == false) {
    window.location.href = "home.html";
  }

  user = JSON.parse(user);

  $("#sell-item-form").on("submit", function (e) {
    e.preventDefault();

    var formData = new FormData(this);
    formData.append("userID", user.userID);

    $.ajax({
      url: "http://localhost:5088/api/Products",
      type: "POST",
      contentType: false,
      processData: false,
      data: formData,
      success: function (response) {
        window.location.href = "home.html";
      },
      error: function (xhr, status, error) {
        $("#sell-item-error").html("An error occurred: " + error);
      },
    });
  });

  document.getElementById("menu-toggle").addEventListener("click", function () {
    document.getElementById("mobile-menu").classList.remove("translate-x-full");
    document.getElementById("mobile-menu").classList.add("translate-x-0");
  });

  document.getElementById("menu-close").addEventListener("click", function () {
    document.getElementById("mobile-menu").classList.remove("translate-x-0");
    document.getElementById("mobile-menu").classList.add("translate-x-full");
  });
});
