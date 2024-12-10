using ANI.DTO;

namespace ANI.Services;

public interface IOrderService
{
   //get all orders with matching userID foreign key
    Task<IEnumerable<OrderDTO>> GetOrders(Guid userID);

   
    Task<OrderDTO> GetOrder(Guid orderID);

   
    Task<OrderDTO> CreateOrder(OrderDTO order);

   
    Task<OrderDTO> UpdateOrder(OrderDTO order);

    
    Task<OrderDTO> DeleteOrder(Guid orderID);
}
