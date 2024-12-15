$(document).ready(function () {
  let user = sessionStorage.getItem("userDetails");
  if (!user) window.location.href = "login.html";

  user = JSON.parse(user);

  document.getElementById("menu-toggle").addEventListener("click", function () {
    document.getElementById("mobile-menu").classList.remove("translate-x-full");
    document.getElementById("mobile-menu").classList.add("translate-x-0");
  });

  document.getElementById("menu-close").addEventListener("click", function () {
    document.getElementById("mobile-menu").classList.remove("translate-x-0");
    document.getElementById("mobile-menu").classList.add("translate-x-full");
  });
  //assume user.isFarmer

  /*
    OrderResponseDTO

    public Guid OrderID { get; set; }
    public int Quantity { get; set; }
    public Guid ProductID { get; set; }
    public Guid UserID { get; set; }
    public DateTime CreatedAt { get; set; }
    public Guid ProductID { get; set; }



     ProductResponseDTO

     public string Name { get; set; } = null!;
     public string ProductPictureUrl { get; set; } = null!;
    */
  let total = 0;
  fetch("http://localhost:5088/api/Orders/farmer/" + user.userID) // Fetch data type is OrderResponseDTO
    .then((response) => response.json())
    .then((data) => {
      data.forEach(function (order) {
        let buyerName;
        let productName;
        let productPic;
        let productPrice;

        Promise.all([
          fetch("http://localhost:5088/api/Products/" + order.productID)
            .then((productResponse) => productResponse.json())
            .then((productData) => {
              productName = productData.name;
              productPic = productData.productPictureUrl;
              productPrice = productData.price;
            })
            .catch((error) => {
              console.log("Error fetching product data:", error);
            }),
          fetch("http://localhost:5088/api/UserDTOs/" + order.userID)
            .then((userResponse) => userResponse.json())
            .then((userData) => {
              buyerName = userData.username;
            })
            .catch((error) => {
              console.log("Error fetching user data:", error);
            }),
        ])
          .then(() => {
            $("#orders").prepend(
              `<tr>
                <td class="py-3 flex items-center space-x-4">
                  <img
                    src="${productPic}"
                    alt="${productName}"
                    class="h-12 w-12 rounded-md"
                  />
                  <div>
                    <p class="font-medium text-gray-700">${productName}</p>
                  </div>
                </td>
                <td class="text-center text-gray-700">${order.quantity}</td>
                <td class="text-center text-gray-700">${buyerName}</td>
                <td class="text-right text-gray-700">${order.createdAt}</td>
              </tr>`
            );
            total += productPrice * order.quantity;
            $("#total").html(total + " Php");
          })
          .catch((error) => {
            console.log("Error processing order:", error);
          });
      });
    })
    .catch((error) => {
      console.log("Error fetching orders:", error);
    });
});
