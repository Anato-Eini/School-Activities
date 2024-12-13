using ANI.DTO;

namespace ANI.Services;

public interface IOrderService
{
   //get all orders with matching userID foreign key
    Task<IEnumerable<OrderResponseDTO>> GetOrders(Guid userID);


    Task<IEnumerable<OrderResponseDTO>> GetOrdersToFarmer(Guid userID);

   
    Task<OrderResponseDTO> GetOrder(Guid orderID);

   
    Task<OrderResponseDTO> CreateOrder(OrderCreateDTO order);

   
    Task<OrderResponseDTO> UpdateOrder(OrderUpdateDTO order);

    
    Task<OrderResponseDTO> DeleteOrder(Guid orderID);
}
