$(document).ready(function () {
  $("#login-form").on("submit", function (e) {
    e.preventDefault();
    var formData = $(this).serializeArray();
    var jsonData = {};

    $.each(formData, function () {
      jsonData[this.name] = this.value;
    });

    $.ajax({
      url: "http://localhost:5088/api/UserDTOs/login",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(jsonData),
      success: function (response) {
        sessionStorage.setItem("userDetails", JSON.stringify(response));
        window.location.href = "home.html";
      },
      error: function (xhr, status, error) {
        $("#login-error").html("An error occurred: " + error);
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
