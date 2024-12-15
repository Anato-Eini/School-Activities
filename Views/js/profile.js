$(document).ready(function () {
  var user = sessionStorage.getItem("userDetails");

  if (!user) window.location.href = "login.html";

  var userParsed = JSON.parse(user);

  $("#username").html(userParsed.username);
  $("#first-name").html(userParsed.firstName);
  $("#last-name").html(userParsed.lastName);
  $("#phone-number").html(userParsed.phoneNumber);
  $("#address").html(userParsed.address);
  $("#is-staff").html(userParsed.isStaff.toString());
  $("#is-farmer").html(userParsed.isFarmer.toString());
  $("#profile-picture").attr("src", userParsed.profilePictureUrl);

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
