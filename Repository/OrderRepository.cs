using ANI.Models;
using Microsoft.EntityFrameworkCore;

namespace ANI.Repository;

public class OrderRepository(AniContext context) : IOrderRepository
{
    private readonly AniContext _context = context;

    public async Task<IEnumerable<Order>> GetOrders(Guid userID) => await _context.Orders
                        .Where(order => order.UserID == userID)
                        .ToListAsync();

    public async Task<Order> GetOrder(Guid orderID) 
            => await _context.Orders.FindAsync(orderID) ?? throw new KeyNotFoundException($"Order with id {orderID} not found.");

    public async Task<IEnumerable<Order>> GetOrdersToFarmer(Guid userID) => await _context.Orders.Where(order => order.Product.UserID == userID).ToListAsync();

    public async Task<Order> CreateOrder(Order order)
    {
        _context.Orders.Add(order);

        await _context.SaveChangesAsync();

        return order;
    }
    
    public async Task<Order> UpdateOrder(Order order)
    {
        _context.Entry(order).State = EntityState.Modified;

        await _context.SaveChangesAsync();

        return order;
    }
 
    public async Task<Order> DeleteOrder(Guid orderID)
    {
        Order order = await _context.Orders.FindAsync(orderID) ?? throw new KeyNotFoundException($"Order with id {orderID} not found.");

        _context.Orders.Remove(order);
        await _context.SaveChangesAsync();

        return order;
    }
}
