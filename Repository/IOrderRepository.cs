using ANI.Models;

namespace ANI.Repository;

public interface IOrderRepository
{

    Task<IEnumerable<Order>> GetOrders(Guid userID);


    Task<IEnumerable<Order>> GetOrdersToFarmer(Guid userID);


    Task<Order> GetOrder(Guid orderID);


    Task<Order> CreateOrder(Order order);


    Task<Order> UpdateOrder(Order order);


    Task<Order> DeleteOrder(Guid orderID);
}

