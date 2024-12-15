$(document).ready(function () {
    let user = sessionStorage.getItem("userDetails");
    if (!user) window.location.href = "login.html";

    user = JSON.parse(user);

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

    fetch("http://localhost:5088/api/Orders/farmer/" + user.userID) // Fetch data type is OrderResponseDTO
        .then((response) => response.json())
        .then((data) => {
            data.forEach(function (order) {
                let buyerName;
                let productName;
                let productPic;

                Promise.all([
                    fetch("http://localhost:5088/api/Products/" + order.productID)
                        .then((productResponse) => productResponse.json())
                        .then((productData) => {
                            productName = productData.name;
                            productPic = productData.productPictureUrl;
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
                ]).then(() => {
                    $("#orders").prepend(
                        `<tr>
                            <td class="py-3 flex items-center space-x-4">
                            <img
                            src="${productPic}"
                            alt="${productName}"
                            class="h-12 w-12 rounded-md"
                            />
                            <div
                            class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4"
                            >
                            ${productName}
                            </div>
                            </td>
                            <td class="text-center text-gray-700">${order.quantity}</td>
                            <td class="text-center text-gray-700">${buyerName}</td>
                            <td class="text-right text-gray-700">${order.createdAt}</td>
                            <td class="text-center">
                            <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded finished" data-order="${order.orderID}">
                            Finished
                            </button>
                            <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded cancel" data-order="${order.orderID}">
                            Cancel
                            </button>
                            </td>
                           </tr>`
                    )
                        
                    $('.finished').click(function () {
                        const orderID = $(this).data("order")
                            fetch("http://localhost:5088/api/Orders/finished/" + orderID, {
                                method: "PUT",
                            }).then(() => {
                                alert("Order status updated successfully");
                                window.location.reload();
                            }).catch((error) => alert("Error updating order status:", error));
                    });

                    $('.cancel').click(function () {
                        const orderID = $(this).data("order")
                        fetch("http://localhost:5088/api/Orders/cancel/" + orderID, {
                            method: "PUT",
                        }).then(() => {
                            alert("Order status updated successfully");
                            window.location.reload();
                        }).catch((error) => alert("Error updating order status:", error));
                    });
                    
                }).catch((error) => alert("Error processing order:", error));
            });
        }).catch((error) => alert("Error fetching orders:", error));
});
