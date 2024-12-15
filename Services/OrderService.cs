using ANI.DTO;
using ANI.Models;
using ANI.Repository;
using AutoMapper;

namespace ANI.Services;

public class OrderService(IOrderRepository orderRepository, IMapper mapper, IProductRepository productRepository, IUserRepository userRepository) : IOrderService
{
    private readonly IMapper _mapper = mapper;
    private readonly IOrderRepository _orderRepository = orderRepository;
    private readonly IProductRepository _productRepository = productRepository;
    private readonly IUserRepository _userRepository = userRepository;

    public async Task<IEnumerable<OrderResponseDTO>> GetOrders(Guid userID)
    {
        return _mapper.Map<IEnumerable<OrderResponseDTO>>(await _orderRepository.GetOrders(userID));
    }

    public async Task<OrderResponseDTO> GetOrder(Guid orderID)
    {
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.GetOrder(orderID));
    }

    public async Task<IEnumerable<OrderResponseDTO>> GetOrdersToFarmer(Guid userID) => _mapper.Map<IEnumerable<OrderResponseDTO>>(await _orderRepository.GetOrdersToFarmer(userID));
    
    public async Task<OrderResponseDTO> CreateOrder(OrderCreateDTO order)
    {
        Product product = await _productRepository.GetProduct(order.ProductID);

        if (product.Stock < order.Quantity)
            throw new InvalidOperationException("Not enough stock");

        product.Stock -= order.Quantity;
        await _productRepository.UpdateProduct(product);

        Order? existingOrder = await _orderRepository.GetOrder(order.UserID, order.ProductID);

        if (existingOrder != null)
        {
            existingOrder.Quantity += order.Quantity;
            return _mapper.Map<OrderResponseDTO>(await _orderRepository.UpdateOrder(existingOrder));
        } 
        Order newOrder = _mapper.Map<Order>(order);

        newOrder.User = await _userRepository.GetUser(order.UserID);
        newOrder.Product = product;

        return _mapper.Map<OrderResponseDTO>(await _orderRepository.CreateOrder(newOrder));
    }

    public async Task<OrderResponseDTO> UpdateOrder(OrderUpdateDTO order)
    {
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.UpdateOrder(_mapper.Map<Order>(order)));
    }

    public async Task<OrderResponseDTO> DeleteOrder(Guid orderID)
    {
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.DeleteOrder(orderID));
    }

    public async Task<OrderResponseDTO> FinishOrder(Guid orderID){
        Order order = await _orderRepository.GetOrder(orderID);
        order.FinishedAt = DateTime.Now;
        order.IsFinished = true;
        return _mapper.Map<OrderResponseDTO>(await _orderRepository.UpdateOrder(order));
    }

    public async Task<OrderResponseDTO> CancelOrder(Guid orderID){
        Order order = await _orderRepository.GetOrder(orderID);
        Product product = await _productRepository.GetProduct(order.ProductID);

        product.Stock += order.Quantity;

        await _productRepository.UpdateProduct(product);
        await _orderRepository.DeleteOrder(orderID);

        return _mapper.Map<OrderResponseDTO>(order);
    }
}
