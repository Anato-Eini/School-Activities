$(document).ready(function () {
    var user = sessionStorage.getItem("userDetails");

    if (!user) window.location.href = "login.html";

    let cart = sessionStorage.getItem("cart");
    cart = cart ? JSON.parse(cart) : [];

    $('#cart-count').html(cart.length);
    $('#goToCart').on('click', function () {
        window.location.href = 'cart.html';
    });

    var userParsed = JSON.parse(user);

    $("#username").html(userParsed.username);
    $("#first-name").html(userParsed.firstName);
    $("#last-name").html(userParsed.lastName);
    $("#phone-number").html(userParsed.phoneNumber);
    $("#address").html(userParsed.address);
    $("#profile-picture").attr("src", userParsed.profilePictureUrl);
    $('#profile-picture-desk').attr('src', userParsed.profilePictureUrl);
    if (userParsed.isStaff == true && userParsed.isFarmer == true) {
        $('#isStaff').html('Staff');
        $('#isFarmer').html('Farmer');
    } else {
        $('#splitter').html('');
        if (userParsed.isStaff == true) {
            $('#isStaff').html('Staff');
        } else if (userParsed.isFarmer == true) {
            $('#isFarmer').html('Farmer');
        }
    }



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
