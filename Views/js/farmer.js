$(document).ready(function () {
    let user = sessionStorage.getItem('userDetails');
    if (!user)
        window.location.href = 'login.html';

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
    
    fetch('http://localhost:5088/api/Orders/farmer/' + user.userID) // Fetch data type is OrderResponseDTO
        .then(response => response.json())
        .then(data => {
            data.forEach(function (order) {
                console.log(order);

                let buyerName;
                let productName;
                let productPic;

                Promise.all([
                    fetch('http://localhost:5088/api/Products/' + order.productID)
                        .then(productResponse => productResponse.json())
                        .then(productData => {
                            productName = productData.name;
                            productPic = productData.productPictureUrl;
                        })
                        .catch(error => {
                            console.log('Error fetching product data:', error);
                        }),
                    fetch('http://localhost:5088/api/UserDTOs/' + order.userID)
                        .then(userResponse => userResponse.json())
                        .then(userData => {
                            buyerName = userData.username;
                        })
                        .catch(error => {
                            console.log('Error fetching user data:', error);
                        })
                ])
                    .then(() => {
                        $('#orders').prepend(
                            `<div class="bg-gray-200 border border-gray-300 p-2 rounded-lg mb-2">
                        <p class="text-lg font-bold">Product: ${productName}</p>
                        <p class="text-lg font-bold">Buyer: ${buyerName}</p>
                        <p>Quantity: ${order.quantity}</p>
                        <p>Date ordered: ${order.createdAt}</p>
                        <img src="${productPic}" alt="Product Image" class="w-full h-auto rounded-lg mt-2">
                    </div>`
                        );
                    })
                    .catch(error => {
                        console.log('Error processing order:', error);
                    });
            });
        })
        .catch(error => {
            console.log('Error fetching orders:', error);
        });

});